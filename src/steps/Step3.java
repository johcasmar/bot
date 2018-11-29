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
 * Esta clase ejecuta la prueba del tercer paso para crear una nueva solicitud
 * donde diligenciamos un formulario con la informaci�n de la Microempresa.
 * 
 * @author: Sergio Ruiz.
 * @version: 03/01/18
 */

public class Step3 implements IStep {

	private String departamento;
	private String ciudad;
	private String telefono;
	private String celular;
	private String correo;
	private String nombreArr;
	private String celularArr;
	private String numEmpleados;
	private String mesesActividad;
	private String mesesLocal;
	private String direccion;
	private static String valPaso3="Sin validar";
	
	final static Logger logger = LogManager.getLogger(Step3.class);
	final static Properties p = new Properties();


	/**
	 * @param departamento
	 * @param ciudad
	 * @param telefono
	 * @param celular
	 * @param correo
	 * @param nombreArr
	 * @param celularArr
	 * @param numEmpleados
	 * @param mesesActividad
	 * @param mesesLocal
	 * @param direccion
	 */
	public Step3(String departamento, String ciudad, String telefono, String celular, String correo, String nombreArr,
			String celularArr, String numEmpleados, String mesesActividad, String mesesLocal, String direccion) {
		this.departamento = departamento.trim();
		this.ciudad = ciudad.trim();
		this.telefono = telefono.trim();
		this.celular = celular.trim();
		this.correo = correo.trim();
		this.nombreArr = nombreArr.trim();
		this.celularArr = celularArr.trim();
		this.numEmpleados = numEmpleados.trim();
		this.mesesActividad = mesesActividad.trim();
		this.mesesLocal = mesesLocal.trim();
		this.direccion = direccion.trim();
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
	 * @return the nombreArr
	 */
	public String getNombreArr() {
		return nombreArr;
	}

	/**
	 * @param nombreArr
	 *            the nombreArr to set
	 */
	public void setNombreArr(String nombreArr) {
		this.nombreArr = nombreArr;
	}

	/**
	 * @return the celularArr
	 */
	public String getCelularArr() {
		return celularArr;
	}

	/**
	 * @param celularArr
	 *            the celularArr to set
	 */
	public void setCelularArr(String celularArr) {
		this.celularArr = celularArr;
	}

	/**
	 * @return the numEmpleados
	 */
	public String getNumEmpleados() {
		return numEmpleados;
	}

	/**
	 * @param numEmpleados
	 *            the numEmpleados to set
	 */
	public void setNumEmpleados(String numEmpleados) {
		this.numEmpleados = numEmpleados;
	}

	/**
	 * @return the mesesActividad
	 */
	public String getMesesActividad() {
		return mesesActividad;
	}

	/**
	 * @param mesesActividad
	 *            the mesesActividad to set
	 */
	public void setMesesActividad(String mesesActividad) {
		this.mesesActividad = mesesActividad;
	}

	/**
	 * @return the mesesLocal
	 */
	public String getMesesLocal() {
		return mesesLocal;
	}

	/**
	 * @param mesesLocal
	 *            the mesesLocal to set
	 */
	public void setMesesLocal(String mesesLocal) {
		this.mesesLocal = mesesLocal;
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

		public static String getValPaso3() {
		return valPaso3;
	}

	public static void setValPaso3(String valPaso3) {
		Step3.valPaso3 = valPaso3;
	}

	
	/**
	 * @param driver
	 * @throws Exception 
	 */
	
	
	public void infoMicroempresa(AndroidDriver<?> driver) throws Exception {
		p.load(new FileReader("src\\config\\config.properties"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("state"))));

		try {
			logger.info("Iniciando Inspeccion e Insercion de datos en el Paso3");
			Interaccion.photo_Evidencia(driver, "Step3");
			driver.findElement(By.id("state")).click();
			listDepartamento(driver);
			driver.findElement(By.id("city")).click();
			listCiudad(driver);
			driver.findElement(By.id("home_phone")).click();
			driver.findElement(By.id("home_phone")).sendKeys(telefono);
			driver.findElement(By.id("cell_phone")).click();
			driver.findElement(By.id("cell_phone")).sendKeys(celular);
			driver.findElement(By.id("email")).click();
			driver.findElement(By.id("email")).sendKeys(correo);
			driver.findElement(By.id("fullname_arr")).click();
			driver.findElement(By.id("fullname_arr")).sendKeys(nombreArr);
			Interaccion.ocultarTeclado(driver);
			driver.findElement(By.id("phone_arr")).click();
			driver.findElement(By.id("phone_arr")).sendKeys(celularArr);
			driver.findElement(By.id("employee_number")).click();
			driver.findElement(By.id("employee_number")).sendKeys(numEmpleados);
			Interaccion.ocultarTeclado(driver);
			driver.findElement(By.id("seniority_activity")).click();
			driver.findElement(By.id("seniority_activity")).sendKeys(mesesActividad);
			driver.findElement(By.id("seniority_local")).click();
			driver.findElement(By.id("seniority_local")).sendKeys(mesesLocal);
			Interaccion.ocultarTeclado(driver);
			driver.findElement(By.id("business_address")).click();
			driver.findElement(By.id("business_address")).sendKeys(direccion);
			Interaccion.photo_Evidencia(driver, "Step3_Reg");
			Interaccion.ocultarTeclado(driver);
			driver.findElement(By.id(p.getProperty("btnNext"))).click();
			logger.info("Insercion de datos satisfacrtoria del paso 3");

			try {
				logger.info("CARGANDO VALIDACION DE LOS DATOS INSERTADOS EN EL PASO 3");
				logger.info("Validando datos en el campo " + departamento);
				driver.findElement(By.xpath("//*[@text='" + departamento + "']"));
				logger.info("Validando datos en el campo " + ciudad);
				driver.findElement(By.xpath("//*[@text='" + ciudad + "']"));
				logger.info("Validando datos en el campo " + telefono);
				driver.findElement(By.xpath("//*[@text='" + telefono + "']"));
				logger.info("Validando datos en el campo " + celular);
				driver.findElement(By.xpath("//*[@text='" + celular + "']"));
				logger.info("Validando datos en el campo " + correo);
				driver.findElement(By.xpath("//*[@text='" + correo + "']"));
				logger.info("Validando datos en el campo " + nombreArr);
				driver.findElement(By.xpath("//*[@text='" + nombreArr + "']"));
				logger.info("Validando datos en el campo " + celularArr);
				driver.findElement(By.xpath("//*[@text='" + celularArr + "']"));
				logger.info("Validando datos en el campo " + numEmpleados);
				driver.findElement(By.xpath("//*[@text='" + numEmpleados + "']"));
				listValidacion(driver);
				logger.info("Validando datos en el campo " + mesesActividad);
				driver.findElement(By.xpath("//*[@text='" + mesesActividad + "']"));
				logger.info("Validando datos en el campo " + mesesLocal);
				driver.findElement(By.xpath("//*[@text='" + mesesLocal + "']"));
				logger.info("Validando datos en el campo " + direccion);
				driver.findElement(By.xpath("//*[@text='" + direccion + "']"));
				Interaccion.photo_Evidencia(driver, "Step3_Val");
				logger.info("Validacion de datos satisfactoria en el paso 3 \n");
				driver.findElement(By.xpath(p.getProperty("btnContinuar"))).click();
				setValPaso3("EXITOSO");

			} catch (Exception e) {
				Interaccion.photo_Evid_Fallida(driver, "Paso 3 msm Confirmar");
				logger.error("No se pudieron validar los datos insertados " + Interaccion.mensajeError(e));
				
			}

		} catch (Exception e) {
			
			Interaccion.photo_Evid_Fallida(driver, "Paso 3 ");
			logger.error("Error insercion de datos fallida " + Interaccion.mensajeError(e));
			setValPaso3("FALLO");
		}
	}
	
	public void listValidacion(AndroidDriver<?> driver) throws Exception {

		try {
	
			(new TouchAction(driver)).press(Integer.parseInt(p.getProperty("valPntIniX")),
					Integer.parseInt(p.getProperty("valPntIniY"))).
			moveTo(Integer.parseInt(p.getProperty("valPntFinX")), 
					Integer.parseInt(p.getProperty("valPntFinY"))).release().perform();
					//240,610 <<>> 4,-413
		} catch (Exception e) {
						
			Interaccion.photo_Evid_Fallida(driver, "Step4_listValid");
			logger.error("Problema con el desplazamiento \n ");
			setValPaso3("FALLO");
		}

	}

	// Seleccion de ciudad en combobox
	public void listDepartamento(AndroidDriver<?> driver) {
		MobileElement listElement = null;
		try {
			AndroidElement list = (AndroidElement) driver
					.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.ListView"));
				listElement = list
					.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
							+ "new UiSelector().text(\"" + departamento + "\"));"));

		} catch (Exception e) {
			Interaccion.photo_Evid_Fallida(driver, "Step3_listDepart");
			logger.error("Problema con identificar el Departamento \n");
			setValPaso3("FALLO");
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
			Interaccion.photo_Evid_Fallida(driver, "Step3_listCity");
			logger.error("Problema con identificar la Ciudad \n ");
			setValPaso3("FALLO");
		}
		listElement.click();
	}

	/**
	 * @param driver
	 * @throws Exception 
	 */
	public void orquestador(AndroidDriver<?> driver) throws Exception {
		infoMicroempresa(driver);
	}



}