package br.com.l.eval.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.l.eval.model.Language;

public interface LanguageRepository extends MongoRepository<Language, String>{

}
