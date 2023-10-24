package kr.co.aoiv.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.aoiv.home.collections.Admin;
import kr.co.aoiv.home.repository.AdminRepository;
import kr.co.aoiv.home.service.AdminService;

/*implements는 인터페이스를 상속받아서 구현하는 클래스*/

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	/*관리자 등록*/
	public int insertAdmin(Admin col) {
		
		adminRepository.save(col);
		
		return 0;
	}
	
	/* 관리자 로그인 조회*/
	public List<Admin> selectAdmin(Admin admin) {
	
		List<Admin> result = adminRepository.findByUserIdAndUserPw(admin.getUserId(), admin.getUserPw());
		
		return result;
		
	}
	
}
