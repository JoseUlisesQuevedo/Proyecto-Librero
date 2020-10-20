/*
 * Jose Ulises Quevedo
 * Diciembre 2019
 * Controlador de la vista Libro, trabaja con el objeto librero del LibreroControlador
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class LibroControlador extends LibroVista {
	
	private static final long serialVersionUID= 1L;
	String tituloDelLibro; 
	Librero libreroDeTrabajo;
	
	public LibroControlador(String titulo, Librero librero) {
		super(titulo, librero);
		
		tituloDelLibro = titulo;
		libreroDeTrabajo = librero;
		
		
	
		
		
		botonImprimeCitas.addActionListener(new EscuchadorImprimeCitas());
		botonPrestar.addActionListener(new EscuchadorPrestarRegresar());
		botonLeer.addActionListener(new EscuchadorLeer());
		botonAgregarCita.addActionListener(new EscuchadorAgregaCita());
		
	}
	
	private class EscuchadorImprimeCitas implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			//citas.setText(libreroDeTrabajo.getCitas(tituloDelLibro));
			
			citas.setText(libreroDeTrabajo.getCitas(tituloDelLibro));
		}
		
	}
	
	private class EscuchadorPrestarRegresar implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			String name;
			
			
			if(!libreroDeTrabajo.isPrestado(tituloDelLibro)) {
				
				name = JOptionPane.showInputDialog(null,
	                    "Ingrese el nombre de quien se le prestará", null);
			
				if(name != null)
					libreroDeTrabajo.prestar(tituloDelLibro, name);
				
				infoLibro.setText(libreroDeTrabajo.getUnLibroDePrestados(tituloDelLibro));
				
				
			}
			
			
			
		}
		
	}
	
	private class EscuchadorLeer implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			String calif, op;
			double calificacion;
			
			calif = JOptionPane.showInputDialog(null,
                    "Ingrese una calificacion para este libro (sobre 10)", null);
			
			op = JOptionPane.showInputDialog(null,
                    "Ingrese una opinion sobre el libro (Opcional, si no quiere opinar deje este campo vacío)", null); 
			
			if(calif != null) {
				libreroDeTrabajo.leer(tituloDelLibro);
				calificacion = Double.parseDouble(calif);
				libreroDeTrabajo.calificar(tituloDelLibro,calificacion);
				if(op != null)
					libreroDeTrabajo.setOpinion(tituloDelLibro,op);
			
			}
			
			
			infoLibro.setText(libreroDeTrabajo.getUnLibro(tituloDelLibro).toString());
			
			
		}
		
		
	}
	
	private class EscuchadorAgregaCita implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			String pg, cita;
			int pgInt;
			
			pg = txtPag.getText();
			cita = txtCita.getText();
			
			if(!pg.equals("")){
				
				pgInt = Integer.parseInt(pg);
				
				libreroDeTrabajo.agregarCita(tituloDelLibro, cita, pgInt);
				
				citas.setText("Cita agregada con exito!");
				
				
			}
			else {
				citas.setText("Error al agregar cita. Revise que haya ingresado una pagina");
			}
			
			infoLibro.setText(libreroDeTrabajo.getUnLibro(tituloDelLibro).toString());
			
		}
		
	}
	


}
