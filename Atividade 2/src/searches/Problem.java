package searches;

public class Problem {

    String initialState;
    public Problem(String initialState) {
        this.initialState = initialState;
    }

    public void solve(int searchType) {
        switch (searchType) {
            case 1:
                Search.hillClimbing(initialState);
                break;
            case 2:
                Search.hillClimbingHighestScore(initialState);
                break;
            case 3:
                Search.bestFirst(initialState);
                break;
            default:
                System.out.println("Tipo de busca não encontrada");
        }
    }
}
