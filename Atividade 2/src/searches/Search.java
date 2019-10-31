package searches;

import java.util.*;

public class Search {
    private static Queue<String> generated;
    private static Set<String> redundance;

    private static void quickSort(String[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int posicaoPivo = separar(vetor, inicio, fim);
            quickSort(vetor, inicio, posicaoPivo - 1);
            quickSort(vetor, posicaoPivo + 1, fim);
        }
    }

    private static int separar(String[] vetor, int inicio, int fim) {
        String pivo = vetor[inicio];
        int i = inicio + 1, f = fim;
        while (i <= f) {
            if (evaluate(vetor[i]) <= evaluate(pivo))
                i++;
            else if (evaluate(pivo) < evaluate(vetor[f]))
                f--;
            else {
                String troca = vetor[i];
                vetor[i] = vetor[f];
                vetor[f] = troca;
                i++;
                f--;
            }
        }
        vetor[inicio] = vetor[f];
        vetor[f] = pivo;
        return f;
    }

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
        for (int i = 0; i < 21; i++) {
            countNurse = 0;
            for (int j = i; j < 210; j += 21) {
                if (state.charAt(j) == '1') {
                    countNurse++;
                }
            }
            if (countNurse > 3 || countNurse < 1) {
                countViolations++;
            }
        }
        return countViolations;
    }

    public static int r2Violation(String state) {
        int countViolations = 0;
        int countNurse;
        for (int i = 0; i < 210; i += 21) {
            countNurse = 0;
            for (int j = i; j < i + 21; j++) {
                if (state.charAt(j) == '1') {
                    countNurse++;
                }
            }
            if (countNurse > 5) {
                countViolations++;
            }
        }
        return countViolations;
    }

    public static int r3Violation(String state) {
        int countViolations = 0;
        int count = 0;

        for (int i = 0; i < 210; i += 21) {
            for (int j = i; j < i + 21; j++) {
                if (state.charAt(j) == '1') {
                    count++;
                } else {
                    countViolations += (count / 4);
                    count = 0;
                }
            }
        }
        return countViolations;
    }

    public static int r4Violation(String state) {
        int countViolations = 0;
        int count;
        int countH;

        for (int i = 0; i < 210; i += 21) {
            countH = 0;
            for (int j = 0; j < 3; j++) {
                count = 0;
                for (int k = i + j; k < 21; k += 3) {
                    if (state.charAt(j) == '1') {
                        count++;
                    }
                }
                if (count < 7) {
                    countH++;
                }
            }
            if (countH == 3) {
                countViolations++;
            }
        }
        return countViolations;
    }

    public static int evaluate(String state) {
        return r1Violation(state) + r2Violation(state) + r3Violation(state) + r4Violation(state);
    }

    public static String generateStateHillClimbing(String currentState) {
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
            stateChar[count] = Character.forDigit(value, 10);
            state = String.valueOf(stateChar);

            val = evaluate(state);
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

    public static String generateStateSteepestAscent(String currentState) {
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
            stateChar[count] = Character.forDigit(value, 10);
            state = String.valueOf(stateChar);

            val = evaluate(state);
            states.put(state, val);

            aux = (states.get(state) < states.get(aux)) ? state : aux;
            if (states.get(aux) == 0) {
                return aux;
            }
            count++;
        }

        System.out.println("Avaliação: " + states.get(aux));
        return aux;
    }

    public static void generateStateBestFirst(String currentState) {
        String state = currentState;
        int count = 0;
        int val;
        String aux = state;
        val = evaluate(state);

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

    public static void hillClimbing(String initialState) {
        String state = initialState;
        generated = new LinkedList<>();
        String stateChild;

        while (true) {
            stateChild = generateStateHillClimbing(state);
            if (state.equals(stateChild)) {
                System.out.println("Solução encontrada");
                return;
            }
            state = stateChild;
        }
    }

    public static void hillClimbingSteepestAscent(String initialState) {
        String state = initialState;
        generated = new LinkedList<>();
        String stateChild;

        while (true) {
            stateChild = generateStateSteepestAscent(state);
            if (state.equals(stateChild)) {
                System.out.println("Solução encontrada");
                return;
            }
            state = stateChild;
        }
    }

    public static void bestFirst(String initialState) {
        String state;
        generated = new LinkedList<>();
        redundance = new HashSet<>();
        generated.add(initialState);
        while (!generated.isEmpty()) {
            state = generated.remove();
            System.out.println("Gerados: " + generated.size());
            System.out.println(evaluate(state));
            System.out.println(state);
            if (evaluate(state) == 0) {
                printState(state);
                System.out.println("Solução encontrada");
                return;
            }

            generateStateBestFirst(state);

            String[] generateds = new String[generated.size()];
            int count = 0;
            while (!generated.isEmpty()) {
                generateds[count] = generated.remove();
                count++;
            }

            quickSort(generateds, 0, count - 1);

            for (String gen : generateds) {
                generated.add(gen);
            }


        }
        System.out.println("Resultado não encontrado");
    }

}
