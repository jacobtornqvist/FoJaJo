package dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.LogEntry;

public class LogEntryDAO {
	
	ConnectionFactory conFact = new ConnectionFactory();
	
	public ArrayList<LogEntry> mapResultsetToLogEntry(ResultSet result)throws SQLException {
		ArrayList<LogEntry> logEntries = new ArrayList<LogEntry>();
		while(result.next()) {
			logEntries.add(new LogEntry(result.getInt(1), result.getDouble(2), result.getString(3), result.getTimestamp(4), result.getInt(5)));
		}
		return logEntries;
	}
	public ArrayList<LogEntry> getLogEntries(int accountNbr) throws Exception{
		final String call = "{call user_getAllEntries(?)}";
		try (Connection con = conFact.createConnection(); CallableStatement stmt = con.prepareCall(call)){
			stmt.setInt(1, accountNbr);
			stmt.execute();
			return mapResultsetToLogEntry(stmt.getResultSet());
		} catch (Exception e) {
			throw ExceptionHandler.handelException(e, accountNbr);
		}
	}

}
