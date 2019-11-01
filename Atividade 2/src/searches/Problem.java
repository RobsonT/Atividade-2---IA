package searches;

import java.util.Map;

public class Problem {

    //Recebendo string contendo o estado inicial do problema
    String initialState;
    int nNursey;

    public Problem(String initialState, int nNursey) {
        this.initialState = initialState;
        this.nNursey = nNursey;
    }

    //chamada da busca escolhida pelo usuario
    public Map<String, Integer> solve(int searchType, int nNursey) {
        switch (searchType) {
            case 1:
                HillClimbing.hillClimbing(initialState, nNursey);
                return null;
            case 2:
                return SteepestAscentHillClimbing.hillClimbingSteepestAscent(initialState, nNursey);
            case 3:
                BestFirst.bestFirst(initialState, nNursey);
                return null;
            default:
                System.out.println("Tipo de busca não encontrada");
                return null;
        }
    }
}
