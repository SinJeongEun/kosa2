package serverClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.Connection;

import org.json.JSONObject;

import service.ReviewService;
import service.UserService;

public class SocketClient {
	// 필드
	ChatServer chatServer;
	Socket socket;
//	Connection conn;
	
	String id;
	String name;
	String password;
	String phoneNumber;
	String address;
	
	DataInputStream dis;
	DataOutputStream dos;
	
	// 서비스 사용하기 위한 생성자 만들기
	UserService userSerivce;
	ReviewService reviewService;;
	// 사용발 리뷰서비스, 드으등등 이렇게 하나의 객체를 생성해서 후에 사용하면 됨
	
	// 생성자
	public SocketClient(ChatServer chatServer, Socket socket) {
		try {
			this.chatServer = chatServer;
//			this.conn = chatServer.conn;
			this.socket = socket;
			this.dis = new DataInputStream(socket.getInputStream());
			this.dos = new DataOutputStream(socket.getOutputStream());
//			
//			this.userSerivce = new UserService(ChatServer.conn);
//			this.reviewService = new ReviewService();
			
//			receive();

		} catch (IOException e) {

		}

	}

	// 클라이언트로부터 받음
//	public void receive() {
//
//		chatServer.threadPool.execute(() -> {
//			try {
//				while (true) {
//					
//					String receiveJson = dis.readUTF(); 
//					JSONObject jsonObject = new JSONObject(receiveJson); 
//				
//					
//					String what = jsonObject.getString("what");//파싱했을 때 어떤 정보인지 분별함
//					System.out.println(" what  실행 됨~~~~~~~~~~~  " + jsonObject);
//					switch(what) {
//						case "join": //로그인이면 ~~ 
////							question1();
//							this.id = jsonObject.getString("id");
//							this.name =jsonObject.getString("name");
//							this.password = jsonObject.getString("password");
//							this.phoneNumber = jsonObject.getString("phoneNumber");
//							this.address = jsonObject.getString("address");
//							
//							System.out.println("conn ~~~~~~~~~~~  " + ChatServer.conn);
//							
////							int result = userSerivce.addUser(id, name, password, phoneNumber, address, this); //서버에서 할 기능을 호출 로그인이면 새로운 데이터 추가~
//							System.out.println(" sql  실행 됨~~~~~~~~~~~  " + result);
//							
//							
////							if(result == 1) {
////								JSONObject jsonObject2 = new JSONObject();
////								jsonObject2.put("result", "성공"); //회원가입기능이면 what에 join
////								String json = jsonObject2.toString();
////								send(json);	
////							}
////							else {
////								JSONObject jsonObject2 = new JSONObject();
////								jsonObject2.put("result", "1.회원가입 '\n 2.로그인"); 
////								question1();
//
//							}
//							
//							break;
//							
//						case "login":
//							
//					}
//					chatServer.win(this);
//					
//					}
//				
//			} catch (Exception e) { // 클라이언트가 서버와의 연결을 끊었을 때 클라이언트의 정보를 지워야함
//				System.out.println("에러남");
//
//			}
//
//		});
//
//	}
	
	public void question1(){
		//제이슨으로 넘겨
		System.out.println("----------------------------------------------------");
		System.out.println("환영합니다");
		System.out.println("1. 회원가입 2. 로그인");
		System.out.println("----------------------------------------------------");
	}
	// 클라이언트로 보냄
	public void send(String json) {
		try {
			dos.writeUTF(json);
			dos.flush();
		} catch (IOException e) {
		}
	}

	public void close() {
		try {
			socket.close();
		} catch (IOException e) {

		}

	}

}
