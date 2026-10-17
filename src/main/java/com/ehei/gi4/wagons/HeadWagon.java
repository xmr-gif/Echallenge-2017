package com.ehei.gi4.wagons;

import com.ehei.gi4.Wagon;

public class HeadWagon implements Wagon {
    private final boolean first;

    public HeadWagon(boolean first) {
        this.first = first;
    }

    public String render() {
        return first ? "<HHHH" : "HHHH>" ;
    }
}
