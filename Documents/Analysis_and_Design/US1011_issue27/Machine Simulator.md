# Simulador de máquina
=======================================

# 1. Requisitos

**Demo1** Como Gestor de Projeto, eu pretendo que a equipa desenvolva uma aplicação que simule o funcionamento de uma máquina, nomeadamente no envio de mensagens geradas por estas.

# 2. Análise

Desenvolvido em C (e usando threads).
As mensagens a enviar são lidas de um ficheiro de texto.
A identificação da máquina e cadência de envio são estipuladas por parametro.
Deve contemplar cenários de erro que permitam aferir a resiliência do SCM.

# 3. Design

Para resolver este US foi criado um programa em C responsável por simular uma máquina. Devido a este US estar intimamente ligado com o US 1012, o design para este US teve em conta alguns pormenores do mesmo. Sendo assim, de forma a permitir concorrência entre o funcionamento normal da máquina e o sistema de monitorização, é criada uma thread assim que o programa é executado para suportar os pedidos de monitorização. Este é explicado na sua respetiva documentação com mais profundidade. Quanto ao funcionamento efetivo da máquina, este corre na thread principal do programa e cria a conexão com o sistema central de comunicação. De seguida, manda um pedido "HELLO" de acordo com o protocolo de comunicação. Se não obtiver um ACK a execução da máquina é fechada. Se obtiver um ACK, procede ao pedido de "MSG", ao qual se obtiver resposta positiva, procede ao envio de mensagens sempre assegurando a receção por parte do destinatário esperando uma resposta para cada mensagem enviada.
