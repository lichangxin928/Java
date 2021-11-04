package com.lcx.listener;

import com.lcx.event.MyApplicationEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class MyTransactionEventListener {

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void doSome(MyApplicationEvent event){
        System.out.println("事务提交了");
    }
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void otherSome(MyApplicationEvent event){
        System.out.println("do other things");
    }
}
