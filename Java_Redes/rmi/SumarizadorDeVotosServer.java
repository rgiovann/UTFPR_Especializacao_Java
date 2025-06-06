package local.redes;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
/**
 * Classe principal que inicializa o servidor RMI para sumarização de votos.
 * 
 * Esta aplicação configura o ambiente de execução remoto, publica o objeto
 * remoto `SumarizadorDeVotosImpl` no registro RMI e imprime periodicamente 
 * os resultados da votação.
 * 
 * Também realiza configuração para que o console da IDE suporte corretamente
 * caracteres especiais (UTF-8).
 * 
 * @author Giovanni L. Rozza  
 * @version 1.0
 * @since   23-05-2025
 */
public class SumarizadorDeVotosServer {

    public static void main(String[] args) {
    	
		try {
			// resolve problema dos caracteres especiais no console da IDE
			System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));

		} catch (Exception e) {
			System.err.println("Erro ao configurar UTF-8: " + e.getMessage());
		}
    	
        try {
            LocateRegistry.createRegistry(1099);
            SumarizadorDeVotosImpl sumarizador = new SumarizadorDeVotosImpl();
            Naming.rebind("rmi://localhost:1099/SumarizaVotos", sumarizador);
            sumarizador.imprimeCabecalho();

            while (true) {
                Thread.sleep(5000);
                sumarizador.imprimeResultadoVotacao();
            }

        } catch (Exception e) {
            System.out.println("Erro RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

