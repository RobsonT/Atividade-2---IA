package searches;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class HillClimbing {

    //implementacao do gerador de estados da Busca Subida de Enconsta
    public static String generateStateHillClimbing(String currentState, int nNursey) {
        String state = currentState;
        int count = 0;
        int val;
        System.out.println("Estado visitado");
        SearchUtils.printState(state, nNursey);
        System.out.println("Avaliação: " + SearchUtils.evaluate(state, nNursey));
        //map que ira conter os estados e seu valor respectivamente
        //o valor representa o quanto o estado sastifaz os requisitos),
        Map<String, Integer> states = new HashMap<>();
        String aux = state;
        val = SearchUtils.evaluate(state, nNursey);
        states.put(aux, val);

        while (count < state.length()) {
            state = currentState;

            int value = Character.getNumericValue(state.charAt(count));
            value = (value + 1) % 2;
            char[] stateChar = state.toCharArray();
            stateChar[count] = Character.forDigit(value, 10);
            state = String.valueOf(stateChar);

            val = SearchUtils.evaluate(state, nNursey);
            states.put(state, val);

            if (states.get(state) < states.get(aux)) {
                aux = state;
                return aux;
            }
            count++;
        }
        System.out.println("Avaliação: " + states.get(aux));
        return aux;
    }

    //implementacao da busca Subida de Enconsta
    public static void hillClimbing(String initialState,int nNursey) {
        String state = initialState;
        String stateChild;

        while (true) {
            stateChild = generateStateHillClimbing(state, nNursey);
            if (state.equals(stateChild)) {
                System.out.println("Solução encontrada");
                return;
            }
            state = stateChild;
        }
    }

}
