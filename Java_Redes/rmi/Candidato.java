package local.redes;

import java.io.Serializable;
import java.util.Objects;
/**
 * Representa um candidato em um sistema de votação.
 * 
 * Esta classe armazena informações básicas de um candidato, como nome,
 * número identificador e a contagem parcial de votos. É serializável, 
 * permitindo que objetos desta classe sejam transmitidos ou persistidos.
 * 
 * Os métodos equals e hashCode são sobrescritos para permitir comparações 
 * baseadas no nome e no número do candidato.
 * 
 * @author Giovanni L. Rozza  
 * @version 1.0
 * @since   23-05-2025
 */
public class Candidato implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nomeCandidato;
    private int nrCandidato;
    private int nrVotosParcial;
    
    public Candidato(String nomeCandidato, int nrCandidato, int nrVotosParcial) {
        this.nomeCandidato = nomeCandidato;
        this.nrCandidato = nrCandidato;
        this.nrVotosParcial = nrVotosParcial;
    }
    
    public String getNomeCandidato() { return nomeCandidato; }
    public int getNrCandidato() { return nrCandidato; }
    public int getNrVotosParcial() { return nrVotosParcial; }

	@Override
	public int hashCode() {
		return Objects.hash(nomeCandidato, nrCandidato);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidato other = (Candidato) obj;
		return Objects.equals(nomeCandidato, other.nomeCandidato) && nrCandidato == other.nrCandidato;
	}

	public void setNrVotosParcial(int nrVotosParcial) {
		this.nrVotosParcial = nrVotosParcial;
	}  
    
}