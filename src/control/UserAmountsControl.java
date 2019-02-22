package control;

import java.util.List;

import dtos.UserAmount;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAmountsControl {

	public static void fillUserAmounts(List<UserAmount> userAmounts) throws SQLException {


		String sql = "SELECT isnull(validation_operator_user_name,'Sin Usuario') as user1, \r\n"
				+ "count(id) as amount \r\n"
				+ "FROM Synergetics_Report.dbo.batch_instance \r\n" + "where batch_status = 'FINISHED' \r\n"
				+ "and last_modified > concat(left(CURRENT_TIMESTAMP,11), ' 00:00:00.000')\r\n"
				+ "group by validation_operator_user_name \r\n" + "\r\n" + "union all\r\n" + "\r\n"
				+ "SELECT 'TOTAL' as user1, \r\n" + "count(id) as amount \r\n"
				+ "FROM Synergetics_Report.dbo.batch_instance \r\n" + "where batch_status = 'FINISHED' \r\n"
				+ "and last_modified > concat(left(CURRENT_TIMESTAMP,11), ' 00:00:00.000')\r\n" + "\r\n" + "\r\n"
				+ "order by amount desc";
		ResultSet rs = (ResultSet) SQLControl.runSQL(SQLControl.SELECT, sql);

		while (rs.next()) {

			UserAmount u1 = new UserAmount(rs.getString("user1").replace("1", ""), rs.getInt("amount"));
			userAmounts.add(u1);

			System.out.println(rs.getString("user1") + " " + rs.getInt("amount"));
		}
		
		SQLControl.closeResultSetStuff(rs);

	}

}
