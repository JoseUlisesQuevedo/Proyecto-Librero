/*
 * José Ulises Quevedo
 * Diciembre 2019
 * Modela a un libro con su colección de Citas
 */

import java.util.ArrayList;

public class Libro {
	
	private String titulo;
	private String genero;
	private String autor;
	private char formato;
	private Cita[] citas;
	private int citasRegistradas;
	private final int MAX_CITAS = 20;
	private boolean leido;
	private boolean prestado; 
	private double calificacion;
	private String opinion;
	
	public Libro() {
		//Constructor de set-up, crea arreglo de citas y su contador 
		//Se asume que un libro que se registra no esta prestado inicialmente
		
		citas = new Cita[MAX_CITAS];
		citasRegistradas = 0;
		this.prestado = false;
	}
	
	public Libro(String titulo) {
		//Constructor para búsquedas con manejadores Genéricos
		this.titulo = titulo;
	}
	
	public Libro(String titulo, String genero, String nombreAutor, char formato) {
		//Opcion para dar de alta un libro que no se ha leído; Solo se recibe titulo, genero y autor
		//Leído se pone como false;
		
		this();
		this.formato = formato;
		this.titulo = titulo;
		this.genero = genero;
		this.autor = nombreAutor;
		this.leido = false;		
	}
	
	/*
	 * 
	CONSTRUCTOR YA NO SE UTILIZA. SI SE DA DE ALTA UN LIBRO LEÍDO, OBLIGATORIAMENTE SE PIDE CALIFICACION Y NO SE
	OBLIA OPINIÓN. SI NO SE AGREGA OPINION, SE PONE LA OPINION COMO UN EMPTY STRING (Y SE UTILIZA EL CONSTRUCTOR 
	DE ABAJO  
	  
	 
	public Libro(String titulo, String genero, String nombreAutor, char formato, double calificacion) {
		//Opción para dar de alta un libro leído, al que no se le agrega opinión
		//Un libro leído necesariamente necesita tener una calificación
		
		this();
		this.titulo = titulo;
		this.genero = genero;
		this.formato = formato;
		this.autor = nombreAutor;
		this.leido = true;
		this.calificacion = calificacion;		
		
	}
	
	*/
	
	public Libro(String titulo, String genero, String nombreAutor, char formato, double calificacion, String opinion) {
		//Opción para dar de alta un libro leído (CALIFICACIÓN OBLIGATORIA, OPINION OPCIONAL)
		
		this();
		this.titulo = titulo;
		this.genero = genero;
		this.formato = formato;
		this.autor = nombreAutor;
		this.leido = true;
		this.calificacion = calificacion;	
		this.opinion = opinion;	
	}

	public String getTitulo() {
		return titulo;
	}

	public String getGenero() {
		return genero;
	}

	public String getAutor() {
		return autor;
	}
	
	public char getFormato() {
		return formato;
	}

	public boolean isLeido() {
		return leido;
	}
	
	public void leer() {
		leido = true;
	}

	public boolean isPrestado() {
		return prestado;
	}
	
	public void prestar() {
		
		this.prestado = true;
	}
	
	public void regresar() {
		this.prestado = false;
	}

	public double getCalificacion() {
		return calificacion;
	}
	
	//Estos dos metodos se usan para poner calificacion y opinión a un libro que no se dio de alta como leído
	
	public void calificar(double cali) {
		calificacion = cali;
	}
	
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getOpinion() {
		return opinion;
	}
	
	public boolean agregarCita(String cita, int pag) {
		boolean res = false;
		Cita nuevaCita;
		
		if(citasRegistradas < MAX_CITAS) {
			nuevaCita = new Cita(cita, pag);
			citas[citasRegistradas] = nuevaCita;
			citasRegistradas++;
			res = true;
		}
		
		return res;
	}
	
	public int getCitasRegistradas() {
		//Regresa numero de citas
		return citasRegistradas;
	}
	
	public String getCitas() {
		//Regresa la impresion de todas las citas;
		
		String res = "Este libro no tiene citas";
		ArrayList<String> citasDelLibro = new ArrayList<String>();
		
		for(int i = 0; i < citasRegistradas; i++)
			citasDelLibro.add(citas[i].toString());
		
		if(!citasDelLibro.isEmpty())
			res = citasDelLibro.toString();
		
		return res;
	}
	
	public String toString() {
		StringBuilder cad = new StringBuilder();
		
		cad.append("\t"+ "Libro		" + "\n");
		cad.append("Titulo: " + titulo+ "\n");
		cad.append("Autor: " + autor+ "\n");
		cad.append("Género: " + genero+ "\n");
		cad.append("Formato (D = digital ; F = Físico): " + formato+ "\n");
		cad.append("Este libro está prestado: " + prestado+ "\n");
		cad.append("Con " + citasRegistradas + " citas registradas"+ "\n");
		
		if(leido) {
			cad.append("Calificación: " + calificacion+"/10"+ "\n");
			cad.append("Opinion: " + opinion+ "\n");
		}
		else
			cad.append("Este libro no ha sido leído"+ "\n");
		
		return cad.toString();
	}
	
	public int compareTo(Libro otro) {
		return this.titulo.compareTo(otro.titulo);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	

}
