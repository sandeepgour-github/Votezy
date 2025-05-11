package in.sand.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VoteRequestDTO {
    @NotNull(message = "Voter id is required")
	private Long voterId;
    
    @NotNull(message = "Candidate id is required")
	private Long candidateId;
}
