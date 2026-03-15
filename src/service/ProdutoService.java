package service;

import domain.Produto;
import domain.CategoriaProduto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService {

    private EstoqueService estoqueService;

    private int proximoId = 1;

    private List<Produto> produtos;

    public ProdutoService(EstoqueService estoqueService) {
        this.produtos = new ArrayList<>();
        this.estoqueService = estoqueService;
    }

    public void cadastrarProduto(String nome, CategoriaProduto categoria, double preco) {
        Produto produto = new Produto(proximoId, nome, categoria, preco);
        produtos.add(produto);
        proximoId++;
        System.out.println("PRODUTO CADASTRADO COM SUCESSO!");
        System.out.println("===================================");
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

        for (Produto p : produtos) {
            System.out.println("Nome: " + p.getNome());
            System.out.println("Categoria: " + p.getCategoria());
            System.out.println("Preço: " + p.getPreco());
            System.out.println("ID: " + p.getId());
            System.out.println("----------------------");
        }
    }

    public Produto buscarProdutoPorID(int id){

        for (Produto p : produtos){
            if (p.getId() == id){
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
}
