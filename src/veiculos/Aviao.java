package veiculos;



import java.util.Scanner;

/**
 * Classe Aviao - Subclasse de Veiculo
 * Representa um aviao com atributos especificos
 * 
 * @author Jonathas Felipe
 * @version 1.0
 */
public class Aviao extends Veiculo {
    private static Scanner scanner = new Scanner(System.in);
    // Atributos especificos do aviao
    private String prefixo;
    private String dataRevisao;
    
    /**
     * Construtor vazio
     */
    public Aviao() {
        super();
        this.prefixo = "";
        this.dataRevisao = "";
    }
    
    /**
     * Construtor com parametros
     * @param prefixo Prefixo do aviao
     * @param capacidadeTanque Capacidade do tanque
     * @param numeroPassageiros Numero de passageiros
     * @param preco Preco do aviao
     * @param dataRevisao Data da ultima revisao
     */
    public Aviao(String prefixo, int capacidadeTanque, int numeroPassageiros, 
                 double preco, String dataRevisao) {
        super(capacidadeTanque, numeroPassageiros, preco);
        this.prefixo = prefixo;
        this.dataRevisao = dataRevisao;
    }
    
    // Metodos getters e setters especificos
    public String getPrefixo() { return prefixo; }
    public void setPrefixo(String prefixo) { this.prefixo = prefixo; }
    
    public String getDataRevisao() { return dataRevisao; }
    public void setDataRevisao(String dataRevisao) { this.dataRevisao = dataRevisao; }
    
    /**
     * Implementacao do metodo abstrato imprimir
     * Exibe todos os dados do aviao
     */
    @Override
    public void imprimir() {
        System.out.println("=== DADOS DO AVIAO ===");
        System.out.println("Prefixo: " + prefixo);
        System.out.println("Capacidade do Tanque: " + capacidadeTanque + " litros");
        System.out.println("Numero de Passageiros: " + numeroPassageiros);
        System.out.println("Preco: R$ " + String.format("%.2f", preco));
        System.out.println("Data da Revisao: " + dataRevisao);
        System.out.println("======================");
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
            System.out.println("=== ENTRADA DE DADOS - AVIAO ===");
            
            System.out.print("Digite o prefixo do aviao: ");
            this.prefixo = lerEntrada();
            
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
            
            System.out.print("Digite o preco do aviao: ");
            this.preco = Double.parseDouble(lerEntrada());
            if (this.preco < 0) {
                throw new IllegalArgumentException("Preco nao pode ser negativo");
            }
            
            System.out.print("Digite a data da ultima revisao: ");
            this.dataRevisao = lerEntrada();
            
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
     * Metodo especifico para reajustar preco do aviao
     * @param percentual Percentual de reajuste
     */
    public void reajustarPreco(double percentual) {
        super.reajustarPreco(percentual);
        System.out.println("Preco do aviao " + prefixo + " reajustado para R$ " + 
                          String.format("%.2f", preco));
    }
}
