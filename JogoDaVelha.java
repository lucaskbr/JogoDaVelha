import com.sun.org.apache.xml.internal.utils.SystemIDResolver;

import java.util.Scanner;

public class JogoDaVelha {

    private String jogo[][] = new String[3][3];


    public JogoDaVelha() {

        for (int i = 0; i < jogo.length; i++) {
            for (int j = 0; j < jogo.length; j++) {
                jogo[i][j] = " ";
            }
        }
    }

    public void imprimir() {

        System.out.println("  Jogo da VELHA\n");

        for (int i = 0; i < 3; i++) {
            System.out.print("Linha " + i + ":  ");
            for (int j = 0; j < 3; j++) {
                System.out.print(jogo[i][j]);

                if (j == 0 || j == 1) {
                    System.out.print("|");
                }
            }

            System.out.println();

            if (i == 0 || i == 1) {
                System.out.print("          ");
                System.out.print("-----");
                System.out.println();
            }
        }

        System.out.println();
    }

    public void intro() {

        System.out.println("O Jogador um sera o \'x\' ");
        System.out.println("O Jogador dois sera o \'o\' ");
        System.out.println("O Jogador um começa, em seguida o dois");

    }


    public int escolhaDeColuna() {

        Scanner input = new Scanner(System.in);
        System.out.println("Digite qual coluna(0,1,2) você quer realizar a jogada");
        int coluna = input.nextInt();

        coluna = verificadorDeColuna(coluna);

        return coluna;

    }


    public int verificadorDeColuna(int coluna) {

        while (coluna > 2 | coluna < 0) {
            System.out.println("Escolha alguma coluna valida!");
            coluna = escolhaDeColuna();
        }

        return coluna;
    }


    public int escolhaDeLinha() {

        Scanner input = new Scanner(System.in);
        System.out.println("Digite a linha (0,1,2) que você quer jogar");
        int linha = input.nextInt();

        linha = verificadorDeLinha(linha);

        return linha;

    }


    public int verificadorDeLinha(int linha) {

        while (linha > 2 | linha < 0) {
            System.out.println("Escolha alguma linha valida");
            linha = escolhaDeLinha();
        }
        return linha;
    }


    public boolean verificarJogada(int linha, int coluna) {

        boolean resposta = false;

        for (int i = 0; i < jogo.length; i++) {

            for (int j = 0; j < jogo.length - 1; j++) {

                if (jogo[linha][coluna].equals(" ")) {
                    resposta = true;
                } else {
                    System.out.println("Linha ou coluna inválida, tente novamente");
                    break;
                }
            }
        }

        return resposta;

    }


    public void realizarJogada(String player, int linha, int coluna) {
        jogo[linha][coluna] = player;
    }


    public boolean verificarGanhador() {

        boolean resultado = false;
        int ganhadorHorizontal1 = 0;
        int ganhadorHorizontal2 = 0;
        int ganhadorVertical1 = 0;
        int ganhadorVertical2 = 0;

        String ganhador1 = "\n\tJogador 1 ganhou!!!\n";
        String ganhador2 = "\n\tJogador 2 ganhou!!!\n";


        /*VERIFICAÇÃO DIAGONAL*/

        if (jogo[0][0].equals("x") && jogo[1][1].equals("x") && jogo[2][2].equals("x")) {
            System.out.println(ganhador1);
            resultado = true;
        }

        if (jogo[0][2].equals("x") && jogo[1][1].equals("x") && jogo[2][0].equals("x")) {
            System.out.println(ganhador1);
            resultado = true;
        }


        if (jogo[0][0].equals("o") && jogo[1][1].equals("o") && jogo[2][2].equals("o")) {
            System.out.println(ganhador2);
            resultado = true;
        }

        if (jogo[0][2].equals("o") && jogo[1][1].equals("o") && jogo[2][0].equals("o")) {
            System.out.println(ganhador2);
            resultado = true;
        }

        /*FIM VERIFICAÇÃO DIAGONAL*/


        /*OUTRAS VERIFICAÇÕES*/
        for (int i = 0; i < jogo.length; i++) {
            for (int j = 0; j < jogo.length; j++) {

                /*VERIFICAÇÃO HORIZONTAL*/
                if (jogo[i][j].equals("x")) {
                    ganhadorHorizontal1++;
                    if (ganhadorHorizontal1 == 3) {
                        System.out.println(ganhador1);
                        resultado = true;
                        break;
                    }
                }

                if (jogo[i][j].equals("o")) {
                    ganhadorHorizontal2++;
                    if (ganhadorHorizontal2 == 3) {
                        System.out.println(ganhador2);
                        resultado = true;
                        break;
                    }
                }
                /*FIM VERIFICAÇÃO HORIZONTAL*/

                /*VERIFICAÇÃO VERTICAL*/

                if (jogo[j][i].equals("x")) {
                    ganhadorVertical1++;
                    if (ganhadorVertical1 == 3) {
                        System.out.println(ganhador1);
                        resultado = true;
                        break;
                    }
                }

                if (jogo[j][i].equals("o")) {
                    ganhadorVertical2++;
                    if (ganhadorVertical2 == 3) {
                        System.out.println(ganhador2);
                        resultado = true;
                        break;
                    }
                }

                /* FIM VERIFICAÇÃO VERTICAL*/
            }
            ganhadorHorizontal1 = 0;
            ganhadorHorizontal2 = 0;
            ganhadorVertical1 = 0;
            ganhadorVertical2 = 0;
        }
        /*FIM OUTRAS VERIFICAÇÕES*/
        return resultado;
    }


    public void gameOn() {

        int i = 1;
        int coluna = 0;
        int linha = 0;
        boolean ganhador = false;

        do {
            linha = escolhaDeLinha();
            coluna = escolhaDeColuna();

            while (!verificarJogada(linha, coluna)) {
                linha = escolhaDeLinha();
                coluna = escolhaDeColuna();
            }

            if (verificarJogada(linha, coluna)) {
                if (i % 2 != 0) {
                    realizarJogada("x", linha, coluna);
                } else {
                    realizarJogada("o", linha, coluna);
                }
            }

            imprimir();

            if (i >= 5) {
                ganhador = verificarGanhador();
            }

            i++;
            //System.out.println(i);
        } while (ganhador == false && i < 10);

        if(ganhador == false){

            System.out.println("Ninguem ganhou, deu velha");

        }



    }
}
