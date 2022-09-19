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

    public String imprimeMatriz(int[][] matriz){
        String primeiraLinha = String.format("| %s | %s | %s |\n", tratarValor(matriz[LINHA_UM][COLUNA_UM]), tratarValor(matriz[LINHA_UM][COLUNA_DOIS]), tratarValor(matriz[LINHA_UM][COLUNA_TRES]));
        String segundaLinha  = String.format("| %s | %s | %s |\n", tratarValor(matriz[LINHA_DOIS][COLUNA_UM]), tratarValor(matriz[LINHA_DOIS][COLUNA_DOIS]), tratarValor(matriz[LINHA_DOIS][COLUNA_TRES]));
        String terceiraLinha = String.format("| %s | %s | %s |\n", tratarValor(matriz[LINHA_TRES][COLUNA_UM]), tratarValor(matriz[LINHA_TRES][COLUNA_DOIS]), tratarValor(matriz[LINHA_TRES][COLUNA_TRES]));
        String matrizImpressao = primeiraLinha+segundaLinha+terceiraLinha;
        return matrizImpressao;
    }

    public boolean movimentar(int valor) {
        int[][] indicePretendido = this.getIndiceValor(valor);
        int[][] indiceValorVazio = this.getIndiceValorVazio();

        return trocaVazio(indicePretendido, indiceValorVazio);
    }

    public List<Integer> getMovimentosDisponiveis() {
        int[][] indiceValorVazio = this.getIndiceValorVazio();
        List<Integer> movimentosDisponiveis = new ArrayList<>();

        int linha = indiceValorVazio[0][0];
        int coluna = indiceValorVazio[0][1];

        Integer valorCima = null;
        try {
            valorCima = this.matrizInicial[linha-1][coluna];
        } catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e);
        } finally {
            if(!Objects.isNull(valorCima)){
                movimentosDisponiveis.add(valorCima);
            }
        }

        Integer valorBaixo = null;
        try {
            valorBaixo = this.matrizInicial[linha+1][coluna];
        } catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e);
        } finally {
            if(!Objects.isNull(valorBaixo)){
                movimentosDisponiveis.add(valorBaixo);
            }
        }

        Integer valorEsquerda = null;
        try {
            valorEsquerda = this.matrizInicial[linha][coluna-1];
        } catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e);
        } finally {
            if(!Objects.isNull(valorEsquerda)){
                movimentosDisponiveis.add(valorEsquerda);
            }
        }

        Integer valorDireita = null;
        try {
            valorDireita = this.matrizInicial[linha][coluna+1];
        } catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e);
        } finally {
            if(!Objects.isNull(valorDireita)){
                movimentosDisponiveis.add(valorDireita);
            }
        }
        return movimentosDisponiveis;
    }

    public boolean venceu() {
        return Arrays.deepEquals(matrizInicial, matrizAlvo);
    }

    private boolean trocaVazio(int[][] indicePretendido, int[][] indiceValorVazio) {
        boolean trocou = false;
        try {
            int valorPretendido = this.matrizInicial[indicePretendido[0][0]][indicePretendido[0][1]];
            if(getMovimentosDisponiveis().contains(valorPretendido)){
                this.matrizInicial[indiceValorVazio[0][0]][indiceValorVazio[0][1]] = valorPretendido;
                this.matrizInicial[indicePretendido[0][0]][indicePretendido[0][1]] = VAZIO;
                trocou = true;
            }
        } catch (ArrayIndexOutOfBoundsException e){

        }
        return trocou;
    }


    private String tratarValor(int value){
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
}
