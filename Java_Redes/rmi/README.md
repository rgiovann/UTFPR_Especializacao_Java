Urna Eletrônica com RMI e Java Swing
Bem-vindo ao projeto Urna Eletrônica, uma aplicação distribuída desenvolvida como resposta à atividade da disciplina Java Redes da Especialização em Desenvolvimento Java da UTFPR (Universidade Tecnológica Federal do Paraná). Este projeto simula um sistema de votação eletrônica utilizando Java RMI (Remote Method Invocation) para comunicação cliente-servidor e Java Swing para uma interface gráfica amigável, permitindo registrar votos para candidatos, consolidá-los em um servidor central e exibir logs coloridos das operações realizadas.
📋 Descrição do Projeto
Este projeto foi concebido para atender aos requisitos da disciplina de Java Redes, demonstrando a aplicação de conceitos de sistemas distribuídos e interfaces gráficas. Ele implementa um sistema de votação eletrônica com as seguintes funcionalidades:

Interface Gráfica (Swing): Interface intuitiva para selecionar candidatos, inserir a quantidade de votos e enviar os dados ao servidor.
Comunicação Distribuída (RMI): Os votos são enviados para um servidor central que consolida os resultados em tempo real.
Logs Coloridos: Mensagens de sucesso e erro são exibidas em um painel de log com cores diferenciadas (verde para sucesso, vermelho para erro).
Resultados em Tempo Real: O servidor exibe periodicamente os totais de votos consolidados no console.
Suporte a UTF-8: Configuração para exibir caracteres especiais corretamente no console da IDE.

O projeto é estruturado em quatro classes principais:

SumarizadorDeVotos (Interface): Define o contrato para sumarização de votos via RMI.
SumarizadorDeVotosImpl: Implementa a lógica de consolidação de votos, armazenando-os em um HashMap.
SumarizadorDeVotosServer: Inicializa o servidor RMI e publica o objeto remoto.
UrnaJavaGUI: Interface gráfica para interação com o usuário e envio de votos.

🚀 Funcionalidades

Seleção de Candidatos: Escolha um candidato a partir de uma lista pré-definida.
Registro de Votos: Insira a quantidade de votos e envie para o servidor central.
Logs Dinâmicos: Visualize o histórico de operações com timestamps e cores (limite de 50 mensagens).
Encerramento Seguro: Confirmação antes de fechar a aplicação.
Resultados Consolidados: O servidor exibe os totais de votos a cada 5 segundos.

🛠 Tecnologias Utilizadas

Java 8+: Linguagem principal do projeto.
Java RMI: Para comunicação distribuída entre cliente e servidor.
Java Swing: Para a interface gráfica.
UTF-8 Encoding: Suporte a caracteres especiais no console.
GridBagLayout: Para um layout responsivo e organizado na interface gráfica.

📂 Estrutura do Projeto
src/
└── local/
    └── redes/
        ├── Candidato.java          # Classe modelo para candidatos
        ├── SumarizadorDeVotos.java # Interface remota RMI
        ├── SumarizadorDeVotosImpl.java # Implementação do sumarizador
        ├── SumarizadorDeVotosServer.java # Servidor RMI
        └── UrnaJavaGUI.java        # Interface gráfica da urna


Nota: O projeto assume que a classe Candidato existe com métodos como getNrCandidato(), getNomeCandidato(), getNrVotosParcial(), e setNrVotosParcial().

📦 Como Executar
Pré-requisitos

JDK 8+ instalado.
IDE (como IntelliJ ou Eclipse) ou linha de comando com javac e java.
Conexão local para o servidor RMI (executado em localhost:1099).

Passos para Execução

Compile o projeto:
javac src/local/redes/*.java


Execute o servidor:
java -cp src local.redes.SumarizadorDeVotosServer


Execute a urna (cliente):
java -cp src local.redes.UrnaJavaGUI


Interaja com a interface:

Selecione um candidato no menu suspenso.
Insira a quantidade de votos no campo de texto.
Clique em Enviar Servidor Central para registrar os votos.
Veja os logs coloridos no painel inferior.
Clique em Encerrar Urna para fechar a aplicação com confirmação.

Dica: Certifique-se de que o servidor está rodando antes de iniciar a urna.

📝 Notas

Este projeto foi desenvolvido como parte da disciplina Java Redes da Especialização em Desenvolvimento Java da UTFPR.
O servidor atualiza os resultados a cada 5 segundos no console.
A interface gráfica é responsiva, com tamanho mínimo de 450x240 pixels e proporção 5:3.
O log da urna é limitado a 50 mensagens para otimizar desempenho.
Em caso de erros de conexão RMI, verifique se o rmiregistry está ativo e se o serviço está registrado em rmi://localhost:1099/SumarizaVotos.
