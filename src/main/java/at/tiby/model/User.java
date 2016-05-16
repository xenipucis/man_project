package at.tiby.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Builder
public class User implements Serializable {

	private Integer id;
	private String name;
	private String email;
}
