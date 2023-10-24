package kr.co.aoiv.home.service;


import java.util.List;

import kr.co.aoiv.home.collections.Visitor;

public interface VisitorService {

	/*관리자 등록*/
	public int insertVisitor(Visitor visitor);
	
	/*관리자 목록*/
	public List<Visitor> selectListVisitor();
	
	/*코스피 api 호출*/
	public String getKospiApiCall();
}
