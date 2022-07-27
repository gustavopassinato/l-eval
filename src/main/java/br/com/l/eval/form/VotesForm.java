package br.com.l.eval.form;

import javax.validation.constraints.NotBlank;

public class VotesForm {

	@NotBlank(message = "This field is mandatory!")
	private String languageId;

	@NotBlank(message = "This field is mandatory!")
	private String personalKey;

	public VotesForm(String languageId, String personalKey) {
		this.languageId = languageId;
		this.personalKey = personalKey;
	}

	public String getLanguageId() {
		return languageId;
	}

	public String getPersonalKey() {
		return personalKey;
	}

}
