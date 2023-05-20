package N_Queens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class State {

    private final ArrayList<Integer> values;

    public State(int n) {
        this.values = new ArrayList<>(n);
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int val = random(n);
            while (set.contains(val)) {
                val = random(n);
            }
            set.add(val);
            values.add(val);
        }
    }

    public static int random(int x) {
        Random random = new Random();
        return random.nextInt(x);
    }

    public State(ArrayList<Integer> values) {
    	 this.values = new ArrayList<>(values);
    }

   
    
    public ArrayList<Integer> getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "State{" +
                "values=" + values +
                '}';
    }
    public String toString(int a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            for (int j = 0; j < values.size(); j++) {
                if (values.get(i) == j) {
                    sb.append("Q ");
                } else {
                    sb.append(". ");
                }
            }
           
            sb.append("\n");
        }
        System.out.println("the state array" +values.toString());
        return sb.toString();
    }
}