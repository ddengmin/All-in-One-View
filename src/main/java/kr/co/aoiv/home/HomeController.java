package kr.co.aoiv.home;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import kr.co.aoiv.home.collections.Visitor;
import kr.co.aoiv.home.service.AdminService;
import kr.co.aoiv.home.service.VisitorService;
import lombok.extern.slf4j.Slf4j;

/**
* @packageName    : kr.co.aoiv.home
* @fileName       : HomeController.java
* @author         : YM
* @date           : 2022.05.14
* @description    : 프로젝트 메인 컨트롤러
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2022.05.14        YM      	최초 생성
*/
@Slf4j
@Controller
public class HomeController {
	
	@Autowired
	AdminService adminService;

	@Autowired
	VisitorService visitorService;	
	
    /**
    * @methodName    : main
    * @description 	 : 메인페이지 이동
    * @author        : YM
    * @date        : 2022.08.27
    * @param request
    * @return
    * @throws Exception
    */
    @GetMapping({"/main","/"})
	public ModelAndView main(HttpServletRequest request) throws Exception{

    	ModelAndView modelAndView = new ModelAndView();

    	log.info("main() 호출");
    	
    	log.info(" get IP >>>>>> " +request.getRemoteAddr());
    	
    	//지금 시간
    	String nowDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    	
    	Visitor visitor = Visitor.builder().visitIp(request.getRemoteAddr()).visitDate(nowDate).build();
    	
    	visitorService.insertVisitor(visitor);
    	
    	modelAndView.setViewName("main/main");
    	
    	return modelAndView;
    }

    /**
    * @methodName    : spec
    * @description 	 : 개발환경 페이지 이동
    * @author        : YM
    * @date        : 2022.08.27
    * @param request
    * @return
    * @throws Exception
    */
    @GetMapping("/spec")
   	public ModelAndView spec(HttpServletRequest request) throws Exception{

       	log.info("spec() 호출");
       	
       	ModelAndView modelAndView = new ModelAndView();
       	
       	modelAndView.setViewName("main/spec");
       	
       	return modelAndView;
       }    
    
    
    /**
    * @methodName    : home
    * @description 	 : 관리자 로그인페이지 이동
    * @author        : YM
    * @date        : 2022.08.27
    * @return
    * @throws Exception
    */
    @GetMapping("/admin")
	public ModelAndView home() throws Exception{
    	
    	log.info("home() 호출");
    	
    	ModelAndView modelAndView = new ModelAndView();
    	
    	modelAndView.setViewName("admin/login");
    	
    	return modelAndView;
    }


    /**
    * @methodName    : adminMain
    * @description 	 : 관리자 메인페이지 이동
    * @author        : YM
    * @date        : 2022.08.27
    * @return
    * @throws Exception
    */
    @GetMapping("/admin/main")
	public ModelAndView adminMain(HttpSession session) throws Exception{
    	
    	log.info("adminMain() 호출");
    	
    	ModelAndView modelAndView = new ModelAndView();
    	
    	if(session.getAttribute("adminSession") == null) {
    		modelAndView.setView(new RedirectView("/admin"));
    	}else {
    		modelAndView.setViewName("admin/main");
    	}
    	
    	return modelAndView;
    }  

    /**
    * @methodName    : adminLogout
    * @description 	 : 관리자 로그아웃
    * @author        : YM
    * @date        : 2022.08.27
    * @return
    * @throws Exception
    */
    @GetMapping("/admin/logout")
	public ModelAndView adminLogout(HttpSession session) throws Exception{
    	
    	log.info("adminLogout() 호출");
    	
    	//세션정보 삭제
    	session.invalidate();
    	
    	ModelAndView modelAndView = new ModelAndView();

    	//로그아웃하면 관리자 로그인 페이지로 이동한다
		modelAndView.setView(new RedirectView("/admin"));
    	
    	return modelAndView;
    } 

}
