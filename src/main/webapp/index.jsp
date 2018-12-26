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
  <link rel="stylesheet" href="<%=basePath%>source/css/flexslider.css" />
  <!-- import CSS -->
  <link rel="stylesheet" href="https://unpkg.com/element-ui@2.3.7/lib/theme-chalk/index.css">
  <script type="text/javascript" src="<%=basePath%>source/jquery-3.1.1.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>source/bootstrap.js"></script>
  <script type="text/javascript" src="<%=basePath%>source/js/jquery.flexslider-min.js"></script>
  <script type="text/javascript" src="<%=basePath%>source/js/jquery.easing.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>assets/scripts/vue.js"></script>
  <script src="//unpkg.com/element-ui@2.3.7/lib/index.js"></script>
  <title>Lost&Found System</title>
</head>

<body>
  <header style="position: relative;">
    <nav class="container">
      <div class="nav_l">
        <h2 class="head_tilte"><a href="#">SUT校园失物招领系统</a></h2>

      </div>

      <div class="nav_r">
        <ul>
          <li><img src="images/default.png" /></li>

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
                 <input type="text" name="scontent" class="form-control" onkeydown="onKeyDown(event)"/>
                 <span onclick="fsubmit()" class="input-group-addon btn btn-info"><a id="ra"><i class="glyphicon glyphicon-search"></i></a></span>
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
	<div class="poi-container" >
		<div class="crumb-container">
			  <nav class="poi-crumb">
			  	<a href="" class="poi-crumb__item" title="返回到首页"> 
			  		<i class="iconfont arrow">&#xe604;</i>
			  	</a>
			  	<!--<span class="poi-crumb__split">
			  		<i class="glyphicon glyphicon-chevron-right" aria-hidden="true"></i> 
			  	</span>
			  	<a class="poi-crumb__item" href="">
			  		失物信息
			  	</a>-->
				</nav>
		</div>
		<div class="areabox clearfix">
			<div class="larea">
				<div id="app1">
					<template>
					  <el-carousel>
					    <el-carousel-item v-for="(lost,index) in lostInfos" v-if="index<=3" >
					      <a :href="'user/ldetail/'+lost.lid" target="_blank"><img :src="'img/faces/'+lost.limg" height="300px"  style="display:block;margin:auto;width: initial;max-width: 100%;"></img></a>
					    </el-carousel-item>
					    <el-carousel-item v-for="(found,index) in foundInfos" v-if="index<=3" >
					      <a :href="'user/fdetail/'+found.fid" target="_blank"><img :src="'img/faces/'+found.fimage" height="300px" style="display:block;margin:auto;width: initial;max-width: 100%;"></img></a>
					    </el-carousel-item>
					  </el-carousel>
					</template>
				</div>
			</div>
			<div class="rarea" id="listDiv">
					<div class="lb">
						<div id="lblist">
							<!--TITLE-->
							<div class="listt">
								<span><a href="user/userlost">more...</a></span>
								<a href="#">失物 - Form</a>
							</div>
							<!--END-->
							<ul>
								<li v-for="(lost,index) in lostInfos" :key="lost.lid">
									<span style="float:right; font-size:11px;font-family:Arial; padding-left:10px;">{{date1[index]}}</span>
									<a :href="'user/ldetail/'+lost.lid" target="_blank">
										<font style="color:">{{lost.lname}}</font>
									</a>
								</li>
				
							</ul>
						</div>
					</div>
					<div class="rb">
						<div id="rblist">
							<!--tile-->
							<div class="listt">
								<span><a href="user/userfound">more...</a></span>
								<a href="#">拾物 - Form</a>
							</div>
							<!--end-->
							    <ul>
								      <li v-for="(found,index) in foundInfos" :key="found.fid">
								        <span style="float:right; font-size:11px;font-family:Arial; padding-left:10px;">{{date2[index]}}</span>
								        <a :href="'user/fdetail/'+found.fid" target="_blank"><font style="color:">{{found.fname}}</font></a>
								      </li>
								</ul>
						</div>
					</div>
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
   
	<script type="text/javascript">
	var vm1 = new Vue({
		el:'#listDiv',
		data:{
			lostInfos:[],
			foundInfos:[],
			date1:[],
			date2:[]
			
		},
		created:function(){
			 $.ajax({
			        type: "POST",
			        url: "admin/admintop",
			        dataType: "json",
			        error: function (XMLHttpRequest, textStatus, errorThrown) {
			            //window.location.href = "/paging/PagingServlet";
			            
			        },
			        success: function (json) {
			            if(json.lost!=""&&json.found!=""){
			            	vm1.lostInfos=json.lost;
			            	vm1.foundInfos=json.found;
			            	vm1.date1=json.date1;
			            	vm1.date2=json.date2;
			            	app1.lostInfos=json.lost;
			            	app1.foundInfos=json.found;
			                
			            }else{
			            	/* var listHtml="";
			                $(".listDiv").html(listHtml); */
			               
			
			            }
			
			
			
			        }
			    });
		}
		
	});
	
	</script>
	<script>
		var app1=new Vue({
			el:"#app1",
			data:{
				lostInfos:[],
				foundInfos:[],
				
			},
			
		});
		
	</script>
</body>
</html>