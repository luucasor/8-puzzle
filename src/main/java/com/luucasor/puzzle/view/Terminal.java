package com.luucasor.puzzle.view;

import com.luucasor.puzzle.model.Tabuleiro;
import com.luucasor.puzzle.service.TabuleiroService;

import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

import static com.luucasor.puzzle.Constantes.*;

public class Terminal {

    TabuleiroService tabuleiroService;
    public Terminal(TabuleiroService tabuleiroService){
        this.tabuleiroService = tabuleiroService;
    }

    public void cabecalho(){
        System.out.println();
        System.out.println("|||||||||||||||||| Início ||||||||||||||||||");
    }

    public void escolherNivelJogo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha o nível de dificuldade do jogo: ");
        System.out.print(Tabuleiro.EnumNivelDificuldade.getNiveis() + ": ");
        Integer valorInformado = null;
        try {
            valorInformado = scanner.nextInt();
            if(0 == valorInformado){
                System.exit(0);
            }
            boolean nivelExistente = Tabuleiro.EnumNivelDificuldade.getEnumByValue(valorInformado) != null;
            if(!nivelExistente){
                System.out.println(ANSI_RED+ "Opção indisponível! Tente novamente:"+ANSI_RESET);
                escolherNivelJogo();
            }
        } catch (Exception e){
            System.out.println(ANSI_RED+ "Valor inválido! Tente novamente:"+ANSI_RESET);
            escolherNivelJogo();
        } finally {
            tabuleiroService.getTabuleiro().setIntegerNivelDificuldadeEscolhida(valorInformado);
        }
    }

    public void cabecalhoJogada(){
        System.out.println();
        System.out.println("Jogada: "+tabuleiroService.getTabuleiro().getNumeroJogadas());
        System.out.println("Dificuldade: "+tabuleiroService.getStringNivelEscolhido());
        System.out.println("----------------------------");
        System.out.println();
        System.out.println("Alvo:");
        System.out.println(tabuleiroService.getStringMatriz(tabuleiroService.getTabuleiro().getMatrizAlvo()));
        System.out.println();
        System.out.println(ANSI_BLUE+"Jogo em andamento:"+ANSI_RESET);
        System.out.println(tabuleiroService.getStringMatriz(tabuleiroService.getTabuleiro().getMatrizInicial()));
        System.out.print("Movimentos disponíveis: ");
        System.out.println(ANSI_BLUE+tabuleiroService.getMovimentosDisponiveis()+ANSI_RESET);
        System.out.println("Ou digite 0 para sair");
        System.out.println();
        System.out.print("Informe o número que deseja movimentar: ");
    }

    public void jogar() {
        Scanner scanner = new Scanner(System.in);
        try {
            Integer valorInformado = scanner.nextInt();
            if(0 == valorInformado){
                System.exit(0);
            }
            if(!tabuleiroService.movimentar(valorInformado)){
                System.out.print(ANSI_RED+ "Opção indisponível! Tente novamente:"+ANSI_RESET);
                jogar();
            }
        } catch (Exception e){
            System.out.print(ANSI_RED+ "Valor inválido! Tente novamente:"+ANSI_RESET);
            jogar();
        }
    }

    public void vitoria(Instant inicio) {
        Instant fim = Instant.now();
        Duration totalTime = Duration.between(inicio, fim);
        String duracaoTexto = "Duração: "+ totalTime.getSeconds()+" segundos";

        System.out.println(ANSI_GREEN+"||||||||||||||||||| VENCEU |||||||||||||||||||"+ANSI_RESET);
        System.out.println("Dificuldade: "+tabuleiroService.getStringNivelEscolhido());
        if(totalTime.getSeconds() > 60){
            duracaoTexto = "Duração: "+ totalTime.getSeconds()/60 +" minutos";
        }
        System.out.println(duracaoTexto);
        System.out.println(ANSI_GREEN+"Total jogadas: "+tabuleiroService.getTabuleiro().getNumeroJogadas()+ANSI_RESET);
        System.out.println();
        System.out.println("Alvo:");
        System.out.println(tabuleiroService.getStringMatriz(tabuleiroService.getTabuleiro().getMatrizAlvo()));
        System.out.println();
        System.out.println("Jogo finalizado:");
        System.out.println(ANSI_GREEN+tabuleiroService.getStringMatriz(tabuleiroService.getTabuleiro().getMatrizInicial())+ANSI_RESET);
        System.out.println(ANSI_GREEN+"||||||||||||||||||| VENCEU |||||||||||||||||||"+ANSI_RESET);
    }

    public void rodape(){
        System.out.println();
        System.out.println("||||||||||||||||||   Fim   ||||||||||||||||||");
    }
}
