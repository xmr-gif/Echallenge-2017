package com.ehei.gi4;

import com.ehei.gi4.wagons.CargoWagon;
import com.ehei.gi4.wagons.HeadWagon;
import com.ehei.gi4.wagons.PassengerWagon;
import com.ehei.gi4.wagons.RestaurantWagon;

public class WagonFactory {
    public static Wagon createWagon(char type, boolean first) {
        return (Wagon) switch (type) {
            case 'H'-> new HeadWagon(first) ;
            case 'P'-> new PassengerWagon();
            case 'R'-> new RestaurantWagon();
            case 'C'-> new CargoWagon();
            default-> throw new IllegalArgumentException("Invalid wagon type");
        };
    }
}
