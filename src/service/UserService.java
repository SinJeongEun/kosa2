package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import provider.ConnectionProvider;
import serverClient.SocketClient;

public class UserService {
	private static PreparedStatement pstmt;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static boolean check(){ // 우리의 회원인지 아닌지르 검사하는 코드
        //중복
        return true;
    }


    public static int addUser(String id, String name, String pw, String phoneNumber, String addr) throws SQLException, IOException {
        // SocketClient sc  인자 잠시 삭제 함
        String sql = "insert into users(user_id, user_name, user_password, phone_number, user_address) values (?,?,?,?,?)";
        pstmt = ConnectionProvider.getConnection().prepareStatement(sql);
                
        pstmt.setString(1, id);
        pstmt.setString(2, name);
        pstmt.setString(3, pw);
        pstmt.setString(4,phoneNumber);
        pstmt.setString(5, addr);

        int rows = pstmt.executeUpdate();
        return rows;
    }

    public static void login(String id, String pw) throws IOException, SQLException {
        System.out.println("아이디를 입력하세요");
        id = br.readLine();

        System.out.println("비번 입력하세요");
        pw = br.readLine();

        String sql = "select user_id from users where user_id=? and user_password=?";
        pstmt =  ConnectionProvider.getConnection().prepareStatement(sql);
        pstmt.setString(1, id);
        pstmt.setString(2, pw);

        ResultSet rs = pstmt.executeQuery();
        if(rs.next()) {
            System.out.println(rs.getString("user_id"));
            System.out.println("로그인 성공");
        }

        else System.out.println("잘못된 로그인 정보입니다.");


    }

    public static void deleteUser() throws IOException, SQLException {
        String id;
        String pw;

        System.out.println("아이디를 입력하세요");
        id = br.readLine();

        System.out.println("비번 입력하세요");
        pw = br.readLine();

        String sql = "select user_id from users where user_id=? and user_password=?";
        pstmt =  ConnectionProvider.getConnection().prepareStatement(sql);
        pstmt.setString(1, id);
        pstmt.setString(2, pw);

        ResultSet rs = pstmt.executeQuery();
        if(rs.next()) {
            //탈퇴 진행
            sql = "delete from users where user_id=?";
            pstmt = ConnectionProvider.getConnection().prepareStatement(sql);
            pstmt.setString(1, id);
            int rows = pstmt.executeUpdate();
            if(rows == 1){
                System.out.println("탈퇴 성공");
            }else System.out.println("잘못된 회원 정보입니다");
        }

        else System.out.println("잘못된 회원 정보입니다.");
    }

    public static void findPw() throws IOException, SQLException {
        String id;
        String name;

        System.out.println("아이디를 입력하세요");
        id = br.readLine();

        System.out.println("이름 입력하세요");
        name = br.readLine();

        String sql = "select user_id from users where user_id=? and user_name=?";
        pstmt =  ConnectionProvider.getConnection().prepareStatement(sql);
        pstmt.setString(1, id);
        pstmt.setString(2, name);

        ResultSet rs = pstmt.executeQuery();
        if(rs.next()) {
            //비밀번호를 알려준다.
            sql = "select user_password from users where user_id=?";
            pstmt = ConnectionProvider.getConnection().prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                System.out.println("당신의 비밀번호 : " + rs.getString("user_password"));
            }else System.out.println("잘못된 회원 정보입니다");
        }

        else System.out.println("잘못된 회원 정보입니다.");
    }
    
    //마이페이지에서 자신의 주소만 수정하기
//    public s
    
    
    
}