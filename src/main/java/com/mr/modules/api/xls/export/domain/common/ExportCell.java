package com.mr.modules.api.xls.export.domain.common;


import com.mr.modules.api.xls.domain.BaseModel;

/**
 * Created by stark.zhang on 2015/11/2.
 */
public class ExportCell extends BaseModel {
    private String title;//导出的标题中文
    private String alias;//对应的别名，映射的字段名

    public ExportCell(){}

    public ExportCell(String title){
        this.title = title;
        this.alias = title;
    }

    public ExportCell(String title, String alias){
        this.title = title;
        this.alias = alias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

}
