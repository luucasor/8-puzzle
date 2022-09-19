package com.luucasor.puzzle;

import javax.swing.*;
import java.util.Scanner;

public class PuzzleMain extends JFrame {

    public static void main(String[] args){
        Tabuleiro tabuleiro = new Tabuleiro();
        jogar(tabuleiro);
    }

    private static void jogar(Tabuleiro tabuleiro){
        System.out.println("Alvo:");
        System.out.println(tabuleiro.imprimeMatriz(tabuleiro.getMatrizAlvo()));
        System.out.println();
        System.out.println("Jogo em andamento:");
        System.out.println(tabuleiro.imprimeMatriz(tabuleiro.getMatrizInicial()));
        System.out.println("Movimentos disponíveis: "+tabuleiro.getValoresAdjacentesDentreOsMovimentosDisponiveis());
        System.out.println("Informe o movimento número desejado: ");
        System.out.println("");

        Scanner scanner = new Scanner(System.in);
        if(tabuleiro.venceu()){
            System.out.println("||||||||||||||||||| VENCEU |||||||||||||||||||");
            System.out.println();
            System.out.println("Alvo:");
            System.out.println(tabuleiro.imprimeMatriz(tabuleiro.getMatrizAlvo()));
            System.out.println();
            System.out.println("Jogo finalizado:");
            System.out.println(tabuleiro.imprimeMatriz(tabuleiro.getMatrizInicial()));
            System.out.println("||||||||||||||||||| VENCEU |||||||||||||||||||");
            scanner.close();
            System.exit(0);
        }
        while(!scanner.hasNextInt() || !tabuleiro.movimentar(scanner.nextInt())){
            System.out.println("Valor inválido! Tente novamente.");
            scanner.next();
        }
        jogar(tabuleiro);
    }
}
