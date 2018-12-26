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
					拾物管理
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
					<table class="table">
						<thead>
							<tr>
								<th>标题</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="listDivId">
							<!-- <tr>
								<td>拾取一个钱包</td>
								<td><a class="btn btn-danger" href="">失主已找到</a></td>
							</tr>
							<tr>
								<td>捡到一个学生证</td>
								<td><a class="btn btn-danger" href="">失主已找到</a></td>
							</tr> -->
						</tbody>
					</table>
				</div>
				<div style="clear: both;"></div>
				<div align="center">
					<ul  id="pagingDivId" class="pagination pager">
						
					</ul>
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
	
	function usersAjax(pageIndex,everyPageDataCount){//初始化数据
	    
	    $.ajax({
	        type: "POST",
	        url: "user/userfoundinfor",
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
	                var listHtml="";
	                for(var i=0;i<json.myData.length;i++){
	                    
	                    
	                   listHtml+='<tr>'
							+'<td>'+json.myData[i].fname+'</td>'
							+'<td><a class="btn btn-danger" href="user/cgfoundinfor/'+json.myData[i].fid+'">失主已找到</a></td>>'
							+'</tr>>'; 
	                }
	                
	                $("#listDivId").html(listHtml);
	               
	                pagingAjax(json.dataCount,everyPageDataCount,json.pageIndex)
	            }else{
	            	var listHtml='<div style="margin: auto;"><h1>无相关记录</h1></div>';
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
	</script>
	<script type="text/javascript">
	$(document).ready(function () {
	    
	    usersAjax(0,10);
	   
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