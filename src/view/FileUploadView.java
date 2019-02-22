package view;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.poi.EncryptedDocumentException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import control.MPScontrol;

@ManagedBean
public class FileUploadView {

	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void upload() {
		if (file != null) {
			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("El archivo", event.getFile().getFileName() + " fue cargado.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		try {
			MPScontrol.convertMPSExcel(event.getFile());

			//Redirection from Java Code

			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(ec.getRequestContextPath() + "/downloads.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
