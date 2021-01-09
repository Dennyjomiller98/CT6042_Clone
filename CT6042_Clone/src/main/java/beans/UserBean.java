package beans;

import java.io.Serializable;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import org.bson.Document;

@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class UserBean implements Serializable
{
	private String fPassword;
	private String fName;
	private String fInformation;
	private String fUsername;

	public UserBean()
	{
		//Empty no-args constructor
	}

	public UserBean(Document doc)
	{
		fName = String.valueOf(doc.get("Name"));
		fInformation = String.valueOf(doc.get("Information"));
		fUsername = String.valueOf(doc.get("Username"));
		fPassword = String.valueOf(doc.get("Password"));
	}

	public String getName()
	{
		return fName;
	}
	public void setName(String name)
	{
		fName = name;
	}

	public String getInformation()
	{
		return fInformation;
	}
	public void setInformation(String information)
	{
		fInformation = information;
	}

	public String getUsername()
	{
		return fUsername;
	}
	public void setUsername(String username)
	{
		fUsername = username;
	}

	public String getPassword()
	{
		return fPassword;
	}
	public void setPassword(String password)
	{
		fPassword = password;
	}
}
