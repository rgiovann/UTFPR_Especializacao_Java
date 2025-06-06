package local.redes;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
/**
 * Interface gráfica da urna eletrônica implementada em Java usando Swing.
 *
 * Esta classe permite ao usuário:
 * - Selecionar um candidato
 * - Informar a quantidade de votos
 * - Enviar os votos para um servidor central via RMI
 * - Visualizar logs das operações realizadas
 * - Encerrar a aplicação com confirmação
 *
 * A comunicação é feita por meio da interface remota SumarizadorDeVotos.
 * As mensagens de log são exibidas com cores diferentes para sucesso e erro,
 * e o histórico é limitado a um número máximo de entradas.
 *
 * @author Giovanni L. Rozza  
 * @version 1.0
 * @since   23-05-2025
 */

public class UrnaJavaGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JComboBox<String> comboBoxCandidatos;
    private JTextField textFieldVotos;
    private JTextPane textPaneLog; // Alterado de JTextArea para JTextPane
    private JButton botaoEnviar;
    private JButton botaoEncerrar;
    private JScrollPane scrollPane;
    private List<Candidato> listaCandidatos = new ArrayList<>();
    private SumarizadorDeVotos sumarizador = null;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int HD_HEIGTH = 768;
    public  final int COR_SUCESSO = 0;   
    public  final int COR_ERRO = 1; 
    public  final int TAMANHO_BUFFER_MSGS = 50; 

    public UrnaJavaGUI() {
        configuraConexaoRMI();
        configuraFramePrincipal();
        inicializarComponentes();
        configurarLayout();
        configurarEventos();
        configurarJanela();
    }

    private void configuraConexaoRMI() {
        try {
            sumarizador = (SumarizadorDeVotos) Naming.lookup("rmi://localhost:1099/SumarizaVotos");
        } catch (Exception e) {
            System.out.println("Erro RMI: " + e.getMessage());
            System.exit(0);
        }        
    }

    private void configuraFramePrincipal() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ajustar o tamanho do frame com base na resolução da tela, mantendo proporção 5:3
        int frameWidth = (int) (screenSize.width * 0.37);
        int frameHeight = (int) (frameWidth * (3.5 / 5.0));

        // Definir um tamanho mínimo fixo (450x240, proporção 5:3)
        int minWidth = 450;
        int minHeight = (int) (minWidth * (3.0 / 5.0));
        if (frameWidth < minWidth) {
            frameWidth = minWidth;
            frameHeight = minHeight + 100;
        }
        this.setSize(frameWidth, frameHeight);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void inicializarComponentes() {
        listaCandidatos.add(new Candidato("João Márcio", 10, 0));
        listaCandidatos.add(new Candidato("Maria Rita", 22, 0));
        listaCandidatos.add(new Candidato("Vitor Bello", 40, 0));
        listaCandidatos.add(new Candidato("Augusto Patto", 50, 0));

        String[] candidatos = listaCandidatos.stream()
            .map(c -> c.getNrCandidato() + " - " + c.getNomeCandidato())
            .toArray(String[]::new);
        comboBoxCandidatos = new JComboBox<>(candidatos);

        textFieldVotos = new JTextField(15);

        textPaneLog = new JTextPane();
        textPaneLog.setFont(new Font("Consolas", Font.PLAIN, screenSize.height < HD_HEIGTH ? 11 : 12));
        textPaneLog.setEditable(false);
        scrollPane = new JScrollPane(textPaneLog);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(-1, 65));
        scrollPane.setMinimumSize(new Dimension(200, 65));

        botaoEnviar = new JButton("Enviar Servidor Central");
        botaoEncerrar = new JButton("Encerrar Urna");
    }

    private void configurarLayout() {
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Linha 0: Título
        JLabel tituloUrna = new JLabel("Urna Java");
        tituloUrna.setFont(new Font("Arial", Font.BOLD, 32));
        tituloUrna.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 25, 0);
        gbc.weightx = 1.0;
        painelPrincipal.add(tituloUrna, gbc);

        // Linha 1: Painéis esquerdo e direito
        JPanel painelEsquerda = new JPanel(new GridBagLayout());
        JPanel painelDireita = new JPanel(new GridBagLayout());
        GridBagConstraints gbcInner = new GridBagConstraints();

        // Configuração do painel esquerdo (Selecione o Candidato e ComboBox)
        gbcInner.gridx = 0;
        gbcInner.gridy = 0;
        gbcInner.anchor = GridBagConstraints.WEST;
        painelEsquerda.add(new JLabel("Selecione o Candidato:"), gbcInner);

        gbcInner.gridy = 1;
        gbcInner.fill = GridBagConstraints.HORIZONTAL;
        gbcInner.insets = new Insets(5, 0, 0, 0);
        painelEsquerda.add(comboBoxCandidatos, gbcInner);

        // Configuração do painel direito (Quantidade de votos e TextField)
        gbcInner.gridx = 0;
        gbcInner.gridy = 0;
        gbcInner.anchor = GridBagConstraints.EAST;
        gbcInner.insets = new Insets(0, 0, 0, 0);
        painelDireita.add(new JLabel("Quantidade de votos:"), gbcInner);

        gbcInner.gridy = 1;
        gbcInner.anchor = GridBagConstraints.EAST;
        gbcInner.insets = new Insets(5, 0, 0, 0);
        textFieldVotos.setPreferredSize(new Dimension(60, 25));
        painelDireita.add(textFieldVotos, gbcInner);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 25, 10);
        gbc.weightx = 0.5;
        painelPrincipal.add(painelEsquerda, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 25, 0);
        gbc.weightx = 0.5;
        painelPrincipal.add(painelDireita, gbc);

        // Linha 2: Botão Enviar
        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.weightx = 0;
        botaoEnviar.setPreferredSize(new Dimension(150, 30));
        painelPrincipal.add(botaoEnviar, gbc);

        // Linha 3: JTextPane com JScrollPane
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        painelPrincipal.add(scrollPane, gbc);

        // Linha 4: Botão Encerrar
        gbc.gridy = 4;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weightx = 0;
        gbc.weighty = 0;
        botaoEncerrar.setPreferredSize(new Dimension(150, 30));
        painelPrincipal.add(botaoEncerrar, gbc);

        this.add(painelPrincipal);
    }

    private void configurarEventos() {
        botaoEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarDados();
            }
        });

        botaoEncerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encerrarAplicacao();
            }
        });
    }

    private void configurarJanela() {
        setTitle("Urna Eletrônica");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                encerrarAplicacao();
            }
        });
    }

    private void enviarDados() {
        String candidatoSelecionado = (String) comboBoxCandidatos.getSelectedItem();
        String quantidadeVotos = textFieldVotos.getText();
        Integer votos = 0;
        if (quantidadeVotos.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, informe a quantidade de votos!", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            votos = Integer.parseInt(quantidadeVotos.trim());
            if (votos <= 0) {
                JOptionPane.showMessageDialog(this, "A quantidade de votos deve ser maior que zero!", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, informe um número válido para a quantidade de votos!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int nrCandidato = Integer.parseInt(candidatoSelecionado.split(" - ")[0]);
        Integer finalVotos = votos;
        listaCandidatos.stream()
            .filter(c -> c.getNrCandidato() == nrCandidato)
            .findFirst()
            .ifPresent(c -> c.setNrVotosParcial(finalVotos));

        try {
            sumarizador.computaVotos(listaCandidatos);
            adicionarLog("[SUCESSO] - Dados enviado com sucesso!", COR_SUCESSO);
        } catch (RemoteException e) {
            adicionarLog("[ERRO] - Erro: " + e.getMessage(), COR_ERRO);
        }
        catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }

        textFieldVotos.setText("");
    }

    private void adicionarLog(String statusMensagem, int corTipo) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String timestamp = now.format(formatter);
        String logEntry = "[" + timestamp + "] " + statusMensagem + "\n";

        Color cor;
        switch (corTipo) {
            case COR_SUCESSO:
                cor = new Color(34, 139, 34); // Verde escuro
                break;
            case COR_ERRO:
                cor = new Color(220, 20, 60); // Vermelho
                break;
            default:
                cor = Color.BLACK; // Cor padrão
                break;
        }

        // Configurar o estilo para a cor
        StyledDocument doc = textPaneLog.getStyledDocument();
        SimpleAttributeSet style = new SimpleAttributeSet();
        StyleConstants.setForeground(style, cor);
        StyleConstants.setBold(style, true);

        try {
            int lineCount = doc.getDefaultRootElement().getElementCount();
            if (lineCount >= TAMANHO_BUFFER_MSGS) {
                // Remover a linha mais antiga (primeira linha)
                int endOffset = doc.getDefaultRootElement().getElement(0).getEndOffset();
                doc.remove(0, endOffset);
            }

            // Adicionar o texto colorido ao final
            doc.insertString(doc.getLength(), logEntry, style);

            // Rolagem automática para o final
            textPaneLog.setCaretPosition(doc.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void encerrarAplicacao() {
        int opcao = JOptionPane.showConfirmDialog(this, "Deseja encerrar a aplicação?", "Confirmação",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (opcao == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        SwingUtilities.invokeLater(UrnaJavaGUI::new);
    }
}