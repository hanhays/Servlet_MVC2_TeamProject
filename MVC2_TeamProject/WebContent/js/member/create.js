$(function() {
		var old_year = $("#year").val();
		var old_month = $("#month").val();
		var new_year = null;
		var new_month = null;
		var date = null;
		$("#year").change(function() {
			new_year = $("#year").val();
			getDate();
		});
		$("#month").change(function() {
			new_month = $("#month").val();
			getDate();
		});
		function getDate() {
			if (new_year == null && new_month == null) {
				year_month_change(old_year, old_month);
			} else if (new_year == null && new_month != null) {
				year_month_change(old_year, new_month);
			} else if (new_year != null && new_month == null) {
				year_month_change(new_year, old_month);
			} else {
				year_month_change(new_year, new_month);
			}
		}
		function year_month_change(year, month) {
			date = new Date(year, month, 0).getDate();
			$("#date").html("");
			getDates(date);
		}
		function getDates(date) {
			for (var i = 1; i <= date; i++) {
				if (String(i).toString().length < 2) {
					$("#date").append(
							"<option value='0" + String(i) + "'>0" + String(i)
									+ "</oprion>");
				} else {
					$("#date").append(
							"<option value='" + i + "'>" + i + "</oprion>");
				}
			}
		}
		getDate();
	});
/**====================================================================**/
var nickName = null;
var msg = null;
$(function(){
	var id = $("input[name='m_id']");
	var nickName = $("input[name='m_nickname']");
	var msg =null;
$(id).change(function(){
	var idcheck = id.val();
	msg=/^[0-9]/.test(idcheck)?"숫자로 시작하실수 없습니다.":
		/.*\s.*/.test(idcheck)?"빈문자는 사용할수 없습니다.":
		 /.*[\W].*/.test(idcheck)?"특수문자는 사용할수 없습니다.":
		idcheck.length<5 || idcheck.length>12 ?"5글자 이상 12글자 이하 입니다.":
		/^[a-zA-Z0-9]*$/.test(idcheck)?"ok":"숫자와 영문으로만 입력해주세요.";
	if(msg!="ok"){
		$("#id_check_msg").html(msg);
		$("#id_check_msg").attr("style","color:red;");
	}else{
		duplicate(id.serialize());
	} 
}); 
$(nickName).change(function(){
	var nick = nickName.val();
	msg = /^[ㄱ-ㅎㅏ-ㅣ]*$/.test(nick)?"자음과 모음은 사용하실수 없습니다.":
		 /^[0-9]/.test(nick)?"숫자로 시작할수 없습니다.":
			 /.*\s.*/.test(nick)?"빈문자는 사용할수 없습니다.":
				 /.*[\W].*/.test(nick)?"특수문자는 사용할수 없습니다.":
			 /^[가-힣]*$/.test(nick)?
				 nick.length<2||nick.length>7?"한글은 2글자 이상 8글자 이하 입니다.":"ok"
				 :nick.length<4||nick.length>15?"영어는 4글자 이상 16글자 이하 입니다.":"ok";
console.log("value = "+nick);
console.log("value length = "+nick.length);

if(msg!="ok"){
	$("#nickName_check_msg").html(msg);
	$("#nickName_check_msg").attr("style","color:red;");
}else{
	duplicate(nickName.serialize());
} 
});
});
function duplicate(data){
$.ajax({
	type : 'post',
    data : {
		data:data
    },
    url : "check.do",
    success : function(data) {
    	var datas = null;
    	var selecter = null;
    	var flag = null;
    	var msg = null;
    	var color = null;
    	console.log(data);
    	if(data!="망함"){
    		datas = data.split(",");
    		flag = datas[0];
    		selecter = datas[1];
    		if(flag=="중복"){
    			msg = selecter=="#id_check_msg"?"중복된 아이디 입니다.":"중복된 닉네임 입니다.";
        		color = "color:red;";
    		}else{
        		msg = selecter=="#id_check_msg"?"사용가능한 아이디 입니다.":"사용가능한 닉네임 입니다.";
        		color = "color:green;";
    		}
    		$(selecter).html(msg);
    		$(selecter).attr("style",color);
    	}
    }
});
}
/**====================================================================**/
$(function() {
	//password_1_check
	//password_2_check
	var password_1 = $("input[name='password']");
	var password_2 = $("input[name='password_check']");
	//$(password_1).focusout(function(){
	//var pwcheck = password_1.val();
	//var msg = /.*[~|!|@|#|$|%|^|&|*].*/.test(pwcheck)?"특수문자는 한개이상 존재해야 합니다.":"ok";
	//$("#password_1_check").html(msg);
	//});
	//function passwordCheck1() {
	$(password_2).focus(function() {
		if (password_1.val() == "") {
			$("#password_1_check").html("비밀번호를 입력해 주세요.");
			$("#password_1_check").attr("style", "color:red;");
			password_1.focus();
			//return false;
		}else{
			passwordCheck(password_1.val());
		}
		//return true;
	});
	//}
	function passwordCheck(password_1) {
		console.log("호출");
		$(password_2).focusout(function() {
			$("#password_1_check").html("");
			if (password_2.val() != "") {
				if (password_1 == password_2.val()) {
					$("#password_2_check").html("비밀번호가 일치합니다.");
					$("#password_2_check").attr("style", "color:greed");
				} else {
					$("#password_2_check").html("비밀번호가 불일치합니다.");
					$("#password_2_check").attr("style", "color:red");
				}
			}
		});
	}
});