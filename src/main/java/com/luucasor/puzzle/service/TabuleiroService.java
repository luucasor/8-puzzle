package com.luucasor.puzzle.service;

import com.luucasor.puzzle.model.Casa;
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
        Casa casaValorVazio = this.getCasaValor(VAZIO);
        List<Integer> movimentosDisponiveis = new ArrayList<>();

        int[][] matrizInicial = this.tabuleiro.getMatrizInicial();

        Integer valorCima = null;
        try {
            valorCima = matrizInicial[casaValorVazio.getLinha()-1][casaValorVazio.getColuna()];
        } catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e);
        } finally {
            if(!Objects.isNull(valorCima)){
                movimentosDisponiveis.add(valorCima);
            }
        }

        Integer valorBaixo = null;
        try {
            valorBaixo = matrizInicial[casaValorVazio.getLinha()+1][casaValorVazio.getColuna()];
        } catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e);
        } finally {
            if(!Objects.isNull(valorBaixo)){
                movimentosDisponiveis.add(valorBaixo);
            }
        }

        Integer valorEsquerda = null;
        try {
            valorEsquerda = matrizInicial[casaValorVazio.getLinha()][casaValorVazio.getColuna()-1];
        } catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e);
        } finally {
            if(!Objects.isNull(valorEsquerda)){
                movimentosDisponiveis.add(valorEsquerda);
            }
        }

        Integer valorDireita = null;
        try {
            valorDireita = matrizInicial[casaValorVazio.getLinha()][casaValorVazio.getColuna()+1];
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
        Casa casaPretendida = this.getCasaValor(valor);
        Casa casaValorVazio = this.getCasaValor(VAZIO);
        return trocaVazio(casaPretendida, casaValorVazio);
    }

    private boolean trocaVazio(Casa casaPretendida, Casa casaValorVazio) {
        boolean trocou = false;
        int[][] matrizInicial = this.tabuleiro.getMatrizInicial();

        try {
            if(getMovimentosDisponiveis().contains(casaPretendida.getValor())){
                matrizInicial[casaValorVazio.getLinha()][casaValorVazio.getColuna()] = casaPretendida.getValor();
                matrizInicial[casaPretendida.getLinha()][casaPretendida.getColuna()] = VAZIO;
                this.tabuleiro.incrementaNumeroJogadas();
                trocou = true;
            }
        } catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(e);
        }
        return trocou;
    }

    public boolean venceu() {
        return Arrays.deepEquals(this.tabuleiro.getMatrizInicial(), this.tabuleiro.getMatrizAlvo());
    }

    private Casa getCasaValor(int valor) {
        Casa casa = null;
        int[][] matrizInicial = this.tabuleiro.getMatrizInicial();
        boolean encontrou = false;
        for (int linha = 0; linha < matrizInicial.length; linha++){
            for (int coluna = 0; coluna < matrizInicial.length; coluna++){
                if(matrizInicial[linha][coluna] == valor){
                    casa = new Casa(linha, coluna, valor);
                    break;
                }
            }
            if(encontrou){
                break;
            }
        }
        return casa;
    }

    public String getStringIndiceValorVazio(){
        Casa casaValorVazio = this.getCasaValor(VAZIO);
        return casaValorVazio.getLinha()+","+casaValorVazio.getColuna();
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