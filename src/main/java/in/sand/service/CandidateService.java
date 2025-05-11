package in.sand.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.sand.dto.CandidateUpdateDTO;
import in.sand.entity.Candidate;
import in.sand.entity.Vote;
import in.sand.exception.ResourceNotFoundException;
import in.sand.repository.CandidateRepository;
@Service
public class CandidateService {

	private CandidateRepository candidateRepository;
	
	public CandidateService(CandidateRepository candidateRepository) {
		this.candidateRepository=candidateRepository;
	}
	
	public Candidate registerCandidate(Candidate candidate) {
		return candidateRepository.save(candidate);
	}
	
	public Candidate getCandidateById(Long id) {
		Candidate candidate=candidateRepository.findById(id).orElse(null);
		if(candidate == null) {
			throw new ResourceNotFoundException("Candidate with id:"+id+" not found");
		}
		return candidate;
	}
	
	public List<Candidate> getAllCandidate() {
		return candidateRepository.findAll();
	}
	
	public Candidate updateCandidate(Long id,CandidateUpdateDTO candidateUpdateDTO) {
		Candidate candidate=candidateRepository.findById(id).orElse(null);
		if(candidate == null) {
			throw new ResourceNotFoundException("Candidate with id:"+id+" not found");
		}
		if(candidateUpdateDTO.getName() != null) {
			candidate.setName(candidateUpdateDTO.getName());
		}
		if(candidateUpdateDTO.getParty() != null) {
			candidate.setParty(candidateUpdateDTO.getParty());
		}
		return candidateRepository.save(candidate);
	}
	public void deleteCandidateById(Long id) {
		Candidate candidate=candidateRepository.findById(id).orElse(null);
		if(candidate == null) {
			throw new ResourceNotFoundException("Candidate with id:"+id+" not found");
		}
		//back reference set karna
		List<Vote> votes=candidate.getVote();
		for(Vote vote:votes) {
			vote.setCandidate(null);
		}
		votes.clear();
		candidateRepository.deleteById(id);
	}
}
