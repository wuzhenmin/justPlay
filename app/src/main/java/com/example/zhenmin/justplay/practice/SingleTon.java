package com.example.zhenmin.justplay.practice;

/**
 * Created by hasee on 2016/3/12.
 */
public class SingleTon {
    private static final SingleTon instance = new SingleTon();
    private SingleTon(){}
    public static SingleTon getInstance(){
        return instance;
    }
}
