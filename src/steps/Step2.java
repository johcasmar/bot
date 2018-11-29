package steps;

import java.io.FileReader;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interaccion.Interaccion;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * Esta clase ejecuta la prueba del segundo paso para crear una nueva solicitud
 * donde diligenciamos un formulario con la informaci�n de la solictud.
 * 
 * @author: Sergio Ruiz.
 * @version: 03/01/18
 * 
 * 
 * Se corrige implementacion  
 * @author cavila
 * @version 22/01/2018
 * 
 * 
 * Se actualizan los metodos para que funcione en la nueva version de android 6.0
 * 
 * @author lroah
 * @version 31/01/2018
 */

public class Step2 implements IStep {

	private String monto;
	private String cuota;
	private String antiguedad;
	private String plazo;
	private String modalidad;
	private String amortizacion;
	private static String valPaso2="Sin validar";
	
	final static Logger logger = LogManager.getLogger(Step2.class);
	final static Properties p = new Properties();
	


	/**
	 * @param monto
	 * @param cuota
	 * @param antiguedad
	 * @param plazo
	 * @param modalidad
	 * @param amortizacion
	 */
	public Step2(String monto, String cuota, String antiguedad, String plazo, String modalidad, String amortizacion) {
		this.monto = monto.trim();
		this.cuota = cuota.trim();
		this.antiguedad = antiguedad.trim();
		this.plazo = plazo.trim();
		this.modalidad = modalidad.trim();
		this.amortizacion = amortizacion.trim();
	}

	/**
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * @param monto
	 *            the monto to set
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}

	/**
	 * @return the cuota
	 */
	public String getCuota() {
		return cuota;
	}

	/**
	 * @param cuota
	 *            the cuota to set
	 */
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}

	/**
	 * @return the antiguedad
	 */
	public String getAntiguedad() {
		return antiguedad;
	}

	/**
	 * @param antiguedad
	 *            the antiguedad to set
	 */
	public void setAntiguedad(String antiguedad) {
		this.antiguedad = antiguedad;
	}

	/**
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * @param plazo
	 *            the plazo to set
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * @return the modalidad
	 */
	public String getModalidad() {
		return modalidad;
	}

	/**
	 * @param modalidad
	 *            the modalidad to set
	 */
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	/**
	 * @return the amortizacion
	 */
	public String getAmortizacion() {
		return amortizacion;
	}

	/**
	 * @param amortizacion
	 *            the amortizacion to set
	 */
	public void setAmortizacion(String amortizacion) {
		this.amortizacion = amortizacion;
	}

		public static String getValPaso2() {
		return valPaso2;
	}

	public static void setValPaso2(String valPaso2) {
		Step2.valPaso2 = valPaso2;
	}

	
	
	/**
	 * Realiza la insercion y validacion del 2° segundo paso
	 * @param driver
	 * @throws Exception 
	 */
	
	
	public void infoSolicitud(AndroidDriver<?> driver) throws Exception {
		p.load(new FileReader("src\\config\\config.properties"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("monto"))));

		try {
			logger.info("Iniciando Inspeccion e Insercion de datos en el Paso2");
			Interaccion.photo_Evidencia(driver, "Step2");
			Interaccion.insertarMonto(driver, "monto", monto);
			Interaccion.insertarMonto(driver, "cuota", cuota);
			driver.findElement(By.id("antiguedad")).click();
			driver.findElement(By.xpath("//*[@text='" + antiguedad + "']")).click();
			driver.findElement(By.id("plazo")).click();
			listPlazo(driver);
			Interaccion.ocultarTeclado(driver);
			driver.findElement(By.id("modalidad")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("//*[@text='" + modalidad + "']")).click();
			driver.findElement(By.id("amortization")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("//*[@text='" + amortizacion + "']")).click();
			Interaccion.photo_Evidencia(driver, "Step2_Reg");	
			driver.findElement(By.id(p.getProperty("btnNext"))).click();
			logger.info("Inserccion de datos satisfactoria del paso 2 ");
			
			try {
				logger.info("CARGANDO VALIDACION DE LOS DATOS INSERTADOS EN EL PASO 2");
				logger.info("Validando datos en el campo " +monto );
				driver.findElement(By.xpath("//*[@text='" + Interaccion.ponerPuntos(monto) + "']"));
				logger.info("Validando datos en el campo " +cuota );
				driver.findElement(By.xpath("//*[@text='" + Interaccion.ponerPuntos(cuota) + "']"));
				logger.info("Validando datos en el campo " +antiguedad);
				driver.findElement(By.xpath("//*[@text='" + antiguedad + "']"));
				logger.info("Validando datos en el campo " +plazo);
				driver.findElement(By.xpath("//*[@text='" + plazo + "']"));
				logger.info("Validando datos en el campo " +modalidad);
				driver.findElement(By.xpath("//*[@text='" + modalidad + "']"));
				logger.info("Validando datos en el campo " +amortizacion);
				driver.findElement(By.xpath("//*[@text='" + amortizacion + "']"));
				Interaccion.photo_Evidencia(driver, "Step2_Val");
				logger.info("Validacion de datos satisfacrtoria en el paso 2 \n");
				driver.findElement(By.xpath(p.getProperty("btnContinuar"))).click();
				setValPaso2("EXITOSO");
				
			} catch (Exception e) {
				setValPaso2("FALLO");
				Interaccion.photo_Evid_Fallida(driver, "Paso 2 msm Confirmar");
				logger.error("No se pudieron validar los datos insertados "+Interaccion.mensajeError(e) );				
				
			}

		} catch (Exception e) {
			setValPaso2("FALLO");
			Interaccion.photo_Evid_Fallida(driver, "Paso 2 ");
			logger.error("Error en la insercion de datos " + Interaccion.mensajeError(e));
						
		}
	}

	/**
	 * Realiza el desplazamiento dentro del combo plazo y selecciona el texto indicado en el excel
	 * @param driver
	 */
	public void listPlazo(AndroidDriver<?> driver) {
		MobileElement listElement = null;
		try {
			AndroidElement list = (AndroidElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.ListView"));
			listElement = list.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("	+ "new UiSelector().text(\"" + plazo + "\"));"));
		} catch (Exception e) {
			Interaccion.photo_Evid_Fallida(driver, "Step2_listPlazo");
			logger.error("Problema con identificar el Plazo \n ");
			setValPaso2("FALLO");
		}
		listElement.click();
	}

	/**Realiza el desplazamiento dentro del combo Amortizacion y selecciona el texto indicado en el excel
	 * @param driver
	 */
	public void listAmortizacion(AndroidDriver<?> driver) {
		MobileElement listElement = null;
		try {
			AndroidElement list = (AndroidElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.ListView"));
		
			listElement = list
					.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
							+ "new UiSelector().text(\"" + amortizacion + "\"));"));
		} catch (Exception e) {
			Interaccion.photo_Evid_Fallida(driver, "Step2_listAmortiza");
			logger.error("Problema con identificar la Amortizacion \n ");
			setValPaso2("FALLO");
		}

		listElement.click();
	}
	
	/**
	 * @param driver
	 * @throws Exception 
	 */
	public void orquestador(AndroidDriver<?> driver) throws Exception {
		infoSolicitud(driver);
	}


}