package oracle;

import beans.UserBean;
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
		UserBean bean = new UserBean(doc);
		LOG.info("Attempting to retrieve DB Data for User: " + bean.getUsername());
		setOracleDriver();
		AbstractOracleConnections conn = new AbstractOracleConnections();
		Connection oracleClient = conn.getOracleClient();
		String sql = "SELECT * FROM CT6042 WHERE USERNAME='" + bean.getUsername() + "'";
		return executeInjectionQuery(oracleClient, sql);
	}

	private String executeInjectionQuery(Connection oracleClient, String sql)
	{
		LOG.info("Executing DB Query for Injection Page");
		StringBuilder dbData = new StringBuilder();
		try (Statement statement = oracleClient.createStatement())
		{
			ResultSet resultSet = statement.executeQuery(sql);
			int unameMatchCount = 0;
			while (resultSet.next())
			{
				String username = resultSet.getString("Username");
				if(username != null)
				{
					LOG.info("Found Matching Username");
					unameMatchCount++;
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
			//Assert DB has not been compromised (Only 1 username should be able to be retrieved from the simple User form)
			if(unameMatchCount == 0)
			{
				LOG.info("Unable to find information for User");
			}
			else if(unameMatchCount > 1)
			{
				LOG.error("Multiple Users have been found. This may be due to a DB error, but for safety (in case of injection attack) no User information will be returned.");
				dbData = new StringBuilder();
				dbData.append("An error occurred potentially due to injection attack.");
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
		LOG.info("Attempting Logging Query from Form Submission.");
		//Returns True or False on if the user would be successful logging in.
		boolean ret;
		setOracleDriver();
		AbstractOracleConnections conn = new AbstractOracleConnections();
		Connection oracleClient = conn.getOracleClient();

		UserBean bean = new UserBean(doc);
		String sql = "SELECT * FROM CT6042 WHERE USERNAME='" + bean.getUsername() + "' AND PASSWORD='" + bean.getPassword() + "'";
		ret = executeLoggingQuery(oracleClient, sql, bean.getUsername(), bean.getPassword());
		return ret;
	}

	private boolean executeLoggingQuery(Connection oracleClient, String sql, String uname, String pword)
	{
		LOG.info("Attempting Logging Query to DB");
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
					LOG.info("User information has been found, returning to alert User on success");
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
		UserBean bean = new UserBean(doc);
		LOG.info("Attempting login (Sensitive Data Exposure) for User: " + bean.getUsername());
		String ret;
		setOracleDriver();
		AbstractOracleConnections conn = new AbstractOracleConnections();
		Connection oracleClient = conn.getOracleClient();

		String sql = "SELECT * FROM CT6042 WHERE USERNAME='" + bean.getUsername() + "' AND PASSWORD='" + bean.getPassword() + "'";
		ret = executeSensitiveDataExposureQuery(oracleClient, sql, bean.getUsername(), bean.getPassword());
		return ret;
	}

	private String executeSensitiveDataExposureQuery(Connection oracleClient, String sql, String uname, String pword)
	{
		LOG.info("Attempting Query to retrieve User Information.");
		String ret = null;
		try (Statement statement = oracleClient.createStatement())
		{
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next())
			{
				String username = resultSet.getString("Username");
				String password = resultSet.getString("Password");
				String info = resultSet.getString("Information");
				LOG.info("login attempt for User: " + username + " at " + System.currentTimeMillis());
				if(uname.equalsIgnoreCase(username) && pword.equals(password))
				{
					LOG.info("Successful Login");
					/*Left in to show BAD practice. NO USER INFORMATION SHOULD BE SHARED VIA LOGS! (Use UserKeys/User IDS if you need to log specific user operations) */
					//LOG.info("Information found: " + info);

					/*Better practice*/
					ret = info;
					break;
				}
				else
				{
					LOG.warn("Failed Login attempt by UserKey: " + username + " at " + System.currentTimeMillis() + ". If this keeps happening, please contact your Administrator to ensure this was an accidental login failure");
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
