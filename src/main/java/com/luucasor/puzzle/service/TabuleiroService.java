package com.luucasor.puzzle.service;

import com.luucasor.puzzle.model.Tabuleiro;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.luucasor.puzzle.Constantes.*;

@Getter
@Setter
public class TabuleiroService {

    Tabuleiro tabuleiro;

    public TabuleiroService(Tabuleiro tabuleiro){
        this.tabuleiro = tabuleiro;
    }

    public List<Integer> getMovimentosDisponiveis() {
        int[][] indiceValorVazio = this.getIndiceValorVazio();
        List<Integer> movimentosDisponiveis = new ArrayList<>();

        int linha = indiceValorVazio[0][0];
        int coluna = indiceValorVazio[0][1];
        int[][] matrizInicial = this.tabuleiro.getMatrizInicial();

        Integer valorCima = null;
        try {
            valorCima = matrizInicial[linha-1][coluna];
        } catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e);
        } finally {
            if(!Objects.isNull(valorCima)){
                movimentosDisponiveis.add(valorCima);
            }
        }

        Integer valorBaixo = null;
        try {
            valorBaixo = matrizInicial[linha+1][coluna];
        } catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e);
        } finally {
            if(!Objects.isNull(valorBaixo)){
                movimentosDisponiveis.add(valorBaixo);
            }
        }

        Integer valorEsquerda = null;
        try {
            valorEsquerda = matrizInicial[linha][coluna-1];
        } catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e);
        } finally {
            if(!Objects.isNull(valorEsquerda)){
                movimentosDisponiveis.add(valorEsquerda);
            }
        }

        Integer valorDireita = null;
        try {
            valorDireita = matrizInicial[linha][coluna+1];
        } catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e);
        } finally {
            if(!Objects.isNull(valorDireita)){
                movimentosDisponiveis.add(valorDireita);
            }
        }
        return movimentosDisponiveis;
    }

    public boolean movimentar(int valor) {
        int[][] indicePretendido = this.getIndiceValor(valor);
        int[][] indiceValorVazio = this.getIndiceValorVazio();

        return trocaVazio(indicePretendido, indiceValorVazio);
    }

    private boolean trocaVazio(int[][] indicePretendido, int[][] indiceValorVazio) {
        boolean trocou = false;
        int[][] matrizInicial = this.tabuleiro.getMatrizInicial();
        try {
            int valorPretendido = matrizInicial[indicePretendido[0][0]][indicePretendido[0][1]];
            if(getMovimentosDisponiveis().contains(valorPretendido)){
                matrizInicial[indiceValorVazio[0][0]][indiceValorVazio[0][1]] = valorPretendido;
                matrizInicial[indicePretendido[0][0]][indicePretendido[0][1]] = VAZIO;
                trocou = true;
            }
        } catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e);
        }
        if(trocou){
            this.tabuleiro.incrementaNumeroJogadas();
        }
        return trocou;
    }

    public boolean venceu() {
        return Arrays.deepEquals(this.tabuleiro.getMatrizInicial(), this.tabuleiro.getMatrizAlvo());
    }

    private int[][] getIndiceValor(int valor) {
        int[][] indiceValor = new int[0][0];
        int[][] matrizInicial = this.tabuleiro.getMatrizInicial();
        for (int linha = 0; linha < matrizInicial.length; linha++){
            for (int coluna = 0; coluna < matrizInicial.length; coluna++){
                if(matrizInicial[linha][coluna] == valor){
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

    public String getStringIndiceValorVazio(){
        int[][] indiceValorVazio = this.getIndiceValorVazio();

        int linha = indiceValorVazio[0][0];
        int coluna = indiceValorVazio[0][1];

        return linha+","+coluna;
    }

    public String getStringNivelEscolhido() {
        return this.tabuleiro.getNivelDificuldadeEscolhida().getChave();
    }

    public String getStringMatriz(int[][] matriz) {
        String primeiraLinha = String.format("| %s | %s | %s |\n", tratarValor(matriz[LINHA_UM][COLUNA_UM]), tratarValor(matriz[LINHA_UM][COLUNA_DOIS]), tratarValor(matriz[LINHA_UM][COLUNA_TRES]));
        String segundaLinha  = String.format("| %s | %s | %s |\n", tratarValor(matriz[LINHA_DOIS][COLUNA_UM]), tratarValor(matriz[LINHA_DOIS][COLUNA_DOIS]), tratarValor(matriz[LINHA_DOIS][COLUNA_TRES]));
        String terceiraLinha = String.format("| %s | %s | %s |\n", tratarValor(matriz[LINHA_TRES][COLUNA_UM]), tratarValor(matriz[LINHA_TRES][COLUNA_DOIS]), tratarValor(matriz[LINHA_TRES][COLUNA_TRES]));
        String matrizImpressao = primeiraLinha+segundaLinha+terceiraLinha;
        return matrizImpressao;
    }

    private String tratarValor(int value){
        return value == 0 ? " " : value+"";
    }
}
