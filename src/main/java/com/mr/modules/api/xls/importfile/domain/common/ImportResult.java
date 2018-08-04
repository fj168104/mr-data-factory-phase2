package com.mr.modules.api.xls.importfile.domain.common;

import com.mr.modules.api.xls.domain.BaseModel;

import java.util.List;

/**
 * Created by stark.zhang on 2015/11/19.
 */
public abstract class ImportResult<E> extends BaseModel {
    public abstract List<E> getResult();
    private String resMsg;//返回的信息
    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

}
