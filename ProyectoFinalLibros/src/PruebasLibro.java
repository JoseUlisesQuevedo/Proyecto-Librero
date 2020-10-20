/*
 * Jose Ulises Quevedo
 * Diciembre 2019
 * Prueba los métodos de la clase libro
 */

public class PruebasLibro {
	
	public static void main(String args[]) {
		
		Libro libro1, libro2, libro3;
		
		libro1 = new Libro("Harry Potter", "Fantasía", "JK Rowling", 'F');
		libro2 = new Libro("Harry Potter 2", "Fantasía", "JK Rowling", 'D',9.8,"");
		libro3 = new Libro("Harry Potter 3", "Fantasía", "JK Rowling", 'F',7, "Malon comparado con el 1 y el 2");
		
		System.out.println(libro1);
		System.out.println(libro2);
		System.out.println(libro3);
		
		
		libro1.prestar();
		System.out.println(libro1.getCitas());
		
		libro2.agregarCita("Y el mundo será de los valientes", 32);
		System.out.println(libro2.getCitas());
		
		System.out.println(libro1);
		System.out.println(libro2);
		
		libro1.regresar();
		libro1.leer();
		libro1.calificar(8);
		libro1.setOpinion("Me gustó más que el 3o, pero sigue siendo mejor el 1o");
		
		System.out.println(libro1);
		
	
		
		

		
		
	}

}
