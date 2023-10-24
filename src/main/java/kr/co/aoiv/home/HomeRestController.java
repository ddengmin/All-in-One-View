package kr.co.aoiv.home;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.aoiv.home.collections.Admin;
import kr.co.aoiv.home.collections.Visitor;
import kr.co.aoiv.home.service.AdminService;
import kr.co.aoiv.home.service.VisitorService;
import lombok.extern.slf4j.Slf4j;

/**
* @packageName    : kr.co.aoiv.home
* @fileName       : HomeRestController.java
* @author         : YM
* @date           : 2022.05.14
* @description    : 프로젝트 메 레스트컨트롤러
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2022.05.14        YM      	최초 생성
*/
@Slf4j
@RestController
public class HomeRestController {
	
	@Autowired
	AdminService adminService;

	@Autowired
	VisitorService visitorService;	
	
    /**
    * @methodName    : adminLogin
    * @description 	 : 관리자 로그인 호출
    * @author        : YM
    * @date        : 2022.08.27
    * @return 결과 List<Visitor>
    * @throws Exception
    */
    @PostMapping("/admin/login")
	public List<Admin> adminLogin(Admin admin, HttpSession session) throws Exception{
    	
    	log.info("adminLogin() Ajax 호출");
    	log.info("admin : {}",admin.toString());
    	
    	if(adminService.selectAdmin(admin).size() > 0) {
    		session.setAttribute("adminSession", "ok");
    	}
    	return adminService.selectAdmin(admin);
    } 
    
    /**
    * @methodName    : getVisitorList
    * @description 	 : 방문자 통계 호출
    * @author        : YM
    * @date        : 2022.08.27
    * @return 결과 List<Visitor>
    * @throws Exception
    */
    @GetMapping("/admin/visitorList")
	public List<Visitor> getVisitorList() throws Exception{
    	log.info("getVisitorList() 호출");
    	return visitorService.selectListVisitor();
    }  

    /**
    * @methodName    : getkospiApi
    * @description 	 : Kospi API호출, 프론트에서는 CROS이슈로인해 ajax get 불가능
    * @author        : YM
    * @date        : 2022.08.27
    * @return api 결과 String
    * @throws Exception
    */
    @GetMapping("/api/kospi")
	public String getkospiApi() throws Exception{
    	log.info("getkospiApi() 호출");
    	return visitorService.getKospiApiCall();
    } 
}
