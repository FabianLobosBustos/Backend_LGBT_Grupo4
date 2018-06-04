package edu.usach.lgbt.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tuser database table.
 * 
 */
@Entity
@Table(name="tuser")
@NamedQuery(name="Tuser.findAll", query="SELECT t FROM Tuser t")
public class Tuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tuser")
	private long idTuser;

	@Column(name="name_tuser")
	private String nameTuser;

	@Column(name="screenname_tuser")
	private String screennameTuser;

	@Column(name="relevance_tuser")
	private int relevanceTuser;


	public Tuser() {
	}


	public long getIdTuser() {
		return idTuser;
	}


	public void setIdTuser(long idTuser) {
		this.idTuser = idTuser;
	}


	public String getNameTuser() {
		return nameTuser;
	}


	public void setNameTuser(String nameTuser) {
		this.nameTuser = nameTuser;
	}


	public String getScreennameTuser() {
		return screennameTuser;
	}


	public void setScreennameTuser(String screennameTuser) {
		this.screennameTuser = screennameTuser;
	}


	public int getRelevanceTuser() {
		return relevanceTuser;
	}


	public void setRelevanceTuser(int relevanceTuser) {
		this.relevanceTuser = relevanceTuser;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}