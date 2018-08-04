package com.mr.modules.api.xls.importfile;


import com.mr.modules.api.xls.importfile.domain.common.Configuration;
import com.mr.modules.api.xls.importfile.exception.FileImportException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.InputStream;

/**
 * Created by stark.zhang on 2015/11/22.
 * 解析config接口
 */
public abstract class ConfigParser {
    abstract public Configuration getConfig(InputStream configStream) throws FileImportException;
    public static String getNodeText(Element element, String key) throws FileImportException {
        NodeList nodeList = element.getElementsByTagName(key);
        if (nodeList.getLength() == 0) {
            throw new FileImportException("Tag is empty. tag:" + key);
        }

        return nodeList.item(0).getTextContent();
    }
}
