package view;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.codec.digest.DigestUtils;

@ManagedBean
@SessionScoped
public class UserBean {

	private boolean logged;
	private String user;
	private String password;

	// DigestUtils.md5Hex(this.password);

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String logIn(ActionEvent event) throws IOException {

		if (DigestUtils.md5Hex(this.password).equals("5527eaab87a00dbe1614481ef174f285")) {
			this.logged = true;
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	        ec.redirect(ec.getRequestContextPath() + "/home.xhtml");
		}
		System.out.println(logged);
		return null;
	}
	
	public void checkLogged() throws IOException {
	    if (!this.logged) {
	        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	        ec.redirect(ec.getRequestContextPath() + "/access.xhtml");
	    }
	}
	

}
