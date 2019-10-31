package searches;

public class Problem {

    //Recebendo string contendo o estado inicial do problema
    String initialState;
    int nNursey;

    public Problem(String initialState, int nNursey) {
        this.initialState = initialState;
        this.nNursey = nNursey;
    }

    //chamada da busca escolhida pelo usuario
    public void solve(int searchType, int nNursey) {
        switch (searchType) {
            case 1:
                HillClimbing.hillClimbing(initialState, nNursey);
                break;
            case 2:
                SteepestAscentHillClimbing.hillClimbingSteepestAscent(initialState, nNursey);
                break;
            case 3:
                BestFirst.bestFirst(initialState, nNursey);
                break;
            default:
                System.out.println("Tipo de busca não encontrada");
        }
    }
}
