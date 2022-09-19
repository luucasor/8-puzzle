package com.luucasor.puzzle;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

import static com.luucasor.puzzle.Constantes.*;

@Getter
@Setter
public class Tabuleiro {

    int[][] matrizAlvo = new int[][] {
            {UM, DOIS, TRES},
            {QUATRO, CINCO, SEIS},
            {SETE, OITO, VAZIO}
    };
    int[][] matrizInicial = new int[][]{
            {UM, DOIS, TRES},
            {QUATRO, OITO, SEIS},
            {SETE, CINCO, VAZIO}
    };

    public String getStringIndiceValorVazio(){
        int[][] indiceValorVazio = this.getIndiceValorVazio();

        int linha = indiceValorVazio[0][0];
        int coluna = indiceValorVazio[0][1];

        return linha+","+coluna;
    }

    public List getOpcoesDeMovimentoDisponiveis() {
        HashMap<SetaEnum, Integer> movimentosDisponiveisESeusValoresAdjacentes = getMovimentosDisponiveisESeusValoresAdjacentes();
        return Arrays.asList(movimentosDisponiveisESeusValoresAdjacentes.keySet().toArray());
    }

    public List getValoresAdjacentesDentreOsMovimentosDisponiveis() {
        HashMap<SetaEnum, Integer> movimentosDisponiveisESeusValoresAdjacentes = getMovimentosDisponiveisESeusValoresAdjacentes();
        return new ArrayList<>(movimentosDisponiveisESeusValoresAdjacentes.values());
    }

    public String imprimeMatriz(int[][] matriz){
        String primeiraLinha = String.format("| %s | %s | %s |\n", substituiZero(matriz[LINHA_UM][COLUNA_UM]), substituiZero(matriz[LINHA_UM][COLUNA_DOIS]), substituiZero(matriz[LINHA_UM][COLUNA_TRES]));
        String segundaLinha  = String.format("| %s | %s | %s |\n", substituiZero(matriz[LINHA_DOIS][COLUNA_UM]), substituiZero(matriz[LINHA_DOIS][COLUNA_DOIS]), substituiZero(matriz[LINHA_DOIS][COLUNA_TRES]));
        String terceiraLinha = String.format("| %s | %s | %s |\n", substituiZero(matriz[LINHA_TRES][COLUNA_UM]), substituiZero(matriz[LINHA_TRES][COLUNA_DOIS]), substituiZero(matriz[LINHA_TRES][COLUNA_TRES]));
        return primeiraLinha+segundaLinha+terceiraLinha;
    }

    public void movimentar(String comando) {
        int[][] indiceValorVazio = this.getIndiceValorVazio();

        int linhaVazio = indiceValorVazio[0][0];
        int colunaVazio = indiceValorVazio[0][1];

        Integer valorPretendido = null;

        switch (comando){
            case "4":
                valorPretendido = this.matrizInicial[linhaVazio][colunaVazio-1];
                break;
            case "6":
                valorPretendido = this.matrizInicial[linhaVazio][colunaVazio+1];
                break;
            case "8":
                valorPretendido = this.matrizInicial[linhaVazio-1][colunaVazio];
                break;
            case "2":
                valorPretendido = this.matrizInicial[linhaVazio+1][colunaVazio];
                break;
        }

        System.out.println(valorPretendido);
        trocaVazio(valorPretendido, linhaVazio, colunaVazio);

    }

    private void trocaVazio(Integer valorPretendido, int linhaVazio, int colunaVazio) {
        int[][] indiceValor = this.getIndiceValor(valorPretendido);

        int linhaPretendida = indiceValor[0][0];
        int colunaPretendida = indiceValor[0][1];

        this.matrizInicial[linhaVazio][colunaVazio] = valorPretendido;
        this.matrizInicial[linhaPretendida][colunaPretendida] = VAZIO;
    }


    private String substituiZero(int value){
        return value == 0 ? " " : value+"";
    }

    private int[][] getIndiceValor(int valor) {
        int[][] indiceValor = new int[0][0];
        for (int linha = 0; linha < this.matrizInicial.length; linha++){
            for (int coluna = 0; coluna < this.matrizInicial.length; coluna++){
                if(this.matrizInicial[linha][coluna] == valor){
                    indiceValor = new int[][]{
                            {linha,coluna}
                    };
                }
            }
        }
        return indiceValor;
    }

    private int[][] getIndiceValorVazio() {
        return getIndiceValor(VAZIO);
    }

    private HashMap<SetaEnum, Integer> getMovimentosDisponiveisESeusValoresAdjacentes() {
        int[][] indiceValorVazio = this.getIndiceValorVazio();
        HashMap<SetaEnum, Integer> movimentosDisponiveisESeusValoresAdjacentes = new HashMap<>();

        int linha = indiceValorVazio[0][0];
        int coluna = indiceValorVazio[0][1];

        Integer valorCima = null;
        try {
            valorCima = this.matrizInicial[linha-1][coluna];
        } catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e);
        } finally {
            if(!Objects.isNull(valorCima)){
                movimentosDisponiveisESeusValoresAdjacentes.put(SetaEnum.CIMA, valorCima);
            }
        }

        Integer valorBaixo = null;
        try {
            valorBaixo = this.matrizInicial[linha+1][coluna];
        } catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e);
        } finally {
            if(!Objects.isNull(valorBaixo)){
                movimentosDisponiveisESeusValoresAdjacentes.put(SetaEnum.BAIXO, valorBaixo);
            }
        }

        Integer valorEsquerda = null;
        try {
            valorEsquerda = this.matrizInicial[linha][coluna-1];
        } catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e);
        } finally {
            if(!Objects.isNull(valorEsquerda)){
                movimentosDisponiveisESeusValoresAdjacentes.put(SetaEnum.ESQUERDA, valorEsquerda);
            }
        }

        Integer valorDireita = null;
        try {
            valorDireita = this.matrizInicial[linha][coluna+1];
        } catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e);
        } finally {
            if(!Objects.isNull(valorDireita)){
                movimentosDisponiveisESeusValoresAdjacentes.put(SetaEnum.DIREITA, valorDireita);
            }
        }

        return movimentosDisponiveisESeusValoresAdjacentes;
    }

    public enum SetaEnum {
        CIMA("8 = ⬆️"), BAIXO("2 = ⬇️"), ESQUERDA("4 = ⬅️"), DIREITA("6 = ➡️");

        private final String seta;

        SetaEnum(String valor){
            this.seta = valor;
        }

        public String toString() {
            return this.seta;
        }
    }
}
