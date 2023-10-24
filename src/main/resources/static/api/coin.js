var coin = function(){
    
    var message = "coin.js 준호";
    
    var callAjax = function (){
        //console.log('coin callAjax 함수 실행');
        
        //''; 넣어주지 않으면 undefined 나와버림...

		var bithumb;
		var upbit;
		var binance;
		
		var appendHtml = "";

        $.get(
			"https://api.binance.com/api/v1/ticker/allPrices"
            , function(data){
				// 1. 바이넨스 획득
				//비동기 방식이기 때문에 ajax의 성공콜백인 done을 이용
                var binanceBtc = data[11].price;
                var binanceBtcSub = binanceBtc.substr(0,5);
				binance = getComma(binanceBtcSub)+' 달러';
				appendHtml +=	'<tr>';
			  	appendHtml += 		'<th style="width:50%;">바이넨스</th>';
				appendHtml += 		'<td>'+binance+'</td>';
				appendHtml += 	'</tr>';
            }
		).done(function() {
			// 2. 업비트 획득
			//비동기 방식이기 때문에 ajax의 성공콜백인 done을 이용
	        $.get(
				"https://crix-api-endpoint.upbit.com/v1/crix/candles/days/?code=CRIX.UPBIT.KRW-BTC"
				, function(data){
					upbit = getComma(data[0].tradePrice)+' 원';
					appendHtml +=  	'<tr>';
				  	appendHtml +=		'<th>업비트</th>';
					appendHtml +=  		'<td>'+upbit+'</td>';
					appendHtml +=  	'</tr>';
	        	}
	        ).done(function(){
				// 3. 빗썸 획득
				//비동기 방식이기 때문에 ajax의 성공콜백인 done을 이용
		        $.get(
					"https://api.bithumb.com/public/ticker/BTC"
					, function(data){
						bithumb = getComma(data["data"].closing_price)+' 원';
						appendHtml +=  	'<tr>';
					  	appendHtml += 		'<th>빗썸</th>';
						appendHtml +=  		'<td>'+bithumb+'</td>';
						appendHtml +=  '</tr>';
						
						$('#coinTbody').html(appendHtml);
		          	}
		          	
		  		);		
			});
			
		});
		

	};

    return {
        init : function(){

        }
        , getMs : function(){
            console.log(message);
        }
        , getCallAjax : callAjax
    }
}();

//코인정보 참고
//https://iri-kang.tistory.com/3

//업비트 (Upbit)
//https://crix-api-endpoint.upbit.com/v1/crix/candles/days/?code=CRIX.UPBIT.KRW-BTC
//KRW-BTC 대신 원하는 마켓 및 심볼을 입력하면 원하는 데이터를 호출

//빗썸 (Bithumb)
//https://api.bithumb.com/public/ticker/ALL
//ALL 대신 원하는 암호화폐 심볼을 입력시 원하는 데이터를 호출

//바이낸스 (Binance)
//https://api.binance.com/api/v1/ticker/allPrices
//allPrices 대신 원하는 심볼을 입력하여 데이터를 호출

