package steps;

import io.appium.java_client.android.AndroidDriver;

/**Clase principal, el orquestador llama la clase que necesita
 * @author cavila
 *
 */
public interface IStep {

	public void orquestador(AndroidDriver<?> driver) throws Exception;

}
