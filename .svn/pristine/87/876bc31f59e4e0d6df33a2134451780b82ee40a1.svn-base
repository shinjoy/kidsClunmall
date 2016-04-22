<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/head.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style>
</style>

<script>
	$(document).ready(function() {
		$("#nv3").addClass("active");
	});
	function searchkey(frm){
		frm.action="my_order.go";
		frm.submit();
		return false;
	}
</script>

</head>

<body>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<article>

	<%@ include file="/WEB-INF/views/common/nav.jsp" %>
	
	<div class="main-images"><img src="/images/img_sub.png"></div>	
	
	<div class="history">
		홈 &gt; 나의 상품
	</div>
	
	<div class="main-contents">
		<form method="POST" onsubmit="return searchkey(this); return false;">
			<div class="search">
				<input type="text" name="keyword" class="itext" style="width:200px;" placeholder="상품 검색">
				<button type="submit" class="btn"><span>검색</span></button>
			</div>
		</form>
		<table class="list">
		<colgroup>
			<col width="15%">
			<col width="15%">
			<col width="*">
			<col width="15%">
			<col width="15%">
		</colgroup>
		<thead>
			<tr>
				<th>주문일자</th>
				<th>주문코드</th>
				<th>주문상품</th>
				<th>주문금액</th>
				<th>주문처리상태</th>
			</tr>
		</thead>
				<c:choose>
					<c:when test="${list.size()>0}">
						<c:forEach var="it" items="${list}">
							<tr>
								<td class="left">${fn:substring(it.orderDate,0,16)}</td>
								<td>${it.orderSeq}</td>
								<td class="left"><a href="/orderDetail.go?seq=${it.orderSeq }">${it.orderTitle}</a></td>
								<td class="right"><fmt:formatNumber>${it.paymentFee}</fmt:formatNumber>원</td>
								<td>${it.status }</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5"><span class="empty-data">주문내역이 없습니다.</span>
							</td>
						</tr>
					</c:otherwise>
				</c:choose>

			</table>

		<%@ include file="/WEB-INF/views/common/paging.jsp" %>

	</div>
	
</article>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>
