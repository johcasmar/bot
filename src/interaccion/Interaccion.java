package interaccion;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class Interaccion {

	final static Logger logger = LogManager.getLogger(Interaccion.class);
	private static File carpetaFallo;
	private static File carpetaEvidencia;
	private static File carpetaHora;

	public static File getCarpetaHora() {
		return carpetaHora;
	}

	public static void setCarpetaHora(File carpetaHora) {
		Interaccion.carpetaHora = carpetaHora;
	}
	static long Tim1 = System.currentTimeMillis();

	/**
	 * Este metodo realiza el desplazamiento del patron, segun las coordenadas del
	 * properties
	 * 
	 * @param driver
	 */
	public static void patron(AndroidDriver<?> driver) {
		Properties p = new Properties();
		try {
			p.load(new FileReader("src\\config\\config.properties"));
		} catch (Exception e) {

			System.err.println("No se encontro la ruta del archivo");
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("pl_pattern"))));
		
		/**
		 * Se realiza selección del patron de autenticación mediante el Screen Size del dispositivo
		 * 
		 * @param driver
		 */
		
		int Height = driver.manage().window().getSize().getHeight();
		int Width = driver.manage().window().getSize().getWidth();
		
		int punntoFinalY = Height * -2 / 100;

		int puntoFinalX = Width * 36 / 100;
		
		int puntoInicialX = Width * 16 / 100;

		int puntoInicialY = Height * 36 / 100;

		int puntoMedioX =  Width * 3 / 100;

		int puntoMedioY = Height * 42 / 100;
		
		(new TouchAction(driver))
				.press((puntoInicialX),
						(puntoInicialY))
				.moveTo((puntoMedioX), (puntoMedioY))
				.moveTo((puntoFinalX), (punntoFinalY))
				.release().perform();
	}

	/**
	 * Este metodo permite seleccionar una fecha estatica
	 * 
	 * @param driver
	 */
	public static void seleccionarFecha(AndroidDriver<?> driver) {
		driver.findElement(By.id("birthdate")).click();
		driver.findElement(By.id("android:id/date_picker_year")).click();
		driver.findElement(By.xpath("//*[@text='1999']")).click();
		driver.findElement(By.id("button1")).click();
	}

	public static void folderEjecucion(AndroidDriver<?> driver) {
		try {
			Calendar calendario = new GregorianCalendar();
			Date ahora = new Date();
			SimpleDateFormat formateador = new SimpleDateFormat("MMMM");

			File carpetaMes = new File("logs\\" + calendario.get(Calendar.DATE) + "-" + formateador.format(ahora) + "-"
					+ calendario.get(Calendar.YEAR));
			carpetaMes.mkdir();

			carpetaHora = new File(
					carpetaMes + "\\" + calendario.get(Calendar.HOUR) + "." + calendario.get(Calendar.MINUTE));
			carpetaHora.mkdir();

			carpetaEvidencia = new File(carpetaHora + "\\photoEvidencia");
			carpetaEvidencia.mkdir();

			carpetaFallo = new File(carpetaHora + "\\photoFallo");
			carpetaFallo.mkdir();

		} catch (Exception e) {
			logger.error("Error al generar las carpetas de ejecucion");
		}
	}

	/**
	  * Este metodo toma un pantallazo y lo almacena en la ruta del directorio
	  * 
	  * @param driver
	  * @param Paso
	  */
	 public static void photo_Evidencia(AndroidDriver<?> driver, String Paso) {
	  try {
	   File scrFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	   FileUtils.copyFile(scrFile1, new File(carpetaEvidencia + "\\Experian " + Paso + "_" + Tim1 + ".png"));
	   
	  } catch (IOException e) {
	   logger.error("Error al generar la foto en " + Paso);
	  }
	 }

	 /**
	  * Este metodo toma pantallazo de los casos fallidos y los almacena
	  * 
	  * @param driver
	  * @param Paso
	  */
	 public static void photo_Evid_Fallida(AndroidDriver<?> driver, String Paso) {
	  Calendar calendario = new GregorianCalendar();

	  File scrFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	  StringBuffer fecha = new StringBuffer();
	  fecha.append(calendario.get(Calendar.DAY_OF_MONTH) + "-");
	  fecha.append((calendario.get(Calendar.MONTH)) + 1 + "-");
	  fecha.append(calendario.get(Calendar.YEAR) + ".");
	  fecha.append(calendario.get(Calendar.HOUR_OF_DAY) + ".");
	  fecha.append(calendario.get(Calendar.MINUTE) + ".");
	  fecha.append(calendario.get(Calendar.SECOND) + ".");

	  try {
	   FileUtils.copyFile(scrFile1, new File(carpetaFallo + "\\Error " + Paso + "_" + fecha.toString() + ".png"));
	   logger.warn("La evidencia se registra en la ruta logs/FechaActual/HoraActual " + Paso + "_" + fecha.toString()
	     + ".png");
	  } catch (IOException e) {
	   logger.error("Error al generar la foto en " + Paso);
	  }
	 }
	 
	 
	/**
	 * Este metodo toma la fecha de ejecucion y la imprime en el encabezado del log
	 */
	
		public static String fecha_Ejec_Steps() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("MMMM");

		Calendar calendario = new GregorianCalendar();
		String Hora = "" + calendario.get(Calendar.HOUR) + "." + calendario.get(Calendar.MINUTE);

		String InicioEjecucion = (calendario.get(Calendar.DATE) + "/" + formateador.format(ahora) + "/"
				+ calendario.get(Calendar.YEAR) + "/" + Hora);

		return InicioEjecucion;

	}

	public static void fecha_Ejec() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("MMMM");

		Calendar calendario = new GregorianCalendar();
		String inicio_Eje = "" + calendario.get(Calendar.HOUR) + ":" + calendario.get(Calendar.MINUTE) + ":"
				+ calendario.get(Calendar.SECOND);

		logger.info(" EJECUCION en Android 5.1.1 Lollipop " + calendario.get(Calendar.DATE) + " de "
				+ formateador.format(ahora) + " del " + calendario.get(Calendar.YEAR) + " a las " + inicio_Eje);

	}


	/**
	 * Captura el error general y lo retorna con solo el elemento buscado
	 * 
	 * @param e
	 * @return
	 */
	public static String mensajeError(Exception e) {
		String msj = e.getMessage();
		try {
			String[] arreglo = msj.split("(?=Element info)");
			return arreglo[1];
		} catch (Exception e1) {
			return msj;
		}
	}

	/**
	 * Oculta el teclado y en caso de no encontrarlo controla el error
	 * 
	 * @param driver
	 */
	public static void ocultarTeclado(AndroidDriver<?> driver) {
		try {
			driver.hideKeyboard();
		} catch (Exception e) {

		}

	}

	/**
	 * Este metodo pone los puntos de miles para validar el parametro numerico con
	 * una longitud maxima de 14 caracteres
	 * 
	 * @param dato
	 * @return
	 */
	public static String ponerPuntos(String dato) {
		if (dato == null)
			return "0";
		if (dato.equals(""))
			return "0";
		if (dato.equals("0"))
			return "0";
		if (dato.equals(null))
			return "0";

		String dato1 = "";
		char[] aux1 = dato.toCharArray();
		for (int i = 0; i < aux1.length; i++) {
			if (aux1[i] != '.' && aux1[i] != ',')
				dato1 = dato1 + String.valueOf(aux1[i]);
			else
				i = aux1.length + 2;
		}
		String str = dato1;
		String salida = "";
		int longitud = str.length();
		if (longitud < 4)
			return dato1;
		if (longitud == 4) {
			String sub2 = str.substring(0, 1);
			String sub1 = str.substring(1, 4);
			salida = sub2 + "," + sub1;
		}
		if (longitud == 5) {
			String sub2 = str.substring(0, 2);
			String sub1 = str.substring(2, 5);
			salida = sub2 + "," + sub1;
		}
		if (longitud == 6) {
			String sub2 = str.substring(0, 3);
			String sub1 = str.substring(3, 6);
			salida = sub2 + "," + sub1;
		}
		if (longitud == 7) {
			String sub2 = str.substring(0, 1);
			String sub1 = str.substring(1, 4);
			String sub0 = str.substring(4, 7);
			salida = sub2 + "," + sub1 + "," + sub0;
		}
		if (longitud == 8) {
			String sub2 = str.substring(0, 2);
			String sub1 = str.substring(2, 5);
			String sub0 = str.substring(5, 8);
			salida = sub2 + "," + sub1 + "," + sub0;
		}
		if (longitud == 9) {
			String sub2 = str.substring(0, 3);
			String sub1 = str.substring(3, 6);
			String sub0 = str.substring(6, 9);
			salida = sub2 + "," + sub1 + "," + sub0;
		}
		if (longitud == 10) {
			String sub2 = str.substring(0, 1);
			String sub1 = str.substring(1, 4);
			String sub0 = str.substring(4, 7);
			String sub = str.substring(7, 10);
			salida = sub2 + "," + sub1 + "," + sub0 + "," + sub;
		}
		if (longitud == 11) {
			String sub2 = str.substring(0, 2);
			String sub1 = str.substring(2, 5);
			String sub0 = str.substring(5, 8);
			String sub = str.substring(8, 11);
			salida = sub2 + "," + sub1 + "," + sub0 + "," + sub;
		}
		if (longitud == 12) {
			String sub2 = str.substring(0, 3);
			String sub1 = str.substring(3, 6);
			String sub0 = str.substring(6, 9);
			String sub = str.substring(9, 12);
			salida = sub2 + "," + sub1 + "," + sub0 + "," + sub;
		}
		if (longitud == 13) {
			String sub2 = str.substring(0, 1);
			String sub1 = str.substring(1, 4);
			String sub0 = str.substring(4, 7);
			String sub = str.substring(7, 10);
			String su = str.substring(10, 13);
			salida = sub2 + "," + sub1 + "," + sub0 + "," + sub + "," + su;
		}
		if (longitud >= 14) {
			System.err.println("Longitud no puede ser mayor o igual a 14");
			return dato;

		}
		return salida;
	}

	/**
	 * Este metodo inserta y valida que el numero insertado corresponda ya que en
	 * ocasiones el teclado no digita los caracteres que corresponde
	 * 
	 * @param driver
	 * @param id
	 * @param txt
	 */
	public static void insertarMonto(AndroidDriver<?> driver, String id, String txt) throws Exception {
		driver.findElement(By.id(id)).click();
		for (int i = 0; i < txt.length(); i++) {
			int vlr_fin = i+1;
			String codigo = txt.substring(i, vlr_fin);
			switch (codigo) {
			case "0":
				driver.pressKeyCode(AndroidKeyCode.KEYCODE_NUMPAD_0);
				break;
			case "1":
				driver.pressKeyCode(AndroidKeyCode.KEYCODE_NUMPAD_1);
				break;
			case "2":
				driver.pressKeyCode(AndroidKeyCode.KEYCODE_NUMPAD_2);
				break;
			case "3":
				driver.pressKeyCode(AndroidKeyCode.KEYCODE_NUMPAD_3);
				break;
			case "4":
				driver.pressKeyCode(AndroidKeyCode.KEYCODE_NUMPAD_4);
				break;
			case "5":
				driver.pressKeyCode(AndroidKeyCode.KEYCODE_NUMPAD_5);
				break;
			case "6":
				driver.pressKeyCode(AndroidKeyCode.KEYCODE_NUMPAD_6);
				break;
			case "7":
				driver.pressKeyCode(AndroidKeyCode.KEYCODE_NUMPAD_7);
				break;
			case "8":
				driver.pressKeyCode(AndroidKeyCode.KEYCODE_NUMPAD_8);
				break;
			case "9":
				driver.pressKeyCode(AndroidKeyCode.KEYCODE_NUMPAD_9);
				break;
			default:
				break;
			}
		}
	}

}
