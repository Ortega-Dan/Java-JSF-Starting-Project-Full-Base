package view;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import control.UserAmountsControl;
import dtos.UserAmount;

@ManagedBean
@RequestScoped // Using Scope annotation
public class AmountsBean {

	private List<UserAmount> userAmounts = new ArrayList<>();

	@PostConstruct
	public void init() {

		try {
			UserAmountsControl.fillUserAmounts(userAmounts);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public List<UserAmount> getUserAmounts() {
		return userAmounts;
	}

	public void setUserAmounts(List<UserAmount> userAmounts) {
		this.userAmounts = userAmounts;
	}

}
