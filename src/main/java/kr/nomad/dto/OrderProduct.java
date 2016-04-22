package kr.nomad.dto;

public class OrderProduct {
	
	int opSeq = 0;
	int orderSeq = 0;
	int productSeq = 0;
	String productName = "";
	int unitPrice = 0;
	int buyCount = 0;
	String productOption = "";
	int discount = 0;
	int payMoney = 0;
	String regDate = "";
	int status = 0;
	String userId = "";
	String orderTitle = "";
	int priceSum = 0;
	int discountSum = 0;
	int deliveryFee = 0;
	int totalFee = 0;
	int paymentFee = 0;
	int paymentType = 0;
	String bankName = "";
	String accountName = "";
	String cardName = "";
	String cardNumber = "";
	String status2 = "";
	String orderDate = "";
	String payDate = "";
	String deliveryDate = "";
	String comment = "";
	String buyerName = "";
	String postcode = "";
	String address1 = "";
	String address2 = "";
	String phone = "";
	String mobile = "";
	String payTypeText ="";
	
	public String getOrderTitle() {
		return orderTitle;
	}
	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}
	public int getPriceSum() {
		return priceSum;
	}
	public void setPriceSum(int priceSum) {
		this.priceSum = priceSum;
	}
	public int getDiscountSum() {
		return discountSum;
	}
	public void setDiscountSum(int discountSum) {
		this.discountSum = discountSum;
	}
	public int getDeliveryFee() {
		return deliveryFee;
	}
	public void setDeliveryFee(int deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	public int getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}
	public int getPaymentFee() {
		return paymentFee;
	}
	public void setPaymentFee(int paymentFee) {
		this.paymentFee = paymentFee;
	}
	public int getPaymentType() {
		return paymentType;
	}
	public String getPayTypeText() {
		if(this.paymentType==1)
		{
			return "신용카드";
		}
		if(this.paymentType==2)
		{
			return "휴대폰";
		}
		if(this.paymentType==3)
		{
			return "무통장입금";
		}
		return null;
	}
	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getStatus2() {
		return status2;
	}
	public void setStatus2(String status2) {
		this.status2 = status2;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getOpSeq() {
		return opSeq;
	}
	public void setOpSeq(int opSeq) {
		this.opSeq = opSeq;
	}
	public int getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(int orderSeq) {
		this.orderSeq = orderSeq;
	}
	public int getProductSeq() {
		return productSeq;
	}
	public void setProductSeq(int productSeq) {
		this.productSeq = productSeq;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}
	public String getProductOption() {
		return productOption;
	}
	public void setProductOption(String productOption) {
		this.productOption = productOption;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(int payMoney) {
		this.payMoney = payMoney;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}


}
