package service;

import domain.FormaPagamento;
import domain.ItemVenda;
import domain.Produto;
import domain.Venda;
import repository.VendaRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class VendaService {

    private ProdutoService produtoService;
    private EstoqueService estoqueService;
    private VendaRepository vendaRepository;
    private List<Venda> vendas;
    private int proximoId;

    public VendaService(ProdutoService produtoService, EstoqueService estoqueService) {
        this.produtoService = produtoService;
        this.estoqueService = estoqueService;
        this.vendaRepository = new VendaRepository();
        this.vendas = vendaRepository.carregar();
        this.proximoId = gerarProximoId();
    }

    public ItemVenda criarItemVenda(int produtoId, int quantidade) {
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

        return new ItemVenda(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                quantidade
        );
    }

    public Venda finalizarVenda(List<ItemVenda> itens, FormaPagamento formaPagamento) {
        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("Venda sem itens.");
        }

        if (formaPagamento == null) {
            throw new IllegalArgumentException("Forma de pagamento inválida.");
        }

        for (ItemVenda item : itens) {
            estoqueService.baixarEstoque(item.getProdutoId(), item.getQuantidade());
        }

        Venda venda = new Venda(proximoId, itens, formaPagamento);
        vendas.add(venda);
        vendaRepository.salvar(vendas);
        proximoId++;

        return venda;
    }

    private int gerarProximoId() {
        int maiorId = 0;

        for (Venda venda : vendas) {
            if (venda.getId() > maiorId) {
                maiorId = venda.getId();
            }
        }

        return maiorId + 1;
    }

    public void listarVendas() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        if (vendas.isEmpty()) {
            System.out.println("NENHUMA VENDA REALIZADA.");
            System.out.println("=========================");
            return;
        }

        System.out.println("===== HISTORICO DE VENDAS =====");

        for (Venda venda : vendas) {

            System.out.println("ID VENDA: " + venda.getId());
            System.out.println("DATA: " + venda.getDataHora().format(formatter));
            System.out.println("FORMA PAGAMENTO: " + venda.getFormaPagamento());

            System.out.println("ITENS:");

            for (ItemVenda item : venda.getItens()) {
                System.out.println(
                        item.getNomeProdutoSnapshot() +
                                " x" + item.getQuantidade() +
                                " = R$ " + item.getSubtotal()
                );
            }

            System.out.println("TOTAL: R$ " + venda.getValorTotal());
            System.out.println("-------------------------------");
        }
    }

    public void limparVendas() {
        vendas.clear();
        vendaRepository.limpar();
        proximoId = 1;
    }
}