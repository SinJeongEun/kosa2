package domain;
import java.util.Date;
import java.util.List;

import lombok.Data;
@Data
public class Orders {
	private int ordersId;
	private Date ordersDate;
	private String userId;
	private char ordersIsDeleted;
	private int totalPrice;
	private int quantity;
	private List<OrdersDetail> ordersDetailList;
	
	//시퀀스 만들기
	public Orders(int id, List<OrdersDetail> list) {
		int price = 0;
		int quantity = 0;
		for(OrdersDetail od : list) {
			price += od.getProduct().getProductPrice() * od.getQuantity();
			quantity += od.getQuantity();
		}
		
		this.totalPrice = price;
		this.quantity = quantity;
	}
	
	//json 객체 만들어서 출력보내는 함수 필요
	
	
}
