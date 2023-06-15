package org.example;

import java.util.Random;

public class TramStation {
    private String name;
    private int x;
    private int y;
    public  int numPeople;
    private static int osoby_tramwaj;

    public TramStation(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.numPeople = 0;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public static int getosoby(){
        return osoby_tramwaj;
    }
    public int getNumPeople() {
        return numPeople;
    }

    public void updateNumPeople(int amount) {
        numPeople = amount;
    }
    public int exchangePeople(int osoby_tramwaj) {
        this.osoby_tramwaj = osoby_tramwaj;
        int wysiadajacy;
        int numPeople = getNumPeople();
        if(osoby_tramwaj<=0){
            wysiadajacy=0;
        }
        else{
            Random random = new Random();
            wysiadajacy = random.nextInt(osoby_tramwaj);
        }

        Random random2 =new Random();

        int wsiadajacy = random2.nextInt(numPeople);
        osoby_tramwaj=osoby_tramwaj-wysiadajacy+wsiadajacy;
        numPeople=numPeople+wysiadajacy-wsiadajacy;
        updateNumPeople(numPeople);
        return osoby_tramwaj;
    }

}