package br.com.l.eval.form;

import javax.validation.constraints.NotBlank;

public class LanguageForm {

	@NotBlank(message = "The name field is mandatory")
	private String name;

	private Integer yearCreation;
	private String creatorName;

	@NotBlank(message = "The image field is mandatory")
	private String image;

	public LanguageForm(String name, Integer yearCreation, String creatorName, String image) {
		this.name = name;
		this.yearCreation = yearCreation;
		this.creatorName = creatorName;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public Integer getYearCreation() {
		return yearCreation;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public String getImage() {
		return image;
	}

}
