package br.com.l.eval.controller;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.l.eval.dto.LanguageDto;
import br.com.l.eval.exceptions.LanguageException;
import br.com.l.eval.form.LanguageForm;
import br.com.l.eval.service.impl.LanguageService;

@RestController
public class LanguageController {

	@Autowired
	private LanguageService languageService;

	@GetMapping("/language/all")
	public ResponseEntity<List<LanguageDto>> getAllLanguages() {
		List<LanguageDto> lenguagesDto;
		try {
			lenguagesDto = languageService.findAll();
			return ResponseEntity.ok().body(lenguagesDto);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@GetMapping("/language/{id}")
	public ResponseEntity<LanguageDto> getLanguageById(@PathVariable(name = "id") String languageId){
		try {
			LanguageDto languageDto = languageService.findById(languageId);
			return ResponseEntity.ok().body(languageDto);
		}catch (LanguageException ex) {
			return ResponseEntity.notFound().build();
		}
		
	}

	@PostMapping("/language")
	public ResponseEntity<LanguageDto> saveNewLanguage(@RequestBody @Valid LanguageForm languageForm,
			UriComponentsBuilder uriBuilder) {

		LanguageDto languageDto = languageService.create(languageForm);

		URI uri = uriBuilder.path("/language/{id}").buildAndExpand(languageDto.getId()).toUri();

		return ResponseEntity.created(uri).body(languageDto);
	}

	@PutMapping("/language/{id}")
	public ResponseEntity<LanguageDto> updateLanguage(@RequestBody @Valid LanguageForm languageForm,
			@PathVariable(name = "id") String languageId) {
		try {
			LanguageDto languageDto = languageService.update(languageForm, languageId);
			
			return ResponseEntity.ok().body(languageDto);
		} catch (LanguageException ex) {
			return ResponseEntity.status(204).build();
		}
	}
	@DeleteMapping("/language/{id}")
	public ResponseEntity<Map<String, String>> deleteLanguage(@PathVariable(name = "id") String languageId){
		Map<String, String> response = new LinkedHashMap<String, String>();
		
		try {
			String deletedLanguageId = languageService.delete(languageId);
			
			response.put("deleted-language-id", deletedLanguageId);
			
			return ResponseEntity.ok().body(response);
		}catch(LanguageException ex) {
			response.put("error", ex.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
}
