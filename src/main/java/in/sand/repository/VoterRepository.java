package in.sand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.sand.entity.Voter;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {

	boolean existsByEmail(String email);
}
