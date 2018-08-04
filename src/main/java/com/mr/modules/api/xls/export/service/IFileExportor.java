package com.mr.modules.api.xls.export.service;

import com.mr.modules.api.xls.export.domain.common.ExportCell;
import com.mr.modules.api.xls.export.exception.FileExportException;

import java.util.List;

/**
 * Created by stark.zhang on 2015/11/6.
 */
public interface IFileExportor {
    /**
     * 数据导出
     * @param data
     * @param exportCells
     * @return
     */
    public Object getExportResult(List<?> data, List<ExportCell> exportCells) throws FileExportException;


}
