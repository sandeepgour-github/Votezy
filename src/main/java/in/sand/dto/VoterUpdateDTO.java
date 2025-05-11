package in.sand.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VoterUpdateDTO {

	@Size(min=1,message = "Voter name cannot be empty")
	private String name;
	
	@Email(message="Please enter valid email format")
	private String email;
}
