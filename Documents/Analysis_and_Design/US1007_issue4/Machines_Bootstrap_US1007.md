# Processo Engenharia Funcionalidade
=======================================

# 1. Requisitos

**Demo1** Como Gestor de Projeto, eu pretendo que a equipa proceda à inicialização (bootstrap) de algumas máquinas.

A interpretação feita deste requisito foi no sentido de criar uma forma de carregar para a base de dados algumas máquinas.

# 2. Análise

Enquanto é desenvolvido o programa é de interesse que não se tenha de inserir informação na base de dados de cada vez que o programa precisa ser testado. Sendo assim, faz-se bootstrap de matérias primas de forma a agilizar e a rentabilizar melhor o tempo da equipa no desenvolvimento do software.

# 3. Design

A forma encontrada para resolver este problema foi criar uma classe **MachineBootstrapper** que faz uso do **MachineController** para criar instâncias de forma a garantir as regras de negócio dadas pelo cliente sem ter o intermédio de uma UI.

## 3.1. Realização da Funcionalidade

![Machines_Bootstrap_US1007.png](Machines_Bootstrap_US1007.png)

## 3.3. Padrões Aplicados

* Bootstrapper
* Controller

## 3.4. Testes

*N/A*

# 4. Implementação

*N/A*

# 5. Integração/Demonstração

*N/A*

# 6. Observações

*N/A*