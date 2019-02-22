package dtos;

public class Warranty {

	private String fecha;
	private String cliente;
	private String ciudad;
	private String serie;
	private String scanner;
	private String amount;
	private String type;
	private String years;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getScanner() {
		return scanner;
	}

	public void setScanner(String scanner) {
		this.scanner = scanner;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Warranty [fecha=" + fecha + ", cliente=" + cliente + ", ciudad=" + ciudad + ", serie=" + serie
				+ ", scanner=" + scanner + ", amount=" + amount + ", type=" + type + ", years=" + years + "]";
	}

}
