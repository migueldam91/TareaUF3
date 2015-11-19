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
	Session session;

	public HibernateHelper() {
		sesion = SessionFactoryUtil.getSessionFactory();
		session = sesion.openSession();
	}

	public void addDirector(String id, String name, String nacionalidad, String anionacimiento) {
		Transaction tx = session.beginTransaction();
		Director director = new Director(id, name, nacionalidad, anionacimiento, new HashSet<Pelicula>(0));
		session.save(director);
		System.out.println("!Director añadido! \n");
		tx.commit();

	}

	public Director getDirector(String nomDirector) {

		Director director = (Director) session.createQuery("from Director where nombre =?").setString(0, nomDirector)
				.uniqueResult();

		return director;
	}

	public void addPelicula(String codigo, String nombredirector, String titulo, String genero, String duracion) {
		Transaction tx = session.beginTransaction();

		Pelicula pelicula = new Pelicula(codigo, getDirector(nombredirector), titulo, genero, duracion);
		session.save(pelicula);
		System.out.println("!Pelicula añadida!\n");
		tx.commit();
	}

	public void showPeliculasdeDirector(String id) {
		Director director = null;
		director = (Director) session.get(Director.class, id);
		if (director != null) {
			System.out.println("**** Nombre Director *****");
			System.out.println("Director " + director.getNombre());
			Set<Pelicula> listaemp = director.getPeliculas();
			
			if (listaemp.size() == 0) {
				System.out.println(director.getNombre() + " no tiene peliculas");
			} else {
				
				Iterator<Pelicula> it = listaemp.iterator();
				System.out.println("Lista de Peliculas");

				while (it.hasNext()) {
					Pelicula pelicula = new Pelicula();
					pelicula = it.next();
					System.out.println("codigo: " + pelicula.getCodigo() + ", titulo: " + pelicula.getTitulo()
							+ ", genero: " + pelicula.getGenero() + ", duracion: " + pelicula.getDuracion()+"\n");
				}
			}
		} else {
			System.out.println("no existe ese Director");
		}
	}

	public void deletePelicula(int codigo) {
		Transaction tx = session.beginTransaction();
		Pelicula pelicula = (Pelicula) session.get(Pelicula.class, String.valueOf(codigo));
		if (pelicula != null) {
			session.delete(pelicula);
			System.out.println("Pelicula borrada\n");
			tx.commit();
		} else {
			System.out.println("No existe la pelicula con codigo: " + codigo +"\n");
		}
	}

	public void deletePelicula(String titulo) {
		Transaction tx = session.beginTransaction();
		Pelicula pelicula = (Pelicula) session.createQuery("from Pelicula where titulo =?").setString(0, titulo)
				.uniqueResult();
		if (pelicula != null) {
			session.delete(pelicula);
			System.out.println("Pelicula borrada\n");
			tx.commit();
		} else {
			System.out.println("No existe la pelicula con titulo: " + titulo+"\n");
		}
	}

	public void updateNombreDirector(int codigo, String nombreNuevo) {
		Transaction tx = session.beginTransaction();
		Director director = (Director) session.get(Director.class, String.valueOf(codigo));

		if (director == null) {
			System.out.println("No existe tal director");
		} else {
			director.setNombre(nombreNuevo);
			session.update(director);
		}
		System.out.println("Cambio de nombre " + director.getNombre());

		tx.commit();

	}

	public void showDirectores() {
		Director director = new Director();
		Query q = session.createQuery("from Director ORDER BY cast(codigo as integer)");
		q.setFetchSize(10);
		Iterator<Director> iter = q.iterate();
		while (iter.hasNext()) {
			director = (Director) iter.next();
			System.out.println(director.getCodigo() + " " + director.getNombre() + " " + director.getAnionacimiento()
					+ " " + director.getNacionalidad() + " \tNumero de peliculas: " + director.getPeliculas().size()
					);
		}
		System.out.println("*********  *********");
	}

	public void cerrarHibernate() {
		session.close();
		System.exit(0);
	}

}
