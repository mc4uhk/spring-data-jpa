package hk.mc4u.backend.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@Entity
@Table(name = "PERSONS")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "DAY_OF_BIRTH")
	private LocalDate dayOfBirth;

	@Transient
	private Pet pet;
	

	public Person() {
	}

	public Person(String firstName, String lastName, String email, LocalDate dayOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dayOfBirth = dayOfBirth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(LocalDate dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public Optional<Pet> getPet() {
		return Optional.ofNullable(pet);
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

}
