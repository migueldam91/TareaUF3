package model;

// Generated 18-nov-2015 13:09:32 by Hibernate Tools 3.4.0.CR1

/**
 * Pelicula generated by hbm2java
 */
public class Pelicula implements java.io.Serializable {

	private String codigo;
	private Director director;
	private String titulo;
	private String genero;
	private String duracion;

	public Pelicula() {
	}

	public Pelicula(String codigo) {
		this.codigo = codigo;
	}

	public Pelicula(String codigo, Director director, String titulo,
			String genero, String duracion) {
		this.codigo = codigo;
		this.director = director;
		this.titulo = titulo;
		this.genero = genero;
		this.duracion = duracion;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Director getDirector() {
		return this.director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDuracion() {
		return this.duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

}
