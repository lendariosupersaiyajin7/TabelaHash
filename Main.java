public class Main {
    public static void main(String[] args) {
        String path = "nomes\\female_names.csv";

        TabelaHash hashTable = new TabelaHash();
        hashTable.loadDados(path);
        hashTable.printTabela();
        hashTable.printTotalColisoes();

        String chave = "Dione";
        String[] resultado = hashTable.buscar(chave);
        if(resultado != null){
            System.out.println("------------------------------------");
            System.out.println("Nome: " + resultado[0] + "\nÍndice: " + hashTable.funcaoHash(chave));
            System.out.println("------------------------------------");
        } else {
            System.out.println("nome não encontrado 😫");
        }

    }
}