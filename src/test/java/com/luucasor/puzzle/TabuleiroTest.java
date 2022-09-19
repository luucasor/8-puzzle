package com.luucasor.puzzle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.luucasor.puzzle.Constantes.*;

public class TabuleiroTest {

    Tabuleiro tabuleiro;

    @BeforeEach
    public void setup(){
        this.tabuleiro = new Tabuleiro();
    }

    @Test
    public void deveRetornarOsValoresCorretosParaConstrucaoDaMatrizAlvo(){
        Assertions.assertEquals(UM, this.tabuleiro.getMatrizAlvo()[LINHA_UM][COLUNA_UM]);
        Assertions.assertEquals(DOIS, this.tabuleiro.getMatrizAlvo()[LINHA_UM][COLUNA_DOIS]);
        Assertions.assertEquals(TRES, this.tabuleiro.getMatrizAlvo()[LINHA_UM][COLUNA_TRES]);

        Assertions.assertEquals(QUATRO, this.tabuleiro.getMatrizAlvo()[LINHA_DOIS][COLUNA_UM]);
        Assertions.assertEquals(CINCO, this.tabuleiro.getMatrizAlvo()[LINHA_DOIS][COLUNA_DOIS]);
        Assertions.assertEquals(SEIS, this.tabuleiro.getMatrizAlvo()[LINHA_DOIS][COLUNA_TRES]);

        Assertions.assertEquals(SETE, this.tabuleiro.getMatrizAlvo()[LINHA_TRES][COLUNA_UM]);
        Assertions.assertEquals(OITO, this.tabuleiro.getMatrizAlvo()[LINHA_TRES][COLUNA_DOIS]);
        Assertions.assertEquals(VAZIO, this.tabuleiro.getMatrizAlvo()[LINHA_TRES][COLUNA_TRES]);
    }

    @Test
    public void deveRetornarOsValoresCorretosParaConstrucaoDaMatrizInicial(){
        Assertions.assertEquals(UM, this.tabuleiro.getMatrizInicial()[LINHA_UM][COLUNA_UM]);
        Assertions.assertEquals(DOIS, this.tabuleiro.getMatrizInicial()[LINHA_UM][COLUNA_DOIS]);
        Assertions.assertEquals(TRES, this.tabuleiro.getMatrizInicial()[LINHA_UM][COLUNA_TRES]);

        Assertions.assertEquals(QUATRO, this.tabuleiro.getMatrizInicial()[LINHA_DOIS][COLUNA_UM]);
        Assertions.assertEquals(OITO, this.tabuleiro.getMatrizInicial()[LINHA_DOIS][COLUNA_DOIS]);
        Assertions.assertEquals(SEIS, this.tabuleiro.getMatrizInicial()[LINHA_DOIS][COLUNA_TRES]);

        Assertions.assertEquals(SETE, this.tabuleiro.getMatrizInicial()[LINHA_TRES][COLUNA_UM]);
        Assertions.assertEquals(CINCO, this.tabuleiro.getMatrizInicial()[LINHA_TRES][COLUNA_DOIS]);
        Assertions.assertEquals(VAZIO, this.tabuleiro.getMatrizInicial()[LINHA_TRES][COLUNA_TRES]);
    }

    @Test
    public void deveRetornarOIndiceDoValorVazioNaMatrizInicial(){
        Assertions.assertEquals("2,2", this.tabuleiro.getStringIndiceValorVazio());
    }

    @Test
    public void deveRetornarOsMovimentosDisponiveisNaMatrizInicial(){
        List opcoes = this.tabuleiro.getMovimentosDisponiveis();
        Assertions.assertEquals(Arrays.asList(SEIS, CINCO), opcoes);
    }

    @Test
    public void deveRetornarOsMovimentosDisponiveiNaMatrizEmbaralhadaVazioCentro(){
        this.tabuleiro.setMatrizInicial(construirMatrizEmbaralhadaVazioCentro());
        List opcoes = this.tabuleiro.getMovimentosDisponiveis();
        Assertions.assertTrue(opcoes.contains(DOIS));
        Assertions.assertTrue(opcoes.contains(CINCO));
        Assertions.assertTrue(opcoes.contains(QUATRO));
        Assertions.assertTrue(opcoes.contains(SEIS));
    }

    @Test
    public void deveRetornarOValorDasPecasAdjacentesReferenteAosMovimentosDisponiveisNaMatrizInicial(){
        List<Integer> valores = this.tabuleiro.getMovimentosDisponiveis();
        Assertions.assertEquals(Arrays.asList(SEIS, CINCO), valores);
    }

    private int[][] construirMatrizEmbaralhadaVazioCentro() {
        return new int[][]{
                {UM, DOIS, TRES},
                {QUATRO, VAZIO, SEIS},
                {SETE, CINCO, OITO}
        };
    }
}
