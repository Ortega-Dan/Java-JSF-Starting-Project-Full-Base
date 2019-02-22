package dtos;

public class RestResponse {
	private String codret;
	private String msg;
	
	public String getCodret() {
		return codret;
	}
	public void setCodret(String codret) {
		this.codret = codret;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	@Override
	public String toString() {
		return "RestResponse [codret=" + codret + ", msg=" + msg + "]";
	}
		
	
}
