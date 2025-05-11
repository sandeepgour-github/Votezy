package in.sand.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "Party name is required")
	private String party;
	
	private int voteCount=0;
	
	
	@OneToMany(mappedBy = "candidate",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Vote> vote;
}
