package com.luucasor.puzzle;

import com.luucasor.puzzle.model.Tabuleiro;
import com.luucasor.puzzle.service.TabuleiroService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.luucasor.puzzle.Constantes.*;

public class TabuleiroTest {

    Tabuleiro tabuleiro;
    TabuleiroService tabuleiroService;

    @BeforeEach
    public void setup(){
        this.tabuleiro = new Tabuleiro();
        this.tabuleiroService = new TabuleiroService(tabuleiro);
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
    public void deveRetornarOsValoresCorretosParaConstrucaoDaMatrizFacil(){
        Assertions.assertEquals(UM, this.tabuleiro.getMatrizFacil()[LINHA_UM][COLUNA_UM]);
        Assertions.assertEquals(DOIS, this.tabuleiro.getMatrizFacil()[LINHA_UM][COLUNA_DOIS]);
        Assertions.assertEquals(TRES, this.tabuleiro.getMatrizFacil()[LINHA_UM][COLUNA_TRES]);

        Assertions.assertEquals(QUATRO, this.tabuleiro.getMatrizFacil()[LINHA_DOIS][COLUNA_UM]);
        Assertions.assertEquals(CINCO, this.tabuleiro.getMatrizFacil()[LINHA_DOIS][COLUNA_DOIS]);
        Assertions.assertEquals(SEIS, this.tabuleiro.getMatrizFacil()[LINHA_DOIS][COLUNA_TRES]);

        Assertions.assertEquals(VAZIO, this.tabuleiro.getMatrizFacil()[LINHA_TRES][COLUNA_UM]);
        Assertions.assertEquals(SETE, this.tabuleiro.getMatrizFacil()[LINHA_TRES][COLUNA_DOIS]);
        Assertions.assertEquals(OITO, this.tabuleiro.getMatrizFacil()[LINHA_TRES][COLUNA_TRES]);
    }

    @Test
    public void deveRetornarOsValoresCorretosParaConstrucaoDaMatrizMedia(){
        Assertions.assertEquals(UM, this.tabuleiro.getMatrizMedia()[LINHA_UM][COLUNA_UM]);
        Assertions.assertEquals(TRES, this.tabuleiro.getMatrizMedia()[LINHA_UM][COLUNA_DOIS]);
        Assertions.assertEquals(VAZIO, this.tabuleiro.getMatrizMedia()[LINHA_UM][COLUNA_TRES]);

        Assertions.assertEquals(CINCO, this.tabuleiro.getMatrizMedia()[LINHA_DOIS][COLUNA_UM]);
        Assertions.assertEquals(DOIS, this.tabuleiro.getMatrizMedia()[LINHA_DOIS][COLUNA_DOIS]);
        Assertions.assertEquals(SEIS, this.tabuleiro.getMatrizMedia()[LINHA_DOIS][COLUNA_TRES]);

        Assertions.assertEquals(QUATRO, this.tabuleiro.getMatrizMedia()[LINHA_TRES][COLUNA_UM]);
        Assertions.assertEquals(SETE, this.tabuleiro.getMatrizMedia()[LINHA_TRES][COLUNA_DOIS]);
        Assertions.assertEquals(OITO, this.tabuleiro.getMatrizMedia()[LINHA_TRES][COLUNA_TRES]);
    }

    @Test
    public void deveRetornarOsValoresCorretosParaConstrucaoDaMatrizDificil(){
        Assertions.assertEquals(CINCO, this.tabuleiro.getMatrizDificil()[LINHA_UM][COLUNA_UM]);
        Assertions.assertEquals(VAZIO, this.tabuleiro.getMatrizDificil()[LINHA_UM][COLUNA_DOIS]);
        Assertions.assertEquals(UM, this.tabuleiro.getMatrizDificil()[LINHA_UM][COLUNA_TRES]);

        Assertions.assertEquals(QUATRO, this.tabuleiro.getMatrizDificil()[LINHA_DOIS][COLUNA_UM]);
        Assertions.assertEquals(DOIS, this.tabuleiro.getMatrizDificil()[LINHA_DOIS][COLUNA_DOIS]);
        Assertions.assertEquals(TRES, this.tabuleiro.getMatrizDificil()[LINHA_DOIS][COLUNA_TRES]);

        Assertions.assertEquals(SETE, this.tabuleiro.getMatrizDificil()[LINHA_TRES][COLUNA_UM]);
        Assertions.assertEquals(OITO, this.tabuleiro.getMatrizDificil()[LINHA_TRES][COLUNA_DOIS]);
        Assertions.assertEquals(SEIS, this.tabuleiro.getMatrizDificil()[LINHA_TRES][COLUNA_TRES]);
    }

    @Test
    public void deveRetornarOIndiceDoValorVazioNaMatrizInicial(){
        Assertions.assertEquals("2,2", this.tabuleiroService.getStringIndiceValorVazio());
    }

    @Test
    public void deveRetornarOsMovimentosDisponiveisNaMatrizInicial(){
        List opcoes = this.tabuleiroService.getMovimentosDisponiveis();
        Assertions.assertEquals(Arrays.asList(VAZIO, VAZIO), opcoes);
    }

    @Test
    public void deveRetornarOsMovimentosDisponiveiNaMatrizEmbaralhadaVazioCentro(){
        this.tabuleiro.setMatrizInicial(construirMatrizEmbaralhadaVazioCentro());
        List opcoes = this.tabuleiroService.getMovimentosDisponiveis();
        Assertions.assertTrue(opcoes.contains(DOIS));
        Assertions.assertTrue(opcoes.contains(CINCO));
        Assertions.assertTrue(opcoes.contains(QUATRO));
        Assertions.assertTrue(opcoes.contains(SEIS));
    }

    @Test
    public void deveRetornarOValorDasPecasAdjacentesReferenteAosMovimentosDisponiveisNaMatrizFacil(){
        this.tabuleiro.setIntegerNivelDificuldadeEscolhida(Tabuleiro.EnumNivelDificuldade.FACIL.getValor());
        List<Integer> valores = this.tabuleiroService.getMovimentosDisponiveis();
        Assertions.assertEquals(Arrays.asList(QUATRO, SETE), valores);
    }

    @Test
    public void deveRetornarOValorDasPecasAdjacentesReferenteAosMovimentosDisponiveisNaMatrizMedia(){
        this.tabuleiro.setIntegerNivelDificuldadeEscolhida(Tabuleiro.EnumNivelDificuldade.MEDIA.getValor());
        List<Integer> valores = this.tabuleiroService.getMovimentosDisponiveis();
        Assertions.assertEquals(Arrays.asList(SEIS, TRES), valores);
    }

    @Test
    public void deveRetornarOValorDasPecasAdjacentesReferenteAosMovimentosDisponiveisNaMatrizDificil(){
        this.tabuleiro.setIntegerNivelDificuldadeEscolhida(Tabuleiro.EnumNivelDificuldade.DIFICIL.getValor());
        List<Integer> valores = this.tabuleiroService.getMovimentosDisponiveis();
        Assertions.assertEquals(Arrays.asList(DOIS, CINCO, UM), valores);
    }

    private int[][] construirMatrizEmbaralhadaVazioCentro() {
        return new int[][]{
                {UM, DOIS, TRES},
                {QUATRO, VAZIO, SEIS},
                {SETE, CINCO, OITO}
        };
    }
}
