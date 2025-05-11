package in.sand.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.sand.entity.Candidate;
import in.sand.entity.Vote;
import in.sand.entity.Voter;
import in.sand.exception.ResourceNotFoundException;
import in.sand.exception.VoteNotAllowedException;
import in.sand.repository.CandidateRepository;
import in.sand.repository.VoteRepository;
import in.sand.repository.VoterRepository;

@Service
public class VoteService {

	private VoteRepository voteRepository;
	private VoterRepository voterRepository;
	private CandidateRepository candidateRepository;
	
	public VoteService(VoteRepository voteRepository, VoterRepository voterRepository,
			CandidateRepository candidateRepository) {
		this.voteRepository = voteRepository;
		this.voterRepository = voterRepository;
		this.candidateRepository = candidateRepository;
	}
	
	public Vote castVote(Long voterId,Long candidateId) {
		
		Voter voter=voterRepository.findById(voterId).orElse(null);
		if(voter==null) {
			throw new ResourceNotFoundException("Voter with id:"+voterId+" not found");
		}
		
		Candidate candidate=candidateRepository.findById(candidateId).orElse(null);
		if(candidate==null) {
			throw new ResourceNotFoundException("Candidate with id:"+candidateId+" not found");
		}

		if(voter.isHasVoted()) {
			throw new VoteNotAllowedException("Vote already casted by this Voter");
		}
		Vote vote=new Vote();
		vote.setVoter(voter);
		vote.setCandidate(candidate);
		
		candidate.setVoteCount(candidate.getVoteCount()+1);
		candidateRepository.save(candidate);
		
		voter.setHasVoted(true);
		voter.setVote(vote);
		voterRepository.save(voter);
	
	   return vote;
	}
	public List<Vote> getAllVotes(){
		return voteRepository.findAll();
	}
}
