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

public class Step7 implements IStep {

	private String tipoDoc;
	private String id;
	private String nombre;
	private String apellido;
	private String empleado;
	private String empresa;
	private String departamento;
	private String ciudad;
	private String direccionResidencia;
	private String cargo;
	private String celular;
	private String correo;
	private String fecha;
	private String fincaRaiz;
	private String mensajeEsperado;
	private String mensajeEsperado2;
	private static String valPaso7 = "Sin Validar";

	final static Logger logger = LogManager.getLogger(Step7.class);
	final static Properties p = new Properties();

	/**
	 * @param tipoDoc
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @param empleado
	 * @param empresa
	 * @param departamento
	 * @param ciudad
	 * @param direccionEmpresa
	 * @param cargo
	 * @param celular
	 * @param correo
	 * @param fincaRaiz
	 * @param mensajeEsperado
	 */
	public Step7(String tipoDoc, String id, String nombre, String apellido, String empleado, String empresa,
			String departamento, String ciudad, String direccionEmpresa, String cargo, String celular, String correo,
			String fecha, String fincaRaiz, String mensajeEsperado, String mensajeEsperado2) {
		this.tipoDoc = tipoDoc.trim();
		this.id = id.trim();
		this.nombre = nombre.trim();
		this.apellido = apellido.trim();
		this.empleado = empleado.trim();
		this.empresa = empresa.trim();
		this.departamento = departamento.trim();
		this.ciudad = ciudad.trim();
		this.direccionResidencia = direccionEmpresa.trim();
		this.cargo = cargo.trim();
		this.celular = celular.trim();
		this.correo = correo.trim();
		this.fecha = fecha.trim();
		this.fincaRaiz = fincaRaiz.trim();
		this.mensajeEsperado = mensajeEsperado.trim();
		this.mensajeEsperado2 = mensajeEsperado2.trim();
	}

	@Override
	public String toString() {
		return tipoDoc + id + nombre + apellido + empleado + empresa + departamento + ciudad + direccionResidencia
				+ cargo + celular + correo + fecha + fincaRaiz + mensajeEsperado + mensajeEsperado2;
	}

	public String getMensajeEsperado2() {
		return mensajeEsperado2;
	}

	public void setMensajeEsperado2(String mensajeEsperado2) {
		this.mensajeEsperado2 = mensajeEsperado2;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
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
	 * @return the empleado
	 */
	public String getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado
	 *            the empleado to set
	 */
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	/**
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa
	 *            the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
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
	 * @return the direccionEmpresa
	 */
	public String getDireccionEmpresa() {
		return direccionResidencia;
	}

	/**
	 * @param direccionEmpresa
	 *            the direccionEmpresa to set
	 */
	public void setDireccionEmpresa(String direccionEmpresa) {
		this.direccionResidencia = direccionEmpresa;
	}

	/**
	 * @return the cargo
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * @param cargo
	 *            the cargo to set
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
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
	 * @return the fincaRaiz
	 */
	public String getFincaRaiz() {
		return fincaRaiz;
	}

	/**
	 * @param fincaRaiz
	 *            the fincaRaiz to set
	 */
	public void setFincaRaiz(String fincaRaiz) {
		this.fincaRaiz = fincaRaiz;
	}

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
	
	public static String getValPaso7() {
		return valPaso7;
	}

	public static void setValPaso7(String valPaso7) {
		Step7.valPaso7 = valPaso7;
	}



	/**
	 * @param driver
	 * @throws Exception
	 */
	public void infoCodeudor(AndroidDriver<?> driver) throws Exception {
		p.load(new FileReader("src\\config\\config.properties"));
		StringBuffer nCeros = new StringBuffer();
		for (int i = 0; i < this.toString().length(); i++) {
			nCeros.append("0");
		}

		if (!this.toString().equalsIgnoreCase(nCeros.toString())) {

			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("doctype"))));

			try {
				logger.info("Iniciando Inspeccion e Insercion de datos en el Paso7");
				Interaccion.photo_Evidencia(driver, "Step7");

				WebDriverWait wait2 = new WebDriverWait(driver, 10);
				wait2.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("doctype"))));
				driver.findElement(By.id("doctype")).click();
				Thread.sleep(500);
				driver.findElement(By.xpath("//*[@text='" + tipoDoc + "']")).click();
				driver.findElement(By.id("docnumber")).click();
				driver.findElement(By.id("docnumber")).sendKeys(id);
				driver.findElement(By.id("fullname")).click();
				driver.findElement(By.id("fullname")).sendKeys(nombre);
				driver.findElement(By.id("last_name")).click();
				driver.findElement(By.id("last_name")).sendKeys(apellido);
				Interaccion.ocultarTeclado(driver);
				// no selecciona si es empleado
				driver.findElement(By.id("employee")).click();
				driver.findElement(By.xpath("//*[@text='" + empleado + "']")).click();
				Interaccion.ocultarTeclado(driver);

				boolean flagEmpleado = false;
				if (empleado.equalsIgnoreCase("Si")) {
					flagEmpleado = true;
					driver.findElement(By.id("company_name")).click();
					driver.findElement(By.id("company_name")).sendKeys(empresa);
					Interaccion.ocultarTeclado(driver);
				}

				driver.findElement(By.id("state")).click();
				listDepartamento(driver);
				driver.findElement(By.id("city")).click();
				listCiudad(driver);
				driver.findElement(By.id("company_address")).click();
				driver.findElement(By.id("company_address")).sendKeys(direccionResidencia);
				Interaccion.ocultarTeclado(driver);

				if (empleado.equalsIgnoreCase("Si")) {
					flagEmpleado = true;
					Interaccion.ocultarTeclado(driver);
					driver.findElement(By.id("position")).click();
					driver.findElement(By.id("position")).sendKeys(cargo);
					Interaccion.ocultarTeclado(driver);
				}

				driver.findElement(By.id("cell_phone")).click();
				driver.findElement(By.id("cell_phone")).sendKeys(celular);
				Interaccion.ocultarTeclado(driver);
				listValidacion(driver);
				driver.findElement(By.id("email")).click();
				driver.findElement(By.id("email")).sendKeys(correo);
				Interaccion.ocultarTeclado(driver);
				Interaccion.seleccionarFecha(driver);
				driver.findElement(By.id("real_state")).click();
				driver.findElement(By.xpath("//*[@text='" + fincaRaiz + "']")).click();

				logger.info("Inserccion de datos satisfacrtoria del paso 7 ");
				Interaccion.photo_Evidencia(driver, "Step7_Reg");
				driver.findElement(By.id(p.getProperty("btnNext"))).click();

				try {
					logger.info("CARGANDO VALIDACION DE LOS DATOS INSERTADOS EN EL PASO 7");
					logger.info("Validando datos en el campo " + tipoDoc);
					driver.findElement(By.xpath("//*[@text='" + tipoDoc + "']"));
					logger.info("Validando datos en el campo " + id);
					driver.findElement(By.xpath("//*[@text='" + id + "']"));
					logger.info("Validando datos en el campo " + nombre);
					driver.findElement(By.xpath("//*[@text='" + nombre + "']"));
					logger.info("Validando datos en el campo " + apellido);
					driver.findElement(By.xpath("//*[@text='" + apellido + "']"));
					logger.info("Validando datos en el campo " + empleado);
					driver.findElement(By.xpath("//*[@text='" + empleado + "']"));

					if (flagEmpleado) {
						logger.info("Validando datos en el campo " + empresa);
						driver.findElement(By.xpath("//*[@text='" + empresa + "']"));
					}

					logger.info("Validando datos en el campo " + departamento);
					driver.findElement(By.xpath("//*[@text='" + departamento + "']"));
					logger.info("Validando datos en el campo " + ciudad);
					driver.findElement(By.xpath("//*[@text='" + ciudad + "']"));
					listValidacion(driver);
					logger.info("Validando datos en el campo " + direccionResidencia);
					driver.findElement(By.xpath("//*[@text='" + direccionResidencia + "']"));

					if (flagEmpleado) {

						logger.info("Validando datos en el campo " + cargo);
						driver.findElement(By.xpath("//*[@text='" + cargo + "']"));
					}

					logger.info("Validando datos en el campo " + celular);
					driver.findElement(By.xpath("//*[@text='" + celular + "']"));

					logger.info("Validando datos en el campo " + correo);
					driver.findElement(By.xpath("//*[@text='" + correo + "']"));
					logger.info("Validando datos en el campo " + fincaRaiz);
					driver.findElement(By.xpath("//*[@text='" + fincaRaiz + "']"));
					Interaccion.photo_Evidencia(driver, "Step7_Val");
					driver.findElement(By.xpath(p.getProperty("btnContinuar"))).click();
					logger.info("Comprobaci�n de datos satisfactorios en el paso 7 \n");
					setValPaso7("EXITOSO");

					// validacion identidad de codeudor
					try {
						wait.until(
								ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@text='Aceptar']"))));
						driver.findElement(By.xpath("//*[@text='" + mensajeEsperado + "']"));

					} catch (Exception e) {
						// evidencia modal no encontrado
						Interaccion.photo_Evid_Fallida(driver, "Paso 7 Modal No Encontrado");
						logger.error("Error mensaje 1 ->" + mensajeEsperado + " No fue encontrado ");
					}

					try {
						driver.findElement(By.xpath(p.getProperty("btnAceptar"))).click();

					} catch (Exception e) {
						setValPaso7("FALLO");
						Interaccion.photo_Evid_Fallida(driver, "Paso 7 msm Confirmar");
						logger.error("No se pudo dar clic en boton Aceptar");
					}

					// Mensaje Esperado 2
					try {
						driver.findElement(By.xpath("//*[@text='" + mensajeEsperado2 + "']"));
					} catch (Exception e) {
						// evidencia modal no encontrado
						Interaccion.photo_Evid_Fallida(driver, "Paso 7 Modal No Encontrado");
						logger.error("Error mensaje 2 ->" + mensajeEsperado2 + " No fue encontrado ");
					}

					try {
						driver.findElement(By.xpath(p.getProperty("btnAceptar"))).click();

					} catch (Exception e) {
						setValPaso7("FALLO");
						logger.error("No se pudo dar clic en boton Aceptar");

					}

				} catch (Exception e) {
					Interaccion.photo_Evid_Fallida(driver, "Paso 7 msm Confirmar");
					logger.error("No se pudieron validar los datos insertados " + Interaccion.mensajeError(e));

				}

			} catch (Exception e) {
				setValPaso7("FALLO");
				Interaccion.photo_Evid_Fallida(driver, "Paso 7 ");
				logger.error("Error insercion de datos fallida " + Interaccion.mensajeError(e));

			}
		} else {
			logger.info("Inicio de omitir paso 7");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			Interaccion.ocultarTeclado(driver);
			logger.info("Oculto teclado");

			try {
				// System.err.println("Inicio de skip");
				Thread.sleep(1000);
				listValidacion(driver);
				Thread.sleep(1000);
				driver.findElement(By.id("next")).click();
				setValPaso7("EXITOSO");

			} catch (Exception e) {
				setValPaso7("FALLO");
				logger.error("Error al omitir paso 7");

			}
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
			Interaccion.photo_Evid_Fallida(driver, "Step4_listValid");
			logger.error("Problema con el desplazamiento \n ");
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
			Interaccion.photo_Evid_Fallida(driver, "Step7_listDepart");
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
		infoCodeudor(driver);

	}
}
	
