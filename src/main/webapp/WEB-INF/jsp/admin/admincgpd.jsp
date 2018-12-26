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
		<title>失物招领管理系统</title>
	<meta charset="utf-8">
	<meta http-equiv="charset" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<link rel="stylesheet" href="<%=basePath%>assets/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=basePath%>assets/vendor/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=basePath%>assets/vendor/linearicons/style.css">
	<link rel="stylesheet" href="<%=basePath%>assets/vendor/chartist/css/chartist-custom.css">
	<!-- MAIN CSS -->
	<link rel="stylesheet" href="<%=basePath%>assets/css/main.css">
	<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
	<link rel="stylesheet" href="<%=basePath%>assets/css/demo.css">
	<link rel="stylesheet" href="<%=basePath%>assets/css/iconfont.css">
	<!-- import CSS -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui@2.3.7/lib/theme-chalk/index.css">
    
	<link rel="stylesheet" href="<%=basePath%>assets/css/myStyle.css">
	<!-- GOOGLE FONTS -->
	<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
	<!-- ICONS -->
	<link rel="apple-touch-icon" sizes="76x76" href="<%=basePath%>assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="<%=basePath%>assets/img/favicon.png">

</head>
<body>
<!-- WRAPPER -->
	<div id="wrapper">
		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="brand">
				<a><span class="nav_title">SUT校园失物招领系统</span></a>
			</div>
			<div class="container-fluid">
				<div class="navbar-btn">
					<button type="button" class="btn-toggle-fullwidth"><i class="iconfont arrow">&#xe624;</i></button>
				</div>
				<div id="navbar-menu">
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="assets/img/user.png" class="img-circle" alt="Avatar"> <span><?php echo $username ?></span> <i class="iconfont">&#xe6f6;</i></a>
							<ul class="dropdown-menu">
								<li><a href="admin/changepsw"><i class="iconfont">&#xe603;</i> <span>更改密码</span></a></li>
								<li><a href="#"><i class="iconfont">&#xe60e;</i> <span>退出登录</span></a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</nav>
		<!-- END NAVBAR -->
		<!-- LEFT SIDEBAR -->
		<div id="sidebar-nav" class="sidebar" style="background-color:#FFF">
			<div id="app">
				<el-row class="tac">
					  <el-col :span="24">
					    <el-menu default-active="1" class="el-menu-vertical-demo">
					      <el-menu-item index="1">
					      	<a  slot="title" href="admin"  style="display:block;">
					        <i class="el-icon-location"></i>
					        <span>首页</span>
					        </a>
					      </el-menu-item>
					      <el-menu-item index="2">
					      	<a slot="title" href="admin/adminlost" style="display:block;">
					        <i class="el-icon-menu"></i>
					        <span>失物管理</span>
					        </a>
					      </el-menu-item>
					      <el-menu-item index="3">
					      	<a slot="title" href="admin/adminfound" style="display:block;">
					        <i class="el-icon-document"></i>
					        <span>招领管理</span>
					        </a>
					      </el-menu-item>
					      <el-menu-item index="4">
					      	<a slot="title" href="admin/admintype" style="display:block;">
					        <i class="el-icon-edit-outline"></i>
					        <span>类别管理</span>
					        </a>
					      </el-menu-item>
					      <el-menu-item index="5">
					        <a slot="title" href="admin/adminusers" style="display:block;">
					        <i class="el-icon-setting"></i>
					        <span>用户管理</span>
					        </a>
					      </el-menu-item>
					    </el-menu>
					  </el-col>
				</el-row>
			</div>
		</div>
		<!-- END LEFT SIDEBAR -->
		<!-- MAIN -->
		<div class="main" style="background: none;">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<!-- OVERVIEW -->
					<div class="panel panel-headline">
						<div class="panel-heading">
							<h3 class="panel-title">修改密码</h3>
							
						</div>
						<div class="panel-body">
							<div class="li">
								<div class="container psw_content">				
									<form class="container form-horizontal form_psw" role="form" method="post" action="admin/changepsw"> 
										<div class="form-group">
									    	<label for="psw1" class="col-sm-2 control-label">新密码</label>
									    	<div class="col-sm-4">
									      	<input type="password" class="form-control" id="psw1" name="password1" placeholder="请输入密码(6-10位)" required>
									    	</div>
									 	</div>
									 	<div class="form-group">
									    	<label for="psw2" class="col-sm-2 control-label">确认密码</label>
									    	<div class="col-sm-4">
									      	<input type="password" class="form-control" id="psw2" name="password2" placeholder="请输入密码" required>
									    	</div>
									 	</div>
									 	<div class="form-group">
										    <div class="col-sm-offset-3 col-sm-5">
										      <input style="display: none;" type="submit" id="sub" value="submit" />
										      <button type="button" class="btn btn-default" onclick="change()">确认</button>
										    </div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<script src="<%=basePath%>assets/vendor/jquery/jquery.min.js"></script>
	<script src="<%=basePath%>assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="<%=basePath%>assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
	<script src="<%=basePath%>assets/vendor/chartist/js/chartist.min.js"></script>
	<script src="<%=basePath%>assets/scripts/klorofil-common.js"></script>
	<script src="<%=basePath%>assets/scripts/vue.js"></script>
	<script src="//unpkg.com/element-ui@2.3.7/lib/index.js"></script>
	<script>
	new Vue({
		el:"#app"
	})
 
	</script>
	<script type="text/javascript">
	function change(){
		var psw1=$("#psw1").val();
		var psw2=$("#psw2").val();
		
		if(psw1.length<6||psw1.length>10){
			alert("新密码长度不合理！");
			return false;
		}
		else if(psw1!=psw2){
			alert("两次密码填写不一致");
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
	            window.location.href="admin";
	        }
	    });
	</script>
</body>
</html>
