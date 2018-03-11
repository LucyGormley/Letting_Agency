/*
 * Lucy Gormley
 */
import java.io.Serializable;
import java.text.NumberFormat;

public class Apartment implements Serializable
{
	private String aptNumber;
	private String address;
	private double rent;
	private int bedrooms;
	private int bathrooms;
	private boolean balcony;
	private boolean available;

	public Apartment(String aptNumber, String address, double rent, int bedrooms, int bathrooms, boolean balcony)//Constructor for apartment object
	{
		this.aptNumber = aptNumber;
		this.address = address;
		this.rent = rent;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.balcony = balcony;
		this.available = true;
	}

	public String toString()// returns apartment details
	{
		String details = "APARTMENT DETAILS: \nApartment Number: " + this.aptNumber + "\tAddress: "  + this.address + "\nNumber of Bedrooms: " + this.bedrooms + "\tNumber of Bathrooms: " + this.bathrooms;
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		details = details + "\nMonthly Rent: " + nf.format(this.rent);
		if(this.balcony == true)
		{
			details = details + "\nThis apartment has a balcony.";
		}
		else
		{
			details = details + ("\nThis apartment does not have a balcony.");
		}
		if(this.available == true)
		{
			details = details + ("\nThis apartment is available to rent.");
		}
		else
		{
			details = details + ("\nThis apartment is not available to rent.");
		}
		return details;
	}
	
	public boolean notAvailable()// changes status to not available
	{
		this.available = false;
		return false;
	}
	
	public boolean nowAvailable()// changes status to available
	{
		this.available = true;
		return true;
	}

	public String getAptNumber()
	{
		return this.aptNumber;
	}

	public String getAddress()
	{
		return this.address;
	}

	public double getRent()
	{
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		nf.format(this.rent);
		return this.rent;
	}

	public int getBedrooms()
	{
		return this.bedrooms;
	}

	public int getBathrooms()
	{
		return this.bathrooms;
	}

	public boolean hasBalcony()
	{
		if(balcony)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean isAvailable()
	{
		if(available)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
}

