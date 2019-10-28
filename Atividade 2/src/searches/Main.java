package searches;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String initialState;

        int choose;

        Problem problem;

        do {
            System.out.println("Digite a sua escolha:");
            System.out.println("1 - Subida de encosta");
            System.out.println("2 - Subida de encostapelo maior aclive");
            System.out.println("3 - Busca pelo melhor primeiro");
            choose = scanner.nextInt();
        } while (choose < 1 && choose > 3);

        do {
            System.out.println("Qual o estado inicial");
            initialState = scanner.next();
        } while (initialState.length() != 210);

        problem = new Problem(initialState);

        problem.solve(choose);
    }
}