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
							$(location).attr('href',"${pageContext.request.contextPath}/restful/list");
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

</head>
<body>

   <table width="500" cellpadding="0" cellspacing="0" border="1">
      <form id="modify" action="${pageContext.request.contextPath}/restful/modify/${content_view.bId}", method="post">
         <input type="hidden" id="bId" value="${content_view.bId}">
         <tr>
            <td> 번호 </td>
            <td> ${content_view.bId} </td>
         </tr>
         <tr>
            <td> 히트 </td>
            <td> ${content_view.bHit} </td>
         </tr>
         <tr>
            <td> 이름 </td>
            <td> <input type="text" id="nName" value="${content_view.nName}"></td>
         </tr>
         <tr>
            <td> 제목 </td>
            <td> <input type="text" id="bTitle" value="${content_view.bTitle}"></td>
         </tr>
         <tr>
            <td> 내용 </td>
            <td> <textarea rows="10" id="bContent" >${content_view.bContent}</textarea></td>
         </tr>
         <tr >
         <!--id="modify" data-bid = "${content_view.bId}"  -->
            <td colspan="2"> <input type="submit" value="수정"> &nbsp;&nbsp; <a href="${pageContext.request.contextPath}/restful/list">목록보기</a> &nbsp;&nbsp; &nbsp;&nbsp; <a href="${pageContext.request.contextPath}/restful/relpyView/${content_view.bId}">답변</a></td>
         </tr>
      </form>
   </table>
   
</body>
</html>