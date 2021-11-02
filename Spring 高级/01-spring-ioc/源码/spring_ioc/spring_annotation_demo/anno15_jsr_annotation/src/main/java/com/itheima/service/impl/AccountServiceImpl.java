package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Named;

/**
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    //@Resource(type = AccountService.class,name = "accountDaoImplTwo")
//    @Inject
    @Named("accountDaoImplTwo")

    @Autowired
//    @Qualifier("accountDaoImplOne")
    private AccountDao accountDao;

    @Override
    public void saveAccount() {
        accountDao.saveAccount();
    }
}
