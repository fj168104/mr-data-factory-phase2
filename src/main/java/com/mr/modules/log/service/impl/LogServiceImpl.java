package com.mr.modules.log.service.impl;
import com.mr.common.base.service.impl.BaseServiceImpl;
import com.mr.modules.log.model.Log;
import com.mr.modules.log.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cuiP
 * Created by JK on 2017/4/27.
 */
@Transactional
@Service
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService{

}
