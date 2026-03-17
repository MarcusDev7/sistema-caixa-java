package service;

import domain.Produto;
import domain.CategoriaProduto;
import repository.ProdutoRepository;

import java.util.List;

public class ProdutoService {

    private EstoqueService estoqueService;
    private ProdutoRepository produtoRepository;
    private int proximoId;
    private List<Produto> produtos;

    public ProdutoService(EstoqueService estoqueService) {
        this.produtoRepository = new ProdutoRepository();
        this.produtos = produtoRepository.carregar();
        this.proximoId = gerarProximoId();
        this.estoqueService = estoqueService;
    }

    public void cadastrarProduto(String nome, CategoriaProduto categoria, double preco) {
        Produto produto = new Produto(proximoId, nome, categoria, preco);
        produtos.add(produto);
        produtoRepository.salvar(produtos);
        proximoId++;

        System.out.println("PRODUTO CADASTRADO!");
        System.out.println("=========================");
        System.out.println();
    }

    public void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("NENHUM PRODUTO CADASTRADO");
            System.out.println("=========================");
            System.out.println("RETORNANDO A TELA INICIAL...");
            System.out.println("=========================");
            return;
        }

        System.out.println("LISTA DE PRODUTOS:");
        System.out.println("=========================");

        for (Produto p : produtos) {
            System.out.println("Nome: " + p.getNome());
            System.out.println("Categoria: " + p.getCategoria());
            System.out.println("Preço: R$" + p.getPreco());
            System.out.println("ID: " + p.getId());
            System.out.println("=========================");
        }
    }

    public Produto buscarProdutoPorID(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void listarEstoque() {
        if (produtos.isEmpty()) {
            System.out.println("NENHUM PRODUTO CADASTRADO");
            return;
        }

        System.out.println("=== ESTOQUE ATUAL ===");

        for (Produto p : produtos) {
            int quantidade = estoqueService.buscarQuantidadePorProdutoId(p.getId());

            System.out.println("Produto: " + p.getNome());
            System.out.println("Quantidade: " + quantidade);
            System.out.println("----------------------");
        }
    }

    private int gerarProximoId() {
        int maiorId = 0;

        for (Produto produto : produtos) {
            if (produto.getId() > maiorId) {
                maiorId = produto.getId();
            }
        }

        return maiorId + 1;
    }
}