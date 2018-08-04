package com.mr.modules.api.xls.importfile;

import com.mr.modules.api.xls.importfile.domain.common.ImportResult;
import com.mr.modules.api.xls.importfile.exception.FileImportException;

import java.io.File;

/**
 * Created by stark.zhang on 2015/11/19.
 */
public abstract class FileImportor {

    public abstract ImportResult getImportResult(File file, String fileName) throws FileImportException;

}
