package info.projekt.database;
// Generated 2017-01-07 11:30:43 by Hibernate Tools 5.2.0.Beta1

import java.util.HashSet;
import java.util.Set;

/**
 * Shippers generated by hbm2java
 */
@SuppressWarnings("serial")
public class Shippers implements java.io.Serializable {

	private Integer shipperId;
	private String companyName;
	private String phone;
	private Set<Orders> orderses = new HashSet<Orders>(0);
	private Set<Orders> orderses_1 = new HashSet<Orders>(0);

	public Shippers() {
	}

	public Shippers(String companyName, String phone) {
		this.companyName = companyName;
		this.phone = phone;
	}

	public Shippers(String companyName, String phone, Set<Orders> orderses, Set<Orders> orderses_1) {
		this.companyName = companyName;
		this.phone = phone;
		this.orderses = orderses;
		this.orderses_1 = orderses_1;
	}

	public Integer getShipperId() {
		return this.shipperId;
	}

	public void setShipperId(Integer shipperId) {
		this.shipperId = shipperId;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<Orders> getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set<Orders> orderses) {
		this.orderses = orderses;
	}

	public Set<Orders> getOrderses_1() {
		return this.orderses_1;
	}

	public void setOrderses_1(Set<Orders> orderses_1) {
		this.orderses_1 = orderses_1;
	}

}
