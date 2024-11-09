public class Main {
    public static void main(String[] args) {
        String path = "nomes\\female_names.csv";

        TabelaHash hashTable = new TabelaHash();
        hashTable.loadDados(path);
        hashTable.printTabela();
        hashTable.printTotalColisoes();

        String chave = "Johnna";
        String[] resultado = hashTable.buscar(chave);
        if(resultado != null){
            System.out.println("Nome: " + resultado[0]);
        } else {
            System.out.println("nome não encontrado 😫");
        }

    }
}