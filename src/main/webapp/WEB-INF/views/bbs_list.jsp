<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/head.jsp" %>

<style>
	
}
</style>

<script>
	$(document).ready(function() {
		$("#nv4").addClass("active");
	});
</script>

</head>

<body>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<article>

	<%@ include file="/WEB-INF/views/common/nav.jsp" %>
	
	<div class="main-images"><img src="/images/img_sub.png"></div>	
	
	<div class="history">
		홈 &gt; 커뮤니티
	</div>
	
	<div class="main-contents">

		<div class="search">
			<input type="text" name="keyword" class="itext" style="width:200px;" placeholder="제목, 내용 검색">
			<button type="button" class="btn"><span>검색</span></button>
		</div>

		<table class="list">
		<colgroup>
			<col width="10%">
			<col width="*">
			<col width="15%">
			<col width="15%">
			<col width="15%">
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td colspan="5">
					<span class="empty-data">게시글이 없습니다.</span>
				</td>
			</tr>
		</tbody>
		</table>

		<%@ include file="/WEB-INF/views/common/paging.jsp" %>

	</div>
	
</article>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>
