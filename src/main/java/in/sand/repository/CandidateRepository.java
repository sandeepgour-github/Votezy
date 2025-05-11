package in.sand.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.sand.entity.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>{

	List<Candidate> findAllByOrderByVoteCountDesc();
}
