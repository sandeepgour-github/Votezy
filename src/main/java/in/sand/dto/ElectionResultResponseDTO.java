package in.sand.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ElectionResultResponseDTO {

	private String electionName;
	private Integer totalVote;
	private Long winnerId;
	private Integer winnerVotes;
}
