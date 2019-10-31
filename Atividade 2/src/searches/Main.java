package searches;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //inicializacao dos parametros utilizados na solucao
        Scanner scanner = new Scanner(System.in);
        String initialState;
        int choose, nNursey;
        Problem problem;


        //menu para receber a escolha do usuario
        do {
            System.out.println("Digite a sua escolha:");
            System.out.println("1 - Subida de encosta");
            System.out.println("2 - Subida de encostapelo maior aclive");
            System.out.println("3 - Busca pelo melhor primeiro");
            choose = scanner.nextInt();
        } while (choose < 1 && choose > 3);

        System.out.println("Insira o número de enfermeiros:");
        nNursey = scanner.nextInt();

        //recebendo o estado inicial do problema
        do {
            System.out.println("Qual o estado inicial");
            initialState = scanner.next();
        } while (initialState.length() != 21 * nNursey);


        //inicializando o problema com o estado inicial recebido pelo cliente
        problem = new Problem(initialState, nNursey);


        //resolucao do problema de acordo com a escolha de busca escolhido pelo cliente
        problem.solve(choose, nNursey);
    }
}