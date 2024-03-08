package com.simple.app;

import java.util.Objects;

public class Application {

    public static void main(String[] args) {
        String str = "a,b,c,,,,,c";
        String intern = str.intern();
        System.out.println(intern);
    }
}
