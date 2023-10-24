package kr.co.aoiv.home.service.impl;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.aoiv.home.collections.Visitor;
import kr.co.aoiv.home.repository.VisitorRepository;
import kr.co.aoiv.home.service.VisitorService;
import lombok.extern.slf4j.Slf4j;

/*implements는 인터페이스를 상속받아서 구현하는 클래스*/

@Slf4j
@Service
public class VisitorServiceImpl implements VisitorService {
	
	
	@Autowired
	private VisitorRepository visitorRepository;
	
	/*방문자 등록*/
	public int insertVisitor(Visitor visitor) {
		
		int result = 0;
		
		Visitor returnObj = visitorRepository.save(visitor);
		
		//save를 하면 추가된 행을 리턴함
		if(!"".equals(returnObj.get_id().toString()) && returnObj.get_id() != null) {
			result = 1;
		}
		log.info("returnObj >>>> {} ",returnObj.toString());
		
		return result;
	}
	
	/*방문자 목록*/
	public List<Visitor> selectListVisitor(){
		return visitorRepository.findAll();
	}
	
	/*코스피 API 호출*/
	public String getKospiApiCall() {
		String result_txt = "";
		String strUrl= "https://m.stock.naver.com/api/json/sise/dailySiseIndexListJson.nhn?code=KOSPI&pageSize=100&page=1";
		try {
			
			// Step1. 호출할 외부 API 를 입력한다.
			URL url = new URL(strUrl); 
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // header에 데이터 통신 방법을 지정한다.
			conn.setConnectTimeout(5000); //서버에 연결되는 Timeout 시간 설정			
			conn.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
			conn.setRequestMethod("GET");	//호출METHOD
			conn.setRequestProperty("Content-Type", "application/json; utf-8");	//헤더타입

			// Post인 경우 데이터를 OutputStream으로 넘겨 주겠다는 설정
			//URLConnection에 대한 doOutput 필드값을 지정된 값으로 설정한다. 
			//URL 연결은 입출력에 사용될 수 있다. 
			//URL 연결을 출력용으로 사용하려는 경우 DoOutput 플래그를 true로 설정하고, 
			//그렇지 않은 경우는 false로 설정해야 한다. 기본값은 false이다.
			conn.setDoOutput(false);

			StringBuilder sb = new StringBuilder();
			
			//연결이 성공이면
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				log.info("Kospi api 호출 성공");
				//Stream을 처리해줘야 하는 귀찮음이 있음. 				
				BufferedReader br = new BufferedReader(						
						new InputStreamReader(conn.getInputStream(), "utf-8"));				
						String line;				
						while ((line = br.readLine()) != null) {					
							sb.append(line).append("\n");					
						}				
						br.close();				
			} else {				
				log.info("Kospi api 호출 실패");
				log.info(conn.getResponseMessage());			
			}
			
			conn.disconnect();
			result_txt = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result_txt;
	}
	
}
