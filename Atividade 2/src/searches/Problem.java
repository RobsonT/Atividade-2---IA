package searches;

import java.util.Map;
// O problema é representado por uma string de bits, cujo tamanho é o número de enfermeiros vezes o número de turnos
// A operação para geração de estado é altera um bit de 0 para 1 ou de 1 para 0
public class Problem {

    String initialState;
    int nNursey;
//recebe o estado inicial e o número de enfermeiros
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
