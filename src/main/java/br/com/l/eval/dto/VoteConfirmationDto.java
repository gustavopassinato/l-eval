package br.com.l.eval.dto;

import java.time.LocalDateTime;

import br.com.l.eval.model.Votes;

public class VoteConfirmationDto {
	private String languageName;
	private LocalDateTime timeStamp;
	private String hash;

	public VoteConfirmationDto(Votes vote) {
		this.languageName = vote.getLanguageName();
		this.timeStamp = vote.getTimestamp();
		this.hash = vote.getHash();
	}

	public String getLanguageName() {
		return languageName;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public String getHash() {
		return hash;
	}

}
