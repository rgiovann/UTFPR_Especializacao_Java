
/*
 * Simulação de uma competição multithreaded que demonstra o uso de sincronização em Java.
 * O programa simula uma série de corridas onde cada competidor é representado por uma thread
 * independente que compete por posições e pontos.
 *
 * A competição é gerenciada utilizando apenas recursos básicos de sincronização do Java:
 * - synchronized para proteção de recursos compartilhados
 * - wait() e notifyAll() para coordenação entre threads
 * - Thread.sleep() para simular o tempo de corrida
 *
 * Fluxo de Execução:
 * 1. Criação dos competidores como threads independentes
 * 2. Para cada corrida:
 *    - Sinalização de início
 *    - Competidores correm (sleep aleatório)
 *    - Cruzam linha de chegada uma única vez
 *    - Pontos são atribuídos pela ordem de chegada
 * 3. Ao final:
 *    - Threads são finalizadas
 *    - Pódio é calculado considerando empates
 *    - Tabela de pontos é exibida
 *
 * Classes:
 * - ThreadCompetidor: Implementa um competidor como uma thread
 *   Métodos:
 *   - createAndStart(): Cria e inicia uma nova thread competidora
 *   - run(): Loop principal que executa as corridas
 *   - terminarCompeticao(): Finaliza a thread
 *   - getNrPontosAcumulados(): Retorna pontuação total
 *
 * - RecursoCompeticao: Gerencia estado compartilhado e sincronização
 *   Métodos:
 *   - aguardarInicioCorrida(): Bloqueia até início da corrida
 *   - iniciarNovaCorrida(): Sinaliza nova corrida
 *   - cruzarLinhaChegada(): Registra chegada e pontos
 *   - limparCorrida(): Prepara próxima corrida
 *   - competidorJaCorreu(): Verifica duplicidade
 *
 * Exceções:
 * - InterruptedException: Lançada se uma thread for interrompida durante wait()
 * - RuntimeException: Lançada se um competidor tentar cruzar a linha mais de uma vez
 *
 * @author Giovanni Leopoldo Rozza
 * @version 1.0
 * @since 20/02/2025
 */

package edu.utpfpr.javaII;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CompeticaoRaceCondition {

    private static final int TOTAL_COMPETIDORES = 10;
    private static final int TOTAL_CORRIDAS = 12;
    final RecursoCompeticao competicao;

    CompeticaoRaceCondition() {

        // instancia atributo da classe que vai ser compartilhado
        competicao = new RecursoCompeticao();

        List<ThreadCompetidor> competidores = new ArrayList<>();

        // instanciamos as threads concorrentes de competidores
        for (int i = 0; i < TOTAL_COMPETIDORES; i++) {
            competidores.add(ThreadCompetidor.createAndStart("Competidor #%s".formatted(i + 1), competicao,TOTAL_CORRIDAS));
        }

        // realiza as corridas
        for (int i = 0; i < TOTAL_CORRIDAS; i++) {
            competicao.iniciarNovaCorrida();  // sinaliza início de nova corrida
            synchronized (competicao) {
                // O while checando condicao de final de corrida
                // serve como proteção contra wake ups espúrios, ou seja,
                // wake ups que não são esperados.
                while (competicao.competidoresVencedores.size() != TOTAL_COMPETIDORES) {
                    try {
                        competicao.wait();
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            competicao.limparCorrida();  // limpa para próxima corrida
        }

        // Finaliza as threads dos competidores
        for (ThreadCompetidor competidor : competidores) {
            competidor.terminarCompeticao();  // Envia sinal para terminar as threads dos competidores
        }

        // === Pódio === o programa leva em consideração EMPATE nos ranks 1 2 e 3, ou seja,
        // se houver empate, todos os competidores empatados são considerados no pódio

        competidores.sort(Comparator.comparingInt(ThreadCompetidor::getNrPontosAcumulados).reversed());

        System.out.println("\n=== Pódio ===");
        int lastPoints = competidores.get(0).nrPontosAcumulados;
        int rank = 0; // Ranking geral
        System.out.printf("%s com %d pontos\n", competidores.get(0).thread.getName(), lastPoints);
        for (int i = 1; i < competidores.size(); i++) {
            ThreadCompetidor competidor = competidores.get(i);

            // Se os pontos são diferentes, atualiza o rank
            if (competidor.nrPontosAcumulados != lastPoints) {
                rank++; // Novo rank baseado na posição
            }

            // Se o rank for maior que 3, interrompe a impressão do pódio
            if (rank > 2) {
                break;
            }

            // Imprime o competidor no pódio
            System.out.printf("%s com %d pontos\n", competidor.thread.getName(), competidor.nrPontosAcumulados);

            // Atualiza a variável lastPoints
            lastPoints = competidor.nrPontosAcumulados;


        }

        // === Tabela de pontos ===
        System.out.println("\n=== Tabela de pontos ===");
        for (ThreadCompetidor competidor : competidores) {
            System.out.printf("#%s com %d pontos\n", competidor.thread.getName(), competidor.nrPontosAcumulados);
        }

    }

    public static void main(String[] args) {
        new CompeticaoRaceCondition();
    }


    private static class ThreadCompetidor implements Runnable {
        private final Thread thread;
        private final RecursoCompeticao competicaoAtual;
        private int nrPontosAcumulados;
        private int pontosUltimaCorrida;
        private boolean continuar;
        private final int nrCorridas;

        public ThreadCompetidor(String nome, RecursoCompeticao competicaoAtual,int nrCorridas) {
            this.thread = new Thread(this, nome);
            this.competicaoAtual = competicaoAtual;
            this.nrPontosAcumulados = 0;
            this.pontosUltimaCorrida=0;
            this.continuar = true;
            this.nrCorridas= nrCorridas;
        }

        public static ThreadCompetidor createAndStart(String nome, RecursoCompeticao competicaoAtual,int nrCorridas) {
            ThreadCompetidor myThread = new ThreadCompetidor(nome, competicaoAtual,nrCorridas);
            myThread.thread.start(); // start the thread
            return myThread;
        }


        @Override
        public void run() {
            int i=0;
            while (continuar) {  // loop principal do competidor
                try {
                    // espera nova corrida começar somente se ainda existem corridas a correr
                    if(i<nrCorridas){
                        competicaoAtual.aguardarInicioCorrida();
                    }
                    // simula a corrida
                    Thread.sleep(ThreadLocalRandom.current().nextInt(10, 50));
                    // cruza a linha de chegada somente UMA VEZ
                    if(!competicaoAtual.competidorJaCorreu(this)){
                        competicaoAtual.cruzarLinhaChegada(this);
                    }
                    i++; // proxima corrida
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void terminarCompeticao() {
            continuar = false;  // Interrompe o loop da thread
        }

        public int getNrPontosAcumulados() {
            return this.nrPontosAcumulados;
        }

    }

    /*
      Nota: Em Java, classes aninhadas (static ou não) dentro da mesma classe externa podem
      acessar os membros privados umas das outras.
     */

    private static class RecursoCompeticao {
        private boolean corridaEmAndamento = false;

        final List<ThreadCompetidor> competidoresVencedores;

        RecursoCompeticao(){
            competidoresVencedores = new ArrayList<>();
        }

        synchronized public void aguardarInicioCorrida() throws InterruptedException {
            while (!corridaEmAndamento) {
                wait();
            }
        }

        synchronized public void iniciarNovaCorrida() {
            corridaEmAndamento = true;
            notifyAll();  // acorda todos os competidores
        }

        synchronized public void cruzarLinhaChegada(ThreadCompetidor competidor) {
            if ( corridaEmAndamento) {
                // double check
                if(competidoresVencedores.contains(competidor)){
                    try {
                        throw new InterruptedException("Competidor já cruzou a linha de chegada");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                competidoresVencedores.add(competidor);
                competidor.pontosUltimaCorrida = (TOTAL_COMPETIDORES + 1) - competidoresVencedores.size();
                competidor.nrPontosAcumulados += competidor.pontosUltimaCorrida;
                if (competidoresVencedores.size() == TOTAL_COMPETIDORES) {
                    notifyAll();  // Notifica a thread principal de que a corrida terminou
                }
            }
        }

        synchronized public void limparCorrida() {
            corridaEmAndamento = false;
            competidoresVencedores.clear();
        }

        synchronized public boolean competidorJaCorreu(ThreadCompetidor competidor) {
             return competidoresVencedores.contains(competidor);
        }

    }

}

