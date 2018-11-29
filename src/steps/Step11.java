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
 * @author Sergio Ruiz
 * @version 05/01/18
 */

public class Step11 implements IStep {

	private String otrosIngresos;
	private String gastosManutencion;
	private String gastosFinanciero;
	private String gastosVivienda;
	private String otrosGastos;
	public static String valPaso11 ="Sin validar";

	final static Logger logger = LogManager.getLogger(Step11.class);
	final static Properties p = new Properties();

	/**
	 * @param otrosIngresos
	 * @param gastosManutencion
	 * @param gastosFinanciero
	 * @param gastosVivienda
	 * @param otrosGastos
	 */
	public Step11(String otrosIngresos, String gastosManutencion, String gastosFinanciero, String gastosVivienda,
			String otrosGastos) {
		this.otrosIngresos = otrosIngresos.trim();
		this.gastosManutencion = gastosManutencion.trim();
		this.gastosFinanciero = gastosFinanciero.trim();
		this.gastosVivienda = gastosVivienda.trim();
		this.otrosGastos = otrosGastos.trim();
	}

	/**
	 * @return the otrosIngresos
	 */
	public String getOtrosIngresos() {
		return otrosIngresos;
	}

	/**
	 * @param otrosIngresos
	 *            the otrosIngresos to set
	 */
	public void setOtrosIngresos(String otrosIngresos) {
		this.otrosIngresos = otrosIngresos;
	}

	/**
	 * @return the gastosManutencion
	 */
	public String getGastosManutencion() {
		return gastosManutencion;
	}

	/**
	 * @param gastosManutencion
	 *            the gastosManutencion to set
	 */
	public void setGastosManutencion(String gastosManutencion) {
		this.gastosManutencion = gastosManutencion;
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

	/**
	 * @return the gastosVivienda
	 */
	public String getGastosVivienda() {
		return gastosVivienda;
	}

	/**
	 * @param gastosVivienda
	 *            the gastosVivienda to set
	 */
	public void setGastosVivienda(String gastosVivienda) {
		this.gastosVivienda = gastosVivienda;
	}

	/**
	 * @return the otrosGastos
	 */
	public String getOtrosGastos() {
		return otrosGastos;
	}

	/**
	 * @param otrosGastos
	 *            the otrosGastos to set
	 */
	public void setOtrosGastos(String otrosGastos) {
		this.otrosGastos = otrosGastos;
	}
	

	public static String getValPaso11() {
		return valPaso11;
	}

	public static void setValPaso11(String valPaso11) {
		Step11.valPaso11 = valPaso11;
	}

	/**
	 * @param driver
	 * @throws Exception 
	 */
	public void pagoMicroempresario(AndroidDriver<?> driver) throws Exception {
		p.load(new FileReader("src\\config\\config.properties"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("other_income"))));

		try {
			logger.info("Iniciando Inspecion e Insercion de datos en el Paso11");
			Interaccion.photo_Evidencia(driver, "Step11");
			
			Interaccion.insertarMonto(driver, "other_income", otrosIngresos);			
			Interaccion.insertarMonto(driver, "living_expenses", gastosManutencion); 
			Interaccion.insertarMonto(driver, "financial_expenses", gastosFinanciero);
			Interaccion.insertarMonto(driver, "home_expenses", gastosVivienda);
			Interaccion.ocultarTeclado(driver);
			Interaccion.insertarMonto(driver, "other_expenses", otrosGastos);
			Interaccion.ocultarTeclado(driver);
			logger.info("Insercion de datos satisfacrtoria del paso 11 ");
			Interaccion.photo_Evidencia(driver, "Step11_Reg");
			driver.findElement(By.id(p.getProperty("btnNext"))).click();
			
		} catch (Exception e) {
			setValPaso11("FALLO");
			Interaccion.photo_Evid_Fallida(driver, "Paso 11 ");
			logger.error("Error insercion de datos fallida " + Interaccion.mensajeError(e));
			
		}

			try {
				logger.info("CARGANDO VALIDACION DE LOS DATOS INSERTADOS EN EL PASO 11");
				logger.info("Validando datos en el campo " + Interaccion.ponerPuntos(otrosIngresos));
				driver.findElement(By.xpath("//*[@text='" + Interaccion.ponerPuntos(otrosIngresos) + "']"));
				logger.info("Validando datos en el campo " + Interaccion.ponerPuntos(gastosManutencion));
				driver.findElement(By.xpath("//*[@text='" + Interaccion.ponerPuntos(gastosManutencion) + "']"));
				logger.info("Validando datos en el campo " + Interaccion.ponerPuntos(gastosFinanciero));
				driver.findElement(By.xpath("//*[@text='" + Interaccion.ponerPuntos(gastosFinanciero) + "']"));
				logger.info("Validando datos en el campo " + Interaccion.ponerPuntos(gastosVivienda));
				driver.findElement(By.xpath("//*[@text='" + Interaccion.ponerPuntos(gastosVivienda) + "']"));
				logger.info("Validando datos en el campo " + Interaccion.ponerPuntos(otrosGastos));
				driver.findElement(By.xpath("//*[@text='" + Interaccion.ponerPuntos(otrosGastos) + "']"));
				Interaccion.photo_Evidencia(driver, "Step11_Val");
				logger.info("Comprobacion de datos satisfactorios en el paso 11 \n");
				driver.findElement(By.xpath(p.getProperty("btnContinuar"))).click();
				setValPaso11("EXITOSO");
				
			} catch (Exception e) {
				
				setValPaso11("FALLO");	
				Interaccion.photo_Evid_Fallida(driver, "Paso 11 msm Confirmar");		
				logger.error("No se pudieron validar los datos insertados " + Interaccion.mensajeError(e));
				
			}

	}

	@Override
	public void orquestador(AndroidDriver<?> driver) throws Exception {
		pagoMicroempresario(driver);

	}

}
