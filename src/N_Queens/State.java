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


    public static State generateRandomState(int n) {
        ArrayList<Integer> values = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            values.add(random.nextInt(n)); // generate a random row position for the queen in column i
        }

        return new State(values);
    }
}

