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
 * Esta clase ejecuta la prueba del quinto paso para crear una nueva solicitud
 * donde diligenciamos un formulario con la informaci�n Sociodemografica del
 * Microempresario.
 * 
 * @author: Sergio Ruiz.
 * @version: 03/01/18
 */

public class Step5 implements IStep {

	private String seguridadSocial;
	private String vivienda;
	private String viviendaHipotecada;
	private String mesesResidencia;
	private String cabezaHogar;
	private String nPersonas;
	public static String valPaso5 = "Sin Validar";

	final static Logger logger = LogManager.getLogger(Step5.class);
	final static Properties p = new Properties();	

	/**
	 * @param seguridadSocial
	 * @param vivienda
	 * @param viviendaHipotecada
	 * @param mesesResidencia
	 * @param cabezaHogar
	 * @param nPersonas
	 */
	public Step5(String seguridadSocial, String vivienda, String viviendaHipotecada, String mesesResidencia,
			String cabezaHogar, String nPersonas) {
		this.seguridadSocial = seguridadSocial.trim();
		this.vivienda = vivienda.trim();
		this.viviendaHipotecada = viviendaHipotecada.trim();
		this.mesesResidencia = mesesResidencia.trim();
		this.cabezaHogar = cabezaHogar.trim();
		this.nPersonas = nPersonas.trim();
	}

	/**
	 * @return the seguridadSocial
	 */
	public String getSeguridadSocial() {
		return seguridadSocial;
	}

	/**
	 * @param seguridadSocial
	 *            the seguridadSocial to set
	 */
	public void setSeguridadSocial(String seguridadSocial) {
		this.seguridadSocial = seguridadSocial;
	}

	/**
	 * @return the vivienda
	 */
	public String getVivienda() {
		return vivienda;
	}

	/**
	 * @param vivienda
	 *            the vivienda to set
	 */
	public void setVivienda(String vivienda) {
		this.vivienda = vivienda;
	}

	/**
	 * @return the viviendaHipotecada
	 */
	public String getViviendaHipotecada() {
		return viviendaHipotecada;
	}

	/**
	 * @param viviendaHipotecada
	 *            the viviendaHipotecada to set
	 */
	public void setViviendaHipotecada(String viviendaHipotecada) {
		this.viviendaHipotecada = viviendaHipotecada;
	}

	/**
	 * @return the mesesResidencia
	 */
	public String getMesesResidencia() {
		return mesesResidencia;
	}

	/**
	 * @param mesesResidencia
	 *            the mesesResidencia to set
	 */
	public void setMesesResidencia(String mesesResidencia) {
		this.mesesResidencia = mesesResidencia;
	}

	/**
	 * @return the cabezaHogar
	 */
	public String getCabezaHogar() {
		return cabezaHogar;
	}

	/**
	 * @param cabezaHogar
	 *            the cabezaHogar to set
	 */
	public void setCabezaHogar(String cabezaHogar) {
		this.cabezaHogar = cabezaHogar;
	}

	/**
	 * @return the nPersonas
	 */
	public String getnPersonas() {
		return nPersonas;
	}

	/**
	 * @param nPersonas
	 *            the nPersonas to set
	 */
	public void setnPersonas(String nPersonas) {
		this.nPersonas = nPersonas;
	}

	public static String getValPaso5() {
		return valPaso5;
	}

	public static void setValPaso5(String valPaso5) {
		Step5.valPaso5 = valPaso5;
	}

	/**
	 * @param driver
	 * @throws Exception
	 */
	public void infoSociodemografica(AndroidDriver<?> driver) throws Exception {
		p.load(new FileReader("src\\config\\config.properties"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("social_security"))));

		try {
			logger.info("Iniciando Inspeccion e Insercion de datos en el Paso5");
			Interaccion.photo_Evidencia(driver, "Step5");

			driver.findElement(By.id("social_security")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("//*[@text='" + seguridadSocial + "']")).click();
			driver.findElement(By.id("tenement_type")).click();
			driver.findElement(By.xpath("//*[@text='" + vivienda + "']")).click();
			driver.findElement(By.id("mortgaged_property")).click();
			driver.findElement(By.xpath("//*[@text='" + viviendaHipotecada + "']")).click();
			driver.findElement(By.id("senority_residence")).click();
			driver.findElement(By.id("senority_residence")).sendKeys(mesesResidencia);
			Interaccion.ocultarTeclado(driver);
			driver.findElement(By.id("head_home")).click();
			driver.findElement(By.xpath("//*[@text='" + cabezaHogar + "']")).click();
			driver.findElement(By.id("number_people")).click();
			listNPersonas(driver);
			logger.info("Insercion de datos satisfacrtoria del paso 5 ");
			Interaccion.photo_Evidencia(driver, "Step5_Reg");
			driver.findElement(By.id(p.getProperty("btnNext"))).click();

			try {
				logger.info("CARGANDO VALIDACION DE LOS DATOS INSERTADOS EN EL PASO 5");
				logger.info("Validando datos en el campo " + seguridadSocial);
				driver.findElement(By.xpath("//*[@text='" + seguridadSocial + "']"));
				logger.info("Validando datos en el campo " + vivienda);
				driver.findElement(By.xpath("//*[@text='" + vivienda + "']"));
				logger.info("Validando datos en el campo " + viviendaHipotecada);
				driver.findElement(By.xpath("//*[@text='" + viviendaHipotecada + "']"));
				logger.info("Validando datos en el campo " + mesesResidencia);
				driver.findElement(By.xpath("//*[@text='" + mesesResidencia + "']"));
				logger.info("Validando datos en el campo " + cabezaHogar);
				driver.findElement(By.xpath("//*[@text='" + cabezaHogar + "']"));
				logger.info("Validando datos en el campo " + nPersonas);
				driver.findElement(By.xpath("//*[@text='" + nPersonas + "']"));
				Interaccion.photo_Evidencia(driver, "Step5_Val");
				driver.findElement(By.xpath(p.getProperty("btnContinuar"))).click(); // probar si es next o continuar
				logger.info("Comprobacion de datos satisfactorios en el paso 5 \n");
				setValPaso5("EXITOSO");

			} catch (Exception e) {
				Interaccion.photo_Evid_Fallida(driver, "Paso 5 msm Confirmar");
				logger.error("No se pudieron validar los datos insertados " + Interaccion.mensajeError(e));

			}

		} catch (Exception e) {
			setValPaso5("FALLO");
			Interaccion.photo_Evid_Fallida(driver, "Paso 5 ");
			logger.error("Error insercion de datos fallida " + Interaccion.mensajeError(e));
		}
	}

	public void listNPersonas(AndroidDriver<?> driver) {
		MobileElement listElement = null;
		try {
			AndroidElement list = (AndroidElement) driver
					.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.ListView"));
			listElement = list
					.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
							+ "new UiSelector().text(\"" + nPersonas + "\"));"));
		} catch (Exception e) {
			Interaccion.photo_Evid_Fallida(driver, "Step5_listNPers");
			logger.error("Problema con identificar el #Personas \n");
		}
		listElement.click();
	}

	/**
	 * @param driver
	 * @throws Exception
	 */
	public void orquestador(AndroidDriver<?> driver) throws Exception {
		infoSociodemografica(driver);
	}

}