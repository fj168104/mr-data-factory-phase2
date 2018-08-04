package com.mr.modules.api.xls.export.domain.excel;

import com.mr.modules.api.xls.export.domain.common.ExportResult;
import com.mr.modules.api.xls.export.domain.common.ExportType;
import com.mr.modules.api.xls.export.exception.FileExportException;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by stark.zhang on 2015/11/6.
 */
public class ExportExcelResult extends ExportResult {
    private Workbook workbook;

    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    @Override
    public Object getResult() {
        return workbook;
    }

    public void export(OutputStream outputStream) throws FileExportException {
        try {
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            throw new FileExportException("Error occurred while export excel msg is " + e);
        }
    }

    @Override
    public ExportType getExportType() {
        return ExportType.EXCEL2007;
    }
}
