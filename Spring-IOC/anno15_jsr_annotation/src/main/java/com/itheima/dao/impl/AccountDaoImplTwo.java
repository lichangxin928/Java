package com.itheima.dao.impl;

import com.itheima.dao.AccountDao;
import org.springframework.stereotype.Repository;

/**
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
@Repository
public class AccountDaoImplTwo implements AccountDao {
    @Override
    public void saveAccount() {
        System.out.println("AccountDaoImplTwo 保存了账户");
    }
}
