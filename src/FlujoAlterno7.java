import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import config.SetUpDriverDevice;
import data.Data;
import data.logExcel2;
import interaccion.Interaccion;
import io.appium.java_client.android.AndroidDriver;
import steps.Step1;
import steps.Step2;
import steps.Step3;
import steps.Step4;
import steps.Step5;
import steps.Step6;
import steps.Step7;
import steps.Step8;
import steps.StepLogin;

/**
 * Esta clase pertenece al flujo alterno 7 cancelación de solicitud en el paso 8
 * 
 * @author avelandiap
 *
 */
public class FlujoAlterno7 {

	public static AndroidDriver<?> driver;

	public static void main(String[] args) throws Exception {

		Calendar calendario = new GregorianCalendar();
		double inicio = calendario.get(Calendar.HOUR_OF_DAY) * 3600 + calendario.get(Calendar.MINUTE) * 60
				+ calendario.get(Calendar.SECOND);
		Interaccion.fecha_Ejec();

		Data data = new Data("src\\data\\prueba.xlsx");

		data.orquestador();
		ArrayList<StepLogin> stepLogin = data.constructorObjetoPasoLogin();
		ArrayList<Step1> step1 = data.constructorObjetoPaso1();
		ArrayList<Step2> step2 = data.constructorObjetoPaso2();
		ArrayList<Step3> step3 = data.constructorObjetoPaso3();
		ArrayList<Step4> step4 = data.constructorObjetoPaso4();
		ArrayList<Step5> step5 = data.constructorObjetoPaso5();
		ArrayList<Step6> step6 = data.constructorObjetoPaso6();
		ArrayList<Step7> step7 = data.constructorObjetoPaso7();
		ArrayList<Step8> step8 = data.constructorObjetoPaso8();

		for (int i = 0; i < stepLogin.size(); i++) {
			driver = SetUpDriverDevice.setUp();
			stepLogin.get(i).orquestador(driver);
			step1.get(i).orquestador(driver);
			step2.get(i).orquestador(driver);
			step3.get(i).orquestador(driver);
			step4.get(i).orquestador(driver);
			step5.get(i).orquestador(driver);
			step6.get(i).orquestador(driver);
			step7.get(i).orquestador(driver);
			/*
			 * Logica flujo anterno cancelacion.
			 */
			step8.get(i).referencias(driver);
			step8.get(i).cancelacion(driver);
			driver.quit();
			calendario = new GregorianCalendar();
			double fin = calendario.get(Calendar.HOUR_OF_DAY) * 3600 + calendario.get(Calendar.MINUTE) * 60
					+ calendario.get(Calendar.SECOND);
			double time = fin - inicio;
			System.out.println("Ejecucion terminada con exito en: " + time + "seg");
			logExcel2.cargarVariable(i);
		}
		logExcel2.generarExcel();
	}
}