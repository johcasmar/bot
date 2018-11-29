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
 * 
 * @author avelandiap
 * @version 04/01/18
 */

public class Step6 implements IStep {

	private String sectorEconomico;
	private String actividadEconomica;
	private String tipoLocal;
	private String tipoEmpleados;
	private String nClientes;
	private String tipoNegocio;
	private String dedicacionNegocio;
	private String mensajeEsperado;
	public static String valPaso6 = "Sin Validar";

	final static Logger logger = LogManager.getLogger(Step6.class);
	final static Properties p = new Properties();

	/**
	 * @param sectorEconomico
	 * @param actividadEconomica
	 * @param tipoLocal
	 * @param tipoEmpleados
	 * @param nClientes
	 * @param tipoNegocio
	 * @param dedicacionNegocio
	 */
	public Step6(String sectorEconomico, String actividadEconomica, String tipoLocal, String tipoEmpleados,
			String nClientes, String tipoNegocio, String dedicacionNegocio, String mensajeEsperado) {
		this.sectorEconomico = sectorEconomico.trim();
		this.actividadEconomica = actividadEconomica.trim();
		this.tipoLocal = tipoLocal.trim();
		this.tipoEmpleados = tipoEmpleados.trim();
		this.nClientes = nClientes.trim();
		this.tipoNegocio = tipoNegocio.trim();
		this.dedicacionNegocio = dedicacionNegocio.trim();
		this.mensajeEsperado = mensajeEsperado.trim();
	}

	/**
	 * @return the sectorEconomico
	 */
	public String getSectorEconomico() {
		return sectorEconomico;
	}

	/**
	 * @param sectorEconomico
	 *            the sectorEconomico to set
	 */
	public void setSectorEconomico(String sectorEconomico) {
		this.sectorEconomico = sectorEconomico;
	}

	/**
	 * @return the actividadEconomica
	 */
	public String getActividadEconomica() {
		return actividadEconomica;
	}

	/**
	 * @param actividadEconomica
	 *            the actividadEconomica to set
	 */
	public void setActividadEconomica(String actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}

	/**
	 * @return the tipoLocal
	 */
	public String getTipoLocal() {
		return tipoLocal;
	}

	/**
	 * @param tipoLocal
	 *            the tipoLocal to set
	 */
	public void setTipoLocal(String tipoLocal) {
		this.tipoLocal = tipoLocal;
	}

	/**
	 * @return the tipoEmpleados
	 */
	public String getTipoEmpleados() {
		return tipoEmpleados;
	}

	/**
	 * @param tipoEmpleados
	 *            the tipoEmpleados to set
	 */
	public void setTipoEmpleados(String tipoEmpleados) {
		this.tipoEmpleados = tipoEmpleados;
	}

	/**
	 * @return the nClientes
	 */
	public String getnClientes() {
		return nClientes;
	}

	/**
	 * @param nClientes
	 *            the nClientes to set
	 */
	public void setnClientes(String nClientes) {
		this.nClientes = nClientes;
	}

	/**
	 * @return the tipoNegocio
	 */
	public String getTipoNegocio() {
		return tipoNegocio;
	}

	/**
	 * @param tipoNegocio
	 *            the tipoNegocio to set
	 */
	public void setTipoNegocio(String tipoNegocio) {
		this.tipoNegocio = tipoNegocio;
	}

	/**
	 * @return the dedicacionNegocio
	 */
	public String getDedicacionNegocio() {
		return dedicacionNegocio;
	}

	/**
	 * @param dedicacionNegocio
	 *            the dedicacionNegocio to set
	 */
	public void setDedicacionNegocio(String dedicacionNegocio) {
		this.dedicacionNegocio = dedicacionNegocio;
	}

	public static String getValPaso6() {
		return valPaso6;
	}

	public static void setValPaso6(String valPaso6) {
		Step6.valPaso6 = valPaso6;
	}

	/**
	 * @param driver
	 * @throws Exception
	 */
	public void infoActividad(AndroidDriver<?> driver) throws Exception {
		p.load(new FileReader("src\\config\\config.properties"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("eco_sector"))));

		try {
			logger.info("Iniciando Inspeccion e Insercion de datos en el Paso6");
			Interaccion.photo_Evidencia(driver, "Step6");

			driver.findElement(By.id("eco_sector")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("//*[@text='" + sectorEconomico + "']")).click();
			driver.findElement(By.id("eco_act")).click();
			Thread.sleep(500);
			listActividad(driver);
			driver.findElement(By.id("local_type")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("//*[@text='" + tipoLocal + "']")).click();
			driver.findElement(By.id("employee_type")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("//*[@text='" + tipoEmpleados + "']")).click();
			driver.findElement(By.id("number_customer")).click();
			driver.findElement(By.id("number_customer")).sendKeys(nClientes);
			Interaccion.ocultarTeclado(driver);
			driver.findElement(By.id("business_type")).click();
			driver.findElement(By.xpath("//*[@text='" + tipoNegocio + "']")).click();
			driver.findElement(By.id("dedication_business")).click();
			listDedicacion(driver);
			logger.info("Insercion de datos satisfacrtoria del paso 6 ");
			Interaccion.photo_Evidencia(driver, "Step6_Reg");
			driver.findElement(By.id(p.getProperty("btnNext"))).click();

			try {
				logger.info("CARGANDO VALIDACION DE LOS DATOS INSERTADOS EN EL PASO 6");
				logger.info("Validando datos en el campo " + sectorEconomico);
				driver.findElement(By.xpath("//*[@text='" + sectorEconomico + "']"));
				logger.info("Validando datos en el campo " + actividadEconomica);
				driver.findElement(By.xpath("//*[@text='" + actividadEconomica + "']"));
				logger.info("Validando datos en el campo " + tipoLocal);
				driver.findElement(By.xpath("//*[@text='" + tipoLocal + "']"));
				logger.info("Validando datos en el campo " + tipoEmpleados);
				driver.findElement(By.xpath("//*[@text='" + tipoEmpleados + "']"));
				logger.info("Validando datos en el campo " + nClientes);
				driver.findElement(By.xpath("//*[@text='" + nClientes + "']"));
				logger.info("Validando datos en el campo " + tipoNegocio);
				driver.findElement(By.xpath("//*[@text='" + tipoNegocio + "']"));
				logger.info("Validando datos en el campo " + dedicacionNegocio);
				driver.findElement(By.xpath("//*[@text='" + dedicacionNegocio + "']"));
				Interaccion.photo_Evidencia(driver, "Step6_Val");
				driver.findElement(By.xpath(p.getProperty("btnContinuar"))).click();
				Thread.sleep(1000);
				logger.info("Comprobacion de datos satisfactorios en el paso 6 \n");
				setValPaso6("EXITOSO");

				try {
					Interaccion.photo_Evidencia(driver, "Step6 ValId");
					driver.findElement(By.xpath("//*[@text='" + mensajeEsperado + "']"));
				} catch (Exception e) {
					logger.error("Error mensaje ->" + mensajeEsperado + " No fue encontrado \n");
				}
				
				
				try {
					driver.findElement(By.xpath(p.getProperty("btnAceptar"))).click();

				} catch (Exception e) {
					setValPaso6("FALLO");
					logger.error("No se pudo pulsar el boton Aceptar del buro \n" + Interaccion.mensajeError(e));
				}

			} catch (Exception e) {
				Interaccion.photo_Evid_Fallida(driver, "Paso 6 msm Confirmar");
				logger.error("No se pudieron validar los datos insertados " + Interaccion.mensajeError(e));

			}

		} catch (Exception e) {
			setValPaso6("FALLO");
			Interaccion.photo_Evid_Fallida(driver, "Paso 6 ");
			logger.error("Error insercion de datos fallida " + Interaccion.mensajeError(e));

		}

	}

	public void listActividad(AndroidDriver<?> driver) {
		MobileElement listElement = null;
		try {
			AndroidElement list = (AndroidElement) driver
					.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.ListView"));
			listElement = list
					.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
							+ "new UiSelector().text(\"" + actividadEconomica + "\"));"));
		} catch (Exception e) {
			Interaccion.photo_Evid_Fallida(driver, "Step6_listDedicar");
			logger.error("Problema con identificar el combo dedicar \n");
		}
		listElement.click();
	}

	public void listDedicacion(AndroidDriver<?> driver) {
		MobileElement listElement = null;
		try {
			AndroidElement list = (AndroidElement) driver
					.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.ListView"));
			listElement = list
					.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
							+ "new UiSelector().text(\"" + dedicacionNegocio + "\"));"));
		} catch (Exception e) {
			Interaccion.photo_Evid_Fallida(driver, "Step6_listDedicar");
			logger.error("Problema con identificar el combo dedicar \n");
		}
		listElement.click();
	}

	@Override
	public void orquestador(AndroidDriver<?> driver) throws Exception {
		infoActividad(driver);

	}

}
