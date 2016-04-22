<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/head.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<style>
</style>

<script>
	$(document).ready(function() {
	});
	
	function onPayMethod(frm) {
		if (frm.pay[0].checked || frm.pay[1].checked) {
			$(".bank-book").css("display","none");
		} else {
			$(".bank-book").css("display","table");
		}
	}
	
	function sameAdd(frm,obj){ //체크

		var userId = $("#userId").val();
		var userName = $("#userName").val();
		var phone = $("#phone").val();
		var mobile = $("#mobile").val();
		var postcode = $("#postcode").val();
		var address1 = $("#address1").val();
		var address2 = $("#address2").val();
		
		if(mobile!=""){//핸드폰
			var arr = new Array();
			arr=mobile.split("-");
			$("#m1").val(arr[0]).attr("selected","selected");
			$("#m2").val(arr[1]);
			$("#m3").val(arr[2]);
		}
		if(phone!=""){//전화번호
			var arr = new Array();
			arr=phone.split("-");
			$("#p1").val(arr[0]).attr("selected","selected");
			$("#p2").val(arr[1]);
			$("#p3").val(arr[2]);
		}
		if(postcode!=""){
			var arr = new Array();
			arr=postcode.split("-");
			$("#post1").val(arr[0]);
			$("#post2").val(arr[1]);
		}
		$("#a1").val(address1);
		$("#a2").val(address2);
		$("#un").val(userName);
	}
	function onAgree(frm){
		var userId = $("#userId").val();
		var userName = $("#un").val();
		var phone1 = $("#p1").val();
		var phone2 = $("#p2").val();
		var phone3 = $("#p3").val();
		var mobile1 = $("#m1").val();
		var mobile2 = $("#m2").val();
		var mobile3 = $("#m3").val();
		var post1 = $("#post1").val();
		var post2 = $("#post2").val();
		var address1 = $("#a1").val();
		var address2 = $("#a2").val();
		var comment = $("#comment").val();
		var pay = $("input:radio[name=pay]:checked").val();
		var feeSum =$("#feeSum").val();
		var discountSum = $("#discountSum").val();
		var deliveryFee = $("#deliveryFee").val();
		var totalFee = $("#totalFee").val();
		var orderTitle = $("#orderTitle").val();
		var bankName = $("#bankName").val();
		var accountName = $("#accountName").val();
		var opSeq = $("#opSeq").val();
		
		if(userName==""){
			alert("이름을 입력해주세요");
			return false;
		}
		if(mobile1==""||mobile2==""||mobile3==""){
			alert("휴대폰 번호를 입력해주세요");
			return false;
		}
		if(phone1!=""||phone2!=""||phone3!=""){
			if(phone1==""||phone2==""||phone3==""){
				alert("전화번호를 입력해 주세요.");
				return false;
			}
		}
		if(post1==""||post2==""){
			alert("우편번호를 입력해 주세요.");
			return false;
		}
		if(address1==""||address2==""){
			alert("주소를 입력해 주세요.");
			return false;
		}
		if(pay==3){
			if(bankName==""||accountName==""){
				alert("입금정보를 입력해주세요.");
				return false;
			}
		}
		if(pay==""){
			alert("결제수단을 선택해주세요");
			return false;
		}
		else{
			var mobile = mobile1+-+mobile2+-+mobile3;
			var phone = phone1+-+phone2+-+phone3;
			var postcode =post1+-+post2;
			var param={
					userId : userId,
					buyerName : userName,
					phone : phone,
					mobile : mobile,
					postcode : postcode,
					address1 : address1,
					address2 : address2,
					comment : comment,
					paymentType : pay,
					priceSum : feeSum,
					discountSum : discountSum,
					deliveryFee : deliveryFee,
					totalFee :totalFee,
					bankName : bankName,
					accountName : accountName,
					orderTitle : orderTitle,
					opSeq : opSeq
			}
			 $.ajax({
				dataType : "json",
				data : param,
				url : "/order_do.go",
				type:"POST",
				success : function(json){
					if(json.result){
						alert(json.msg);
						if (json.order.paymentType == "1" || json.order.paymentType == "2") {
							var mobile = json.order.mobile;
							mobile = mobile.split("-").join("");
							var param = "";
							param += "?payMethod="+ json.order.paymentType;
							param += "&sndOrdernumber="+ json.orderSeq;
							param += "&sndGoodname="+ json.order.orderTitle;
							param += "&sndAmount="+ json.order.totalFee;
							param += "&sndOrdername="+ json.order.buyerName;
							param += "&sndEmail="+ json.user.userId;
							param += "&sndMobile="+ mobile;
							document.location.href="/order_kspay.go"+param;
						} else {
							document.location.href="/home.go";
						}
					}
					else{
						alert(json.msg);
					}
					
				}
			}); 
			return false;
			
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
		홈 &gt; 주문하기
	</div>

	<div class="main-contents">
	
		<form method="post" name="joinForm">
		<input type="hidden" name="validId" value="0">
		<input type="hidden" id ="opSeq" value="${seq }">
		<input type="hidden" name="userId" id="userId"value="${user.userId }">
		<input type="hidden" name="userName" id="userName" value="${user.userName }">
		<input type="hidden" name="postcode" id="postcode" value="${user.postcode }">
		<input type="hidden" name="address1" id="address1" value="${user.address1 }">
		<input type="hidden" name="address2" id="address2" value="${user.address2 }">
		<input type="hidden" name="phone" id="phone" value="${user.phone }">
		<input type="hidden" name="mobile" id="mobile" value="${user.mobile }">
	
		
			<div class="box-720">
				<table class="list">
				<colgroup>
					<col width="*">
					<col width="10%">
					<col width="15%">
					<col width="15%">
					<col width="15%">
				</colgroup>
				<thead>
					<tr>
						<th>상품명</th>
						<th>단가</th>
						<th>수량</th>
						<th>할인금액</th>
						<th>구매금액</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="feeSum" value="0"></c:set>
					<c:set var="discountSum" value="0"></c:set>
					<c:choose>
						<c:when test="${list.size()>0}">
							<c:forEach var="it" items="${list}">
								<tr>
									<td class="left">
										${it.productName}
										<input type="hidden" name="orderTitle" id="orderTitle" value="${list.get(0).productName} 외 ${list.size()-1}건">
										<c:if test="${it.productOption != ''}">(${it.productOption})</c:if> 
									</td>
									<td>
										<fmt:formatNumber>${it.unitPrice}</fmt:formatNumber>원
									</td>
									<td>
										<fmt:formatNumber>${it.buyCount}</fmt:formatNumber>
									</td>
									<td>
										0원
									</td>
									<td>
										<fmt:formatNumber>${it.payMoney}</fmt:formatNumber>원
									</td>
								</tr>
								<c:set var="feeSum" value="${feeSum + it.payMoney}"></c:set>
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
					<c:set var="deliveryFee" value="${feeSum>30000 ? 0 : 3000}"></c:set>
					<c:set var="totalFee" value="${feeSum + deliveryFee}"></c:set>

				</tbody>
				</table>
				
				<div class="box-basket">
					<div class="left" style="width:390px;">
						총 주문 합계 금액
					</div>
					<div class="right">
						<dl>
							<dt>상품 합계금액 : </dt>
							<dd>
								<span id="sum-fee"><fmt:formatNumber>${feeSum}</fmt:formatNumber></span>원
								<input type="hidden" name="feeSum" id="feeSum" value="${feeSum}">
							</dd>
							<dt>할인 합계금액 : </dt>
							<dd>
								<span id="discount-fee"><fmt:formatNumber>${discountSum}</fmt:formatNumber></span>원
								<input type="hidden" name="discountSum" id="discountSum" value="${discountSum}">
							</dd>
							<dt>배송비 : </dt>
							<dd>
								<span id="delivery-fee"><fmt:formatNumber>${deliveryFee}</fmt:formatNumber></span>원
								<input type="hidden" name="deliveryFee" id="deliveryFee" value="${deliveryFee}">
							</dd>
							<dt>총 주문합계금액 : </dt>
							<dd>
								<span id="total-fee"><fmt:formatNumber>${totalFee}</fmt:formatNumber></span>원
								<input type="hidden" name="totalFee" id="totalFee" value="${totalFee}">
							</dd>
						</dl>
					</div>
				</div>
			
			</div>

			<div class="box-720" style="margin-top:30px;">
				<h2>주문자 정보</h2>
			</div>
			<div class="join2-box">

				<table class="edit">
				<colgroup>
					<col width="80">
					<col width="*">
				</colgroup>
				<tr>
					<th>성명</th>
					<td>
						${user.userName }
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>
						(${user.postcode })${user.address1} ${user.address2}
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
					 	${user.phone }
					</td>
				</tr>
				<tr>
					<th>휴대폰</th>
					<td>${user.mobile }
						
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>${user.userId }</td>
				</tr>
				</table>
			</div>
				
			<div class="box-720">
				<h2>배송지 정보</h2>
				<label><input type="checkbox" name="sameAddress" onclick="sameAdd(this.form,this);">배송지 정보가 주문자 정보와 동일합니다.</label>
			</div>

			<div class="join2-box">
				<table class="edit">
				<colgroup>
					<col width="80">
					<col width="*">
				</colgroup>
				<tr>
					<th>* 성명</th>
					<td>
						<input type="text" name="userName" id="un" class="itext userName" value="">
					</td>
				</tr>
				<tr>
					<th>* 주소</th>
					<td>
						<input type="text" name="post1" id="post1" class="itext" style="width:50px;margin-bottom:5px;"> -
						<input type="text" name="post2" id="post2" class="itext" style="width:50px;margin-bottom:5px;"><br>
						<input type="text" name="address1" id="a1" class="itext" style="width:560px;margin-bottom:5px;"><br>
						<input type="text" name="address2" id="a2" class="itext" style="width:560px;">
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<select name="phone1" id="p1">
							<option value="">지역번호</option>
							<option value="02" >02</option>
							<option value="031">031</option>
							<option value="032">032</option>
							<option value="033">033</option>
							<option value="041">041</option>
							<option value="042">042</option>
							<option value="043">043</option>
							<option value="051">051</option>
							<option value="052">052</option>
							<option value="053">053</option>
							<option value="054">054</option>
							<option value="055">055</option>
							<option value="061">061</option>
							<option value="062">062</option>
							<option value="063">063</option>
							<option value="064">064</option>
							<option value="070">070</option>
							<option value="080">080</option>
						</select> -
						<input type="text" name="phone2" id="p2" class="itext" style="width:50px;"> -
						<input type="text" name="phone3" id="p3" class="itext" style="width:50px;">
					</td>
				</tr>
				<tr>
					<th>* 휴대폰</th>
					<td>
						<select name="mobile1" id="m1">
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="018">018</option>
							<option value="019">019</option>
						</select> -
						<input type="text" name="mobile2" id="m2" class="itext" style="width:50px;"> -
						<input type="text" name="mobile3" id="m3" class="itext" style="width:50px;">
					</td>
				</tr>
				<tr>
					<th>배송메시지</th>
					<td>
						<textarea name="comment" id="comment" style="width:100%; height:60px;" ></textarea>
					</td>
				</tr>
				</table>			

			</div>

			<div class="box-720">
				<h2>결제 정보</h2>
			</div>
			<div class="join2-box">

				<table class="edit" style="width:100%;">
				<colgroup>
					<col width="80">
					<col width="*">
				</colgroup>
				<tr>
					<th>결제금액</th>
					<td>
						<span style="font-size:18px; color:#f40; font-weight:bold;"><fmt:formatNumber>${totalFee}</fmt:formatNumber></span>원
					</td>
				</tr>
				<tr>
					<th style="vertical-align:top; padding-top:3px;">결제수단</th>
					<td>
						<label><input type="radio" name="pay" value="1" onclick="onPayMethod(this.form);">신용카드</label>
						<label><input type="radio" name="pay" value="2" onclick="onPayMethod(this.form);">휴대폰소액결제</label>
						<label><input type="radio" name="pay" value="3" onclick="onPayMethod(this.form);">무통장입금</label>
						<dl class="bank-book">
							<dt>은행명 : </dt>		<dd><input type="text" name="bankName" id="bankName"  class="itext" style="width:200px"></dd>
							<dt>입금자명 : </dt>	<dd><input type="text" name="accountName" id="accountName class="itext" style="width:200px"></dd>
						</dl>
					</td>
				</tr>
				</table>
				
			</div>
	
		</form>

		<div class="tools">
			<button type="button" onclick="onAgree(this.form);" class="btn btn-order-all"><span>주문하기</span></button>
			<button type="button" onclick="history.back(-1);" class="btn"><span>취소</span></button>
		</div>

	</div>
	
</article>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>
