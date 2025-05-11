package in.sand.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CandidateUpdateDTO {

	@Size(min=1,message = "Candidate name cannot be empty")
	private String name;
	
	@Size(min=1,message = "Party name cannot be empty")
	private String party;
}
