package kr.nomad.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.dto.User;

public class UserDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper userMapper = new RowMapper() {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setSeq(rs.getInt("seq"));
			user.setUserId(rs.getString("user_id"));
			user.setPassword(rs.getString("password"));
			user.setUserName(rs.getString("user_name"));
			user.setPostcode(rs.getString("postcode"));
			user.setAddress1(rs.getString("address1"));
			user.setAddress2(rs.getString("address2"));
			user.setPhone(rs.getString("phone"));
			user.setMobile(rs.getString("mobile"));
			user.setPoint(rs.getInt("point"));
			user.setRegDate(rs.getString("reg_date"));
			return user;
		}
	};
	
	//중복체크
	
	public boolean correctId(String userId) {
		String query = "SELECT COUNT(*) FROM T_NF_USER WHERE user_id = ? ";
		return (this.jdbcTemplate.queryForInt(query, new Object[] { userId }) == 1);
	}
	
	//회원가입
	public void addUser(final User user) {
		String query = "" +
				"INSERT INTO T_NF_USER " +
				"	(user_id, password, user_name, postcode, address1, address2, phone, mobile, point, reg_date) " +
				"VALUES " +
				"	(?, ?, ?, ?, ?, ?, ?, ?, ?, getDate()) ";
		this.jdbcTemplate.update(query, new Object[] {
			
			user.getUserId(),
			user.getPassword(),
			user.getUserName(),
			user.getPostcode(),
			user.getAddress1(),
			user.getAddress2(),
			user.getPhone(),
			user.getMobile(),
			user.getPoint(),
		
		});
	}
	
	
	 // 아이디 패스워드 일치 확인
	
	public boolean correctPw(String userId, String password) {		
	    String query = "SELECT count(*) AS id_cnt FROM T_NF_USER WHERE user_id = ? and password = ?";
	    try {
		    return this.jdbcTemplate.queryForInt(query, new Object[] { userId, password}) > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public void deleteUser(String seq) {
		String query = "DELETE FROM T_NF_USER WHERE seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { seq });
	}

	public void updateUser(User user) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
				"	seq = ?, " +
				"	user_id = ?, " +
				"	password = ?, " +
				"	user_name = ?, " +
				"	postcode = ?, " +
				"	address1 = ?, " +
				"	address2 = ?, " +
				"	phone = ?, " +
				"	mobile = ?, " +
				"	point = ?, " +
				"	reg_date = ? " +
				"WHERE seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			user.getSeq(),
			user.getUserId(),
			user.getPassword(),
			user.getUserName(),
			user.getPostcode(),
			user.getAddress1(),
			user.getAddress2(),
			user.getPhone(),
			user.getMobile(),
			user.getPoint(),
			user.getRegDate()
		});
	}

	public User getUser(String userId) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_USER " +
				"WHERE user_id = ? ";
		return (User)this.jdbcTemplate.queryForObject(query, new Object[] { userId }, this.userMapper);
	}

	public List<User> getUserList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" seq, user_id, password, user_name, postcode, address1, address2, phone, mobile, point, reg_date " +
				"FROM T_NF_USER " +
				"WHERE seq <= ( " +
				"	SELECT MIN(seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" seq " +
				"		FROM T_NF_USER " +
				"		ORDER BY seq DESC " +
				"	) AS A) " +
				"ORDER BY seq DESC";
		return (List<User>)this.jdbcTemplate.query(query, this.userMapper);
	}

}
