<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/head.jsp" %>

<style>
</style>

<script>
	$(document).ready(function() {
		$("#tm4").addClass("active");
	});
	function aaa(price){//div 변경
		if(price!='0'){
			$('#sum-fee').append($.number(price));
		 	$('#delivery-fee').append((price > 30000 ? 0 : $.number(3000) ));
		 	var sum = parseInt(price);
		 	$('#discount-fee').append('0');
			$('#total-fee').append((price > 30000 ? $.number(price) : $.number(sum+3000) ));  
		}
		
	}

	function basketDelete(frm){
		
		var seqValue = new Array();
		for (var i=0;i<$(".opSeq").length;i++) {
			if (($('.opSeq').eq(i).is(":checked"))) {
				 seqValue.push($(".opSeq").eq(i).val());
			} 
		}
		var seq = seqValue.toString();
		if (seq == "") {
			alert("선택한 상품이 없습니다.");
			return false;
		} else {
			document.location.href = "/delete.go?seq="+seq;
		}
		
	}
	function basketClear(){ //장바구니 비우기
		
		var seqValue = new Array();
		for (var i=0;i<$(".opSeq").length;i++) {
				seqValue.push($(".opSeq").eq(i).val());
		}
		var seq = seqValue.toString();
		document.location.href = "/delete.go?seq="+seq;
		
		
	}
	function orderAll(frm){ //전체상품주문
		
		var seqValue = new Array();
		for (var i=0;i<$(".opSeq").length;i++) {
				seqValue.push($(".opSeq").eq(i).val());
		}
		var seq = seqValue.toString();
		document.location.href = "/order.go?seq="+seq;
		
		
	}
	function seeView(){ //체크
		var feeSum = 0;
		var discountSum = 0;
		var deliveryFee = 0;
		var totalFee = 0;
		
		for (var i=0;i<$(".opSeq").length;i++) {
			 if (($('.opSeq').eq(i).is(":checked"))) {
				 feeSum += parseInt($(".unitPrice").eq(i).val()) * parseInt($(".buyCount").eq(i).val());
				 discountSum += parseInt($(".discount").eq(i).val());
			} 
		}
		var fee = feeSum - discountSum;
		if (fee > 30000 || fee == 0) {
			deliveryFee = 0;
		} else {
			deliveryFee = 3000;
		}
		totalFee = fee + deliveryFee;
		
		$('#sum-fee').html($.number(feeSum));
		$('#discount-fee').html($.number(discountSum));
		$('#delivery-fee').html($.number(deliveryFee));
		$('#total-fee').html($.number(totalFee));
		
		var frm = basketForm;
		frm.priceSum.value = feeSum;
		frm.discountSum.value = discountSum;
		frm.deliveryFee.value = deliveryFee;
		frm.totalFee.value = totalFee;
		
	}
	function changeCount(seq,frm,obj){ //수정
		var count = $(obj).parent().find(".cnt").val();
		var param ={
			seq : seq,
			count : count
		}
		$.ajax({
			dataType : "json",
			data : param,
			url : "/basket_count_update.go",
			type:"POST",
			success : function(json){
				if (json.result) {
					$(obj).parent().parent().find(".pm").html($.number(json.payMoney));
					seeView();
				} else {
					alert(json.message);
				}
			}
		});
	}
	function order() {
		var seqValue = new Array();
		for (var i=0;i<$(".opSeq").length;i++) {
			if (($('.opSeq').eq(i).is(":checked"))) {
				 seqValue.push($(".opSeq").eq(i).val());
			} 
		}
		var seq = seqValue.toString();
		if (seq == "") {
			alert("선택한 상품이 없습니다.");
			return false;
		} else {
			document.location.href = "/order.go?seq="+seq;
		}
	}
</script>

</head>

<body>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<article>

	<%@ include file="/WEB-INF/views/common/nav.jsp" %>
		
	<div class="main-images"><img src="/images/img_sub.png"></div>	
	
	<div class="history">
		홈 &gt; 장바구니
	</div>

	<div class="main-contents">
	
		<form method="post" name="basketForm">

			<table class="list">
			<colgroup>
				<col width="10%">
				<col width="*">
				<col width="10%">
				<col width="15%">
				<col width="15%">
				<col width="15%">
			</colgroup>
			<thead>
				<tr>
					<th>선택</th>
					<th>상품명</th>
					<th>단가</th>
					<th>수량</th>
					<th>할인금액</th>
					<th>구매금액</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${data.size()>0}" >
						<c:forEach items="${data}" var="it">
							<input type="hidden" name="productName" class="productName" value="${it.productName }">
							<input type="hidden" name="unitPrice" class="unitPrice" value="${it.unitPrice }">
							<input type="hidden" name="discount" class="discount" value="0">
							<input type="hidden" name="payMoney" class="payMoney" value="${it.payMoney }">
							<tr>
								<td><input type="checkbox" class="opSeq" value="${it.opSeq }" onchange="seeView();"></td>
								<td class="left">
									${it.productName }
								</td>
								<td>
									<fmt:formatNumber>${it.unitPrice }</fmt:formatNumber>
								</td>
								<td>
									<input type="text" name="buyCount" value="${it.buyCount }" class="itext cnt buyCount" style="width:30px;">
									<button type="button" onclick="changeCount(${it.opSeq},this.form, this)" class="btn btn-count-edit">수정</button>
								</td>
								<td>
									0
								</td>
								<td class="pm">
									<fmt:formatNumber>${it.payMoney }</fmt:formatNumber>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="6">
								<span class="empty-data">게시글이 없습니다.</span>
							</td>
						</tr>
					</c:otherwise>
				</c:choose>

			</tbody>
			</table>
			
			<div class="box-basket">
				<div class="left">
					총 주문 합계 금액
				</div>
				<div class="right">
					<dl>
						<dt>상품 합계금액 : </dt>
						<dd>
							<span id="sum-fee">0</span>원
							<input type="hidden" name="priceSum" value="">
						</dd>
						<dt>할인 합계금액 : </dt>
						<dd>
							<span id="discount-fee">0</span>원
							<input type="hidden" name="discountSum" value="">
						</dd>
						<dt>배송비 : </dt>
						<dd>
							<span id="delivery-fee">0</span>원
							<input type="hidden" name="deliveryFee" value="">
						</dd>
						<dt>총 주문합계금액 : </dt>
						<dd>
							<span id="total-fee">0</span>원
							<input type="hidden" name="totalFee" value="">
						</dd>
					</dl>
				</div>
			</div>
			
			<div class="tools">
				<button type="button" class="btn btn-basket-delete" onclick="basketDelete(this.form);">선택상품삭제</button>
				<button type="button" class="btn btn-basket-clear" onclick="basketClear();">장바구니 비우기</button>
				<button type="button" class="btn btn-order-all" onclick="order(this.form);">선택상품 주문</button>
				<button type="button" class="btn btn-order-all" onclick="orderAll(this.form);">전체상품 주문</button>
				<button type="button" class="btn btn-order-continue" onclick="history.back(-1);">쇼핑 계속하기</button>
			</div>

		</form>		

	</div>
	
</article>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>
