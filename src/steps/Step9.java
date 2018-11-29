package steps;

import java.io.FileReader;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interaccion.Interaccion;
import io.appium.java_client.android.AndroidDriver;

/**
 * 
 * @author avelandiap
 *
 */

public class Step9 implements IStep {

	private String puntaje;
	private String tasaInteres;
	private String monto;
	private String cuota;
	public static String valPaso9 = "Sin Validar";

	final static Logger logger = LogManager.getLogger(Step9.class);
	final static Properties p = new Properties();

	/**
	 * @param puntaje
	 * @param tasaInteres
	 * @param monto
	 * @param cuota
	 */
	public Step9(String puntaje, String tasaInteres, String monto, String cuota) {
		this.puntaje = puntaje.trim();
		this.tasaInteres = tasaInteres.trim();
		this.monto = monto.trim();
		this.cuota = cuota.trim();
	}

	/**
	 * @return the puntaje
	 */
	public String getPuntaje() {
		return puntaje;
	}

	/**
	 * @param puntaje
	 *            the puntaje to set
	 */
	public void setPuntaje(String puntaje) {
		this.puntaje = puntaje;
	}

	/**
	 * @return the tasaInteres
	 */
	public String getTasaInteres() {
		return tasaInteres;
	}

	/**
	 * @param tasaInteres
	 *            the tasaInteres to set
	 */
	public void setTasaInteres(String tasaInteres) {
		this.tasaInteres = tasaInteres;
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

	public static String getValPaso9() {
		return valPaso9;
	}

	public static void setValPaso9(String valPaso9) {
		Step9.valPaso9 = valPaso9;
	}

	public void analisisAsesor(AndroidDriver<?> driver) throws Exception {
		p.load(new FileReader("src\\config\\config.properties"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("score"))));

		try {
			logger.info("Iniciando Inspeccion e Insercion de datos en el Paso9");
			Interaccion.photo_Evidencia(driver, "Step9");

			driver.findElement(By.id("score")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("//*[@text='" + puntaje + "']")).click();
			driver.findElement(By.id("tasa_interes")).click();
			driver.findElement(By.id("tasa_interes")).sendKeys(tasaInteres);
			Interaccion.insertarMonto(driver, "monto_otorgado", monto);
			Interaccion.insertarMonto(driver, "cuota_pago", cuota);
			Interaccion.ocultarTeclado(driver);
			logger.info("Insercion de datos satisfacrtoria del paso 9 ");
			Interaccion.photo_Evidencia(driver, "Step9_Reg");
			driver.findElement(By.id(p.getProperty("btnNext"))).click();

			try {
				logger.info("CARGANDO VALIDACION DE LOS DATOS INSERTADOS EN EL PASO 9");
				logger.info("Validando datos en el campo " + puntaje);
				driver.findElement(By.xpath("//*[@text='" + puntaje + "']"));
				logger.info("Validando datos en el campo " + tasaInteres);
				driver.findElement(By.xpath("//*[@text='" + tasaInteres + "']"));
				logger.info("Validando datos en el campo " + Interaccion.ponerPuntos(monto));
				driver.findElement(By.xpath("//*[@text='" + Interaccion.ponerPuntos(monto) + "']"));
				logger.info("Validando datos en el campo " + Interaccion.ponerPuntos(cuota));
				driver.findElement(By.xpath("//*[@text='" + Interaccion.ponerPuntos(cuota) + "']"));
				Interaccion.photo_Evidencia(driver, "Step9_Val");
				driver.findElement(By.xpath(p.getProperty("btnContinuar"))).click();
				logger.info("Comprobacion de datos satisfactorios en el paso 9 \n");
				setValPaso9("EXITOSO");

			} catch (Exception e) {
				Interaccion.photo_Evid_Fallida(driver, "Paso 9 msm Confirmar");
				logger.error("No se pudieron validar los datos insertados " + Interaccion.mensajeError(e));

			}

		} catch (Exception e) {
			setValPaso9("FALLO");
			Interaccion.photo_Evid_Fallida(driver, "Paso 9 ");
			logger.error("Error insercion de datos fallida " + Interaccion.mensajeError(e));

		}

	}

	@Override
	public void orquestador(AndroidDriver<?> driver) throws Exception {
		analisisAsesor(driver);

	}

}
