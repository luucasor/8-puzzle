package com.luucasor.puzzle;

import com.luucasor.puzzle.model.Tabuleiro;
import com.luucasor.puzzle.service.TabuleiroService;
import com.luucasor.puzzle.view.Terminal;

import java.time.Instant;

public class PuzzleMain {

    public static void main(String[] args) {
        Instant inicio = Instant.now();

        TabuleiroService tabuleiroService = new TabuleiroService(new Tabuleiro());
        Terminal terminal = new Terminal(tabuleiroService);

        terminal.cabecalho();
        terminal.escolherNivelJogo();

        do {
            terminal.cabecalhoJogada();
            terminal.jogar();
        } while (!tabuleiroService.venceu());

        terminal.vitoria(inicio);
        terminal.rodape();
        System.exit(0);
    }
}
