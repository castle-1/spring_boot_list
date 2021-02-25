<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 페이징 부트스트랩 설정 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- 페이징을 지정할 태그에 class에 pagination을 넣으면 자동으로 페이징이 된다.-->
<!-- 페이징의 크기를 제어할 수 있는데 pagination-lg를 추가하면 페이징 크기가 커지고, pagination-sm를 넣으면 작아진다. -->
<!-- 큰 페이징 class="pagination pagination-lg", 보통 페이징 class="pagination", 작은 페이징 class="pagination pagination-sm" -->



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
	
	<ul class ="pagination">
	<!-- li태그의 클래스에 disabled를 넣으면 마우스를 위에 올렸을 때 클릭 금지 마크가 나오고 클릭도 되지 않는다.-->
	<!-- disabled의 의미는 앞의 페이지가 존재하지 않다는 뜻이다. -->

		<li class="disabled">
		 <c:if test="${pageMaker.prev}"> <!--pageMaker.prev가 참이면 실행  -->
	         <a href="${pageMaker.makeQuery(pageMaker.startPage - 1) }">«</a>
	      </c:if>
		</li>
		<li class= "active">
		<!-- li태그의 클래스에 active를 넣으면 색이 반전되고 클릭도 되지 않는다. -->
		<!-- active의 의미는 현재 페이지의 의미이다. -->

	      <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
	         <c:out value="${pageMaker.cri.pageNum == idx?'':''}" />
	         <a  href="${pageMaker.makeQuery(idx)}">${idx}</a> <!--시작부터 끝까지 번호출력  -->
	      </c:forEach>
	    </li>
	    <li>
	      <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
	         <a href="${pageMaker.makeQuery(pageMaker.endPage +1) }"> » </a>
	      </c:if>
	    </li>
	</ul>
			
</body>
</html>