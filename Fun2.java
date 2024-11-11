public class Fun2 extends TabelaAbstrata{
    
    public Fun2(int tamanho) {
        super(tamanho);
    }

    @Override
    public int funcaoHash(String chave) {
        int hash = 0;

        for(int i = 0; i < chave.length(); i++){
            hash = (31 * (hash + chave.charAt(i)) % tamanho);
        }
        return hash;
    }
    
}
