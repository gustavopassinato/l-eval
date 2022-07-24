package br.com.l.eval.dto;

public class LanguageDto {

	private String id;
	private String name;
	private String image;
	private Integer ranking;

	public LanguageDto(String id, String name, String image, Integer ranking) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.ranking = ranking;
	}

	public Integer getRanking() {
		return ranking;
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

}
