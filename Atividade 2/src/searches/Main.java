package searches;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //inicializacao dos parametros utilizados na solucao
        Scanner scanner = new Scanner(System.in);
        String initialState = "";
        int choose, nNursey, chooseState;
        Problem problem;


        //menu para receber a escolha do usuario
        do {
            System.out.println("Digite a sua escolha:");
            System.out.println("1 - Subida de encosta");
            System.out.println("2 - Subida de encostapelo maior aclive");
            System.out.println("3 - Busca pelo melhor primeiro");
            System.out.println("4 - Subida de encostapelo maior aclive n vezes");
            choose = scanner.nextInt();
        } while (choose < 1 && choose > 4);

        System.out.println("Insira o número de enfermeiros:");
        nNursey = scanner.nextInt();

        if (choose != 4) {

            //recebendo o estado inicial do problema
            do {
                System.out.println("Tipo de entrada:");
                System.out.println("1 - Inserir manualmente");
                System.out.println("2 - Gerar automicamente");
                chooseState = scanner.nextInt();
            } while (chooseState != 1 && chooseState != 2);
            if (chooseState == 1) {
                System.out.println("Inserir o estado inicial");
                do {
                    initialState = scanner.next();
                } while (initialState.length() != 21 * nNursey);
            } else {
                while (initialState.length() < 21 * nNursey) {
                    double x = Math.random();
                    if (x < 0.5)
                        initialState += "0";
                    else
                        initialState += "1";
                }
            }
            //inicializando o problema com o estado inicial recebido pelo cliente
            problem = new Problem(initialState, nNursey);


            //resolucao do problema de acordo com a escolha de busca escolhido pelo cliente
            problem.solve(choose, nNursey);
        } else {
            int n;
            System.out.println("Insira a quantidade de veze que a busca será executada:");
            n = scanner.nextInt();
            Map<String, Integer> states = new HashMap<>();
            String best = "";
            for (int i = 0; i < n; i++) {
                initialState = "";
                while (initialState.length() < 21 * nNursey) {
                    double x = Math.random();
                    if (x < 0.5)
                        initialState += "0";
                    else
                        initialState += "1";
                }
                problem = new Problem(initialState, nNursey);
                System.out.println("Execução " + i + ":");
                states.putAll(problem.solve(2, nNursey));
            }

            System.out.println("Melhor estado gerado");
            for (Map.Entry<String, Integer> pair : states.entrySet()) {
                if (best.equals("") || states.get(best) > pair.getValue())
                    best = pair.getKey();
            }

            SearchUtils.printState(best, nNursey);
            System.out.println("Avaliação: " + states.get(best));

        }
    }
}

//21  22    23