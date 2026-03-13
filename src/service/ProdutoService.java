package service;

import domain.Produto;
import domain.CategoriaProduto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService {

    private List<Produto> produtos;

    public ProdutoService() {
        produtos = new ArrayList<>();
    }

    public void cadastrarProduto(String nome, String descricao, CategoriaProduto categoria, double preco) {
        Produto produto = new Produto(nome, descricao, categoria, preco);
        produtos.add(produto);
        System.out.println("✅ PRODUTO CADASTRADO COM SUCESSO!");
        System.out.println("===================================");
    }

    public void listarProdutos() {
        if (produtos.isEmpty()){
            System.out.println("NENHUM PRODUTO CADASTRADO");
            System.out.println("=========================");
            System.out.println("RETORNANDO A TELA INICIAL...");
            System.out.println("=========================");
            return;}

            System.out.println("LISTA DE PRODUTOS:");

                for (Produto p : produtos) {
                    System.out.println("Nome: " + p.getNome());
                    System.out.println("Categoria: " + p.getCategoria());
                    System.out.println("Preço: " + p.getPreco());
                    System.out.println("----------------------");
                }
    }

}
