package br.com.l.eval.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Votes")
public class Votes {

	@Id
	private String id;

	private String languageName;
	private String hash;
	private LocalDateTime timestamp;

	public Votes() {
		// TODO Auto-generated constructor stub
	}

	public Votes(String languageName, String hash, LocalDateTime timestamp) {
		this.languageName = languageName;
		this.hash = hash;
		this.timestamp = timestamp;
	}

	public String getId() {
		return id;
	}

	public String getLanguageName() {
		return languageName;
	}

	public String getHash() {
		return hash;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

}
