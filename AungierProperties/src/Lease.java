/*
 * Lucy Gormley
 */
import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Lease implements Serializable
{
	private String leaseId;
	private LocalDate startDate;
	private LocalDate endDate;
	private Apartment apartment;
	private Tenant tenant;
	private int leaseLength;
	private double leaseRent;

	public Lease(Tenant tenant, String leaseId, Apartment apartment, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear)//Constructor for lease object
	{
		this.leaseId = leaseId;
		this.startDate = LocalDate.of(startYear, startMonth, startDay);
		this.endDate = LocalDate.of(endYear, endMonth, endDay);
		this.apartment = apartment;
		this.tenant = tenant;
	}
	public String getLeaseId()
	{
		return this.leaseId;
	}

	public LocalDate getStartDate()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MMM/yyyy E");
		dtf.format(startDate);
		return this.startDate;
	}

	public LocalDate getEndDate()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MMM/yyyy E");
		dtf.format(endDate);
		return this.endDate;
	}

	public Tenant getTenant()
	{
		return this.tenant;
	}

	public Apartment getApartment()
	{
		return this.apartment;
	}

	public String toString()
	{
		String details = "***LEASE DETAILS***\nLease ID: " + this.leaseId;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MMM/yyyy E");
		details = details + "\nStart Date: " + dtf.format(startDate) + "\tEnd Date: " + dtf.format(endDate);
		details = details + "\nAPARTMENT DETAILS: \n" + apartment.toString() + "\nTENANT DEATILS: \n" + tenant.toString();
		return details;
	}
}