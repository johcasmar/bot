package steps;

import java.io.FileReader;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import interaccion.Interaccion;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * Esta clase ejecuta la prueba del primer paso para crear una nueva solicitud
 * donde diligenciamos un formulario con los datos del microempresario.
 * 
 * @author: Sergio Ruiz.
 * @version: 11/01/2018
 * 
 * 
 *           Se corrige validacion en los montos se crea los objetos y sus
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

public class Step1 implements IStep {

	private String tipoDoc;
	private String id;
	private String nombre;
	private String apellido;
	private String departamento;
	private String ciudad;
	private String direccion;
	private String telefono;
	private String celular;
	private String correo;
	private String conyuge;
	private String fecha_nacimiento;
	private String ventasMensuales;
	private String mensajeEsperado;
	private static String valPaso1="Sin validar";
	final static Logger logger = LogManager.getLogger(Step1.class);
	final static Properties p = new Properties();

	/**
	 * @return the mensajeEsperado
	 */
	public String getMensajeEsperado() {
		return mensajeEsperado;
	}

	/**
	 * @param mensajeEsperado
	 *            the mensajeEsperado to set
	 */
	public void setMensajeEsperado(String mensajeEsperado) {
		this.mensajeEsperado = mensajeEsperado;
	}

	/**
	 * @return the fecha_nacimiento
	 */
	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	/**
	 * @param fecha_nacimiento
	 *            the fecha_nacimiento to set
	 */
	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	/**
	 * @param tipoDoc
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @param departamento
	 * @param ciudad
	 * @param direccion
	 * @param telefono
	 * @param celular
	 * @param correo
	 * @param conyuge
	 * @param fecha_nacimiento
	 * @param ventasMensuales
	 * @param mensajeEsperado
	 */
	public Step1(String tipoDoc, String id, String nombre, String apellido, String departamento, String ciudad,
			String direccion, String telefono, String celular, String correo, String conyuge, String fecha_nacimiento,
			String ventasMensuales, String mensajeEsperado) {
		this.tipoDoc = tipoDoc.trim();
		this.id = id.trim();
		this.nombre = nombre.trim();
		this.apellido = apellido.trim();
		this.departamento = departamento.trim();
		this.ciudad = ciudad.trim();
		this.direccion = direccion.trim();
		this.telefono = telefono.trim();
		this.celular = celular.trim();
		this.correo = correo.trim();
		this.conyuge = conyuge.trim();
		this.fecha_nacimiento = fecha_nacimiento.trim();
		this.ventasMensuales = ventasMensuales.trim();
		this.mensajeEsperado = mensajeEsperado.trim();
	}

	/**
	 * @return the tipoDoc
	 */
	public String getTipoDoc() {
		return tipoDoc;
	}

	/**
	 * @param tipoDoc
	 *            the tipoDoc to set
	 */
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido
	 *            the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
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
	 * @return the conyuge
	 */
	public String getConyuge() {
		return conyuge;
	}

	/**
	 * @param conyuge
	 *            the conyuge to set
	 */
	public void setConyuge(String conyuge) {
		this.conyuge = conyuge;
	}

	/**
	 * @return the ventasMensuales
	 */
	public String getVentasMensuales() {
		return ventasMensuales;
	}

	/**
	 * @param ventasMensuales
	 *            the ventasMensuales to set
	 */
	public void setVentasMensuales(String ventasMensuales) {
		this.ventasMensuales = ventasMensuales;
	}

	public static String getValPaso1() {
		return valPaso1;
	}

	public static void setValPaso1(String valPaso1) {
		Step1.valPaso1 = valPaso1;
	}

	/**
	 * Realiza la insercion y validacion del 1° primer paso
	 * 
	 * @param driver
	 * @throws Exception 
	 */
	public void infoMicroempresario(AndroidDriver<?> driver) throws Exception {

		p.load(new FileReader("src\\config\\config.properties"));
		try {

			logger.info("Iniciando Inspeccion e Insercion de datos en el Paso1");
			Interaccion.photo_Evidencia(driver, "Step1");
			driver.findElement(By.id("doctype")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("//*[@text='" + tipoDoc + "']")).click();
			driver.findElement(By.id("doc_number")).click();
			driver.findElement(By.id("doc_number")).sendKeys(id);
			driver.findElement(By.id("full_name")).click();
			driver.findElement(By.id("full_name")).sendKeys(nombre);
			driver.findElement(By.id("last_name")).click();
			driver.findElement(By.id("last_name")).sendKeys(apellido);
			Interaccion.ocultarTeclado(driver);
			driver.findElement(By.id("state")).click();
			driver.findElement(By.xpath("//*[@text='" + departamento + "']")).click();
			//listDepartamento(driver);
			Interaccion.ocultarTeclado(driver);
			driver.findElement(By.id("city")).click();
			driver.findElement(By.xpath("//*[@text='" + ciudad + "']")).click();
			//listCiudad(driver);
			driver.findElement(By.id("address")).click();
			driver.findElement(By.id("address")).sendKeys(direccion);
			Interaccion.ocultarTeclado(driver);
			driver.findElement(By.id("home_phone")).click();
			driver.findElement(By.id("home_phone")).sendKeys(telefono);
			Interaccion.ocultarTeclado(driver);
			driver.findElement(By.id("cell_phone")).click();
			driver.findElement(By.id("cell_phone")).sendKeys(celular);
			Interaccion.ocultarTeclado(driver);
			driver.findElement(By.id("email")).click();
			driver.findElement(By.id("email")).sendKeys(correo);
			Interaccion.ocultarTeclado(driver);
			Interaccion.seleccionarFecha(driver);
			listValidacion(driver);
			driver.findElement(By.id("conyuge")).click();
			driver.findElement(By.xpath("//*[@text='" + conyuge + "']")).click();		
			Interaccion.insertarMonto(driver, "monthly_sales", ventasMensuales);
			Interaccion.ocultarTeclado(driver);
			logger.info("Ingresa todos los campos satisfactoriamente");
			Interaccion.photo_Evidencia(driver, "Step1_Reg");						
			driver.findElement(By.id(p.getProperty("btnNext"))).click();
		} catch (Exception e) {
			setValPaso1("FALLO");
			Interaccion.photo_Evid_Fallida(driver, "Paso 1");
			logger.error("Fallo en la insercción del campo " + Interaccion.mensajeError(e));
	
		}
		try {
			logger.info("CARGANDO VALIDACION DE LOS DATOS INSERTADOS EN EL PASO 1");
			logger.info("Validando datos en el paso " + tipoDoc);
			driver.findElement(By.xpath("//*[@text='" + tipoDoc + "']"));
			logger.info("Validando datos en el campo " + id);
			driver.findElement(By.xpath("//*[@text='" + id + "']"));
			logger.info("Validando datos en el campo " + nombre);
			driver.findElement(By.xpath("//*[@text='" + nombre + "']"));
			logger.info("Validando datos en el campo " + apellido);
			driver.findElement(By.xpath("//*[@text='" + apellido + "']"));
			logger.info("Validando datos en el campo " + departamento);
			driver.findElement(By.xpath("//*[@text='" + departamento + "']"));
			logger.info("Validando datos en el campo " + ciudad);
			driver.findElement(By.xpath("//*[@text='" + ciudad + "']"));
			logger.info("Validando datos en el campo " + direccion);
			driver.findElement(By.xpath("//*[@text='" + direccion + "']"));
			listValidacion(driver);
			logger.info("Validando datos en el campo " + telefono);
			driver.findElement(By.xpath("//*[@text='" + telefono + "']"));
			logger.info("Validando datos en el campo " + celular);
			driver.findElement(By.xpath("//*[@text='" + celular + "']"));
			logger.info("Validando datos en el campo " + correo);
			driver.findElement(By.xpath("//*[@text='" + correo + "']"));
			driver.findElement(By.xpath("//*[@text='" + conyuge + "']"));
			logger.info("Validando datos en el campo " + conyuge);
			driver.findElement(By.xpath("//*[@text='" + Interaccion.ponerPuntos(ventasMensuales) + "']"));
			logger.info("Validando datos en el campo " + Interaccion.ponerPuntos(ventasMensuales));
			Interaccion.photo_Evidencia(driver, "Step1_Val");
			logger.info("Comprobacion de datos satisfactorios en el paso 1 \n");
			driver.findElement(By.xpath(p.getProperty("btnContinuar"))).click();
			setValPaso1("EXITOSO");

			try {
				Interaccion.photo_Evidencia(driver, "Step1 ValId");
				driver.findElement(By.xpath("//*[@text='" + mensajeEsperado + "']"));

			} catch (Exception e) {
				logger.error("Error mensaje ->" + mensajeEsperado + " No fue encontrado \n");
			}

			try {
				driver.findElement(By.xpath(p.getProperty("btnAceptar"))).click();
			} catch (Exception e) {
				setValPaso1("FALLO");
				logger.error("No se pudo dar clic en boton Aceptar");
			}

		} catch (Exception e) {
			setValPaso1("FALLO");
			Interaccion.photo_Evid_Fallida(driver, "Paso 1 msm Confirmar");
			logger.warn("Error en la confirmacion de datos" + Interaccion.mensajeError(e));
		}
	}

	/**
	 * Realiza el desplazamiento dentro del combo departamento y selecciona el
	 * indicado en el excel
	 * 
	 * @param driver
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
			Interaccion.photo_Evid_Fallida(driver, "Step1_listCity");
			logger.error("Problema con identificar el departamento \n ");
		}
		listElement.click();
	}

	/**
	 * Realiza el desplazamiento dentro del combo ciudad y selecciona el indicado en
	 * el excel
	 * 
	 * @param driver
	 */
	public void listCiudad(AndroidDriver<?> driver) {
		MobileElement listElement = null;
		try {
			AndroidElement list = (AndroidElement) driver
					.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.ListView"));
			listElement = list
					.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
							+ "new UiSelector().text(\"" + ciudad + "\"));"));
		} catch (Exception e) {
			Interaccion.photo_Evid_Fallida(driver, "Step1_listCity");
			logger.error("Problema con identificar la Ciudad \n ");
		}
		listElement.click();
	}

	/**
	 * Realiza un desplazamiento en una ubicacion establecida
	 * 
	 * @param driver
	 * @throws Exception 
	 */
	public void listValidacion(AndroidDriver<?> driver) throws Exception {
		try {
			p.load(new FileReader("src\\config\\config.properties"));
			(new TouchAction(driver)).press(Integer.parseInt(p.getProperty("valPntIniX")),
					Integer.parseInt(p.getProperty("valPntIniY"))).
			moveTo(Integer.parseInt(p.getProperty("valPntFinX")), 
					Integer.parseInt(p.getProperty("valPntFinY"))).release().perform();

		} catch (Exception e) {
						
			Interaccion.photo_Evid_Fallida(driver, "Step1_listValid");
			logger.error("Problema con el desplazamiento \n ");
			setValPaso1("FALLO");
		}
	}

	/**
	 * Encargado de la ejecucion de la clase
	 * 
	 * @param driver
	 * @throws Exception 
	 */
	public void orquestador(AndroidDriver<?> driver) throws Exception {
		infoMicroempresario(driver);
	}

	

}