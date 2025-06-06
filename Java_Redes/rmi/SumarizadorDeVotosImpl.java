package local.redes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Implementação remota da interface SumarizadorDeVotos.
 * 
 * Esta classe é responsável por consolidar os votos recebidos de múltiplos
 * candidatos em um sistema distribuído baseado em RMI (Remote Method Invocation).
 * 
 * Os votos são somados e armazenados em um mapa consolidado, utilizando a
 * identidade do candidato como chave. Além disso, a classe fornece métodos
 * auxiliares para impressão dos resultados da votação em formato legível.
 * 
 * @author Giovanni L. Rozza  
 * @version 1.0
 * @since   23-05-2025
 */
public class SumarizadorDeVotosImpl extends UnicastRemoteObject implements SumarizadorDeVotos {

	private static final long serialVersionUID = 1L;
    private Map<Candidato,Integer> listaConsolidada = new HashMap<>();
	protected SumarizadorDeVotosImpl() throws RemoteException {
		super();
	}

    @Override
    public void computaVotos(List<Candidato> candidatos) {

        for (Candidato candidato : candidatos) {
            int votosParciais = candidato.getNrVotosParcial();
            listaConsolidada.merge(candidato, votosParciais, Integer::sum);
        }
    }
    
    public void imprimeCabecalho() {
    	System.out.println("Eleição");
    	System.out.println("-------");

    	
    }
    
    public void imprimeResultadoVotacao() {
    	if(listaConsolidada.isEmpty()) {
    		return;
    	}
        System.out.println();	
        
        for (Map.Entry<Candidato, Integer> entry : listaConsolidada.entrySet()) {
            Candidato candidato = entry.getKey();
            int votosTotais = entry.getValue();
            String nomeFormatado = String.format("%-15s", candidato.getNomeCandidato());
            String votosFormatados = String.format("%04d", votosTotais);
            System.out.printf("%d %s--- %s votos%n", candidato.getNrCandidato(), nomeFormatado, votosFormatados);
        }

        System.out.println();	
    }
}
