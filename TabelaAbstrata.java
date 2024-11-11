import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class TabelaAbstrata {
    List<LinkedList<String[]>> tabela;
    int tamanho;
    int[] colisoes;
    int totalColisoes;

    public TabelaAbstrata(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new ArrayList<>(tamanho);
        colisoes = new int[tamanho];
        totalColisoes = 0;

        for(int i = 0; i < tamanho; i++){
            tabela.add(new LinkedList<String[]>());
        }
    }

    public abstract int funcaoHash(String chave);


    public void inserir(String[] dado) {
        String chave = dado[0];
        int indice = funcaoHash(chave);

        if(!tabela.get(indice).isEmpty()){
            colisoes[indice]++;
            totalColisoes++;
        }
        tabela.get(indice).add(dado);
    }

    public String[] buscar(String chave) {
        int indice = funcaoHash(chave);

        for(String[] dado : tabela.get(indice)){
            if(dado[0].equals(chave)){
                return dado;
            }
        }
        return null;
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


    public void printTabela() {
        System.out.print("\n===== TABELA HASH =====\n");
        for (int i = 0; i < tamanho; i++) {
            if (!tabela.get(i).isEmpty()) {
                System.out.print("[" + i + ": ");
                for (String[] dado : tabela.get(i)) {
                    System.out.print("{" + String.join(", ", dado) + "} ");
                }
                System.out.print("] ");
            }
        }
        System.out.println();
    }


    public void gerarRelatorio(long tempoInsercao, long tempoBusca) {
        System.out.printf("\n===== RELATÓRIO =====\nTotal Colisões: %d | Inserção: %.2f ms | Busca: %.2f ms\n\n",
                          totalColisoes, tempoInsercao / 1_000_000.0, tempoBusca / 1_000_000.0);
    
        System.out.print("Índices com colisões: ");
        for (int i = 0; i < tamanho; i++) {
            if (colisoes[i] > 0) {
                System.out.print("[" + i + ": " + colisoes[i] + "] ");
            }
        }
        System.out.println();
    
        System.out.print("Distribuição das chaves: ");
        for (int i = 0; i < tamanho; i++) {
            if (!tabela.get(i).isEmpty()) {
                System.out.print("[" + i + ": " + tabela.get(i).size() + "] ");
            }
        }
        System.out.println();
    }
    
    
}
