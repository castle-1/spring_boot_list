<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- csrf �����̽� �ذ��� : jsp�� meta �±��߰�(csrf�� �������) -->
<!-- js���� csrf ��ū, ������ -->
<meta name="_csrf" content="${_csrf.token}"> 
<meta name="_csrf_header" content="${_csrf.headerName}">

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<!-- table -->
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  
  <!-- ��ư -->
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	<script type="text/javascript">
	
	/*js���� csrf��ū, ��� ���  */
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
	    xhr.setRequestHeader(header, token);
	});
	
	 $(document).ready(function(){

			$('#modify').submit(function(event) {
				event.preventDefault();
				
				var nName = $("#nName").val();
				var bTitle = $("#bTitle").val();
				var bContent = $("#bContent").val();
				var bId = $("#bId").val();
				var form = {
						bId : bId,
						nName : nName,
						bContent : bContent,
						bTitle : bTitle
				};
				
				$.ajax({
					type : "PUT",
					url : $(this).attr("action"),
					cache : false,
					contentType:'application/json; charset=utf-8',
					data: JSON.stringify(form),
					success : function(result){
						console.log(result);
						if(result == "SUCCESS"){
							console.log("success");
							$(location).attr('href',"${pageContext.request.contextPath}/admin/list");
							//window.location.replace("${pageContext.request.contextPath}/restful/board");
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
	  <h2>�� ��ȸ</h2>
	  <br>
	  <table class="table">
	    <thead class="thead-dark">
	      <tr>
	        <th>���</th>
	        <th>����</th>	      
	      </tr>
	    </thead>
	    
	    <tbody>
	      <form id="modify" action="${pageContext.request.contextPath}/admin/modify/${content_view.bId}", method="post">
		         <input type="hidden" id="bId" value="${content_view.bId}">
		         <tr>
		            <td> ��ȣ </td>
		            <td> ${content_view.bId} </td>
		         </tr>
		         <tr>
		            <td> ��Ʈ </td>
		            <td> ${content_view.bHit} </td>
		         </tr>
		         <tr>
		            <td> �̸� </td>
		            <td> <input type="text" id="nName" value="${content_view.nName}"></td>
		         </tr>
		         <tr>
		            <td> ���� </td>
		            <td> <input type="text" id="bTitle" value="${content_view.bTitle}"></td>
		         </tr>
		         <tr>
		            <td> ���� </td>
		            <td> <textarea rows="10" id="bContent" >${content_view.bContent}</textarea></td>
		         </tr>
		         <tr >		        
		            <td colspan="2">
		            	<div class="container">
		            		<button type="submit" class="btn btn-dark">����</button>
		            		<button type="reset" class="btn btn-dark">����</button>
		            		
		            		<button type="submit" class="btn btn-dark">
		            			<a id="a" href="${pageContext.request.contextPath}/admin/list">��Ϻ���</a>
		            		</button>
		            		<button type="submit" class="btn btn-dark">
		            			<a id="a" href="${pageContext.request.contextPath}/admin/relpyView/${content_view.bId}">���</a>
		            		</button>		            		
		            	</div> 		            	  
		            </td>
		         </tr>
	      </form>
	     
	    </tbody>
	  </table>
	</div>


   
</body>
</html>