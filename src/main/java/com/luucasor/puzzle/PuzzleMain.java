package com.luucasor.puzzle;

import javax.swing.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PuzzleMain extends JFrame {

    public static void main(String[] args){
        Tabuleiro tabuleiro = new Tabuleiro();
        jogar(tabuleiro);
    }

    private static void jogar(Tabuleiro tabuleiro){
        System.out.println("Alvo:");
        System.out.println(tabuleiro.imprimeMatriz(tabuleiro.getMatrizAlvo()));
        System.out.println();
        System.out.println("Início:");
        System.out.println(tabuleiro.imprimeMatriz(tabuleiro.getMatrizInicial()));
        System.out.println("Movimentos disponíveis: "+tabuleiro.getOpcoesDeMovimentoDisponiveis());
        System.out.println("Informe o movimento de seta desejado: ");
        System.out.println("");

        Scanner ler = new Scanner(System.in);
        String comando = ler.nextLine();

        tabuleiro.movimentar(comando);
        jogar(tabuleiro);
    }
}
