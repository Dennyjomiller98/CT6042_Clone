package oracle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.bson.Document;

public class AttackConnections extends AbstractOracleConnections
{
	public AttackConnections()
	{
		//Empty Constructor
	}

	public String getDataFromUsername(Document doc)
	{
		setOracleDriver();
		AbstractOracleConnections conn = new AbstractOracleConnections();
		Connection oracleClient = conn.getOracleClient();
		String ret;

		String sql = "SELECT * FROM CT6042 WHERE USERNAME='" + doc.get("Username") + "'";
		ret = executeInjectionQuery(oracleClient, sql);
		return ret;
	}

	private String executeInjectionQuery(Connection oracleClient, String sql)
	{
		StringBuilder dbData = new StringBuilder();
		try (Statement statement = oracleClient.createStatement())
		{
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next())
			{
				String username = resultSet.getString("Username");
				if(username != null)
				{
					dbData.append(" Username: ").append(username).append(". ");
				}
				String password = resultSet.getString("Password");
				if(password != null)
				{
					dbData.append(" Password: ").append(password).append(". ");
				}
				String information = resultSet.getString("Information");
				if(information != null)
				{
					dbData.append(" Information: ").append(information).append(". ");
				}
			}
		}
		catch(Exception e)
		{
			LOG.error("Query failure, using query: " + sql, e);
		}
		return dbData.toString();
	}

	public boolean attemptLogin(Document doc)
	{
		//Returns True or False on if the user would be successful logging in.
		boolean ret;
		setOracleDriver();
		AbstractOracleConnections conn = new AbstractOracleConnections();
		Connection oracleClient = conn.getOracleClient();

		String sql = "SELECT * FROM CT6042 WHERE USERNAME='" + doc.get("Username") + "' AND PASSWORD='" + doc.get("Password") + "'";
		ret = executeLoggingQuery(oracleClient, sql, doc.get("Username").toString(), doc.get("Password").toString());
		return ret;
	}

	private boolean executeLoggingQuery(Connection oracleClient, String sql, String uname, String pword)
	{
		boolean ret = false;
		try (Statement statement = oracleClient.createStatement())
		{
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next())
			{
				String username = resultSet.getString("Username");
				String password = resultSet.getString("Password");
				if(uname.equalsIgnoreCase(username) && pword.equals(password))
				{
					ret = true;
					break;
				}
			}
		}
		catch(Exception e)
		{
			LOG.error("Query failure, using query: " + sql, e);
		}
		return ret;
	}

	public String sensitiveData(Document doc)
	{
		//Returns True or False on if the user would be successful logging in.
		LOG.info("Attempting login (Sensitive Data Exposure) for User: " + doc.get("Username"));
		String ret;
		setOracleDriver();
		AbstractOracleConnections conn = new AbstractOracleConnections();
		Connection oracleClient = conn.getOracleClient();

		String sql = "SELECT * FROM CT6042 WHERE USERNAME='" + doc.get("Username") + "' AND PASSWORD='" + doc.get("Password") + "'";
		ret = executeSensitiveDataExposureQuery(oracleClient, sql, doc.get("Username").toString(), doc.get("Password").toString());
		return ret;
	}

	private String executeSensitiveDataExposureQuery(Connection oracleClient, String sql, String uname, String pword)
	{
		String ret = null;
		try (Statement statement = oracleClient.createStatement())
		{
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next())
			{
				String username = resultSet.getString("Username");
				String password = resultSet.getString("Password");
				String name = resultSet.getString("Name");
				String info = resultSet.getString("Information");
				if(uname.equalsIgnoreCase(username) && pword.equals(password))
				{
					LOG.info("Successful attempt for User: " + username + "with Name: " + name + " at " + System.currentTimeMillis());
					LOG.info("Information found: " + info);
					ret = info;
					break;
				}
			}
		}
		catch(Exception e)
		{
			LOG.error("Query failure, using query: " + sql, e);
		}
		return ret;
	}
}
