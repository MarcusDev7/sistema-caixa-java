package ui;

import domain.CategoriaProduto;
import service.ProdutoService;

import java.util.Scanner;

public class MenuPrincipal {

    private ProdutoService produtoService;

    public MenuPrincipal() {
        produtoService = new ProdutoService();
    }

    public void exibirMenu() {

        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("======= BEM-VINDO =======\n" + "===== SISTEMA CAIXA =====\n" + "=== ESCOLHA UMA OPÇÃO ===\n" + "=========================\n" + "1 - CADASTRAR PRODUTO\n" + "2 - LISTAR PRODUTO\n" + "0 - SAIR\n" + "=========================");
            opcao = Integer.parseInt(sc.nextLine());
            System.out.println("=========================");
            switch (opcao) {

                case 1:

                    System.out.println("ENTRANDO NO CADASTRO DE PRODUTOS...");
                    System.out.println("===================================");

                    System.out.print("DIGITE O NOME DO  PRODUTO: ");
                    String nome = sc.nextLine();
                    System.out.println("===================================");

                    System.out.print("DIGITE A DESCRIÇÃO DO  PRODUTO: ");
                    String descricao = sc.nextLine();
                    System.out.println("===================================");

                    System.out.print("DIGITE A CATEGORIA DO PRODUTO: (MARMITAS, SOBREMESAS, BEBIDAS, LANCHES, COMBOS, PORCAO): ");
                    CategoriaProduto categoria = CategoriaProduto.valueOf(sc.nextLine().toUpperCase());
                    System.out.println("===================================");

                    System.out.print("DIGITE O PREÇO DO PRODUTO: ");
                    double preco = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("===================================");

                    produtoService.cadastrarProduto(nome, descricao, categoria, preco);
                    break;

                case 2:

                    System.out.println("ENTRANDO NA LISTA DE PRODUTOS...");
                    System.out.println("=========================");
                    produtoService.listarProdutos();
                    break;

                case 0:
                    System.out.println("SAINDO DO SISTEMA...");
                    break;

                default:
                    System.out.println("OPÇÃO INVALIDA.");
            }

        }
    }
}
