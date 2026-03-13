package domain;

import java.time.LocalDateTime;
import java.util.List;

public class Venda {
    private int id;
    private LocalDateTime dataHora;
    private List<ItemVenda> itens;
    private FormaPagamento formaPagamento;
    private double valorTotal;

    public Venda(List<ItemVenda> itens, FormaPagamento formaPagamento) {

        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("Lista de itens da venda não pode ser nula ou vazia.");
        }
        if (formaPagamento == null) {
            throw new IllegalArgumentException("Forma de Pagamento da venda não pode ser nula ou vazia.");
        }

        this.itens = itens;
        this.formaPagamento = formaPagamento;
        this.dataHora = LocalDateTime.now();
        this.valorTotal = calcularTotal();
    }

    private double calcularTotal() {
        double total = 0;
        for (ItemVenda item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }
}
