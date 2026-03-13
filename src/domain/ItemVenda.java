package domain;

public class ItemVenda {
    private int produtoId;
    private String nomeProdutoSnapshot;
    private double precoUnitarioSnapshot;
    private int quantidade;
    private double subtotal;

    public ItemVenda(int produtoId, String nomeProdutoSnapshot, double precoUnitarioSnapshot, int quantidade) {

        if (nomeProdutoSnapshot == null || nomeProdutoSnapshot.isBlank()) {
            throw new IllegalArgumentException("Nome do produto invalido.");
        }
        if (produtoId <= 0) {
            throw new IllegalArgumentException("ID de produto invalido.");
        }
        if (precoUnitarioSnapshot <= 0) {
            throw new IllegalArgumentException("Preço do produto invalido.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade do produto invalido.");
        }

        this.nomeProdutoSnapshot = nomeProdutoSnapshot;
        this.precoUnitarioSnapshot = precoUnitarioSnapshot;
        this.quantidade = quantidade;
        this.produtoId = produtoId;
        this.subtotal = precoUnitarioSnapshot * quantidade;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public String getNomeProdutoSnapshot() {
        return nomeProdutoSnapshot;
    }

    public double getPrecoUnitarioSnapshot() {
        return precoUnitarioSnapshot;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }
}
