package searches;

import java.util.*;

public class SearchUtils {

    //implementacao do metodo quicksort para ser utilizado na busca BestFirst
    //ele ordena um vetor de String passado como referencia pelo seu valor de avaliação
    public static void quickSort(String[] vetor, int inicio, int fim, int nNursey) {
        if (inicio < fim) {
            int posicaoPivo = separar(vetor, inicio, fim, nNursey);
            quickSort(vetor, inicio, posicaoPivo - 1, nNursey);
            quickSort(vetor, posicaoPivo + 1, fim, nNursey);
        }
    }

    public static int separar(String[] vetor, int inicio, int fim, int nNursey) {
        String pivo = vetor[inicio];
        int i = inicio + 1, f = fim;
        while (i <= f) {
            if (evaluate(vetor[i], nNursey) <= evaluate(pivo, nNursey))
                i++;
            else if (evaluate(pivo, nNursey) < evaluate(vetor[f], nNursey))
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


    //printar estados passados como referencia
    //feito para facilitar a visualizacao
    public static void printState(String state, int nNursey) {
        for (int i = 0; i < nNursey; i++) {
            for (int j = i * 21; j < (i * 21) + 21; j++) {
                System.out.print((state.charAt(j) == '1') ? "|1" : "|0");
            }
            System.out.println("|");
        }
    }


    //imprementacao da violacao 1, referente a deve haver no minimo 1
    //e no maximo 3 enfermeiros em cada turno
    public static int r1Violation(String state, int nNursey) {
        //parametros que representao o valor de vialocao do estado passado e
        //um contador para a quantidade de enfermeiros
        int countViolations = 0;
        int countNurse;

        //for que verifica a quantidade de enfermeiros por turno
        for (int i = 0; i < 21; i++) {
            countNurse = 0;
            for (int j = i; j < nNursey * 21; j += 21) {
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


    //imprementacao da violacao 2, referente a cada enfermeiro deve estar
    //alocado em 5 turno por semana
    public static int r2Violation(String state, int nNursey) {
        //parametros que representao o valor de vialocao do estado passado e
        //um contador para a quantidade de enfermeiros
        int countViolations = 0;
        int countNurse;
        //for que verifica quantidade de turno para cada enfermeiro
        for (int i = 0; i < nNursey * 21; i += 21) {
            countNurse = 0;
            for (int j = i; j < i + 21; j++) {
                if (state.charAt(j) == '1') {
                    countNurse++;
                }
            }
            if (countNurse != 5) {
                countViolations++;
            }
        }
        return countViolations;
    }


    //imprementacao da violacao 3, referente a nenhum enfermeiro
    // pode trabalhar mais que 3 turnos seguidos sem folga
    public static int r3Violation(String state, int nNursey) {
        //parametros que representao o valor de vialocao do estado passado e
        //um contador para a quantidade de enfermeiros
        int countViolations = 0;
        int count = 0;

        //for que verifica total de turno trabalhados seguidos para cada enfermeiro
        for (int i = 0; i < nNursey * 21; i += 21) {
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


    //imprementacao da violacao 4, referente a os horarios do enfemeiros
    //deve ser preferencialmente todos no mesmo turno
    public static int r4Violation(String state, int nNursey) {
        int countViolations = 0;
        int count;
        int countH;

        //for que verifica se os turnos trabalhados para cada esfermeiro
        //estao no mesmo horario
        for (int i = 0; i < nNursey * 21; i += 21) {
            countH = 0;
            for (int j = 0; j < 3; j++) {
                count = 0;
                for (int k = i + j; k < i + j + 21; k += 3) {
                    if (state.charAt(k) == '1') {
                        count++;
                    }
                }
                if (count < 5) {
                    countH++;
                }
            }
            if (countH == 3) {
                countViolations++;
            }
        }
        return countViolations;
    }


    //retorna um int que representa o quanto o estado é bom, ou seja, o quanto ele sastifaz os requisitos
//    Quanto menor o valor, menos violações estão ocorrendo
    public static int evaluate(String state, int nNursey) {
        return r1Violation(state, nNursey) + r2Violation(state, nNursey) + r3Violation(state, nNursey) + r4Violation(state, nNursey);
    }

}
