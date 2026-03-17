package service;

import domain.Estoque;
import repository.EstoqueRepository;

import java.util.List;

public class EstoqueService {

    private List<Estoque> estoqueProdutos;
    private EstoqueRepository estoqueRepository;

    public EstoqueService() {
        this.estoqueRepository = new EstoqueRepository();
        this.estoqueProdutos = estoqueRepository.carregar();
    }

    public void adicionarEstoque(int produtoId, int quantidade) {

        for (Estoque e : estoqueProdutos) {
            if (e.getProdutoId() == produtoId) {
                e.adicionarEstoque(quantidade);
                estoqueRepository.salvar(estoqueProdutos);
                return;
            }
        }

        Estoque novoEstoque = new Estoque(produtoId, quantidade);
        estoqueProdutos.add(novoEstoque);
        estoqueRepository.salvar(estoqueProdutos);
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
                return e.getQuantidade() >= quantidade;
            }
        }

        return false;
    }

    public void baixarEstoque(int produtoId, int quantidade) {
        for (Estoque e : estoqueProdutos) {
            if (e.getProdutoId() == produtoId) {
                e.removerEstoque(quantidade);
                estoqueRepository.salvar(estoqueProdutos);
                return;
            }
        }

        throw new IllegalArgumentException("Estoque do produto não encontrado.");
    }

    public void limparEstoque() {
        estoqueProdutos.clear();
        estoqueRepository.limpar();
    }

}