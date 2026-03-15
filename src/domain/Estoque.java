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

    public void adicionarEstoque(int quantidade){
        if (quantidade <= 0){
            throw new IllegalArgumentException("Quantidade para adicionar deve ser maior que zero.");
        }

        this.quantidade += quantidade;

    }

    public void removerEstoque(int quantidade){
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade para remover deve ser maior que zero.");
        }

        if (quantidade > this.quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente.");
        }

        this.quantidade -= quantidade;
    }
}
