package serverClient;

import java.io.IOException ;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;

import domain.Users;
import service.ReviewService;
import service.UserService;

public class ChatServer {
	// DB 연결
	static Connection conn = null;
	
	//필드
	ServerSocket serverSocket;
	ExecutorService threadPool = Executors.newFixedThreadPool(100);
	List<Users> usersList = new ArrayList<>();
	

	
	public void start() throws IOException {
		try { // JDBC Driver를 메모리로 로딩하고, DriverManager에 등록
		       Class.forName("oracle.jdbc.OracleDriver");

		       // DB와 연결 
		       conn = DriverManager.getConnection("jdbc:oracle:thin:@kosa402.iptime.org:50021/orcl", "java", "oracle");
		       System.out.println("연결 성공" + conn); 		    
		       ReviewService.startReviewService(conn);
		       
		    } catch (Exception e) {
		  	  System.out.println(e.getMessage());
		  	  
		    }
		
		//직접 스레드 클래스로부터 생성. 매개값은 runnable
		serverSocket = new ServerSocket(50001);
		Thread thread = new Thread(()->{
			try {
				while(true) {
				
				Socket socket = serverSocket.accept();
				SocketClient sc = new SocketClient(this,socket); 
				}
			} catch(Exception e) {	
			}
		});
		thread.start();
	}
	
//	public void addSocketClient(String id, String name, String password, String adress, SocketClient sc) { //db에 데이터 넣기 항상 소켓클라이언트 매개변수는 넣어줘야댐
//		
//		Users users = new Users();
//		users.setUserId(id);
//		users.setUserName(name);
//		users.setUserPassword(password);
//		users.setUserAddress(adress);
//		usersList.add(users); //유저 생성 후 list 담음
//		
//		JSONObject root = new JSONObject();//클라이언트에 결과를 보내줄 용도로 잘 담겼다면
//		root.put("check", true); //담은 다음에 true 값을 다시 서버소켓의 send를 이용해 클라이언트 소켓에 전달해줌
//		String json = root.toString();
//		sc.send(json);
//		
//	}
	public void win(SocketClient sc) throws SQLException {		
		JSONObject root = new JSONObject();
		root.put("check", true); //담은 다음에 true 값을 다시 서버소켓의 send를 이용해 클라이언트 소켓에 전달해줌
		String json = root.toString();
		sc.send(json);
	}
	
	public void stop() {
		try {
			serverSocket.close();
			threadPool.shutdownNow();
			//클라이언트와 통신하고 있는 소켓도 close 필요
			//chatRoom.values().stream().forEach(sc -> sc.close());
			System.out.println("[서버] 종료됨 ");
		} catch (IOException e) {
		}
	}
	
	public static void main(String[] args) {
				
		try {
			ChatServer chatServer = new ChatServer();
			chatServer.start();
			System.out.println("----------------------------------------------------");
			System.out.println("서버를 종료하려면 q 또는 Q를 입력하고 enter키를 입력하세요.");
			System.out.println("----------------------------------------------------");


			// 키보드 입력을 받음
			Scanner sc = new Scanner(System.in);
			while (true) {
				String key = sc.nextLine();
				if (key.toLowerCase().equals("q")) {
					break;
				}
			}
			sc.close();

			// TCP 서버 종료
			chatServer.stop();

			
			
		} catch (IOException e) {
			System.out.println("[서버] "+e.getMessage());
		}
		
	}

}
