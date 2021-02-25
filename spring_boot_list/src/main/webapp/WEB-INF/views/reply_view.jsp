<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
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

   <table  border="1">
      <form action = "${pageContext.request.contextPath}/restful/reply/${reply_view.bId}" id="reply" method="post">
         <input type="hidden" id="bId" value="${reply_view.bId}">
         <input type="hidden" id="bGroup" value="${reply_view.bGroup}">
         <input type="hidden" id="bStep" value="${reply_view.bStep}">
         <input type="hidden" id="bIndent" value="${reply_view.bIndent}">
         <tr>
            <td> 번호 </td>
            <td> ${reply_view.bId} </td>
         </tr>
         <tr>
            <td> 히트 </td>
            <td> ${reply_view.bHit} </td>
         </tr>
         <tr>
            <td> 이름 </td>
            <td> <input type="text" id="nName" value="${reply_view.nName}"></td>
         </tr>
         <tr>
            <td> 제목 </td>
            <td> <input type="text" id="bTitle" value="${reply_view.bTitle}"></td>
         </tr>
         <tr>
            <td> 내용 </td>
            <td> <textarea rows="10"  id="bContent">${reply_view.bContent}</textarea></td>
         </tr>
         <tr >
            <td colspan="2"><input type="submit" value="답변"> <a href="${pageContext.request.contextPath}/restful/list" >목록</a></td>
         </tr>
      </form>
   </table>
   
</body>
</html>