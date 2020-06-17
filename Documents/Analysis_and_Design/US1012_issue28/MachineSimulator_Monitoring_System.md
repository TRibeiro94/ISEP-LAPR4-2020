# Simulador de máquina (Sistema de monitorização)
=======================================

# 1. Requisitos

**Demo1** Como Gestor de Projeto, eu pretendo que o simulador de máquina suporte pedidos de monitorização do seu estado.

# 2. Análise

Deve ser concorrente (em C) com o resto da simulação da máquina e o estado deve ser partilhado entre threads.

# 3. Design

Para resolver este US foi criado um programa em C responsável por simular uma máquina. Devido a este US estar intimamente ligado com o US 1011, o design para este US teve em conta alguns pormenores do mesmo. Sendo assim, de forma a permitir concorrência entre o funcionamento normal da máquina e o sistema de monitorização, é criada uma thread assim que o programa é executado para suportar estes pedidos de monitorização. O US1011 é explicado na sua respetiva documentação com mais profundidade. Quanto ao suporte de pedidos de monitorização, este é responsável por criar um socket para ficar disponivel para ouvir "UDP requests" por parte do sistema de monitorização. Ao receber um pedido, o programa verifica o código da mesma para saber que tipo de pedido é e responde em concordância. Se for um pedido "HELLO", o programa responde com o último estado da máquina. Se for um pedido "RESET", o programa efetua um pedido de "HELLO" ao sistema central e reencaminha a resposta para o sistema de monitorização.
