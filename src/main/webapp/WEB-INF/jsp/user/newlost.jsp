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
	<script src="<%=basePath%>source/jquery-1.7.1.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>source/jquery-ui-1.8.20.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>source/ajaxfileupload.js" type="text/javascript"></script>
	
	<link rel="stylesheet" href="<%=basePath%>source/bootstrap.min.css" />
	<link rel="stylesheet" href="<%=basePath%>source/css/bootstrap-datepicker3.css">
	<link rel="stylesheet" href="<%=basePath%>source/style.css" />
	
	<script type="text/javascript" src="<%=basePath%>source/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>source/bootstrap.js"></script>
	<script type="text/javascript" src="<%=basePath%>source/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>source/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>
	<script src="<%=basePath%>source/ajaxfileupload.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=basePath%>source/ajax.js"></script>
	<script type="text/javascript">
	 	function ajaxFileUpload() {
			$.ajaxFileUpload({ 
				type: "POST",//post提交
				url: "FileUploadServlet",
				secureuri: false, //是否需要安全协议，一般设置为false
				fileElementId: "inputfile", //文件上传域的ID
				dataType: "json", //返回值类型 一般设置为json
				success: function (data, status)  {//服务器成功响应处理函数
					if(data.message!=""){
						alert(data.message);
					}else{
						$("#face").attr("src", data.url);
                     var path = document.getElementById("lostimgname");
                     path.value = data.url;
					}
				},
				error: function (data, status, e){//服务器响应失败处理函数
				    alert(e);
				}
			});
		 }
	</script>
	<title>Lost&amp;Found System</title>
</head>
<body>
	<header style="position: relative;">
		<nav class="container">
			<div class="nav_l">
				<h2 class="head_tilte"><a href="#">SUT校园失物招领系统</a></h2>

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
				<a class="poi-crumb__item" href="user/userlost">
					失物信息
				</a>
				<span class="poi-crumb__split">
			  		<i class="glyphicon glyphicon-chevron-right" aria-hidden="true"></i> 
			  	</span>
				<a class="poi-crumb__item" >
					失物信息发布
				</a>
			</nav>
		</div>
		
		<div style="width: 40%;float:right;vertical-align: top;">
			<h2>您可以上传一张图片：</h2>
			<p style="margin-bottom: 10px">您选择的图片上传成功后将在下面直接预览。</p>
			<div style="text-align: center;">
				<div style="overflow: hidden;height: 245px;border:2px solid #CCC;box-shadow:0 0 10px rgba(100, 100, 100, 1);">
					<img src="img/faces/default.jpg" id="face" height="245px" />
				</div>
				
				<p style="margin-top: 15px">
					更新头像图片，请选择头像文件后，点击<span style="color: red;font-weight: bold;">更新图片</span>按钮，上传图片后需要点击左侧
					<span style="color: red;font-weight: bold;">单击发布</span>按钮才能生效
				</p>
				<div>
					<!--<form class="form-inline" role="form" enctype="multipart/form-data" action="upimgServlet" name="imgform" target="submitform" method="post">  -->
						<!--<div class="form-group">  -->
						<div>
							<label class="sr-only" for="inputfile">文件输入</label>
						    <input type="file" id="inputfile" name="uploadFile">
						</div>
						<button class="btn btn-danger" value="更新图片" onclick="ajaxFileUpload()" >更新图片</button>
				<!--</form>  -->	
				</div>
			</div>
		</div>
		
		<div class="publishbox">
			<div class="title" >
				<h3>Lost - 发布</h3>
			</div>
			
			<div class="publishbox_content">
				<form class="form-horizontal form_psw" id="pubform" role="form" method="post" action="user/newlost" > 
					<input type="hidden" value="/img/faces/default.jpg" id="lostimgname" name="lostimg" />
					<div class="form-group">
				    	<label for="psw0" class="col-sm-2 control-label">物品描述 :</label>
				    	<div class="col-sm-7">
				      	<input type="text" class="form-control" id="psw0" name="describe" value='' placeholder="请输入..." required="required">
				    	</div>
				    	<div style="float: left;margin-top: 10px;color: #757575;">(字数20字以内)</div>
				 	</div>
				 	<div class="form-group">
				 		<label for="type0" class="col-sm-2 control-label">物品分类 :</label>
				    	<div class="col-sm-7">
						    <select id="type0" class="form-control" name="type">
						      <c:forEach items="${types}" var="type">
						      <c:if test="${type.tid == '1'}">
						      	<option value="${type.tid }" selected = "selected">${type.typename }</option>
						      </c:if>
						      <c:if test="${type.tid != '1'}">
						      	<option value="${type.tid }">${type.typename }</option>
						      </c:if>
						      </c:forEach>
						    </select>
					    </div>
					</div>
					<div class="form-group">
				    	<label for="psw1" class="col-sm-2 control-label">丢失时间 :</label>
				    	<div class="col-sm-7">
				      	
							<div class="input-group date datepicker">
							    <input type="text" name="date"  class="form-control" id="psw1"  required="required">
							    <div class="input-group-addon">
							        <span class="glyphicon glyphicon-th "></span>
							    </div>
							</div>
						
				      	<!--<input type="text" class="form-control" id="psw1" name="password1" value='' placeholder="请输入...">-->
				    	</div>
				 	</div>
				 
				 	<div class="form-group">
				    	<label for="psw2" class="col-sm-2 control-label">丢失地点 :</label>
				    	<div class="col-sm-7">
				      	<input type="text" class="form-control" id="psw2" name="place" value='' placeholder="请输入..."  required="required">
				    	</div>
				 	</div>
				 	
				 	<div class="form-group">
				    	<label for="psw2" class="col-sm-2 control-label" style="height: 34px;">联系电话 :</label>
				    	<div style="float: left;font-size: 11px;padding-top: 10px ;padding-left: 13px;">
				    		${user.tel }
				    	</div>
				    	<div style="float: right;font-size: 11px;padding-top: 10px ;padding-right: 85px;">
				    		(如需更改，请修改个人信息)
				    	</div>
				 	</div>
				 	
				 	<div class="form-group">
				    	<label for="psw2" class="col-sm-2 control-label">联系邮箱 :</label>
				    	<div style="float: left;font-size: 11px;padding-top: 7px ;padding-left: 13px;">
				    		${user.email }
				    	</div>
				    	<div style="float: right;font-size: 11px;padding-top: 10px ;padding-right: 85px;">
				    		(如需更改，请修改个人信息)
				    	</div>
				 	</div>
				 	<br/>
				 	<div class="form-group">
					    <div class="col-sm-offset-4 col-sm-5">
					      <input style="display: none;" type="submit" id="sub" value="submit" />
					      <button type="button" class="btn btn-default" onclick="publish()">单击发布</button>
					    </div>
					</div>
				</form>
			</div>
		</div>
		<div id="removediv"></div>
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
		$(function(){
			$('.datepicker').datepicker({
				language: 'zh-CN'
			});
		})
		

	</script>
<script type="text/javascript">
	function reloadPage()
	{
		var t = setTimeout(function(){window.location.reload();},1000);
	 
	}
	function publish(){
	
	
	var des = $("#psw0").val();
	
	var pos = $("#psw2").val();
	var classname = $("#classname").val();
	
	if(des.length>=20){
  		 alert("物品描述过长！");
   		 
   		 return false;
	}
	else if(pos.length>=25){
		alert("地点描述过长！");
		return false;
	}
	else{
		
		$("#sub").click();
	}
	}
	$(document).ready(function () {
	       var msg ="${msg}";
	        if(msg!="")
	        {
	            alert(msg);
	            window.location.href="user/newlost";
	           
	        }
	    });
</script>
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
</body>
</html>