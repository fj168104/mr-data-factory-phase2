import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public class HwpfTest {

	/**
	 * word 2007 取
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testWord2007() {
		try {
			OPCPackage opcPackage = POIXMLDocument.openPackage("/home/fengjiang/Documents/4224334.doc");
			POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
			String text2007 = extractor.getText();
			System.out.println(text2007);

			opcPackage.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlException e) {
			e.printStackTrace();
		} catch (OpenXML4JException e) {
			e.printStackTrace();
		}
	}


	/**
	 * word 2003 取
	 * @throws Exception
	 */
		@SuppressWarnings("deprecation")
		@Test
		public void testWord2003 () throws Exception {
			InputStream is = new FileInputStream("/home/fengjiang/Documents/2015-05-08_002359391.doc");
			WordExtractor extractor = new WordExtractor(is);
			//输出word文档所有的文本
			System.out.println(extractor.getText());
			System.out.println(extractor.getTextFromPieces());
			//输出页眉的内容
			System.out.println("页眉：" + extractor.getHeaderText());
			//输出页脚的内容
			System.out.println("页脚：" + extractor.getFooterText());
			//输出当前word文档的元数据信息，包括作者、文档的修改时间等。
			System.out.println(extractor.getMetadataTextExtractor().getText());
			//获取各个段落的文本
			String paraTexts[] = extractor.getParagraphText();
			for (int i = 0; i < paraTexts.length; i++) {
				System.out.println("Paragraph " + (i + 1) + " : " + paraTexts[i]);
			}
			//输出当前word的一些信息
			printInfo(extractor.getSummaryInformation());
			//输出当前word的一些信息
			this.printInfo(extractor.getDocSummaryInformation());
			this.closeStream(is);
		}

	/**
	 *	兼容 word 2003 2007读取
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testReadByAll () throws Exception {
		String path = "/home/fengjiang/Documents/4224334.doc";
		InputStream in = new FileInputStream(path);
		System.out.println(path);
		String bodyText="";
		try {
//            转换成  PushbackinputStream
			if (!in.markSupported()) {
				in = new PushbackInputStream(in, 8);
			}
//            其他word版本
			if(POIFSFileSystem.hasPOIFSHeader(in))
			{
				HWPFDocument document = new HWPFDocument(in);
				WordExtractor extractor = new WordExtractor(document);
				bodyText = extractor.getText();
				System.out.println(bodyText);
				return ;
			}
//             07 版本
			XWPFDocument document = new XWPFDocument(in);
			XWPFWordExtractor extractor =new XWPFWordExtractor(document);
			bodyText = extractor.getText();
			System.out.println(bodyText);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

		/**
		 * 输出SummaryInfomation
		 * @param info
		 */

	private void printInfo(SummaryInformation info) {
		//作者
		System.out.println(info.getAuthor());
		//字符统计
		System.out.println(info.getCharCount());
		//页数
		System.out.println(info.getPageCount());
		//标题
		System.out.println(info.getTitle());
		//主题
		System.out.println(info.getSubject());
	}

	/**
	 * 输出DocumentSummaryInfomation
	 *
	 * @param info
	 */
	private void printInfo(DocumentSummaryInformation info) {
		//分类
		System.out.println(info.getCategory());
		//公司
		System.out.println(info.getCompany());
	}

	/**
	 * 关闭输入流
	 *
	 * @param is
	 */
	private void closeStream(InputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
