# Processo Engenharia Funcionalidade
=======================================

# 1. Requisitos

**SCM** Como SCM, pretendo proceder à recolha das mensagens geradas nas/pelas máquinas de uma determinada linha de produção.

A interpretação feita deste requisito foi no sentido de criar uma forma de carregar para a base de dados algumas mensagens de forma a ficarem prontas para posterior processamento pelo Serviço de Processamento de Mensagens.

# 2. Análise

Desenvolvido em Java e usando threads para recolher as mensagens de forma concorrente por linha e por máquina.
As mensagens a enviar são lidas para um ficheiro de texto (log) e persistidas na base de dados onde ficam disponiveis para posterior processamento.

# 3. Design

A forma encontrada para resolver este problema foi criar uma classe **SCMTcpServer** que por sua vez cria Threads para agilizar o processo de recolha de mensagens atrvés da classe **MessageCollection**.

## 3.1. Realização da Funcionalidade

![SCM_US4002.svg](SCM_US4002.svg)

## 3.3. Padrões Aplicados

* Controller

## 3.4. Testes

*N/A*

# 4. Implementação

*N/A*

# 5. Integração/Demonstração

*N/A*

# 6. Observações

*N/A*