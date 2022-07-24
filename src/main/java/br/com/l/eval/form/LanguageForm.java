package br.com.l.eval.form;

import javax.validation.constraints.NotBlank;

public class LanguageForm {

	@NotBlank(message = "The name field is mandatory")
	private String name;
	
	@NotBlank(message = "The image field is mandatory")
	private String image;

	public LanguageForm(String name, String image) {
		this.name = name;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public String getImage() {
		return image;
	}

}
