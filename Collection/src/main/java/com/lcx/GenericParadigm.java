package com.lcx;

import java.util.ArrayList;
import java.util.List;

public class GenericParadigm {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<Float> list2 = new ArrayList<>();
        System.out.println(list1.getClass() == list2.getClass());
        System.out.println(list1.getClass());
    }
}
