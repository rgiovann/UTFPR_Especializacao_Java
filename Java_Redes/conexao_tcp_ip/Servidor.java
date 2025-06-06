package local.redes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Classe Servidor - Responsável por iniciar um servidor TCP na porta 50000 que
 * aceita conexões de clientes, desserializa objetos do tipo {@code Pessoa}
 * recebidos via socket, imprime seus dados no console e envia uma mensagem de
 * confirmação ao cliente.
 *
 * <p>O servidor é multi-threaded: cada conexão aceita é tratada em uma nova
 * thread, garantindo a escalabilidade e permitindo múltiplas conexões simultâneas.</p>
 *
 * <p>Este servidor exige que os clientes enviem objetos serializados de forma
 * compatível com a classe {@code Pessoa}, que deve implementar {@code Serializable}.</p>
 *
 * @author Giovanni L. Rozza
 * @version 1.0
 */
public class Servidor {

    public static void main(String[] args) {
      
        try (ServerSocket servidor = new ServerSocket(50000)) {
            System.out.println("Servidor iniciado na porta 50000. Aguardando conexões...");

            while (true) {
                try {
                    Socket conexao = servidor.accept();
                    System.out.println("Nova conexão aceita: " + conexao.getInetAddress().getHostAddress());

                    Thread thread = new Thread(() -> {
                        try (ObjectInputStream entrada = new ObjectInputStream(conexao.getInputStream()); DataOutputStream saida = new DataOutputStream(conexao.getOutputStream())) {

                            Pessoa pessoa = (Pessoa) entrada.readObject();
                            System.out.println("Dados recebidos: Nome = " + pessoa.getNome() + ", Idade = " + pessoa.getIdade());

                            saida.writeUTF("Dados recebidos com sucesso!");

                        } catch (ClassNotFoundException e) {
                            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, "Erro na desserialização do objeto", e);
                        } catch (IOException e) {
                            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, "Erro ao processar conexão", e);
                        }
                    });

                    thread.start();

                } catch (IOException e) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, "Erro ao aceitar conexão", e);
                }
            }
        } catch (IOException e) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, "Erro ao iniciar o servidor", e);
        }
    }
}
