package br.com.l.eval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.l.eval.dto.LanguageDto;
import br.com.l.eval.exceptions.LanguageException;
import br.com.l.eval.service.impl.LanguageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/languages")
@Api(tags = "Programming Languages Queries")
public class LanguageQueryController {

	@Autowired
	private LanguageService languageService;
	
	
	@GetMapping()
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Search for all languages on the database", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LanguageDto>> getAllLanguages() {

		List<LanguageDto> lenguagesDto;
		try {
			lenguagesDto = languageService.findAll();
			return ResponseEntity.ok().body(lenguagesDto);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Search for a language by it's id on the database", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LanguageDto> getLanguageById(@PathVariable(name = "id") String languageId) {
		try {
			LanguageDto languageDto = languageService.findById(languageId);
			return ResponseEntity.ok().body(languageDto);
		} catch (LanguageException ex) {
			return ResponseEntity.notFound().build();
		}

	}
}
