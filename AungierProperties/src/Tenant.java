/*
 * Lucy Gormley
 */
import java.io.Serializable;
import java.text.NumberFormat;

public class Tenant implements Serializable 
{
	private String name;
	private String phoneNumber;
	private String referee;
	private String emailAddress;
	private double maxRent;

	public Tenant(String name, String phoneNumber, String referee, String emailAddress, double maxRent) //constructor for tenant objects
	{
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.referee = referee;
		this.emailAddress = emailAddress;
		this.maxRent = maxRent;
	}

	public String getName() 
	{
		return this.name;
	}

	public String getPhoneNumber() 
	{
		return this.phoneNumber;
	}

	public String getReferee()
	{
		return this.referee;
	}

	public String getEmailAddress()
	{
		return this.emailAddress;
	}

	public double getMaxRent()
	{
		return this.maxRent;
	}

	public void setMaxRent(double newMaxRent)
	{
		this.maxRent = this.maxRent + newMaxRent;
	}

	public String toString() //returns tenant details
	{
		String details = "Name: " + this.name + "\tPhone Number: " + this.phoneNumber + "\tReferee: " + this.referee + "\nEmail Address: " + this.emailAddress;
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		details = details + "\tMaximum rent tenant can pay per month: " + nf.format(maxRent);
		return details;
	}

}
