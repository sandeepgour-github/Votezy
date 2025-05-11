package in.sand.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import in.sand.entity.Candidate;
import in.sand.entity.ElectionResult;
import in.sand.exception.ResourceNotFoundException;
import in.sand.repository.CandidateRepository;
import in.sand.repository.EleResultRepository;
import in.sand.repository.VoteRepository;
import jakarta.transaction.Transactional;

@Service
public class ElectionResultService {

	private EleResultRepository electionResultRepository;
	private CandidateRepository candidateRepository;
	private VoteRepository voteRepository;
	
	public ElectionResultService(EleResultRepository electionResultRepository,CandidateRepository candidateRepository,VoteRepository voteRepository) {
		  this.electionResultRepository=electionResultRepository;
		  this.candidateRepository=candidateRepository;
		  this.voteRepository=voteRepository;
	}
	
	@Transactional
	public ElectionResult declaElectionResult(String electionName) {
		Optional<ElectionResult> electionResult=electionResultRepository.findByElectionName(electionName);
		if(electionResult.isPresent()) {
			return electionResult.get();
		}
		if(voteRepository.count()==0) {
			throw new IllegalStateException("Cannot declare the result as no votes have been casted");
		}
		List<Candidate> candidates=candidateRepository.findAllByOrderByVoteCountDesc();
		if(candidates.isEmpty()) {
			throw new ResourceNotFoundException("No candidates are available");
		}
		Candidate winner=candidates.get(0);
		int totalVotes=0;
		for(Candidate candidate:candidates) {
			totalVotes+=candidate.getVoteCount();
		}
		ElectionResult result=new ElectionResult();
		result.setElectionName(electionName);
		result.setTotalVote(totalVotes);
		result.setWinner(winner);
		return electionResultRepository.save(result);
	}
	
	@Transactional
	public List<ElectionResult> getAllElectionResult() {
		return electionResultRepository.findAll();
	}
}
