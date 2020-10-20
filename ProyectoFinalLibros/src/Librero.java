/*
 * Jose Ulises Quevedo
 * Diciembre 2019
 * Modela un librero que contine una matriz de libros, cada renglon es un genero
 */

import java.util.ArrayList;


import Manejadores.ManejadorArreglosGenerico;
import Manejadores.ManejadorMatricesGenerico;

public class Librero {
	
	private String dueno;
	private Libro[][] libros;
	private String[] generos = {"Fantasía","Historia","Ciencia","Novela"};
	private int[] librosXGenero;
	//MAX_GENEROS y MAX_LIBROS no son privados porque se requiere conocer para generar la matriz de botones
	final int MAX_GENEROS = 4; 
	final int MAX_LIBROS = 30;
	private Libro[] librosPrestados;
	private int cuantosPrestados; 
	private String[] personasPrestadas;
	private final int MAX_PRESTADOS = 20;
	private int librosTotales;
	
	public Librero() {
		//Constructor de setup, inicializa matriz (librero) y arreglos (libros prestados y librosXGenero)
		//Incializa contadores (librosTotales, librosPrestados, librosXGeneros;
		libros = new Libro[MAX_GENEROS][MAX_LIBROS];
		librosTotales = 0;
		
		librosPrestados = new Libro[MAX_PRESTADOS];
		personasPrestadas = new String[MAX_PRESTADOS];
		cuantosPrestados = 0;
		
		librosXGenero = new int[MAX_GENEROS];
		for(int i = 0; i < MAX_GENEROS; i++)
			librosXGenero[i] = 0;	
	}
	
	public Librero(String dueno) {
		this();
		this.dueno = dueno;
	}
	
	//Metodos para funcionalidad de vista / controlador
	public String getDueno() {
		return dueno;
	}
	
	public int getLibrosTotales() {
		return librosTotales;
	}
	
	public String[] getGeneros() {
		String[] generosPatito;
		//Regresa un arreglo local con los nombres de los generos
		//Usado por los ComboBox de la vista para poner las opciones de géneros
		
		generosPatito = new String[MAX_GENEROS];
		
		for(int i = 0; i < MAX_GENEROS; i++)
			generosPatito[i] = generos[i];
		
		return generosPatito;	
	}
	
	public String getUnLibro(String titulo) {
		String res = "Libro no encontrado";
		int[] pos = new int[2];
		Libro buscador;
		
		buscador = new Libro(titulo);
		
		pos = ManejadorMatricesGenerico.buscaMatriz(libros, MAX_GENEROS, librosXGenero, buscador);
		
		if(pos[0] != -1) 
			res = libros[pos[0]][pos[1]].toString();
		
		return res;
	}
	
	public String getUnLibroDePrestados(String tituloDelLibro) {
		String res = "Libro no encontrado";
		int pos;
		Libro buscador;
		
		buscador = new Libro(tituloDelLibro);
		
		pos = ManejadorArreglosGenerico.busSecDes(librosPrestados, cuantosPrestados, buscador);
		
		if(pos != -1) {
			res = librosPrestados[pos].toString();
		}
		
		return res;
	}

	public String getUnLibro(int j, int i) {
		//METODO QUE SOLO SE USA PARA ACTUALIZAR VISTA DEL LIBRERO
		//Devuelve el título del libro para los botones
		
		return libros[j][i].getTitulo();
	}
	
	public int getLibrosXGenero(int indiceGenero) {
		//Devuelve cuantos libros en un genero
		//Utilizado en el for para actualizar botones (para saber hasta donde pedir títulos)
		
		return librosXGenero[indiceGenero];
	}
	
	//METODOS PANEL ALTA Y BAJA
	
	public boolean altaLibro(String titulo, String genero, String nombreAutor, char formato) {
		//Alta libro no leido
		
		boolean res = false;
		int indiceGen;
		Libro libro;
		
		indiceGen = ManejadorArreglosGenerico.busSecDes(generos, MAX_GENEROS, genero);
		
		if(indiceGen != -1 && librosXGenero[indiceGen] < MAX_LIBROS) {
			libro = new Libro(titulo, genero, nombreAutor, formato);
			libros[indiceGen][librosXGenero[indiceGen]] = libro;
			librosXGenero[indiceGen]++;
			librosTotales++;
			res = true;
		}
		return res;	
	}
	
	/*
	Ya no se usa, igual que el constructor de Libro que ya no se usa
	
	public boolean altaLibro(String titulo, String genero, String nombreAutor, char formato,double calif) {
		boolean res = false;
		int indiceGen;
		Libro libro;
		
		indiceGen = ManejadorArreglosGenerico.busSecDes(generos, MAX_GENEROS, genero);
		
		if(indiceGen != -1 && librosXGenero[indiceGen] < MAX_LIBROS) {
			libro = new Libro(titulo, genero, nombreAutor, formato,calif);
			libros[indiceGen][librosXGenero[indiceGen]] = libro;
			librosXGenero[indiceGen]++;
			librosTotales++;
			res = true;
		}
		
		return res;
		
	}
	*/
	
	public boolean altaLibro(String titulo, String genero, String nombreAutor, char formato,double calif,String opinion) {
		//Alta libro leído
		
		boolean res = false;
		int indiceGen;
		Libro libro;
		
		indiceGen = ManejadorArreglosGenerico.busSecDes(generos, MAX_GENEROS, genero);
		
		if(indiceGen != -1 && librosXGenero[indiceGen] < MAX_LIBROS) {
			libro = new Libro(titulo, genero, nombreAutor, formato,calif,opinion);
			libros[indiceGen][librosXGenero[indiceGen]] = libro;
			librosXGenero[indiceGen]++;
			librosTotales++;
			res = true;
		}
		return res;
	}
	
	private boolean altaLibro(Libro libro) {
		//Alta que solo se usa para mover un libro de Prestados al Librero, porque el libro ya está creado y viviendo en prestados
		boolean res = false;
		int indiceGen;
		
		indiceGen = ManejadorArreglosGenerico.busSecDes(generos, MAX_GENEROS, libro.getGenero());
		
		if(indiceGen != -1 && librosXGenero[indiceGen] < MAX_LIBROS) {
			libro.regresar();
			libros[indiceGen][librosXGenero[indiceGen]] = libro;
			librosXGenero[indiceGen]++;
			res = true;
		}
		return res;
	}

	public boolean eliminarLibro(String titulo) {
		boolean res = false;
		int pos[] = new int[2];
		Libro buscador = new Libro(titulo);
		
		pos = ManejadorMatricesGenerico.buscaMatriz(libros, MAX_GENEROS, librosXGenero, buscador);
		
		if(pos[0] != -1) {
			
			ManejadorArreglosGenerico.eliminaEnDesorden(libros[pos[0]], librosXGenero[pos[0]], libros[pos[0]][pos[1]]);
			librosTotales--;
			librosXGenero[pos[0]]--;
			res = true;
		}
		return res;
	}
	
	//METODOS VENTANA LIBRO (estos metodos acceden a los metodos de la clase Libro a través del librero)
	 
	public boolean agregarCita(String titulo, String cita, int pag) {
		boolean res = false;
		int[] pos = new int[2];
		Libro buscador;
		
		buscador = new Libro(titulo);
		
		pos = ManejadorMatricesGenerico.buscaMatriz(libros, MAX_GENEROS, librosXGenero, buscador);
		
		if(pos[0] != -1) 
			if(libros[pos[0]][pos[1]].agregarCita(cita, pag))
				res = true;
		
		return res;
	}
		
	public boolean isPrestado(String titulo) {
		boolean res = false;
		int[] posLibrero = new int[2];
		int posPrestados;
		Libro buscador;
		
		buscador = new Libro(titulo);
		
		posLibrero = ManejadorMatricesGenerico.buscaMatriz(libros, MAX_GENEROS, librosXGenero, buscador);
		
		if(posLibrero[0] != -1) {
			res = libros[posLibrero[0]][posLibrero[1]].isPrestado();
			}
		else {
			posPrestados = ManejadorArreglosGenerico.busSecDes(librosPrestados, cuantosPrestados, buscador);
			if(posPrestados != -1) {
				res = librosPrestados[posPrestados].isLeido();
			}
		}
		return res;
		}
	
	public void leer(String tituloDelLibro) {
		int[] pos = new int[2];
		Libro buscador;
		
		buscador = new Libro(tituloDelLibro);
		
		pos = ManejadorMatricesGenerico.buscaMatriz(libros, MAX_GENEROS, librosXGenero, buscador);
		
		if(pos[0] != -1) {
			libros[pos[0]][pos[1]].leer();
			}	
	}
	
	public void calificar(String tituloDelLibro, double calificacion) {
		int[] pos = new int[2];
		Libro buscador;
		
		buscador = new Libro(tituloDelLibro);
		
		pos = ManejadorMatricesGenerico.buscaMatriz(libros, MAX_GENEROS, librosXGenero, buscador);
		
		if(pos[0] != -1) {
			
			libros[pos[0]][pos[1]].calificar(calificacion);;
			}
	}
	
	public void setOpinion(String tituloDelLibro, String op) {
		int[] pos = new int[2];
		Libro buscador;
		
		buscador = new Libro(tituloDelLibro);
		
		pos = ManejadorMatricesGenerico.buscaMatriz(libros, MAX_GENEROS, librosXGenero, buscador);
		
		if(pos[0] != -1) {
			libros[pos[0]][pos[1]].setOpinion(op);
		}
	}
	
	public String getCitas(String tituloDelLibro) {
		int[] pos = new int[2];
		Libro buscador;
		String res = "Este libro no tiene citas";
		
		buscador = new Libro(tituloDelLibro);
		
		pos = ManejadorMatricesGenerico.buscaMatriz(libros, MAX_GENEROS, librosXGenero, buscador);
		
		if(pos[0] != -1) {
			res = libros[pos[0]][pos[1]].getCitas();
			}
		return res;
	}
	
	public void prestar(String titulo, String prestador) {
		int[] pos = new int[2];
		Libro buscador;
		
		buscador = new Libro(titulo);
		
		pos = ManejadorMatricesGenerico.buscaMatriz(libros, MAX_GENEROS, librosXGenero, buscador);
		
		if(pos[0] != -1) {
			//marca al libro como prestado
			libros[pos[0]][pos[1]].prestar();
			
			//Copia el libro al arreglo de libros prestado
			librosPrestados[cuantosPrestados] = libros[pos[0]][pos[1]];
			personasPrestadas[cuantosPrestados] = prestador;
			
			cuantosPrestados++;
			
			//Elimina libro de su renglon en el librero
			ManejadorArreglosGenerico.eliminaEnDesorden(libros[pos[0]], librosXGenero[pos[0]], libros[pos[0]][pos[1]]);
			librosXGenero[pos[0]]--;
		}
	}
	
	public void regresar(String titulo) {
		int pos; 
		Libro buscador;
		
		buscador = new Libro(titulo);
		
		pos = ManejadorArreglosGenerico.busSecDes(librosPrestados, cuantosPrestados, buscador);
		
		//Solo si se encontró el libro Y aún se puede regresar al estante
		//altaLibro(Libro) cambia el estado a No Prestado y Aumenta el contador de LibrosXGenero
		
		if(pos != -1 && altaLibro(librosPrestados[pos])) {
		
			//Borra el libro del arreglo de prestados, la persona y decrementa cuantosPrestados
			ManejadorArreglosGenerico.eliminaEnDesorden(librosPrestados, cuantosPrestados, librosPrestados[pos]);
			ManejadorArreglosGenerico.eliminaEnDesorden(personasPrestadas, cuantosPrestados, personasPrestadas[pos]);
			cuantosPrestados--;
		}
	}
	
	//METODOS PANEL BUSQUEDAS
	
	public Libro getUnLibroObjeto(String titulo) {
		//Este metodo se usa para generar la vista/controlador de los libros
		Libro res = null;
		int[] pos = new int[2];
		Libro buscador;
		
		buscador = new Libro(titulo);
		
		pos = ManejadorMatricesGenerico.buscaMatriz(libros, MAX_GENEROS, librosXGenero, buscador);
		
		if(pos[0] != -1) 
			res = libros[pos[0]][pos[1]];
		
		return res;
	}
	
	public String librosUnAutor(String autor) {
		ArrayList<String> librosAutor = new ArrayList<String>();
		
		for(int j = 0; j < MAX_GENEROS; j++)
			for(int i = 0; i < librosXGenero[j]; i++)
				if(libros[j][i].getAutor().equals(autor))
					librosAutor.add(libros[j][i].getTitulo());
		
		
		return "Libros de " + autor + " :" +librosAutor.toString();	
	}
	
	public String librosConXCali(double doubleCalif) {
		ArrayList<String> librosXCalif = new ArrayList<String>();
		
		for(int j = 0; j < MAX_GENEROS; j++)
			for(int i = 0; i < librosXGenero[j]; i++)
				if(libros[j][i].getCalificacion() == doubleCalif)
					librosXCalif.add(libros[j][i].getTitulo());
		
		
		return "Libros con " + doubleCalif + " de calificacion: " +librosXCalif.toString();	
		
	}
	
	public String librosConMasDeXCalif(double doubleCalif) {
		
		ArrayList<String> librosXCalif = new ArrayList<String>();
		
		for(int j = 0; j < MAX_GENEROS; j++)
			for(int i = 0; i < librosXGenero[j]; i++)
				if(libros[j][i].getCalificacion() >= doubleCalif)
					librosXCalif.add(libros[j][i].getTitulo());
		
		return "Libros con " + doubleCalif + " de calificacion: " +librosXCalif.toString();	
	}
	
	public Libro buscarEnPrestados(String titulo) {
		/*
		 * Regresa objeto de libro porque se requiere para crear la vista/controlador del libro
		 */
		Libro res = null;
		int pos;
		Libro buscador = new Libro(titulo);
		
		pos = ManejadorArreglosGenerico.busSecDes(librosPrestados, cuantosPrestados, buscador);
		
		if(pos != -1)
			res = librosPrestados[pos];
		
		return res;
	}

	//METODOS PANEL GENERALES
	public String librosLeidos() {
		ArrayList<String> libsLeidos = new ArrayList<String>();
		
		//Buscando en librero
		for(int j = 0; j < MAX_GENEROS; j++)
			for(int i = 0; i < librosXGenero[j]; i++)
				if(libros[j][i].isLeido())
					libsLeidos.add(libros[j][i].getTitulo());
		
		//Buscando en libros prestados
		for(int i = 0; i < cuantosPrestados; i++)
			if(librosPrestados[i].isLeido())
				libsLeidos.add(librosPrestados[i].getTitulo());
				libsLeidos.add("\n");
		
		
		return libsLeidos.toString();
	}

	public String librosNoLeidos() {
		ArrayList<String> libsNoLeidos = new ArrayList<String>();
		
		//Buscando en librero
		for(int j = 0; j < MAX_GENEROS; j++)
			for(int i = 0; i < librosXGenero[j]; i++)
				if(!libros[j][i].isLeido())
					libsNoLeidos.add(libros[j][i].getTitulo());
		
		//Buscando en libros prestados
		for(int i = 0; i < cuantosPrestados; i++)
			if(!librosPrestados[i].isLeido()) {
				libsNoLeidos.add(librosPrestados[i].getTitulo());
				libsNoLeidos.add("\n");
			}
		return libsNoLeidos.toString();
	}

	public String librosPrestados() {
		StringBuilder cad = new StringBuilder();
		
		for(int i = 0; i < cuantosPrestados; i++) 
			cad.append("Libro: " + librosPrestados[i].getTitulo() + " Prestado a: " + personasPrestadas[i] + "\n");
		
		return cad.toString();
	}

	public String libroFavorito() {
		String res = "Error, no hay un libro favorito";
		int generoMaxInicial;
		int[] posMax = new int[2];
		
		
		if(librosTotales != 0) {
			//Si el librero no está vacio, busca el primer genero que no está vacio
			//Evita error en caso de que no haya libro, en cuyo caso pos mayor se queda {0,0} y se rompe al llamar al libro
			
			generoMaxInicial = 0;
		
			while(generoMaxInicial < MAX_GENEROS && librosXGenero[generoMaxInicial] == 0) {
				generoMaxInicial++;
		}
		
		//Asume que el libro con mas calificacion es el primer libro del genero no vacío
		posMax[0] = generoMaxInicial;
		posMax[1] = 0;
		
		for(int j = generoMaxInicial; j < MAX_GENEROS; j++)
			for(int i = 0; i < librosXGenero[j]; i++)
				if(libros[j][i].getCalificacion() > libros[posMax[0]][posMax[1]].getCalificacion()) {
					posMax[0] = j;
					posMax[1] = i;
				}
		
		//Solo si se encontró un máximo (si el librero no estába vacio);
		
		if(libros[posMax[0]][posMax[1]] != null)
			res = libros[posMax[0]][posMax[1]].toString();
		
	}
		return res;
	}

	public String librosEnCadaGenero() {
		StringBuilder cad = new StringBuilder();
	
		for(int i = 0; i < MAX_GENEROS; i++)
			cad.append(generos[i] + " : " + librosXGenero[i] + "\n");
		
		return cad.toString();
	}
	
	
	//METODOS POR GENERO (EN TODOS SE RECIBE EL GENERO COMO STRING, SE USA ManejadorGeneroico PARA PASAR A INDICE DEL ARREGLO/MATRIZ)
	
	public String librosLeidos(String genero) {
		ArrayList<String> libsLeidos = new ArrayList<String>();
		int indGenero;
		
		
		indGenero = ManejadorArreglosGenerico.busSecDes(generos, MAX_GENEROS, genero);
		
		if(indGenero != -1) {
			
			//Buscando en librero
				for(int i = 0; i < librosXGenero[indGenero]; i++)
					if(libros[indGenero][i].isLeido())
						libsLeidos.add(libros[indGenero][i].getTitulo());
		
				//Buscando en libros prestados
				for(int i = 0; i < cuantosPrestados; i++)
					if(librosPrestados[i].isLeido() && librosPrestados[i].getGenero().equals(genero))
						libsLeidos.add(librosPrestados[i].getTitulo());
					libsLeidos.add("\n");
		
		}
		
		return libsLeidos.toString();
	}
	
	public String librosNoLeidos(String genero) {
		ArrayList<String> libsNoLeidos = new ArrayList<String>();
		int indGenero;
		
		
		indGenero = ManejadorArreglosGenerico.busSecDes(generos, MAX_GENEROS, genero);
		
		if(indGenero != -1) {
			//Buscando en librero
				for(int i = 0; i < librosXGenero[indGenero]; i++)
					if(!libros[indGenero][i].isLeido())
						libsNoLeidos.add(libros[indGenero][i].getTitulo());
		
				//Buscando en libros prestados
				for(int i = 0; i < cuantosPrestados; i++)
					if(!librosPrestados[i].isLeido() && librosPrestados[i].getGenero().equals(genero))
						libsNoLeidos.add(librosPrestados[i].getTitulo());
					libsNoLeidos.add("\n");
		
		}
		
		return libsNoLeidos.toString();
	}
	
	public String libroFavorito(String genero) {
		int indGenero;
		int indMax;
		String res = "Error! No hay un libro favorito en este género";
		
		
		indGenero = ManejadorArreglosGenerico.busSecDes(generos, MAX_GENEROS, genero);
		
		if(indGenero != -1 && librosXGenero[indGenero] != 0) {
			//Solo corre si se encontró el genero Y hay al menos 1 libro en ese genero
			
			indMax = 0;
			
			for(int i = 1; i < librosXGenero[indGenero]; i++)
				if(libros[indGenero][i].getCalificacion() > libros[indGenero][indMax].getCalificacion())
					indMax = i;
			
			res = libros[indGenero][indMax].toString();
		}
		
		return res;
		
	}
	
	public String librosPrestados(String genero) {
		StringBuilder cad = new StringBuilder();
		
		for(int i = 0; i < cuantosPrestados; i++) 
			if(librosPrestados[i].getGenero().equals(genero))
				cad.append("Libro: " + librosPrestados[i].getTitulo() + " Prestado a: " + personasPrestadas[i] + "\n");
				
		return cad.toString();
	}
	
	public int cuantosLibrosGenero(String genero) {
		int indGenero;
		int res = -1;
		
		indGenero = ManejadorArreglosGenerico.busSecDes(generos, MAX_GENEROS, genero);
		
		if(indGenero != -1)
			res = librosXGenero[indGenero];
		
		return res;
		
	}
	
	
	public String toString() {
		StringBuilder cad = new StringBuilder();

		cad.append("Librero de " + dueno +"\n" );
		cad.append("Con " + librosTotales + " libros"+ "\n");
	
		cad.append("Información de los libros: " + "\n");
		
		cad.append("\n" + "Género: " +"\n"+"\n");
		for(int i = 0; i < MAX_GENEROS; i++) {
			cad.append(generos[i]+"(" + librosXGenero[i] + ") " + "\t"+"\t" +"\n");
			for(int j = 0; j < librosXGenero[i]; j++) {
				cad.append(libros[i][j].toString() );
				cad.append("\n");
				
		}
			cad.append("\n");
			cad.append("\n");
			cad.append("\n");
		}
		
		cad.append("Con " + cuantosPrestados + " libros prestados" + "\n");
		cad.append("Información libros prestados: "+ "\n");
		
		for(int i = 0; i < cuantosPrestados; i++) {
			cad.append("Libro: " + librosPrestados[i].getTitulo() + " Prestado a: " + personasPrestadas[i]+"\n");
			
		}
		
		cad.append("--------------------------------------------------------"+ "\n");
		
		return cad.toString();
		
	}

	public int compareTo(Librero otro) {
		
		return dueno.compareTo(otro.dueno);
		
	}

	@Override
	

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dueno == null) ? 0 : dueno.hashCode());
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
		Librero other = (Librero) obj;
		if (dueno == null) {
			if (other.dueno != null)
				return false;
		} else if (!dueno.equals(other.dueno))
			return false;
		return true;
	}

}
