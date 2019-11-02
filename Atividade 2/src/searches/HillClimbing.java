package searches;

import java.util.HashMap;
import java.util.Map;

public class HillClimbing {

    //implementacao do gerador de estados da Busca Subida de Enconsta
    public static String generateStateHillClimbing(String currentState, int nNursey) {
        String state = currentState;
        int count = 0;
        int val;
//        Printa o estado visitado
        System.out.println("Estado visitado");
        SearchUtils.printState(state, nNursey);
        System.out.println("Avaliação: " + SearchUtils.evaluate(state, nNursey));
        //map que ira conter os estados e seu valor respectivamente
        //o valor representa o quanto o estado sastifaz os requisitos),
        Map<String, Integer> states = new HashMap<>();
        String aux = state;
        val = SearchUtils.evaluate(state, nNursey);
        states.put(aux, val);
//      Gera os filhos do estado
        while (count < state.length()) {
            state = currentState;
//      altera um bit do estado
            int value = Character.getNumericValue(state.charAt(count));
            value = (value + 1) % 2;
            char[] stateChar = state.toCharArray();
            stateChar[count] = Character.forDigit(value, 10);
            state = String.valueOf(stateChar);
//          Avalia o novo estado obtido
            val = SearchUtils.evaluate(state, nNursey);
            states.put(state, val);
//          Verifica se o estado obtido é melhor do que o estado visitado
            if (states.get(state) < states.get(aux)) {
//                Se sim retorna o estado obtido
                aux = state;
                return aux;
            }
            count++;
        }
//        Se nenhum filho é melhor o próprio estado visitado é retornado
        return aux;
    }

    //implementacao da busca Subida de Enconsta
    public static void hillClimbing(String initialState, int nNursey) {
        String state = initialState;
        String stateChild;

        while (true) {
            // Obtem o primeiro filho com melhor avaliação do que o estado visitado
            stateChild = generateStateHillClimbing(state, nNursey);
//            Verifica se foi possivel obter um estado melhor do que o visitado
            if (state.equals(stateChild)) {
//                Se não foi possível a busca é encerrada
                System.out.println("Solução encontrada");
                return;
            }
//            Se foi possível é atualizado o próximo estado a ser visitado
            state = stateChild;
        }
    }

}
