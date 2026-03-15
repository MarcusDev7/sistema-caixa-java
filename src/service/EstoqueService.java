package service;

import domain.Estoque;

import java.util.ArrayList;
import java.util.List;

public class EstoqueService {

    private List<Estoque> estoqueProdutos;

    public EstoqueService() {
        estoqueProdutos = new ArrayList<>();
    }

    public void adicionarEstoque(int produtoId, int quantidade) {

        for (Estoque e : estoqueProdutos) {
            if (e.getProdutoId() == produtoId) {
                e.adicionarEstoque(quantidade);
                return;
            }
        }

        Estoque novoEstoque = new Estoque(produtoId, quantidade);
        estoqueProdutos.add(novoEstoque);
    }

    public int buscarQuantidadePorProdutoId(int produtoId) {

        for (Estoque e : estoqueProdutos) {
            if (e.getProdutoId() == produtoId) {
                return e.getQuantidade();
            }
        }

        return 0;
    }

    public boolean temEstoqueSuficiente(int produtoId, int quantidade) {
        for (Estoque e : estoqueProdutos) {
            if (e.getProdutoId() == produtoId) {
                if (e.getQuantidade() == quantidade) {
                    if (e.getQuantidade() >= quantidade) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public void baixarEstoque(int produtoId, int quantidade){
        for (Estoque e : estoqueProdutos){
            if (e.getQuantidade() >= quantidade) {
                e.removerEstoque(quantidade);
                return;
            }

            throw new IllegalArgumentException("invalido.");

        }
    }
}
