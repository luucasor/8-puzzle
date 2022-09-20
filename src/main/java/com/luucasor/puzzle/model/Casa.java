package com.luucasor.puzzle.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Casa {

    Integer linha;
    Integer coluna;
    int valor;

    public Casa(Integer linha, Integer coluna, int valor) {
        this.linha = linha;
        this.coluna = coluna;
        this.valor = valor;
    }

}
