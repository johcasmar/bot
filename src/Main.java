import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import config.SetUpDriverDevice;
import data.Data;
import data.logExcel2;
import interaccion.Interaccion;
import io.appium.java_client.android.AndroidDriver;
import steps.Step1;
import steps.Step10;
import steps.Step11;
import steps.Step12;
import steps.Step2;
import steps.Step3;
import steps.Step4;
import steps.Step5;
import steps.Step6;
import steps.Step7;
import steps.Step8;
import steps.Step9;
import steps.StepLogin;

/**
 * Se revisa el flujo para Android 5.1.1 Lollipop
 * 
 * @author lroah
 * @version: 31/01/2018
 * 
 *           Se corrige valida.cion en los montos se crea los objetos y sus
 *           respectivos constructores
 * @author cavila
 * @version 22/01/2018
 */

public class Main {
	/*
	 * Se declara publico el dirver(conecta appium) y el Loogger(Captura y envia el
	 * mensaje de consola)
	 */
	public static AndroidDriver<?> driver;
	public static int i = 0;
	public static final Logger logger = LogManager.getLogger(Main.class);

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {

		String conyugue;
		
		Data data = new Data("src\\data\\prueba.xlsx");
		Calendar calendario = new GregorianCalendar();
		double inicio = calendario.get(Calendar.HOUR_OF_DAY) * 3600 + calendario.get(Calendar.MINUTE) * 60
				+ calendario.get(Calendar.SECOND);
		Interaccion.fecha_Ejec();
		data.orquestador();

		try {
			ArrayList<StepLogin> stepLogin = data.constructorObjetoPasoLogin();
			ArrayList<Step1> step1 = data.constructorObjetoPaso1();
			ArrayList<Step2> step2 = data.constructorObjetoPaso2();
			ArrayList<Step3> step3 = data.constructorObjetoPaso3();
			ArrayList<Step4> step4 = data.constructorObjetoPaso4();
			ArrayList<Step5> step5 = data.constructorObjetoPaso5();
			ArrayList<Step6> step6 = data.constructorObjetoPaso6();
			ArrayList<Step7> step7 = data.constructorObjetoPaso7();
			ArrayList<Step8> step8 = data.constructorObjetoPaso8();
			ArrayList<Step9> step9 = data.constructorObjetoPaso9();
			ArrayList<Step10> step10 = data.constructorObjetoPaso10();
			ArrayList<Step11> step11 = data.constructorObjetoPaso11();
			ArrayList<Step12> step12 = data.constructorObjetoPaso12();

			Interaccion.folderEjecucion(driver);
			
			String valAux = "";

			for (i = 0; i < stepLogin.size(); i++) {
				
				stepLogin.get(i).setValLogin("Sin Validar");
				step1.get(i).setValPaso1("Sin Validar");
				step2.get(i).setValPaso2("Sin Validar");				
				step3.get(i).setValPaso3("Sin Validar");
				step4.get(i).setValPaso4("Sin Validar");
				step5.get(i).setValPaso5("Sin Validar");
				step6.get(i).setValPaso6("Sin Validar");
				step7.get(i).setValPaso7("Sin Validar");
				step8.get(i).setValPaso8("Sin Validar");
				step9.get(i).setValPaso9("Sin Validar");
				step10.get(i).setValPaso10("Sin Validar");
				step11.get(i).setValPaso11("Sin Validar");
				step12.get(i).setValPaso12("Sin Validar");
				
				
				driver = SetUpDriverDevice.setUp();			
				
				conyugue = step1.get(i).getConyuge();

				stepLogin.get(i).orquestador(driver);				
				valAux = stepLogin.get(i).getValLogin();
				
				if (valAux.equals("EXITOSO")) {
					step1.get(i).orquestador(driver);
					valAux = step1.get(i).getValPaso1();	
				}	
			if (valAux.equals("EXITOSO")) {
					step2.get(i).orquestador(driver);
					valAux = step2.get(i).getValPaso2();
				}
				if (valAux.equals("EXITOSO")) {
					step3.get(i).orquestador(driver);
					valAux = step3.get(i).getValPaso3();
				}
				if (valAux.equals("EXITOSO")) {
					if (conyugue.equalsIgnoreCase("Si")) {
						step4.get(i).orquestador(driver);
						valAux = step4.get(i).getValPaso4();
					}
				}
				if (valAux.equals("EXITOSO")) {
					step5.get(i).orquestador(driver);
					valAux = step5.get(i).getValPaso5();
				}
				if (valAux.equals("EXITOSO")) {
					step6.get(i).orquestador(driver);
					valAux = step6.get(i).getValPaso6();
				}
				if (valAux.equals("EXITOSO")) {
					step7.get(i).orquestador(driver);
					valAux = step7.get(i).getValPaso7();
				}
				if (valAux.equals("EXITOSO")) {
					step8.get(i).orquestador(driver);
					valAux = step8.get(i).getValPaso8();
				}
				if (valAux.equals("EXITOSO")) {
					step9.get(i).orquestador(driver);
					valAux = step9.get(i).getValPaso9();
				}
				if (valAux.equals("EXITOSO")) {
					step10.get(i).orquestador(driver);
					valAux = step10.get(i).getValPaso10();
				}
				if (valAux.equals("EXITOSO")) {
					step11.get(i).orquestador(driver);
					valAux = step11.get(i).getValPaso11();
				}
				if (valAux.equals("EXITOSO")) {
					step12.get(i).orquestador(driver);
					valAux = step12.get(i).getValPaso12();
				}

				calendario = new GregorianCalendar();
				double fin = calendario.get(Calendar.HOUR_OF_DAY) * 3600 + calendario.get(Calendar.MINUTE) * 60
						+ calendario.get(Calendar.SECOND);
				double time = fin - inicio;
				logger.info("Ejecucion terminada con exito en: " + time + "seg");
				driver.quit();
				logExcel2.cargarVariable(i);

			}
			logExcel2.generarExcel();

		} catch (Exception e) {
			logExcel2.cargarVariable(i-1);
			logExcel2.generarExcel();
			logger.error("Error Inesperado, Validar Informacion Excel, y parametros de configuracion" + e);
			driver.quit();
			System.exit(-1);
		}

	}
}
