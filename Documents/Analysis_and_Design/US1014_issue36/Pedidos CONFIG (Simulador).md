# Pedidos CONFIG (Simulador de máquina)
=======================================

# 1. Requisitos

**US1014** Como Gestor de Projeto, eu pretendo que o simulador de máquina suporte a receção de ficheiros de configuração.

# 2. Análise

Deve ser concorrente (em C) com o resto da simulação da máquina. Para isto vai se usar threads de forma a ser possivel a receção de ficheiros de configuração sem interromper nenhum dos outros sistemas em funcionamento na máquina. A receção de ficheiros de configuração necessita comunicação entre duas plataformas distintas. De forma a estabelecer esta comunicação sem interromper os outros sistemas na máquina de funcionar em plenitude será preciso efetuar a criação de um socket entre o sistema de comunicação, que assume o papel de "cliente TCP", e, o simulador que assume o papel de "server TCP". O cliente pretende também que possa enviar estes ficheiros de configuração a qualquer momento logo o sistema deve ser posto a correr perpetuamente até a máquina ser desligada. A informação do ficheiro de configuração deve ser guardado de alguma forma. É criado um ficheiro aquando da receção do pedido configuração ao qual vai ser adicionado o conteúdo do pedido.


# 3. Design

Quando a máquina é ligada é criada uma thread para suportar paralelamente ao resto da máquina a receção de pedidos de ficheiro de configuração. É nesta thread que é criado o "server TCP" que fica à escuta de pedidos por parte do sistema de comunicação. Quando este receber esse pedido, verifica se o ID que vem com o pedido é o seu mesmo. Se corresponder, o pedido é aceite, guarda a informação num ficheiro e termina com uma resposta "ACK". Se não corresponder, o pedido é recusado com o envio de um "NACK". Para este serviço continuar sempre ligado, esta escuta de pedidos é posta dentro de um loop para no fim de responder a um pedido de configuração ficar imediatamente disponível para receber outro.
