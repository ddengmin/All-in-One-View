
var jusik = function(){
	
	var fnPlusMinus = function(num) {
  		return num >= 0;
	};


    var callAjax = function(){

        $.ajax({ 
            type: "GET"
            , async: false
            //cros 이슈로 서버에서 api 커넥션 후 데이터를 가져옴 https://digitalbourgeois.tistory.com/57
            //, url: "https://m.stock.naver.com/api/json/sise/dailySiseIndexListJson.nhn?code=KOSPI&pageSize=100&page=1"
            , url: "/api/kospi"
            , dataType : "json"
            , success: function(data){
	
				let result = data.result.siseList[0] || [];
				let appendHtml = "";

				//등락률이 마이너스인지 플러스인지 구한다.
				let getPositive = fnPlusMinus(result.cr);
				
				//true면 플러스,false 면 마이너스
				let isPlusMinus = (getPositive ? "▲" : "▼");

	    		appendHtml += 	'<tr>';
	      		appendHtml += 		'<th style="width:50%;">날짜</th>';
  				appendHtml += 		'<td>'+result.dt.replace(/(\d{4})(\d{2})(\d{2})/g, '$1년$2월$3일');+'</td>';
	    		appendHtml += 	'</tr>';
	    		appendHtml += 	'<tr>';
	      		appendHtml += 		'<th>현재가</th>';
  				appendHtml += 		'<td>'+result.ncv+'</td>';
	    		appendHtml += 	'</tr>';
    			appendHtml += 	'<tr>';
	      		appendHtml += 		'<th>등락률</th>';
			    appendHtml += 		'<td> '+isPlusMinus+' '+result.cv+' ('+result.cr+'%) </td>';
    			appendHtml += 	'</tr>';

				$('#jusikTbody').html(appendHtml);

            }
            , error:function(xhr,status,error){
                console.log("실패?");
                console.log("xhr?",xhr);
                console.log("status?",status);
                console.log("실패?",error);

            } 
        }) 

    };
    return{
        init : function(){
            
        },
        getCallAjax : callAjax
    }
}();
  
  
  


  
  