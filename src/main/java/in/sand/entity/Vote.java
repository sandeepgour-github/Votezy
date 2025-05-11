package in.sand.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="voter_id",unique = true)
	@JsonIgnore
	private Voter voter;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="candidate_id")
	private Candidate candidate;
	
	@JsonProperty
	public Long getVoterId() {
		return voter.getId();
	}
	@JsonProperty
	public Long getCandidateId() {
		return candidate.getId();
	}
}
