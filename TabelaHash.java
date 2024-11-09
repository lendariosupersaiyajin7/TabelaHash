import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TabelaHash {
    List<LinkedList<String[]>> tabela;
    int tamanho = 7927;
    int[] colisoes;
    int totalColisoes;

    public TabelaHash() {
        tabela = new ArrayList<>(tamanho);
        colisoes = new int[tamanho];
        totalColisoes = 0;
        for(int i = 0; i < tamanho; i++){
            tabela.add(new LinkedList<>());
            colisoes[i] = 0;
        }
    }

    public void loadDados(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dadosLinha = linha.split(", ");
                inserir(dadosLinha);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    int funcaoHash(String chave) {
        int hash = 0;
        for (int i = 0; i < chave.length(); i++) {
            hash = (25 * hash + chave.charAt(i)) % tamanho;
        }
        return hash;
    }

    public void inserir(String[] dado) {
        String chave = dado[0];
        int index = funcaoHash(chave);

        // Verifica se há colisão
        if (!tabela.get(index).isEmpty()) {
            colisoes[index]++;
            totalColisoes++;
        }

        tabela.get(index).add(dado);
    }

    public String[] buscar(String chave) {
        int index = funcaoHash(chave);

        for (String[] dado : tabela.get(index)) {
            if (dado[0].equals(chave)) {
                return dado;
            }
        }
        return null;
    }

    public void printTabela() {
        for (int i = 0; i < tamanho; i++) {
            System.out.print("Índice: " + i + " - ");
            System.out.println("Colisões: " + colisoes[i]);
            for (String[] dado : tabela.get(i)) {
                System.out.print(dado[0] + " ");
            }
            System.out.println();
        }
    }

    // Método para gerar relatório com colisões, tempos e distribuição das chaves
    public void gerarRelatorio(long tempoInsercao, long tempoBusca) {
        System.out.println("\n===== RELATÓRIO =====");
        System.out.println("\nTotal de colisões: " + totalColisoes);
        System.out.println("\nTempo de inserção total (em nanossegundos): " + tempoInsercao);
        System.out.println("Tempo de busca total (em nanossegundos): " + tempoBusca);
        System.out.println("Tempo de inserção total (em milissegundos): " + tempoInsercao / 1_000_000.0);
        System.out.println("Tempo de busca total (em milissegundos): " + tempoBusca / 1_000_000.0);


        System.out.println("\nNúmero de colisões por índice:");
        for (int i = 0; i < tamanho; i++) {
            if (colisoes[i] > 0) {
                System.out.println("Índice: " + i + " - Colisões: " + colisoes[i]);
            }
        }

        System.out.println("\nDistribuição das chaves (quantidade de chaves em cada posição):");
        for (int i = 0; i < tamanho; i++) {
            if (!tabela.get(i).isEmpty()) {
                System.out.println("Índice " + i + " - " + tabela.get(i).size() + " chaves");
            }
        }


    }
}
