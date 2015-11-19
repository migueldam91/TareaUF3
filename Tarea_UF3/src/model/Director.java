package model;

// Generated 18-nov-2015 13:09:32 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Director generated by hbm2java
 */
public class Director implements java.io.Serializable {

	private String codigo;
	private String nombre;
	private String nacionalidad;
	private String anionacimiento;
	private Set peliculas = new HashSet(0);

	public Director() {
	}

	public Director(String codigo) {
		this.codigo = codigo;
	}

	public Director(String codigo, String nombre, String nacionalidad,
			String anionacimiento, Set peliculas) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.anionacimiento = anionacimiento;
		this.peliculas = peliculas;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getAnionacimiento() {
		return this.anionacimiento;
	}

	public void setAnionacimiento(String anionacimiento) {
		this.anionacimiento = anionacimiento;
	}

	public Set getPeliculas() {
		return this.peliculas;
	}

	public void setPeliculas(Set peliculas) {
		this.peliculas = peliculas;
	}

}