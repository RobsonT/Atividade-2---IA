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
//        Calcula a avaliação do estado visitado
        String aux = state;
        val = SearchUtils.evaluate(state, nNursey);

//        Gera todos os filhos do estado
        while (count < state.length()) {
            state = currentState;
//  para gerar um filho um bit é alterado de 1 para 0 ou de 0 para 1
            int value = Character.getNumericValue(state.charAt(count));
            value = (value + 1) % 2;
            char[] stateChar = state.toCharArray();
            stateChar[count] = Character.forDigit(value, 10);
            state = String.valueOf(stateChar);
//  é feito o controle de redundancia
            if (redundance.add(state))
//                Se o estado ainda não foi gerado é colocado da fila
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
//        É feito a visita aos estados presente da fila
        while (!generated.isEmpty()) {
            if(state.equals(initialState))
                generated.remove();
//      Printa o estado que está sendo visitado
            System.out.println("Estado visitado:");
            SearchUtils.printState(state, nNursey);
            System.out.println("Avaliação: " + SearchUtils.evaluate(state, nNursey));
//      Gera os filhos do estado visitado
            generateStateBestFirst(state, nNursey);
//      Ordenação da fila
//      Converte a fila para um vetor
            String[] generateds = new String[generated.size()];
            int count = 0;
            while (!generated.isEmpty()) {
                generateds[count] = generated.remove();
                count++;
            }
//          aplica o quick sort no vetor
            SearchUtils.quickSort(generateds, 0, count - 1, nNursey);
//          Transforma o vetor novamente em uma fila
            for (String gen : generateds) {
                generated.add(gen);
            }
//          obtem o proximo estado a ser visitado
            state = generated.remove();
//          verifica se o proximo estado é melhor avaliado que o atual
            if (SearchUtils.evaluate(state, nNursey) < SearchUtils.evaluate(bestState, nNursey)) {
//                Se sim o melhor estado é atualizado
                bestState = state;
            }else{
//                senão a busca é encerrada
                System.out.println("Solução encontrada!");
                return;
            }
        }
        System.out.println("Resultado não encontrado");
    }
}
