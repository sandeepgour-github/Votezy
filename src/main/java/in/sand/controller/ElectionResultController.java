package in.sand.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.sand.dto.ElectionResultRequestDTO;
import in.sand.dto.ElectionResultResponseDTO;
import in.sand.entity.ElectionResult;
import in.sand.service.ElectionResultService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/elections")
@CrossOrigin
public class ElectionResultController {

	private ElectionResultService electionResultService;

	public ElectionResultController(ElectionResultService electionResultService) {
		this.electionResultService = electionResultService;
	}
	
	@PostMapping("/declare")
	public ResponseEntity<ElectionResultResponseDTO> declareElectionResult(@RequestBody @Valid ElectionResultRequestDTO electionResultRequestDTO){
		ElectionResult electionResult=electionResultService.declaElectionResult(electionResultRequestDTO.getElectionName());
		ElectionResultResponseDTO electionResultResponse=new ElectionResultResponseDTO(electionResult.getElectionName(), electionResult.getTotalVote(), electionResult.getWinnerId(), electionResult.getWinnerVoteCount());
		return new ResponseEntity<ElectionResultResponseDTO>(electionResultResponse,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<ElectionResult>> getElectionResult(@RequestBody @Valid ElectionResultRequestDTO electionResultRequestDTO){
		List<ElectionResult> electionResults=electionResultService.getAllElectionResult();
		return new ResponseEntity<List<ElectionResult>>(electionResults,HttpStatus.OK);
	}
	
}
