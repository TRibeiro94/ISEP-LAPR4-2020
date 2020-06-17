# Pedidos RESET (Simulador de máquina)
=======================================

# 1. Requisitos

**US1016** Como Gestor de Projeto, eu pretendo que o simulador de máquina suporte pedidos de reinicialização (reset) do seu estado.


# 2. Análise

Deve ser concorrente (em C) com o resto da simulação da máquina e o estado deve ser partilhado entre threads. Como vai haver recursos a partilhar a mesma informação vai ser efetuada a criação de controlo de acesso exclusivo a essa informação.
Este requisito necessita de comunicação entre três plataformas distintas. De forma a estabelecer esta comunicação seriam precisos de efetuar a criação de dois sockets. Um entre o simulador e o sistema central e um segundo entre o sistema de monitorização e o simulador, mas como ambos os sockets já se encontram implementados por necessidade de requisitos anteriores faremos uso do mesmo. Como o pedido "RESET" faz com que o simulador envie um pedido "HELLO" em TCP ao sistema central é preciso a thread UDP ter conhecimento do socket TCP criado para comunicar com o sistema central. A thread UDP precisa também de ter controlo de acesso exclusivo de informação, logo, a variável criada na thread principal deve ser passada para a thread UDP.

# 3. Design

É usado o mesmo socket já criado para escutar por pedidos "UDP". Como os pedidos vêm com uma identificação do seu pedido, estes são respondidos em conformidade. Se a sua identificação vier com o id "3" (pedido RESET) é então feito um pedido de "HELLO" ao serviço de comunicação. A resposta deste vai ser a resposta encaminhada para o sistema de monitorização. A solução a conceber para termos então acesso à variável de controlo de acesso exclusivo e acesso ao socket TCP criado na thread principal deve partir pela criação de uma estrutura com dois campos. Um para o descritivo do socket TCP e um para o apontador da variável de controlo de acesso. Esta estrutura deve ser passada por parâmetro aquando da criação da thread UDP para esta poder funcionar em plenitude.
