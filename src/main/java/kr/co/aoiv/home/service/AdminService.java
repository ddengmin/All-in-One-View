package kr.co.aoiv.home.service;

import java.util.List;

import kr.co.aoiv.home.collections.Admin;

public interface AdminService {

	/*관리자 등록*/
	public int insertAdmin(Admin collections);
	
	/*관리자 정보 조회*/
	public List<Admin> selectAdmin(Admin admin);
	
}
