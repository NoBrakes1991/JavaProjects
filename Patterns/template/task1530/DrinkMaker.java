package com.javarush.task.task15.task1530;

public abstract class DrinkMaker {

    abstract void getRightCup();
    abstract void putIngredient();
    abstract void pour();
    void order (){
        System.out.println("минуточку");
    }
    void makeDrink(){
        order();
        getRightCup();
        putIngredient();
        pour();


    }
}
