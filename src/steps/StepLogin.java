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
 * Esta clase ejecuta la prueba del login accediendo al sistema donde
 * diligenciamos los campos, realizamos el patron, seleccionamos la entidad y
 * damos clic en la primera opcion del menu.
 * 
 * @author: Sergio Ruiz.
 * @version: 11/01/2018
 * 
 * 
 *           Se actualiza el patron para que realice dos desplazamientos y sus
 *           respectivos constructores
 * @author cavila
 * @version 22/01/2018
 * 
 * 
 *          Se actualizan los metodos para que funcione en la nueva version de
 *          android 6.0
 * 
 * @author lroah
 * @version 31/01/2018
 */
public class StepLogin implements IStep {
	private String tipo_id;
	private String user;
	private String pass;
	private String fundacion;
	public static String valLogin = "Sin Validar";

	final static Logger logger = LogManager.getLogger(StepLogin.class);
	final static Properties p = new Properties();

	/**
	 * Metodo constructor con sus parametros
	 * 
	 * @param tipo_id
	 * @param user
	 * @param pass
	 * @param fundacion
	 */
	public StepLogin(String tipo_id, String user, String pass, String fundacion) {
		this.tipo_id = tipo_id.trim();
		this.user = user.trim();
		this.pass = pass.trim();
		this.fundacion = fundacion.trim();
	}

	/**
	 * @return the tipo_id
	 */
	public String getTipo_id() {
		return tipo_id;
	}

	/**
	 * @param tipo_id
	 *            the tipo_id to set
	 */
	public void setTipo_id(String tipo_id) {
		this.tipo_id = tipo_id;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass
	 *            the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return the fundacion
	 */
	public String getFundacion() {
		return fundacion;
	}

	/**
	 * @param fundacion
	 *            the fundacion to set
	 */
	public void setFundacion(String fundacion) {
		this.fundacion = fundacion;
	}

	public static String getValLogin() {
		return valLogin;
	}

	public static void setValLogin(String valLogin) {
		StepLogin.valLogin = valLogin;
	}

	/**
	 * Este
	 * 
	 * @param driver
	 * @author lroah
	 * @throws Exception
	 * *
	 */
	public void login(AndroidDriver<?> driver) throws Exception {
		try {
			p.load(new FileReader("src\\config\\config.properties"));
			logger.info(" Iniciando el app, validando elementos del Login");
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(p.getProperty("cmbGeneral")))));
			driver.findElement(By.xpath(p.getProperty("cmbGeneral"))).click();
			driver.findElement(By.xpath("//*[@text='" + this.tipo_id + "']")).click();
			driver.findElement(By.id("email")).sendKeys(this.user);
			driver.findElement(By.id("password")).sendKeys(this.pass);
			Interaccion.photo_Evidencia(driver, "Login");
			driver.findElement(By.id("iniciar_sesion")).click();
			logger.info("Se ingresa al sistema correctamente");

		} catch (Exception e) {
			setValLogin("FALLO");
			logger.error(" No se pudo ingresar al sistema \n");

			try {
				Interaccion.photo_Evid_Fallida(driver, "Login");
			
			} catch (Exception e1) {
				logger.error("No se guardar la imagen del login \n");
				
			}
		}
	}

	public void patron(AndroidDriver<?> driver) throws Exception {

		try {
			logger.info(" Validando elementos del activity patron");
			Interaccion.patron(driver);
			driver.findElementById("co.com.experian.sla.mobile.dc:id/pl_right_button").click();
			Interaccion.patron(driver);
			driver.findElementById("co.com.experian.sla.mobile.dc:id/pl_right_button").click();
			logger.info("Se ingresan las coordenadas del patron");
			Interaccion.photo_Evidencia(driver, "Patron");

		} catch (Exception e) {
			setValLogin("FALLO");
			logger.error(" El patron no puede ser senalado, verifique las coordenadas \n");
			Interaccion.photo_Evid_Fallida(driver, "Patron");
			
		}
	}

	public void seleccionEntidad(AndroidDriver<?> driver) throws Exception {

		try {
			logger.info(" Validando combo de Entidad de Usuario");
			driver.findElement(By.xpath("//*[@text='" + p.getProperty("cmbInicialclic") + "']")).click();
			driver.findElement(By.xpath("//*[@text='" + p.getProperty("cmbInicialclic") + "']")).click();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("btn_aceppt"))));
			driver.findElement(By.xpath("//*[@text='" + p.getProperty("btn_aceppt") + "']")).click();
			logger.info("Selecciona la entidad correctamente");

		} catch (Exception e) {
			setValLogin("FALLO");
			logger.error("No se puede seleccionar la entidad de usuario \n");
			Interaccion.photo_Evid_Fallida(driver, "seleccionEntidad");
			
		}
	}

	public void seleccionMenu(AndroidDriver<?> driver) throws Exception {
		try {
			logger.info(" Buscando la opcion del menu");
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("consulta"))));
			driver.findElement(By.id("co.com.experian.sla.mobile.dc:id/consulta")).click();
			logger.info("Selecciona la primera opcion del menu \n");
			setValLogin("EXITOSO");
			Interaccion.photo_Evidencia(driver, "Seleccion Menu1");

		} catch (Exception e) {
			setValLogin("FALLO");

			try {
				logger.error("No se puede seleccionar la correspondiente opcion el menu" + Interaccion.mensajeError(e));
				Interaccion.photo_Evid_Fallida(driver, "SeleccionMenu");
			

			} catch (Exception e1) {

				logger.error("No se puede guardar la imagen" + Interaccion.mensajeError(e1));
				e1.printStackTrace();
				setValLogin("FALLO");
				
			}
		}
	}

	@Override
	public void orquestador(AndroidDriver<?> driver) throws Exception {
			login(driver);
			patron(driver);
			seleccionEntidad(driver);
			seleccionMenu(driver);
	}

	@Override
	public String toString() {
		return "StepLogin [tipo_id=" + tipo_id + ", user=" + user + ", pass=" + pass + ", fundacion=" + fundacion + "]";
	}

}