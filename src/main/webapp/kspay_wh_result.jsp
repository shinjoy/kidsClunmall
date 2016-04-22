<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<!DOCTYPE HTML>

<% request.setCharacterEncoding("utf-8"); %>
<%
 response.setHeader("Cache-Control","no-cache"); 
 response.setHeader("Pragma","no-cache"); 
 response.setDateHeader("Expires",0); 
%>



<html lang="ko">
<head>
	<title>KIDS CLUB Mall</title>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta http-equiv="Content-Script-Type" content="text/javascript" />
	<meta http-equiv="Content-Style-Type" content="text/css" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<!-- 
		안드로이드 브라우저는 width를 지정하지 않는 이상, 980px의 width값을 가진다. 
		이보다 더 작은 해상도를 지원할 경우, viewport 메타 태그를 추가한다.
		데스트탑 브라우저에서는 viewport 메타 태그가 무시된다.
	 -->
	<!--<link rel="stylesheet" type="text/css" href="/css/nf_admin.css" media="screen and (min-width: 801px)" />  데스크탑을 위한 css. 480px 초과 해상도의 경우로 설정 -->
	<!-- <link rel="stylesheet" type="text/css" href="/css/devicemanager-m.css" media="only screen and (max-width: 800px)" /> 안드로이드를 위한 css. 480px 이하 해상도의 경우로 설정 -->
	<!-- IE를 위한 별도 처리 css -->
	<!--[if IE]>
	    <link rel="stylesheet" type="text/css" href="/css/devicemanager.css" media="all" />
	<![endif]-->

	<link rel="stylesheet" type="text/css" href="/css/nf.admin.css" />
	<link rel="stylesheet" type="text/css" href="/css/nf.admin.kidsclubmall.css" />
	<link rel="stylesheet" type="text/css" href="/css/nf.admin.table.css" />
	<link rel="stylesheet" type="text/css" href="/lib/bestist/bestist.pop.css" />
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	
	<script type="text/javascript" src="/lib/jquery/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="/lib/jquery/jquery.number.js"></script>
	<script type="text/javascript" src="/lib/bestist/bestist.util.form.js"></script>
	<script type="text/javascript" src="/lib/bestist/bestist.pop.js"></script>

<%
	String rcid		= request.getParameter("reWHCid"		);
	String rctype	= request.getParameter("reWHCtype"		);
	String rhash	= request.getParameter("reWHHash"			);

	String	authyn =  "";
	String	trno   =  "";
	String	trddt  =  "";
	String	trdtm  =  "";
	String	amt    =  "";
	String	authno =  "";
	String	msg1   =  "";
	String	msg2   =  "";
	String	ordno  =  "";
	String	isscd  =  "";
	String	aqucd  =  "";
	String	temp_v =  "";
	String	result =  "";
	String	halbu  =  "";
	String	cbtrno =  "";
	String	cbauthno =  "";
	String	resultcd =  "";

	//업체에서 추가하신 인자값을 받는 부분입니다
	String	a =  request.getParameter("a"); 
	String	b =  request.getParameter("b"); 
	String	c =  request.getParameter("c"); 
	String	d =  request.getParameter("d");

	ksnet.kspay.KSPayWebHostBean ipg = new ksnet.kspay.KSPayWebHostBean(rcid);
	if (ipg.kspay_send_msg("1"))		//KSNET 결제결과 중 아래에 나타나지 않은 항목이 필요한 경우 Null 대신 필요한 항목명을 설정할 수 있습니다.
	{
		authyn	 = ipg.kspay_get_value("authyn");
		trno	 = ipg.kspay_get_value("trno"  );
		trddt	 = ipg.kspay_get_value("trddt" );
		trdtm	 = ipg.kspay_get_value("trdtm" );
		amt		 = ipg.kspay_get_value("amt"   );
		authno	 = ipg.kspay_get_value("authno");
		msg1	 = ipg.kspay_get_value("msg1"  );
		msg2	 = ipg.kspay_get_value("msg2"  );
		ordno	 = ipg.kspay_get_value("ordno" );
		isscd	 = ipg.kspay_get_value("isscd" );
		aqucd	 = ipg.kspay_get_value("aqucd" );
		temp_v	 = "";
		result	 = ipg.kspay_get_value("result");
		halbu	 = ipg.kspay_get_value("halbu");
		cbtrno	 = ipg.kspay_get_value("cbtrno");
		cbauthno	 = ipg.kspay_get_value("cbauthno");
		
		if (null != authyn && 1 == authyn.length())
		{
			if (authyn.equals("O"))
			{
				// 승인성공
				resultcd = "0000";
				
				// DB연결
        		String url = "jdbc:jtds:sqlserver://218.234.17.164:1433;tds=8.0;lastupdatecount=true;DatabaseName=KIDSCLUBMALL";
	        	String id = "promise";                                                    // 사용자 계정
	        	String pw = "facepro$$$";                                                // 사용자 계정의 패스워드

	        	Connection conn = null;                                        // null로 초기화 한다.
	        	PreparedStatement pstmt = null;

	        	Class.forName("net.sourceforge.jtds.jdbc.Driver");		// 데이터베이스와 연동하기 위해 DriverManager에 등록한다.
	        	conn=DriverManager.getConnection(url,id,pw);            // DriverManager 객체로부터 Connection 객체를 얻어온다.
	        	
	        	String sql = "UPDATE T_NF_ORDER SET status = '결재완료', pay_date = getDate() WHERE order_seq = ? ";        // sql 쿼리
	        	pstmt = conn.prepareStatement(sql);            // prepareStatement에서 해당 sql을 미리 컴파일한다.
	        	pstmt.setInt(1,Integer.parseInt(ordno)); //포인트	Integer.parseInt(LGD_PRODUCTCODE.split("[|]")[2])
	        	pstmt.executeUpdate();                                        // 쿼리를 실행한다.

			}else
			{
				resultcd = authno.trim();
			}

			//ipg.kspay_send_msg("3");		// 정상처리가 완료되었을 경우 호출합니다.(이 과정이 없으면 일시적으로 kspay_send_msg("1")을 호출하여 거래내역 조회가 가능합니다.)
		}
	}
%>
<link href="http://kspay.ksnet.to/store/KSPayFlashV1.3/mall/css/pgstyle.css" rel="stylesheet" type="text/css" charset="euc-kr">
</head>
<script language="javascript">
// 현금영수증 출력 스크립트
function CashreceiptView(tr_no)
{
    receiptWin = "http://pgims.ksnet.co.kr/pg_infoc/src/bill/ps2.jsp?s_pg_deal_numb="+tr_no;
    window.open(receiptWin , "" , "scrollbars=no,width=434,height=580");
}
// 신용카드 영수증 출력 스크립트
function receiptView(tr_no)
{
	receiptWin = "http://pgims.ksnet.co.kr/pg_infoc/src/bill/credit_view.jsp?tr_no="+tr_no;
    window.open(receiptWin , "" , "scrollbars=no,width=434,height=700");
}
-->
</script>

<body>

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
				<li style="padding-right:20px;">${USER_NAME}님 반갑습니다.</li>
				<li><a href=/logout.go><span id=tm2>로그아웃</span></a></li>
				<li><a href="/basket.go"><span id="tm4" class="line">장바구니</span></a></li>
			</ul>
		</div>
	</article>
</header>

<article>


	<div class="logo"><a href="/"><img src="/images/logo_top.png" alt="KIDS CLUB mall"></a></div>
	<nav>
		<ul>
			<li><a href="/intro.go"><span id="nv1">회사소개</span></a>
			<li><a href="/product_list.go"><span id="nv2">제품소개</span></a>
			<li><a href="/my_order.go"><span id="nv3">나의상품</span></a>
			<li><a href="/bbs_list.go"><span id="nv4">커뮤니티</span></a>
			<li><a href="/notice_list.go"><span id="nv5">공지사항</span></a>
		</ul>
	</nav>
		
	<div class="main-images"><img src="/images/img_sub.png"></div>	
	
	<div class="history">
		홈 &gt; 주문하기
	</div>

	<div class="main-contents">
	
		<table width="560" border="0" cellspacing="0" cellpadding="0" style="margin:0 auto 50px;">
		  <tr>
		    <td height="50" align="right" background="http://kspay.ksnet.to/store/KSPayFlashV1.3/mall/imgs/bg_top.gif" class="txt_pd1"></td>
		  </tr>
		  <tr>
		    <td height="530" valign="top" background="http://kspay.ksnet.to/store/KSPayFlashV1.3/mall/imgs/bg_man.gif">	
			<table width="560" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td width="25">&nbsp;</td>
		        <td width="505" align="center">
				<table width="500" border="0" align="center" cellpadding="0" cellspacing="0">
		      <tr>
		        <td height="40" style="padding:0px 0px 0px 15px; "><img src="http://kspay.ksnet.to/store/KSPayFlashV1.3/mall/imgs/ico_tit5.gif" width="30" height="30" align="absmiddle"> <strong>결과항목</strong></td>
		      </tr>
		      <tr>
		        <td align="center"><table width="400" border="0" cellspacing="0" cellpadding="0">
		          <tr bgcolor="#FFFFFF">
		            <td width="120"><img src="http://kspay.ksnet.to/store/KSPayFlashV1.3/mall/imgs/ico_right.gif" width="11" height="11" align="absmiddle"> 결제방법</td>
		            <td width="280">
		<%
								if (null == result || 4 != result.length())
								{
									out.println("(???)");
								}else
								{
									switch (result.charAt(0))
									{
										case '1' : out.println("신용카드"); break;
										case 'I' : out.println("신용카드"); break;
										case '2' : out.println("실시간계좌이체"); break;
										case '6' : out.println("가상계좌발급"); break; 
										case 'M' : out.println("휴대폰결제"); break; 
										case 'G' : out.println("상품권"); break; 
										default  :  out.println("(????)"); break; 
									}
								}
		%>
		            </td>
		          </tr>
		          <tr bgcolor="#E3E3E3"> <td height="1" colspan="2"></td> </tr>
		
		          <tr bgcolor="#FFFFFF">
		            <td width="120"><img src="http://kspay.ksnet.to/store/KSPayFlashV1.3/mall/imgs/ico_right.gif" width="11" height="11" align="absmiddle"> 성공여부</td>
		            <td width="280"><%=authyn%>(<%= ((authyn != null && authyn.equals("O")) ? "승인성공" : "승인거절") %>)</td>
		          </tr>
		          <tr bgcolor="#E3E3E3"> <td height="1" colspan="2"></td> </tr>
		          <tr bgcolor="#FFFFFF">
		            <td width="120"><img src="http://kspay.ksnet.to/store/KSPayFlashV1.3/mall/imgs/ico_right.gif" width="11" height="11" align="absmiddle"> 응답코드</td>
		            <td width="280"><%=resultcd%></td>
		          </tr>
		          <tr bgcolor="#E3E3E3"> <td height="1" colspan="2"></td> </tr>
		          <tr bgcolor="#FFFFFF">
		            <td width="120"><img src="http://kspay.ksnet.to/store/KSPayFlashV1.3/mall/imgs/ico_right.gif" width="11" height="11" align="absmiddle"> 주문번호</td>
		            <td width="280"><%=ordno%></td>
		          </tr>
		          <tr bgcolor="#E3E3E3"> <td height="1" colspan="2"></td> </tr>
		          <tr bgcolor="#FFFFFF">
		            <td width="120"><img src="http://kspay.ksnet.to/store/KSPayFlashV1.3/mall/imgs/ico_right.gif" width="11" height="11" align="absmiddle"> 금액</td>
		            <td width="280"><%=amt%></td>
		          </tr>
		          <tr bgcolor="#E3E3E3"> <td height="1" colspan="2"></td> </tr>
		          <tr bgcolor="#FFFFFF">
		            <td width="120"><img src="http://kspay.ksnet.to/store/KSPayFlashV1.3/mall/imgs/ico_right.gif" width="11" height="11" align="absmiddle"> 거래번호</td>
		            <td width="280"><%=trno%></td>
		          </tr>
		          <tr bgcolor="#E3E3E3"> <td height="1" colspan="2"></td> </tr>
		          <tr bgcolor="#FFFFFF">
		            <td width="120"><img src="http://kspay.ksnet.to/store/KSPayFlashV1.3/mall/imgs/ico_right.gif" width="11" height="11" align="absmiddle"> 거래일자</td>
		            <td width="280"><%=trddt%></td>
		          </tr>
		          <tr bgcolor="#E3E3E3"> <td height="1" colspan="2"></td> </tr>
		          <tr bgcolor="#FFFFFF">
		            <td width="120"><img src="http://kspay.ksnet.to/store/KSPayFlashV1.3/mall/imgs/ico_right.gif" width="11" height="11" align="absmiddle"> 거래시간</td>
		            <td width="280"><%=trdtm%></td>
		          </tr>
		          <tr bgcolor="#E3E3E3"> <td height="1" colspan="2"></td> </tr>
		<% if (authyn != null && authyn.equals("O")) { %>
		          <tr bgcolor="#FFFFFF">
		            <td width="120"><img src="http://kspay.ksnet.to/store/KSPayFlashV1.3/mall/imgs/ico_right.gif" width="11" height="11" align="absmiddle"> 카드사 승인번호/은행 코드번호</td>
		            <td width="280"><%=authno%></td>
		          </tr>
		          <tr bgcolor="#E3E3E3"> <td height="1" colspan="2"></td> </tr>
		<% } %>
		          <tr bgcolor="#FFFFFF">
		            <td width="120"><img src="http://kspay.ksnet.to/store/KSPayFlashV1.3/mall/imgs/ico_right.gif" width="11" height="11" align="absmiddle"> 발급사코드/가상계좌번호/계좌이체번호</td>
		            <td width="280"><%=isscd%></td>
		          </tr>
		          <tr bgcolor="#E3E3E3"> <td height="1" colspan="2"></td> </tr>
		          <tr bgcolor="#FFFFFF">
		            <td width="120"><img src="http://kspay.ksnet.to/store/KSPayFlashV1.3/mall/imgs/ico_right.gif" width="11" height="11" align="absmiddle"> 매입사코드</td>
		            <td width="280"><%=aqucd%></td>
		          </tr>
		          <tr bgcolor="#E3E3E3"> <td height="1" colspan="2"></td> </tr>
		          <tr bgcolor="#FFFFFF">
		            <td width="120"><img src="http://kspay.ksnet.to/store/KSPayFlashV1.3/mall/imgs/ico_right.gif" width="11" height="11" align="absmiddle"> 메시지1</td>
		            <td width="280"><%=msg1%></td>
		          </tr>
		          <tr bgcolor="#E3E3E3"> <td height="1" colspan="2"></td> </tr>
		          <tr bgcolor="#FFFFFF">
		            <td width="120"><img src="http://kspay.ksnet.to/store/KSPayFlashV1.3/mall/imgs/ico_right.gif" width="11" height="11" align="absmiddle"> 메시지2</td>
		            <td width="280"><%=msg2%></td>
		          </tr>
		          <tr bgcolor="#E3E3E3"> <td height="1" colspan="2"></td> </tr>
		
				  <% if (authyn != null && authyn.equals("O") && trno.charAt(0) == '1') { %> <!-- 정상승인의 경우만 영수증출력: 신용카드의 경우만 제공 -->
		          <tr bgcolor="#FFFFFF">
		            <td width="400" colspan="2" align="center"> <input type="button" value="영수증출력" onClick="javascript:receiptView('<%=trno%>')"> </td>
		          </tr>
		          <tr bgcolor="#E3E3E3"> <td height="1" colspan="2"></td> </tr>
		      <% } %>
		      <% if (authyn != null && authyn.equals("O") && trno.charAt(0) == '2') { %> <!-- 정상승인의 경우만 영수증출력: 계좌이체의 경우만 제공 -->
		          <tr bgcolor="#FFFFFF">
		            <td width="400" colspan="2" align="center"> <input type="button" value="영수증출력" onClick="javascript:CashreceiptView('<%=cbtrno%>')"> </td>
		          </tr>
		          <tr bgcolor="#E3E3E3"> <td height="1" colspan="2"></td> </tr>
		      <% } %>
		        </table></td>
		      </tr>
		    </table>
				</td>
		        <td width="30">&nbsp;</td>
		      </tr>
		    </table>
			</td>
		  </tr>
		  <tr>
		    <td height="37" background="http://kspay.ksnet.to/store/KSPayFlashV1.3/mall/imgs/bg_bot.gif">&nbsp;</td>
		  </tr>
		</table>
	</div>
</article>
</body>
</html>
