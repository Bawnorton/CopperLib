package com.bawnorton.copperlib;


import com.bawnorton.copperlib.api.CopperApi;
import com.bawnorton.copperlib.copper.CopperPerson;

public class Main {
    public static void main(String[] args) {
        CopperApi api = new CopperApi(, );

        CopperPerson person = api.getPerson(112407455);
        System.out.println(person);
    }
}