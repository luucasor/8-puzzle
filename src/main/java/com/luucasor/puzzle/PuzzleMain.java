package com.luucasor.puzzle;

import java.io.IOException;
import java.util.Scanner;

import static com.luucasor.puzzle.Constantes.*;

public class PuzzleMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        Tabuleiro tabuleiro = new Tabuleiro();
        int numeroJogada = 1;
        do {
            System.out.println();
            System.out.println("Jogada: "+numeroJogada);
            System.out.println("----------");
            cabecalho(tabuleiro);
            jogar(tabuleiro);
            numeroJogada++;
        } while (!tabuleiro.venceu());

        if(tabuleiro.venceu()){
            System.out.println(ANSI_GREEN+"||||||||||||||||||| VENCEU |||||||||||||||||||"+ANSI_RESET);
            System.out.println(ANSI_GREEN+"Total jogadas: "+numeroJogada+ANSI_RESET);
            System.out.println();
            System.out.println("Alvo:");
            System.out.println(tabuleiro.imprimeMatriz(tabuleiro.getMatrizAlvo()));
            System.out.println();
            System.out.println("Jogo finalizado:");
            System.out.println(ANSI_GREEN+tabuleiro.imprimeMatriz(tabuleiro.getMatrizInicial())+ANSI_RESET);
            System.out.println(ANSI_GREEN+"||||||||||||||||||| VENCEU |||||||||||||||||||"+ANSI_RESET);
            System.exit(0);
        }
    }

    private static void jogar(Tabuleiro tabuleiro) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        try {
            Integer valorInformado = scanner.nextInt();
            if(0 == valorInformado){
                System.exit(0);
            }
            if(!tabuleiro.movimentar(valorInformado)){
                System.out.print(ANSI_RED+ "Opção indisponível! Tente novamente:"+ANSI_RESET);
                jogar(tabuleiro);
            }
        } catch (Exception e){
            System.out.print(ANSI_RED+ "Valor inválido! Tente novamente:"+ANSI_RESET);
            jogar(tabuleiro);
        }
    }

    private static void cabecalho(Tabuleiro tabuleiro){
        System.out.println();
        System.out.println("Alvo:");
        System.out.println(tabuleiro.imprimeMatriz(tabuleiro.getMatrizAlvo()));
        System.out.println();
        System.out.println(ANSI_BLUE+"Jogo em andamento:"+ANSI_RESET);
        System.out.println(tabuleiro.imprimeMatriz(tabuleiro.getMatrizInicial()));
        System.out.print("Movimentos disponíveis: ");
        System.out.println(ANSI_BLUE+tabuleiro.getMovimentosDisponiveis()+ANSI_RESET);
        System.out.println("Ou digite 0 para sair");
        System.out.println();
        System.out.print("Informe o número que deseja movimentar: ");
    }
}
