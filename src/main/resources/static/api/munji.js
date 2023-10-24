var munji = function(){

    //모듈 스코프 내에서 사용할 변수 작성
    var message = 'munji.js 로드';
    var callAjax = function (){
        $.ajax({ 
            type: "GET"
            , async: false
            , url: "http://openapi.seoul.go.kr:8088/6d4d776b466c656533356a4b4b5872/json/RealtimeCityAir/1/99"
            , data: {}
            , success: function(response){ 


				//해당 구만 검색
				let guNameArr = ['중구','강동구','강서구','강남구','강북구'];
				
                let mise = response["RealtimeCityAir"]["row"]; 

				let appendHtml = "";
				
				$('#munjiPTitle').html('서울특별시 기준');;
				
                //url에서 가지고 온 데이터 중 row 리스트 데이터만 뽑아 mise 변수에 넣음 
                for(let i = 0; i < mise.length; i++){ 
                    //반복문으로 mise 변수에 있는 구 이름, 미세먼지 데이터 값을 변수에 넣고 출력하는 반복문 
                    let gu_name = mise[i]["MSRSTE_NM"]; //구 이름
                    let gu_mise = mise[i]["IDEX_MVL"]; // 미세먼지 수치 
                    let gu_mise_nm = mise[i]["IDEX_NM"]; //미세먼지 수치명
                    let pm10 = mise[i]["PM10"]; //미세먼지  role 0~15 매우좋음 / 15~30 좋음 / 30~40 양호
                    let pm25 = mise[i]["PM25"]; //초미세먼지 role 0~8 매우좋음 / 8~15 좋음 / 15~20 양호
                    let no2 = mise[i]["NO2"]; //이산화질소 role은 없음
                    let str_mise = "";
                    let miseStatus = "";
                    
                    if(guNameArr.indexOf(gu_name) > -1){

						miseStatus = (gu_mise >= 80 ? "red" : "green");				

		    			appendHtml += 	'<tr>';
			      		appendHtml += 		'<th>'+gu_name+'</th>';
		  				appendHtml += 		'<td style="color:'+miseStatus+'" >'+gu_mise+'</td>';
						appendHtml += 		'<td>'+gu_mise_nm+'</td>';
		    			appendHtml += 	'</tr>';
                    }
                } 

                $('#munjiTbody').html(appendHtml);
                
            } 
        }) 
    };

    //외부에 공개하기 위한 역할 (공용 인터페이스)
    //모듈 패턴 외부에서 함수 호출이 가능한 것은 객체 리터럴을 반환값으로 넘겨줬기 때문
    return {
        init : function(){
            
        }
        , getMsg : function() {
            console.log(message);
        }
        , getCallAjax : callAjax
    }
}();
