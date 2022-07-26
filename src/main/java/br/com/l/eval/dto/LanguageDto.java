package br.com.l.eval.dto;

import br.com.l.eval.model.Language;

public class LanguageDto {

	private String id;
	private String name;
	private Integer yearCreation;
	private String creatorName;
	private String image;
	private Integer totalVotes;

	public LanguageDto(Language language) {
		this.id = language.getId();
		this.name = language.getName();
		this.yearCreation = language.getYearCreation();
		this.creatorName = language.getCreatorName();
		this.image = language.getImage();
		this.totalVotes = language.getTotalVotes();

	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getImage() {
		return image;
	}

	public Integer getYearCreation() {
		return yearCreation;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public Integer getTotalVotes() {
		return totalVotes;
	}

}
