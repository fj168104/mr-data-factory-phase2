package com.mr.modules.api.xls.export;

import com.mr.modules.api.xls.export.domain.common.ExportConfig;
import com.mr.modules.api.xls.export.domain.common.ExportResult;
import com.mr.modules.api.xls.export.domain.common.ExportType;
import com.mr.modules.api.xls.export.domain.excel.ExportCSVResult;
import com.mr.modules.api.xls.export.domain.excel.ExportExcelResult;
import com.mr.modules.api.xls.export.exception.FileExportException;
import com.mr.modules.api.xls.export.impl.CSVExportor;
import com.mr.modules.api.xls.export.impl.ExcelExportor;
import org.apache.poi.ss.usermodel.Workbook;
import java.util.List;

/**
 * Created by stark.zhang on 2015/11/7.
 */
public class FileExportor {
    public final static String EXPORT_XML_BASE_PATH = "/properties/framework/export/";

    /**
     * 通过list<T> T可为bean或者map<String, Object>  导出文件
     *
     * @param exportConfig
     * @param data
     * @return
     */
    public static ExportResult getExportResult(ExportConfig exportConfig, List<?> data) throws FileExportException {
        ExportType exportType = exportConfig.getExportType();
        switch (exportType) {
            case EXCEL2007:
                Workbook workbook = new ExcelExportor().getExportResult(data, exportConfig.getExportCells());
                ExportExcelResult exportExcelResult = new ExportExcelResult();
                exportExcelResult.setWorkbook(workbook);
                exportExcelResult.setFileName(exportConfig.getFileName());
                return exportExcelResult;
            case CSV:
                StringBuilder stringBuilder = new CSVExportor().getExportResult(data, exportConfig.getExportCells());
                ExportCSVResult exportCSVResult = new ExportCSVResult();
                exportCSVResult.setResult(stringBuilder.toString());
                exportCSVResult.setFileName(exportConfig.getFileName());
                return exportCSVResult;
        }
        throw new FileExportException("找不到对应的export type, export type is " + exportType.getNumber());
    }



}
