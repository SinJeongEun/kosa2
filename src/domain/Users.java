package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Users {
	private String userId;
	private String userName;
	private String userPassword;
	private String phoneNumber;
	private String userAddress;
	private String userType;
}
