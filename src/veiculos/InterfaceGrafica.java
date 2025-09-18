package veiculos;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface Grafica para o Sistema de Gerenciamento de Veiculos
 * Utiliza Java Swing para criar uma interface visual e interativa
 * 
 * @author Jonathas Felipe
 * @version 1.0
 */
public class InterfaceGrafica extends JFrame {
    private List<VeiculoInfo> veiculos;
    private DefaultTableModel modelAvioes;
    private DefaultTableModel modelNavios;
    private JTable tabelaAvioes;
    private JTable tabelaNavios;
    private JTextField campoPrefixo, campoNome, campoCapacidade, campoPassageiros, campoPreco;
    private JTextField campoDataRevisao, campoDataLancamento, campoTripulantes;
    private JLabel labelPassageiros, labelTripulantes;
    private JComboBox<String> comboBoxTipo;
    private JLabel labelEstatisticas;

    // Classe interna para armazenar informações dos veículos
    public static class VeiculoInfo {
        public String tipo;
        public String prefixoNome;
        public int capacidade;
        public int passageiros;
        public double preco;
        public String dataRevisao;
        public int tripulantes;
        public String dataLancamento;

        public VeiculoInfo(String tipo, String prefixoNome, int capacidade, int passageiros, 
                          double preco, String dataRevisao, int tripulantes, String dataLancamento) {
            this.tipo = tipo;
            this.prefixoNome = prefixoNome;
            this.capacidade = capacidade;
            this.passageiros = passageiros;
            this.preco = preco;
            this.dataRevisao = dataRevisao;
            this.tripulantes = tripulantes;
            this.dataLancamento = dataLancamento;
        }
    }

    public InterfaceGrafica() {
        veiculos = new ArrayList<>();
        inicializarComponentes();
        configurarJanela();
    }

    private void inicializarComponentes() {
        // Configuração da janela principal
        setTitle("Sistema de Gerenciamento de Veículos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel superior - Cadastro
        JPanel painelCadastro = criarPainelCadastro();
        add(painelCadastro, BorderLayout.NORTH);

        // Painel central - Tabelas
        JPanel painelTabelas = criarPainelTabelas();
        add(painelTabelas, BorderLayout.CENTER);

        // Painel inferior - Estatísticas e Controles
        JPanel painelInferior = criarPainelInferior();
        add(painelInferior, BorderLayout.SOUTH);
    }

    private JPanel criarPainelCadastro() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createTitledBorder("Cadastro de Veículos"));
        painel.setBackground(new Color(240, 248, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Tipo de veículo
        gbc.gridx = 0; gbc.gridy = 0;
        painel.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        comboBoxTipo = new JComboBox<>(new String[]{"Avião", "Navio"});
        comboBoxTipo.addActionListener(e -> atualizarCampos());
        painel.add(comboBoxTipo, gbc);

        // Prefixo/Nome
        gbc.gridx = 2;
        painel.add(new JLabel("Prefixo/Nome:"), gbc);
        gbc.gridx = 3;
        campoPrefixo = new JTextField(10);
        campoNome = new JTextField(10);
        painel.add(campoPrefixo, gbc);
        painel.add(campoNome, gbc);

        // Capacidade do tanque
        gbc.gridx = 0; gbc.gridy = 1;
        painel.add(new JLabel("Capacidade Tanque:"), gbc);
        gbc.gridx = 1;
        campoCapacidade = new JTextField(10);
        painel.add(campoCapacidade, gbc);

        // Número de passageiros/tripulantes
        gbc.gridx = 2;
        labelPassageiros = new JLabel("Passageiros:");
        labelTripulantes = new JLabel("Tripulantes:");
        painel.add(labelPassageiros, gbc);
        gbc.gridx = 3;
        campoPassageiros = new JTextField(10);
        painel.add(campoPassageiros, gbc);

        // Preço
        gbc.gridx = 0; gbc.gridy = 2;
        painel.add(new JLabel("Preço:"), gbc);
        gbc.gridx = 1;
        campoPreco = new JTextField(10);
        painel.add(campoPreco, gbc);

        // Data de revisão (para aviões)
        gbc.gridx = 2;
        painel.add(new JLabel("Data Revisão:"), gbc);
        gbc.gridx = 3;
        campoDataRevisao = new JTextField(10);
        painel.add(campoDataRevisao, gbc);

        // Campo adicional para tripulantes (usado dinamicamente)
        gbc.gridx = 0; gbc.gridy = 3;
        painel.add(labelTripulantes, gbc);
        gbc.gridx = 1;
        campoTripulantes = new JTextField(10);
        painel.add(campoTripulantes, gbc);

        // Data de lançamento (para navios)
        gbc.gridx = 2;
        painel.add(new JLabel("Data Lançamento:"), gbc);
        gbc.gridx = 3;
        campoDataLancamento = new JTextField(10);
        painel.add(campoDataLancamento, gbc);

        // Botões
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 4;
        JPanel painelBotoes = new JPanel(new FlowLayout());
        
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBackground(new Color(34, 139, 34));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.addActionListener(e -> cadastrarVeiculo());
        
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBackground(new Color(220, 20, 60));
        btnLimpar.setForeground(Color.WHITE);
        btnLimpar.addActionListener(e -> limparCampos());
        
        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnLimpar);
        painel.add(painelBotoes, gbc);

        // Inicializar campos para o tipo padrão
        campoNome.setVisible(false);
        campoTripulantes.setVisible(false);
        campoDataLancamento.setVisible(false);
        labelTripulantes.setVisible(false);
        atualizarCampos();

        return painel;
    }

    private JPanel criarPainelTabelas() {
        JPanel painel = new JPanel(new GridLayout(1, 2));
        painel.setBorder(BorderFactory.createTitledBorder("Veículos Cadastrados"));

        // Tabela de Aviões
        String[] colunasAvioes = {"Prefixo", "Capacidade", "Passageiros", "Preço", "Data Revisão"};
        modelAvioes = new DefaultTableModel(colunasAvioes, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaAvioes = new JTable(modelAvioes);
        tabelaAvioes.setBackground(new Color(255, 248, 220));
        JScrollPane scrollAvioes = new JScrollPane(tabelaAvioes);
        scrollAvioes.setBorder(BorderFactory.createTitledBorder("Aviões"));

        // Tabela de Navios
        String[] colunasNavios = {"Nome", "Capacidade", "Passageiros", "Tripulantes", "Preço", "Data Lançamento"};
        modelNavios = new DefaultTableModel(colunasNavios, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaNavios = new JTable(modelNavios);
        tabelaNavios.setBackground(new Color(240, 248, 255));
        JScrollPane scrollNavios = new JScrollPane(tabelaNavios);
        scrollNavios.setBorder(BorderFactory.createTitledBorder("Navios"));

        painel.add(scrollAvioes);
        painel.add(scrollNavios);

        return painel;
    }

    private JPanel criarPainelInferior() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(new Color(245, 245, 245));

        // Painel de estatísticas
        JPanel painelStats = new JPanel(new FlowLayout());
        labelEstatisticas = new JLabel("Total de Veículos: 0 | Aviões: 0 | Navios: 0");
        labelEstatisticas.setFont(new Font("Arial", Font.BOLD, 12));
        painelStats.add(labelEstatisticas);
        painel.add(painelStats, BorderLayout.NORTH);

        // Painel de controles
        JPanel painelControles = new JPanel(new FlowLayout());
        
        JButton btnReajustarAvioes = new JButton("Reajustar Preços Aviões");
        btnReajustarAvioes.setBackground(new Color(255, 165, 0));
        btnReajustarAvioes.setForeground(Color.WHITE);
        btnReajustarAvioes.addActionListener(e -> reajustarPrecos("aviões"));
        
        JButton btnEstatisticas = new JButton("Ver Estatísticas Detalhadas");
        btnEstatisticas.setBackground(new Color(75, 0, 130));
        btnEstatisticas.setForeground(Color.WHITE);
        btnEstatisticas.addActionListener(e -> mostrarEstatisticasDetalhadas());
        
        JButton btnCalcularRelacao = new JButton("Calcular Relação Tripulantes");
        btnCalcularRelacao.setBackground(new Color(128, 0, 128));
        btnCalcularRelacao.setForeground(Color.WHITE);
        btnCalcularRelacao.addActionListener(e -> calcularRelacaoTripulantes());
        
        JButton btnLimparTudo = new JButton("Limpar Todos os Veículos");
        btnLimparTudo.setBackground(new Color(220, 20, 60));
        btnLimparTudo.setForeground(Color.WHITE);
        btnLimparTudo.addActionListener(e -> limparTodosVeiculos());

        painelControles.add(btnReajustarAvioes);
        painelControles.add(btnEstatisticas);
        painelControles.add(btnCalcularRelacao);
        painelControles.add(btnLimparTudo);

        painel.add(painelControles, BorderLayout.CENTER);

        return painel;
    }

    private void atualizarCampos() {
        String tipo = (String) comboBoxTipo.getSelectedItem();
        
        if ("Avião".equals(tipo)) {
            // Mostrar campos do avião
            campoPrefixo.setVisible(true);
            campoNome.setVisible(false);
            campoDataRevisao.setVisible(true);
            campoTripulantes.setVisible(false);
            campoDataLancamento.setVisible(false);
            
            // Mostrar label "Passageiros" e esconder "Tripulantes"
            labelPassageiros.setVisible(true);
            labelTripulantes.setVisible(false);
            
            // Limpar campos que não são do avião
            campoNome.setText("");
            campoTripulantes.setText("");
            campoDataLancamento.setText("");
            
        } else if ("Navio".equals(tipo)) {
            // Mostrar campos do navio
            campoPrefixo.setVisible(false);
            campoNome.setVisible(true);
            campoDataRevisao.setVisible(false);
            campoTripulantes.setVisible(true);
            campoDataLancamento.setVisible(true);
            
            // Mostrar labels "Passageiros" e "Tripulantes" para navio
            labelPassageiros.setVisible(true);
            labelTripulantes.setVisible(true);
            
            // Limpar campos que não são do navio
            campoPrefixo.setText("");
            campoDataRevisao.setText("");
        }
        
        // Forçar atualização da interface
        revalidate();
        repaint();
    }

    private void cadastrarVeiculo() {
        try {
            String tipo = (String) comboBoxTipo.getSelectedItem();
            int capacidade = Integer.parseInt(campoCapacidade.getText());
            double preco = Double.parseDouble(campoPreco.getText());

            VeiculoInfo veiculo;

            if ("Avião".equals(tipo)) {
                String prefijo = campoPrefixo.getText();
                String dataRevisao = campoDataRevisao.getText();
                int passageiros = Integer.parseInt(campoPassageiros.getText());
                
                // Validação específica para avião
                if (prefijo.trim().isEmpty()) {
                    throw new IllegalArgumentException("Prefixo do avião é obrigatório!");
                }
                if (dataRevisao.trim().isEmpty()) {
                    throw new IllegalArgumentException("Data de revisão é obrigatória!");
                }
                
                veiculo = new VeiculoInfo("Avião", prefijo, capacidade, passageiros, 
                                        preco, dataRevisao, 0, "");
            } else {
                String nome = campoNome.getText();
                int passageiros = Integer.parseInt(campoPassageiros.getText());
                int tripulantes = Integer.parseInt(campoTripulantes.getText());
                String dataLancamento = campoDataLancamento.getText();
                
                // Validação específica para navio
                if (nome.trim().isEmpty()) {
                    throw new IllegalArgumentException("Nome do navio é obrigatório!");
                }
                if (dataLancamento.trim().isEmpty()) {
                    throw new IllegalArgumentException("Data de lançamento é obrigatória!");
                }
                
                veiculo = new VeiculoInfo("Navio", nome, capacidade, passageiros, 
                                        preco, "", tripulantes, dataLancamento);
            }

            // Mostrar confirmação antes de cadastrar
            String mensagemConfirmacao;
            if ("Avião".equals(tipo)) {
                mensagemConfirmacao = String.format(
                    "Confirme os dados do %s:\n\n" +
                    "Prefixo: %s\n" +
                    "Capacidade: %d litros\n" +
                    "Passageiros: %d\n" +
                    "Preço: R$ %.2f\n" +
                    "Data Revisão: %s\n\n" +
                    "Deseja cadastrar este veículo?",
                    tipo.toLowerCase(),
                    veiculo.prefixoNome,
                    veiculo.capacidade,
                    veiculo.passageiros,
                    veiculo.preco,
                    veiculo.dataRevisao
                );
            } else {
                mensagemConfirmacao = String.format(
                    "Confirme os dados do %s:\n\n" +
                    "Nome: %s\n" +
                    "Capacidade: %d litros\n" +
                    "Passageiros: %d\n" +
                    "Tripulantes: %d\n" +
                    "Preço: R$ %.2f\n" +
                    "Data Lançamento: %s\n\n" +
                    "Deseja cadastrar este veículo?",
                    tipo.toLowerCase(),
                    veiculo.prefixoNome,
                    veiculo.capacidade,
                    veiculo.passageiros,
                    veiculo.tripulantes,
                    veiculo.preco,
                    veiculo.dataLancamento
                );
            }

            int confirmacao = JOptionPane.showConfirmDialog(this, mensagemConfirmacao, 
                "Confirmar Cadastro", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (confirmacao == JOptionPane.YES_OPTION) {
                veiculos.add(veiculo);
                atualizarTabelas();
                atualizarEstatisticas();
                limparCampos();
                
                JOptionPane.showMessageDialog(this, "Veículo cadastrado com sucesso!", 
                                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Cadastro cancelado.", 
                                            "Cancelado", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro: Digite apenas números nos campos numéricos!", 
                                        "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), 
                                        "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro inesperado: " + e.getMessage(), 
                                        "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarTabelas() {
        // Limpar tabelas
        modelAvioes.setRowCount(0);
        modelNavios.setRowCount(0);

        // Atualizar com dados atuais
        for (VeiculoInfo veiculo : veiculos) {
            if ("Avião".equals(veiculo.tipo)) {
                modelAvioes.addRow(new Object[]{
                    veiculo.prefixoNome,
                    veiculo.capacidade,
                    veiculo.passageiros,
                    String.format("R$ %.2f", veiculo.preco),
                    veiculo.dataRevisao
                });
            } else if ("Navio".equals(veiculo.tipo)) {
                modelNavios.addRow(new Object[]{
                    veiculo.prefixoNome,
                    veiculo.capacidade,
                    veiculo.passageiros,
                    veiculo.tripulantes,
                    String.format("R$ %.2f", veiculo.preco),
                    veiculo.dataLancamento
                });
            }
        }
    }

    private void atualizarEstatisticas() {
        long totalAvioes = veiculos.stream().filter(v -> "Avião".equals(v.tipo)).count();
        long totalNavios = veiculos.stream().filter(v -> "Navio".equals(v.tipo)).count();
        
        labelEstatisticas.setText(String.format("Total de Veículos: %d | Aviões: %d | Navios: %d", 
                                               veiculos.size(), totalAvioes, totalNavios));
    }

    private void limparCampos() {
        // Limpar campos comuns
        campoCapacidade.setText("");
        campoPassageiros.setText("");
        campoPreco.setText("");
        
        // Limpar campos específicos do avião
        campoPrefixo.setText("");
        campoDataRevisao.setText("");
        
        // Limpar campos específicos do navio
        campoNome.setText("");
        campoTripulantes.setText("");
        campoDataLancamento.setText("");
    }

    private void reajustarPrecos(String tipo) {
        String input = JOptionPane.showInputDialog(this, 
            "Digite o percentual de reajuste para os " + tipo + ":", "Reajuste de Preços", 
            JOptionPane.QUESTION_MESSAGE);
        
        if (input != null && !input.trim().isEmpty()) {
            try {
                double percentual = Double.parseDouble(input);
                
                if (percentual < 0) {
                    throw new IllegalArgumentException("Percentual não pode ser negativo!");
                }
                
                int veiculosAtualizados = 0;
                for (VeiculoInfo veiculo : veiculos) {
                    if ("Avião".equals(veiculo.tipo)) {
                        veiculo.preco = veiculo.preco * (1 + percentual / 100);
                        veiculosAtualizados++;
                    }
                }
                
                atualizarTabelas();
                JOptionPane.showMessageDialog(this, 
                    String.format("Reajuste aplicado com sucesso!\n%d aviões atualizados.", veiculosAtualizados), 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro: Digite um valor numérico válido!", 
                                            "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), 
                                            "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarEstatisticasDetalhadas() {
        double totalPrecoAvioes = 0, totalPrecoNavios = 0;
        int totalPassageirosAvioes = 0, totalPassageirosNavios = 0;
        int totalCapacidadeAvioes = 0, totalCapacidadeNavios = 0;
        int totalTripulantes = 0;

        for (VeiculoInfo veiculo : veiculos) {
            if ("Avião".equals(veiculo.tipo)) {
                totalPrecoAvioes += veiculo.preco;
                totalPassageirosAvioes += veiculo.passageiros;
                totalCapacidadeAvioes += veiculo.capacidade;
            } else if ("Navio".equals(veiculo.tipo)) {
                totalPrecoNavios += veiculo.preco;
                totalPassageirosNavios += veiculo.passageiros;
                totalCapacidadeNavios += veiculo.capacidade;
                totalTripulantes += veiculo.tripulantes;
            }
        }

        long countAvioes = veiculos.stream().filter(v -> "Avião".equals(v.tipo)).count();
        long countNavios = veiculos.stream().filter(v -> "Navio".equals(v.tipo)).count();

        String estatisticas = String.format(
            "=== ESTATÍSTICAS DETALHADAS ===\n\n" +
            "AVIÕES:\n" +
            "• Total: %d aviões\n" +
            "• Preço total: R$ %.2f\n" +
            "• Preço médio: R$ %.2f\n" +
            "• Total passageiros: %d\n" +
            "• Capacidade total: %d litros\n\n" +
            "NAVIOS:\n" +
            "• Total: %d navios\n" +
            "• Preço total: R$ %.2f\n" +
            "• Preço médio: R$ %.2f\n" +
            "• Total passageiros: %d\n" +
            "• Total tripulantes: %d\n" +
            "• Capacidade total: %d litros\n\n" +
            "GERAL:\n" +
            "• Total veículos: %d\n" +
            "• Preço total geral: R$ %.2f\n" +
            "• Total geral passageiros: %d\n" +
            "• Capacidade total geral: %d litros",
            countAvioes,
            totalPrecoAvioes,
            countAvioes > 0 ? totalPrecoAvioes / countAvioes : 0,
            totalPassageirosAvioes,
            totalCapacidadeAvioes,
            countNavios,
            totalPrecoNavios,
            countNavios > 0 ? totalPrecoNavios / countNavios : 0,
            totalPassageirosNavios,
            totalTripulantes,
            totalCapacidadeNavios,
            veiculos.size(),
            totalPrecoAvioes + totalPrecoNavios,
            totalPassageirosAvioes + totalPassageirosNavios,
            totalCapacidadeAvioes + totalCapacidadeNavios
        );

        JOptionPane.showMessageDialog(this, estatisticas, "Estatísticas Detalhadas", 
                                    JOptionPane.INFORMATION_MESSAGE);
    }

    private void calcularRelacaoTripulantes() {
        StringBuilder relacoes = new StringBuilder("=== RELAÇÃO PASSAGEIROS POR TRIPULANTES ===\n\n");
        
        for (VeiculoInfo veiculo : veiculos) {
            if ("Navio".equals(veiculo.tipo)) {
                double relacao = veiculo.tripulantes > 0 ? 
                    (double) veiculo.passageiros / veiculo.tripulantes : 0.0;
                relacoes.append(String.format("• %s: %.2f passageiros por tripulante\n", 
                                            veiculo.prefixoNome, relacao));
            }
        }
        
        if (relacoes.toString().equals("=== RELAÇÃO PASSAGEIROS POR TRIPULANTES ===\n\n")) {
            relacoes.append("Nenhum navio cadastrado.");
        }
        
        JOptionPane.showMessageDialog(this, relacoes.toString(), "Relação Tripulantes", 
                                    JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void limparTodosVeiculos() {
        if (veiculos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há veículos para limpar!", 
                                        "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacao = JOptionPane.showConfirmDialog(this, 
            "Tem certeza que deseja remover TODOS os veículos cadastrados?\n\n" +
            "Esta ação não pode ser desfeita!",
            "Confirmar Limpeza", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE);
        
        if (confirmacao == JOptionPane.YES_OPTION) {
            veiculos.clear();
            atualizarTabelas();
            atualizarEstatisticas();
            limparCampos();
            
            JOptionPane.showMessageDialog(this, 
                "Todos os veículos foram removidos com sucesso!", 
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void configurarJanela() {
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InterfaceGrafica();
        });
    }
}