package in.sand.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ElectionResultRequestDTO {

	@NotBlank(message = "Election name is required")
	private String electionName;
}
