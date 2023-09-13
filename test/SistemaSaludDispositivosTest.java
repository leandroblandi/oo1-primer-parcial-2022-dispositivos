package test;

import java.time.LocalDate;
import java.time.LocalTime;

import modelo.Dispositivo;
import modelo.Empresa;
import modelo.SistemaSaludDispositivos;

public class SistemaSaludDispositivosTest {

	public static void main(String[] args) {

		SistemaSaludDispositivos ssd = new SistemaSaludDispositivos();

		System.out.println("Test 1");
		ssd.agregarEmpresa("Empresa 1");
		ssd.agregarEmpresa("Empresa 2");

		System.out.println("Test 2");
		System.out.println(ssd.traerEmpresa("Empresa 2"));

		System.out.println("Test 3");
		Empresa empresa1 = ssd.traerEmpresa("Empresa 1");
		Empresa empresa2 = ssd.traerEmpresa("Empresa 2");
		try {
			ssd.agregarDispositivo("Sensor Humedad", "A2020", empresa1);
			ssd.agregarDispositivo("Sensor Temperatura", "A2325", empresa1);
			ssd.agregarDispositivo("Sensor Presion", "B2021", empresa2);
			ssd.agregarDispositivo("Sensor Calor", "B2326", empresa2);
			System.out.println(ssd.getDispositivos());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test 4");
		try {
			ssd.agregarDispositivo("Sensor Movimiento", "A2021", empresa1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test 5");
		Dispositivo sensorCalor = ssd.traerDispositivo("B2326");
		System.out.println(sensorCalor);

		System.out.println("Test 6");
		sensorCalor.agregarMetrica(18, LocalDate.of(2022, 9, 18), LocalTime.of(10, 0));
		sensorCalor.agregarMetrica(19, LocalDate.of(2022, 9, 19), LocalTime.of(12, 30));
		sensorCalor.agregarMetrica(23, LocalDate.of(2022, 9, 20), LocalTime.of(15, 0));
		sensorCalor.agregarMetrica(20, LocalDate.of(2022, 9, 21), LocalTime.of(18, 30));
		sensorCalor.agregarMetrica(18, LocalDate.of(2022, 9, 22), LocalTime.of(22, 30));
		System.out.println(sensorCalor.getMetricas());

		System.out.println("Test 7");
		System.out.println(sensorCalor.traerMetrica(LocalDate.of(2022, 9, 19), LocalTime.of(12, 30)));

		System.out.println("Test 8");
		System.out.println(sensorCalor.traerMetricas(LocalDate.of(2022, 9, 19), LocalDate.of(2022, 9, 21)));

		System.out.println("Test 9");
		System.out.println(
				sensorCalor.traerMetricas(sensorCalor, LocalDate.of(2022, 9, 19), LocalDate.of(2022, 9, 21), 22));

	}

}
