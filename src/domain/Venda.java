package domain;

import java.time.LocalDateTime;
import java.util.List;

public class Venda {
    private int id;
    private LocalDateTime dataHora;
    private List<ItemVenda> itens;
    private FormaPagamento formaPagamento;
    private double valorTotal;

    public Venda(int id, List<ItemVenda> itens, FormaPagamento formaPagamento) {
        this(id, LocalDateTime.now(), itens, formaPagamento);
    }

    public Venda(int id, LocalDateTime dataHora, List<ItemVenda> itens, FormaPagamento formaPagamento) {

        if (id <= 0) {
            throw new IllegalArgumentException("ID da venda inválido.");
        }

        if (dataHora == null) {
            throw new IllegalArgumentException("Data/hora da venda inválida.");
        }

        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("Lista de itens da venda não pode ser nula ou vazia.");
        }

        if (formaPagamento == null) {
            throw new IllegalArgumentException("Forma de pagamento da venda não pode ser nula.");
        }

        this.id = id;
        this.dataHora = dataHora;
        this.itens = itens;
        this.formaPagamento = formaPagamento;
        this.valorTotal = calcularTotal();
    }

    private double calcularTotal() {
        double total = 0;
        for (ItemVenda item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}