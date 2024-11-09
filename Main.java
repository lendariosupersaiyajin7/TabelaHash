public class Main {
    public static void main(String[] args) {
        String path = "nomes\\female_names.csv";

        TabelaHash hashTable = new TabelaHash();
        //hashTable.printTabela();
        long inicioInsercao = System.nanoTime();
        hashTable.loadDados(path);
        long fimInsercao = System.nanoTime();
        long inicioBusca = System.nanoTime();
        String chaveBusca = "Dione";
        String[] resultado = hashTable.buscar(chaveBusca);
        long fimBusca = System.nanoTime();

        if(resultado != null){
            System.out.println("------------------------------------");
            System.out.println("Nome: " + resultado[0] + "\nÍndice: " + hashTable.funcaoHash(chaveBusca));
            System.out.println("------------------------------------");
        } else {
            System.out.println("Nome não encontrado 😫");
        }


        hashTable.gerarRelatorio(fimInsercao - inicioInsercao, fimBusca - inicioBusca);
    }
}
