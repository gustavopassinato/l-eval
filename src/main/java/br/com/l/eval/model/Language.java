package br.com.l.eval.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Lenguages")
public class Language {

	@Id
	private String id;

	private String name;
	private String image;
	private Integer ranking;

	public Language(String name, String image) {
		this.name = name;
		this.image = image;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getImage() {
		return image;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

}
