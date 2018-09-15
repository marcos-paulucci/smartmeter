package uk.co.scottishpower.smartmeter.smartreads;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Smartread.java - The smart read class, which is the entity stored in the in-memory database.
 * @author  Marcos Paulucci
 */

@Entity
public class Smartread {
	@Id
	@GeneratedValue
	private Long ACCOUNT_NUMBER;
	private Long GAS_ID;
	private Long ELEC_ID;
	private double ELEC_SMART_READ;
	private double GAS_SMART_READ;


	public Smartread() {
		super();
	}

	public Long getACCOUNT_NUMBER() {
		return ACCOUNT_NUMBER;
	}

	public void setACCOUNT_NUMBER(Long ACCOUNT_NUMBER) {
		this.ACCOUNT_NUMBER = ACCOUNT_NUMBER;
	}

	public Long getGAS_ID() {
		return GAS_ID;
	}

	public void setGAS_ID(Long GAS_ID) {
		this.GAS_ID = GAS_ID;
	}

	public Long getELEC_ID() {
		return ELEC_ID;
	}

	public void setELEC_ID(Long ELEC_ID) {
		this.ELEC_ID = ELEC_ID;
	}

	public double getELEC_SMART_READ() {
		return ELEC_SMART_READ;
	}

	public void setELEC_SMART_READ(double ELEC_SMART_READ) {
		this.ELEC_SMART_READ = ELEC_SMART_READ;
	}

	public double getGAS_SMART_READ() {
		return GAS_SMART_READ;
	}

	public void setGAS_SMART_READ(double GAS_SMART_READ) {
		this.GAS_SMART_READ = GAS_SMART_READ;
	}
}
