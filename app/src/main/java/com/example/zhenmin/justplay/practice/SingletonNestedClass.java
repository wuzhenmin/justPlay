package com.example.zhenmin.justplay.practice;

/**
 * Created by hasee on 2016/3/12.
 */
public class SingletonNestedClass {
    private static class SingleTonHolder{
        private static final SingletonNestedClass instance = new SingletonNestedClass();

    }
    private SingletonNestedClass(){}
    public static final SingletonNestedClass getInstance(){
        return SingleTonHolder.instance;
    }
}
