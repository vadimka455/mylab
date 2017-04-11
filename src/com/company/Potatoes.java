package com.company;


class Potatoes {
    private int num = 1;
    private int weight = 1;

    // public String type = "White";
    Potatoes(int num, int weight) {
        this.num = num;
        this.weight = weight;
    }

    Potatoes(int num) {
        this.num = num;
        this.weight = 5;
    }

    Potatoes() {
        //
    }

    int getWeight() {
        return this.weight;
    }

    void setWeight(int weight) {
        this.weight = weight;
    }
    int getNum(){
        return this.num;
    }
    void setNum(int num){
        this.num=num;
    }

}
