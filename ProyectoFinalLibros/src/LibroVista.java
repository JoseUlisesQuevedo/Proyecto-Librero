
/*
 * Jose Ulises Quevedo
 * Diciembre 2019
 * Vista de libro individual que se abre al acceder a un libro
 */

import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.Border;


public class LibroVista extends JFrame {
	
	private static final long serialVersionUID= 1L;
	// Componentes info libros
	JTextArea infoLibro;
	
	//Componentes panel Botones
	JButton botonPrestar, botonLeer, botonImprimeCitas;

	//Componentes panel verCitas
	JTextArea citas;
	JScrollPane citasScroll;
	
	
	//Componentes panel agregaCitas
	JLabel labelPag,labelCita;
	JTextField txtPag;
	JTextArea txtCita;
	JButton botonAgregarCita;
	
	
	Librero libreroDeTrabajo;
	
	
	public LibroVista(String titulo,Librero librero) {
		
		//Setup del JFrame
		super(titulo);
		JPanel base = new JPanel();
		Border marco = BorderFactory.createEmptyBorder(10,10,10,10);
		base.setBorder(marco);
		base.setLayout(new GridLayout(2,2));
		libreroDeTrabajo = librero;
		
		//Setup panelInfo
		
		JPanel panelInfo = new JPanel();
		infoLibro = new JTextArea(librero.getUnLibroObjeto(titulo).toString());
		infoLibro.setEditable(false);
		infoLibro.setOpaque(false);
		panelInfo.add(infoLibro);
		
		base.add(panelInfo);
		
		//Setup panelBotones
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(3,1));
		botonPrestar = new JButton("Prestar este libro");
		botonLeer = new JButton("Leer este libro / Cambiar calificación u opinión");
		botonImprimeCitas = new JButton("Imprimir citas");
		
		panelBotones.add(botonPrestar);
		panelBotones.add(botonLeer);
		panelBotones.add(botonImprimeCitas);
		base.add(panelBotones);
		
		//Setup Panel Ver citas
		JPanel panelVerCitas = new JPanel();
		citas = new JTextArea(librero.getUnLibroObjeto(titulo).getCitas());
		citas.setEditable(false);
		
		citasScroll = new JScrollPane(citas);
		
		base.add(citasScroll);
		
		//Setup AgregaCita
		labelPag = new JLabel("Pagina: ");
		labelCita = new JLabel("Cita: ");
		txtPag = new JTextField(12);
		txtCita = new JTextArea(10,10);
		botonAgregarCita = new JButton("Agregar cita");
		
		JPanel panelAgregaCitas = new JPanel();
		
		panelAgregaCitas.setLayout(new GridLayout(3,2));
		
		panelAgregaCitas.add(labelPag);
		panelAgregaCitas.add(txtPag);
		panelAgregaCitas.add(labelCita);
		panelAgregaCitas.add(txtCita);
		panelAgregaCitas.add(botonAgregarCita);
		
		base.add(panelAgregaCitas);

		
		
		this.add(base);
		this.setVisible(true);
		this.setBounds(200,200, 800,500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	

}
