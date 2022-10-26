package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class CartDetail {
	private int quantity;
	
	private Product product;
	
	
}
