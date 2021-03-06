package kr.nomad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.dto.Order;
import kr.nomad.dto.OrderProduct;

public class OrderProductDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper orderproductMapper = new RowMapper() {
		public OrderProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrderProduct orderproduct = new OrderProduct();
			orderproduct.setOpSeq(rs.getInt("op_seq"));
			orderproduct.setUserId(rs.getString("user_id"));
			orderproduct.setOrderSeq(rs.getInt("order_seq"));
			orderproduct.setProductSeq(rs.getInt("product_seq"));
			orderproduct.setProductName(rs.getString("product_name"));
			orderproduct.setUnitPrice(rs.getInt("unit_price"));
			orderproduct.setBuyCount(rs.getInt("buy_count"));
			orderproduct.setProductOption(rs.getString("product_option"));
			orderproduct.setDiscount(rs.getInt("discount"));
			orderproduct.setPayMoney(rs.getInt("pay_money"));
			orderproduct.setRegDate(rs.getString("reg_date"));
			orderproduct.setStatus(rs.getInt("status"));
			return orderproduct;
		}
	};
	private RowMapper orderproductMapper2 = new RowMapper() {
		public OrderProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrderProduct orderproduct = new OrderProduct();
			orderproduct.setOpSeq(rs.getInt("op_seq"));
			orderproduct.setUserId(rs.getString("user_id"));
			orderproduct.setOrderSeq(rs.getInt("order_seq"));
			orderproduct.setProductSeq(rs.getInt("product_seq"));
			orderproduct.setProductName(rs.getString("product_name"));
			orderproduct.setUnitPrice(rs.getInt("unit_price"));
			orderproduct.setBuyCount(rs.getInt("buy_count"));
			orderproduct.setProductOption(rs.getString("product_option"));
			orderproduct.setDiscount(rs.getInt("discount"));
			orderproduct.setPayMoney(rs.getInt("pay_money"));
			orderproduct.setRegDate(rs.getString("reg_date"));
			orderproduct.setPriceSum(rs.getInt("price_sum"));
			orderproduct.setDiscountSum(rs.getInt("discount_sum"));
			orderproduct.setDeliveryFee(rs.getInt("delivery_fee"));
			orderproduct.setTotalFee(rs.getInt("total_fee"));
			orderproduct.setPaymentFee(rs.getInt("payment_fee"));
			orderproduct.setPaymentType(rs.getInt("payment_type"));
			orderproduct.setBankName(rs.getString("bank_name"));
			orderproduct.setAccountName(rs.getString("account_name"));
			orderproduct.setCardName(rs.getString("card_name"));
			orderproduct.setCardNumber(rs.getString("card_number"));
			orderproduct.setStatus2(rs.getString("status2"));
			orderproduct.setOrderDate(rs.getString("order_date"));
			orderproduct.setPayDate(rs.getString("pay_date"));
			orderproduct.setDeliveryDate(rs.getString("delivery_date"));
			orderproduct.setComment(rs.getString("comment"));
			orderproduct.setBuyerName(rs.getString("buyer_name"));
			orderproduct.setPostcode(rs.getString("postcode"));
			orderproduct.setAddress1(rs.getString("address1"));
			orderproduct.setAddress2(rs.getString("address2"));
			orderproduct.setPhone(rs.getString("phone"));
			orderproduct.setMobile(rs.getString("mobile"));
			
			
			
			return orderproduct;
		}
	};
	//장바구니담기
	public void addOrderProduct(final OrderProduct orderproduct) {
		String query = "" +
				"INSERT INTO T_NF_ORDER_PRODUCT " +
				"	(product_seq, order_seq, product_name, unit_price, buy_count, product_option, discount, pay_money, reg_date, status, user_id) " +
				"VALUES " +
				"	( ?, 0, ?, ?, ?, ?, ?, ?, getDate(), ? , ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			orderproduct.getProductSeq(),
			orderproduct.getProductName(),
			orderproduct.getUnitPrice(),
			orderproduct.getBuyCount(),
			orderproduct.getProductOption(),
			orderproduct.getDiscount(),
			orderproduct.getPayMoney(),
			orderproduct.getStatus(),
			orderproduct.getUserId(),
		});
	}
	
	public List<OrderProduct> getOrderProductList(String userId) {
		String query = "" +
				"select * from t_nf_order_product where user_id = ? and status = 1 ";
		
		return (List<OrderProduct>)this.jdbcTemplate.query(query,new Object[] { userId }, this.orderproductMapper);
	}
	
	public OrderProduct getOrderProductList(String userId,int seq) {
		String query = "" +
				"select top 1 * from t_nf_order_product where user_id = ? and status = 1 and product_seq = ? ";
		
		return (OrderProduct)this.jdbcTemplate.queryForObject(query,new Object[] { userId,seq }, this.orderproductMapper);
	}
	//체크박스
	public OrderProduct chProduct(int seq,String userId) {
		String query = "" + 
				"SELECT *" +
				"FROM T_NF_ORDER_PRODUCT " +
				"WHERE product_seq = ? and user_id = ? and status = 1";
		return (OrderProduct)this.jdbcTemplate.queryForObject(query, new Object[] { seq ,userId}, this.orderproductMapper);
	}

	public void deleteOrderProduct(String seq) {
		String query = "DELETE FROM T_NF_ORDER_PRODUCT where op_seq in ("+seq+")";
		this.jdbcTemplate.update(query);
	}

	public void updateOrderProduct(OrderProduct orderproduct) { 
		String query = "" + 
				"UPDATE T_NF_ORDER_PRODUCT SET " +
				"	op_seq = ?, " +
				"	order_seq = ?, " +
				"	product_seq = ?, " +
				"	product_name = ?, " +
				"	unit_price = ?, " +
				"	buy_count = ?, " +
				"	product_option = ?, " +
				"	discount = ?, " +
				"	pay_money = ?, " +
				"	reg_date = ?, " +
				"	status = ? " +
				"WHERE op_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			orderproduct.getOpSeq(),
			orderproduct.getOrderSeq(),
			orderproduct.getProductSeq(),
			orderproduct.getProductName(),
			orderproduct.getUnitPrice(),
			orderproduct.getBuyCount(),
			orderproduct.getProductOption(),
			orderproduct.getDiscount(),
			orderproduct.getPayMoney(),
			orderproduct.getRegDate(),
			orderproduct.getStatus()
		});
	}

	public void updateOrderProductCount(int opSeq, int buyCount, int payMoney) { 
		String query = "" + 
				"UPDATE T_NF_ORDER_PRODUCT SET " +
				"	buy_count = ?, " +
				"	pay_money = ? " +
				"WHERE op_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { buyCount, payMoney, opSeq });
	}
	//주문시 상태값을 바꿈 
	public void updateOrderProduct(String opSeq,int seq) { 
		String query = "" + 
				"UPDATE T_NF_ORDER_PRODUCT SET " +
				"	status = 2 ," +
				"	order_seq = ? " +
				"where op_seq in ("+opSeq+")";
		this.jdbcTemplate.update(query, new Object[] {seq});
	}

	public OrderProduct getOrderProduct(int op_seq) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_ORDER_PRODUCT " +
				"WHERE op_seq = ? ";
		try {
			return (OrderProduct)this.jdbcTemplate.queryForObject(query, new Object[] { op_seq }, this.orderproductMapper);
		} catch (Exception e) {
			return new OrderProduct();
		}
	}
	//상품 상세보기
	public List<OrderProduct> getOrderProductList(int seq, int page, int itemCountPerPage) {
		String query = "" 
			+ "	SELECT * FROM ( "
			+ "		SELECT "
			+ "			ROW_NUMBER() OVER(order by op_seq DESC) as row_seq ,* "
			+ "			 from V_NF_ORDERD "
			+ "			where order_seq = ?"
			+ "      )AS A"
			+ "	WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		try{
			return (List<OrderProduct>)this.jdbcTemplate.query(query, new Object[] { seq }, this.orderproductMapper2);
		}catch(Exception e){
			return null;
		}
	}
	
	//상품 갯수
	public int getOrderProductcnt(int seq) {
		String query = "" 
			+"select count(*) from v_nf_orderd where order_seq = ?";
		try{
			return (int)this.jdbcTemplate.queryForInt(query, new Object[] { seq });
		}catch(Exception e){
			return 0;
		}
	}
	public List<OrderProduct> getOrderProductSelectedList(String seq) {
		String query = ""
	            + " SELECT * "
	            + " FROM T_NF_ORDER_PRODUCT "
	            + "	WHERE op_seq in ("+ seq +") and status=1 ";
		return (List<OrderProduct>)this.jdbcTemplate.query(query, this.orderproductMapper);
	}
	

}
