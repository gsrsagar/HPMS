package com.spring.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="Tests")
@NoArgsConstructor
@AllArgsConstructor
public class Tests implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2299886008406493973L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long testId;
	
	private String testName;
	
	private long cost;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "labId", nullable = false)
	private Laboratory lab;
	

	public long getTestId() {
		return testId;
	}

	public void setTestId(long testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public long getCost() {
		return cost;
	}

	public void setCost(long cost) {
		this.cost = cost;
	}

	public Laboratory getLab() {
		return lab;
	}

	public void setLab(Laboratory lab) {
		this.lab = lab;
	}
	@Override
	public String toString() {
		return "Tests [testId=" + testId + ", testName=" + testName + ", cost=" + cost + ", lab=" + lab +"]";
	}

	public Tests(long testId, String testName, long cost, Laboratory lab) {
		this.testId = testId;
		this.testName = testName;
		this.cost = cost;
		this.lab = lab;
	}
	
	public Tests() {
		
	}
	
}
