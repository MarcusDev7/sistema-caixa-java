package repository;

import domain.Estoque;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueRepository {

    private static final String ARQUIVO = "estoque.csv";

    public void salvar(List<Estoque> estoques) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {

            for (Estoque estoque : estoques) {
                String linha = estoque.getProdutoId() + ";" + estoque.getQuantidade();
                bw.write(linha);
                bw.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar estoque no arquivo.", e);
        }
    }

    public List<Estoque> carregar() {
        List<Estoque> estoques = new ArrayList<>();
        File arquivo = new File(ARQUIVO);

        if (!arquivo.exists()) {
            return estoques;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                int produtoId = Integer.parseInt(partes[0]);
                int quantidade = Integer.parseInt(partes[1]);

                Estoque estoque = new Estoque(produtoId, quantidade);
                estoques.add(estoque);
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar estoque do arquivo.", e);
        }

        return estoques;
    }

    public void limpar() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            // abre o arquivo e sobrescreve vazio
        } catch (IOException e) {
            throw new RuntimeException("Erro ao limpar arquivo de estoque.", e);
        }
    }
}
