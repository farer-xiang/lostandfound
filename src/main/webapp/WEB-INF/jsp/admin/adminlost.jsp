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
					    <el-menu default-active="2" class="el-menu-vertical-demo">
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
							<h3 class="panel-title">失物管理</h3>
							
						</div>
						<div class="panel-body">
							<div id="app1">
								<div class="form-group">
							 		<label for="type0" class="col-sm-1 control-label" style="line-height: 34px;">类别 :</label>
							    	<div class="col-sm-5">
									    <select id="type0" class="form-control">
									      <option v-for="t in types" :key="t.tid" :value="t.tid" >{{t.typename}}</option>
									    </select>
								    </div>
								    <div class="col-sm-2">
								    	<a @click="gettype()"><button type="button" class="btn btn-default">查询</button></a>
								    </div>
								</div>
							</div>
							<div class="li">
								<table class="table table-bordered">
								  <caption></caption>
								  <thead>
								    <tr>
								      <th width="60%">物品描述</th>
								      
								      <th>发布时间</th>
								      <th>状态</th>
								      <th>操作</th>
								    </tr>
								  </thead>
								  <tbody id="listDivId">
								  	<tr v-for="(lostInfo,index) in lostInfos" :key="lostInfo.lid">
								      <td>{{lostInfo.lname}}</td>
								      <td>{{dateInfos[index]}}</td>
								      <td>{{lostInfo.lstatus|status}}</td>
								      <td class="op">
									      <a @click="detail(lostInfo.lid)" style="cursor: pointer">查看</a>
									      &nbsp;&nbsp;
									      <div style="display:none;"><a id="clickmodal" href="#modal-container-606893" data-toggle="modal"></a></div>
									      <a @click="del(lostInfo.lid)" style="cursor: pointer">删除</a>
									      
								      </td>
								    </tr>
								    <tr v-if="lostInfos.length === 0">
								    	<td colspan="3">无相关结果</td>
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
							<div class="row clearfix">
								<div class="col-md-12 column">
									<!-- <a href="#modal-container-606893" data-toggle="modal">触发遮罩窗体</a>-->
									
									<div class="modal fade" id="modal-container-606893" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-dialog" style="width:1155px">
											<div class="modal-content">
												<div class="modal-header">
													 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
													<h4 class="modal-title" id="myModalLabel">
														详情
													</h4>
												</div>
												
												<div class="table_container" id="infotable" >
												  	<div class="table_img">
													  		<img :src="'img/faces/'+limg" alt="picture" />
													  </div>
													  
													  <div style="width: 48%;">
													  	<table class="table">
																
																<thead>
																	<tr>
																		<th colspan="2">Lost - 信息表</th>
																		
																	</tr>
																</thead>
																<tbody>
																	<tr class="active">
																		<th width="25%">失主 :</td>
																		<td>{{name}}</td>
																		
																	</tr>
																	<tr class="success">
																		<th>物品描述 :</td>
																		<td>{{lname}}</td>
																		
																	</tr>
																	<tr class="active">
																		<th>物品类别 :</td>
																		<td>{{type}}</td>
																		
																	</tr>
																	<tr class="warning">
																		<th>丢失日期 :</td>
																		<td>{{ldate}}</td>
																		
																	</tr>
																	<tr class="danger">
																		<th>丢失地点 :</td>
																		<td>{{lpos}}</td>
																		
																	</tr>
																	<tr class="active">
																		<th>联系电话 :</td>
																		<td>{{tel}}</td>
																		
																	</tr>
																	<tr class="success">
																		<th>联系邮箱 :</td>
																		<td>{{email}}</td>
																		
																	</tr>
																</tbody>
															</table>
													  </div>
													  
													  
												  </div>
												
											</div>
											
										</div>
										
									</div>
									
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
	<script>
	new Vue({
		el:"#app"
	})
 
	</script>
	<script type="text/javascript">
	var vm1 = new Vue({
		el:'#listDivId',
		data:{
			lostInfos:[],
			dateInfos:[]
			
		},
		methods:{
			detail:function(message){
				$.ajax({
					type:"POST",
					url:"admin/ldetail",
					data:{
						lid:message
					},
					dataType:"json",
					error: function (XMLHttpRequest, textStatus, errorThrown) {
			            //window.location.href = "/paging/PagingServlet";
			        },
			        success: function (json) {
			            if(json.lost!=""){
			            	vm2.name=json.user.name;
			                vm2.lname=json.lost.lname;
			                vm2.ldate=json.time;
			                vm2.lpos=json.lost.lpos;
			                vm2.tel=json.user.tel;
			                vm2.email=json.user.email;
			                vm2.limg=json.lost.limg;
			                vm2.type=json.type.typename;
			            	$("#clickmodal").click();
			            }else{
			            	alert("读取错误");
			            }
			        }
				});
				
			},
			del:function(message){
				
				if(confirm("确认删除？"))
					window.location.href='admin/dellost/'+message;
				else
					return false;
			}
		},
		filters: {
		  status: function (value) {
		    if (value == '0') 
		    	return 'finding';
		    else
		    	return '已找到';
		  }
		}
			
	});
	var vm2 = new Vue({
		el:'#infotable',
		data:{
			name:'',
			lname:'',
			ldate:'',
			lpos:'',
			tel:'',
			email:'',
			limg:'default.jpg',
			type:''
		}
	});
	</script>
	<script type="text/javascript">
	function usersAjax(pageIndex,everyPageDataCount,type){//初始化数据
	    
	    $.ajax({
	        type: "POST",
	        url: "admin/adminlost",
	        data: {
	            pageIndex: pageIndex,//当前第几页（0序列）
	            everyPageDataCount:everyPageDataCount,//每一页多少条数据
	            type:type
	            
	        },
	        dataType: "json",
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            //window.location.href = "/paging/PagingServlet";
	        },
	        success: function (json) {
	            if(json.myData!=""){
	            	vm1.lostInfos=json.myData;
	            	vm1.dateInfos=json.date;
	                
	                pagingAjax(json.dataCount,everyPageDataCount,json.pageIndex,type)
	            }else{
	            	vm1.lostInfos=[];
	            	vm1.dateInfos=[];
	            	pagingAjax(json.dataCount,everyPageDataCount,json.pageIndex,type)
		
	            }
	
	
	
	        }
	    });
	}
	
	function pagingAjax(dataCount,everyPageDataCount,nowPage,type){
	    $("#pagingDivId").zcPage({
	        allDataCount: dataCount,//一共有多少条数据
	        everyPageDataCount: everyPageDataCount,//每一页显示多少条数据
	        nowPageCataCount:nowPage,//当前是第几页
	        success: function (nowPageCataCount/*当前是第几页*/) {
	            usersAjax(nowPageCataCount,everyPageDataCount,type)
	
	        },
	    });
	}
	$(document).ready(function () {
	    
	    usersAjax(0,8,0);
	   
	});
	</script>
	<script>
	var app1=new Vue({
		el:"#app1",
		data:{
			types:[]
		},
		created:function(){
			 $.ajax({
			        type: "POST",
			        url: "user/type",
			        dataType: "json",
			        error: function (XMLHttpRequest, textStatus, errorThrown) {
			           
			            
			        },
			        success: function (json) {
			            if(json.types!=""){
			            	
			            	app1.types=json.types;
			                
			            }else{
			            	
			
			            }
					}
			    });
		},
		methods:{
			gettype:function(){
				var type=$("#type0").val();
				usersAjax(0,8,type);
			}
		}
	});
	</script>
	
</body>
</html>
