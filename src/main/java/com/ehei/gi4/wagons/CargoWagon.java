package com.ehei.gi4.wagons;

import com.ehei.gi4.Wagon;

public class CargoWagon implements Wagon {
    private boolean filled = false;

    public String render(){
        return filled ? "|^^^^|" : "|    |" ;
    }

    public void fill (){
        if(filled){
            throw new IllegalStateException("Wagon already filled");
        }

        filled = true;
    }

    public boolean isFilled(){
        return filled;
    }

}
