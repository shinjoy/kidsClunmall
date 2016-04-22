<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/head.jsp" %>

<style>
	.box-login { border-radius:12px; border:1px solid #ddd; background-color:#fafafa; width:500px; margin:30px auto; }
	.box-login .login-form { display:table; width:320px; margin:20px auto 5px; }
	.box-login .login-form .login-input { display:table-cell; vertical-align:top; }
	.box-login .login-form .login-input dt { clear:left; float:left; width:60px; padding-top:4px; margin-bottom:5px; }
	.box-login .login-form .login-input dd { float:left; margin-bottom:5px; }
	.box-login .login-form .login-btn { display:table-cell; vertical-align:top; }
	.box-login .login-form .login-btn button { border-radius:3px; background-color:#f40; padding:22px; }
	.box-login .login-form .login-btn button span { color:#fff; }
	.box-login .login-tools { text-align:center; margin-bottom:20px; }
</style>

<script>
	$(document).ready(function() {
		$("#tm2").addClass("active");
	});
	function gologin(frm){
		
		var param ={
				userId : frm.loginId.value,
				pass : frm.loginPw.value
		}
		$.ajax({
			data : param,
			dataType : "json",
			url : "/login_do.go",
			type : "POST",
			success : function(json){
				
				 if(json.result){
					
					document.location.href="/home.go";
				}else{
					alert(json.msg);
				} 
				
			}
		});
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
		홈 &gt; 로그인
	</div>

	<div class="main-contents">

		<form method="post" name="loginForm">
		<div class="box-login">
			<div class="login-form">
				<div class="login-input">
					<dl>
						<dt>로그인</dt>		<dd><input type="text" name="loginId" class="itext" style="width:160px;"></dd>
						<dt>비밀번호</dt>	<dd><input type="password" name="loginPw" class="itext" style="width:160px;"></dd>
					</dl>
				</div>
				<div class="login-btn">
					<button type="button" onclick="gologin(this.form);"><span>로그인</span></button>
				</div>
			</div>
			<div class="login-tools">
				<a href="/join1.go">회원가입</a> | 
				<a href="javascript:alert('준비중입니다.');" >아이디/비밀번호 찾기</a>
			</div>
		</div>
		</form>

	</div>
	
</article>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>
