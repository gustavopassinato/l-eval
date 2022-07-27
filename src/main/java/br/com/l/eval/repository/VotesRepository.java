package br.com.l.eval.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.l.eval.model.Votes;

public interface VotesRepository extends MongoRepository<Votes, String>{

}
