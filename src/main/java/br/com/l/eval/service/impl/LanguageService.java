package br.com.l.eval.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.l.eval.dto.LanguageDto;
import br.com.l.eval.exceptions.LanguageException;
import br.com.l.eval.form.LanguageForm;
import br.com.l.eval.model.Language;
import br.com.l.eval.repository.LanguageRepository;

@Service
public class LanguageService {

	@Autowired
	private LanguageRepository languageRepository;
	
	
	public LanguageDto create(LanguageForm languageForm) {
		Language language = languageRepository.save(new Language(languageForm.getName(), languageForm.getImage()));
		
		return toDto(language);
	}
	

	public List<LanguageDto> findAll() {
		List<Language> languages = languageRepository.findAll();
		
		List<LanguageDto> languagesDto = languages.stream().map(language -> 
			new LanguageDto(language.getId(), language.getName(), language.getImage(), language.getRanking()))
		.collect(Collectors.toList());
		
		return languagesDto;
		
	}

	public LanguageDto findById(String id) {
		Optional<Language> languageOpt = languageRepository.findById(id);

		if (languageOpt.isPresent()) {
			return toDto(languageOpt.get());
			
		}

		throw new LanguageException("Not found language");

	}

	public LanguageDto update(LanguageForm languageForm, String languageId) {
		Optional<Language> languageOpt = languageRepository.findById(languageId);

		if (languageOpt.isPresent()) {
			Language language = languageOpt.get();

			language.setName(languageForm.getName());
			language.setImage(languageForm.getImage());

			return toDto(languageRepository.save(language));

		}

		throw new LanguageException("Not found language");

	}
	
	public String delete(String languageId) {
		Optional<Language> languageOpt = languageRepository.findById(languageId);
		
		if (languageOpt.isPresent()) {
			String id = languageOpt.get().getId();
			languageRepository.delete(languageOpt.get());
			
			return id;
		}

		throw new LanguageException("Not found language");
	}
	
	public LanguageDto toDto(Language language) {
		LanguageDto languageDto = new LanguageDto(language.getId(), language.getName() , language.getImage(), language.getRanking());
		
		return languageDto;
	}
}
