package com.ehei.gi4;

import java.util.ArrayList;
import java.util.List;
import com.ehei.gi4.wagons.CargoWagon;
import com.ehei.gi4.wagons.HeadWagon;

public class Train {
    private final List<Wagon> wagons = new ArrayList<>();

    public Train(String config){
        for(int i = 0 ; i < config.length() ; i++){
            char type = config.charAt(i) ;

            boolean first = false;

            if(i == 0){
                first = true ;
            }

            wagons.add(WagonFactory.createWagon(type, first));

        }
    }

    public void print(){
        for(int i = 0 ; i < wagons.size() ; i++){
            if(i != wagons.size() - 1){
                System.out.print(wagons.get(i).render() + "::");
            }else{
                System.out.print(wagons.get(i).render());
            }

        }
    }

    public void fill(){
        for(Wagon wagon : wagons){
            if( wagon instanceof CargoWagon cargo && !cargo.isFilled()){
                cargo.fill() ;
                return ;
            }
        }

        throw new IllegalStateException(
                "No empty wagon available in the train"
        );
    }

    public void detachEnd(){
        if(wagons.get(wagons.size() - 1) instanceof HeadWagon head){
            wagons.removeLast();
            return;
        }

        throw new IllegalStateException(
          "there is no head at the end of the train"
        );
    }

    public void detachHead(){
        if(wagons.get(0) instanceof HeadWagon head){
            wagons.removeFirst();
            return;
        }

        throw new IllegalStateException(
                "there is no head at the start of the train"
        );
    }


}
