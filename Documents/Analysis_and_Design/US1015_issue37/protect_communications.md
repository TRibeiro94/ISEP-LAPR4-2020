# Proteger comunicações
=======================================

# 1. Requisitos

**US1015** Como Gestor de Projeto, eu pretendo que as comunicações entre o simulador de máquina e o SCM estejam protegidas.


# 2. Análise

Para a resolução desta funcionalidade, a proteção será protegida nas comunicações *TCP*.
Na cadeia de eventos, que leva à comunicação C-Java (Máquinas-SCM), já implementada no Sprint anterior, devem ser feitas alterações, para que o socket usado, seja protegido através de TLS/SSL. Para isso será necessário uma Java KeyStore (JKS) em comum bem como um Client key e um Client pem, que são essencialmente repositórios de certificados de segurança. Neste sentido, daremos uso a bibliotecas específicas associadas a este protocolo. Irão ser criados vários certificados de "cliente" que por sua vez são adicionados ao JKS de Java que vai assumir o papel de "server" para de esta forma poder validar e aceitar os "clientes" que se vão tentar ligar. De forma aos métodos específicos da comunicação SSL serem reconhecidos pelo OS, são acrescentados "-lssl" "-lcrypto" no momento de "linkagem" do programa.

# 3. Design

Para resolver este requisito a solução é fazer uso do socket já criado na comunicação para criar um novo "socket SSL". Para esta comunicação funcionar, tanto do lado do simulador, como do lado do sistema central de mensagens tem de haver informações sobre os certificados da contra parte. Para obter estes certificados vai ser criado um script que gera ficheiros "client side" com as suas keys e vai adicionar estas keys a um ficheiro "server side" para que os clients possam ser reconhecidos pelo processo de handshake do SSL. Depois deste processo o novo "socket SSL" está pronto a ser usado e a comunicação está estabelecida. Resta apenas trocar as antigas funções read() e write() pelas novas SSL_read() e SSL_write() respetivamente que fazem uso da comunicação segura.
