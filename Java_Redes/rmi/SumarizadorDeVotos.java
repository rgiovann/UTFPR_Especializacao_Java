package local.redes;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
/**
 * Interface remota para sumarização de votos em uma aplicação distribuída.
 * 
 * Define o contrato que deve ser implementado por qualquer objeto remoto
 * responsável por receber e processar uma lista de candidatos, computando
 * seus votos parciais.
 * 
 * Esta interface estende Remote, o que permite que suas implementações 
 * sejam acessadas via RMI (Remote Method Invocation).
 * 
 * @author Giovanni L. Rozza  
 * @version 1.0
 * @since   23-05-2025
 */
public interface SumarizadorDeVotos extends Remote {
	
	public void computaVotos(List<Candidato> candidatos) throws RemoteException;;

}
