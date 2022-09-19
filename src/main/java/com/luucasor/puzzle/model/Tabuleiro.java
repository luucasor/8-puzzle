package com.luucasor.puzzle.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Objects;

import static com.luucasor.puzzle.Constantes.*;

@Getter
@Setter
public class Tabuleiro {

    int[][] matrizAlvo = new int[][] {
            {UM, DOIS, TRES},
            {QUATRO, CINCO, SEIS},
            {SETE, OITO, VAZIO}
    };

    int[][] matrizInicial = new int[3][3];

    int[][] matrizFacil = new int[][]{
            {UM, DOIS, TRES},
            {QUATRO, CINCO, SEIS},
            {VAZIO, SETE, OITO}
    };

    int[][] matrizMedia = new int[][]{
            {UM, TRES, VAZIO},
            {CINCO, DOIS, SEIS},
            {QUATRO, SETE, OITO}
    };

    int[][] matrizDificil = new int[][]{
            {CINCO, VAZIO, UM},
            {QUATRO, DOIS, TRES},
            {SETE, OITO, SEIS}
    };

    EnumNivelDificuldade nivelDificuldadeEscolhida;

    int numeroJogadas = 1;

    public void incrementaNumeroJogadas(){
        this.numeroJogadas++;
    }

    public void setIntegerNivelDificuldadeEscolhida(Integer valorInformado){
        Tabuleiro.EnumNivelDificuldade nivel = Tabuleiro.EnumNivelDificuldade.getEnumByValue(valorInformado);
        if(nivel != null){
            this.setNivelDificuldadeEscolhida(nivel);
            switch (this.getNivelDificuldadeEscolhida()){
                case FACIL:
                    this.setMatrizInicial(this.getMatrizFacil());
                    break;
                case MEDIA:
                    this.setMatrizInicial(this.getMatrizMedia());
                    break;
                case DIFICIL:
                    this.setMatrizInicial(this.getMatrizDificil());
                    break;
            }
        }
    }

    public enum EnumNivelDificuldade {
        FACIL("Fácil", 1), MEDIA("Média", 2), DIFICIL("Difícil", 3);

        private final String chave;
        private final Integer valor;

        EnumNivelDificuldade(String chave, Integer valor){
            this.chave = chave;
            this.valor = valor;
        }

        public String getChave(){
            return chave;
        }

        public Integer getValor(){
            return valor;
        }

        public static EnumNivelDificuldade getEnumByValue(Integer value){
            return Arrays.stream(EnumNivelDificuldade.values()).filter(n -> Objects.equals(n.valor, value)).findFirst().orElse(null);
        }

        public static String getNiveis(){
            return FACIL.valor+":"+FACIL.chave+", "+MEDIA.valor+":"+MEDIA.chave+", "+DIFICIL.valor+":"+DIFICIL.chave;
        }
    }
}
