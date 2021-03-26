package hk.mc4u.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pet {

	@JsonProperty
	private Long id;

	@JsonProperty
	private String name;

	@JsonProperty
	private Integer age;

	
	public Pet() {
		super();
	}

	public Pet(Long id, String name, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
