package in.sand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.sand.entity.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{

}
