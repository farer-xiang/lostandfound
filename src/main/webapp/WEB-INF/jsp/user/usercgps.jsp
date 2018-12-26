<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
      String path = request.getContextPath();
      String basePath = request.getScheme() + "://"
                  + request.getServerName() + ":" + request.getServerPort()
                  + path + "/";
%>
<base href="<%=basePath%>">  

<html>
<head>
	<link rel="stylesheet" href="<%=basePath%>source/bootstrap.min.css" />
    <link rel="stylesheet" href="<%=basePath%>source/style.css" />
	<script type="text/javascript" src="<%=basePath%>source/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>source/bootstrap.js"></script>
	<script type="text/javascript" src="<%=basePath%>source/syq-pagination1.0.js"></script>
	<title>Lost&amp;Found System</title>
</head>

<body>
	<header style="position: relative;">
		<nav class="container">
			<div class="nav_l">
				<h2 class="head_tilte"><a href="#">SUT校园失物招领系统</a></h2>

				<!--<ul style="margin: 120px 150px 0px 150px;">
          <li>
            <a href="newfound.html" style="padding:20px;">发布招领</a>
          </li>

          <li>
            <a href="newlost.html" style="padding:20px;">发布寻物</a>
          </li>
        </ul>-->
			</div>

			<div class="nav_r">
				<ul>
					<li><img src="./images/default.png" /></li>

					
				</ul>
			</div>
		</nav>
		<div id="search">
			<a id="la" class="home" href=""><i class="iconfont arrow">&#xe604;</i></a>
			<a class="search_item" href="user/newlost">
				发布失物信息
			</a>
			<a class="search_item" href="user/newfound">
				发布拾物信息
			</a>

			 <form action="user/usersearch" method="get" id="searchform">
				<input type="hidden" id="mod" name="mod" value="lost" />
				<div id="searchwindow" class="col-sm-3">
					<div class="input-group">
						<div class="input-group-btn">
							<button id="sel" type="button" class="btn btn-default 
                        dropdown-toggle" data-toggle="dropdown">lost
                            <span class="caret"></span>
                        </button>
							<ul class="dropdown-menu">
								<li>
									<a onclick="lost()">lost</a>
								</li>

								<li>
									<a onclick="found()">found</a>
								</li>
							</ul>
						</div>
						<input type="text" name="scontent" class="form-control" onkeydown="onKeyDown(event)" />
						<span onclick="fsubmit()" class="input-group-addon btn btn-info"><a id="ra" ><i class="glyphicon glyphicon-search"></i></a></span>
					</div>
				</div>
			</form>
			<ul>
       	  <li id="accountmenu">
            <div href="#" style="padding:1rem;;font-family:arial;">
            	<c:if test="${Uname == NULL}">
            		<a href="login">请登录</a>
            	</c:if>
            	<c:if test="${Uname != NULL}">
            	<a title="查看我的相关信息" href="user/userlostinfor">${Uname}</a> , <a href="logout">退出</a>
            	</c:if>
            </div>

          </li>
        </ul>
		</div>
	</header>
	<div class="poi-container">
		<div class="crumb-container">
			<nav class="poi-crumb">
				<a href="" class="poi-crumb__item" title="返回到首页">
					<i class="iconfont arrow">&#xe604;</i>
				</a>
				<span class="poi-crumb__split">
			  		<i class="glyphicon glyphicon-chevron-right" aria-hidden="true"></i> 
			  	</span>
				<a class="poi-crumb__item" href="">
					密码管理
				</a>
			</nav>
		</div>
		
		<div class="box clearfix">
			<div class="lbox">
				<dl>
					<dt>功能导航</dt>
				
					<dd>
						<ul>
							<li><a href="user/userlostinfor">失物管理</a></li>
							<li><a href="user/userfoundinfor">拾物管理</a></li>
							<li><a href="user/userchangeif">信息管理</a></li>
							<li><a href="user/userchangeps">密码管理</a></li>
						</ul>
					</dd>
				</dl>
			</div>
			<div class="rbox">
				<div class="tablecontain">
					<div class="tabletitle">密码管理</div>
					<form action="user/userchangeps" class="form-horizontal" id="changepsw" role="form" method="post"> 
						
					 	<br/>
						<div class="form-group">
					    	<label for="psw1" class="col-sm-2 control-label">新密码</label>
					    	<div class="col-sm-7">
					      	<input type="text" class="form-control" id="psw1" name="password1" value='' placeholder="请输入新密码，长度在6-15位之间" required="required">
					    	</div>
					 	</div>
					 	<br/>
					 	<div class="form-group">
					    	<label for="psw2" class="col-sm-2 control-label">确认密码</label>
					    	<div class="col-sm-7">
					      	<input type="text" class="form-control" id="psw2" name="password2" value='' placeholder="确认新密码" required="required">
					    	</div>
					 	</div>
					 	<br/>
					 			<button type="submit" class="btn btn-default" >确认更改</button>
						    
						
					</form>
				</div>
				<div style="clear: both;"></div>
				
			</div>
		</div>
		
	</div>
  <footer>
			<div class="footer_wrap container">
				<div class="footer_l">
					<ul>
						<li><a href="javascript:void(0);">关于我们</a></li>
						<li>|</li>
						<li><a href="javascript:void(0);">联系我们</a></li>
						<li>|</li>
						<li><a href="javascript:void(0);">支持我们</a></li>
					</ul>
				</div>
				<div class="footer_r">
					<span>&copy;</span>
					<p>2017&nbsp;沈阳工业大学</p>
				</div>
			</div>
	</footer>
	<script type="text/javascript">
	    function onKeyDown(event){
	        var e = event || window.event || arguments.callee.caller.arguments[0];
	                   
	        if(e && e.keyCode==13){ // enter 键
	            /*  alert("此处回车触发搜索事件"); */
	             $("#searchform").submit();
	        }
	        
	    }
	    function fsubmit(){
	    	$("#searchform").submit();
	    }
	    function lost(){
	    	$("#mod").val("lost");
	    	var str='lost<span class="caret"></span>';
	    	$("#sel").html(str);
	    }
	    function found(){
	    	$("#mod").val("found");
	    	var str='found<span class="caret"></span>';
	    	$("#sel").html(str);
	    }
	
	</script>
	<script>
		$(document).ready(function(){
			$("#changepsw").on("submit",function(){
				/* var psw0=$("#psw0").val(); */
				var psw1=$("#psw1").val();
				var psw2=$("#psw2").val();
				if(psw1.length<6||psw1.length>15){
					alert("新密码长度不合理！");
					return false;
				}
				else if(psw1!=psw2){
					alert("两次密码填写不一致");
					return false;
				}
				else{
					alert("修改成功！");
					return true; 
				}
			});
		});
		
	</script>
	</body>

	</html>