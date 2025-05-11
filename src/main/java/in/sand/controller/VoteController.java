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

import in.sand.dto.VoteRequestDTO;
import in.sand.dto.VoteResponseDTO;
import in.sand.entity.Vote;
import in.sand.service.VoteService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/votes")
@CrossOrigin
public class VoteController {

	private VoteService voteService;

	public VoteController(VoteService voteService) {
		this.voteService = voteService;
	}
	
	@PostMapping("/cast")
	public ResponseEntity<VoteResponseDTO> castVote(@RequestBody @Valid VoteRequestDTO voteRequestDTO){
		
		Vote vote=voteService.castVote(voteRequestDTO.getVoterId(),voteRequestDTO.getCandidateId());
		VoteResponseDTO voteResponse=new VoteResponseDTO("Vote casted successfully",true,vote.getVoterId(),vote.getCandidateId());
	    return new ResponseEntity<VoteResponseDTO>(voteResponse,HttpStatus.CREATED);
 	}
	
	@GetMapping
	public ResponseEntity<List<Vote>> getAllVotes(){
		List<Vote> votes=voteService.getAllVotes();
		return new ResponseEntity<List<Vote>>(votes,HttpStatus.OK);
	}
	
}
