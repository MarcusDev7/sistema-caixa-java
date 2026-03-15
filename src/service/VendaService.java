package service;

import domain.Produto;

public class VendaService {

    private ProdutoService produtoService;
    private EstoqueService estoqueService;

    public VendaService(ProdutoService produtoService, EstoqueService estoqueService) {
        this.produtoService = produtoService;
        this.estoqueService = estoqueService;
    }

    public void realizarVenda(int produtoId, int quantidade) {
        Produto produto = produtoService.buscarProdutoPorID(produtoId);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }

        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade inválida para venda.");
        }

        if (!estoqueService.temEstoqueSuficiente(produtoId, quantidade)) {
            throw new IllegalArgumentException("Estoque insuficiente.");
        }

        double total = produto.getPreco() * quantidade;

        estoqueService.baixarEstoque(produtoId, quantidade);

        System.out.println("VENDA REALIZADA COM SUCESSO!");
        System.out.println("Produto: " + produto.getNome());
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Total: R$ " + total);
        System.out.println("===================================");
    }
}
