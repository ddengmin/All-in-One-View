package kr.co.aoiv.home.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import kr.co.aoiv.home.collections.Admin;

//JPA-MongoRepository 방식사용
public interface AdminRepository extends MongoRepository<Admin, String> {
	
	List<Admin> findByUserIdAndUserPw(String userId, String userPw);
	
}
