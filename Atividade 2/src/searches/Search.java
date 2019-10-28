package searches;

import java.util.*;

public class Search {
    private static Queue<int[]> generated;

    public static void printState(String state) {
        for (int i = 0; i < 10; i++) {
            for (int j = i * 21; j < (i * 21) + 21; j++) {
                System.out.print((state.charAt(j) == '1') ? "|1" : "|0");
            }
            System.out.println("|");
        }
    }

    public static int r1Violation(String state) {
        int countViolations = 0;
        int countNurse;
        for (int i = 0; i < 21; i++){
            countNurse = 0;
            for (int j = i; j < 210; j += 21) {
                if(state.charAt(j) == '1'){
                    countNurse++;
                }
            }
            if(countNurse > 3 && countNurse < 1){
                countViolations++;
            }
        }
        return countViolations;
    }

    public static int r2Violation(String state) {
        int countViolations = 0;
        int countNurse;
        for (int i = 0; i < 210; i+=21){
            countNurse = 0;
            for (int j = i; j < i+21; j ++) {
                if(state.charAt(j) == '1'){
                    countNurse++;
                }
            }
            if(countNurse > 5){
                countViolations++;
            }
        }
        return countViolations;
    }

    public static int r3Violation(String state) {
        int countViolations = 0;
        int count = 0;

        for (int i = 0; i < 210; i+=21){
            for (int j = i; j < i+21; j ++) {
                if(state.charAt(j) == '1'){
                    count++;
                }else{
                    countViolations += (count / 4);
                    count = 0;
                }
            }
        }
        return countViolations;
    }
//101100010111010
//101101 100 010 111 010
//11 00 11
    public static int r4Violation(String state) {
        int countViolations = 0;
        int count;
        int countH;

        for (int i = 0; i < 210; i+=21){
            countH = 0;
            for(int j = 0; j < 3; j++){
                count = 0;
                for (int k = i+j; k < 21; k+=3){
                    if(state.charAt(j) == '1'){
                        count++;
                    }
                }
                if (count < 7){
                    countH++;
                }
            }
            if (countH == 3){
                countViolations++;
            }
        }
        return countViolations;
    }

    public static int evaluate(String state){
        return  r1Violation(state) + r2Violation(state) + r3Violation(state) + r4Violation(state);
    }

    public static String generateState(String currentState) {
        String state = currentState;
        int count = 0;
        int val;
        System.out.println("Estado visitado");
        printState(state);
        Map<String, Integer> states = new HashMap<>();
        String aux = state;
        val = evaluate(state);
        states.put(aux, val);

        while (count < state.length()) {
            state = currentState;

            int value = Character.getNumericValue(state.charAt(count));
            value = (value + 1) % 2;
            char[] stateChar = state.toCharArray();
            stateChar[count] = Character.toChars(value)[0];
            state = String.valueOf(stateChar);

            val = r1Violation(state) + r2Violation(state) + r3Violation(state) + r4Violation(state);
            states.put(state, val);

            aux = (states.get(state) < states.get(aux))? state : aux;
            count++;
        }

        return aux;
    }

    public static void hillClimbing(String initialState) {
        String state = initialState;
        generated = new LinkedList<>();
        String stateChild;

        while (true) {
            stateChild = generateState(state);
            if(state.equals(stateChild)){
                System.out.println("Solução encontrada");
                return;
            }
            state = stateChild;
        }
    }

    public static void hillClimbingHighestScore(String initialState) {

    }

    public static void bestFirst(String initialState) {

    }

}
