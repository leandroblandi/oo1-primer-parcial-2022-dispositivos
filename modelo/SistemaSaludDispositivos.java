package modelo;

import java.util.ArrayList;
import java.util.List;

public class SistemaSaludDispositivos {

	private List<Dispositivo> dispositivos;
	private List<Empresa> empresas;

	public SistemaSaludDispositivos() {
		this.dispositivos = new ArrayList<Dispositivo>();
		this.empresas = new ArrayList<Empresa>();
	}

	public boolean agregarEmpresa(String nombre) {
		int id = calcularAutoIncrementalEmpresa();
		return empresas.add(new Empresa(id, nombre));
	}

	public Empresa traerEmpresa(String nombre) {
		Empresa empresa = null;
		boolean empresaEncontrada = false;
		int i = 0;
		while ((i < empresas.size()) && (!empresaEncontrada)) {
			Empresa empresaActual = empresas.get(i);
			if (empresaActual.getNombre().equalsIgnoreCase(nombre)) {
				empresa = empresaActual;
				empresaEncontrada = true;
			}
			i++;
		}
		return empresa;
	}

	public boolean agregarDispositivo(String nombre, String codigo, Empresa empresa) throws Exception {
		int id = calcularAutoIncrementalDispositivo();
		return dispositivos.add(new Dispositivo(id, nombre, codigo, empresa));
	}

	public Dispositivo traerDispositivo(String codigo) {
		Dispositivo dispositivo = null;
		boolean dispositivoEncontrado = false;
		int i = 0;
		while ((i < dispositivos.size()) && (!dispositivoEncontrado)) {
			Dispositivo dispositivoActual = dispositivos.get(i);
			if (dispositivoActual.getCodigo().equalsIgnoreCase(codigo)) {
				dispositivo = dispositivoActual;
				dispositivoEncontrado = true;
			}
			i++;
		}
		return dispositivo;
	}

	private int calcularAutoIncrementalEmpresa() {

		int id = 0;

		if (empresas.isEmpty()) {
			id = 1;
		} else {
			id = empresas.get(empresas.size() - 1).getId() + 1;
		}
		return id;
	}

	private int calcularAutoIncrementalDispositivo() {

		int id = 0;

		if (dispositivos.isEmpty()) {
			id = 1;
		} else {
			id = dispositivos.get(dispositivos.size() - 1).getId() + 1;
		}
		return id;
	}

	@Override
	public String toString() {
		return "SistemaSaludDispositivos [dispositivos=" + dispositivos + ", empresas=" + empresas + "]";
	}

	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}
}
