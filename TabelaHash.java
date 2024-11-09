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

    // Método para imprimir o total de colisões
    public void printTotalColisoes() {
        System.out.println("\nTotal de colisões: " + totalColisoes);
    }
}
