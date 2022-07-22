package br.com.l.eval.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.l.eval.model.Language;
import br.com.l.eval.repository.LanguageRepository;

@RestController
public class LanguageController {
	
	@Autowired
	private LanguageRepository languageRepository;

	@GetMapping("/languages")
	public ResponseEntity<List<Language>> getAllLanguages(){
		List<Language> lenguages;
		try {
			lenguages = languageRepository.findAll();
			return ResponseEntity.ok().body(lenguages);
			
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/languages")
	public Language saveNewLanguage(@RequestBody @Valid Language language){
		return languageRepository.save(language);
	}
}
