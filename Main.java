import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = "nomes\\female_names.csv";
        int tamanho = 7927;

        Fun1 tabela = new Fun1(tamanho);
        Fun2 tabela2 = new Fun2(tamanho);
        String chave = "Dione";
        int index = tabela.funcaoHash(chave);


        long inicioInsercao1 = System.nanoTime();
        tabela.loadDados(path);
        long fimInsercao1 = System.nanoTime();
        long tempoInsercao1 = fimInsercao1 - inicioInsercao1;

        long inicioBusca1 = System.nanoTime();
        String[] resultado1 = tabela.buscar(chave);
        long fimBusca1 = System.nanoTime();
        long tempoBusca1 = fimBusca1 - inicioBusca1;


        long inicioInsercao2 = System.nanoTime();
        tabela2.loadDados(path);
        long fimInsercao2 = System.nanoTime();
        long tempoInsercao2 = fimInsercao2 - inicioInsercao2;

        long inicioBusca2 = System.nanoTime();
        String[] resultado2 = tabela2.buscar(chave);
        long fimBusca2 = System.nanoTime();
        long tempoBusca2 = fimBusca2 - inicioBusca2;
        
        while(true){
            System.out.println("\n===== TDE 3 =====\n");
            System.out.println("1. imprimir tabela (FUNÇÃO HASH 1)");
            System.out.println("2. mostrar relatório (FUNÇÃO HASH 1)");
            System.out.println("3. buscar chave (FUNÇÃO HASH 1)");
            System.out.println("4. imprimir tabela (FUNÇÃO HASH 2)");
            System.out.println("5. mostar relatório (FUNÇÃO HASH 2)");
            System.out.println("6. buscar chave (FUNÇÃO HASH 2)");
            System.out.println("7. encerrar programa");

            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                tabela.printTabela();
                break;

                case 2:
                tabela.gerarRelatorio(tempoInsercao1, tempoBusca1);
                break;

                case 3:
                if(resultado1 != null){
                    System.out.println("Tabela Hash Nº 1 - NOME ENCONTRADO: " + resultado1[0] + "| INDICE: " + index);
                    break;
                } else {
                    System.out.println("Tabela Hash Nº 1 - NOME NÃO ENCONTRADO");
                    break;
                }

                case 4:
                tabela2.printTabela();
                break;

                case 5:
                tabela2.gerarRelatorio(tempoInsercao2, tempoBusca2);
                break;

                case 6:
                if(resultado2 != null){
                    System.out.println("Tabela Hash Nº 1 - NOME ENCONTRADO: " + resultado1[0] + "| INDICE: " + index);
                    break;
                } else {
                    System.out.println("Tabela Hash Nº 1 - NOME NÃO ENCONTRADO");
                    break;
                }

                case 7:
                System.out.println("programa encerrado");
                sc.close();
                return;
            }

        }

    }
}