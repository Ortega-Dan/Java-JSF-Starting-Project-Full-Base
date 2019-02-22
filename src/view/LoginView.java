package view;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.faces.event.ActionEvent;

import control.TimeControl;
import dtos.Llegada;

@ManagedBean
@SessionScoped
public class LoginView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ipAddress = "";
	private String user = "";
	private boolean notie = false;
	private List<Llegada> listaLlegadas = null;
	private String notieText = "";

	@PostConstruct
	public void init() {

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String ipAddressNumba = request.getHeader("X-FORWARDED-FOR");
		if (ipAddressNumba == null) {
			ipAddressNumba = request.getRemoteAddr();
		}
		System.out.println("ipAddress:" + ipAddressNumba);
		ipAddress = ipAddressNumba;

		if (ipAddress.equalsIgnoreCase("20.21.2.198")) {
			this.user = "Juliana";
		} else if (ipAddress.equalsIgnoreCase("20.21.2.199")) {
			this.user = "Claudia";
		} else if (ipAddress.equalsIgnoreCase("20.21.2.196")) {
			this.user = "Mauber";
		} else
			this.user = "Otro";

		getLlegada();

		this.updateListaLlegadas();
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public boolean isNotie() {
		return notie;
	}

	public String getNotieText() {
		return notieText;
	}

	public void setNotieText(String notieText) {
		this.notieText = notieText;
	}

	public void setNotie(boolean notie) {
		this.notie = notie;
	}

	public String getLlegada() {

		String llegaste = null;
		String llegastetime = null;
		try {
			llegastetime = TimeControl.verifyIPofUserAndDate(ipAddress, LocalDate.now());
			System.out.println("Refresh by" + ipAddress);

			if (llegastetime == null) {
				llegastetime = LocalTime.now().toString();
				TimeControl.insertInfoForIPofUserAndDate(ipAddress, user, LocalDate.now(), llegastetime);
			}

			llegastetime = LocalTime.parse(llegastetime.replaceAll("0000", ""))
					.format(DateTimeFormatter.ofPattern("hh:mm a"));

			llegaste = ", hoy " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))
					+ ", llegaste a las " + llegastetime;

			return user + llegaste;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user + llegaste;

	}

	private void updateListaLlegadas() {
		try {

			listaLlegadas = new ArrayList<>();
			TimeControl.getListOfLast7(user, listaLlegadas);
			System.out.println("LIst of last 7");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Llegada> getUltimos7() {
		return listaLlegadas;

	}

	public String myButtonAction(ActionEvent actionEvent) {
		this.notieText = this.notieText.replaceAll("\\s", " ").trim();
		if (!this.notieText.isEmpty()) {
			TimeControl.updateNote(this.ipAddress, this.user, this.notieText);
		}
		this.updateListaLlegadas();
		return null;
	}

	public String toggleNotie(ActionEvent actionEvent) {
		if (this.notie)
			this.notie = false;
		else
			this.notie = true;
		return null;

	}

}