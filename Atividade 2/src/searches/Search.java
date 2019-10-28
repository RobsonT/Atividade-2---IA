package searches;

import java.util.*;

public class Search {
    private static Queue<int[]> generated;

    public static void printState(int[] state) {

    }

    public static boolean isGoal(int[] state) {

        return true;
    }

    public static String toString(int[] state) {
        String result = "";
        for (int i = 0; i < state.length; i++) {
            result += state[i];
        }
        return result;
    }

    public static boolean generateState(int[] currentState, int type) {

        int[] state = currentState.clone();

        int col = 0;
        int inserted = 0;
        int count = 0;

        for (int i = 0; i < state.length; i++) {
            if (state[i] == 0) {
                col = i;;
                break;
            }
        }

        System.out.println("Estado atual");
        printState(state);

        System.out.println("\nEstados gerados a partir do atual");

        Stack<int[]> aux = new Stack<>();

        while (count < state.length) {

            do {
                state[col]++;
                count++;
            } while ((state[col] <= state.length) && !issafe(state, col));

            if (state[col] <= state.length && redundantState.add(toString(state))) {
                //printa o estado caso ele seja valido
                printState(state);
                System.out.println();
                if (type == 1) {        //acontece caso seja DFS
                    aux.push(state.clone());
                } else {                //acontece caso seja BFS
                    generated.addLast(state.clone());
                    bfs.add(state.clone(), bfsManager, currentState.clone());
                }
                //inclementa o total de estados criados a partir do estado visitado
                inserted++;
            }
        }

        //verifica se pelo menos 1 estado foi criado a partir do estado visitado
        if (inserted > 0) {
            //em caso de BFS os estados gerados sao removidos da pilha de estado auxiliar
            //e inseridos na lista de estados a serem visitados
            if (type == 1) {
                while (!aux.empty()) {
                    generated.addFirst(aux.pop());
                }
            }
            //em ambas as buscas (BFS ou DFS), caso seja gerados algum estado é retornado true
            return true;
        }
        //em ambas as buscas (BFS ou DFS), caso não seja gerados algum estado é retornado false
        System.out.println("Nenhum estado gerado!!");
        return false;
    }

    public static void hillClimbing(String initialState) {
        String state = initialState;
        generated = new LinkedList<>();

        while (true) {

            if (isGoal(state)) {
                System.out.println("Solução encontrada!!");
                break;
            } else {    //caso contrario ocorre a criacao de novos estados
                if (!generateState(state, 1)) {
                    //se nao foi possivel gerar novos estados o no visitado é removido do caminho pacial
                    try {
                        solution.removeLast();
                    } catch (Exception e) {
                        System.out.println("Falha do sistema");
                        return;
                    }
                }
            }
            //caso todos os estados sejam testados e nenhum seja estado objetivo
            if (generated.isEmpty()) {
                System.out.println("Erro: não possui solução!!\n");
                break;
            }
            //o state é atualizado com o proximo nó a ser visitado
            state = generated.removeFirst();
            //o soluction é atualizado com o novo caminho parcial
            while (countQueen(state) <= countQueen(solution.getLast()))
                solution.removeLast();
            solution.addLast(state.clone());
        }
    }

    public static void hillClimbingHighestScore(String initialState) {

    }

    public static void bestFirst(String initialState) {

    }

}
