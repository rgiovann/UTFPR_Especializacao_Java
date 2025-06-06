package local.redes;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que implementa uma interface gráfica de usuário (GUI) para o cliente,
 * utilizando Java Swing. Permite a entrada de dados (nome e idade) para criar
 * objetos da classe Pessoa, que são enviados a um servidor via comunicação
 * cliente-servidor utilizando sockets. A interface exibe as respostas do
 * servidor em uma área de texto e inclui validações para garantir a integridade
 * dos dados inseridos. Além disso, a classe ajusta dinamicamente o tamanho do
 * frame com base na resolução da tela do usuário, mantendo uma proporção de 5:3
 * (largura:altura) e um tamanho mínimo para garantir usabilidade.
 * 
 * @author Giovanni L. Rozza
 * @version 1.0
 */
public class FormCliente extends JFrame {

	private static final long serialVersionUID = -2287486437813950365L;
	private JTextField nomeField;
	private JFormattedTextField idadeField;
	private JTextArea retornoTextArea;
	private JButton enviarButton;

	public FormCliente() {
		super("Formulário do Cliente");

		// Painel principal com GridBagLayout
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre componentes
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;

		int y = 0;

		gbc.gridx = 0;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		panel.add(new JLabel("Nome"), gbc);

		y++;
		gbc.gridy = y;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1.0;
		nomeField = new JTextField();
		nomeField.setToolTipText("Digite o nome da pessoa");
		panel.add(nomeField, gbc);

		y++;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.gridy = y;
		panel.add(new JLabel("Idade"), gbc);

		// Campo Idade (somente inteiros)
		y++;
		gbc.gridy = y;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1.0;
		NumberFormat format = NumberFormat.getIntegerInstance();
		format.setGroupingUsed(false);
		NumberFormatter numberFormatter = new NumberFormatter(format) {
			/**
			 * override para tolerar campo vazio enquanto usuario edita campo numerico
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Object stringToValue(String text) throws ParseException {
				if (text == null || text.trim().isEmpty()) {
					return null; // Permite campo vazio
				}
				return super.stringToValue(text);
			}
		};
		numberFormatter.setAllowsInvalid(false);
		numberFormatter.setMinimum(0);
		idadeField = new JFormattedTextField(numberFormatter);
		idadeField.setToolTipText("Digite a idade (somente números inteiros ≥ 0)");
		panel.add(idadeField, gbc);

		y++;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		panel.add(new JLabel("Retorno do Servidor"), gbc);

		y++;
		gbc.gridy = y;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		retornoTextArea = new JTextArea();
		retornoTextArea.setEditable(false);
		retornoTextArea.setLineWrap(true);
		retornoTextArea.setWrapStyleWord(true);
		retornoTextArea.setToolTipText("Exibe a resposta do servidor"); // Adicionado tooltip
		JScrollPane scrollPane = new JScrollPane(retornoTextArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPane, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;

		y++;
		gbc.gridy = y;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.EAST;
		enviarButton = new JButton("Enviar");
		enviarButton.setToolTipText("Envia os dados para o servidor");
		panel.add(enviarButton, gbc);

		enviarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Validação do campo nome
				String nome = nomeField.getText().trim();
				if (nome.isEmpty()) {
					JOptionPane.showMessageDialog(FormCliente.this, "O campo Nome não pode estar vazio.",
							"Erro de Validação", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Validação do campo idade
				Object idadeValue = idadeField.getValue();
				if (idadeValue == null) {
					JOptionPane.showMessageDialog(FormCliente.this, "O campo Idade não pode estar vazio ou inválido.",
							"Erro de Validação", JOptionPane.ERROR_MESSAGE);
					return;
				}

				int idade = ((Number) idadeValue).intValue();

				// try-with-resources
				try (Socket conexao = new Socket("127.0.0.1", 50000);
						ObjectOutputStream saida = new ObjectOutputStream(conexao.getOutputStream());
						DataInputStream entrada = new DataInputStream(conexao.getInputStream());) {

					Pessoa p = new Pessoa(nome, idade);

					saida.writeObject(p);

					String resposta = entrada.readUTF();

					retornoTextArea.append("Recebeu do servidor:\n" + resposta + "\n");
					retornoTextArea.setCaretPosition(retornoTextArea.getDocument().getLength());
					nomeField.setText("");
					idadeField.setValue(null); // Limpa o campo

				} catch (ConnectException ex) {
					JOptionPane.showMessageDialog(FormCliente.this,
							"Não foi possível conectar ao servidor. Por favor, reinicie o aplicativo do servidor e tente novamente.",
							"Erro de Conexão", JOptionPane.ERROR_MESSAGE);
				} catch (IOException ex) {
					Logger.getLogger(FormCliente.class.getName()).log(Level.SEVERE, "Erro na conexão com o servidor",
							ex);
				}

			}

		});

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Ajustar o tamanho do frame com base na resolução da tela,
		// mantendo proporção 5:3

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;

		// Usar 30% da largura da tela como base para a largura do frame
		int frameWidth = (int) (screenWidth * 0.3);
		// Calcular a altura para manter a proporção 5:3 (largura:altura = 5:3)
		int frameHeight = (int) (frameWidth * (3.0 / 5.0)); // altura = largura * (3/5)

		// Definir um tamanho mínimo fixo (400x240, proporção 5:3)
		int minWidth = 450;
		int minHeight = (int) (minWidth * (3.0 / 5.0)); // 240 pixels (proporção 5:3)
		if (frameWidth < minWidth) {
			frameWidth = minWidth;
			frameHeight = minHeight;
		}

		this.setSize(frameWidth, frameHeight);
		this.setContentPane(panel);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// Look and feel nativo
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ignored) {
		}
		SwingUtilities.invokeLater(FormCliente::new);
	}

}