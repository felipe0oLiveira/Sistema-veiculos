package veiculos;

import java.util.Scanner;

/**
 * Aplicacao principal para gerenciar veiculos (avioes e navios)
 * Utiliza vetores de objetos e tratamento de excecoes
 * 
 * @author Jonathas Felipe
 * @version 1.0
 */
public class AplicacaoVeiculos {
    private static final int TAMANHO_VETOR = 20; // 10 avioes + 10 navios
    private static Veiculo[] veiculos = new Veiculo[TAMANHO_VETOR];
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("= SISTEMA DE GERENCIAMENTO DE VEICULOS =");
        System.out.println("Inicializando sistema...\n");
        
        try {
            inicializarVeiculos();
            exibirMenu();
        } catch (Exception e) {
            System.err.println("Erro critico na aplicacao: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
    
    /**
     * Metodo auxiliar para leitura de entrada que funciona tanto em console quanto em IDE
     * @return String lida do teclado
     */
    private static String lerEntrada() {
        try {
            // Tenta usar System.console() primeiro (funciona em terminal real)
            if (System.console() != null) {
                return lerEntrada();
            }
        } catch (Exception e) {
            // Se falhar, usa Scanner como fallback
        }
        
        // Fallback para Scanner (funciona em IDEs)
        return scanner.nextLine();
    }
    
    /**
     * Inicializa o vetor com 10 avioes e 10 navios usando construtores vazios
     */
    private static void inicializarVeiculos() {
        System.out.println("Inicializando veiculos...");
        
        // Criando 10 avioes
        for (int i = 0; i < 10; i++) {
            veiculos[i] = new Aviao();
        }
        
        // Criando 10 navios
        for (int i = 10; i < 20; i++) {
            veiculos[i] = new Navio();
        }
        
        System.out.println("20 veiculos inicializados com sucesso!");
        System.out.println("- 10 Avioes");
        System.out.println("- 10 Navios\n");
    }
    
    /**
     * Exibe o menu principal da aplicacao
     */
    private static void exibirMenu() {
        int opcao = -1;
        
        do {
            System.out.println("MENU PRINCIPAL");
            System.out.println("1. Cadastrar dados dos avioes");
            System.out.println("2. Cadastrar dados dos navios");
            System.out.println("3. Exibir todos os avioes");
            System.out.println("4. Exibir todos os navios");
            System.out.println("5. Exibir todos os veiculos");
            System.out.println("6. Reajustar precos dos avioes");
            System.out.println("7. Calcular passageiros por tripulantes (navios)");
            System.out.println("8. Estatisticas gerais");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            
            try {
                String input = lerEntrada();
                if (input.trim().isEmpty()) {
                    System.out.println("Opcao invalida! Tente novamente.\n");
                    continue;
                }
                opcao = Integer.parseInt(input);
                
                switch (opcao) {
                    case 1:
                        cadastrarAvioes();
                        break;
                    case 2:
                        cadastrarNavios();
                        break;
                    case 3:
                        exibirAvioes();
                        break;
                    case 4:
                        exibirNavios();
                        break;
                    case 5:
                        exibirTodosVeiculos();
                        break;
                    case 6:
                        reajustarPrecosAvioes();
                        break;
                    case 7:
                        calcularPassageirosPorTripulantes();
                        break;
                    case 8:
                        exibirEstatisticas();
                        break;
                    case 0:
                        System.out.println("Encerrando aplicacao...");
                        break;
                    default:
                        System.out.println("Opcao invalida! Tente novamente.\n");
                }
            } catch (NumberFormatException e) {
                System.err.println("Erro: Digite apenas numeros para as opcoes do menu.\n");
                opcao = -1;
            } catch (Exception e) {
                System.err.println("Erro inesperado: " + e.getMessage() + "\n");
                opcao = -1;
            }
            
        } while (opcao != 0);
    }
    
    /**
     * Cadastra dados para todos os avioes
     */
    private static void cadastrarAvioes() {
        System.out.println("\n=== CADASTRO DE AVIOES ===");
        
        for (int i = 0; i < 10; i++) {
            System.out.println("\n--- Aviao " + (i + 1) + " ---");
            cadastrarAviao(i);
            
            // Perguntar se quer continuar ou voltar ao menu
            if (i < 9) { // Não perguntar no último avião
                System.out.println("\nDeseja continuar cadastrando avioes?");
                System.out.println("1. Sim - Próximo avião");
                System.out.println("2. Não - Voltar ao menu principal");
                System.out.print("Escolha uma opção: ");
                
                try {
                    String opcao = lerEntrada();
                    if (opcao.equals("2")) {
                        System.out.println("Retornando ao menu principal...\n");
                        return;
                    }
                } catch (Exception e) {
                    System.out.println("Opção inválida. Continuando com o próximo avião...\n");
                }
            }
        }
        
        System.out.println("\nCadastro de avioes concluido!\n");
    }
    
    /**
     * Cadastra dados para um aviao especifico
     */
    private static void cadastrarAviao(int indice) {
        try {
            System.out.println("=== ENTRADA DE DADOS - AVIAO ===");
            
            System.out.print("Digite o prefixo do aviao: ");
            String prefixo = lerEntrada();
            
            System.out.print("Digite a capacidade do tanque (litros): ");
            String capacidadeStr = lerEntrada();
            int capacidade = Integer.parseInt(capacidadeStr);
            if (capacidade < 0) {
                throw new IllegalArgumentException("Capacidade do tanque nao pode ser negativa");
            }
            
            System.out.print("Digite o numero de passageiros: ");
            String passageirosStr = lerEntrada();
            int passageiros = Integer.parseInt(passageirosStr);
            if (passageiros < 0) {
                throw new IllegalArgumentException("Numero de passageiros nao pode ser negativo");
            }
            
            System.out.print("Digite o preco do aviao: ");
            String precoStr = lerEntrada();
            double preco = Double.parseDouble(precoStr);
            if (preco < 0) {
                throw new IllegalArgumentException("Preco nao pode ser negativo");
            }
            
            System.out.print("Digite a data da ultima revisao: ");
            String dataRevisao = lerEntrada();
            
            // Criar novo aviao com os dados fornecidos
            veiculos[indice] = new Aviao(prefixo, capacidade, passageiros, preco, dataRevisao);
            
            System.out.println("Dados do aviao cadastrados com sucesso!");
            
        } catch (NumberFormatException e) {
            System.err.println("Erro: Entrada invalida. Digite apenas numeros para os campos numericos.");
            System.err.println("Detalhes: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }
    
    /**
     * Cadastra dados para todos os navios
     */
    private static void cadastrarNavios() {
        System.out.println("\n=== CADASTRO DE NAVIOS ===");
        
        for (int i = 10; i < 20; i++) {
            System.out.println("\n--- Navio " + (i - 9) + " ---");
            cadastrarNavio(i);
            
            // Perguntar se quer continuar ou voltar ao menu
            if (i < 19) { // Não perguntar no último navio
                System.out.println("\nDeseja continuar cadastrando navios?");
                System.out.println("1. Sim - Próximo navio");
                System.out.println("2. Não - Voltar ao menu principal");
                System.out.print("Escolha uma opção: ");
                
                try {
                    String opcao = lerEntrada();
                    if (opcao.equals("2")) {
                        System.out.println("Retornando ao menu principal...\n");
                        return;
                    }
                } catch (Exception e) {
                    System.out.println("Opção inválida. Continuando com o próximo navio...\n");
                }
            }
        }
        
        System.out.println("\nCadastro de navios concluido!\n");
    }
    
    /**
     * Cadastra dados para um navio especifico
     */
    private static void cadastrarNavio(int indice) {
        try {
            System.out.println("=== ENTRADA DE DADOS - NAVIO ===");
            
            System.out.print("Digite o nome do navio: ");
            String nome = lerEntrada();
            
            System.out.print("Digite a capacidade do tanque (litros): ");
            String capacidadeStr = lerEntrada();
            int capacidade = Integer.parseInt(capacidadeStr);
            if (capacidade < 0) {
                throw new IllegalArgumentException("Capacidade do tanque nao pode ser negativa");
            }
            
            System.out.print("Digite o numero de passageiros: ");
            String passageirosStr = lerEntrada();
            int passageiros = Integer.parseInt(passageirosStr);
            if (passageiros < 0) {
                throw new IllegalArgumentException("Numero de passageiros nao pode ser negativo");
            }
            
            System.out.print("Digite o numero de tripulantes: ");
            String tripulantesStr = lerEntrada();
            int tripulantes = Integer.parseInt(tripulantesStr);
            if (tripulantes < 0) {
                throw new IllegalArgumentException("Numero de tripulantes nao pode ser negativo");
            }
            
            System.out.print("Digite o preco do navio: ");
            String precoStr = lerEntrada();
            double preco = Double.parseDouble(precoStr);
            if (preco < 0) {
                throw new IllegalArgumentException("Preco nao pode ser negativo");
            }
            
            System.out.print("Digite a data de lancamento: ");
            String dataLancamento = lerEntrada();
            
            // Criar novo navio com os dados fornecidos
            veiculos[indice] = new Navio(nome, capacidade, passageiros, tripulantes, preco, dataLancamento);
            
            System.out.println("Dados do navio cadastrados com sucesso!");
            
        } catch (NumberFormatException e) {
            System.err.println("Erro: Entrada invalida. Digite apenas numeros para os campos numericos.");
            System.err.println("Detalhes: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }
    
    /**
     * Exibe dados de todos os avioes
     */
    private static void exibirAvioes() {
        System.out.println("\n=== RELATORIO DE AVIOES ===");
        
        for (int i = 0; i < 10; i++) {
            System.out.println("\n--- Aviao " + (i + 1) + " ---");
            veiculos[i].imprimir();
        }
        
        System.out.println("\n");
    }
    
    /**
     * Exibe dados de todos os navios
     */
    private static void exibirNavios() {
        System.out.println("\n=== RELATORIO DE NAVIOS ===");
        
        for (int i = 10; i < 20; i++) {
            System.out.println("\n--- Navio " + (i - 9) + " ---");
            veiculos[i].imprimir();
        }
        
        System.out.println("\n");
    }
    
    /**
     * Exibe dados de todos os veiculos
     */
    private static void exibirTodosVeiculos() {
        System.out.println("\n=== RELATORIO GERAL DE VEICULOS ===");
        
        System.out.println("\n--- AVIOES ---");
        for (int i = 0; i < 10; i++) {
            System.out.println("\nAviao " + (i + 1) + ":");
            veiculos[i].imprimir();
        }
        
        System.out.println("\n--- NAVIOS ---");
        for (int i = 10; i < 20; i++) {
            System.out.println("\nNavio " + (i - 9) + ":");
            veiculos[i].imprimir();
        }
        
        System.out.println("\n");
    }
    
    /**
     * Reajusta precos de todos os avioes
     */
    private static void reajustarPrecosAvioes() {
        try {
            System.out.print("\nDigite o percentual de reajuste para os avioes: ");
            String percentualStr = lerEntrada();
            double percentual = Double.parseDouble(percentualStr);
            
            System.out.println("\n= REAJUSTE DE PRECOS - AVIOES =");
            
            for (int i = 0; i < 10; i++) {
                System.out.println("\nReajustando aviao " + (i + 1) + ":");
                veiculos[i].reajustarPreco(percentual);
            }
            
            System.out.println("\nReajuste concluido!\n");
            
        } catch (NumberFormatException e) {
            System.err.println("Erro: Digite um valor numerico valido para o percentual.\n");
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage() + "\n");
        }
    }
    
    
    /**
     * Calcula e exibe a relacao passageiros por tripulantes para todos os navios
     */
    private static void calcularPassageirosPorTripulantes() {
        System.out.println("\n=== RELACAO PASSAGEIROS POR TRIPULANTES - NAVIOS ===");
        
        for (int i = 10; i < 20; i++) {
            Navio navio = (Navio) veiculos[i];
            System.out.println("\nNavio " + (i - 9) + " (" + navio.getNome() + "):");
            double relacao = navio.passageirosPorTripulantes();
            System.out.println("Relacao: " + String.format("%.2f", relacao) + " passageiros por tripulante");
        }
        
        System.out.println("\n");
    }
    
    /**
     * Exibe estatisticas gerais do sistema
     */
    private static void exibirEstatisticas() {
        System.out.println("\n=== ESTATISTICAS GERAIS ===");
        
        // Estatisticas dos avioes
        double totalPrecoAvioes = 0;
        int totalPassageirosAvioes = 0;
        int totalCapacidadeAvioes = 0;
        
        for (int i = 0; i < 10; i++) {
            totalPrecoAvioes += veiculos[i].getPreco();
            totalPassageirosAvioes += veiculos[i].getNumeroPassageiros();
            totalCapacidadeAvioes += veiculos[i].getCapacidadeTanque();
        }
        
        // Estatisticas dos navios
        double totalPrecoNavios = 0;
        int totalPassageirosNavios = 0;
        int totalCapacidadeNavios = 0;
        int totalTripulantes = 0;
        
        for (int i = 10; i < 20; i++) {
            totalPrecoNavios += veiculos[i].getPreco();
            totalPassageirosNavios += veiculos[i].getNumeroPassageiros();
            totalCapacidadeNavios += veiculos[i].getCapacidadeTanque();
            totalTripulantes += ((Navio) veiculos[i]).getNumeroTripulantes();
        }
        
        // Exibindo estatisticas
        System.out.println("--- AVIOES ---");
        System.out.println("Total de avioes: 10");
        System.out.println("Preco total: R$ " + String.format("%.2f", totalPrecoAvioes));
        System.out.println("Preco medio: R$ " + String.format("%.2f", totalPrecoAvioes / 10));
        System.out.println("Total de passageiros: " + totalPassageirosAvioes);
        System.out.println("Capacidade total de tanque: " + totalCapacidadeAvioes + " litros");
        
        System.out.println("\n--- NAVIOS ---");
        System.out.println("Total de navios: 10");
        System.out.println("Preco total: R$ " + String.format("%.2f", totalPrecoNavios));
        System.out.println("Preco medio: R$ " + String.format("%.2f", totalPrecoNavios / 10));
        System.out.println("Total de passageiros: " + totalPassageirosNavios);
        System.out.println("Total de tripulantes: " + totalTripulantes);
        System.out.println("Capacidade total de tanque: " + totalCapacidadeNavios + " litros");
        
        System.out.println("\n--- GERAL ---");
        System.out.println("Total de veiculos: 20");
        System.out.println("Preco total geral: R$ " + String.format("%.2f", totalPrecoAvioes + totalPrecoNavios));
        System.out.println("Total geral de passageiros: " + (totalPassageirosAvioes + totalPassageirosNavios));
        System.out.println("Capacidade total geral: " + (totalCapacidadeAvioes + totalCapacidadeNavios) + " litros");
        
        System.out.println("\n");
    }
}