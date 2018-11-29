package data;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import interaccion.Interaccion;
import steps.Step1;
import steps.Step10;
import steps.Step11;
import steps.Step12;
import steps.Step2;
import steps.Step3;
import steps.Step4;
import steps.Step5;
import steps.Step6;
import steps.Step7;
import steps.Step8;
import steps.Step9;
import steps.StepLogin;

public class logExcel2 {

	public static ArrayList<String[]> listReporte = new ArrayList<>();

	private static XSSFWorkbook workbook;

	@SuppressWarnings("static-access")
	public static void cargarVariable(int contador) throws Exception {

		Data data = new Data("src\\data\\Prueba.xlsx");
		ArrayList<StepLogin> stepLogin = data.constructorObjetoPasoLogin();
		ArrayList<Step1> step1 = data.constructorObjetoPaso1();
		ArrayList<Step2> step2 = data.constructorObjetoPaso2();
		ArrayList<Step3> step3 = data.constructorObjetoPaso3();
		ArrayList<Step4> step4 = data.constructorObjetoPaso4();
		ArrayList<Step5> step5 = data.constructorObjetoPaso5();
		ArrayList<Step6> step6 = data.constructorObjetoPaso6();
		ArrayList<Step7> step7 = data.constructorObjetoPaso7();
		ArrayList<Step8> step8 = data.constructorObjetoPaso8();
		ArrayList<Step9> step9 = data.constructorObjetoPaso9();
		ArrayList<Step10> step10 = data.constructorObjetoPaso10();
		ArrayList<Step11> step11 = data.constructorObjetoPaso11();
		ArrayList<Step12> step12 = data.constructorObjetoPaso12();
		if (contador == 0) {
			String[] encabezado = new String[] { "FechaEjecucion", "idExcel", "idMicroempresario", "pasoLogin", "paso1",
					"paso2", "paso3", "paso4", "paso5", "paso6", "paso7", "paso8", "paso9", "pasao10", "paso11",
					"paso12" };
			listReporte.add(encabezado);
		}
		String[] filas = new String[] { Interaccion.fecha_Ejec_Steps(), Integer.toString(contador + 1),
				step1.get(contador).getId().trim(), stepLogin.get(contador).getValLogin(),
				step1.get(contador).getValPaso1(), step2.get(contador).getValPaso2(), step3.get(contador).getValPaso3(),
				step4.get(contador).getValPaso4(), step5.get(contador).getValPaso5(), step6.get(contador).getValPaso6(),
				step7.get(contador).getValPaso7(), step8.get(contador).getValPaso8(), step9.get(contador).getValPaso9(),
				step10.get(contador).getValPaso10(), step11.get(contador).getValPaso11(),
				step12.get(contador).getValPaso12() };
		listReporte.add(filas);

	}

	public static void generarExcel() throws Exception {

		Calendar calendario = new GregorianCalendar();
		
		XSSFSheet hoja = null;
		XSSFWorkbook workbook = null;
			
		try {

	

			workbook = new XSSFWorkbook();
			hoja = workbook.createSheet();
			workbook.setSheetName(0, "Hoja excel");

		} catch (Exception e) {
			System.out.print("No se creo archivo");
		}


		XSSFCell celda;
		XSSFRow fila;

		for (int i = 0; i < listReporte.size(); i++) {
			int contador = i;
			fila = hoja.createRow(contador);// Crea filas
			String filasReporte[] = listReporte.get(contador);
			for (int j = 0; j < filasReporte.length; ++j) {
				String validar = filasReporte[j];
				celda = fila.createCell(j);
				celda.setCellValue(validar);
			}

		}
		try {					
			FileOutputStream output_file = new FileOutputStream(
					Interaccion.getCarpetaHora() + "\\" + calendario.get(Calendar.HOUR_OF_DAY)
						+ "." + calendario.get(Calendar.MINUTE) + "-LogExcel.xlsx");
			workbook.write(output_file);
			output_file.close();
			
		} catch (Exception e) {
			System.out.println("No se guardo el archivo ");
		}



	}

}
