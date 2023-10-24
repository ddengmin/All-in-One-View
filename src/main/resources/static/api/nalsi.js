var nalsi = function(){


    var callAjax = function (){

        let date = new Date();

        let year = String(date.getFullYear());
        //console.log(year);

        let month = String(date.getMonth()+1);
        if(month < 10){
            month = "0" + month;
        }
        //console.log(month);

        let day = String(date.getDate());
        if(day < 10){
            day = "0" + day;

        }
        //console.log(day);

        let today = year+month+day;
        //console.log('today',today);

        $.ajax({ 
            type: "GET"
            , async: false
            //, url: "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey=OgUcwlkmB1XXfEGbIMpy9hve55iWC4gsQscidDMbwFT02ltg%2FM0Dx6WuBrpcP49FCeLz0WwhQxwH5rjEruB7SQ%3D%3D&pageNo=1&numOfRows=400&dataType=JSON&base_date=20220401&base_time=0500&nx=55&ny=127"
            , url: "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey=OgUcwlkmB1XXfEGbIMpy9hve55iWC4gsQscidDMbwFT02ltg%2FM0Dx6WuBrpcP49FCeLz0WwhQxwH5rjEruB7SQ%3D%3D&pageNo=1&numOfRows=400&dataType=JSON&base_date="+today+"&base_time=0500&nx=55&ny=127"
            , data: {}
            , success: function(data){ 
	

                //최저기온
                let downGion = data["response"]["body"]["items"]["item"][301].fcstValue;
                
                //최고기온
                let upGion = data["response"]["body"]["items"]["item"][120].fcstValue;
                
                //강수확률 >>>> 시간별로 뿌려주기
                let rainProbability = data["response"]["body"]["items"]["item"][120].fcstValue;
                
                let rainProbabilitySub = rainProbability.substr(0,2);

				$('#nalsiPTitle').html('서울특별시 기준');;

				let appendHtml = "";

	    		appendHtml += 	'<tr>';
	      		appendHtml += 		'<th style="width:50%;">최저기온</th>';
  				appendHtml += 		'<td>'+downGion+'°C</td>';
	    		appendHtml += 	'</tr>';				
	    		appendHtml += 	'<tr>';
	      		appendHtml += 		'<th style="width:50%;">최고기온</th>';
  				appendHtml += 		'<td>'+upGion+'°C</td>';
	    		appendHtml += 	'</tr>';
    			appendHtml += 	'<tr>';
	      		appendHtml += 		'<th>강수확률</th>';
  				appendHtml += 		'<td>'+rainProbabilitySub+'%</td>';
    			appendHtml += 	'</tr>';
                
                $('#nalsiTbody').html(appendHtml);


            } 
        }) 
    };
    
    return{
        init : function(){
            
        }
        , getCallAjax : callAjax
    }

    
}();

//기상청 날씨 활용
//https://toubi-tobap.tistory.com/10