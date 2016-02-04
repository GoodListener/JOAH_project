var myName = '';
var coupleCheck = '';
var search = '';
var word = '';
if (location.href.indexOf('?') != -1) {
	search = location.href.split('?')[1].split('=')[1].split('&')[0];
	word = location.href.split('&')[1].split('=')[1];
}

$.getJSON('ajax/detailmember.do?id=' + sessionStorage.getItem('loginSession'), function(resultObj) {
  var ajaxResult = resultObj.ajaxResult;
  if (ajaxResult.status == "success") {
    var member = ajaxResult.data;
    /* $("#photo_div").html("<img src='images/portfolio/"+ member.photo +"' class='img-responsive' alt=''>") */
    $("#member_id").val(sessionStorage.getItem('loginSession'));
    $("#member_pwd").val(member.pwd);
    $("#member_name").val(member.name);
    myName = member.name;
    console.log(myName);
    $("#member_email").val(member.email);
    $("#member_gender").val(member.gender);
    $("#member_age").val(member.age);
    $("#member_cid").val(member.cid);
    coupleCheck = member.cid;
    console.log("coupleCheck : " + coupleCheck);
    
    
    if (coupleCheck === null || coupleCheck === '') {
    	$.getJSON('ajax/CR/requestCheck.do', function(resultObj){
		    for (var request of resultObj.data) {
		     if (request.my_id == sessionStorage.getItem('loginSession')) {
		    	 
		    	 var soloHeaven = $('#coupleCheck');
		    	 
		    	 $('#coupleCheck')
		     	.html(
		     	  "<div>" + request.request_id +"님 께 커플신청중...</div>"
		     	+ "<button type='button' id='cancleCouple'>커플 신청 취소</button>").appendTo(soloHeaven);
		    	 
		    	 $('#cancleCouple').click(
		 				function(event) {
		 					var my_Id = sessionStorage.getItem('loginSession')
		 					$.getJSON('ajax/CR/delete.do?my_id=' + my_Id, function(resultObj) {
		 						console.log("커플신청 취소함.");
		 						var ajaxResult = resultObj.ajaxResult;
		 						if (ajaxResult.status == "success") {
		 							alert("커플 신청 취소 성공.");
		 							location.href = "mypage.html";
		 						} else {
		 							alert("커플 신청 중이 아닙니다.");
		 						}
		 					})
		 				});
		     } else if (request.request_id == sessionStorage.getItem('loginSession')) {
		    	 
		    	 console.log("111111111111118");
		    	 console.log(request.request_id);
		    	 var dd = request.my_id;
		    	 console.log(dd);
		    	 
		    	 var soloHeaven = $('#coupleCheck');
		    	 
		    	 $('#coupleCheck')
		     	.html(
		         "<div>"+dd+"님 께서 커플신청을 하셨습니다...</div><br>"
		         + "<button type='button' id='acceptBtn'>커플 수락</button>"
		         + "<button type='button' id='rejectBtn'>커플 거절</button>"		
		         ).appendTo(soloHeaven);
		    	 console.log(dd);
		    	  $('#acceptBtn').click(
			 				function(event) {
			 					var my_Id = sessionStorage.getItem('loginSession')
			 					
			 					$.getJSON('ajax/CR/reject.do?request_id=' + my_Id, function(resultObj) {
			 						console.log("커플신청 수락함.");
			 						var ajaxResult = resultObj.ajaxResult;
			 						if (ajaxResult.status == "success") {
			 							
			 							$.post('ajax/updateCid.do', {
			 								id : my_Id,
			 								cid : request.my_id,
			 							}, function(resultObj) {
			 								var ajaxResult = resultObj.ajaxResult;
			 								if (ajaxResult.status == "success") {
			 								} else {
			 									alert("게시물 변경에 실패했습니다.");
			 								}
			 							}, 'json');
			 							
			 							$.post('ajax/updateCid.do', {
			 								id : request.my_id,
			 								cid : my_Id,
			 							}, function(resultObj) {
			 								var ajaxResult = resultObj.ajaxResult;
			 								if (ajaxResult.status == "success") {
			 								} else {
			 									alert("게시물 변경에 실패했습니다.");
			 								}
			 							}, 'json');
			 							alert("커플 신청 수락 성공.");
			 							location.href = "mypage.html";
			 						} else {
			 							alert("오류!");
			 						}
			 					})
			 				}); 
		    	 
		    	 $('#rejectBtn').click(
		 				function(event) {
		 					var my_Id = sessionStorage.getItem('loginSession')
		 					$.getJSON('ajax/CR/reject.do?request_id=' + my_Id, function(resultObj) {
		 						console.log("커플신청 거절함.");
		 						var ajaxResult = resultObj.ajaxResult;
		 						if (ajaxResult.status == "success") {
		 							alert("커플 신청 거절 성공.");
		 							location.href = "mypage.html";
		 						} else {
		 							alert("오류!");
		 						}
		 					})
		 				});
		    	 
		     }
		     else {
		    	 
      	var soloHeaven = $('#coupleCheck'); 
    	
    	$('#coupleCheck')
    	.html(
    	   "<div>"
 		 + "<form id='form1'>"
		 + "<nav class='search_border'>"
		 + "<select name='search' style='border-radius:2em'>"
		 + "  <option value='id'>아이디</option>"
		 + "</select>&nbsp;&nbsp;&nbsp;"
		 + "<input type='text' class='search_text' style='padding-left: 15px;' name='word'id='search_couple'>"
		 + "<input type='submit' class='search_but' value='회원검색'></nav>"
		 + "</form></div><br><br><div id='searchSolo'></div>"
		 + "<div class='center_content search_id'></div>"
		 + "<div style='float:right; margin: 30px 0;display: inline-flex;'>"
         + "<button type='button' class='btn btn-submit1' id='requestCouple' style='float:left;letter-spacing: 0px;padding: 1px 20px;'>커플 신청</button>"
         + "<button type='button' class='btn btn-submit2' id='cancleCouple' style='float:left;padding: 10px 12px;letter-spacing: 0px;    margin-left: 10px;'>커플 신청 취소</button>"
     + "</div>"
         ).appendTo(soloHeaven);
    	
    	$('#cancleCouple').click(
				function(event) {
					var my_Id = sessionStorage.getItem('loginSession')
					$.getJSON('ajax/CR/delete.do?my_id=' + my_Id, function(resultObj) {
						console.log("커플신청 취소함.");
						var ajaxResult = resultObj.ajaxResult;
						if (ajaxResult.status == "success") {
							alert("커플 신청 취소 성공.");
							location.href = "mypage.html";
						} else {
							alert("커플 신청 중이 아닙니다.");
						}
					})
				});
		     }
    	
    	
		    } // for문 끝
    	}); // checkRequest 끝.
    	
    } else {
    	
    	$.getJSON('ajax/member/coupleinfo.do?id=' + coupleCheck, function(resultObj) {
   		 console.log("coupleCheck2 : " + coupleCheck);	
   		var ajaxResult = resultObj.ajaxResult;
   		var coupleHell = $('#coupleCheck')
   		console.log(resultObj.ajaxResult);
   		if (ajaxResult.status == "success") {
   			
   			$('#coupleCheck')
   			.html(
   			   "<div class='profile text-center'>"
   		     + "<img class='img_mypage'></div>" 
   	         + "<div class='center_content'>"
   	         + "<label style='margin: 15px 0 15px 0px; font-size: 30px; padding-left:20px;'>애인 정보</label>"
   	         + "<div class='sidebar blog-sidebar'>"
   	         + "<div class='sidebar-item categories' style='position:'>"
   	         + "<ul class='nav navbar-stacked' style='margin-top:-5px;padding-top:3px;border-top: 3px solid #80828c;'>"
   		     + "<li><a>ID<input id='couple_id' style='border-radius:13px;'type='text' readonly></a></li>"
   		     + "<li><a>Name<input id='couple_name' style='border-radius:13px;'type='text'readonly></a></li>"
   		     + "<li><a>E-Mail<input id='couple_email' style='border-radius:13px;'type='text' readonly></a></li>"
   		     + "<li><a>Gender<input id='couple_gender' style='border-radius:13px;'type='text' readonly></a></li>"
   		     + "<li><a>Age<input id='couple_age' style='border-radius:13px;'type='text' readonly></a></li><br>" 
   	         + "</ul><div class='farewellBtn'><img src='images/mypage/farewellBtn.png' width='70px' height='50px'>" 
   	         + "<button type='button' id='farewellBtn'>커플끊기</button></div></div></div></div>"
   	         ).appendTo(coupleHell);
   			
   			 var couple = ajaxResult.data;
   			 console.log(ajaxResult.data);
   			 $("#couple_id").val(couple.id);
   			 $("#couple_name").val(couple.name);
   			 $("#couple_email").val(couple.email);
   			 $("#couple_gender").val(couple.gender);
   			 $("#couple_age").val(couple.age);
   			 $(".img_mypage").attr("src", "../profile_image/"+member.photo);
   			 
   			 $('#farewellBtn').click(function(event) {
   				$.post('ajax/farewellCid.do', {
						id : sessionStorage.getItem('loginSession'),
						cid : null
					}, function(resultObj) {
						var ajaxResult = resultObj.ajaxResult;
						if (ajaxResult.status == "success") {
						} else {
							alert("오류.");
						}
					}, 'json');
   				
   				$.post('ajax/farewellCid.do', {
					id : coupleCheck,
					cid : null
				}, function(resultObj) {
					var ajaxResult = resultObj.ajaxResult;
					if (ajaxResult.status == "success") {
						location.href = "mypage.html";
					} else {
						alert("오류..");
					}
				}, 'json');
   				alert("커플이 해제되었습니다.");
   			 });
   		}	
   	});
    }
  }
  
  if (word != '' && search !='') {
		$.getJSON('ajax/coupleSearch.do?search=' + search + '&word=' + word , function(resultObj) {
			var search_Solo = $('#searchSolo');
			 for (var member of resultObj.data) {
				 console.log(member);
				$("#searchSolo")
				.html("<label style='margin: 108px 0 15px 0px; font-size: 30px; padding-left:20px;'>검색 정보</label><br>"
					+ "<div class='profile text-center'>"	
					+ "<div class='sidebar-item categories' style='position:'>"
		            + "<ul class='nav navbar-stacked' style='margin-top:-5px;padding-top:3px;border-top: 3px solid #80828c;'><br><br>"
		            + "<br><br><img src='../profile_image/" + member.photo +"' class='img_mypage' alt='' style='margin: -17px 0 4px 0;'>"
		            + "</div><div>아이디(이름) : "
		            + "<input value='     "  + member.id  + "(" + member.name + ")' style='border-radius:13px;'type='text' readonly></div><br>"
		            + "<div>나이 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<input value='           "  + member.age  
		            + "' style='border-radius:13px;'type='text' readonly></div><br>"
		            ).appendTo(search_Solo);
			 }
			 
			 $('#requestCouple').click(function(event) {
					
					var my_Id = sessionStorage.getItem('loginSession');
					var IdCheck = 0; // 1 이면 커플신청중임 0이면 커플 신청중 아님.
					var CidCheck = 0; //1 이면 상대방이 이미 커플이 있음. 0이면 상대방이 커플이 없음.
					
					console.log("상대방아이디 - " + word);
					console.log("내이메일 - " + my_Id);
					
					if (word == sessionStorage.getItem('loginSession') ) {
						console.log("아무리 외로워도 본인과 커플 ㄴㄴ ");
						alert('본인에게 커플 신청을 할 수 없습니다.');
					} else {
						
						$.getJSON('ajax/couplecheck.do', function(resultObj){
						    for (var member of resultObj.data) {
						    	if (member.id == word) {
						    		
						    		if ((member.cid == null || member.cid == '')) {
						    			
						    			console.log("나는 커플이 없따 ㅠㅠ!!!");
						    			console.log(member);
						    			
						    			$.getJSON('ajax/CR/idcheck.do', function(resultObj){
						    			    for (var couple_request of resultObj.data) {
						    			      if(my_Id == couple_request.my_id){
						    			    	  IdCheck = 1;
						    			      }
						    			    }
						    			    console.log(IdCheck);
						    			    if (IdCheck!=1) {
						    				    	$.post('ajax/CR/add.do', {	
						    						my_id : my_Id,
						    						my_name : myName,
						    						request_id : word,
						    					    }, function(resultObj) {
						    						var ajaxResult = resultObj.ajaxResult;
						    						if (ajaxResult.status == "success") {
						    							alert("커플 신청 성공.");
						    							location.href = "mypage.html";
						    						} else {
						    							alert("오류!!!!");
						    						}
						    					}, 'json');  
						    			    } else {
						    			    	alert("커플 신청중 입니다. 커플 신청을 취소하고 다시 신청해주세요.");
						    			    }
						    			})
						    			
						    		} else {
						    			console.log("나는 커플이 있따!!!");
						    			console.log(member);
						    			alert("상대방은 이미 커플중입니다..");
						    		}
						    	} 
						    }
						 })
					}
				});
		});
	}
});