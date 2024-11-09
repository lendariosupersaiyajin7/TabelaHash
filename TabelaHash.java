import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TabelaHash {
    List<LinkedList<String[]>> tabela;
    int tamanho = 7927;
    int[] colisoes;  // Array para contar as colisões por índice
    int totalColisoes; // Variável para armazenar o número total de colisões

    public TabelaHash() {
        tabela = new ArrayList<>(tamanho);
        colisoes = new int[tamanho];  // Inicializa o array de colisões
        totalColisoes = 0;  // Inicializa o contador total de colisões
        for(int i = 0; i < tamanho; i++){
            tabela.add(new LinkedList<>());
            colisoes[i] = 0;  // Inicializa a contagem de colisões em zero
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

    private int funcaoHash(String chave) {
        int hash = 0;
        for (int i = 0; i < chave.length(); i++) {
            hash = (25 * hash + chave.charAt(i)) % tamanho;
        }
        return hash;
    }

    public void inserir(String[] dado) {
        String chave = dado[0];
        int index = funcaoHash(chave);

        // Verifica se já existe algum dado nesta posição (indica uma colisão)
        if (!tabela.get(index).isEmpty()) {
            colisoes[index]++;  // Incrementa a contagem de colisões por índice
            totalColisoes++;     // Incrementa o contador total de colisões
        }

        // Insere o novo dado na lista encadeada
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

    // Método para imprimir as colisões por índice
    public void printColisoes() {
        System.out.println("\nContagem de Colisões por Índice:");
        for (int i = 0; i < tamanho; i++) {
            if (colisoes[i] > 0) {
                System.out.println("Índice " + i + " - " + colisoes[i] + " colisões");
            }
        }
    }

    // Método para imprimir o total de colisões
    public void printTotalColisoes() {
        System.out.println("\nTotal de colisões: " + totalColisoes);
    }
}
