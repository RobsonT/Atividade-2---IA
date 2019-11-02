package searches;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SteepestAscentHillClimbing {

    //implementacao do gerador de estados da busca Subida de Enconsta pelo Maior Aclive
    public static String generateStateSteepestAscent(String currentState, int nNursey) {
        String state = currentState;
        int count = 0;
        int val;
//        printa o estado visitado e sua avaliação
        System.out.println("Estado visitado");
        SearchUtils.printState(state, nNursey);
        System.out.println("Avaliação: "+SearchUtils.evaluate(state, nNursey));
        Map<String, Integer> states = new HashMap<>();
        String aux = state;
        val = SearchUtils.evaluate(state, nNursey);
        states.put(aux, val);

//        gera todos os filhos do estado passado como parametro
        while (count < state.length()) {
            state = currentState;
// Alteraa um bit do estado para zero ou para 1
            int value = Character.getNumericValue(state.charAt(count));
            value = (value + 1) % 2;
            char[] stateChar = state.toCharArray();
            stateChar[count] = Character.forDigit(value, 10);
            state = String.valueOf(stateChar);
// Avalia o novo estado gerado
            val = SearchUtils.evaluate(state, nNursey);
            states.put(state, val);
// Verifica se o estado gerado é o melhor
            aux = (states.get(state) < states.get(aux)) ? state : aux;
            if (states.get(aux) == 0) {
                return aux;
            }
            count++;
        }
//        retorna o melhor estado gerado
        return aux;
    }

    //implementacao da busca Subida de Enconsta pelo Maior Aclive
    public static Map<String, Integer> hillClimbingSteepestAscent(String initialState, int nNursey) {
        String state = initialState;
        String stateChild;

        while (true) {
//            Obtem o melhor filhor gerado a partir do estado passado
            stateChild = generateStateSteepestAscent(state, nNursey);
//            verifica se houve alguma melhoria do estado visitado para o estado gerado
            if (state.equals(stateChild)) {
//                Se não houve melhoria encerra a busca e retorna o mehor estado encontrado
                System.out.println("Solução encontrada");
                HashMap<String, Integer> best = new HashMap<>();
                best.put(state, SearchUtils.evaluate(state, nNursey));
                return best;
            }
//            Se houve melhora prepara o próximo estado a ser visitado
            state = stateChild;
        }
    }

}
