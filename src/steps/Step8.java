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
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * 
 * @author avelandiap
 *
 */

public class Step8 implements IStep {

	private String nombre;
	private String relacion;
	private String telefono;
	private String celular;
	private String correo;
	private String departamento;
	private String ciudad;
	private String direccion;
	private String NReferencias;
	public static String valPaso8 = "Sin Validar";
	final static Logger logger = LogManager.getLogger(Step8.class);
	final static Properties p = new Properties();

	/**
	 * @param nombre
	 * @param relacion
	 * @param telefono
	 * @param celular
	 * @param correo
	 * @param departamento
	 * @param ciudad
	 * @param direccion
	 */
	public Step8(String nombre, String relacion, String telefono, String celular, String correo, String departamento,
			String ciudad, String direccion, String NReferencias) {
		this.nombre = nombre.trim();
		this.relacion = relacion.trim();
		this.telefono = telefono.trim();
		this.celular = celular.trim();
		this.correo = correo.trim();
		this.departamento = departamento.trim();
		this.ciudad = ciudad.trim();
		this.direccion = direccion.trim();
		this.NReferencias = NReferencias.trim();
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the relacion
	 */
	public String getRelacion() {
		return relacion;
	}

	/**
	 * @param relacion
	 *            the relacion to set
	 */
	public void setRelacion(String relacion) {
		this.relacion = relacion;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * @param celular
	 *            the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo
	 *            the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the departamento
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * @param departamento
	 *            the departamento to set
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad
	 *            the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion
	 *            the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public static String getValPaso8() {
		return valPaso8;
	}

	public static void setValPaso8(String valPaso8) {
		Step8.valPaso8 = valPaso8;
	}

	public void referencias(AndroidDriver<?> driver) throws Exception {
		p.load(new FileReader("src\\config\\config.properties"));
		Interaccion.ocultarTeclado(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("full_name"))));

		try {
			logger.info("Iniciando Inspeccion e Inserccion de datos en el Paso8");
			Interaccion.photo_Evidencia(driver, "Step8");
			for (int i = 0; i < Integer.parseInt(NReferencias); i++) {

				Thread.sleep(1000);
				driver.findElement(By.id("full_name")).click();
				driver.findElement(By.id("full_name")).sendKeys(nombre);
				driver.findElement(By.id("relationship")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@text='" + relacion + "']")).click();
				driver.findElement(By.id("home_phone")).click();
				driver.findElement(By.id("home_phone")).sendKeys(telefono);
				driver.findElement(By.id("cell_phone")).click();
				driver.findElement(By.id("cell_phone")).sendKeys(celular);
				driver.findElement(By.id("email")).click();
				driver.findElement(By.id("email")).sendKeys(correo);
				Interaccion.ocultarTeclado(driver);
				driver.findElement(By.id("state")).click();
				listDepartamento(driver);
				Interaccion.ocultarTeclado(driver);
				driver.findElement(By.id("city")).click();
				listCiudad(driver);
				driver.findElement(By.id("address")).click();
				driver.findElement(By.id("address")).sendKeys(direccion);
				Interaccion.ocultarTeclado(driver);
				driver.findElement(By.id(p.getProperty("btnReferencia"))).click();
				int n = i + 1;
				logger.info("inserccion de la referencia # " + n + " Correctamente");
			}

			logger.info("Inserccion de datos satisfacrtoria del paso 8 ");
			Interaccion.photo_Evidencia(driver, "Step8_Reg");

		} catch (Exception e) {
			setValPaso8("FALLO");
			Interaccion.photo_Evid_Fallida(driver, "Paso 8 ");
			logger.error("Error insercion de datos fallida " + Interaccion.mensajeError(e));

		}
	}

	public void confirmacion(AndroidDriver<?> driver) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {

		}
		Interaccion.ocultarTeclado(driver);
		try {
			listValidacion(driver);
		} catch (Exception e) {

		}

		try {
			driver.findElement(By.id("next")).click();
		} catch (Exception e) {
			listValidacion(driver);
			listValidacion(driver);
			driver.findElement(By.id(p.getProperty("btnNext"))).click();
		}

		if (NReferencias.equals("0")) {
			driver.findElement(By.xpath(p.getProperty("btnContinuar"))).click();
			setValPaso8("EXITOSO");
			logger.info("No se agregaron referencias");
		} else {

			try {

				logger.info("CARGANDO VALIDACION DE LOS DATOS INSERTADOS EN EL PASO 8");
				logger.info("Validando datos en el campo " + nombre);
				driver.findElement(By.xpath("//*[@text='" + nombre + "']"));

				logger.info("Validando datos en el campo " + celular);
				driver.findElement(By.xpath("//*[@text='" + celular + "']"));

				Interaccion.photo_Evidencia(driver, "Step8_Val");

				driver.findElement(By.xpath(p.getProperty("btnContinuar"))).click();
				logger.info("Comprobacion de datos satisfactorios en el paso 8 \n");
				setValPaso8("EXITOSO");

			} catch (Exception e) {
				Interaccion.photo_Evid_Fallida(driver, "Paso 8 msm Confirmar");
				logger.error("No se pudieron validar los datos insertados " + Interaccion.mensajeError(e));

			}
		}

	}

	public void cancelacion(AndroidDriver<?> driver) {
		driver.findElement(By.id(p.getProperty("btnCancel"))).click();
		driver.findElement(By.xpath(p.getProperty("btnCancelar"))).click();
	}

	public void guardar(AndroidDriver<?> driver) {
		driver.findElement(By.id(p.getProperty("btnCancel"))).click();
		driver.findElement(By.xpath(p.getProperty("btnGuardar"))).click();
		driver.findElement(By.xpath(p.getProperty("btnGuardar"))).click();
	}

	/**
	 * Seleccion de ciudad en combobox
	 */
	public void listDepartamento(AndroidDriver<?> driver) {
		MobileElement listElement = null;
		try {
			AndroidElement list = (AndroidElement) driver
					.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.ListView"));
			listElement = list
					.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
							+ "new UiSelector().text(\"" + departamento + "\"));"));

		} catch (Exception e) {
			Interaccion.photo_Evid_Fallida(driver, "Step8_listDepart");
			logger.error("Problema con identificar el Departamento \n");
		}
		listElement.click();
	}

	// Seleccion de ciudad en combobox
	public void listCiudad(AndroidDriver<?> driver) {
		MobileElement listElement = null;
		try {
			AndroidElement list = (AndroidElement) driver
					.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.ListView"));
			listElement = list
					.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
							+ "new UiSelector().text(\"" + ciudad + "\"));"));
		} catch (Exception e) {
			Interaccion.photo_Evid_Fallida(driver, "Step7_listCity");
			logger.error("Problema con identificar la Ciudad \n ");
		}
		listElement.click();
	}

	@Override
	public void orquestador(AndroidDriver<?> driver) throws Exception {
		if (this.NReferencias.equalsIgnoreCase("0")) {
			confirmacion(driver);

		} else {
			referencias(driver);
			confirmacion(driver);
			// guardar(driver);
		}
	}

	public void listValidacion(AndroidDriver<?> driver) {
		try {
			p.load(new FileReader("src\\config\\config.properties"));
			(new TouchAction(driver))
					.press(Integer.parseInt(p.getProperty("puntoFinalX")),
							Integer.parseInt(p.getProperty("puntoFinalY")))
					.moveTo(Integer.parseInt(p.getProperty("puntoInicialX")),
							Integer.parseInt(p.getProperty("puntoInicialY")))
					.release().perform();
		} catch (Exception e) {
			Interaccion.photo_Evid_Fallida(driver, "Step8_listValid");
			logger.warn("Problema con el desplazamiento \n ");
		}

	}

}
