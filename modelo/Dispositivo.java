package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Dispositivo {

	private int id;
	private String nombre;
	private String codigo;
	private List<Metrica> metricas;
	private Empresa empresa;

	public boolean agregarMetrica(int valor, LocalDate fecha, LocalTime hora) {
		return this.metricas.add(new Metrica(valor, fecha, hora));
	}

	public Metrica traerMetrica(LocalDate fecha, LocalTime hora) {
		Metrica metrica = null;
		boolean metricaEncontrada = false;
		int i = 0;
		while ((i < metricas.size()) && (!metricaEncontrada)) {
			Metrica metricaActual = metricas.get(i);
			if (metricaActual.getFecha().equals(fecha) && metricaActual.getHora().equals(hora)) {
				metrica = metricaActual;
				metricaEncontrada = true;
			}
			i++;
		}
		return metrica;
	}

	public List<Metrica> traerMetricas(LocalDate desde, LocalDate hasta) {
		List<Metrica> metricasCoincidentes = new ArrayList<Metrica>();
		for (Metrica metrica : metricas) {
			if (entreFechas(metrica.getFecha(), desde, hasta)) {
				metricasCoincidentes.add(metrica);
			}
		}
		return metricasCoincidentes;
	}

	public List<Metrica> traerMetricas(Dispositivo dispositivo, LocalDate desde, LocalDate hasta, int menorAValor) {
		List<Metrica> metricasCoincidentes = new ArrayList<Metrica>();
		for (Metrica metrica : dispositivo.getMetricas()) {
			if ((entreFechas(metrica.getFecha(), desde, hasta)) && (metrica.getValor() < menorAValor)) {
				metricasCoincidentes.add(metrica);
			}
		}
		return metricasCoincidentes;
	}

	private boolean entreFechas(LocalDate fecha, LocalDate desde, LocalDate hasta) {
		return (fecha.equals(desde) || ((fecha.isAfter(desde)) && (fecha.isBefore(hasta))) || fecha.equals(hasta));
	}

	private boolean validarCodigo(String codigo) {

		// 'A1234' => {'A', '1', '2', ...}
		char[] codigoArray = codigo.toCharArray();

		// 'A' || 'B'
		char primerDigito = codigoArray[0];
		int sumaDigitos = sumaArray(codigoArray);
		boolean valido = false;

		if ((primerDigito == 'A') && (esPar(sumaDigitos))) {
			valido = true;
		} else if ((primerDigito == 'B') && (!esPar(sumaDigitos))) {
			valido = true;
		}
		return valido;
	}

	private boolean esPar(int numero) {
		return (numero % 2 == 0);
	}

	private int sumaArray(char[] codigoArray) {
		int suma = 0;
		for (int i = 1; i < codigoArray.length; i++) {
			// getNumericValue <=> parseInt
			suma += Character.getNumericValue(codigoArray[i]);
		}
		return suma;
	}

	public Dispositivo(int id, String nombre, String codigo, Empresa empresa) throws Exception {
		this.setId(id);
		this.setNombre(nombre);
		this.setCodigo(codigo);
		this.setEmpresa(empresa);
		this.metricas = new ArrayList<Metrica>();
	}

	@Override
	public String toString() {
		return "Dispositivo [id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + ", metricas=" + metricas
				+ ", empresa=" + empresa + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) throws Exception {
		if (validarCodigo(codigo)) {
			this.codigo = codigo;
		} else {
			throw new Exception(String.format("El codigo `%s` es invalido", codigo));
		}
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Metrica> getMetricas() {
		return metricas;
	}
}
