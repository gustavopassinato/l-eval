package br.com.l.eval.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.l.eval.dto.LanguageDto;
import br.com.l.eval.exceptions.LanguageException;
import br.com.l.eval.exceptions.VoteException;
import br.com.l.eval.form.LanguageForm;
import br.com.l.eval.model.Language;
import br.com.l.eval.repository.LanguageRepository;

@Service
public class LanguageService {

	@Autowired
	private LanguageRepository languageRepository;

	public LanguageDto create(LanguageForm languageForm) {

		Language languageToSave = new Language(languageForm);

		Language savedLanguage = languageRepository.save(languageToSave);

		return new LanguageDto(savedLanguage);
	}

	public List<LanguageDto> findAll() {
		List<Language> languages = languageRepository.findAll();

		List<LanguageDto> languagesDto = languages.stream().map(language -> new LanguageDto(language))
				.collect(Collectors.toList());

		return languagesDto;

	}

	public LanguageDto findById(String id) {
		Optional<Language> languageOpt = languageRepository.findById(id);

		if (languageOpt.isPresent()) {
			return new LanguageDto(languageOpt.get());

		}

		throw new LanguageException("Not found language");

	}

	public LanguageDto update(LanguageForm languageForm, String languageId) {

		Language updatedLangauge = languageRepository.findById(languageId).map(language -> {
			language.setCreatorName(languageForm.getCreatorName());
			language.setImage(languageForm.getImage());
			language.setName(languageForm.getName());
			language.setYearCreation(languageForm.getYearCreation());
			return languageRepository.save(language);
		}).orElseGet(() -> {
			return languageRepository.save(new Language(languageForm));
		});

		return new LanguageDto(updatedLangauge);

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

	public void updateLanguageVotes(String languageId) {

		languageRepository.findById(languageId).map(language -> {
			language.increaseVotes();
			return languageRepository.save(language);
		}).orElseGet(() -> {
			throw new VoteException("Error saving vote");
		});
	}

}
