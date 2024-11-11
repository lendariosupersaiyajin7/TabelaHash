public class Fun1 extends TabelaAbstrata {

    public Fun1(int tamanho) {
        super(tamanho);
    }

    @Override
    public int funcaoHash(String chave) {
        int hash = 0;

        for(int i = 0; i < chave.length(); i++){
            hash = (25 * (hash + chave.charAt(i)) % tamanho);
        }
        return hash;
    }
    
    
}
