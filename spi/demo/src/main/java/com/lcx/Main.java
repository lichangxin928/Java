package com.lcx;

import com.lcx.spi.SpiInterface;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        ServiceLoader<SpiInterface> load = ServiceLoader.load(SpiInterface.class);
        Iterator<SpiInterface> iterator = load.iterator();
        while (iterator.hasNext()){
            SpiInterface next = iterator.next();
            next.baseUrl();
        }

    }
}
