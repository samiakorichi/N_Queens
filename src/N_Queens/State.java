package N_Queens;

import java.util.ArrayList;
import java.util.Random;



public class State {
	
	//la classe d'état contient une représentation d'un état de la carte
	//la représentation consiste en un vecteur d'entiers V
	//où V[i] = position de la reine dans la colonne de la ligne i
 
    private static ArrayList<Integer> values;
    public State(ArrayList<Integer> nvalues){
        values = nvalues;
    }
    public static ArrayList<Integer> getValues() {
        return values;
    }


}

