/*
 * Jose Ulises Quevedo 000189442
 * Diciembre 2019
 * Controlador del librero, controla VistaLibrero y trabaja con un objeto de clase Librero
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class LibreroControlador extends LibreroVista {
	
	static Librero misLibros = new Librero("Uli");
	
	private static final long serialVersionUID= 1L;
	
	
	
	public LibreroControlador(String titulo, Librero librero) {
		super(titulo,librero);
		
		
		//BOTON ALTA y ELIMINA LIBRO
		botonAltaLibro.addActionListener(new EscuchadorAltaBaja());
		botonEliminaLibro.addActionListener(new EscuchadorAltaBaja());
		
		//ESCUCHADORES BUSQUEDAS
		botonBuscaTitulo.addActionListener(new EscuchadorLibrito());
		botonBuscaAutor.addActionListener(new EscuchadorBuscaAutor());
		botonBuscaXEstrellas.addActionListener(new EscuchadorEstrellas());
		botonBuscaMasEstrellas.addActionListener(new EscuchadorEstrellas());
		botonBuscaEnPrestados.addActionListener(new EscuchadorPrestados());
		
		//ESCUCHADORES GRALES
		botonArchivo.addActionListener(new EscuchadorGenerales());
		botonLeidos.addActionListener(new EscuchadorGenerales());
		botonNoLeidos.addActionListener(new EscuchadorGenerales());
		botonPrestados.addActionListener(new EscuchadorGenerales());
		botonLibroFavorito.addActionListener(new EscuchadorGenerales());
		botonLibrosXGenero.addActionListener(new EscuchadorGenerales());
		botonTodoMiLibrero.addActionListener(new EscuchadorGenerales());
		
		//ESCUCHADORES LIBRERO
		for(int i = 0; i < librero.MAX_GENEROS; i++)
			for(int j = 0; j < librero.MAX_LIBROS; j++) {
				libritos[i][j].addActionListener(new EscuchadorLibrito());
			}
		
		//ESCUCHADORES METODOS POR GENEROS
		botonLeidosG.addActionListener(new EscuchadorGeneros());
		botonNoLeidosG.addActionListener(new EscuchadorGeneros());
		botonPrestadosG.addActionListener(new EscuchadorGeneros());
		botonLibroFavoritoG.addActionListener(new EscuchadorGeneros());
		botonCuantosLibros.addActionListener(new EscuchadorGeneros());
		
	}
	
	private class EscuchadorAltaBaja implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			char accion;
			String titulo, autor, genero, calif, op;
			char formato, leido;
			double califDouble;
			
			
			accion = e.getActionCommand().charAt(0);
		
			

			switch(accion) {
				case 'A':
					
					//Caso dar de alta
			
					//Obtiene elementos mínimos necesarios para el alta
					titulo = txtTitulo.getText();
					autor = txtAutor.getText();
					genero = (String) boxGenero.getSelectedItem();
					formato = grupoFormato.getSelection().getActionCommand().charAt(0);
					
					//Revisa que el campo de autor y de titulo no estén vacios (para evitar errores)
					if(!titulo.equals("") && !autor.equals("")) {
						
						//Determina si se dará de alta como léido o no leído
						if(radioLeido.isSelected()) {
						
							calif = txtCalif.getText();
							califDouble = Double.parseDouble(calif);
							op = txtOpinion.getText();
						
							if(misLibros.altaLibro(titulo, genero, autor, formato,califDouble,op)) {
								areaInformacion.setText("Alta exitosa!");
								actualizarLibrero();
							}
							else
								areaInformacion.setText("Alta Fallida, a este genero ya no le caben libros");
						
						}
						else
							if(misLibros.altaLibro(titulo, genero, autor, formato)) {
								areaInformacion.setText("Alta exitosa!");
								actualizarLibrero();
							}
							else
								areaInformacion.setText("Alta Fallida, a este genero ya no le caben libros");
						
					}
					else
						areaInformacion.setText("Alta fallida, falta ingresar datos!");
					break;
					
				case 'E':
					titulo = txtTitulo.getText();
					
					if(misLibros.eliminarLibro(titulo)) {
						areaInformacion.setText("Libro eliminado exitosamente");
						actualizarLibrero();
					}
					else
						areaInformacion.setText("Error al eliminar libro. \nProbablemente este libro no esté en tu librero, o hayas escrito mal el título");
					
					break;		
			}
		
		
		
		}

	}
	
	private class EscuchadorBuscaAutor implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String nomAutor;
			
			nomAutor = txtBuscaPorAutor.getText();
			
			areaInformacion.setText(librero.librosUnAutor(nomAutor));
			
			
		}
		
	}
	
	private class EscuchadorEstrellas implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			String calif;
			double doubleCalif = 0; //inicializacion en 0 por si doubleCalif falla
			char accion;
			
			calif = txtBuscaXEstrellas.getText();
			
			
			if(!calif.equals(""))
				//Si el campo no está vacio, hace la conversión a double
				doubleCalif = Double.parseDouble(calif);
			
			accion = e.getActionCommand().charAt(0);
			
			
			
			switch(accion) {
				case 'B':
					
					areaInformacion.setText(librero.librosConXCali(doubleCalif));
					
					break;
					
				case '*':
					
					areaInformacion.setText(librero.librosConMasDeXCalif(doubleCalif));
					
					
					break;
			
			}
		}
	}
	
	private class EscuchadorGenerales implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			char accion;
			File ent;
			int returnValue;
			
			accion = e.getActionCommand().charAt(0);
			
			switch(accion) {
			
				case 'C':
					
					JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
					
					returnValue = jfc.showOpenDialog(null);
					
					if(returnValue == JFileChooser.APPROVE_OPTION) {
						ent = jfc.getSelectedFile();
					
					
					
					
						try(Scanner lect = new Scanner(ent)){
							String titulo, autor,genero,auxFormato, opinion;
							char formato;
							double calif;
							boolean leido;
							int librosARegistrar;
							
							
							librosARegistrar = lect.nextInt();
							lect.nextLine();
							
							for(int i = 0; i < librosARegistrar; i++) {
								titulo = lect.nextLine();
								autor = lect.nextLine();
								genero = lect.nextLine();
								auxFormato = lect.nextLine();
								formato = auxFormato.charAt(0);
								
								leido = lect.nextBoolean();
								
								if(leido) {
									calif = lect.nextDouble();
									lect.nextLine();
									opinion = lect.nextLine();
									if(librero.altaLibro(titulo, genero, autor, formato,calif,opinion))
										areaInformacion.append("Libro dado de alta con exito!"+"\n");
								}
								else {
									if(librero.altaLibro(titulo, genero, autor, formato))
										areaInformacion.append("Libro dado de alta con exito!"+"\n");
									lect.nextLine();
								}
							}
							
							lect.close();
							
							areaInformacion.append(librosARegistrar + " registrados con exito");
							actualizarLibrero();
						}
					
						catch(FileNotFoundException fnfe) {
							System.err.println("Error con el archivo "+ fnfe);
							System.exit(-1);
						}
					}
					
				
					
					break;
			
				case '-':
					
					areaInformacion.setText(librero.librosLeidos());
					
					break;
					
				case '*':
					
					areaInformacion.setText(librero.librosNoLeidos());
					
					break;
					
				case 'L':
					
					areaInformacion.setText(librero.librosPrestados());

					
					break;
					
				case 'O':
					
					areaInformacion.setText(librero.libroFavorito());

					
					break;
					
				case '¿':
					
					areaInformacion.setText(librero.librosEnCadaGenero());

					
					break;
					
				case 'I':
					
					areaInformacion.setText(librero.toString());
					
					break;
			}
			
		}
		
	}
	
	public void actualizarLibrero() {
		
		for(int j = 0; j < librero.MAX_GENEROS; j++) {
			for(int i = 0; i < librero.getLibrosXGenero(j); i++) {
				libritos[j][i].setText(librero.getUnLibro(j,i));
			}
			
			// Borra etiquetas del resto de las celdas (Para eliminaciones)
			for(int i = librero.getLibrosXGenero(j); i < librero.MAX_LIBROS; i++)
				libritos[j][i].setText(" ");
		}
			
	}
	
	private class EscuchadorLibrito implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String nombreABuscar;
			Libro librito;
			char accion;
			LibroVista controladorLibrito;
			
			accion = e.getActionCommand().charAt(0);
			
			switch(accion) {
			
			case '*':
				//Solo si se picó el busca por titulo. Si no se asume que el event vino de un boton del librero
				nombreABuscar = txtBuscaPorTitulo.getText();
				
				break;
				
			default:
				
				nombreABuscar = e.getActionCommand();
				
				break;
			}
			
			librito = librero.getUnLibroObjeto(nombreABuscar);
			
			if(librito != null) {
				
				controladorLibrito = new LibroControlador(librito.getTitulo(), misLibros);
																	// MANDAR EL LIBRERO
				areaInformacion.setText("Libro encontrado!");

			}
			else 
				areaInformacion.setText("Libro no encontrado // Lugar vacío \n (TIP: Busca en tu librero de prestados, quizá está ahí");
			
			
		}
		
		
	}
	
	private class EscuchadorPrestados implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			String libroABuscar;
			Libro librito;
			LibroVista vistaLibrito;
			
			
			libroABuscar = txtPrestados.getText();
			
			librito = librero.buscarEnPrestados(libroABuscar);
			
			if(librito != null) {
				misLibros.regresar(librito.getTitulo());
				areaInformacion.setText("Libro regresado al librero!");
			}
			
			else 
				areaInformacion.setText("Libro no está en prestados");
			
		}
		
	}
	
	private class EscuchadorGeneros implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String genero;
			char accion;
			
			genero = (String) boxGeneroMetodos.getSelectedItem();
			accion = e.getActionCommand().charAt(0);
			
			switch(accion){
			
			case '*':
				
				areaInformacion.setText(librero.librosLeidos(genero));
				
				break;
			
			case '-':
				
				areaInformacion.setText(librero.librosNoLeidos(genero));
				
				break;
				
			case '<':
				
				areaInformacion.setText(librero.libroFavorito(genero));
				
				break;
				
			case 'L':
				
				areaInformacion.setText(librero.librosPrestados(genero));
				
				break;
				
			case '¿':
				
				areaInformacion.setText("Hay " + librero.cuantosLibrosGenero(genero) + " libros del genero " + genero);
				
				break;
				
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		
		LibreroControlador yo;
		
		 yo = new LibreroControlador(misLibros.getDueno(), misLibros);
		
		

	}

}
