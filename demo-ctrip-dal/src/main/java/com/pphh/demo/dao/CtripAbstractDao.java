package com.pphh.demo.dao;

import com.ctrip.platform.dal.common.enums.DatabaseCategory;
import com.ctrip.platform.dal.dao.DalQueryDao;

/**
 * CtripDao
 *
 * @author huangyinhuang
 * @date 6/21/2018
 */
public abstract class CtripAbstractDao {

    public static final String DATA_BASE = "simple_orm";
    public static final DatabaseCategory dbCategory = DatabaseCategory.MySql;
    public DalQueryDao queryDao = new DalQueryDao(DATA_BASE);

}
