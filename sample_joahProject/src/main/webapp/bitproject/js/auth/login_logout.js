//로그인 관련 자바 스크립트.

$(document).ready( function() {
	
    var id = $.cookie("id");
    if (id != null && id != "") {
      $("#signin-userID").val(id); // id textbox에 쿠키값 출력
      $("#saveId").attr("checked", "checked"); // 체크박스에 체크
    }
  });  

    $('#loginBtn').click( function(event) {
          var checkStatus = $("#saveId").is(":checked");
          if (checkStatus) {
            $.cookie("id", $("#signin-userID").val());
          } else {
            $.cookie("id", "");
          }
           
          $.post('ajax/login.do', {
            id : $('#signin-userID').val(),
            password : $('#signin-password').val(),
          }, function(resultObj) {
            var ajaxResult = resultObj.ajaxResult;
            if (ajaxResult.status == "success") {
              idSession = ajaxResult.data.id;
              sessionStorage.setItem('loginSession',idSession);
              location.href = "";
            } else {
              alert("아이디가 존재하지 않거나 패스워드가 일치하지 않습니다.");
            }
          }, 'json');
        });
  
    $('#after_login1').click( function(event) {
         $.post('ajax/logout.do',
         function(resultObj) {
          var ajaxResult = resultObj.ajaxResult;
          if (ajaxResult.status == "success") {
            sessionStorage.removeItem('loginSession');
            location.href = "index.html";
          }
        },'json');
      });
    //알람 이미지를 클릭하면 바가 뜨고 안뜨고 하는 것.
    $('#alarm_img').click(function(event) {
    	if($('#alarm_tri').attr("class") != "on") {
    	$('#alarm_tri').show("slide");
    	$('#alarm_bar').show("slide");
    	$('#alarm_tri').attr("class","on");
    	$('#alarm_bar').attr("class","on");
    	} else {
    	$('#alarm_tri').hide("slide");
    	$('#alarm_bar').hide("slide");
    	$('#alarm_tri').attr("class","off");
    	$('#alarm_bar').attr("class","off");
    	}
    });
    
    $('#admin_logout').click( function(event) {
      $.post('ajax/logout.do',
          function(resultObj) {
        var ajaxResult = resultObj.ajaxResult;
        if (ajaxResult.status == "success") {
          sessionStorage.removeItem('loginSession');
          location.href = "";
        }
      },'json');
    });
  
   if(sessionStorage.getItem('loginSession') != undefined) {
	   if (sessionStorage.getItem('loginSession') != 'admin') {
		   console.log(sessionStorage.getItem('loginSession'));
		     $("#after_login1").show();
		     $("#after_login2").show();
		     $("#after_login2").text(sessionStorage.getItem('loginSession') + "님");
		     $("#after_login3").show();
		     $("#logAlarm").show();
		     $("#after_login5").show();
		     $("#com-content").show();
	   } else {
		   console.log(sessionStorage.getItem('loginSession'));
		     $("#admin_logout").show();
		     $("#admin_menu_id").show();
		     $("#admin_menu_id").text(sessionStorage.getItem('loginSession') + "님");
		     $("#admin_menu").show();
		     $("#com-content").show();
	   }
     
  } else {
    $("#before_login1").show();
    $("#before_login2").show();
    $("#com-content2").show();
  }