package edu.usach.lgbt.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the stadistic database table.
 * 
 */
@Entity
@Table(name="stadistic")
@NamedQuery(name="Stadistic.findAll", query="SELECT s FROM Stadistic s")
public class Stadistic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_stadistic")
	private int idStadistic;

	@Column(name="contingency_stadistic")
	private int contingencyStadistic;

	@Column(name="date_stadistic")
	private Timestamp dateStadistic;

	@Column(name="name_stadistic")
	private String nameStadistic;

	@Column(name="negative_stadistic")
	private int negativeStadistic;

	@Column(name="positive_stadistic")
	private int positiveStadistic;

	//bi-directional many-to-one association to Region
	@ManyToOne
	@JoinColumn(name="idRegion")
	private Region region;

	public Stadistic() {
	}

	public int getIdStadistic() {
		return this.idStadistic;
	}

	public void setIdStadistic(int idStadistic) {
		this.idStadistic = idStadistic;
	}

	public int getContingencyStadistic() {
		return this.contingencyStadistic;
	}

	public void setContingencyStadistic(int contingencyStadistic) {
		this.contingencyStadistic = contingencyStadistic;
	}

	public Timestamp getDateStadistic() {
		return this.dateStadistic;
	}

	public void setDateStadistic(Timestamp dateStadistic) {
		this.dateStadistic = dateStadistic;
	}

	public String getNameStadistic() {
		return this.nameStadistic;
	}

	public void setNameStadistic(String nameStadistic) {
		this.nameStadistic = nameStadistic;
	}

	public int getNegativeStadistic() {
		return this.negativeStadistic;
	}

	public void setNegativeStadistic(int negativeStadistic) {
		this.negativeStadistic = negativeStadistic;
	}

	public int getPositiveStadistic() {
		return this.positiveStadistic;
	}

	public void setPositiveStadistic(int positiveStadistic) {
		this.positiveStadistic = positiveStadistic;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}