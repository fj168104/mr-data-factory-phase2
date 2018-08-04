package com.mr.modules.api.xls.export.domain.excel;

import com.mr.modules.api.xls.export.domain.common.ExportResult;
import com.mr.modules.api.xls.export.domain.common.ExportType;
import com.mr.modules.api.xls.export.exception.FileExportException;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by stark.zhang on 2015/11/6.
 */
public class ExportCSVResult extends ExportResult {
    private String result;

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public Object getResult() {
        return result;
    }

    public void export(OutputStream outputStream) throws FileExportException {
        try {
            outputStream.write(result.getBytes("UTF-8"));
            outputStream.close();
        } catch (IOException e) {
            throw new FileExportException("Error occurred while exportCSV msg is " + e);
        }
    }

    @Override
    public ExportType getExportType() {
        return ExportType.CSV;
    }


}
