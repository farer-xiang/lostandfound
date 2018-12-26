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
					    <el-menu default-active="5" class="el-menu-vertical-demo">
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
							<h3 class="panel-title">用户管理</h3>
							
						</div>
						<div class="panel-body">
							<div style="float: right;width: 50%;">
								
									<input type="file" id="inputfile" name="file" class="form-control" style="display: inline;width: 70%;vertical-align: middle;" required="required"/>
									<button class="btn btn-default" onclick="ajaxFileUpload()">批量添加</button>
									<p style="color:red">温馨提示：批量导入请选择后缀为<strong>(.xls)</strong>的Excel文件，文件内容格式为五列<strong>（学号、密码、姓名、邮箱、电话）</strong>，请严格按照格式创建文件！</p>
							</div>
							<div class="add">
								<a class="a_btn" href="admin/useradd"><button type="button" class="btn btn-default">添加用户</button></a>
							</div>
							<!-- 表格 -->
							<div class="li">
								<table class="table table-bordered">
								  <caption>账户基本信息</caption>
								  <thead>
								    <tr>
								      <th width="20%">id</th>
								      <th width="60%">用户名</th>
								      
								      <th>操作</th>
								    </tr>
								  </thead>
								  <tbody id="listDivId">
									<tr v-for="userInfo in userInfos" :key="userInfo.uid">
								      <td>{{userInfo.uid}}</td>
								      <td>{{userInfo.name}}</td>
								      
								      <td class="op">
									      <a :href="'admin/userchange/'+userInfo.uid">修改</a>
									      <a @click="del(userInfo.uid)" style="cursor: pointer">删除</a>
									      <a v-if="userInfo.status === '0'" :href="'admin/adminlock/'+userInfo.uid">冻结</a>
									      <a v-else :href="'admin/adminunlock/'+userInfo.uid" style="color:rgb(232, 47, 47);">解冻</a>
								      </td>
								    </tr>
								   
								  </tbody>
								</table>
								<!--<ul  id="pagingDivId" class="pager">
                                   </ul>-->
                                <div align="center">
								  <ul id="pagingDivId" class="pagination pager">
									<li><a href="#">&laquo;</a></li>
									<li class="active"><a href="#">1</a></li>
									<li class="disabled"><a href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li><a href="#">&raquo;</a></li>
								  </ul>
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
	<script type="text/javascript" src="<%=basePath%>source/syq-pagination1.0.js"></script>
	<script src="//unpkg.com/element-ui@2.3.7/lib/index.js"></script>
	<script src="<%=basePath%>source/ajaxfileupload.js" type="text/javascript"></script>
	<script>
	new Vue({
		el:"#app"
	})
 
	</script>
	<script type="text/javascript">
	var vm1=new Vue({
		el:"#listDivId",
		data:{
			userInfos:[]
		},
		methods:{
			del:function(message){
				
				if(confirm("确认删除？"))
					window.location.href='admin/userdel/'+message;
				else
					return false;
			}
		}
	});
	</script>
	<script type="text/javascript">
	function usersAjax(pageIndex,everyPageDataCount){//初始化数据
	    
	    $.ajax({
	        type: "POST",
	        url: "admin/adminusers",
	        data: {
	            pageIndex: pageIndex,//当前第几页（0序列）
	            everyPageDataCount:everyPageDataCount,//每一页多少条数据
	            
	        },
	        dataType: "json",
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            //window.location.href = "/paging/PagingServlet";
	        },
	        success: function (json) {
	            if(json.myData!=""){
	            	vm1.userInfos=json.myData;
	        
	                
	                pagingAjax(json.dataCount,everyPageDataCount,json.pageIndex)
	            }else{
	            	var listHtml="";
	                $("#listDivId").html(listHtml);
	               
	
	            }
	
	
	
	        }
	    });
	}
	
	function pagingAjax(dataCount,everyPageDataCount,nowPage){
	    $("#pagingDivId").zcPage({
	        allDataCount: dataCount,//一共有多少条数据
	        everyPageDataCount: everyPageDataCount,//每一页显示多少条数据
	        nowPageCataCount:nowPage,//当前是第几页
	        success: function (nowPageCataCount/*当前是第几页*/) {
	            usersAjax(nowPageCataCount,everyPageDataCount)
	
	        },
	    });
	}
	$(document).ready(function () {
	    
	    usersAjax(0,8);
	   
	});
	</script>
	 <script type="text/javascript">
	 	function ajaxFileUpload() {
			$.ajaxFileUpload({ 
				type: "POST",//post提交
				url: "XLSFileUploadServlet",
				secureuri: false, //是否需要安全协议，一般设置为false
				fileElementId: "inputfile", //文件上传域的ID
				dataType: "json", //返回值类型 一般设置为json
				success: function (data, status)  {//服务器成功响应处理函数
					if(data.message!=""){
						alert(data.message);
						window.location.href="admin/adminusers";
					}else{
						alert("添加成功");
						window.location.href="admin/adminusers";
					}
				},
				error: function (data, status, e){//服务器响应失败处理函数
				    alert(e);
				}
			});
		 }
	</script>
</body>
</html>
