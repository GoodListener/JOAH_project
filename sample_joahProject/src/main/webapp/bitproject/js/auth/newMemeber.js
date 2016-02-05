// 회원가입 관련 자바스크립트.

	// 프로필 사진.
  $('#fileupload').fileupload({
      url: 'ajax/upload.do',
      dataType: 'json',
      maxFileSize: 10000000,
      disableImageResize: /Android(?!.*Chrome)|Opera/
        .test(window.navigator.userAgent),
    }).on('fileuploadsubmit', function(e, data) {
      // 서버에 일반 폼 데이터도 보내고 싶으면, submit 하기 전에
      // 다음과 같이 formData 프로퍼티에 값을 설정하라!
      /*
      data.formData = {
        data1: 'okok',
        data2: 'nono'
      };
      */
    }).on('fileuploaddone', function(e, data) {
      console.log(data.result);
      $('#files').html('');
      $.each(data.result.data,function (index, file) {
          $('<span/>')
          .text(file.name 
              + '(' + file.originName + ')'
              + ', ' + file.size)
              .appendTo('#files');
          $('#fphotofile').val(file.name);
      })
      });
   
  /* 회원가입 */
  ////////////////
  var uID = $("#signup-userID");
  var uPWD = $("#signup-password");
  var uPWDcheck = $("#signup-check-password");
  var uEmail = $("#signup-email");
  var uName = $("#signup-username");
  var uAge = $("#signup-userAge");
  var uGender = $("input[name='radios']:checked");
  var isExistID = false;
  /* 아이디 중복 체크 */
  
  $("#signup-userID").keypress(function(event) {
	  isID = /^[a-zA-Z0-9]{2,20}$/;
	    if( uID.val() == "" ){
	      $("#span-id-error").attr("class","cd-error-message is-visible");
	      $("#span-id-error").text("아이디를 입력해주세요.");
	      $("#signup-userID").addClass("has-error");
	      $("#id-confirm").addClass("notvisible");
	    } else if (isExistID) {
	      $("#span-id-error").attr("class","cd-error-message is-visible");
	      $("#span-id-error").text("이미 존재하는 아이디 입니다.");
	      $("#signup-userID").addClass("has-error");
	      $("#id-confirm").addClass("notvisible");
	      isExistID = false;
	    } else if (!isID.test(uID.val())) {
	      $("#span-id-error").attr("class","cd-error-message is-visible");
	      $("#span-id-error").text("영문 혹은 숫자로 입력해주세요.(2~20글자)");
	      $("#signup-userID").addClass("has-error");
	      $("#id-confirm").addClass("notvisible");
	    } else {
	      $("#span-id-error").attr("class","cd-success-message");
	      $("#span-id-error").addClass("is-visible");
	      $("#signup-userID").addClass("has-success");
	      $("#id-confirm").attr("class", "id-confirm visible");
	      $("#id-confirm").text("좋은 아이디네요.");
	    }
  });
  
  $("#signup-userID").blur(function(event) {
    $.getJSON('ajax/idcheck.do', function(resultObj){
        for (var member of resultObj.data) {
          if(uID.val() == member.id){
            isExistID = true;
          }
        }
    
    isID = /^[a-zA-Z0-9]{2,20}$/;
    if( uID.val() == "" ){
      $("#span-id-error").attr("class","cd-error-message is-visible");
      $("#span-id-error").text("아이디를 입력해주세요.");
      $("#signup-userID").addClass("has-error");
      $("#id-confirm").addClass("notvisible");
      return false;
    } else if (isExistID) {
      $("#span-id-error").attr("class","cd-error-message is-visible");
      $("#span-id-error").text("이미 존재하는 아이디 입니다.");
      $("#signup-userID").addClass("has-error");
      $("#id-confirm").addClass("notvisible");
      isExistID = false;
      return false;
    } else if (!isID.test(uID.val())) {
      $("#span-id-error").attr("class","cd-error-message is-visible");
      $("#span-id-error").text("영문 혹은 숫자로 입력해주세요.(2~20글자)");
      $("#signup-userID").addClass("has-error");
      $("#id-confirm").addClass("notvisible");
      return false;
    } else {
      $("#span-id-error").attr("class","cd-success-message");
      $("#span-id-error").addClass("is-visible");
      $("#signup-userID").addClass("has-success");
      $("#id-confirm").attr("class", "id-confirm visible");
      $("#id-confirm").text("좋은 아이디네요.");
    }
    });
  });
  
  
  $("input[name='radios']").click(function(event) {
    uGender = $("input[name='radios']:checked");
    uGender.addClass("has-success");
  }); /* 성별을 체크했을 때에 uGender 값 바꾸기. */
  
  
  var isPassword = /^[a-zA-Z0-9]{7,15}$/;
  $("#signup-password").keypress(function(event) {
	  console.log(event);
      if (uPWD.val() != "" && isPassword.test(uPWD.val())) {
    	  console.log("성공");
         $("#span-pwd-error").attr("class","cd-success-message");
         $("#span-pwd-error").addClass("is-visible");
         $("#signup-password").addClass("has-success");
        $("#span-pwd-error").text("");
      }
  });
  
  $("#signup-password").blur(function(event) {
      if (uPWD.val() == "") {
        $("#span-pwd-error").attr("class","cd-error-message");
        $("#span-pwd-error").addClass("is-visible");
        $("#signup-password").addClass("has-error");
        $("#span-pwd-error").text("비밀번호를 입력해주세요.");
      } else if (!isPassword.test(uPWD.val())) {
    	$("#span-pwd-error").attr("class","cd-error-message");
        $("#span-pwd-error").addClass("is-visible");
        $("#signup-password").addClass("has-error");
        $("#span-pwd-error").text("비밀번호는 영문, 숫자 조합 7~15자로 입력해주세요.");
      } else {
         $("#span-pwd-error").attr("class","cd-success-message");
         $("#span-pwd-error").addClass("is-visible");
         $("#signup-password").addClass("has-success");
        $("#span-pwd-error").text("");
      }
  });
  
  $('#signup-check-password').blur(function(event) {
      if (uPWD.val() != uPWDcheck.val()) {
         $("#span-check-error").attr("class","cd-error-message");
        $("#span-check-error").addClass("is-visible");
        $("#signup-check-password").addClass("has-error");
        $("#span-check-error").text("비밀번호가 일치하지 않습니다.");
      } else {
         $("#span-check-error").attr("class","cd-success-message");
        $("#span-check-error").addClass("is-visible");
        $("#signup-check-password").addClass("has-success");
        $("#span-check-error").text("비밀번호가 일치합니다.");
      }
  });
  
  var isEmail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
  uEmail.keypress(function(event) {
	     if(uEmail.val() != "" && isEmail.test(uEmail.val())) {
	       $("#span-email-error").attr("class","cd-success-message");
	       $("#span-email-error").addClass("is-visible");
	       uEmail.addClass("has-success");
	       $("#span-email-error").text("사용할 수 있는 이메일 입니다.");
	     }
	  });
  
  uEmail.blur(function(event) {
     if(uEmail.val() == "") {
        $("#span-email-error").attr("class","cd-error-message");
       $("#span-email-error").addClass("is-visible");
       uEmail.addClass("has-error");
       $("#span-email-error").text("이메일을 입력해주세요.");
     } else if (!isEmail.test(uEmail.val())) {
        $("#span-email-error").attr("class","cd-error-message");
       $("#span-email-error").addClass("is-visible");
       uEmail.addClass("has-error");
       $("#span-email-error").text("이메일 형식에 맞지 않습니다. ex) test@google.com");
     } else {
        $("#span-email-error").attr("class","cd-success-message");
       $("#span-email-error").addClass("is-visible");
       uEmail.addClass("has-success");
       $("#span-email-error").text("사용할 수 있는 이메일 입니다.");
     }
  });
  
  var isName = /^[가-힣a-zA-Z]{2,10}$/; //영어도 되도록 하기.
  uName.keypress(function(event) {
	  console.log(event);
	     if(uName.val() != "" && isName.test(uName.val())) {
	      $("#span-username-error").attr("class","cd-success-message");
	      $("#span-username-error").addClass("is-visible");
	      uName.addClass("has-success");
	      $("#span-username-error").text("잘했어요.");
	    }
	  });
  
  uName.blur(function(event) {
     if(uName.val() == "") {
        $("#span-username-error").attr("class","cd-error-message");
       $("#span-username-error").addClass("is-visible");
       uName.addClass("has-error");
       $("#span-username-error").text("이름을 입력해주세요.");
    } else if (!isName.test(uName.val())) {
       $("#span-username-error").attr("class","cd-error-message");
      $("#span-username-error").addClass("is-visible");
      uName.addClass("has-error");
      $("#span-username-error").text("이름은 한글, 영어(대,소문자) 10자이내로 입력해주세요.");
    } else {
       $("#span-username-error").attr("class","cd-success-message");
      $("#span-username-error").addClass("is-visible");
      uName.addClass("has-success");
      $("#span-username-error").text("잘했어요.");
    }
  });
  
  uAge.blur(function(event) {
    if (uAge.val() == "") {
       $("#span-age-error").attr("class","cd-error-message");
        $("#span-age-error").addClass("is-visible");
        uAge.addClass("has-error");
        $("#span-age-error").text("나이를 입력해주세요.");
    } else {
       $("#span-age-error").attr("class","cd-success-message");
        $("#span-age-error").addClass("is-visible");
        uAge.addClass("has-success");
        $("#span-age-error").text("잘했어요.");
    }
  });
  
  
  var interest = [];
  $("#addBtn").click(function(event) {
     /* console.log(uGender.hasClass("has-success")); */
   if(!uID.hasClass("has-success")) {
      uID.focus();
      return;
   }
   if(!uPWD.hasClass("has-success")) {
      uPWD.focus();
       return;
     }
   if(!uPWDcheck.hasClass("has-success")) {
      uPWDcheck.focus();
       return;
     }
   if(!uEmail.hasClass("has-success")) {
      uEmail.focus();
      return;
   }
   if(!uName.hasClass("has-success")) {
      uName.focus();
       return;
     }
   if(!uAge.hasClass("has-success")) {
      uAge.focus();
      return;
   }
  /*if(!uGender.hasClass("has-success")) {
      $("#span-gender-error").attr("class","cd-error-message");
     $("#span-gender-error").addClass("is-visible");
     uGender.focus();
      return;
   } */ //성별 아직 해결안됨 
   
	  interest[0] = $("input[id='food']:checked").val();
	  interest[1] = $("input[id='entertain']:checked").val();
	  interest[2] = $("input[id='experience']:checked").val();
	  interest[3] = $("input[id='exercise']:checked").val();
	  interest[4] = $("input[id='show']:checked").val();
	  
   console.log(uGender.val());
   console.log($('#fphotofile').val());
   if ($('#fphotofile').val() == "" && uGender.val() == 'male') {     
     $('#fphotofile').val("defaultMale.jpg");
   }
   if ($('#fphotofile').val() == "" && uGender.val() == 'female') {     
     $('#fphotofile').val("defaultFemale.jpg");
   }
  $.post('ajax/addmember.do', {
     id: $('#signup-userID').val(),
     password: $('#signup-password').val(),
     email: $('#signup-email').val() ,
     name: $('#signup-username').val() ,
     age: $('#signup-userAge').val() , 
     gender: uGender.val(),
     photo: $('#fphotofile').val(),
     interestList: JSON.stringify(interest)
   }, function(resultObj) {
     var ajaxResult = resultObj.ajaxResult;
     if (ajaxResult.status == "success") {
              location.href = "index.html";
            } else {
            alert("게시물 등록에 실패했습니다.");
          } 
   }, 'json'); 
  });
  
  //모달 초기화.
  $('.cd-close-form').click(function(event) {
        //로그인 초기화
        $('#signin-userID').val('');
        $('#signin-password').val('');
       
       //비번찾기 초기화.         
        $('#reset-email').val('');
        
        //회원가입 초기화
        //계정관련
        $('#signup-userID').val('');
        $('#span-id-error').addClass("hidden");
        $("#id-confirm").attr("class", "id-confirm hidden");
        
        //비밀번호 관련
        $('#signup-password').val('');
        $("#span-pwd-error").addClass("hidden");
          $('#signup-check-password').val('');
          $("#span-check-error").addClass("hidden");
          
          //이메일 관련
        $('#signup-email').val('');
        $("#span-email-error").addClass("hidden");
        
        //이름 관련
        $('#signup-username').val('');
        $("#span-username-error").addClass("hidden");
        
        //나이 관련
        $('#signup-age').val('');
        $("#span-age-error").addClass("hidden");
        
        //프로필 사진관련.
        $('#fphotofile').val('');
        $('#prev_procImageView').css('display', 'none');
        
        //성별 관련
        $('.signup-gender').prop("checked", false);
        
        //흥미관련
        $('.inter').prop("checked", false);
        totalChecked = 0; // 최대 선택갯수 초기화.
        
        $('.cd-form input.has-success').css('border', '1px solid #d2d8d8');
        
        $('.cd-form input.has-error').addClass(".cd-form input.has-border");
      });
  
  //esc로 닫을때.
  $(document).keydown(function(e){
      if(e.keyCode == 27){
       //로그인 초기화
         $('#signin-userID').val('');
         $('#signin-password').val('');
        
        //비번찾기 초기화.         
         $('#reset-email').val('');
         
         //회원가입 초기화
         //계정관련
         $('#signup-userID').val('');
         $('#span-id-error').addClass("hidden");
         $("#id-confirm").attr("class", "id-confirm hidden");
         
         //비밀번호 관련
         $('#signup-password').val('');
         $("#span-pwd-error").addClass("hidden");
           $('#signup-check-password').val('');
           $("#span-check-error").addClass("hidden");
           
           //이메일 관련
         $('#signup-email').val('');
         $("#span-email-error").addClass("hidden");
         
         //이름 관련
         $('#signup-username').val('');
         $("#span-username-error").addClass("hidden");
         
         //나이 관련
         $('#signup-age').val('');
         $("#span-age-error").addClass("hidden");
         
         //프로필 사진관련.
         $('#fphotofile').val('');
         $('#prev_procImageView').css('display', 'none');
         
         //성별 관련
         $('.signup-gender').prop("checked", false);
         
         //흥미관련
         $('.inter').prop("checked", false);
         totalChecked = 0; // 최대 선택갯수 초기화.
         
         $('.cd-form input.has-success').css('border', '1px solid #d2d8d8');
         
         $('.cd-form input.has-error').addClass(".cd-form input.has-border");
         
          }
  });
 var maxChecked = 3;   //선택가능한 체크박스 갯수
 var totalChecked = 0; // 설정 끝
 function CountChecked(field) {
     if (field.checked)
         totalChecked += 1;
     else
         totalChecked -= 1;
     if (totalChecked > maxChecked) {
         alert ("최대 3개 까지만 가능합니다.");
     field.checked = false;
     totalChecked -= 1;
     }
     
 }
 function ResetCount(){
     totalChecked = 0;
 }