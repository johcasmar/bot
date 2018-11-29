package data;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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

/**
 * Clase encargada de la lectura y mapeo del excel
 * 
 * @author cavila
 * @version 1.0
 *
 */
public class Data {

	private String file;

	/**
	 * Metodo constructor de la clase Data con la ruta del excel .xlsx
	 * 
	 * @param file
	 */
	public Data(String file) {
		this.file = file;
	}

	final static Logger logger = LogManager.getLogger(Data.class);

	public static ArrayList<ArrayList<StringBuffer>> excel = new ArrayList<>();

	/**
	 * Este metodo se encarga de leer el archivo excel, recorriendo cada hoja del
	 * mismo. Se valida que el archivo este en la ruta, y con el nombre
	 * correspondiente.
	 * 
	 * @author lroah
	 */
	public void orquestador() {
		try {
			File f = new File(this.file);
			// RegistroDemoEscritorio
			if (f.exists()) {
				for (int i = 0; i <= 12; i++) {
					int HojaIdx = i;
					convertSelectedSheetInXLXSFileToCSV(f, HojaIdx);

				}
				logger.info(" El documento de excel se lee correctamente");
			} else {
				logger.error(" Ruta o nombre del excel no encontrada /...: " + file);

			}
		} catch (Exception e) {
			logger.fatal(" Error en la lectura del excel  \n" + Interaccion.mensajeError(e));
			System.exit(-1);

		}
	}

	/**
	 * Retorna el arreglo de una lista con la lectura del excel
	 * 
	 * @return the excel
	 */
	public static ArrayList<ArrayList<StringBuffer>> getExcel() {
		return excel;
	}

	/**
	 * Este metodo se encarga de leer y convertir el archivo de excel en parametros
	 * 
	 * @param xlsxFile
	 * @param HojaIdx
	 * @throws Exception
	 */
	private static void convertSelectedSheetInXLXSFileToCSV(File xlsxFile, int HojaIdx) throws Exception {

		FileInputStream fileInStream = new FileInputStream(xlsxFile);
		// Abrir el xlsx y obtener la hoja solicitada del libro de trabajo
		XSSFWorkbook workBook = new XSSFWorkbook(fileInStream);
		XSSFSheet selSheet = workBook.getSheetAt(HojaIdx);
		// Interactura a traves de todas las filas de la hoja seleccionada
		Iterator<Row> rowIterator = selSheet.iterator();
		// Este Array representa cada una de las hojas
		ArrayList<StringBuffer> hoja = new ArrayList<>();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			StringBuffer sb = new StringBuffer();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if (sb.length() != 0) {
					sb.append(" | ");
				}
				switch (cell.getCellTypeEnum()) {
				case STRING:
					String txt = cell.getStringCellValue().toString().trim();
					if (txt.equalsIgnoreCase("N/A")) {
						sb.append("");
					} else {
						sb.append(txt);

					}
					// sb.append(cell.getStringCellValue());
					break;
				case NUMERIC:
					double n = cell.getNumericCellValue();
					DecimalFormat df = new DecimalFormat("#######0");
					String parse = df.format(n);
					sb.append(parse);
					break;
				case BOOLEAN:
					sb.append(cell.getBooleanCellValue());
					break;

				default:

				}
			}

			hoja.add(sb);
		}

		excel.add(hoja);
		workBook.close();

	}

	/**
	 * Contruye el metodo del objeto en el Login del excel
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<StepLogin> constructorObjetoPasoLogin() throws Exception {

		ArrayList<StepLogin> stepLoginList = new ArrayList<>();
		ArrayList<StringBuffer> pageLogin = excel.get(0);

		for (int i = 1; i < pageLogin.size(); i++) {
			if (pageLogin.get(i).toString().isEmpty()) {

				break;

			} else {
				String[] param = pageLogin.get(i).toString().split("\\|");
				stepLoginList.add(new StepLogin(param[0], param[1], param[2], param[3]));
			}

		}

		return stepLoginList;
	}

	/**
	 * Construye el metodo del objeto en el paso 1(uno) del excel
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Step1> constructorObjetoPaso1() throws Exception {

		ArrayList<Step1> step1List = new ArrayList<>();
		ArrayList<StringBuffer> page1 = excel.get(1);

		// Empieza en 1 para ignorar el encabezado
		for (int i = 1; i < page1.size(); i++) {
			if (page1.get(i).toString().isEmpty()) {
				break;

			} else {
				String[] param = page1.get(i).toString().split("\\|");
				Step1 step1 = new Step1(param[0], param[1], param[2], param[3], param[4], param[5], param[6], param[7],
						param[8], param[9], param[10], param[11], param[12], param[13]);
				step1List.add(step1);
			}

		}

		return step1List;
	}

	/**
	 * Construye el metodo del objeto en el paso 2(dos) del excel
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Step2> constructorObjetoPaso2() throws Exception {
		ArrayList<Step2> step2List = new ArrayList<>();
		ArrayList<StringBuffer> page2 = excel.get(2);

		for (int i = 1; i < page2.size(); i++) {
			if (page2.get(i).toString().isEmpty()) {
				break;

			} else {
				String[] param = page2.get(i).toString().split("\\|");
				Step2 step2 = new Step2(param[0], param[1], param[2], param[3], param[4], param[5]);
				step2List.add(step2);
			}

		}

		return step2List;
	}

	/**
	 * Construye el metodo del objeto en el paso 3(tres) del excel
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Step3> constructorObjetoPaso3() throws Exception {

		ArrayList<Step3> step3List = new ArrayList<>();
		ArrayList<StringBuffer> page2 = excel.get(3);

		for (int i = 1; i < page2.size(); i++) {
			if (page2.get(i).toString().isEmpty()) {
				break;

			} else {
				String[] param = page2.get(i).toString().split("\\|");
				Step3 step3 = new Step3(param[0], param[1], param[2], param[3], param[4], param[5], param[6], param[7],
						param[8], param[9], param[10]);
				step3List.add(step3);
			}

		}

		return step3List;
	}

	/**
	 * Construye el metodo del objeto en el paso 4(cuatro) del excel
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Step4> constructorObjetoPaso4() throws Exception {

		ArrayList<Step4> step4List = new ArrayList<>();
		ArrayList<StringBuffer> page4 = excel.get(4);

		for (int i = 1; i < page4.size(); i++) {
			// if (page4.get(i).toString().isEmpty()) {
			// break;
			//
			// } else {
			try {

				String[] param = page4.get(i).toString().split("\\|");

				Step4 step4 = new Step4(param[0], param[1], param[2], param[3], param[4], param[5], param[6], param[7],
						param[8], param[9], param[10], param[11], param[12], param[13], param[14], param[15],
						param[16]);
				step4List.add(step4);

			} catch (Exception e) {

			}
			// }

		}

		return step4List;
	}

	/**
	 * Construye el metodo del objeto en el paso 5(cinco) del excel
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Step5> constructorObjetoPaso5() throws Exception {

		ArrayList<Step5> step5List = new ArrayList<>();
		ArrayList<StringBuffer> page5 = excel.get(5);

		for (int i = 1; i < page5.size(); i++) {
			if (page5.get(i).toString().isEmpty()) {
				break;

			} else {
				String[] param = page5.get(i).toString().split("\\|");
				Step5 step5 = new Step5(param[0], param[1], param[2], param[3], param[4], param[5]);
				step5List.add(step5);
			}

		}

		return step5List;
	}

	/**
	 * Construye el metodo del objeto en el paso 6(seis) del excel
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Step6> constructorObjetoPaso6() throws Exception {

		ArrayList<Step6> step6List = new ArrayList<>();
		ArrayList<StringBuffer> page6 = excel.get(6);

		for (int i = 1; i < page6.size(); i++) {
			if (page6.get(i).toString().isEmpty()) {
				break;

			} else {
				String[] param = page6.get(i).toString().split("\\|");
				Step6 step6 = new Step6(param[0], param[1], param[2], param[3], param[4], param[5], param[6], param[7]);
				step6List.add(step6);
			}

		}

		return step6List;
	}

	/**
	 * Construye el metodo del objeto en el paso 7(siete) del excel
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Step7> constructorObjetoPaso7() throws Exception {

		ArrayList<Step7> step7List = new ArrayList<>();
		ArrayList<StringBuffer> page7 = excel.get(7);

		for (int i = 1; i < page7.size(); i++) {
			// if (page7.get(i).toString().isEmpty()) {
			// break;
			//
			// } else {
			String[] param = page7.get(i).toString().split("\\|");
			Step7 step7 = new Step7(param[0], param[1], param[2], param[3], param[4], param[5], param[6], param[7],
					param[8], param[9], param[10], param[11], param[12], param[13], param[14], param[15]);
			step7List.add(step7);
			// }

		}

		return step7List;
	}

	/**
	 * Construye el metodo del objeto en el paso 8(ocho) del excel
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Step8> constructorObjetoPaso8() throws Exception {

		ArrayList<Step8> step8List = new ArrayList<>();
		ArrayList<StringBuffer> page8 = excel.get(8);

		for (int i = 1; i < page8.size(); i++) {
			if (page8.get(i).toString().isEmpty()) {
				break;

			} else {
				String[] param = page8.get(i).toString().split("\\|");
				Step8 step8 = new Step8(param[0], param[1], param[2], param[3], param[4], param[5], param[6], param[7],
						param[8]);
				step8List.add(step8);
			}

		}

		return step8List;
	}

	/**
	 * Construye el metodo del objeto en el paso 9(nueve) del excel
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Step9> constructorObjetoPaso9() throws Exception {

		ArrayList<Step9> step9List = new ArrayList<>();
		ArrayList<StringBuffer> page9 = excel.get(9);

		for (int i = 1; i < page9.size(); i++) {
			if (page9.get(i).toString().isEmpty()) {
				break;

			} else {
				String[] param = page9.get(i).toString().split("\\|");
				Step9 step9 = new Step9(param[0], param[1], param[2], param[3]);
				step9List.add(step9);
			}

		}

		return step9List;
	}

	/**
	 * Construye el metodo del objeto en el paso 10(diez) del excel
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Step10> constructorObjetoPaso10() throws Exception {

		ArrayList<Step10> step10List = new ArrayList<>();
		ArrayList<StringBuffer> page10 = excel.get(10);

		for (int i = 1; i < page10.size(); i++) {
			if (page10.get(i).toString().isEmpty()) {
				break;

			} else {
				String[] param = page10.get(i).toString().split("\\|");
				Step10 step10 = new Step10(param[0], param[1], param[2], param[3]);
				step10List.add(step10);
			}

		}

		return step10List;
	}

	/**
	 * Construye el metodo del objeto en el paso 11(once) del excel
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Step11> constructorObjetoPaso11() throws Exception {

		ArrayList<Step11> step11List = new ArrayList<>();
		ArrayList<StringBuffer> page11 = excel.get(11);

		for (int i = 1; i < page11.size(); i++) {
			if (page11.get(i).toString().isEmpty()) {
				break;

			} else {
				String[] param = page11.get(i).toString().split("\\|");
				Step11 step11 = new Step11(param[0], param[1], param[2], param[3], param[4]);
				step11List.add(step11);
			}

		}

		return step11List;
	}

	/**
	 * Construye el metodo del objeto en el paso 12(doce) del excel
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Step12> constructorObjetoPaso12() throws Exception {

		ArrayList<Step12> step12List = new ArrayList<>();
		ArrayList<StringBuffer> page12 = excel.get(12);

		for (int i = 1; i < page12.size(); i++) {
			if (page12.get(i).toString().isEmpty()) {
				break;

			} else {
				String[] param = page12.get(i).toString().split("\\|");
				Step12 step12 = new Step12(param[0], param[1], param[2], param[3], param[4], param[5]);
				step12List.add(step12);
			}

		}

		return step12List;
	}

}