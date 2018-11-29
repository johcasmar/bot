package steps;

import java.io.FileReader;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interaccion.Interaccion;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

/**
 * Actualizacion de metodos y mensajes del log
 * 
 * @author lroah
 * @version 27/01/18
 */

/**
 * 
 * @author Sergio Ruiz
 * @version 05/01/18
 */

public class Step12 implements IStep {

	private String cartera;
	private String inventario;
	private String activoFijo;
	private String pasivosMicroEmpr;
	private String activosFamilia;
	private String pasivosFamili;
	public static String valPaso12 = "Sin Validar";

	final static Logger logger = LogManager.getLogger(Step12.class);
	final static Properties p = new Properties();

	/**
	 * @param cartera
	 * @param inventario
	 * @param activoFijo
	 * @param pasivosMicroEmpr
	 * @param activosFamilia
	 * @param pasivosFamili
	 */
	public Step12(String cartera, String inventario, String activoFijo, String pasivosMicroEmpr, String activosFamilia,
			String pasivosFamili) {
		this.cartera = cartera.trim();
		this.inventario = inventario.trim();
		this.activoFijo = activoFijo.trim();
		this.pasivosMicroEmpr = pasivosMicroEmpr.trim();
		this.activosFamilia = activosFamilia.trim();
		this.pasivosFamili = pasivosFamili.trim();
	}

	/**
	 * @return the cartera
	 */
	public String getCartera() {
		return cartera;
	}

	/**
	 * @param cartera
	 *            the cartera to set
	 */
	public void setCartera(String cartera) {
		this.cartera = cartera;
	}

	/**
	 * @return the inventario
	 */
	public String getInventario() {
		return inventario;
	}

	/**
	 * @param inventario
	 *            the inventario to set
	 */
	public void setInventario(String inventario) {
		this.inventario = inventario;
	}

	/**
	 * @return the activoFijo
	 */
	public String getActivoFijo() {
		return activoFijo;
	}

	/**
	 * @param activoFijo
	 *            the activoFijo to set
	 */
	public void setActivoFijo(String activoFijo) {
		this.activoFijo = activoFijo;
	}

	/**
	 * @return the pasivosMicroEmpr
	 */
	public String getPasivosMicroEmpr() {
		return pasivosMicroEmpr;
	}

	/**
	 * @param pasivosMicroEmpr
	 *            the pasivosMicroEmpr to set
	 */
	public void setPasivosMicroEmpr(String pasivosMicroEmpr) {
		this.pasivosMicroEmpr = pasivosMicroEmpr;
	}

	/**
	 * @return the activosFamilia
	 */
	public String getActivosFamilia() {
		return activosFamilia;
	}

	/**
	 * @param activosFamilia
	 *            the activosFamilia to set
	 */
	public void setActivosFamilia(String activosFamilia) {
		this.activosFamilia = activosFamilia;
	}

	/**
	 * @return the pasivosFamili
	 */
	public String getPasivosFamili() {
		return pasivosFamili;
	}

	/**
	 * @param pasivosFamili
	 *            the pasivosFamili to set
	 */
	public void setPasivosFamili(String pasivosFamili) {
		this.pasivosFamili = pasivosFamili;
	}

	public static String getValPaso12() {
		return valPaso12;
	}

	public static void setValPaso12(String valPaso12) {
		Step12.valPaso12 = valPaso12;
	}

	/**
	 * @param driver
	 * @throws Exception
	 */
	public void pagoMicroempresario(AndroidDriver<?> driver) throws Exception {
		p.load(new FileReader("src\\config\\config.properties"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("portafolio"))));

		try {
			logger.info("Iniciando Inspeccion e Insercion de datos en el Paso12");
			Interaccion.photo_Evidencia(driver, "Step12");

			Interaccion.insertarMonto(driver, "portafolio", cartera);
			Interaccion.insertarMonto(driver, "inventory", inventario);
			Interaccion.insertarMonto(driver, "fixed_assets", activoFijo);
			Interaccion.insertarMonto(driver, "liabilities", pasivosMicroEmpr);
			Interaccion.ocultarTeclado(driver);
			Interaccion.insertarMonto(driver, "assets_family", activosFamilia);
			Interaccion.insertarMonto(driver, "liabilities_family", pasivosFamili);
			Interaccion.ocultarTeclado(driver);
			logger.info("Inserccion de datos satisfactoria del paso 12 ");
			Interaccion.photo_Evidencia(driver, "Step12_Reg");
			driver.findElement(By.id(p.getProperty("btnNext"))).click();

			try {
				logger.info("CARGANDO VALIDACION DE LOS DATOS INSERTADOS EN EL PASO 12");
				logger.info("Validando datos en el campo " + Interaccion.ponerPuntos(cartera));
				driver.findElement(By.xpath("//*[@text='" + Interaccion.ponerPuntos(cartera) + "']"));
				logger.info("Validando datos en el campo " + Interaccion.ponerPuntos(inventario));
				driver.findElement(By.xpath("//*[@text='" + Interaccion.ponerPuntos(inventario) + "']"));
				logger.info("Validando datos en el campo " + Interaccion.ponerPuntos(activoFijo));
				driver.findElement(By.xpath("//*[@text='" + Interaccion.ponerPuntos(activoFijo) + "']"));
				logger.info("Validando datos en el campo " + Interaccion.ponerPuntos(pasivosMicroEmpr));
				driver.findElement(By.xpath("//*[@text='" + Interaccion.ponerPuntos(pasivosMicroEmpr) + "']"));
				logger.info("Validando datos en el campo " + Interaccion.ponerPuntos(activosFamilia));
				driver.findElement(By.xpath("//*[@text='" + Interaccion.ponerPuntos(activosFamilia) + "']"));
				logger.info("Validando datos en el campo " + Interaccion.ponerPuntos(pasivosFamili));
				driver.findElement(By.xpath("//*[@text='" + Interaccion.ponerPuntos(pasivosFamili) + "']"));
				Interaccion.photo_Evidencia(driver, "Step12_Val");
				driver.findElement(By.xpath(p.getProperty("btnContinuar"))).click();
				logger.info("Comprobacion de datos satisfactorios en el paso 12 \n");
				setValPaso12("EXITOSO");

				try {
					p.load(new FileReader("src\\config\\config.properties"));
					Interaccion.photo_Evidencia(driver, "Step12 Primer Modal ");
					logger.info("Valida modal paso 12");
					wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(p.getProperty("btnAceptar"))))).click();
						
					Interaccion.photo_Evidencia(driver, "Paso 12 Primer Modal");


				} catch (Exception e) {

					Interaccion.photo_Evid_Fallida(driver, "Paso 12 Modal No Encontrado");
					logger.error("No se logro validar el mensaje");
				}

				Interaccion.photo_Evidencia(driver, "Paso 12");
				
				try {
					p.load(new FileReader("src\\config\\config.properties"));
					Interaccion.photo_Evidencia(driver, "Step12 Segundo Modal ");
					logger.info("Valida modal paso 12");
					wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(p.getProperty("btnAceptar"))))).click();
						
					Interaccion.photo_Evidencia(driver, "Paso 12 Segundo Modal");


				} catch (Exception e) {

					Interaccion.photo_Evid_Fallida(driver, "Paso 12 Modal No Encontrado");
					logger.error("No se logro validar el mensaje");
				}

				Interaccion.photo_Evidencia(driver, "Paso 12");

				
				
				
				try {
					p.load(new FileReader("src\\config\\config.properties"));
					Interaccion.photo_Evidencia(driver, "Step 13");
					WebDriverWait wait2 = new WebDriverWait(driver, 10);
					wait2.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(p.getProperty("btnFinalizar")))))
							.click();
					logger.info("Finalizacion del paso # 12");

				} catch (Exception e) {
					Interaccion.photo_Evid_Fallida(driver, "Step13 Boton Finalizar");
					logger.info("No se encontro el boton Finalizar");
				}

			} catch (Exception e) {
				setValPaso12("FALLO");
				Interaccion.photo_Evid_Fallida(driver, "Paso 12 msm Confirmar");
				logger.error("No se pudieron validar los datos insertados " + Interaccion.mensajeError(e));
			}

		} catch (Exception e) {
			setValPaso12("FALLO");
			Interaccion.photo_Evid_Fallida(driver, "Paso 12 ");
			logger.error("Error insercion de datos fallida " + Interaccion.mensajeError(e));

		}

	}

	public void listValidacion(AndroidDriver<?> driver) {

		try {
			p.load(new FileReader("src\\config\\config.properties"));

			(new TouchAction(driver)).press(Integer.parseInt(p.getProperty("valPntIniX")),
					Integer.parseInt(p.getProperty("valPntIniY"))).
			moveTo(Integer.parseInt(p.getProperty("valPntFinX")), 
					Integer.parseInt(p.getProperty("valPntFinY"))).release().perform();
		} catch (Exception e) {
			Interaccion.photo_Evid_Fallida(driver, "Step12_listValid");
			logger.error("Problema con el desplazamiento \n ");
		}

	}

	@Override
	public void orquestador(AndroidDriver<?> driver) throws Exception {
		pagoMicroempresario(driver);

	}

}
