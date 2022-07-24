package br.com.l.eval.controller.validation;

public class ValidationErrorDto {

	public String field;
	public String message;

	public ValidationErrorDto(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

}
