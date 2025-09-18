package veiculos; 

/**
* Superclasse Veiculo
* Contem os atributos e metodos comuns as classes Aviao e Navio
*  
* @author Jonathas Felipe
* @version 1.0
*/

public abstract class Veiculo {
	// Atributos comuns protegidos
	
	protected int capacidadeTanque;
	protected int numeroPassageiros;
	protected double preco;
	
	/**
	 * Construtor Vazio
	 */
	public Veiculo() {
		this.capacidadeTanque = 0;
		this.numeroPassageiros = 0;
		this.preco = 0.0;
	}
	
	/**
	 * Construtor com Parametros
	 * * @param capacidadeTanque Capacidade do tanque
     * @param numeroPassageiros Numero de passageiros
     * @param preco Preco do veiculo
	 */
	public Veiculo(int capacidadeTanque, int numeroPassageiros, double preco) {
		this.capacidadeTanque = capacidadeTanque;
		this.numeroPassageiros = numeroPassageiros;
		this.preco = preco;
	}
	
	// Metodos getters e setters
	public int getCapacidadeTanque() {return capacidadeTanque; }
	public void setCapacidadeTanque(int capacidadeTanque) { this.capacidadeTanque = capacidadeTanque; }
	 
	public int getNumeroPassageiros() {return numeroPassageiros; }
	public void getNumeroPassageiros(int numeroPassageiros) { this.numeroPassageiros = numeroPassageiros; }
	
	public double getPreco() { return preco; }
	public void setPreco(double preco) { this.preco = preco; }
	  
	  /**
	     * Metodo abstrato para imprimir dados especificos de cada subclasse
	     */
	    public abstract void imprimir();
	    
	    /**
	     * Metodo abstrato para entrada de dados especificos de cada subclasse
	     */
	    public abstract void entrada();
	    
	    /**
	     * Metodo comum para reajustar preco
	     * @param percentual Percentual de reajuste
	     */
	    public void reajustarPreco(double percentual) {
	        if (percentual > 0) {
	            this.preco = this.preco * (1 + percentual / 100);
	        }
	    }
	}
