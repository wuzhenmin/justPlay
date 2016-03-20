// ICalculate.aidl
package com.example.zhenmin.justplay.AIDL;

// Declare any non-default types here with import statements

interface ICalculate {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);


    int add(int first,int second);
}
