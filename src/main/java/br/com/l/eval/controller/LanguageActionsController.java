package br.com.l.eval.controller;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.l.eval.dto.LanguageDto;
import br.com.l.eval.dto.VoteConfirmationDto;
import br.com.l.eval.exceptions.LanguageException;
import br.com.l.eval.exceptions.VoteException;
import br.com.l.eval.form.LanguageForm;
import br.com.l.eval.form.VotesForm;
import br.com.l.eval.service.impl.LanguageService;
import br.com.l.eval.service.impl.VotesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/languages")
@Api(tags = "Programming Languages Actions")
public class LanguageActionsController {
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private VotesService votesService;
	
	@PostMapping()
	@ApiOperation(value = "Save a new programming language on the database", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LanguageDto> saveNewLanguage(@RequestBody @Valid LanguageForm languageForm,
			UriComponentsBuilder uriBuilder) {

		LanguageDto languageDto = languageService.create(languageForm);

		URI uri = uriBuilder.path("/language/{id}").buildAndExpand(languageDto.getId()).toUri();

		return ResponseEntity.created(uri).body(languageDto);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Update the informations of a saved language", produces = MediaType.APPLICATION_JSON_VALUE)
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
	@ApiOperation(value = "Delete a programming language by it's ID", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> deleteLanguage(@PathVariable(name = "id") String languageId) {
		Map<String, String> response = new LinkedHashMap<String, String>();

		try {
			String deletedLanguageId = languageService.delete(languageId);

			response.put("deleted-language-id", deletedLanguageId);

			return ResponseEntity.ok().body(response);
		} catch (LanguageException ex) {
			response.put("error", ex.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PostMapping("/vote")
	@ApiOperation(value = "Vote on your prefereted language", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> vote(@RequestBody @Valid VotesForm votesForm){
		try {
			VoteConfirmationDto confirmatedVote = votesService.save(votesForm, languageService);
			
			languageService.updateLanguageVotes(votesForm.getLanguageId());
			return ResponseEntity.ok().body(confirmatedVote);
		}catch (VoteException es) {
			return ResponseEntity.badRequest().body(es.getMessage());
		}
	}
}
