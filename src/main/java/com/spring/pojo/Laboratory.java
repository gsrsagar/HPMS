package com.spring.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="laboratory")
public class Laboratory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8120180689300493231L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long labId;
	private String labName;
	
	
	public long getLabId() {
		return labId;
	}
	public void setLabId(long labId) {
		this.labId = labId;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	
	public Laboratory(long labId, String labName) {
		this.labId = labId;
		this.labName = labName;
	}
	
	public Laboratory() {
	}
	@Override
	public String toString() {
		return "Laboratory [labId=" + labId + ", labName=" + labName + "]";
	}
	
	
	
	
	
}
