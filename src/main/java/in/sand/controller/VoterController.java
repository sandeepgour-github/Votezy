package in.sand.controller;

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

import in.sand.dto.VoterUpdateDTO;
import in.sand.entity.Voter;
import in.sand.service.VoterService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/voters")
@CrossOrigin
public class VoterController {

	private VoterService voterService;
	
	public VoterController(VoterService voterService) {
		this.voterService=voterService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<Voter> registerVoter(@RequestBody @Valid Voter voter){
		Voter registerVoter = voterService.registerVoter(voter);
		return new ResponseEntity<Voter>(registerVoter,HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Voter> getVoterById(@PathVariable Long id){
		Voter voter=voterService.getVoterById(id);
		return new ResponseEntity<Voter>(voter,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Voter>> getAllVoter(){
		List<Voter> voters=voterService.getAllVoters();
		return new ResponseEntity<List<Voter>>(voters,HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Voter> updateVoter(@PathVariable Long id,@RequestBody @Valid VoterUpdateDTO voterUpdateDTO){
		Voter voter=voterService.updateVoter(id, voterUpdateDTO);
		return new ResponseEntity<Voter>(voter,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteVoter(@PathVariable Long id){
		voterService.deleteVoter(id);
		return new ResponseEntity<String>("Voter with id:"+id+"deleted successfully",HttpStatus.OK);
	}
	
}
