package config;

import java.io.FileReader;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import interaccion.Interaccion;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;

public class SetUpDriverDevice {

	private static AndroidDriver<?> driver;
	final static Logger logger = LogManager.getLogger(SetUpDriverDevice.class);
	final static Properties p = new Properties();


	/**
	 * Este metodo retorno la configuraci�n del driver en appium
	 * lee las variables del archivo externo en el properties
	 * como es nombre de dispositivo, version, plataforma, ruta del app, etc
	 * 
	 */
	
	public static AndroidDriver<?> setUp() {
		

		try {

			Properties p = new Properties();
			p.load(new FileReader("src\\config\\config.properties"));

			// Configuracion hacia un archivo properties.
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
			capabilities.setCapability("deviceName", p.getProperty("deviceName"));
			capabilities.setCapability(CapabilityType.BROWSER_NAME, p.getProperty("versSO"));
			capabilities.setCapability(CapabilityType.VERSION, p.getProperty("versAndroid"));
			capabilities.setCapability("platformName", p.getProperty("versSO"));
			capabilities.setCapability("app", p.getProperty("appFile"));
			capabilities.setCapability("appPackage", p.getProperty("appPackage"));
			capabilities.setCapability("appActivity", p.getProperty("appActivity"));
			capabilities.setCapability("autoGrantPermissions", "true");
		//	capabilities.setCapability("unicodeKeyboard", true);
			capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD,true);
			
			//capabilities.setCapability("resetKeyboard", true);	//
			
			/*
			 * Deshabilita el teclado del movil y usa el interno de appium
			 *  capabilities.setCapability("unicodeKeyboard", true);
			 *  capabilities.setCapability("resetKeyboard", true);
			 */
			driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().getSize().getHeight();
			driver.manage().window().getSize().getWidth();
			logger.info(driver.manage().window().getSize().getHeight());
			logger.info(driver.manage().window().getSize().getWidth());
			logger.info("Conexion correcta al movil");
			
			
			
		} catch (Exception e) {
			logger.fatal("Error en la configuracion del movil \n" + Interaccion.mensajeError(e));			
			System.exit(-1);
		}
		return driver;
	}
}
