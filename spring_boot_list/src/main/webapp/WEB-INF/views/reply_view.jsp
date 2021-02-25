<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<!-- ���̺� -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  
  <!-- ��ư -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	
	 $(document).ready(function(){

			$('#reply').submit(function(event) {
				event.preventDefault();
				
				var bId = $("#bId").val();
				var nName = $("#nName").val();
				var bTitle = $("#bTitle").val();
				var bContent = $("#bContent").val();
				var bGroup = $("#bGroup").val();
				var bStep = $("#bStep").val();
				var bIndent = $("#bIndent").val();
				
				var reply = {
						bId : bId,
						nName : nName,
						bContent : bContent,
						bTitle : bTitle,
						bGroup : bGroup,
						bStep : bStep,
						bIndent : bIndent
				};
				
				$.ajax({
					type : "POST",
					url : $(this).attr("action"),
					cache : false,
					contentType:'application/json; charset=utf-8',
					data: JSON.stringify(reply),
					success : function(result){
						console.log(result);
						if(result == "SUCCESS"){
							console.log("success");
							$(location).attr('href',"${pageContext.request.contextPath}/restful/list");
							
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


</head>
<body>

		<div class="container">
		  <h2>��� �ۼ�</h2>
		  <br>
		  <table class="table">
		    <thead class="thead-dark">
		      <tr>
		        <th>���</th>
		        <th>����</th>		        
		      </tr>
		    </thead>
		    
		    <form action = "${pageContext.request.contextPath}/restful/reply/${reply_view.bId}" id="reply" method="post">
         <input type="hidden" id="bId" value="${reply_view.bId}">
         <input type="hidden" id="bGroup" value="${reply_view.bGroup}">
         <input type="hidden" id="bStep" value="${reply_view.bStep}">
         <input type="hidden" id="bIndent" value="${reply_view.bIndent}">
         <tr>
            <td> ��ȣ </td>
            <td> ${reply_view.bId} </td>
         </tr>
         <tr>
            <td> ��Ʈ </td>
            <td> ${reply_view.bHit} </td>
         </tr>
         <tr>
            <td> �̸� </td>
            <td> <input type="text" id="nName" value="${reply_view.nName}"></td>
         </tr>
         <tr>
            <td> ���� </td>
            <td> <input type="text" id="bTitle" value="${reply_view.bTitle}"></td>
         </tr>
         <tr>
            <td> ���� </td>
            <td> <textarea rows="10"  id="bContent">${reply_view.bContent}</textarea></td>
         </tr>
         
         <tr >
            <td colspan="2">
		            	<div class="container">
		            		<button type="submit" class="btn btn-dark">����ۼ�</button>
		            		<button type="reset" class="btn btn-dark">����</button>
		            		
		            		<button type="submit" class="btn btn-dark">
		            			<a href="${pageContext.request.contextPath}/restful/list">��Ϻ���</a>
		            		</button>		            				            		
		            	</div> 		            	  
		            </td>
         </tr>
      </form>
		  </table>
	</div>


</body>
</html>