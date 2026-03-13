package domain;

public class Estoque {
    private int produtoId;
    private int quantidade;

    public int getProdutoId() {
        return produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Estoque(int produtoId, int quantidade) {

        if (produtoId <= 0) {
            throw new IllegalArgumentException("ID do produto invalido.");
        }
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade de Estoque do produto invalida.");
        }

        this.produtoId = produtoId;
        this.quantidade = quantidade;


    }
}
