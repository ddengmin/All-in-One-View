var corona = function(){

	var callAjax = function (){
		
		//console.log('callAjax 함수 실행');
        $.ajax({ 
    	    type: "GET"
            , async: false
            , url: 'https://api.corona-19.kr/korea/beta/?serviceKey=oCIP6xcBgnEm8wrVH31yUKD5YiFSQJsWL'
            , data: {}
            , success: function(data){ 
	
				let result = data.korea;
				$('#coronaPTitle').html(data.API.updateTime.replace('발생현황','발생현황<br>'));

				let appendHtml = "";

	    		appendHtml += 	'<tr>';
	      		appendHtml += 		'<th style="width:50%;">일일 확진자</th>';
  				appendHtml += 		'<td>'+getComma(result.incDec)+' 명</td>';
	    		appendHtml += 	'</tr>';				
	    		appendHtml += 	'<tr>';
	      		appendHtml += 		'<th style="width:50%;">누적 확진자</th>';
  				appendHtml += 		'<td>'+getComma(result.totalCnt)+' 명</td>';
	    		appendHtml += 	'</tr>';
    			appendHtml += 	'<tr>';
	      		appendHtml += 		'<th>누적 사망자</th>';
  				appendHtml += 		'<td>'+getComma(result.deathCnt)+' 명</td>';
    			appendHtml += 	'</tr>';
                
                $('#coronaTbody').html(appendHtml);
                
            } 
    	}) 
	};

    return {
		init : function(){
            
    	}
    	, getCallAjax : callAjax
    }

}();




//공공데이터 활용하기
//https://github.com/dhlife09/Corona-19-API
//https://kibua20.tistory.com/145