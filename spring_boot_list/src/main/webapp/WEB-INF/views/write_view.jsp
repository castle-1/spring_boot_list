<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<%-- <sec:csrfMetaTags/> --%>
<!-- 헤어 안에 추가  -->
<!-- csrf 관련이슈 해결방법 : jsp에 meta 태그추가(csrf값 얻기위해) -->
<!-- js에서 csrf 토큰, 헤더등록 -->
<meta name="_csrf" content="${_csrf.token}"> 
<meta name="_csrf_header" content="${_csrf.headerName}">



	<!-- table 부트스트랩 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home</title>
	
	<!-- 버튼 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	<script type="text/javascript">
	
	/*js에서 csrf토큰, 헤더 등록  */
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
	    xhr.setRequestHeader(header, token);
	});

	
	
	 $(document).ready(function(){
		 var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			

			$('#write').submit(function(event) {
				event.preventDefault();
			
				
				
				var nName = $("#nName").val();
				var bTitle = $("#bTitle").val();
				var bContent = $("#bContent").val();
				
				var write = {
						
						nName : nName,
						bContent : bContent,
						bTitle : bTitle
				};
				
				$.ajax({
					type : "POST",
					url : $(this).attr("action"),
					cache : false,
					contentType:'application/json; charset=utf-8',
					data: JSON.stringify(write),
					success : function(result){
						console.log(result);
						if(result == "SUCCESS"){
							console.log("success");
							$(location).attr('href',"${pageContext.request.contextPath}/admin/list");
							
						}
					},
					error : function(e){						
						alert("fail");
						
						console.log(result);
						console.log(e);
					}										
				});	//ajax			
			});					
		
	   }); // end ready()
	
	</script>
	
	<style type="text/css">
		#a{
			color :#ffffff;
			text-decoration: none;
		}
	
	</style>
		
</head>
<body>
	<div class="container">
	  <h2>글작성</h2>
	  <br>	  
	  <table class="table">
	  	<form id="write" action="${pageContext.request.contextPath}/admin/write" method="post">
	    <thead class="thead-dark">
	      <tr>
	        <th>목록</th>
	        <th>내용</th>	       
	      </tr>
	    </thead>
	    <tbody>	      
			<tr>
				<td> 이름 </td>
				<td> <input type="text" id="nName" size = "50"> </td>
			</tr>
			<tr>
				<td> 제목 </td>
				<td> <input type="text" id="bTitle" size = "50"> </td>
			</tr>
			<tr>
				<td> 내용 </td>
				<td> <textarea id="bContent" rows="10" ></textarea> </td>
			</tr>
			<tr >
				<td colspan="2">
				<div class="container">
					<button type="submit" class="btn btn-dark">작성</button>
					<button type="reset" class="btn btn-dark">리셋</button>
					<button type="submit" class="btn btn-dark">
						<a id="a" href="${pageContext.request.contextPath}/admin/list">목록</a>
					</button>
					
				</div>
				</td>
			</tr>
			    
	    </tbody>
	    </form>
	  </table>	 
	</div>	
</body>
</html>
