package in.sand.controller;
//This is an candidate controller
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import in.sand.dto.CandidateUpdateDTO;
import in.sand.entity.Candidate;
import in.sand.service.CandidateService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin
public class CandidateController {

	private CandidateService candidateService;
	
	public CandidateController(CandidateService candidateService) {
		this.candidateService=candidateService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<Candidate> registerCandidate(@RequestBody @Valid Candidate candidate){
		Candidate c=candidateService.registerCandidate(candidate);
		return new ResponseEntity<Candidate>(c,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id){
		Candidate candidate=candidateService.getCandidateById(id);
		return new ResponseEntity<Candidate>(candidate,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Candidate>> getCandidateById(){
		List<Candidate> candidates=candidateService.getAllCandidate();
		return new ResponseEntity<List<Candidate>>(candidates,HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id,@RequestBody @Valid  CandidateUpdateDTO candidateUpdateDTO){
		Candidate candidate=candidateService.updateCandidate(id, candidateUpdateDTO);
		return new ResponseEntity<Candidate>(candidate,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCandidate(@PathVariable Long id){
		candidateService.deleteCandidateById(id);
		return new ResponseEntity<String>("Candidate deleted with id:"+ id,HttpStatus.OK);
	}
}
