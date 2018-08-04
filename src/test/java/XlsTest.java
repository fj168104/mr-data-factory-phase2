
import com.google.common.collect.Lists;
import com.mr.modules.api.xls.export.ExportConfigFactory;
import com.mr.modules.api.xls.export.FileExportor;
import com.mr.modules.api.xls.export.domain.common.ExportCell;
import com.mr.modules.api.xls.export.domain.common.ExportConfig;
import com.mr.modules.api.xls.export.domain.common.ExportResult;
import com.mr.modules.api.xls.export.exception.FileExportException;
import com.mr.modules.api.xls.importfile.ConfigParser;
import com.mr.modules.api.xls.importfile.ConfigurationParserFactory;
import com.mr.modules.api.xls.importfile.FileImportExecutor;
import com.mr.modules.api.xls.importfile.domain.MapResult;
import com.mr.modules.api.xls.importfile.domain.common.Configuration;
import com.mr.modules.api.xls.importfile.domain.common.ImportCell;
import com.mr.modules.api.xls.importfile.exception.FileImportException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by fengj on 2018/3/23.
 */
public class XlsTest {
	public static void main(String[] args) throws FileImportException, FileNotFoundException, FileExportException, URISyntaxException {

//		testImport();
//        URL u = Test.class.getResource("import/config.xml");
//        System.out.println(u.toString());
		testImportConfig();
//		testImportByNoConfig();
	}



	/**
	 * 把excel导入，变成map
	 *
	 * @throws FileImportException
	 * @throws FileNotFoundException
	 * @throws URISyntaxException
	 */
	public static void testImportConfig() throws FileImportException, FileNotFoundException, URISyntaxException {

		ConfigParser configParser = ConfigurationParserFactory.getConfigParser(Configuration.ParserType.XML);
		URI uri = XlsTest.class.getResource("import/testImport.xlsx").toURI();
		File importFile = new File(uri);
		Configuration configuration = null;
		try {
			configuration = configParser.getConfig(XlsTest.class.getResourceAsStream("import/config.xml"));
			MapResult mapResult = (MapResult) FileImportExecutor.importFile(configuration, importFile, importFile.getName());
			List<Map> maps = mapResult.getResult();
			for (Map<String, Object> map : maps) {
				int index = (int) map.get("index");
				float f1 = (float) map.get("float");
				String string = (String) map.get("string");
				Date date = (Date) map.get("date");
				BigDecimal bigDecimal = (BigDecimal) map.get("bigdecimal");
				System.out.println("index :" + index + " f1 : " + f1 + " string : " + string
						+ " date : " + date.toString() + " bigdecimal " + bigDecimal);
			}
		} catch (FileImportException e) {
			System.out.println(e);
		}
	}

	/**
	 * 把excel导入，变成map
	 *
	 * @throws FileImportException
	 * @throws FileNotFoundException
	 * @throws URISyntaxException
	 */
	public static void testImportByNoConfig() throws FileImportException, FileNotFoundException, URISyntaxException {
		File importFile = new File("/home/fengjiang/Documents/处罚与处分记录.xlsx");
		Configuration configuration = new Configuration();
		try {
			configuration.setStartRowNo(1);
			List<ImportCell> importCells = Lists.newArrayList(
					new ImportCell(0 ,"index"),
					new ImportCell(1, "float"),
					new ImportCell(2, "string"),
					new ImportCell(3, "date"),
					new ImportCell(4,"bigdecimal")
			);
			configuration.setImportCells(importCells);
			configuration.setImportFileType(Configuration.ImportFileType.EXCEL);

			MapResult mapResult = (MapResult) FileImportExecutor.importFile(configuration, importFile, importFile.getName());
			List<Map> maps = mapResult.getResult();
			for (Map<String, Object> map : maps) {
				Object index = map.get("index");
				Object f1 = map.get("float");
				Object string =map.get("string");
				Object date =  map.get("date");
				Object bigDecimal = map.get("bigdecimal");
				System.out.println("index :" + index + " f1 : " + f1 + " string : " + string
						+ " date : " + date + " bigdecimal : " + bigDecimal.toString());
			}
		} catch (FileImportException e) {
			System.out.println(e);
		}
	}


	/**
	 * excel导出，从一个map或者实体类变成excel
	 *
	 * @throws FileNotFoundException
	 * @throws FileExportException
	 */
	public static void testExportByConfig() throws FileNotFoundException, FileExportException {
		ExportConfig exportConfig = ExportConfigFactory.getExportConfig(XlsTest.class.getResourceAsStream("export/exportconfig.xml"));
		//map也可以换成一个实体类
		List<Map> lists = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			Map<String, Object> maps = new HashMap<>();
			maps.put("index", i);
			maps.put("date", new Date());
			maps.put("greet", "hi" + i);
			maps.put("float", Float.valueOf(i));
			maps.put("bigdecimal", BigDecimal.valueOf(i));
			lists.add(maps);
		}
		ExportResult exportResult = FileExportor.getExportResult(exportConfig, lists);
		//输出文件在d盘根目录，系统是win
//        OutputStream outputStream = new FileOutputStream("d://output.xlsx");
		//系统mac
		OutputStream outputStream = new FileOutputStream("/home/fengjiang/Documents/output.xlsx");
		exportResult.export(outputStream);

	}

	public static void testExportByNoConfig() throws FileNotFoundException, FileExportException {
		ExportConfig exportConfig = new ExportConfig();
		exportConfig.setFileName("output1.xlsx");
		List<ExportCell> exportCells = Lists.newArrayList(
				new ExportCell("index"),
				new ExportCell("date"),
				new ExportCell("greet"),
				new ExportCell("float"),
				new ExportCell("bigdecimal")
		);
		exportConfig.setExportCells(exportCells);

		//map也可以换成一个实体类
		List<Map> lists = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			Map<String, Object> maps = new HashMap<>();
			maps.put("index", i);
			maps.put("date", new Date());
			maps.put("greet", "hi" + i);
			maps.put("float", Float.valueOf(i));
			maps.put("bigdecimal", BigDecimal.valueOf(i));
			lists.add(maps);
		}
		ExportResult exportResult = FileExportor.getExportResult(exportConfig, lists);
		//输出文件在d盘根目录，系统是win
//        OutputStream outputStream = new FileOutputStream("d://output.xlsx");
		//系统mac
		OutputStream outputStream = new FileOutputStream("/home/fengjiang/Documents/output.xlsx");
		exportResult.export(outputStream);

	}

	/**
	 * 我用于web下载时的代码
	 */
	private void testExportInDownload() {
//        HttpServletResponse httpResponse = response();
//        ExportType exportType = exportResult.getExportType();
//        httpResponse.setContentType(exportType.getContentType());
//        httpResponse.setHeader("Content-disposition", "attachment;filename=" + reEncodeExportName(exportResult.getFileName()) + "." + exportType.getFileType());
//        try {
//            exportResult.export(httpResponse.getOutputStream());
//        } catch (IOException e) {
//            throw new FileExportException(" exportFile " + e.getMessage());
//        }
	}
}
