# 8-puzzle


O Jogo dos Oito é um jogo de tabuleiro. O jogo é estruturado em um tabuleiro 3×3 com 8 peças (cada peça tem um número de 1 a 8) e um espaço vazio. O objetivo é colocar os números nas peças para combinar com a configuração final usando o espaço vazio. Podemos deslizar quatro peças adjacentes (esquerda, direita, acima e abaixo) no espaço vazio.

### Estado final ou vitória:

<table>
  <tr>
    <td>1</td>
    <td>2</td>
    <td>3</td>
  </tr>
  <tr>
    <td>4</td>
    <td>5</td>
    <td>6</td>
  </tr>
  <tr>
    <td>7</td>
    <td>8</td>
    <td></td>
  </tr>
</table>

### Exemplo de estado inicial ou embaralhado:

<table>
  <tr>
    <td>1</td>
    <td>2</td>
    <td>3</td>
  </tr>
  <tr>
    <td>4</td>
    <td>8</td>
    <td>6</td>
  </tr>
  <tr>
    <td>7</td>
    <td>5</td>
    <td></td>
  </tr>
</table>

## Objetivo:

### Implementar um jogo de oito (não é necessário um solucionador automático do jogo).

O limite da implementação fica a critério do candidato;
Recomenda-se o uso da melhores práticas de desenvolvimento;
Não limitamos a arquitetura ou solução.

## Como rodar aplicação

Para rodar a aplicação, basta carregá-la em sua IDE de preferência, importando-a como projeto Maven e executar a classe PuzzleMain.java
Outra alternativa é navegar até a raiz do projeto e executar o comando abaixo:
```
mvn exec:java -Dexec.mainClass="com.luucasor.puzzle.PuzzleMain"
```
