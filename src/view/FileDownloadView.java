package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import control.CreateZip;

@ManagedBean
public class FileDownloadView {

	private StreamedContent file;

	public FileDownloadView() {
		try {
			File originalDir = new File(System.getenv("IKNOLINE_FILES_DIR"));
			File[] dirs = originalDir.listFiles();

			File nowFile = dirs[0];
			System.out.println("Dirs Length: " + dirs.length);
			System.out.println("The dir to Download is: " + nowFile.getPath());

			String pathToDownload = CreateZip.createZip(nowFile.getPath());

			InputStream stream = new FileInputStream(pathToDownload);

			// InputStream stream =
			// FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/iknoimage.png");
			file = new DefaultStreamedContent(stream, "application/zip", nowFile.getName() + " pdfsyexcel.zip");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StreamedContent getFile() {
		return file;
	}
}
