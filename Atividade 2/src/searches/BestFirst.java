package searches;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BestFirst {
    //inicializacao da fila para receber os estados gerados e do conjunto para cuidar de redundancia
    private static Queue<String> generated;
    private static Set<String> redundance;

    //implementacao do gerador de estados da busca Melhor Primeiro
    public static void generateStateBestFirst(String currentState, int nNursey) {
        String state = currentState;
        int count = 0;
        int val;
        String aux = state;
        val = SearchUtils.evaluate(state, nNursey);

        while (count < state.length()) {
            state = currentState;

            int value = Character.getNumericValue(state.charAt(count));
            value = (value + 1) % 2;
            char[] stateChar = state.toCharArray();
            stateChar[count] = Character.forDigit(value, 10);
            state = String.valueOf(stateChar);

            if (redundance.add(state))
                generated.add(state);

            count++;
        }
    }

    //implementacao da busca Melhor Primeiro
    public static void bestFirst(String initialState, int nNursey) {
        String state = initialState;
        String bestState = state;;
        generated = new LinkedList<>();
        redundance = new HashSet<>();
        generated.add(initialState);
        while (!generated.isEmpty()) {
            if(state.equals(initialState))
                generated.remove();

            System.out.println("Estado visitado:");
            SearchUtils.printState(state, nNursey);
            System.out.println("Avaliação: " + SearchUtils.evaluate(state, nNursey));

            generateStateBestFirst(state, nNursey);

            String[] generateds = new String[generated.size()];
            int count = 0;
            while (!generated.isEmpty()) {
                generateds[count] = generated.remove();
                count++;
            }

            SearchUtils.quickSort(generateds, 0, count - 1, nNursey);

            for (String gen : generateds) {
                generated.add(gen);
            }

            state = generated.remove();

            if (SearchUtils.evaluate(state, nNursey) < SearchUtils.evaluate(bestState, nNursey)) {
                bestState = state;
            }else{
                System.out.println("Solução encontrada!");
                return;
            }
        }
        System.out.println("Resultado não encontrado");
    }
}
