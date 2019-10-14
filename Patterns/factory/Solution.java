package com.javarush.task.task14.task1408;

/* 
Куриная фабрика
*/

public class Solution {
    public static void main(String[] args) {
        Hen hen = HenFactory.getHen(Country.BELARUS);
        hen.getCountOfEggsPerMonth();
    }

    static class HenFactory {

        static Hen getHen(String country) {
            Hen hen = null;
            //напишите тут ваш код
            if ("Belarus".equals(country)){
            hen = new BelarusianHen();}
            if ("Moldova".equals(country)){
            hen = new MoldovanHen();}
            if ("Russia".equals(country)){
            hen = new RussianHen();}
            if ("Ukraine".equals(country)){
            hen = new UkrainianHen();}
            return hen;

        }
    }


}
