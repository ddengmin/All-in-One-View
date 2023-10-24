package kr.co.aoiv;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/*
다른 도메인에서의 자원을 호출하는 행위에 제한이 없을 경우 안전하지 않습니다. 
CORS (Cross-Origin Resource Sharing)는 이렇게 시스템 수준에서 타 도메인 간 자원 호출을 승인하거나 차단하는 것을 결정하는 것입니다.
Same Origin Policy에 의해 Script에 의한 cross-site http requests는 허용되지 않습니다.
즉 Ajax를 사용하여 통신을 하고 Front와 Back을 구별하여(ex Spring Boot + Vue.js)
개발하는 구조의 웹 서비스는 개발단계에서 서버와의 통신을 위해 추가로 설정이 필요합니다.
ProjectApplication.java 와 동일하거나 그 하위에 위치한 폴더에 작성
*/

//@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("http://127.0.0.1:8090") 
		.allowedOrigins("http://localhost:8090"); //허용할 주소 및 포트
	}
}
