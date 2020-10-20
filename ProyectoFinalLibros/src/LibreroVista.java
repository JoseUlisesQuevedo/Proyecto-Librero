import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Dimension;

/*
 * Jose Ulises Quevedo
 * Diciembre 2019
 *Vista del librero
 */

public class LibreroVista extends JFrame {
	
	private static final long serialVersionUID= 1L;
	
	
	//La vista usa un objeto de tipo librero para determinar el texto de las ComboBox y cantidad de botones en el panelLibrero
	Librero librero;
	
	//Elementos panel base
	Dimension screenSize;
	
	//Elementos panel "Agrega y elimina libro"
	JLabel labelTitulo, labelAutor,labelGenero, labelFormato, labelLeido, labelCalif, labelOpinion;
	JTextField txtTitulo, txtAutor, txtCalif;
	JComboBox<String> boxGenero;
	JRadioButton radioLeido, radioNoLeido, radioDigital, radioFisico;
	ButtonGroup grupoLeidos, grupoFormato;
	JButton botonAltaLibro, botonEliminaLibro, botonAltaDesdeArchivo;
	JTextArea txtOpinion;
	
	//Elementos panel librero
	JTextArea txtLibrero;
	JButton[][] libritos; 
	JScrollPane scrollLibrero;
	
	//Elementos Panel Busqueda
	JLabel labelBuscaPorTitulo, labelLibrosUnAutor, labelLibrosXEstrellas, labelBuscaPrestados;
	JTextField txtBuscaPorTitulo, txtBuscaPorAutor, txtBuscaXEstrellas, txtPrestados;
	JButton botonBuscaTitulo, botonBuscaEnPrestados, botonBuscaAutor, botonBuscaXEstrellas, botonBuscaMasEstrellas, botonBuscaPrestados;
	
	
	//Elementos Panel Metodos Generales
	JButton botonArchivo, botonLeidos,botonNoLeidos, botonPrestados, botonLibroFavorito, botonLibrosXGenero, botonTodoMiLibrero;
	
	//Elementos panel información
	JTextArea areaInformacion;
	JScrollPane scroll;
	
	//Elementos panel MetodosXGenero
	JComboBox<String> boxGeneroMetodos;
	JButton  botonLeidosG, botonNoLeidosG, botonPrestadosG, botonLibroFavoritoG, botonCuantosLibros;

	
	public LibreroVista(String titulo, Librero librero) {
		super(titulo);
		this.librero = librero; 
	
		
		//Panel base sobre el que van todos los paneles (panelBase)
		JPanel base;base = new JPanel();
		Border marcoBase = BorderFactory.createEmptyBorder(25,25,25,25);
		base.setBorder(marcoBase);
		base.setLayout(new GridLayout(2,3));
		
		//Inicialización y acomodo de panelAlta
			
			//Inicialización Labels
		labelTitulo = new JLabel("Título");
		labelAutor = new JLabel("Autor");
		labelGenero = new JLabel("Genero");
		labelFormato = new JLabel("Formato");
		labelLeido = new JLabel("Leido");
		labelCalif = new JLabel("Calif. (OBLIGATORIA SI LEIDO)");
		labelOpinion = new JLabel("Opinion (Opcional)");
			
			//Inicialización TextFields, ComboBoxes y Radios
		txtTitulo = new JTextField(12);
		txtAutor = new JTextField(12);
		txtOpinion = new JTextArea();
		txtCalif = new JTextField(12);
		boxGenero = new JComboBox<String>(librero.getGeneros());
		
		grupoFormato = new ButtonGroup();
		radioDigital = new JRadioButton("Digital");
		radioDigital.setActionCommand("Digital");
		radioFisico = new JRadioButton("Físico");
		radioFisico.setActionCommand("Fisico");
		radioFisico.setSelected(true);
		grupoFormato.add(radioDigital);
		grupoFormato.add(radioFisico);
		JPanel panelBotoncitosFormato = new JPanel();
		panelBotoncitosFormato.setLayout(new GridLayout(2,1));
		panelBotoncitosFormato.add(radioDigital);
		panelBotoncitosFormato.add(radioFisico);
		
		grupoLeidos = new ButtonGroup();
		radioLeido = new JRadioButton("Leido");
		radioNoLeido = new JRadioButton("No Leido");
		radioLeido.setSelected(true);
		grupoLeidos.add(radioLeido);
		grupoLeidos.add(radioNoLeido);
		JPanel panelBotoncitosLeido = new JPanel();
		panelBotoncitosLeido.setLayout(new GridLayout(2,1));
		panelBotoncitosLeido.add(radioLeido);
		panelBotoncitosLeido.add(radioNoLeido);
		
		
			//Inicialización Botones
		
		botonAltaLibro = new JButton("Agregar Libro");
		botonEliminaLibro = new JButton("Elimina Libro");
		botonAltaDesdeArchivo = new JButton("Leer Archivo con Libros");
		
			//Creacion y acomodo en panel
		
		JPanel panelAlta = new JPanel();
		Border marco = BorderFactory.createTitledBorder(null,"Agregar y eliminar libros",TitledBorder.CENTER,TitledBorder.TOP);
		panelAlta.setBorder(marco);
		panelAlta.setLayout(new GridLayout(9,2));
		
		panelAlta.add(labelTitulo);
		panelAlta.add(txtTitulo);
		panelAlta.add(labelAutor);
		panelAlta.add(txtAutor);
		panelAlta.add(labelGenero);
		panelAlta.add(boxGenero);
		panelAlta.add(labelFormato);
		panelAlta.add(panelBotoncitosFormato);
		panelAlta.add(labelLeido);
		panelAlta.add(panelBotoncitosLeido);
		panelAlta.add(labelCalif);
		panelAlta.add(txtCalif);
		panelAlta.add(labelOpinion);
		panelAlta.add(txtOpinion);
		panelAlta.add(botonAltaLibro);
		panelAlta.add(botonEliminaLibro);
		
		base.add(panelAlta);
		
		//Inicialización y acomodo panel librero
		
			//Inicialización del Panel
		
		// REVISAR ACCESIBILIDAD A LIBRERO Y LA IDEA DE TENER EL LIBRERO COMO ARGUMENTO
		
		JPanel panelLibrero = new JPanel();
		Border marcoLibrero = BorderFactory.createTitledBorder(null, "Mis libros", TitledBorder.CENTER, TitledBorder.TOP);
		panelLibrero.setBorder(marcoLibrero);
		panelLibrero.setLayout(new GridLayout(librero.MAX_GENEROS,librero.MAX_LIBROS));
		
		libritos = new JButton[librero.MAX_GENEROS][librero.MAX_LIBROS];
		
			
			//Inicialización botones en matriz de botones
		for(int i = 0; i < librero.MAX_GENEROS; i++)
			for(int j = 0; j < librero.MAX_LIBROS; j++) {
				libritos[i][j] = new JButton(" ");
				libritos[i][j].setSize(new Dimension(20,20));
				panelLibrero.add(libritos[i][j]);
			}
		
		scrollLibrero = new JScrollPane(panelLibrero);
		
		base.add(scrollLibrero);
		
		
		//Inicializacion y acomodo Panel Busquedas
		JPanel panelBusquedas = new JPanel();
		Border marcoBusquedas = BorderFactory.createTitledBorder(null, "Buscar", TitledBorder.CENTER,TitledBorder.TOP);
		panelBusquedas.setBorder(marcoBusquedas);
		panelBusquedas.setLayout(new GridLayout(13,1));
		
	
		
			//Inicializacion Labels, TextFields y Botones
		
		labelBuscaPorTitulo = new JLabel("Buscar por título");
		labelLibrosUnAutor = new JLabel("Todos los libros de un autor");
		labelLibrosXEstrellas = new JLabel("Filtrar libros por calificación");
		labelBuscaPrestados = new JLabel("Regresar un libro que está en prestados");
		
		txtBuscaPorTitulo = new JTextField(12);
		txtBuscaPorAutor = new JTextField(12);
		txtBuscaXEstrellas = new JTextField(12);
		txtPrestados = new JTextField(12);
		
		botonBuscaTitulo = new JButton("*Buscar en librero*");
		botonBuscaAutor = new JButton("Buscar por autor");
		botonBuscaXEstrellas = new JButton("Buscar libros con esta calificacion");
		botonBuscaMasEstrellas = new JButton("*Buscar libros con más de esta calificación*");
		botonBuscaEnPrestados = new JButton("Regresar un libro que está en prestados");
		
			//Añadiendo todos los elementos al panel
		
		panelBusquedas.add(labelBuscaPorTitulo);
		panelBusquedas.add(txtBuscaPorTitulo);
		panelBusquedas.add(botonBuscaTitulo);
		panelBusquedas.add(labelLibrosUnAutor);
		panelBusquedas.add(txtBuscaPorAutor);
		panelBusquedas.add(botonBuscaAutor);
		panelBusquedas.add(labelLibrosXEstrellas);
		panelBusquedas.add(txtBuscaXEstrellas);
		panelBusquedas.add(botonBuscaXEstrellas);
		panelBusquedas.add(botonBuscaMasEstrellas);
		panelBusquedas.add(labelBuscaPrestados);
		panelBusquedas.add(txtPrestados);
		panelBusquedas.add(botonBuscaEnPrestados);
		
		base.add(panelBusquedas);
		
		//Inicializaión y acomodo panel metodos generales
		JPanel panelGenerales = new JPanel();
		Border marcoGenerales = BorderFactory.createTitledBorder(null, "Operaciones Generales", TitledBorder.CENTER,TitledBorder.TOP);
		panelGenerales.setBorder(marcoGenerales);
		panelGenerales.setLayout(new GridLayout(7,1));
		
			//Inicializacion Botones
		botonArchivo = new JButton("Cargar libros desde archivo");
		botonLeidos = new JButton("-Lista de todos los libros leídos-");
		botonNoLeidos = new JButton("*Lista de todos los libros sin leer*");
		botonPrestados = new JButton("Lista de todos los libros prestados");
		botonLibroFavorito = new JButton("Obtener libro favorito");
		botonLibrosXGenero = new JButton("¿Cuántos libros en cada genero?");
		botonTodoMiLibrero = new JButton("Imprime todo mi librero");
		
			//Añadiendo al panel
		panelGenerales.add(botonArchivo);
		panelGenerales.add(botonLeidos);
		panelGenerales.add(botonNoLeidos);
		panelGenerales.add(botonPrestados);
		panelGenerales.add(botonLibroFavorito);
		panelGenerales.add(botonLibrosXGenero);
		panelGenerales.add(botonTodoMiLibrero);
		
			//Añadiendo el panel al panel base
		base.add(panelGenerales);	
		
		//Inicialización y ordenamiento panel inforamción
		JPanel panelInformacion = new JPanel();
		Border marcoInfo = BorderFactory.createTitledBorder(null, "Información: ", TitledBorder.LEFT,TitledBorder.TOP);
		panelInformacion.setBorder(marcoInfo);
		
		areaInformacion = new JTextArea(25,40);
		
		scroll = new JScrollPane(areaInformacion);
		
		panelInformacion.add(scroll);
		
		base.add(panelInformacion);
		
		
		//Panel Metodos X Genero
		JPanel panelGeneros = new JPanel();
		Border marcoGeneros = BorderFactory.createTitledBorder(null, "Operaciones Por Genero: ", TitledBorder.LEFT,TitledBorder.TOP);
		panelGeneros.setBorder(marcoGeneros);
		panelGeneros.setLayout(new GridLayout(6,1));
	
			//Inicializacion Botones y Combo Box
		boxGeneroMetodos = new JComboBox<String>(librero.getGeneros());
		botonLeidosG = new JButton("*Lista de libros leídos*");
		botonNoLeidosG = new JButton("- Lista de libros no leídos - ");
		botonLibroFavoritoG = new JButton("<Libro favorito>");
		botonPrestadosG = new JButton("Lista de libros prestados");
		botonCuantosLibros = new JButton("¿Cuántos libros de este género tienes?");
		
			//Agregando botones y box al panel
		
		panelGeneros.add(boxGeneroMetodos);
		panelGeneros.add(botonLeidosG);
		panelGeneros.add(botonNoLeidosG);
		panelGeneros.add(botonLibroFavoritoG);
		panelGeneros.add(botonPrestadosG);
		panelGeneros.add(botonCuantosLibros);
		
			//Agregando panel a la base
	
		base.add(panelGeneros);
		
	
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.add(base);
		
		//Requerimientos JFrame;
		this.setVisible(true);
		this.setBounds(0,0,(int)screenSize.getWidth(),(int)screenSize.getHeight());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	

}
