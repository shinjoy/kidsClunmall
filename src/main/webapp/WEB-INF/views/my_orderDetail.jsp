<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/head.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style>
</style>

<script>
	$(document).ready(function() {
		$("#nv3").addClass("active");
	});
	function rr(){
		alert("준비중입니다");
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

		<table class="list">
		<colgroup>
			<col width="*">
			<col width="15%">
			<col width="15%">
			<col width="15%">
			<col width="15%">
			<col width="15%">
			<col width="15%">
		</colgroup>
		<thead>
			<tr>
				<th>상품명</th>
				<th>주문갯수</th>
				<th>상품가격</th>
				<th>할인금액</th>
				<th>주문금액</th>
			</tr>
		</thead>
				<c:choose>
					<c:when test="${list.size()>0}">
						<c:forEach var="it" items="${list}">
							<tr>
								<td class="left">${it.productName} 
									<c:if test="${it.productOption != ''}">(${it.productOption})</c:if> 
								</td>
								<td class="left">${it.buyCount}</td>
								<td class="left"><fmt:formatNumber>${it.unitPrice}</fmt:formatNumber></td>
								<td class="left"><fmt:formatNumber>${it.discount}</fmt:formatNumber>원</td>
								<td class="left"><fmt:formatNumber>${it.payMoney}</fmt:formatNumber>원</td>
							</tr>
							

						</c:forEach>
							<tr>
								<td align="right" colspan="5">주문 처리 상태 : <c:choose>
										<c:when
											test="${list.get(0).status2 eq '배송중' or list.get(0).status2 eq '배송완료' }">
										${list.get(0).status2 }        <button type="button" class ="btn-orange" onclick="rr();" style="padding-top:5px; background-color: orange">배송조회</button>
										</c:when>
										<c:otherwise>
										${list.get(0).status2 } 
									</c:otherwise>
									</c:choose>
								</td>
							</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5"><span class="empty-data">주문내역이 없습니다.</span>
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table >
			<br><br>
			<table class="list">
				<tr>
					<td><h1>주문정보</h1></td>
				</tr>
				
				<tr>
					<td class="left" colspan="5">주문자명 : ${list.get(0).buyerName }
					
				</tr>
				<tr>
					<td class="left" colspan="5">배송지 :
						(${list.get(0).postcode})${list.get(0).address1}${list.get(0).address2}
					
				</tr>
				<tr>
					<td class="left" colspan="5">전화번호 : ${list.get(0).phone}
				</tr>
				<tr>
					<td class="left" colspan="5">휴대폰 : ${list.get(0).mobile}
				</tr>
				<c:choose>
					<c:when test="${list.get(0).paymentType!=3 }">
						<tr>
							<td class="left" colspan="5">결제수단 : ${list.get(0).payTypeText }
						</tr>
						<tr>
							<td class="left" colspan="5">결제일 : ${list.get(0).payDate}
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td class="left" colspan="5">결제수단 : ${list.get(0).payTypeText }
						</tr>
						<tr>
							<td class="left" colspan="5">입금은행 : ${list.get(0).bankName }
						</tr>
						<tr>
							<td class="left" colspan="5">계좌번호 : ${list.get(0).accountName }
						</tr>
						<c:if test="${list.get(0).status2 != '주문' }">
						<tr>
							<td class="left" colspan="5">결제일 : ${list.get(0).payDate}
						</tr>
						</c:if>
					</c:otherwise>
				</c:choose>
			</table>

			
		<span class="btn-tool" >
			<a href="javascript:history.back(-1);" class="btn-green" style="padding: 4px 15px;">목록으로</a>
		</span>
		<%@ include file="/WEB-INF/views/common/paging.jsp" %>
	</div>
	
</article>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>


</body>
</html>
