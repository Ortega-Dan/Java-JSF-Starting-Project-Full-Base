package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dtos.Llegada;

public class TimeControl {

	/**
	 * Returns the time if already recorded for that date, or null if not recorded
	 * yet
	 * 
	 * @throws SQLException
	 */
	public static String verifyIPofUserAndDate(String ipAddress, LocalDate date) throws SQLException {
		String result = null;
		String sqlSelect = "select * from Ourownrecords.dbo.login where ipaddress = '" + ipAddress + "' and fecha = '"
				+ date + "';";

		ResultSet rs = (ResultSet) SQLControl.runSQL(SQLControl.SELECT, sqlSelect);

		if (rs.next()) {

			result = rs.getString(5);
		}

		return result;
	}

	public static void insertInfoForIPofUserAndDate(String ipAddress, String user, LocalDate now, String llegastetime) {

		String sqlInsert = "insert into Ourownrecords.dbo.login values('" + System.currentTimeMillis() + user + "', '"
				+ user + "','" + ipAddress + "', getdate(), '" + llegastetime + "', null)";

		System.out.println(SQLControl.runSQL(SQLControl.INSERT, sqlInsert));

	}

	public static void getListOfLast7(String user, List<Llegada> listaLlegadas) throws SQLException {

		String sql = "select top 7 * from Ourownrecords.dbo.login where usuario = '" + user + "' order by fecha desc";
		ResultSet rs = (ResultSet) SQLControl.runSQL(SQLControl.SELECT, sql);
		String fechaToShow = null;
		String horaToShow = null;
		String noteIndicatorToShow = "";
		String actualNoteForTooltip = "";

		while (rs.next()) {

			fechaToShow = LocalDate.parse(rs.getString("fecha")).format(DateTimeFormatter.ofPattern("E. dd-MMM-yyyy"));
			horaToShow = LocalTime.parse(rs.getString("hora")).format(DateTimeFormatter.ofPattern("hh:mm a"));
			String tempNoteText = null;
			noteIndicatorToShow = "";
			actualNoteForTooltip = "";
			tempNoteText = rs.getString("note");
			if (tempNoteText != null) {
				noteIndicatorToShow = "Ver";
				actualNoteForTooltip = tempNoteText;
			}

			Llegada ll = new Llegada(user, fechaToShow, horaToShow, noteIndicatorToShow, actualNoteForTooltip);
			listaLlegadas.add(ll);

		}

		SQLControl.closeResultSetStuff(rs);

	}

	public static void updateNote(String ipAddress, String user, String notieText) {
		String sqlup = "update Ourownrecords.dbo.login set note = '" + notieText + "' " + "where usuario = '" + user
				+ "' and ipaddress = '" + ipAddress + "' and fecha = convert(date,getdate());";

		System.out.println(SQLControl.runSQL(SQLControl.UPDATE, sqlup));

	}

}
