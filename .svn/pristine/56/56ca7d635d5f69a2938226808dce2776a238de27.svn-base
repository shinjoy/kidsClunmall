package kr.nomad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.dto.Order;
import kr.nomad.dto.OrderProduct;

public class OrderDao {

	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper orderMapper = new RowMapper() {
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
			Order order = new Order();
			order.setOrderSeq(rs.getInt("order_seq"));
			order.setUserId(rs.getString("user_id"));
			order.setOrderTitle(rs.getString("order_title"));
			order.setPriceSum(rs.getInt("price_sum"));
			order.setDiscountSum(rs.getInt("discount_sum"));
			order.setDeliveryFee(rs.getInt("delivery_fee"));
			order.setTotalFee(rs.getInt("total_fee"));
			order.setPaymentFee(rs.getInt("payment_fee"));
			order.setPaymentType(rs.getInt("payment_type"));
			order.setBankName(rs.getString("bank_name"));
			order.setAccountName(rs.getString("account_name"));
			order.setCardName(rs.getString("card_name"));
			order.setCardNumber(rs.getString("card_number"));
			order.setStatus(rs.getString("status"));
			order.setOrderDate(rs.getString("order_date"));
			order.setPayDate(rs.getString("pay_date"));
			order.setDeliveryDate(rs.getString("delivery_date"));
			order.setComment(rs.getString("comment"));
			order.setBuyerName(rs.getString("buyer_name"));
			order.setPostcode(rs.getString("postcode"));
			order.setAddress1(rs.getString("address1"));
			order.setAddress2(rs.getString("address2"));
			order.setPhone(rs.getString("phone"));
			order.setMobile(rs.getString("mobile"));
			return order;
		}
	};
	//주문하기
	public void addOrder(Order order) {
		String query = "" +
				"INSERT INTO T_NF_ORDER " +
				"	(user_id, order_title, price_sum, discount_sum, "
				+ "delivery_fee, total_fee, payment_fee, payment_type, bank_name, "
				+ "account_name, card_name, card_number, status, order_date, pay_date, "
				+ "delivery_date, comment, buyer_name, postcode, address1, address2, phone, mobile) " +
				"VALUES " +
				"	(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, getDate(), ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			
			order.getUserId(),
			order.getOrderTitle(),
			order.getPriceSum(),
			order.getDiscountSum(),
			order.getDeliveryFee(),
			order.getTotalFee(),
			order.getPaymentFee(),
			order.getPaymentType(),
			order.getBankName(),
			order.getAccountName(),
			order.getCardName(),
			order.getCardNumber(),
			order.getStatus(),
			order.getPayDate(),
			order.getDeliveryDate(),
			order.getComment(),
			order.getBuyerName(),
			order.getPostcode(),
			order.getAddress1(),
			order.getAddress2(),
			order.getPhone(),
			order.getMobile()
		});
	}
	//seq 뽑기
	
	public int getOrderseq(String userId) {
		String query = "" + 
				"SELECT max(order_seq) " +
				"FROM T_NF_ORDER where user_id = ?";
		return (int)this.jdbcTemplate.queryForInt(query,new Object[] { userId });
	}
	

	public void deleteOrder(String order_seq) {
		String query = "DELETE FROM T_NF_ORDER WHERE order_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { order_seq });
	}

	public void updateOrder(Order order) { 
		String query = "" + 
				"UPDATE T_NF_ORDER SET " +
				"	order_seq = ?, " +
				"	user_id = ?, " +
				"	order_title = ?, " +
				"	price_sum = ?, " +
				"	discount_sum = ?, " +
				"	delivery_fee = ?, " +
				"	total_fee = ?, " +
				"	payment_fee = ?, " +
				"	payment_type = ?, " +
				"	bank_name = ?, " +
				"	account_name = ?, " +
				"	card_name = ?, " +
				"	card_number = ?, " +
				"	status = ?, " +
				"	order_date = ?, " +
				"	pay_date = ?, " +
				"	delivery_date = ?, " +
				"	comment = ?, " +
				"	buyer_name = ?, " +
				"	postcode = ?, " +
				"	address1 = ?, " +
				"	address2 = ?, " +
				"	phone = ?, " +
				"	mobile = ? " +
				"WHERE order_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			order.getOrderSeq(),
			order.getUserId(),
			order.getOrderTitle(),
			order.getPriceSum(),
			order.getDiscountSum(),
			order.getDeliveryFee(),
			order.getTotalFee(),
			order.getPaymentFee(),
			order.getPaymentType(),
			order.getBankName(),
			order.getAccountName(),
			order.getCardName(),
			order.getCardNumber(),
			order.getStatus(),
			order.getOrderDate(),
			order.getPayDate(),
			order.getDeliveryDate(),
			order.getComment(),
			order.getBuyerName(),
			order.getPostcode(),
			order.getAddress1(),
			order.getAddress2(),
			order.getPhone(),
			order.getMobile()
		});
	}

	public Order getOrder(String order_seq) {
		String query = "" + 
				"SELECT order_seq, user_id, order_title, price_sum, discount_sum, delivery_fee, total_fee, payment_fee, payment_type, bank_name, account_name, card_name, card_number, status, order_date, pay_date, delivery_date, comment, buyer_name, postcode, address1, address2, phone, mobile " +
				"FROM T_NF_ORDER " +
				"WHERE order_seq = ? ";
		return (Order)this.jdbcTemplate.queryForObject(query, new Object[] { order_seq }, this.orderMapper);
	}
	//내 상품
	public List<Order> getOrderProductMyList(String userId,String keyword,int page, int itemCountPerPage) {
		
			String con ="";
		if(keyword!=""){
			con = "and order_title like "+"'%"+keyword+"%'";
		}
		
		String query = "" 
				+ "	SELECT * FROM ( "
				+ "		SELECT "
				+ "			ROW_NUMBER() OVER(order by ORDER_DATE DESC) as row_seq ,* "
				+ "			 from T_NF_ORDER "
				+ "			where user_id = ?"
				+ con
				+ "      )AS A"
				+ "	WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		try{
			return (List<Order>)this.jdbcTemplate.query(query, new Object[] { userId },this.orderMapper);
		}catch(Exception e){
			return null;
		}
	}
	//갯수
	public int getOrderProductMycnt(String userId,String keyword) {
		String con ="";
		if(keyword!=""){
			con = "and order_title like "+"'%"+keyword+"%'";
		}
		String query = ""
	            + " SELECT COUNT(*) "
	            + " FROM T_NF_ORDER "
	            + "	WHERE user_id = ?"+con;
		return (int)this.jdbcTemplate.queryForInt(query, new Object[] {userId});
	}
}
