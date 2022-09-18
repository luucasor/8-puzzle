import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class PuzzleTest {

    int[][] matrizAlvo;
    int[][] matrizInicial;
    int[][] matrizEmbaralhadaVazioCentro;
    final static int LINHA_UM = 0;
    final static int LINHA_DOIS = 1;
    final static int LINHA_TRES = 2;

    final static int COLUNA_UM = 0;
    final static int COLUNA_DOIS = 1;
    final static int COLUNA_TRES = 2;

    //Operar
    final static int UM = 1;
    final static int DOIS = 2;
    final static int TRES = 3;
    final static int QUATRO = 4;
    final static int CINCO = 5;
    final static int SEIS = 6;
    final static int SETE = 7;
    final static int OITO = 8;
    final static int VAZIO = 0;

    @BeforeEach
    public void setup(){
        this.matrizAlvo = construirMatrizAlvo();
        this.matrizInicial = construirMatrizInicial();
        this.matrizEmbaralhadaVazioCentro = construirMatrizEmbaralhadaVazioCentro();
    }

    @Test
    public void deveRetornarOsValoresCorretosParaConstrucaoDaMatrizAlvo(){
        Assertions.assertEquals(UM, matrizAlvo[LINHA_UM][COLUNA_UM]);
        Assertions.assertEquals(DOIS, matrizAlvo[LINHA_UM][COLUNA_DOIS]);
        Assertions.assertEquals(TRES, matrizAlvo[LINHA_UM][COLUNA_TRES]);

        Assertions.assertEquals(QUATRO, matrizAlvo[LINHA_DOIS][COLUNA_UM]);
        Assertions.assertEquals(CINCO, matrizAlvo[LINHA_DOIS][COLUNA_DOIS]);
        Assertions.assertEquals(SEIS, matrizAlvo[LINHA_DOIS][COLUNA_TRES]);

        Assertions.assertEquals(SETE, matrizAlvo[LINHA_TRES][COLUNA_UM]);
        Assertions.assertEquals(OITO, matrizAlvo[LINHA_TRES][COLUNA_DOIS]);
        Assertions.assertEquals(VAZIO, matrizAlvo[LINHA_TRES][COLUNA_TRES]);
    }

    @Test
    public void deveRetornarOsValoresCorretosParaConstrucaoDaMatrizInicial(){
        Assertions.assertEquals(UM, matrizInicial[LINHA_UM][COLUNA_UM]);
        Assertions.assertEquals(DOIS, matrizInicial[LINHA_UM][COLUNA_DOIS]);
        Assertions.assertEquals(TRES, matrizInicial[LINHA_UM][COLUNA_TRES]);

        Assertions.assertEquals(QUATRO, matrizInicial[LINHA_DOIS][COLUNA_UM]);
        Assertions.assertEquals(OITO, matrizInicial[LINHA_DOIS][COLUNA_DOIS]);
        Assertions.assertEquals(SEIS, matrizInicial[LINHA_DOIS][COLUNA_TRES]);

        Assertions.assertEquals(SETE, matrizInicial[LINHA_TRES][COLUNA_UM]);
        Assertions.assertEquals(CINCO, matrizInicial[LINHA_TRES][COLUNA_DOIS]);
        Assertions.assertEquals(VAZIO, matrizInicial[LINHA_TRES][COLUNA_TRES]);
    }

    @Test
    public void deveRetornarOIndiceDoValorVazioNaMatrizInicial(){
        String indiceValorVazio = "";
        for (int linha = 0; linha < matrizInicial.length; linha++){
            for (int coluna = 0; coluna < matrizInicial.length; coluna++){
                System.out.print(matrizInicial[linha][coluna]+" ");
                if(coluna == matrizInicial.length -1) {
                    System.out.println("");
                }
                if(matrizInicial[linha][coluna] == VAZIO){
                    indiceValorVazio = linha+","+coluna;
                }
            }
        }
        System.out.println();
        System.out.println("Índice valor VAZIO: "+ indiceValorVazio);
        Assertions.assertEquals("2,2", indiceValorVazio);
    }

    @Test
    public void deveRetornarOsMovimentosDisponiveisNaMatrizInicial(){
        List<SetaEnum> opcoes = getOpcoesDeMovimentoDisponiveis(matrizInicial);
        System.out.println("Opções: "+opcoes);
        Assertions.assertEquals(Arrays.asList(SetaEnum.CIMA, SetaEnum.ESQUERDA), opcoes);
    }

    @Test
    public void deveRetornarOsMovimentosDisponiveiNaMatrizEmbaralhadaVazioCentro(){
        List<SetaEnum> opcoes = getOpcoesDeMovimentoDisponiveis(matrizEmbaralhadaVazioCentro);
        System.out.println("Opções: "+opcoes);
        Assertions.assertEquals(Arrays.asList(SetaEnum.ESQUERDA, SetaEnum.CIMA, SetaEnum.BAIXO, SetaEnum.DIREITA), opcoes);
    }

    @Test
    public void deveRetornarOValorDasPecasAdjacentesReferenteAosMovimentosDisponiveisNaMatrizInicial(){
        List<Integer> valores = getValoresAdjacentesDentreOsMovimentosDisponiveis(matrizInicial);

        System.out.println(valores);
        Assertions.assertEquals(Arrays.asList(SEIS, CINCO), valores);
    }

    private HashMap<SetaEnum, Integer> getMovimentosDisponiveisESeusValoresAdjacentes(int[][] matriz) {
        int[][] indiceValorVazio = getIndiceValorVazio(matriz);
        HashMap<SetaEnum, Integer> movimentosDisponiveisESeusValoresAdjacentes = new HashMap<>();

        int linha = indiceValorVazio[0][0];
        int coluna = indiceValorVazio[0][1];

        Integer valorCima = null;
        try {
            valorCima = matriz[linha-1][coluna];
        } catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e);
        } finally {
            if(!Objects.isNull(valorCima)){
                movimentosDisponiveisESeusValoresAdjacentes.put(SetaEnum.CIMA, valorCima);
            }
        }

        Integer valorBaixo = null;
        try {
            valorBaixo = matriz[linha+1][coluna];
        } catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e);
        } finally {
            if(!Objects.isNull(valorBaixo)){
                movimentosDisponiveisESeusValoresAdjacentes.put(SetaEnum.BAIXO, valorBaixo);
            }
        }

        Integer valorEsquerda = null;
        try {
            valorEsquerda = matriz[linha][coluna-1];
        } catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e);
        } finally {
            if(!Objects.isNull(valorEsquerda)){
                movimentosDisponiveisESeusValoresAdjacentes.put(SetaEnum.ESQUERDA, valorEsquerda);
            }
        }

        Integer valorDireita = null;
        try {
            valorDireita = matriz[linha][coluna+1];
        } catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e);
        } finally {
            if(!Objects.isNull(valorDireita)){
                movimentosDisponiveisESeusValoresAdjacentes.put(SetaEnum.DIREITA, valorDireita);
            }
        }

        return movimentosDisponiveisESeusValoresAdjacentes;
    }

    private List getOpcoesDeMovimentoDisponiveis(int[][] matriz) {
        HashMap<SetaEnum, Integer> movimentosDisponiveisESeusValoresAdjacentes = getMovimentosDisponiveisESeusValoresAdjacentes(matriz);
        return Arrays.asList(movimentosDisponiveisESeusValoresAdjacentes.keySet().toArray());
    }

    private List getValoresAdjacentesDentreOsMovimentosDisponiveis(int[][] matriz) {
        HashMap<SetaEnum, Integer> movimentosDisponiveisESeusValoresAdjacentes = getMovimentosDisponiveisESeusValoresAdjacentes(matriz);
        return new ArrayList<>(movimentosDisponiveisESeusValoresAdjacentes.values());
    }

    private int[][] getIndiceValorVazio(int[][] matriz) {
        int[][] indiceValorVazio = new int[0][0];
        for (int linha = 0; linha < matriz.length; linha++){
            for (int coluna = 0; coluna < matriz.length; coluna++){
                boolean ehVazio = matriz[linha][coluna] == 0;
                if(ehVazio){
                    System.out.print("  ");
                } else {
                    System.out.print(matriz[linha][coluna]+" ");
                }
                if(coluna == matriz.length -1) {
                    System.out.println("");
                }
                if(matriz[linha][coluna] == VAZIO){
                    indiceValorVazio = new int[][]{
                            {linha,coluna}
                    };
                }
            }
        }
        return indiceValorVazio;
    }

    public enum SetaEnum {
        CIMA("⬆️"), BAIXO("⬇️"), ESQUERDA("⬅️"), DIREITA("➡️");

        private final String seta;

        SetaEnum(String valor){
            this.seta = valor;
        }

        public String toString() {
            return this.seta;
        }
    }

    private int[][] construirMatrizAlvo() {
        return new int[][] {
                {UM, DOIS, TRES},
                {QUATRO, CINCO, SEIS},
                {SETE, OITO, VAZIO}
        };
    }

    private int[][] construirMatrizInicial(){
        return new int[][]{
                {UM, DOIS, TRES},
                {QUATRO, OITO, SEIS},
                {SETE, CINCO, VAZIO}
        };
    }

    private int[][] construirMatrizEmbaralhadaVazioCentro() {
        return new int[][]{
                {UM, DOIS, TRES},
                {QUATRO, VAZIO, SEIS},
                {SETE, CINCO, OITO}
        };
    }
}
