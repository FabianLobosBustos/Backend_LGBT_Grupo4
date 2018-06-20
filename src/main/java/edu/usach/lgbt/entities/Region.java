package edu.usach.lgbt.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;


/**
 * The persistent class for the Region database table.
 * 
 */
@Entity
@Table(name="region")
@NamedQuery(name="Region.findAll", query="SELECT r FROM Region r")
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idRegion;

	@Column(name="name_region")
	private String nameRegion;

	//bi-directional many-to-one association to Stadistic
	@OneToMany(mappedBy="region")
	@JsonManagedReference
	private List<Stadistic> stadistics;

	public Region() {
	}

	public int getIdRegion() {
		return this.idRegion;
	}

	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}

	public String getNameRegion() {
		return this.nameRegion;
	}

	public void setNameRegion(String nameRegion) {
		this.nameRegion = nameRegion;
	}

	public List<Stadistic> getStadistics() {
		return this.stadistics;
	}

	public void setStadistics(List<Stadistic> stadistics) {
		this.stadistics = stadistics;
	}

	public Stadistic addStadistic(Stadistic stadistic) {
		getStadistics().add(stadistic);
		stadistic.setRegion(this);

		return stadistic;
	}

	public Stadistic removeStadistic(Stadistic stadistic) {
		getStadistics().remove(stadistic);
		stadistic.setRegion(null);

		return stadistic;
	}

}