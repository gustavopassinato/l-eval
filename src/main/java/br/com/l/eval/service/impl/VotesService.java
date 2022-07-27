package br.com.l.eval.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.l.eval.dto.LanguageDto;
import br.com.l.eval.dto.VoteConfirmationDto;
import br.com.l.eval.exceptions.VoteException;
import br.com.l.eval.form.VotesForm;
import br.com.l.eval.model.Votes;
import br.com.l.eval.repository.VotesRepository;

@Service
public class VotesService {

	@Autowired
	private VotesRepository votesRepository;

	public VoteConfirmationDto save(VotesForm votesForm, LanguageService languageService) {
		try {
			Votes vote = create(votesForm, languageService);

			Votes savedVote = votesRepository.save(vote);

			return new VoteConfirmationDto(savedVote);

		} catch (NoSuchAlgorithmException e) {

			throw new VoteException(e.getMessage());
		}
	}

	private Votes create(VotesForm votesForm, LanguageService languageService) throws NoSuchAlgorithmException {
		LanguageDto votedLanguageDto = languageService.findById(votesForm.getLanguageId());

		String personalKey = votesForm.getPersonalKey();

		Votes vote = new Votes(votedLanguageDto.getName(), hash(personalKey), LocalDateTime.now());

		return vote;

	}

	private String hash(String stringToHash) throws NoSuchAlgorithmException {
		 // Create MessageDigest instance for MD5
	      MessageDigest md = MessageDigest.getInstance("MD5");

	      // Add password bytes to digest
	      md.update(stringToHash.getBytes());

	      // Get the hash's bytes
	      byte[] bytes = md.digest();

	      // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
	      StringBuilder sb = new StringBuilder();
	      for (int i = 0; i < bytes.length; i++) {
	        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	      }

	      // Get complete hashed password in hex format
	   return sb.toString();

	}
}
