package com.example.demo.utils;

import java.util.Random;

public class suijishu {


    public int getRandom() {
        Random rd = new Random();
        int r = rd.nextInt(1000) + 1000;
        System.out.print(r);
        return r;
    }

}
