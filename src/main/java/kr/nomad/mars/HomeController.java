package kr.nomad.mars;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.nomad.dao.OrderDao;
import kr.nomad.dao.OrderProductDao;
import kr.nomad.dao.UserDao;
import kr.nomad.dto.Order;
import kr.nomad.dto.OrderProduct;
import kr.nomad.dto.User;
import kr.nomad.util.Paging;
import kr.nomad.util.Response;
import net.sf.json.JSONObject;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//@Autowired private UserDao userDao;

	
	// 페이지당 아이템 갯수
	@Value("#{config['page.itemCountPerPage']}")
	int ITEM_COUNT_PER_PAGE;

	// 페이징당 페이지 갯수
	@Value("#{config['page.pageCountPerPaging']}")
	int PAGE_COUNT_PER_PAGING;

	// 파일 루트
	@Value("#{config['file.root']}")
	String FILE_ROOT;

	String FILE_PATH = "";
	String FILE_LOCAL_PATH = "";

	// 파일 최대크기(Mb)
	@Value("#{config['file.maxSize']}")
	int FILE_MAX_SIZE;
	
	@Autowired	UserDao userDao;
	@Autowired OrderProductDao orderproductDao;
	@Autowired OrderDao orderDao;
	
/*	//헤더
	@RequestMapping("/header.go")
	public String adminCountController(
			HttpSession session, Model model, HttpServletResponse res) {
		
		Map<String, Object> map = new HashMap<String, Object>();
	    
	    int loginstatus =0;
	    
	    if(session.getAttribute("USER_ID")!=null){
	    	loginstatus =1;
		}
	   
	    map.put("loginstatus", loginstatus);
	   
	 
	    JSONObject jsonObject = JSONObject.fromObject(map);
	    Response.responseWrite(res, jsonObject);
	   	    
	    return null;

	}
	*/
	
	/**
	 * 메인 
	 */
	@RequestMapping("/home.go")
	public String homeController(
	        @RequestParam(value="msg", required=false, defaultValue="") String msg, 
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
		
	    model.addAttribute("msg", msg);
	    return "/home";
	}
	
	/**
	 * 로그인 
	 */
	@RequestMapping("/login.go")
	public String loginController(
	        @RequestParam(value="msg", required=false, defaultValue="") String msg, 
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
	 
	    model.addAttribute("msg", msg);
	    return "/login";
	}

	/**
	 * 로그인  하기
	 */
	@RequestMapping("/login_do.go")
	public String loginDoController(
	        @RequestParam(value="userId", required=false, defaultValue="") String userId, 
	        @RequestParam(value="pass", required=false, defaultValue="") String pass,
	        HttpServletRequest req, HttpServletResponse res, Model model, HttpSession session
	    ) {
		
		Map<String, Object> map = new HashMap();
		
		try {
			
			boolean userCheck = userDao.correctId(userId);
			if (userCheck) {
					
	//			enPw = Sha256Util.encryptPassword(password);
				boolean loginCheck = userDao.correctPw(userId, pass);
	
				if (loginCheck ) {
	
						User user = userDao.getUser(userId);
						session.setAttribute("USER_ID", user.getUserId());
						session.setAttribute("USER_NAME", user.getUserName());
						map.put("msg", "로그인이 성공되었습니다.");
						map.put("result", true);
						map.put("user", user);
				}else {
					map.put("msg", "패스워드가 일치하지 않습니다.");
					map.put("result", false);
				}
	
			} else {
				map.put("msg", "해당 ID가 존재하지 않거나 ID가 일치하지 않습니다.");
				map.put("result", false);
			}
		}catch (Exception e) {
			map.put("msg", e.getMessage());
			map.put("result", false);
		
		}
		
		Response.responseWrite(res, map);
		return null;
	}
	
	/**
	 * 회원가입
	 */
	@RequestMapping("/join1.go")
	public String join1Controller(
	        @RequestParam(value="msg", required=false, defaultValue="") String msg, 
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
	 
	    model.addAttribute("msg", msg);
	    return "/join1";
	}

	/**
	 * 회원가입
	 */
	@RequestMapping("/join2.go")
	public String join2Controller(
	        @RequestParam(value="msg", required=false, defaultValue="") String msg, 
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
	 
	    model.addAttribute("msg", msg);
	    return "/join2";
	}
	
	/**
	 * 아이디중복체크
	 */
	@RequestMapping("/emailch.go")
	public String emailchController(
	        @RequestParam(value="userId", required=false, defaultValue="") String userId, 
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
		
		
		Map<String, Object> map = new HashMap();
		
		boolean idchk=userDao.correctId(userId);
		if(idchk){
			map.put("result", false);
			map.put("msg", "이미 사용중인 아이디입니다.\n다른 아이디를 사용하세요.");
		}else{
			map.put("result", true);
			map.put("msg", "사용가능한 아이디 입니다.");
		}
		
		JSONObject js = JSONObject.fromObject(map);
		Response.responseWrite(res, map);
		
		return null;
	    
	}
	
	@RequestMapping("/join_do.go")
	public String joinDoController(
	        @RequestParam(value="userId", required=false, defaultValue="") String userId, 
	        @RequestParam(value="pass", required=false, defaultValue="") String pass,
	        @RequestParam(value="username", required=false, defaultValue="") String username,
	        @RequestParam(value="mobile", required=false, defaultValue="") String mobile,
	        @RequestParam(value="phone", required=false, defaultValue="") String phone,
	        @RequestParam(value="postcode", required=false, defaultValue="") String postcode,
	        @RequestParam(value="address1", required=false, defaultValue="") String address1,
	        @RequestParam(value="address2", required=false, defaultValue="") String address2,
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
		
		Map<String, Object> map = new HashMap();
		User uu = new User();
		if(userDao.correctId(userId)==false){
			uu.setUserId(userId);
			//String enPw = Sha256Util.encryptPassword(pass);
			uu.setPassword(pass);
			uu.setUserName(username);
			uu.setMobile(mobile);
			uu.setPostcode(postcode);
			uu.setAddress1(address1);
			uu.setAddress2(address2);
			uu.setPhone(phone);
			userDao.addUser(uu);
			map.put("result", true);
			map.put("msg", "회원가입이 완료되었습니다.");
		}else{
			map.put("result", false);
			map.put("msg", "중복되는 아이디가 있습니다.");
		}
		
		JSONObject js = JSONObject.fromObject(map);
		Response.responseWrite(res, map);
		
		return null;
	}

	/**
	 * 회사소개
	 */
	@RequestMapping("/intro.go")
	public String introController(
	        @RequestParam(value="msg", required=false, defaultValue="") String msg, 
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
	 
	    model.addAttribute("msg", msg);
	    return "/intro";
	}

	/**
	 * 상품 리스트
	 */
	@RequestMapping("/product_list.go")
	public String productListController(
	        @RequestParam(value="msg", required=false, defaultValue="") String msg, 
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
	 
	    model.addAttribute("msg", msg);
	    return "/product_list";
	}

	/**
	 * 상품 상세보기
	 */
	@RequestMapping("/product_detail.go")
	public String productDetailController(
	        @RequestParam(value="seq", required=false, defaultValue="0") int seq, 
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
	 
	    model.addAttribute("seq", seq);
	    return "/product_detail";
	}

	
	/**
	 * 장바구니
	 */
	@RequestMapping("/basket_do.go")
	public String basketController(
	        @RequestParam(value="pname", required=false, defaultValue="") String pname, 
	        @RequestParam(value="unit", required=false, defaultValue="0") int unit, 
	        @RequestParam(value="seq", required=false, defaultValue="0") int seq, 
	        @RequestParam(value="delivery", required=false, defaultValue="0") int delivery, 
	        @RequestParam(value="price", required=false, defaultValue="0") int price, 
	        @RequestParam(value="cnt", required=false, defaultValue="0") int cnt, 
	        @RequestParam(value="op", required=false, defaultValue="") String opt, 
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
		
		try {
			req.setCharacterEncoding("utf-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap();
		String userId = (String)(session.getAttribute("USER_ID"));
		
		OrderProduct op = new OrderProduct();
		op.setProductName(pname);//상품명
		op.setUnitPrice(unit);//상품가격
		op.setProductOption(opt);//옵션
		op.setProductSeq(seq);
		op.setBuyCount(cnt); //갯수
		op.setDiscount(0);//할인금액
		op.setPayMoney((cnt*unit));//주문총금액
		op.setStatus(1);
		op.setUserId(userId);
		orderproductDao.addOrderProduct(op);	
	    
		JSONObject js = JSONObject.fromObject(map);
		Response.responseWrite(res, map);
		  
		
	    return null;
	}
	
	/**
	 * 장바구니 이동
	 */
	@RequestMapping("/basket.go")
	public String basketgoController(
	        
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
		
		try {
			req.setCharacterEncoding("utf-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String userId = (String)(session.getAttribute("USER_ID"));
		int sumfee =0;
		
		List<OrderProduct>list = orderproductDao.getOrderProductList(userId);
		
		Iterator<OrderProduct>it = list.iterator();
		while(it.hasNext()){
			sumfee += (it.next()).getPayMoney();
		
		}
		
		model.addAttribute("data",list );
		model.addAttribute("sumfee", sumfee);
		
	    return "/basket";
	}
	
	/**
	 * 즉시구매
	 */
	@RequestMapping("/em_order.go")
	public String emgoController(
			@RequestParam(value="pname", required=false, defaultValue="") String pname, 
	        @RequestParam(value="unit", required=false, defaultValue="0") int unit, 
	        @RequestParam(value="seq", required=false, defaultValue="0") int seq, 
	        @RequestParam(value="delivery", required=false, defaultValue="0") int delivery, 
	        @RequestParam(value="price", required=false, defaultValue="0") int price, 
	        @RequestParam(value="cnt", required=false, defaultValue="0") int cnt, 
	        @RequestParam(value="op", required=false, defaultValue="") String opt, 
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
		
		try {
			req.setCharacterEncoding("utf-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap();
		String userId = (String)(session.getAttribute("USER_ID"));
		
		OrderProduct op = new OrderProduct();
		op.setProductName(pname);//상품명
		op.setUnitPrice(unit);//상품가격
		op.setProductOption(opt);//옵션
		op.setProductSeq(seq);
		op.setBuyCount(cnt); //갯수
		op.setDiscount(0);//할인금액
		op.setPayMoney((cnt*unit));//주문총금액
		op.setStatus(1);
		op.setUserId(userId);
		orderproductDao.addOrderProduct(op);
		
		
		User user = userDao.getUser(userId);
		OrderProduct op2 = orderproductDao.getOrderProductList(userId, seq);
		
	    map.put("seq",op2.getOpSeq());
	    
	    JSONObject js = JSONObject.fromObject(map);
		Response.responseWrite(res, map);
	    return null;
	}
	

	/**
	 * 장바구니 체크
	 */
	@RequestMapping("/basket_count_update.go")
	public String basketCountUpdateController(
			 @RequestParam(value="seq", required=false, defaultValue="") int seq, 
			 @RequestParam(value="count", required=false, defaultValue="") int count, 
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
		Map<String, Object> map = new HashMap();
		
		try {
			OrderProduct op = orderproductDao.getOrderProduct(seq);
			int unitPrice = op.getUnitPrice();
			int payMoney = unitPrice * count;
			orderproductDao.updateOrderProductCount(seq, count, payMoney);
			map.put("result", true);
			map.put("payMoney", payMoney);
		} catch (Exception e) {
			map.put("result", false);
			map.put("message", e.getMessage());
		}
		Response.responseWrite(res, map);
	    return null;
	}
	
	//선택상품 삭제
	@RequestMapping("/delete.go")
	public String deleteController(
			@RequestParam(value="seq", required=false, defaultValue="") String seq, 
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
		Map<String, Object> map = new HashMap();
		String userId = (String)(session.getAttribute("USER_ID"));
		
		orderproductDao.deleteOrderProduct(seq);
		
		List<OrderProduct>list = orderproductDao.getOrderProductList(userId);
		int sumfee=0;
		Iterator<OrderProduct>it = list.iterator();
		while(it.hasNext()){
			sumfee += (it.next()).getPayMoney();
		
		}
		
		model.addAttribute("data",list );
		model.addAttribute("sumfee", sumfee);
		
		return "/basket";
		
	}
	
	
	

	/**
	 * 주문하기
	 */
	@RequestMapping("/order.go")
	public String orderController(
			@RequestParam(value="seq", required=false, defaultValue="") String seq, 
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
	 
		String userId = (String)(session.getAttribute("USER_ID"));
		User user = userDao.getUser(userId);
		List list = orderproductDao.getOrderProductSelectedList(seq);
		
	    model.addAttribute("list", list);
	    model.addAttribute("user", user);
	    model.addAttribute("seq", seq);
	    return "/order";
	}
	
	/**
	 * 최종주문
	 */
	@RequestMapping("/order_do.go")
	public String orderDoController(
			@RequestParam(value="opSeq", required=false, defaultValue="") String opSeq, 
			Order od,HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
			Map<String, Object> map = new HashMap();
		try{
			od.setPaymentFee(od.getDeliveryFee()+od.getPriceSum()-od.getDiscountSum());
			
			if(od.getPaymentType()==3){
				od.setStatus("주문");
			}else{
				od.setStatus("주문");
			}
			orderDao.addOrder(od);
			int seq = (orderDao.getOrderseq(od.getUserId()));
			User user = userDao.getUser(od.getUserId());
			
			map.put("orderSeq", seq);
			map.put("order", od);
			map.put("user", user);
			
			orderproductDao.updateOrderProduct(opSeq,seq);
			map.put("msg","주문이 완료되었습니다.");
			map.put("result", true);
		}catch(Exception e){
			map.put("msg","주문이 실패하였습니다.");
			map.put("result", false);
		}
			
		    JSONObject js = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
		
	    return null;
	}

	/**
	 * 나의 상품
	 */
	@RequestMapping("/order_kspay.go")
	public String orderKspayController(
			@RequestParam(value="payMethod", required=false, defaultValue="") String payMethod,
			@RequestParam(value="sndOrdernumber", required=false, defaultValue="") String sndOrdernumber,
			@RequestParam(value="sndGoodname", required=false, defaultValue="") String sndGoodname,
			@RequestParam(value="sndAmount", required=false, defaultValue="") String sndAmount,
			@RequestParam(value="sndOrdername", required=false, defaultValue="") String sndOrdername,
			@RequestParam(value="sndEmail", required=false, defaultValue="") String sndEmail,
			@RequestParam(value="sndMobile", required=false, defaultValue="") String sndMobile,
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
		
		model.addAttribute("payMethod", payMethod);
		model.addAttribute("sndOrdernumber", sndOrdernumber);
		model.addAttribute("sndGoodname", sndGoodname);
		model.addAttribute("sndAmount", sndAmount);
		model.addAttribute("sndOrdername", sndOrdername);
		model.addAttribute("sndEmail", sndEmail);
		model.addAttribute("sndMobile", sndMobile);
	    return "/order_kspay";
	}
	/**
	 * 나의 상품
	 */
	@RequestMapping("/my_order.go")
	public String myOrderController(
			@RequestParam(value="page", required=false, defaultValue="1") int page,
			@RequestParam(value="keyword", required=false, defaultValue="") String keyword,
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
		
		List list = null;
		String paging =null;
		try {
			
			String userId = (String)(session.getAttribute("USER_ID"));
		
			
			list = orderDao.getOrderProductMyList(userId,keyword,page,ITEM_COUNT_PER_PAGE);
			int cnt = orderDao.getOrderProductMycnt(userId,keyword);
			paging = Paging.getPaging(page, cnt, ITEM_COUNT_PER_PAGE,PAGE_COUNT_PER_PAGING);		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("list", list);
	    model.addAttribute("paging", paging);
	    return "/my_order";
	}
	/**
	 * 나의 상품 상세보기
	 */
	@RequestMapping("/orderDetail.go")
	public String orderDetailController(
			@RequestParam(value="seq", required=false, defaultValue="0") int seq, 
			@RequestParam(value="page", required=false, defaultValue="1") int page,
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
		
		List<OrderProduct>list = null;
		list=orderproductDao.getOrderProductList(seq, page, ITEM_COUNT_PER_PAGE);
		int cnt = orderproductDao.getOrderProductcnt(seq);
		String paging = Paging.getPaging(page, cnt, ITEM_COUNT_PER_PAGE,PAGE_COUNT_PER_PAGING);	
		
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		
	    return "/my_orderDetail";
	}

	/**
	 * 커뮤니티 리스트
	 */
	@RequestMapping("/bbs_list.go")
	public String bbsListController(
	        @RequestParam(value="msg", required=false, defaultValue="") String msg, 
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
	 
	    model.addAttribute("msg", msg);
	    return "/bbs_list";
	}

	/**
	 * 커뮤니티 읽기
	 */
	@RequestMapping("/bbs_view.go")
	public String bbsViewController(
	        @RequestParam(value="msg", required=false, defaultValue="") String msg, 
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
	 
	    model.addAttribute("msg", msg);
	    return "/bbs_view";
	}

	/**
	 * 공지사항 리스트
	 */
	@RequestMapping("/notice_list.go")
	public String noticeListController(
	        @RequestParam(value="msg", required=false, defaultValue="") String msg, 
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
	 
	    model.addAttribute("msg", msg);
	    return "/notice_list";
	}

	/**
	 * 공지사항 읽기
	 */
	@RequestMapping("/notice_view.go")
	public String noticeViewController(
	        @RequestParam(value="seq", required=false, defaultValue="0") int seq, 
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
	 
	    model.addAttribute("seq", seq);
	    return "/notice_view";
	}

	/**
	 * 개인정보 취급방침 
	 */
	@RequestMapping("/term_private.go")
	public String termPrivateController(
	        @RequestParam(value="msg", required=false, defaultValue="") String msg, 
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
	 
	    model.addAttribute("msg", msg);
	    return "/term_private";
	}

	/**
	 * 이메일 무단수집거부 
	 */
	@RequestMapping("/term_email.go")
	public String termEmailController(
	        @RequestParam(value="msg", required=false, defaultValue="") String msg, 
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
	 
	    model.addAttribute("msg", msg);
	    return "/term_email";
	}
	//로그아웃
	@RequestMapping("/logout.go")
	public String logoutController(
	        @RequestParam(value="msg", required=false, defaultValue="") String msg, 
	        HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model
	    ) {
		session.removeAttribute("USER_ID");
		session.removeAttribute("USER_NAME");
	   
	    return "redirect:/home.go";
	}

	
}
