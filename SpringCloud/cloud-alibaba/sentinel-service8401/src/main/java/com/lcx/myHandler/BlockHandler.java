package com.lcx.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class BlockHandler {


    public static String handlerException(BlockException e){
        return "自定义handler exception " + e.getMessage();
    }
}
