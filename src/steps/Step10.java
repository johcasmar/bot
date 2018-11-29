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

public class Step10 implements IStep {

	private String ventasBueno;
	private String ventasMalo;
	private String gastosMensuales;
	private String gastosFinanciero;
	public static String valPaso10 = "Sin Validar";
	final static Logger logger = LogManager.getLogger(Step10.class);
	final static Properties p = new Properties();

	/**
	 * @param ventasBueno
	 * @param ventasMalo
	 * @param gastosMensuales
	 * @param gastosFinanciero
	 */
	public Step10(String ventasBueno, String ventasMalo, String gastosMensuales, String gastosFinanciero) {
		this.ventasBueno = ventasBueno.trim();
		this.ventasMalo = ventasMalo.trim();
		this.gastosMensuales = gastosMensuales.trim();
		this.gastosFinanciero = gastosFinanciero.trim();
	}

	/**
	 * @return the ventasBueno
	 */
	public String getVentasBueno() {
		return ventasBueno;
	}

	/**
	 * @param ventasBueno
	 *            the ventasBueno to set
	 */
	public void setVentasBueno(String ventasBueno) {
		this.ventasBueno = ventasBueno;
	}

	/**
	 * @return the ventasMalo
	 */
	public String getVentasMalo() {
		return ventasMalo;
	}

	/**
	 * @param ventasMalo
	 *            the ventasMalo to set
	 */
	public void setVentasMalo(String ventasMalo) {
		this.ventasMalo = ventasMalo;
	}

	/**
	 * @return the gastosMensuales
	 */
	public String getGastosMensuales() {
		return gastosMensuales;
	}

	/**
	 * @param gastosMensuales
	 *            the gastosMensuales to set
	 */
	public void setGastosMensuales(String gastosMensuales) {
		this.gastosMensuales = gastosMensuales;
	}

	/**
	 * @return the gastosFinanciero
	 */
	public String getGastosFinanciero() {
		return gastosFinanciero;
	}

	/**
	 * @param gastosFinanciero
	 *            the gastosFinanciero to set
	 */
	public void setGastosFinanciero(String gastosFinanciero) {
		this.gastosFinanciero = gastosFinanciero;
	}

	public static String getValPaso10() {
		return valPaso10;
	}

	public static void setValPaso10(String valPaso10) {
		Step10.valPaso10 = valPaso10;
	}

	public void analisisAsesor(AndroidDriver<?> driver) throws Exception {
		p.load(new FileReader("src\\config\\config.properties"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("sales_good_day"))));

		try {
			logger.info("Iniciando Inspeccion e Insercion de datos en el Paso10");
			Interaccion.photo_Evidencia(driver, "Step10");
			Interaccion.insertarMonto(driver, "sales_good_day", ventasBueno);
			Interaccion.insertarMonto(driver, "sales_bad_day", ventasMalo);
			Interaccion.insertarMonto(driver, "monthly_costs", gastosMensuales);
			Interaccion.insertarMonto(driver, "financial_expenses", gastosFinanciero);
			Interaccion.ocultarTeclado(driver);
			logger.info("Insercion de datos satisfacrtoria del paso 10 ");
			Interaccion.photo_Evidencia(driver, "Step10_Reg");
			driver.findElement(By.id(p.getProperty("btnNext"))).click();

			try {
				logger.info("CARGANDO VALIDACION DE LOS DATOS INSERTADOS EN EL PASO 10");
				logger.info("Validando datos en el campo " + Interaccion.ponerPuntos(ventasBueno));
				driver.findElement(By.xpath("//*[@text='" + Interaccion.ponerPuntos(ventasBueno) + "']"));
				logger.info("Validando datos en el campo " + Interaccion.ponerPuntos(ventasMalo));
				driver.findElement(By.xpath("//*[@text='" + Interaccion.ponerPuntos(ventasMalo) + "']"));
				logger.info("Validando datos en el campo " + Interaccion.ponerPuntos(gastosMensuales));
				driver.findElement(By.xpath("//*[@text='" + Interaccion.ponerPuntos(gastosMensuales) + "']"));
				logger.info("Validando datos en el campo " + Interaccion.ponerPuntos(gastosFinanciero));
				driver.findElement(By.xpath("//*[@text='" + Interaccion.ponerPuntos(gastosFinanciero) + "']"));
				logger.info("Comprobacion de datos satisfactorios en el paso 10 \n");
				driver.findElement(By.xpath(p.getProperty("btnContinuar"))).click();
				setValPaso10("EXITOSO");

			} catch (Exception e) {
				setValPaso10("FALLO");
				Interaccion.photo_Evid_Fallida(driver, "Paso 10 msm Confirmar");
				logger.error("No se pudieron validar los datos insertados " + Interaccion.mensajeError(e));

			}

		} catch (Exception e) {
			setValPaso10("FALLO");
			Interaccion.photo_Evid_Fallida(driver, "Paso 10 ");
			logger.error("Error confirmacion de datos fallida " + Interaccion.mensajeError(e));

		}
	}

	@Override
	public void orquestador(AndroidDriver<?> driver) throws Exception {
		analisisAsesor(driver);

	}

}
