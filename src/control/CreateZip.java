package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZip {

	public static String createZip(String pathToDirOrFile) {

		String zipPath = pathToDirOrFile + ".zip";

		// zipPath = "/home/danort/Desktop/mvntest/somezip.zip";

		String zipFile = zipPath;

		String srcDir = pathToDirOrFile;

		try {

			// create byte buffer

			byte[] buffer = new byte[1024];

			FileOutputStream fos = new FileOutputStream(zipFile);

			ZipOutputStream zos = new ZipOutputStream(fos);

			File dir = new File(srcDir);

			File[] files = dir.listFiles();

			for (int i = 0; i < files.length; i++) {

				System.out.println("Adding file: " + files[i].getName());

				FileInputStream fis = new FileInputStream(files[i]);

				// begin writing a new ZIP entry, positions the stream to the start of the entry
				// data

				zos.putNextEntry(new ZipEntry(files[i].getName()));

				int length;

				while ((length = fis.read(buffer)) > 0) {

					zos.write(buffer, 0, length);

				}

				zos.closeEntry();

				// close the InputStream

				fis.close();

			}

			// close the ZipOutputStream

			zos.close();

		}

		catch (IOException ioe) {

			ioe.printStackTrace();

		}

		return zipPath;
	}

}
