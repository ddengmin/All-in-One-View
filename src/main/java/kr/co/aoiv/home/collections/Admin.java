package kr.co.aoiv.home.collections;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import lombok.Builder;
import lombok.Data;

@Data
//컬렉션은 몽고DB Document 의 그룹이며 RDBMS 의 예를 들면 Table 과 개념
@Document(collection = "admin")
//Docuemtn 는 하나의 키(key) 와 값(value)의 집합으로 이루어져 있으며 동적 스키마 
public class Admin {
	
	@Id
	private ObjectId _id;
	  
    @Field ("userId")
    private String userId;
    
    @Field ("userPw")
    private String userPw;

    @Builder
    public Admin(String userId, String userPw) {
        this.userId = userId;
        this.userPw = userPw;
    }

}
