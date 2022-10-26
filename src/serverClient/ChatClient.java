package serverClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import org.json.JSONObject;
//이거로 설명
public class ChatClient {
	//필드
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	
	
	public void join() { //회원가입 메소드 
		 
		Scanner sc = new Scanner(System.in);
		
		System.out.println("----------------------------------------------------");
		System.out.println("회원가입을 환영합니다 *^_^*");
		
		System.out.println("id를 입력하세요");
		String id = sc.nextLine();
		
		System.out.println("이름을 입력하세요");
		String name = sc.nextLine();
		
		System.out.println("비밀번호를 입력하세요");
		String password =sc.nextLine();
		
		System.out.println("주소를 입력해보세요");
		String address = sc.nextLine();
		
		System.out.println("주소를 입력해보세요");
		String pn = sc.nextLine();
		
		System.out.println("----------------------------------------------------");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("what", "join"); //회원가입기능이면 what에 join
		jsonObject.put("id", id);
		jsonObject.put("name", name);
		jsonObject.put("password", password);
		jsonObject.put("address", address);
		jsonObject.put("phoneNumber", pn);
		
		String json = jsonObject.toString();
		try {
			send(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		receive();
		
	}
	public void login() { //로그인 메소드
		Scanner sc = new Scanner(System.in);
		
		System.out.println("----------------------------------------------------");
		System.out.println("로그인을 수행합니다 *^_^*");
		
		System.out.println("id를 입력하세요");
		String id = sc.nextLine();
		
		System.out.println("비밀번호를 입력하세요");
		String password = sc.nextLine();
	
		
		System.out.println("----------------------------------------------------");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("what", "login"); //로그인 기능이면 what에 login을 붙임
		jsonObject.put("id", id);
		jsonObject.put("password", password);		
		
		String json = jsonObject.toString();
		try {
			send(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		receive();
	}
	
	public void connect() throws IOException{
		
		socket = new Socket("localhost",50001);
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
		System.out.println("[클라이언트] 서버에 연결됨");
	}
	
	public void receive() {
		Thread thread = new Thread(()->{
			try {
			while(true) {
				String json = dis.readUTF();
				JSONObject root = new JSONObject(json);
				System.out.println(root.getBoolean("result"));
				
				
				
			}
			}catch(IOException e) {
				System.out.println("[클라이언트] 서버 연결 끊김");
				System.exit(0);
			}
		});
		thread.start();
	}
	
	public void send(String json) throws IOException {
		dos.writeUTF(json);
		dos.flush();
		
	}
	
	public void unconnect() throws IOException {
		socket.close();
		
	}
	
	public static void main(String[] args) {
		try {
			ChatClient chatClient = new ChatClient();
			chatClient.connect();
			Scanner sc = new Scanner(System.in);
			
			int num = Integer.parseInt(sc.nextLine());
			switch(num) {
				case 1:
					chatClient.join();
					break;
				case 2: 
					chatClient.login();
					break;
				case 3: 
					break;//대충 로그인 됐다 ~치면
			}
			
			System.out.println("----------------------------------------------------");
			System.out.println("환영합니다 고객님 무슨 서비스를 이용하실래요");
			System.out.println("1. 상품보기 2. 장바구니 어쩌구 3. 어쩌구");
			System.out.println("----------------------------------------------------");
			
			
			while(true) {
				String message = sc.nextLine();
				if(message.toLowerCase().equals("q")){
				break;
				} 
			}
			
			
			chatClient.unconnect();
			
		} catch(Exception e) {
			System.out.println("[클라이언트] 서버 연결 안됨");
		}
		System.out.println("[클라이언트] 종료");
	}

}
