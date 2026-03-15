package domain;

public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private CategoriaProduto categoria;
    private double preco;
    private boolean ativo;

    public int getId() {
        return id;
    }

    public String getNome() {return nome;}

    public String getDescricao() {
        return descricao;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    public double getPreco() {
        return preco;
    }

    public boolean isAtivo() {return ativo;}

    public Produto(int id, String nome, CategoriaProduto categoria, double preco) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Erro.");
        }

        if (id <= 0){
            throw new IllegalArgumentException("ID do produto invalido.");
        }

        if (categoria == null) {
            throw new IllegalArgumentException("Categoria do produto invalida.");
        }
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço do produto deve ser maior que zero.");
        }

        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.preco = preco;
        this.ativo = true;
        this.id = id;

    }
}
