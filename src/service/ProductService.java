package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.Users;

public class ProductService {
	static Connection conn;
	static PreparedStatement pstmt;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	
    public static void startProductService(Connection c) throws SQLException, IOException {
        conn = c;
        //처리 로직에 따라 회원가입, 로그인, 회원탈퇴 메소드 실행

        //회원가입
        addProduct();
    }
    
	public boolean isAdmin(Users user) {
		if(user.getUserType().equals("ADMIN")) return true; 
		else return false;
	}
	
	//상품 등록, 
	public static void addProduct() throws SQLException, IOException {
		System.out.println("실행");
		//select문으로 모든 카테고리테이블과 컴퍼니 테이블을 보여줌. 관리자는 선택하여 카테고리와 컴퍼니 항목을 입력할 수 있다
		String sql = "insert into product(product_id, product_name, product_price, product_size,product_color, category_id, company_id, product_sex) values (?,?,?,?,?,?,?,?)";
        try {
			pstmt = conn.prepareStatement(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        int id;
        String name;
        int price;
        String color;
        int categoryId;
        int companyId;
        String productSex;
        int size;

        // 각 아이디, 이름, 패스워드, 비번, 전화번호, 주소 받는 코드 구현
        id = 1;

        System.out.println("상품명을 입력하세요");
        name = br.readLine();

        System.out.println("가격을 입력하세요");
        price = Integer.parseInt(br.readLine());
        
        System.out.println("사이즈를 입력하세요");
        size = Integer.parseInt(br.readLine());

        System.out.println("색깔을 입력하세요");
        //메소드로 테이블 전체보여주고 선택할 수 있게 함
        color = br.readLine();

        System.out.println("카테고리id를 입력하세요");
        //메소드로 테이블 전체보여주고 선택할 수 있게 함
        categoryId = 1;
        
        System.out.println("회사id를 입력하세요");
        //메소드로 테이블 전체보여주고 선택할 수 있게 함
        companyId = 1;
        
        System.out.println("성별을 입력하세요");
        //메소드로 테이블 전체보여주고 선택할 수 있게 함
        productSex = br.readLine();

        
        pstmt.setInt(1, id);
        pstmt.setString(2, name);
        pstmt.setInt(3, price);
        pstmt.setInt(4, size);
        pstmt.setString(5,color);
        pstmt.setInt(6, categoryId);
        pstmt.setInt(7, companyId);
        pstmt.setString(8, productSex);

     int r = pstmt.executeUpdate();  
     System.out.println(r);
	}
	// 상품 삭제(관리자)
	
	// 상품 조회, 
	// 필터링(전부)

}
