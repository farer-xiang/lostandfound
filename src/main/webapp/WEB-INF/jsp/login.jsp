<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
      String path = request.getContextPath();
      String basePath = request.getScheme() + "://"
                  + request.getServerName() + ":" + request.getServerPort()
                  + path + "/";
%>
<base href="<%=basePath%>">  

<link rel="stylesheet" href="<%=basePath%>css/style.css">
<link rel="stylesheet" href="<%=basePath%>css/bootstrap.css">
<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">

<script type="text/javascript" src="<%=basePath%>bootstrap/js/jquery-3.1.1.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>bootstrap/js/ajax.js"></script> 
<script type="text/javascript" src="<%=basePath%>bootstrap/js/bootstrap.js"></script> 
<html>
<head>
    <title>SUT校园失物招领系统</title>

</head>

<body class="index_wrap">
	<div class="container_fluid">
		<div class="login">
			<div class="login_title">
				<h2>SUT校园失物招领系统</h1>
				<p>用户登录</p>
			</div>
			<div id="note"></div>
			<div class="login_content">
				<form action="#" method="post">
					<div class="form-group">
						<label for="username">用户名：</label>
						<input class="in" type="text" name="userNo" id="userNo" onblur="check()" placeholder="请输入您的用户名" required autofocus >
						<div class="note">（学号）</div>
					</div>
					<div class="form-group">
						<label for="password">密&nbsp;&nbsp;码：</label>
						<input class="in" type="password" name="password" id="password" onblur="check()" placeholder="请输入您的密码" required>
						<div class="note">（默认为：000000）</div>
					</div>
					<div class="form-group">
						<label for="check">验证码：</label>
						<input class="in_check" type="text" name="codetext" onkeyup="check()" id="codeInput" >
						<img class="check_img" id="code" src="SaveCodeServlet" onclick="changecode()">
						&nbsp;<img alt="" src="" id="codeResult" style="width: 30px;height: 30px;display: none">
					</div>
					<div class="form-group">
						<button class="btn login_btn" disabled="disabled" id="goBtu" type="button"  type="submit" onclick="login()">登录</button>
					</div>
					<%-- <center><p id="message"></p></center> --%>
				</form>
				
			</div>
		</div>
	</div>
    

   
<script type="text/javascript">  
function changecode() {
	document.getElementById("code").src = "SaveCodeServlet?time=" + Math.random();
}

function check() {
	console.log("111");
	/*var code = document.getElementById("codeInput");
	var userCode = code.value; */
	var userCode=$("#codeInput").val();
	if(userCode.length==0){
		
		var go = document.getElementById("goBtu");
		go.disabled = "disabled";
	}
	else{
		
	var url = "CheckCodeServlet?code=" + userCode + "&amp;time=" + Math.random();
	txtAjaxRequest(url, "get", true, null, checkCallBack, null, null);
	}
}
function checkCallBack(txt, obj) {
	var userNo = document.getElementById("userNo").value;

	var password =document.getElementById("password").value;
	var result = document.getElementById("codeResult");
	var go = document.getElementById("goBtu");
	result.style.display = "inline-block";
	
	if (txt == "{\"strMessage\":\"1\"}") {
		result.src = "img/ok.png";
		if(userNo==""||userNo==null||password==""||password==null){
			
			go.disabled = "disabled";
		}
		else
		go.disabled = false;
	} else {
		result.src = "img/fail.png";
		go.disabled = "disabled";
	}
	

}
function login(){

	 var userNo = document.getElementById("userNo").value;

	 var password =document.getElementById("password").value;
	 var prts =  document.getElementById("message"); 
	 
	    	$.ajax( {
	    		
	    		 
			    type : "post",
			    url : "login", 
			    data : {userNo: userNo, password: password}, 
   				success : function(msg) {
   						
				        if (msg == '1') {
				        	
				        	window.location.href="admin";
							
				        }  
				        else {
				        	if(msg == '2')
				        		window.location.href="user";
				        	else{
				        		if(msg == '3'){
				        			var listHtml="<div class='alert alert-danger alert-dismissable' role='alert'>"+
					        		"<button type='button' class='close' data-dismiss='alert'"+
									"	aria-label='close'>"+
									"	<span aria-hidden='true'>&times;</span>"+
									"</button>"+
									"用户已冻结,请联系管理员"+
									"</div>";
					        		$("#note").html(listHtml);
				        		}
				        		else{
					        		var listHtml="<div class='alert alert-danger alert-dismissable' role='alert'>"+
					        		"<button type='button' class='close' data-dismiss='alert'"+
									"	aria-label='close'>"+
									"	<span aria-hidden='true'>&times;</span>"+
									"</button>"+
									"用户名或密码错误"+
									"</div>";
					        		$("#note").html(listHtml);
				        		}
 				        	}
				        }
			    },
			    error:function(msg){ 
				        alert("error");
			    }
			});


	    
}
</script>
</body>

</html>
