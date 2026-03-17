package ui;

import domain.*;
import service.EstoqueService;
import service.ProdutoService;
import service.VendaService;

import java.util.ArrayList;
import java.util.List;
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

            System.out.println("===== SISTEMA CAIXA =====");
            System.out.println("=== ESCOLHA UMA OPÇÃO ===");
            System.out.println("=========================");
            System.out.println("1 - CADASTRAR PRODUTO");
            System.out.println("2 - LISTAR PRODUTO");
            System.out.println("-------------------------");
            System.out.println("3 - ADICIONAR ESTOQUE");
            System.out.println("4 - VISUALIZAR ESTOQUE");
            System.out.println("-------------------------");
            System.out.println("5 - REALIZAR VENDA");
            System.out.println("6 - HISTORICO DE VENDAS");
            System.out.println("-------------------------");
            System.out.println("7 - REINICIAR ESTOQUE E VENDAS");
            System.out.println("-------------------------");
            System.out.println("0 - SAIR");
            System.out.println("=========================");

            System.out.print("DIGITE A OPÇÃO: "); opcao = Integer.parseInt(sc.nextLine());
            System.out.println("=========================");

            switch (opcao) {

                case 1:

                    System.out.println("ENTRANDO NO CADASTRO DE PRODUTOS...");
                    System.out.println("=========================");
                    System.out.print("NOME: ");
                    String nome = sc.nextLine();
                    System.out.println("=========================");

                    System.out.print("CATEGORIA (MARMITAS, SOBREMESAS, BEBIDAS, LANCHES, COMBOS, PORCAO): ");
                    CategoriaProduto categoria = CategoriaProduto.valueOf(sc.nextLine().toUpperCase());
                    System.out.println("=========================");

                    System.out.print("PREÇO: R$");
                    double preco = Double.parseDouble(sc.nextLine().replace(",", "."));
                    System.out.println("=========================");

                    produtoService.cadastrarProduto(nome, categoria, preco);
                    break;

                case 2:
                    produtoService.listarProdutos();
                    break;

                case 3:
                    System.out.println("DIGITE O ID E A QUANTIDADE");
                    System.out.println("=========================");
                    System.out.print("ID PRODUTO: ");
                    int idEstoque = Integer.parseInt(sc.nextLine());

                    Produto p = produtoService.buscarProdutoPorID(idEstoque);

                    if (p == null) {
                        System.out.println("PRODUTO NÃO ENCONTRADO");
                        break;
                    }

                    System.out.println("=========================");
                    System.out.print("QUANTIDADE: ");
                    int qtd = Integer.parseInt(sc.nextLine());

                    estoqueService.adicionarEstoque(idEstoque, qtd);
                    System.out.println("=========================");
                    System.out.println("ESTOQUE ADICIONADO");
                    System.out.println("=========================");
                    break;

                case 4:
                    produtoService.listarEstoque();
                    break;

                case 5:

                    System.out.println("===== NOVA VENDA =====");
                    System.out.println("=========================");

                    List<ItemVenda> itensVenda = new ArrayList<>();
                    int opcaoVenda = 1;

                    while (opcaoVenda == 1) {
                        System.out.println("DIGITE O ID");
                        System.out.println("=========================");
                        System.out.print("ID PRODUTO: ");
                        int idVenda = Integer.parseInt(sc.nextLine());
                        System.out.println("=========================");

                        Produto produtoVenda = produtoService.buscarProdutoPorID(idVenda);

                        if (produtoVenda == null) {
                            System.out.println("PRODUTO NÃO EXISTE");
                            continue;
                        }

                        System.out.println("=========================");
                        System.out.println("PRODUTO: " + produtoVenda.getNome());
                        System.out.println("=========================");
                        System.out.println("O PRODUTO ESTÁ CORRETO?");
                        System.out.println("-------------------------");
                        System.out.println("1 CONFIRMAR");
                        System.out.println("2 DIGITAR NOVAMENTE");
                        System.out.println("-------------------------");

                        System.out.print("DIGITE A OPÇÃO: ");
                        int confirmacao = Integer.parseInt(sc.nextLine());

                        if (confirmacao == 2) continue;

                        System.out.println("=========================");
                        System.out.print("QUANTIDADE: ");
                        int qtdVenda = Integer.parseInt(sc.nextLine());
                        System.out.println("=========================");

                        try {
                            ItemVenda item = vendaService.criarItemVenda(idVenda, qtdVenda);
                            itensVenda.add(item);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            continue;
                        }

                        double total = 0;

                        System.out.println("===== CARRINHO =====");

                        for (ItemVenda item : itensVenda) {
                            System.out.println("Produto: " + item.getNomeProdutoSnapshot());
                            System.out.println("Quantidade: " + item.getQuantidade());
                            System.out.printf("Subtotal: R$ %.2f%n", item.getSubtotal());
                            System.out.println("----------------------");

                            total += item.getSubtotal();
                        }

                        System.out.printf("TOTAL PARCIAL: R$ %.2f%n", total);
                        System.out.println("=========================");
                        System.out.println("1 ADICIONAR MAIS");
                        System.out.println("2 FINALIZAR");
                        System.out.println("=========================");

                        opcaoVenda = Integer.parseInt(sc.nextLine());
                    }

                    if (itensVenda.isEmpty()) {
                        System.out.println("VENDA CANCELADA");
                        break;
                    }

                    System.out.println("=========================");
                    System.out.println("FORMA PAGAMENTO:");
                    System.out.println("----------------------");
                    System.out.println("1- DINHEIRO");
                    System.out.println("2- PIX");
                    System.out.println("3- DEBITO");
                    System.out.println("4- CREDITO");
                    System.out.println("----------------------");

                    System.out.print("DIGITE A OPÇÃO: "); int opPg = Integer.parseInt(sc.nextLine());
                    System.out.println("=========================");

                    FormaPagamento formaPagamento = null;

                    switch (opPg) {
                        case 1: formaPagamento = FormaPagamento.DINHEIRO; break;
                        case 2: formaPagamento = FormaPagamento.PIX; break;
                        case 3: formaPagamento = FormaPagamento.CARTAO_DEBITO; break;
                        case 4: formaPagamento = FormaPagamento.CARTAO_CREDITO; break;
                    }

                    if (formaPagamento == null) {
                        System.out.println("PAGAMENTO INVALIDO");
                        break;
                    }

                    Venda venda = vendaService.finalizarVenda(itensVenda, formaPagamento);

                    System.out.println();
                    System.out.println("== VENDA FINALIZADA! ==");
                    System.out.println();
                    System.out.println("===== COMPROVANTE =====");

                    for (ItemVenda item : itensVenda) {
                        System.out.println(item.getNomeProdutoSnapshot() + " x" + item.getQuantidade());
                        System.out.printf("Subtotal: R$ %.2f%n", item.getSubtotal());
                    }

                    System.out.printf("TOTAL: R$ %.2f%n", venda.getValorTotal());
                    System.out.println("PAGAMENTO: " + formaPagamento);

                    System.out.println("===== COMPROVANTE =====");
                    System.out.println();
                    break;

                case 6:
                    vendaService.listarVendas();
                    break;

                case 7:
                    estoqueService.limparEstoque();
                    vendaService.limparVendas();
                    System.out.println("ESTOQUE E VENDAS REINICIADOS COM SUCESSO.");
                    break;

                case 0:
                    System.out.println("SAINDO...");
                    break;

                default:
                    System.out.println("OPÇÃO INVÁLIDA");
            }
        }
    }
}