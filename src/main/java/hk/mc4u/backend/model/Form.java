package hk.mc4u.backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Form {
	private String name;

	private Person person = new Person();

	private Part partA = new Part();
	private Part partB = new Part();

	@XmlElementWrapper(name = "pets")
	@XmlElement(name = "pet")
	private List<Pet> pets = new ArrayList<>();

}
