package dtos;

public class UserAmount {
	private String user;
	private int amount;
	
	
	
	public UserAmount(String user, int amount) {
		
		this.user = user;
		this.amount = amount;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
}
