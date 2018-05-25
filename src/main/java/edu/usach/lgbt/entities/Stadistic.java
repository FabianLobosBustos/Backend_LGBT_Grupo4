package edu.usach.lgbt.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;



@Entity
@JsonIgnoreProperties({"stadistic"})
@Table(name="stadistic")
@NamedQuery(name="Stadistic.findAll", query="SELECT a FROM Stadistic a")
public class Stadistic{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_stadistic;

	@Column(name="name_stadistic", nullable=false, length=70)
	private String name_stadistic;

	@Column(name="positive_stadistic", nullable=false)
	private int positive_stadistic;
	
	@Column(name="negative_stadistic", nullable=false)
	private int negative_stadistic;

	@Column(name="contingency_stadistic", nullable=false)
	private int contingency_stadistic;

	@Column(name="date_stadistic", nullable=true)
	private Timestamp date_stadistic;
	
	/*
	@Column(name="id_region", nullable=true)
	private int id_region;
	*/
	
	//luego usaremos las otras partes, por ahora solo analisis
	// de sentimientos.

	
	public Stadistic() {
	}



	public int getId_stadistic() {
		return id_stadistic;
	}



	public void setId_stadistic(int id_stadistic) {
		this.id_stadistic = id_stadistic;
	}



	public String getName_stadistic() {
		return name_stadistic;
	}



	public void setName_stadistic(String name_stadistic) {
		this.name_stadistic = name_stadistic;
	}




	public Timestamp getDate_stadistic() {
		return date_stadistic;
	}



	public void setDate_stadistic(Timestamp date_stadistic) {
		this.date_stadistic = date_stadistic;
	}



	public int getPositive_stadistic() {
		return positive_stadistic;
	}



	public void setPositive_stadistic(int positive_stadistic) {
		this.positive_stadistic = positive_stadistic;
	}



	public int getNegative_stadistic() {
		return negative_stadistic;
	}



	public void setNegative_stadistic(int negative_stadistic) {
		this.negative_stadistic = negative_stadistic;
	}



	public int getContingency_stadistic() {
		return contingency_stadistic;
	}



	public void setContingency_stadistic(int contingency_stadistic) {
		this.contingency_stadistic = contingency_stadistic;
	}


	

	
}