package repository;

import domain.FormaPagamento;
import domain.ItemVenda;
import domain.Venda;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VendaRepository {

    private static final String ARQUIVO_VENDAS = "vendas.csv";
    private static final String ARQUIVO_ITENS = "itens_venda.csv";

    public void salvar(List<Venda> vendas) {
        salvarVendas(vendas);
        salvarItens(vendas);
    }

    private void salvarVendas(List<Venda> vendas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_VENDAS))) {

            for (Venda venda : vendas) {
                String linha = venda.getId() + ";" +
                        venda.getDataHora() + ";" +
                        venda.getFormaPagamento() + ";" +
                        venda.getValorTotal();

                bw.write(linha);
                bw.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar vendas no arquivo.", e);
        }
    }

    private void salvarItens(List<Venda> vendas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_ITENS))) {

            for (Venda venda : vendas) {
                for (ItemVenda item : venda.getItens()) {
                    String linha = venda.getId() + ";" +
                            item.getProdutoId() + ";" +
                            item.getNomeProdutoSnapshot() + ";" +
                            item.getPrecoUnitarioSnapshot() + ";" +
                            item.getQuantidade() + ";" +
                            item.getSubtotal();

                    bw.write(linha);
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar itens da venda no arquivo.", e);
        }
    }

    public List<Venda> carregar() {
        List<Venda> vendas = new ArrayList<>();

        File arquivoVendas = new File(ARQUIVO_VENDAS);
        File arquivoItens = new File(ARQUIVO_ITENS);

        if (!arquivoVendas.exists() || !arquivoItens.exists()) {
            return vendas;
        }

        try (BufferedReader brVendas = new BufferedReader(new FileReader(ARQUIVO_VENDAS))) {
            String linhaVenda;

            while ((linhaVenda = brVendas.readLine()) != null) {
                String[] partesVenda = linhaVenda.split(";");

                int idVenda = Integer.parseInt(partesVenda[0]);
                LocalDateTime dataHora = LocalDateTime.parse(partesVenda[1]);
                FormaPagamento formaPagamento = FormaPagamento.valueOf(partesVenda[2]);

                List<ItemVenda> itensDaVenda = carregarItensPorVenda(idVenda);

                Venda venda = new Venda(idVenda, dataHora, itensDaVenda, formaPagamento);
                vendas.add(venda);
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar vendas do arquivo.", e);
        }

        return vendas;
    }

    private List<ItemVenda> carregarItensPorVenda(int idVenda) {
        List<ItemVenda> itens = new ArrayList<>();

        try (BufferedReader brItens = new BufferedReader(new FileReader(ARQUIVO_ITENS))) {
            String linhaItem;

            while ((linhaItem = brItens.readLine()) != null) {
                String[] partesItem = linhaItem.split(";");

                int idVendaItem = Integer.parseInt(partesItem[0]);

                if (idVendaItem == idVenda) {
                    int produtoId = Integer.parseInt(partesItem[1]);
                    String nomeProduto = partesItem[2];
                    double precoUnitario = Double.parseDouble(partesItem[3]);
                    int quantidade = Integer.parseInt(partesItem[4]);

                    ItemVenda item = new ItemVenda(produtoId, nomeProduto, precoUnitario, quantidade);
                    itens.add(item);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar itens da venda.", e);
        }

        return itens;
    }

    public void limpar() {
        try (BufferedWriter bwVendas = new BufferedWriter(new FileWriter(ARQUIVO_VENDAS));
             BufferedWriter bwItens = new BufferedWriter(new FileWriter(ARQUIVO_ITENS))) {
            // sobrescreve os dois arquivos vazios
        } catch (IOException e) {
            throw new RuntimeException("Erro ao limpar arquivos de vendas.", e);
        }
    }
}