package kr.co.aoiv.home.collections;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import lombok.Builder;
import lombok.Data;

@Data
//컬렉션은 몽고DB Document 의 그룹이며 RDBMS 의 예를 들면 Table 과 개념
@Document(collection = "visitor")
//Docuemtn 는 하나의 키(key) 와 값(value)의 집합으로 이루어져 있으며 동적 스키마 
public class Visitor {
	
	@Id
	private ObjectId _id;
	  
    @Field ("visitIp")
    private String visitIp;
    
    @Field ("visitDate")
    private String visitDate;

    @Builder
    public Visitor(String visitIp, String visitDate) {
        this.visitIp = visitIp;
        this.visitDate = visitDate;
    }

}
