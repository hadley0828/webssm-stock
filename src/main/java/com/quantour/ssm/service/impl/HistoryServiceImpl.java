package com.quantour.ssm.service.impl;

import com.quantour.ssm.service.HistoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhangzy on 2017/5/25.
 * 用来处理和历史记录相关的接口 历史记录包括个股信息和执行策略
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HistoryServiceImpl implements HistoryService{


}
