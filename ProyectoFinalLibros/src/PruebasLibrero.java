/*
 * Jose Ulises Quevedo
 * Diciembre 2019
 * Prueba los métodos de la clase Librero
 */

public class PruebasLibrero {

	public static void main(String[] args) {
		
		Librero miLibrero = new Librero("Uli");
		
		if(miLibrero.altaLibro("Harry Potter", "Fantasía", "JK Rowling", 'F'))
			System.out.println("Alta exitosa!");
		if(miLibrero.altaLibro("Harry Potter 2", "Fantasía", "JK Rowling", 'D',9.8,""));
			System.out.println("Alta exitosa!");
		if(miLibrero.altaLibro("Harry Potter 3", "Novela", "JK Rowling", 'F',7, "Malon comparado con el 1 y el 2"))
			System.out.println("Alta exitosa!");
		if(miLibrero.altaLibro("Percy Jackson", "Fantasía", "Manoquero", 'F',9,""))
			System.out.println("Alta exitosa!");
		if(miLibrero.altaLibro("La caída de los gigantes", "Historia", "Ken Follet", 'D',9.8,"Muy bueno"));
			System.out.println("Alta exitosa!");
		if(miLibrero.altaLibro("Entre tonos de gris", "Novela", "Ruta Septis", 'F',9, ""))
			System.out.println("Alta exitosa!");
		if(miLibrero.altaLibro("Cosmos", "Ciencia", "Carl Sagan", 'F'))
			System.out.println("Alta exitosa!");
		if(miLibrero.altaLibro("Biologia 1", "Ciencia", "IB", 'D',10,""));
			System.out.println("Alta exitosa!");
		if(miLibrero.altaLibro("Norwegian Wood", "Novela", "Haruki Murakami", 'F',6.1, "Meh"))
			System.out.println("Alta exitosa!");
		
		
		//Buscando un libro
		System.out.println(miLibrero.getUnLibro("Harry Potter 3"));
		//Buscando un libro que no está
		System.out.println(miLibrero.getUnLibro("1984"));
		
		//Agregando cita desde librero
		miLibrero.agregarCita("Harry Potter", "Harry amaba el chocolate", 22);
		//Imprimiendo citas desde librero
		System.out.println(miLibrero.getCitas("Harry Potter"));
			
		
		//Prestando libro
		miLibrero.prestar("Harry Potter", "Mariela");
		
		System.out.println(miLibrero);
		//Regresandolo
		miLibrero.regresar("Harry Potter");
		
		//Borrando libro
		miLibrero.eliminarLibro("Harry Potter");
		
		//Leyendo, calificando y poniendo opinion desde librero
		miLibrero.leer("Cosmos");
		miLibrero.calificar("Cosmos", 9.9);
		miLibrero.setOpinion("Cosmos", "De los mejores libros que he leído");
		System.out.println(miLibrero.getUnLibro("Cosmos"));
		
		//Probando metodos por genero
		System.out.println("Libros en novela: " + miLibrero.cuantosLibrosGenero("Novela"));
		System.out.println("Libros favorito en novela: " + miLibrero.libroFavorito("Novela"));
		System.out.println("Libros leídos en novela: " + miLibrero.librosLeidos("Novela"));
		System.out.println("Libros no leídos en novela: " + miLibrero.librosNoLeidos("Novela"));
		
		//Probando metodos generales
		System.out.println("Libros leídos: " + miLibrero.librosLeidos());
		System.out.println("Libros no leídos: " + miLibrero.librosNoLeidos());
		System.out.println("Libros prestados : " + miLibrero.librosPrestados());
		System.out.println("Libro favorito: " + miLibrero.libroFavorito());
		
		//Probando filtros
		System.out.println("Libros de JK Rowling " + miLibrero.librosUnAutor("JK Rowling"));
		System.out.println("Libros con 9: " + miLibrero.librosConXCali(9));
		System.out.println("Libros con más de 8: " + miLibrero.librosConMasDeXCalif(8));

	}

}
