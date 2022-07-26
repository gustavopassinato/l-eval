package br.com.l.eval.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ValidationErrorDto> illegalArgumentSaveing(MethodArgumentNotValidException ex){

		List<ValidationErrorDto> validationErrors = new ArrayList<ValidationErrorDto>();
		
		
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		
		fieldErrors.forEach(error ->{
			String messagerError = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			ValidationErrorDto validationErrorDto = new ValidationErrorDto(error.getField(), messagerError);
			validationErrors.add(validationErrorDto);
		});
		
		return validationErrors;
	}

}
