package domain;

import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class Product {
	private int productId;
	private String productName;
	private int productPrice;
	private int productSize;
	private String productColor;
	private String productSex;
	
	//상품 하나는 하나의 회사(브랜드), 하나의 카테고리
	private Company company;
	private Category category;
	
	
	public JSONObject productTOJson(Product product) {
		
		JSONObject json = new JSONObject();
		json.put("productId", 1);
		json.put("productName", "조던");
		json.put("productPrice", 1111111);
		json.put("productSize", 250);
		json.put("productColor", "노랑색");
		
		return json;
	}
}
