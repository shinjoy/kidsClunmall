<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	$(document).ready(function() {
		//manCount.getCount();
	});
</script>
<header class="top-header">
	<article>
		<div class="left"> 
			<ul>
				<li><a href="/"><span class="line" id="tm1">홈</span></a></li>
			</ul>
		</div>
		<div class="right">
			<ul >
				<c:choose>
					<c:when test="${USER_ID==null || USER_ID == ''}">
						<li><a href=/login.go><span id=tm2>로그인</span></a></li>
						<li><a href="/join1.go"><span id="tm3">회원가입</span></a></li>
					</c:when>
					<c:otherwise>
						<li style="padding-right:20px;">${USER_NAME}님 반갑습니다.</li>
						<li><a href=/logout.go><span id=tm2>로그아웃</span></a></li>
					</c:otherwise>
				</c:choose>
				<li><a href="/basket.go"><span id="tm4" class="line">장바구니</span></a></li>
			</ul>
		</div>
	</article>
</header>