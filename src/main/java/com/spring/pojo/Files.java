package com.spring.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "reports")
@NoArgsConstructor
public class Files  implements Serializable{
	
  /**
	 * 
	 */
	private static final long serialVersionUID = 4890622458192989085L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  private String type;

  @Lob
  private byte[] data;

  public Files() {
  }

  public Files(String name, String type, byte[] data) {
    this.name = name;
    this.type = type;
    this.data = data;
  }
  public void setId(long id) {
	  this.id=id;
  }
  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

public Files(long id, String name, String type, byte[] data) {
	this.id = id;
	this.name = name;
	this.type = type;
	this.data = data;
}

}
