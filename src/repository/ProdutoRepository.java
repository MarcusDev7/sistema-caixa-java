package repository;

import domain.CategoriaProduto;
import domain.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    private static final String ARQUIVO = "produtos.csv";

    public void salvar(List<Produto> produtos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Produto produto : produtos) {
                String linha = produto.getId() + ";" +
                        produto.getNome() + ";" +
                        produto.getCategoria() + ";" +
                        produto.getPreco() + ";" +
                        produto.isAtivo();

                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar produtos no arquivo.", e);
        }

    }

    public List<Produto> carregar() {
        List<Produto> produtos = new ArrayList<>();
        File arquivo = new File(ARQUIVO);

        if (!arquivo.exists()) {
            return produtos;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                int id = Integer.parseInt(partes[0]);
                String nome = partes[1];
                CategoriaProduto categoria = CategoriaProduto.valueOf(partes[2]);
                double preco = Double.parseDouble(partes[3]);
                boolean ativo = Boolean.parseBoolean(partes[4]);

                Produto produto = new Produto(id, nome, categoria, preco, ativo);

                produtos.add(produto);
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar produtos do arquivo.", e);
        }

        return produtos;
    }
}