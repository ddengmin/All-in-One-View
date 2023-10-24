package kr.co.aoiv.home.repository;



import org.springframework.data.mongodb.repository.MongoRepository;

import kr.co.aoiv.home.collections.Visitor;

//JPA-MongoRepository 방식사용
public interface VisitorRepository extends MongoRepository<Visitor, String> {
	
}
