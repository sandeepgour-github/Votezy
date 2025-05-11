package in.sand.service;

import org.springframework.stereotype.Service;

import in.sand.dto.VoterUpdateDTO;
import in.sand.entity.Candidate;
import in.sand.entity.Vote;
import in.sand.entity.Voter;
import in.sand.exception.DuplicateResourceException;
import in.sand.exception.ResourceNotFoundException;
import in.sand.repository.CandidateRepository;
import in.sand.repository.VoterRepository;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class VoterService {

	private VoterRepository voterRepository;
	private CandidateRepository candidateRepository;
	
	public VoterService(VoterRepository voterRepository,CandidateRepository candidateRepository) {
		this.voterRepository=voterRepository;
		this.candidateRepository=candidateRepository;
	}
	public Voter registerVoter(Voter voter) {
		if(voterRepository.existsByEmail(voter.getEmail())) {
			throw new DuplicateResourceException("email id already eixts");
		}
		return voterRepository.save(voter);
	}
	
	public Voter getVoterById(Long id) {
		Voter voter=voterRepository.findById(id).orElse(null);
		if(voter == null) {
			throw new ResourceNotFoundException("Voter with id:"+id+"not found");
		}
		return voter;
	}
	
	public List<Voter> getAllVoters(){
		return voterRepository.findAll();
	}
	public Voter updateVoter(Long id,VoterUpdateDTO voterUpdateDTO) {
	   Voter voter=voterRepository.findById(id).orElse(null);
	   if(voter == null) {
		   throw new ResourceNotFoundException("Voter with id:"+id+"not found");
	   }
	   if(voterUpdateDTO.getName() != null)
	   voter.setName(voterUpdateDTO.getName());
	   if(voterUpdateDTO.getEmail() != null)
	   voter.setEmail(voterUpdateDTO.getEmail());
	   return voterRepository.save(voter);
	}
	
	@Transactional
	public void deleteVoter(Long id) {
		 Voter voter=voterRepository.findById(id).orElse(null);
		   if(voter == null) {
			   throw new ResourceNotFoundException("Voter with id:"+id+"not found");
		   }
		   Vote vote=voter.getVote();
		   if(vote != null) {
			   Candidate candidate=vote.getCandidate();
			   candidate.setVoteCount(candidate.getVoteCount()-1);
			   candidateRepository.save(candidate);
		   }
		   voterRepository.delete(voter);
	}
}
