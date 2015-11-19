package model;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		HibernateHelper hibernate = new HibernateHelper();
		Scanner in = new Scanner(System.in);
		boolean funcionar = true;
		int opc = -1, opc2;
		String parametro, parametro1, parametro2, parametro3, parametro4, parametro5;

		while (funcionar) {

			System.out.println("Introduzca la opción elegida: ");
			System.out.println("1- Mostrar todos los directores.");
			System.out.println("2- Mostrar películas de un director.");
			System.out.println("3- Insertar un director nuevo.");
			System.out.println("4- Insertar una película nueva.");
			System.out.println("5- Borrar una película por codigo.");
			System.out.println("6- Borrar una película por nombre.");
			System.out.println("7- Cambiar nombre de un director.");
			System.out.println("8- Salir.");
			opc = in.nextInt();
			in.nextLine();
			switch (opc) {
			case 1:
				hibernate.showDirectores();
				break;
			case 2:
				System.out.println("Introduzca el código del director: ");
				parametro = in.next();
				hibernate.showPeliculasdeDirector(parametro);
				break;
			case 3:
				System.out.println("Introduzca nuevo Director:");
				System.out.print("Codigo: ");
				parametro1 = in.next();
				in.nextLine();
				System.out.println("param " + parametro1);
				System.out.print("Nombre: ");
				parametro2 = in.nextLine();
				System.out.println("param " + parametro2);
				System.out.print("Nacionalidad: ");
				parametro3 = in.next();
				// in.next();
				System.out.println("param " + parametro3);
				System.out.print("Año de Nacimiento: ");
				parametro4 = in.next();
				// in.next();
				System.out.println("param " + parametro4);
				hibernate.addDirector(parametro1, parametro2, parametro3,
						parametro4);
				break;
			case 4:
				System.out.println("Introduzca los datos de la película: ");
				System.out.print("Codigo: ");
				parametro1 = in.next();
				in.nextLine();
				System.out.print("Título: ");
				parametro2 = in.nextLine();
				System.out.print("Género: ");
				parametro3 = in.nextLine();
				System.out.print("Duración: ");
				parametro4 = in.next();
				in.nextLine();
				System.out.print("Nombre del Director: ");
				parametro5 = in.nextLine();
				hibernate.addPelicula(parametro1, parametro5, parametro2,
						parametro3, parametro4);
				break;
			case 5:
				System.out
						.println("Introduzca el código de la película a borrar: ");
				opc2 = in.nextInt();
				hibernate.deletePelicula(opc2);
				break;
			case 6:
				System.out
						.println("Introduzca el nombre de la película a borrar:");
				parametro = in.nextLine();
				hibernate.deletePelicula(parametro);
				break;
			case 7:
				System.out
						.println("Introduzca el código del director a modificar y el nombre que desea poner: ");
				System.out.print("Codigo: ");
				opc2 = in.nextInt();
				in.nextLine();
				System.out.println("Nombre: ");
				parametro = in.nextLine();
				hibernate.updateNombreDirector(opc2, parametro);
				break;
			case 8:
				funcionar = false;
				hibernate.cerrarHibernate();
				break;
			default:
				System.out.println("Introduzca una opción válida...");
				break;
			}
		}
	}

}
