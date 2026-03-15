package ui;

import domain.CategoriaProduto;
import domain.Produto;
import service.EstoqueService;
import service.ProdutoService;
import service.VendaService;

import java.util.Scanner;

public class MenuPrincipal {

    private ProdutoService produtoService;
    private EstoqueService estoqueService;
    private VendaService vendaService;

    public MenuPrincipal() {
        estoqueService = new EstoqueService();
        produtoService = new ProdutoService(estoqueService);
        vendaService = new VendaService(produtoService, estoqueService);
    }

    public void exibirMenu() {

        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("======= BEM-VINDO =======\n" + "===== SISTEMA CAIXA =====\n" + "=== ESCOLHA UMA OPÇÃO ===\n" + "=========================\n" + "1 - CADASTRAR PRODUTO\n" + "2 - LISTAR PRODUTO\n" + "3 - ADICIONAR ESTOQUE\n" +  "4 - VISUALIZAR ESTOQUE\n" +  "5 - REALIZAR VENDA\n" + "0 - SAIR\n" + "=========================");
            opcao = Integer.parseInt(sc.nextLine());
            System.out.println("=========================");
            switch (opcao) {

                case 1:

                    System.out.println("ENTRANDO NO CADASTRO DE PRODUTOS...");
                    System.out.println("===================================");

                    System.out.print("DIGITE O NOME DO  PRODUTO: ");
                    String nome = sc.nextLine();
                    System.out.println("===================================");

                    System.out.print("DIGITE A CATEGORIA DO PRODUTO: (MARMITAS, SOBREMESAS, BEBIDAS, LANCHES, COMBOS, PORCAO): ");
                    CategoriaProduto categoria = CategoriaProduto.valueOf(sc.nextLine().toUpperCase());
                    System.out.println("===================================");

                    System.out.print("DIGITE O PREÇO DO PRODUTO: ");
                    double preco = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("===================================");

                    produtoService.cadastrarProduto(nome, categoria, preco);
                    break;

                case 2:
                    System.out.println("ENTRANDO NA LISTA DE PRODUTOS...");
                    System.out.println("=========================");
                    produtoService.listarProdutos();
                    break;

                case 3:
                    System.out.println("ENTRANDO NA ENTRADA DE ESTOQUE...");
                    System.out.println("===================================");
                    System.out.print("DIGITE O ID DO PRODUTO: ");
                    int produtoId = Integer.parseInt(sc.nextLine());
                    System.out.println("===================================");
                    Produto produtoEncontrado = produtoService.buscarProdutoPorID(produtoId);

                    if (produtoEncontrado ==  null){
                        System.out.println("PRODUTO NÃO ENCONTRADO.");
                        System.out.println("===================================");
                        break;
                    }

                    System.out.print("DIGITE A QUANTIDADE PARA ADICIONAR: ");
                    int quantidade = Integer.parseInt(sc.nextLine());
                    System.out.println("===================================");

                    estoqueService.adicionarEstoque(produtoId, quantidade);
                    System.out.println("ESTOQUE ADICIONADO COM SUCESSO!");
                    System.out.println("===================================");
                    break;

                case 4:
                    System.out.println("VISUALIZANDO ESTOQUE...");
                    produtoService.listarEstoque();


                case 5:
                    System.out.println("ENTRANDO NO FLUXO DE VENDA...");
                    System.out.println("===================================");

                    System.out.print("DIGITE O ID DO PRODUTO: ");
                    int produtoIdVenda = Integer.parseInt(sc.nextLine());
                    System.out.println("===================================");

                    System.out.print("DIGITE A QUANTIDADE PARA VENDER: ");
                    int quantidadeVenda = Integer.parseInt(sc.nextLine());
                    System.out.println("===================================");

                    vendaService.realizarVenda(produtoIdVenda, quantidadeVenda);
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
