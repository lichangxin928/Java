package com.itheima.dao.impl;

import com.itheima.dao.AccountDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
@Repository

public class AccountDaoImplOne implements AccountDao {
    @Override
    public void saveAccount() {
        System.out.println("AccountDaoImplOne 保存了账户");
    }
}
