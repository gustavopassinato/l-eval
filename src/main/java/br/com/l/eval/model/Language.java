package br.com.l.eval.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.l.eval.form.LanguageForm;

@Document(collection = "Lenguages")
public class Language {

	@Id
	private String id;

	private String name;
	private Integer yearCreation;
	private String creatorName;
	private String image;
	private Integer totalVotes;

	public Language() {
		// TODO Auto-generated constructor stub
	}

	public Language(LanguageForm languageForm) {

		this.name = languageForm.getCreatorName();
		this.yearCreation = languageForm.getYearCreation();
		this.creatorName = languageForm.getCreatorName();
		this.image = languageForm.getImage();
		this.totalVotes = 0;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setYearCreation(Integer yearCreation) {
		this.yearCreation = yearCreation;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void increaseVotes() {
		this.totalVotes +=1;
	}

	public Integer getYearCreation() {
		return yearCreation;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public String getImage() {
		return image;
	}

	public Integer getTotalVotes() {
		return totalVotes;
	}

}
