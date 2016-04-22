<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/head.jsp" %>

<style>
</style>

<script>
	$(document).ready(function() {
		$("#nv5").addClass("active");
	});
</script>

</head>

<body>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<article>

	<%@ include file="/WEB-INF/views/common/nav.jsp" %>
	
	<div class="main-images"><img src="/images/img_sub.png"></div>	
	
	<div class="history">
		홈 &gt; 공지사항
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
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><a href="/notice_view.go?seq=3">3</a></td>
				<td class="left"><a href="/notice_view.go?seq=3">쿠루쿠루 어린이 안전벨트 입점</a></td>
				<td>관리자</td>
				<td class="date">2015-10-10</td>
			</tr>
			<tr>
				<td><a href="/notice_view.go?seq=2">2</a></td>
				<td class="left"><a href="/notice_view.go?seq=2">몽구 EZ 카시트 입점</a></td>
				<td>관리자</td>
				<td class="date">2015-10-10</td>
			</tr>
			<tr>
				<td><a href="/notice_view.go?seq=1">1</a></td>
				<td class="left"><a href="/notice_view.go?seq=1">안녕하세요. KIDS Club mall 입니다.</a></td>
				<td>관리자</td>
				<td class="date">2015-10-10</td>
			</tr>
		</tbody>
		</table>

		<%@ include file="/WEB-INF/views/common/paging.jsp" %>

	</div>
	
</article>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>
