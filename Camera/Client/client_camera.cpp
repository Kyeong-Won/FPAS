//g++ -o ex ex.cpp `pkg-config opencv --cflags --libs`
#include <opencv2/opencv.hpp>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <pthread.h>
#include <netinet/in.h>
#include <fcntl.h>
#include <sys/stat.h>

using namespace cv;
using namespace std;

#define IP "127.0.0.1"
#define PORT 54321
#define BUF_SIZE 100000

struct Msg{
    int len;
    char *buf;
};

int main(int argc, char** argv) {
    int sockfd, newfd;
    struct sockaddr_in my_addr, their_addr;
    unsigned int sin_size;
    char place[20];
    
    struct Msg m;

    if(argc < 4){
        fprintf(stderr, "Usage: %s <PLACE> <IP> <PORT>\n", argv[0]);
        return 0;
    }

    sockfd = socket(PF_INET, SOCK_STREAM, 0);
    if (sockfd == -1)
    {
        perror("socket");
        return 0;
    }
    
    my_addr.sin_family = PF_INET;
    my_addr.sin_port = htons(atoi(argv[3]));
    my_addr.sin_addr.s_addr = inet_addr(argv[2]);
    memset(&(my_addr.sin_zero), 0, 8);

    sin_size = sizeof(struct sockaddr_in);
    
    if(connect(sockfd,(struct sockaddr*)&my_addr, sizeof(my_addr)) < 0){
        perror("connect");
        exit(1);
    }

    strcpy(place, argv[1]);
    if(send(sockfd, place, strlen(place), 0) < 0) {
        perror("place send");
        exit(1);
    }

    // 1. webcam open
    VideoCapture cap(0);
    if (!cap.isOpened())
    {
        printf("Can't open the camera");
        return 0;
    }

    int fd;
    int len;
    int temp;

    char file_name[20];
    struct stat st;
    Mat frame;
    
    m.buf = (char*)malloc(BUF_SIZE);
    sprintf(file_name, "client_cap%03d.jpg", sockfd);
    while(1){ 
        int size=0;
        // 2. save webcam img
        cap.read(frame);
        imwrite(file_name, frame);
        fd = open(file_name, O_RDONLY);

        // 3. open webcam img, save size
        fstat(fd, &st);
        m.len = htonl(st.st_size);
        send(sockfd, &(m.len), 4, 0);
        
        // 4. send img
        while((len = read(fd, m.buf, BUF_SIZE)) > 0) {
            size += len;
            send(sockfd, m.buf, len, 0);  
        }
        
        printf("%d\n", size);
        sleep(1);
    }      
    free(m.buf);
    close(fd); 
    close(sockfd);
    return 0;
}
