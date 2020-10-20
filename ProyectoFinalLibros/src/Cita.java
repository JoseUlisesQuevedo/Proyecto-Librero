/*
 * José Ulises Quevedo
 * Diciembre 2019
 * Modela una cita de un libro, con texto y pagina
 */

public class Cita {
	private String cita;
	private int pag;
	
	public Cita(String cita, int pag) {
		this.cita = cita;
		this.pag = pag;
	}

	public String getCita() {
		return cita;
	}

	public int getPag() {
		return pag;
	}
	
	public String toString() {
		StringBuilder cad = new StringBuilder();
		
		cad.append("Cita de la página " + pag + "\n");
		cad.append("Cita: " + cita);
		
		return cad.toString();
	}
	public int compareTo(Cita otra) {
		return cita.compareTo(otra.cita);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cita == null) ? 0 : cita.hashCode());
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
		Cita other = (Cita) obj;
		if (cita == null) {
			if (other.cita != null)
				return false;
		} else if (!cita.equals(other.cita))
			return false;
		return true;
	}
}
