package com.mr.modules.api.xls.importfile;

import com.mr.modules.api.xls.importfile.domain.common.Configuration;
import com.mr.modules.api.xls.importfile.exception.FileImportException;
import com.mr.modules.api.xls.importfile.impl.XmlConfigParser;

/**
 * Created by stark.zhang on 2015/11/28.
 */
public class ConfigurationParserFactory {
    public static ConfigParser getConfigParser(Configuration.ParserType parserType) throws FileImportException {
        if (parserType == null) {
            throw new FileImportException("parserType is null");
        }
        if (parserType == Configuration.ParserType.XML) {
            return new XmlConfigParser();
        }
        return new XmlConfigParser();
    }


}
