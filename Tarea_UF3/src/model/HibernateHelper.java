package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateHelper {
	private SessionFactory sesion;
	List<Director> directores = null;

	public HibernateHelper() {
		sesion = SessionFactoryUtil.getSessionFactory();
	}

	public void addDirector(String id, String name, String nacionalidad,
			String anionacimiento) {
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		Director director = new Director(id, name, nacionalidad,
				anionacimiento, new HashSet<Pelicula>(0));
		session.save(director);
		System.out.println("!Director añadido!");
		tx.commit();
		session.close();
		// System.exit(0);
	}

	public List<Director> getDirectores() {
		// abrimos sesion
		Session session = sesion.openSession();
		// Creamos lista
		List<Director> deps;
		// crea consulta
		Query query = session.createQuery("from Director");
		// almacena el resultado
		deps = query.list();
		// cierra sesion

		session.close();

		return deps;
	}

	public Director getDirector(String nomDirector) {
		Session session = sesion.openSession();
		Director dir = (Director) session
				.createQuery("from Director where nombre =?")
				.setString(0, nomDirector).uniqueResult();

		System.out.println("Nombre director de metodo getDirector "+ dir.getNombre());
		session.close();
		return dir;
	}

	public void addPelicula(String codigo, String nombredirector,
			String titulo, String genero, String duracion) {
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		/*
		 * directores = getDirectores(); Director director = null; Director
		 * director1 = null;
		 * 
		 * Iterator<Director> it = directores.iterator();
		 * System.out.println("Lista de Directores"); // Iterador recorre set
		 * para buscar director por nombre while (it.hasNext()) { director =
		 * it.next(); if (director.getNombre().equalsIgnoreCase(nombredirector))
		 * { System.out.println("nombre: " + director.getNombre() + " codigo " +
		 * director.getCodigo()); director1 = director; } }
		 */

		Pelicula pelicula = new Pelicula(codigo, getDirector(nombredirector), titulo, genero,
				duracion);
		session.save(pelicula);
		System.out.println("!Pelicula añadida!");
		tx.commit();
		session.close();
		// System.exit(0);
	}

	/*
	 * public Departamento getDepartamento(int id) {
	 * 
	 * Session session = sesion.openSession(); Departamento dep = null; if (id!=
	 * -1) { dep = (Departamento) session.get(Departamento.class, id); } if (dep
	 * == null) { dep = new Departamento(); }
	 * 
	 * session.close();
	 * 
	 * return dep; }
	 */
	public void showPeliculasdeDirector(String id) {

		Session session = sesion.openSession();
		Director dep = null;
		dep = (Director) session.get(Director.class, id);
		if (dep != null) {
			System.out.println("**** Nombre Director *****");
			System.out.println("Director " + dep.getNombre());
			Set<Pelicula> listaemp = dep.getPeliculas();
			if (listaemp.size() == 0) {
				System.out.println(dep.getNombre() + " no tiene peliculas");
			} else {
				// Copiamos la lista a un iterador
				Iterator<Pelicula> it = listaemp.iterator();
				System.out.println("Lista de Peliculas");
				// Iterador recorre set para mostrar empleados
				while (it.hasNext()) {
					Pelicula emp = new Pelicula();
					emp = it.next();
					System.out.println("codigo: " + emp.getCodigo()
							+ ", titulo: " + emp.getTitulo() + ", genero: "
							+ emp.getGenero() + ", duracion: "
							+ emp.getDuracion());
				}
			}
		} else {
			System.out.println("no existe ese Director");
		}

		session.close();
		// System.exit(0);
	}

	public void deletePelicula(int codigo) {
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		Pelicula dep = (Pelicula) session.get(Pelicula.class,
				String.valueOf(codigo));
		if (dep != null) {
			session.delete(dep);
			System.out.println("Pelicula borrada");
			tx.commit();
		} else {
			System.out.println("No existe la pelicula con codigo: " + codigo);
		}

		session.close();
	}

	public void deletePelicula(String titulo) {
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		Pelicula dep = (Pelicula) session.get(Pelicula.class, titulo);
		if (dep != null) {
			session.delete(dep);
			System.out.println("Pelicula borrada");
			tx.commit();
		} else {
			System.out.println("No existe la pelicula con titulo: " + titulo);
		}

		session.close();
	}

	public void updateNombreDirector(int codigo, String nombreNuevo) {
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		Director dep = (Director) session.get(Director.class,
				String.valueOf(codigo));

		if (dep == null) {
			System.out.println("No existe tal director");
		} else {
			dep.setNombre(nombreNuevo);
			session.update(dep);
		}
		System.out.println("Cambio de nombre " + dep.getNombre());

		tx.commit();
		session.close();
		// System.exit(0);
	}

	/*
	 * public List<Departamento> getDepartment2() { // abrimos sesion Session
	 * session = sesion.openSession(); // Creamos lista List<Departamento> deps;
	 * // crea consulta Query query = session.createQuery("from Departamento");
	 * // almacena el resultado deps = query.list(); // cierra sesion
	 * session.close();
	 * 
	 * return deps; }
	 */

	public void showDeps() {
		Session session = sesion.openSession();
		Director dep = new Director();
		Query q = session.createQuery("from Director");
		q.setFetchSize(10);
		Iterator iter = q.iterate();
		while (iter.hasNext()) {
			dep = (Director) iter.next();
			System.out.println(dep.getCodigo() + " " + dep.getNombre() + " "
					+ dep.getAnionacimiento() + " " + dep.getNacionalidad()
					+ dep.getPeliculas());
		}
		session.close();
	}

	public void cerrarHibernate() {
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		session.close();
		System.exit(0);
	}
	/*
	 * public void showUniqueDep(String nomDept){ Session session =
	 * sesion.openSession(); Departamento dep = (Departamento)
	 * session.createQuery("from Departamento where dnombre =?") .setString(0,
	 * nomDept).uniqueResult();
	 * 
	 * System.out.println(dep.getNumDept()+" "+dep.getDnombre()+" "
	 * +dep.getCiudad()); session.close(); } //Dummy method public void
	 * averageSalario(){ Session session = sesion.openSession(); Query cons =
	 * session.createQuery("select avg(salario) from Empleado"); Double media =
	 * (Double) cons.uniqueResult(); System.out.println("Media:"+media);
	 * session.close(); }
	 */

	// proyecto con todos estos métodos y una interfaz gráfica.

	// Falta insert,update,delete!!!! lo ven viernes 13

}
