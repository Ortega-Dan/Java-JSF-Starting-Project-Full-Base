package view;
import java.time.LocalDate;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped // Using Scope annotation
public class FirstBean {

	private String name;

	public String getName() {
		return LocalDate.now().toString();
	}

}