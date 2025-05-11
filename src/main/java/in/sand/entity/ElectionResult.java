package in.sand.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class ElectionResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Election Name is required")
	private String electionName;
	
	private int totalVote;
	
	@OneToOne
	@JoinColumn(name="winner_id")
	@JsonIgnore
	private Candidate winner;
	
	@JsonProperty
	public Long getWinnerId() {
		return winner!=null ? winner.getId():null;
	}
	
	@JsonProperty
	public int getWinnerVoteCount() {
		return winner!=null ? winner.getVoteCount():0;
	}
	
	
}
