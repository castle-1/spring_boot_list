<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	
		 $(document).ready(function (){
			$('.delete').click(function(event) {//id는 한 문서에서 유일해야한다. 즉. 한 번만 사용가능 , class는 중복사용 가능
				event.preventDefault();
				var obj = $(this).parent().parent(); //클로저
				
				$.ajax({
					type : "DELETE",
					url : $(this).attr("href"),
					success : function(result){
						//data타입이 없다면 text로 들어온다. 컨트롤러에서 리턴된 entity의 메세지가 전달
						console.log(result);
						if(result == "SUCCESS"){
							$(obj).remove();//클로저
						}
					},
					error : function(e){
						console.log(e);
					}										
				});	//ajax			
			});	//click					
		});	//document	
		
	</script>
	
</head>
<body>

	<table  width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>번호</td>
			<td>이름</td>
			<td>제목</td>
			<td>날짜</td>
			<td>히트</td>
			<td>삭제</td>
		</tr>
		<c:forEach items="${list}" var="dto">
			<tr>
				<td>${dto.bId}</td>
				<td>${dto.nName}</td>
				<td><c:forEach begin="1" end="${dto.bIndent}">-</c:forEach> 
				<a href="${pageContext.request.contextPath}/restful/board/${dto.bId}">${dto.bTitle}</a></td>
				<td>${dto.bDate}</td>
				<td>${dto.bHit}</td>
				<td ><a class="delete" href="${pageContext.request.contextPath}/restful/board/${dto.bId}">삭제</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="6"><a href="writeView">글작성</a></td>
		</tr>
	</table>
		<!--페이징 처리부분  -->
	 <c:if test="${pageMaker.prev}"> <!--pageMaker.prev가 참이면 실행  -->
         <a href="${pageMaker.makeQuery(pageMaker.startPage - 1) }">«</a>
      </c:if>

      <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
         <c:out value="${pageMaker.cri.pageNum == idx?'':''}" />
         <a  href="${pageMaker.makeQuery(idx)}">${idx}</a> <!--시작부터 끝까지 번호출력  -->
      </c:forEach>
      
      <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
         <a href="${pageMaker.makeQuery(pageMaker.endPage +1) }"> » </a>
      </c:if> <br>
	
			
</body>
</html>