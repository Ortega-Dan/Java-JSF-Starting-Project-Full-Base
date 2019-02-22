package control;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Iterator;

import dtos.Warranty;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.primefaces.model.UploadedFile;

public class MPScontrol {

	public static void convertMPSExcel(UploadedFile uploadedFile) throws Exception {
		System.out.println("Convirtiendo");

		Workbook wb = WorkbookFactory.create(uploadedFile.getInputstream());

		// IMPORTED FROM HERE

		File toDelete = new File(System.getenv("IKNOLINE_FILES_DIR"));

		FileUtils.deleteDirectory(toDelete);

		DataFormatter formatter = new DataFormatter();

		// Creating directory for PDF export
		String dateFolder = LocalDateTime.now().toString().replaceFirst("\\.\\d{3}", "").replaceAll(":", "")
				.replace('T', ' ');
		String pathDir = System.getenv("IKNOLINE_FILES_DIR") + File.separator + dateFolder;

		// Creating New Excel with new Data
		Workbook newworkbook = new XSSFWorkbook();
		Sheet newsheet = newworkbook.createSheet(dateFolder);
		int newrowcounter = 0;

		Sheet sheet = wb.getSheetAt(0);

		Iterator<Row> rowIte = sheet.rowIterator();
		boolean finish = false;
		Warranty warranty = null;
		while (rowIte.hasNext()) {

			Row row = rowIte.next();
			String currentData = "";

			warranty = new Warranty();

			Iterator<Cell> cellIte = row.cellIterator();
			for (int count = 1; cellIte.hasNext() && count < 14; count++) {

				Cell cell = cellIte.next();
				if (formatter.formatCellValue(cell).equals("")) {
					finish = true;
					break;
				}
				if (formatter.formatCellValue(cell).trim().equalsIgnoreCase("fecha")) {
					break;
				}
				if (("" + count).matches("1|2|4|7|9|11|12|13")) {
					// System.out.print(count);

					// System.out.println(cell.getCellType());

					if (cell.getCellType() == CellType.FORMULA) {
						switch (cell.getCachedFormulaResultType()) {
						case NUMERIC:
							currentData = "" + cell.getNumericCellValue();
							break;
						case STRING:
							currentData = cell.getStringCellValue();
							break;
						}
					} else {
						currentData = formatter.formatCellValue(cell);
					}

					// System.out.print(currentData + "\t\t");
					switch (count) {
					case 1:
						warranty.setFecha(currentData);
						break;
					
					case 2:
						warranty.setType(currentData);
						break;

					case 4:
						warranty.setCliente(currentData);
						break;

					case 7:
						warranty.setCiudad(currentData);
						break;

					case 9:
						warranty.setSerie(currentData);
						break;

					case 11:
						warranty.setScanner(currentData);
						break;

					case 12:
						warranty.setAmount(currentData);
						break;

					case 13:
						warranty.setYears(currentData);;
						break;
					}
				}
			}
			if (finish)
				break;
			else if (warranty.getSerie() != null) {

				System.out.println(warranty);
				NewPDF.createPDF(pathDir, warranty.getFecha(), warranty.getSerie(), warranty.getScanner(), warranty.getType(), "Sin Mantenimientos",
						warranty.getYears(), warranty.getCliente(), "", warranty.getCiudad(), "", "", "", "", "");

				// Creating each new workbook row now
				Row newRow = newsheet.createRow(newrowcounter++);

				newRow.createCell(0).setCellValue(LocalDateTime.now().toString().replace('T', ' '));
				newRow.createCell(1).setCellValue(warranty.getSerie());
				newRow.createCell(2).setCellValue(warranty.getScanner());
				newRow.createCell(3).setCellValue(warranty.getType());
				newRow.createCell(4).setCellValue(warranty.getFecha());
				newRow.createCell(5).setCellValue("Sin Mantenimientos");
				newRow.createCell(6).setCellValue(warranty.getYears());
				newRow.createCell(7).setCellValue(warranty.getCliente());
				newRow.createCell(10).setCellValue(warranty.getCiudad());
				newRow.createCell(15).setCellValue(
						"Agregar comentario, en una proxima versión tendrás un espacio para agregarlo");
				Cell cell = newRow.createCell(19);
				cell.setCellValue("Producido en IknoLine.");

			}
			// System.out.println(System.getenv("IKNOLINE_FILES_DIR"));

		}

		for (int i = 0; i < 7; i++) {
			newsheet.autoSizeColumn(i);
		}
		newsheet.autoSizeColumn(10);
		newsheet.autoSizeColumn(15);
		newsheet.autoSizeColumn(19);

		FileOutputStream fileOut = new FileOutputStream(pathDir + File.separator + dateFolder + ".xlsx");
		newworkbook.write(fileOut);
		fileOut.close();

		// Closing the workbook
		newworkbook.close();

	}

	public static void main(String args[]) {
		System.out.println("Running");
		try {
			MPScontrol.hagale(
					"C:\\Users\\dev1\\Google Drive (dan.ortega@ikno.com.co)\\IknoLine Files\\seriales 1 a 30 Noviembre 2018 (File From MPS)2.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void hagale(String path) throws Exception {
		// FROM HERE

		Workbook wb = WorkbookFactory.create(new File(path));

		// TO HERE

	}
}