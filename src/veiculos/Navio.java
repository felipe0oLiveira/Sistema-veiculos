package veiculos;

import java.util.Scanner;

/**
 * Classe Navio - Subclasse de Veiculo
 * Representa um navio com atributos especificos
 * 
 * @author Jonathas Felipe
 * @version 1.0
 */
public class Navio extends Veiculo {
    private static Scanner scanner = new Scanner(System.in);
    // Atributos especificos do navio
    private String nome;
    private int numeroTripulantes;
    private String dataLancamento;
    
    /**
     * Construtor vazio
     */
    public Navio() {
        super();
        this.nome = "";
        this.numeroTripulantes = 0;
        this.dataLancamento = "";
    }
    
    /**
     * Construtor com parametros
     * @param nome Nome do navio
     * @param capacidadeTanque Capacidade do tanque
     * @param numeroPassageiros Numero de passageiros
     * @param numeroTripulantes Numero de tripulantes
     * @param preco Preco do navio
     * @param dataLancamento Data de lancamento
     */
    public Navio(String nome, int capacidadeTanque, int numeroPassageiros, 
                 int numeroTripulantes, double preco, String dataLancamento) {
        super(capacidadeTanque, numeroPassageiros, preco);
        this.nome = nome;
        this.numeroTripulantes = numeroTripulantes;
        this.dataLancamento = dataLancamento;
    }
    
    /**
     * Construtor com nome e preco
     * @param nome Nome do navio
     * @param preco Preco do navio
     */
    public Navio(String nome, double preco) {
        super();
        this.nome = nome;
        this.preco = preco;
        this.numeroTripulantes = 0;
        this.dataLancamento = "";
    }
    
    /**
     * Construtor com nome, capacidade e passageiros
     * @param nome Nome do navio
     * @param capacidadeTanque Capacidade do tanque
     * @param numeroPassageiros Numero de passageiros
     */
    public Navio(String nome, int capacidadeTanque, int numeroPassageiros) {
        super(capacidadeTanque, numeroPassageiros, 0.0);
        this.nome = nome;
        this.numeroTripulantes = 0;
        this.dataLancamento = "";
    }
    
    /**
     * Construtor com nome, tripulantes e data de lancamento
     * @param nome Nome do navio
     * @param numeroTripulantes Numero de tripulantes
     * @param dataLancamento Data de lancamento
     */
    public Navio(String nome, int numeroTripulantes, String dataLancamento) {
        super();
        this.nome = nome;
        this.numeroTripulantes = numeroTripulantes;
        this.dataLancamento = dataLancamento;
    }
    
    
    // Metodos getters e setters especificos
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public int getNumeroTripulantes() { return numeroTripulantes; }
    public void setNumeroTripulantes(int numeroTripulantes) { this.numeroTripulantes = numeroTripulantes; }
    
    public String getDataLancamento() { return dataLancamento; }
    public void setDataLancamento(String dataLancamento) { this.dataLancamento = dataLancamento; }
    
    /**
     * Implementacao do metodo abstrato imprimir
     * Exibe todos os dados do navio
     */
    @Override
    public void imprimir() {
        System.out.println("=== DADOS DO NAVIO ===");
        System.out.println("Nome: " + nome);
        System.out.println("Capacidade do Tanque: " + capacidadeTanque + " litros");
        System.out.println("Numero de Passageiros: " + numeroPassageiros);
        System.out.println("Numero de Tripulantes: " + numeroTripulantes);
        System.out.println("Preco: R$ " + String.format("%.2f", preco));
        System.out.println("Data de Lancamento: " + dataLancamento);
        System.out.println("=====================");
    }
    
    /**
     * Metodo auxiliar para leitura de entrada que funciona tanto em console quanto em IDE
     * @return String lida do teclado
     */
    private static String lerEntrada() {
        try {
            // Tenta usar System.console() primeiro (funciona em terminal real)
            if (System.console() != null) {
                return System.console().readLine();
            }
        } catch (Exception e) {
            // Se falhar, usa Scanner como fallback
        }
        
        // Fallback para Scanner (funciona em IDEs)
        return scanner.nextLine();
    }
    
    /**
     * Implementacao do metodo abstrato entrada
     * Realiza a entrada de dados via teclado com tratamento de excecoes
     */
    @Override
    public void entrada() {
        try {
            System.out.println("=== ENTRADA DE DADOS - NAVIO ===");
            
            System.out.print("Digite o nome do navio: ");
            this.nome = lerEntrada();
            
            System.out.print("Digite a capacidade do tanque (litros): ");
            this.capacidadeTanque = Integer.parseInt(lerEntrada());
            if (this.capacidadeTanque < 0) {
                throw new IllegalArgumentException("Capacidade do tanque nao pode ser negativa");
            }
            
            System.out.print("Digite o numero de passageiros: ");
            this.numeroPassageiros = Integer.parseInt(lerEntrada());
            if (this.numeroPassageiros < 0) {
                throw new IllegalArgumentException("Numero de passageiros nao pode ser negativo");
            }
            
            System.out.print("Digite o numero de tripulantes: ");
            this.numeroTripulantes = Integer.parseInt(lerEntrada());
            if (this.numeroTripulantes < 0) {
                throw new IllegalArgumentException("Numero de tripulantes nao pode ser negativo");
            }
            
            System.out.print("Digite o preco do navio: ");
            this.preco = Double.parseDouble(lerEntrada());
            if (this.preco < 0) {
                throw new IllegalArgumentException("Preco nao pode ser negativo");
            }
            
            System.out.print("Digite a data de lancamento: ");
            this.dataLancamento = lerEntrada();
            
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
     * Metodo especifico para calcular relacao passageiros por tripulantes
     * @return Relacao passageiros por tripulantes
     */
    public double passageirosPorTripulantes() {
        if (numeroTripulantes == 0) {
            System.out.println("Aviso: Nao e possivel calcular a relacao - numero de tripulantes e zero");
            return 0.0;
        }
        return (double) numeroPassageiros / numeroTripulantes;
    }
    
    /**
     * Metodo especifico para reajustar preco do navio
     * @param percentual Percentual de reajuste
     */
    public void reajustarPreco(double percentual) {
        super.reajustarPreco(percentual);
        System.out.println("Preco do navio " + nome + " reajustado para R$ " + 
                          String.format("%.2f", preco));
    }
}
