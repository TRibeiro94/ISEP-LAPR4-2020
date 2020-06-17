#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <strings.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/ioctl.h>
#include <netdb.h>
#include <pthread.h>
#include <openssl/crypto.h>
#include <openssl/ssl.h>
#include <openssl/err.h>
#include <openssl/conf.h>
#include <openssl/x509.h>

#define BUF_SIZE 300
#define SERVER_PORT1 "31222"
#define SERVER_PORT2 "31206"
#define SERVER_PORT3 "31207"
#define GETS(B,S) {fgets(B,S-2,stdin);B[strlen(B)-1]=0;}

#define SERVER_SSL_CERT_FILE "serverJ.pem"

unsigned char last_state = 150;
unsigned short id;

typedef struct {
	SSL* sock;
    pthread_mutex_t *mux;
} info;

void* udpServer(void *arg);
void* tcpServer(void *arg);
void sendHelloMessage(SSL* sock);

// read a string from stdin protecting buffer overflow
int main(int argc, char **argv) {
    id = atoi(argv[1]);
    info information;
    pthread_mutex_t mux;
    int mutex = pthread_mutex_init(&mux, NULL);
    if(mutex != 0){
        printf("Failed to create mutex\n"); exit(1);
    }
    information.mux = &mux;
    //inicia o udp "server"
    pthread_t threadUdp;

    //inicia o tcp "server"
    pthread_t threadTcpServer;
    pthread_create(&threadTcpServer, NULL, tcpServer, NULL);
    
    
    //tcp "client"
    int err, sock, connected = 1;
    char line[BUF_SIZE];
    struct addrinfo req, *list;
    bzero((char *)&req,sizeof(req));
    // let getaddrinfo set the family depending on the supplied server address
    req.ai_family = AF_UNSPEC;
    req.ai_socktype = SOCK_STREAM;
    err=getaddrinfo("10.8.210.108", SERVER_PORT1 , &req, &list);
    if(err) {
        printf("Failed to get server address, error: %s\n",gai_strerror(err)); exit(1);
    }
    sock=socket(list->ai_family,list->ai_socktype,list->ai_protocol);
    if(sock==-1) {
        perror("Failed to open socket"); freeaddrinfo(list); exit(1);
    }
    printf("Connecting\n");
    if(connect(sock,(struct sockaddr *)list->ai_addr, list->ai_addrlen)==-1) {
        perror("Failed connect1"); freeaddrinfo(list); close(sock); connected = 0;
    }
    if (connected){

        const SSL_METHOD *method=SSLv23_client_method();
        SSL_CTX *ctx = SSL_CTX_new(method);
            
        strcpy(line,"client1");strcat(line,".pem");
        SSL_CTX_use_certificate_file(ctx, line, SSL_FILETYPE_PEM);
        strcpy(line,"client1");strcat(line,".key");
        SSL_CTX_use_PrivateKey_file(ctx, line, SSL_FILETYPE_PEM);
        if (!SSL_CTX_check_private_key(ctx)) {
            puts("Error loading client's certificate/key");
            close(sock);
            exit(1);
        }

        SSL_CTX_set_verify(ctx, SSL_VERIFY_PEER,NULL);
        // THE SERVER'S CERTIFICATE IS TRUSTED
        SSL_CTX_load_verify_locations(ctx,SERVER_SSL_CERT_FILE,NULL);
        // Restrict TLS version and cypher suites
        SSL_CTX_set_min_proto_version(ctx,TLS1_2_VERSION);
        SSL_CTX_set_cipher_list(ctx, "HIGH:!aNULL:!kRSA:!PSK:!SRP:!MD5:!RC4");
        SSL *sslConn = SSL_new(ctx);
        SSL_set_fd(sslConn, sock);
        if(SSL_connect(sslConn)!=1) {
            puts("TLS handshake error");
            SSL_free(sslConn);
            close(sock);
            exit(1);
        }
        printf("TLS version: %s\nCypher suite: %s\n",
        SSL_get_cipher_version(sslConn),SSL_get_cipher(sslConn));
        if(SSL_get_verify_result(sslConn)!=X509_V_OK) {
            puts("Sorry: invalid server certificate");
            SSL_free(sslConn);
            close(sock);
            exit(1);
        }

        X509* cert=SSL_get_peer_certificate(sslConn);
        X509_free(cert);
        if(cert==NULL) {
            puts("Sorry: no certificate provided by the server");
            SSL_free(sslConn);
            close(sock);
            exit(1);
        }
        information.sock=sslConn;

        pthread_create(&threadUdp, NULL, udpServer, (void*)&information);

        freeaddrinfo(list);
    
        printf("Connected to SCM.\n");

        //hello msg
        pthread_mutex_lock(&mux);
        sendHelloMessage(sslConn);

        //wait for ack
        char leitura[6];
        SSL_read(sslConn, leitura, 6);
        pthread_mutex_unlock(&mux);
        unsigned char abc = (unsigned char)leitura[1];
        pthread_mutex_lock(&mux);
        last_state = abc;
        if (abc == 151){
            return 1;
        }
        pthread_mutex_unlock(&mux);

        pthread_mutex_lock(&mux);
        if(last_state == 150){
            //MSG message
            FILE *fp;
            char line[6];
            line[1] = 1;
            char temp[255];
            fp = fopen("Mensagens.DD4", "r");
            while(fgets(temp, 255, fp) != NULL){ //pode ser que precise de um select a cada escritura para verificar se posso escrever mais
                printf("Imprimi\n");
                unsigned short size = strlen(temp);
                line[4] = size & 0xFF;
                line[5] = size & 0xFF00;
                SSL_write(sslConn, line, 6); //escreve o cabecalho para o sistema poder saber quantos bytes de mensagem sÃ£o
                SSL_write(sslConn, temp, size); //escreve a mensagem
                printf("Escrevi\n");
                SSL_read(sslConn, leitura, 6);
                printf("Li\n");
                unsigned char abc = (unsigned char)leitura[1];
                last_state = abc;
                if (abc == 151){
                    fclose(fp);
                    break;
                }
                
            }
        }
        pthread_mutex_unlock(&mux);
        pthread_join(threadUdp, NULL);
        close(sock);
    }
    pthread_join(threadTcpServer, NULL);
    pthread_mutex_destroy(&mux);
    exit(0);
}


void* udpServer(void *arg) {
    //receber o socket tcp por parametro
    SSL* tcpSocket = ((info*)arg)->sock;
    pthread_mutex_t mux = *(((info*)arg)->mux);

    struct sockaddr_storage client;
    int err, sock;
    unsigned int adl;
    char linha[BUF_SIZE];
    char cliIPtext[BUF_SIZE], cliPortText[BUF_SIZE];
    struct addrinfo req, *list;
    bzero((char *)&req,sizeof(req));
    // request a IPv6 local address will allow both IPv4 and IPv6 clients to use it
    req.ai_family = AF_INET6;
    req.ai_socktype = SOCK_DGRAM;
    req.ai_flags = AI_PASSIVE; // local address
    err=getaddrinfo(NULL, SERVER_PORT2 , &req, &list);
    if(err) {
        printf("Failed to get local address, error: %s\n",gai_strerror(err)); exit(1);
    }
    sock=socket(list->ai_family,list->ai_socktype,list->ai_protocol);
    if(sock==-1) {
        perror("Failed to open socket"); freeaddrinfo(list); exit(1);
    }
    if(bind(sock,(struct sockaddr *)list->ai_addr, list->ai_addrlen)==-1) {
        perror("Bind failed");close(sock);freeaddrinfo(list);exit(1);
    }
    freeaddrinfo(list);
    puts("Listening for UDP requests (IPv6/IPv4). Use CTRL+C to terminate the server");
    adl=sizeof(client);
    while(1) {
        recvfrom(sock,linha,BUF_SIZE,0,(struct sockaddr *)&client,&adl);
        printf("Conexão\n");
        unsigned char code = (unsigned char)linha[1];
        if (code == 3){
		    printf("Resseting machine\n");
		    sleep(5);
		    printf("On\n");
            pthread_mutex_lock(&mux);
            sendHelloMessage(tcpSocket);
            char leitura[6];
            SSL_read(tcpSocket, leitura, 6);
            pthread_mutex_unlock(&mux);
            pthread_mutex_lock(&mux);
            last_state = (unsigned char)linha[1];
            pthread_mutex_unlock(&mux);
            sendto(sock,leitura,sizeof(leitura),0,(struct sockaddr *)&client,adl);
        }
        if (code == 0){
            //mandar o ultimo estado da maquina
            pthread_mutex_lock(&mux);
            if(last_state == 150){
		        sendto(sock,"150",3,0,(struct sockaddr *)&client,adl);
	        }
	        if(last_state == 151){
		        sendto(sock,"151",3,0,(struct sockaddr *)&client,adl);
            }
            pthread_mutex_unlock(&mux);
        }
        if(!getnameinfo((struct sockaddr *)&client,adl, cliIPtext,BUF_SIZE,cliPortText,BUF_SIZE,NI_NUMERICHOST|NI_NUMERICSERV)){
            printf("Request from node %s, port number %s\n", cliIPtext, cliPortText);
        } else {
            puts("Got request, but failed to get client address");
        }
    }
    close(sock);
    pthread_exit(NULL);
}

void* tcpServer(void *arg){

    struct sockaddr_storage from;
    int err, newSock, sock;
    unsigned int adl;
    char cliIPtext[BUF_SIZE], cliPortText[BUF_SIZE];
    struct addrinfo req, *list;
    fd_set rfds_master;
    bzero((char *)&req,sizeof(req));
    // requesting a IPv6 local address will allow both IPv4 and IPv6 clients to use it
    req.ai_family = AF_INET6;
    req.ai_socktype = SOCK_STREAM; // TCP
    req.ai_flags = AI_PASSIVE; // local address
    err=getaddrinfo(NULL, SERVER_PORT3 , &req, &list);
    if(err) {
        printf("Failed to get local address, error: %s\n",gai_strerror(err)); exit(1);
    }
    sock=socket(list->ai_family,list->ai_socktype,list->ai_protocol);
    if(sock==-1) {
        perror("Failed to open socket"); freeaddrinfo(list); exit(1);
    }
    if(bind(sock,(struct sockaddr *)list->ai_addr, list->ai_addrlen)==-1) {
        perror("Bind failed");close(sock);freeaddrinfo(list);exit(1);
    }
    freeaddrinfo(list);
    listen(sock,SOMAXCONN);
    FD_ZERO(&rfds_master);
    FD_SET(sock,&rfds_master);
    puts("Accepting TCP connections (IPv6/IPv4). Use CTRL+C to terminate the server");
    adl=sizeof(from);
    //loop aqui penso eu
    while(1){
        newSock=accept(sock,(struct sockaddr *)&from,&adl);
        getnameinfo((struct sockaddr *)&from,adl,cliIPtext,BUF_SIZE,cliPortText,BUF_SIZE,NI_NUMERICHOST|NI_NUMERICSERV);
        printf("New conn: %s, port %s\n", cliIPtext, cliPortText);

        char leitura[6];
        read(newSock, leitura, 6);
        unsigned short abc = (unsigned short)leitura[2];
        abc += (256*(unsigned short)leitura[3]);

        if (abc == id){
            unsigned short dataLength = (unsigned short)leitura[4];
            dataLength += (256*(unsigned short)leitura[5]);
            char config[dataLength];
            //read config
            read(newSock, config, dataLength);
            //put the config in a file
            FILE *fp;
            fp = fopen ("file_name.txt", "w");
            fputs(config, fp);
            fclose(fp);
            //write ack
            char line[6];
            line[0] = 0;
            line[1] = 150;
            line[2] = id & 0xFF;
            line[3] = id & 0xFF00;
            line[4] = 0;
            line[5] = 0;
            write(newSock, line, sizeof(line));
        } else {
            //write nack
            char line[6];
            line[0] = 0;
            line[1] = 151;
            line[2] = id & 0xFF;
            line[3] = id & 0xFF00;
            line[4] = 0;
            line[5] = 0;
            write(newSock, line, sizeof(line));
        }
    }
    pthread_exit(NULL);
}

void sendHelloMessage(SSL* sock){
    char line[6];
    //unsigned short id = 123;
    line[0] = 0;
    line[1] = 0;
    line[2] = id & 0xFF;
    line[3] = id & 0xFF00;
    line[4] = 0;
    line[5] = 0;
    SSL_write(sock, line, sizeof(line));
}