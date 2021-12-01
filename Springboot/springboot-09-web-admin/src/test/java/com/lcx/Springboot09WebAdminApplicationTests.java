package com.lcx;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.stream.IntStream;

@SpringBootTest
class Springboot09WebAdminApplicationTests {

    @Test
    void contextLoads() {
        String string = "abc";

        char [] chars = string.toCharArray();
        int [] arr = {1,2,3,3,4};
        IntStream sorted = Arrays.stream(arr).sorted();
        System.out.println(arr);
    }

}
