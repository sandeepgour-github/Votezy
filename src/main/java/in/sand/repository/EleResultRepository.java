package in.sand.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.sand.entity.ElectionResult;

@Repository
public interface EleResultRepository extends JpaRepository<ElectionResult, Long> {

	Optional<ElectionResult> findByElectionName(String electionName);
}
