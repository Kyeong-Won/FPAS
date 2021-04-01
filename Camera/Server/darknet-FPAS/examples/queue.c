#include <stdio.h>
#include "queue.h"
#include <stdlib.h>
#include <string.h>
#include <string.h>

QueueType q;

// void error(char* message) {fprintf(stderr, "%s\n", message); exit(1);}
void init_queue(QueueType* q) {q->rear = 0; q->front =0; q->size=0;}
void queue_print(QueueType* q)
{
    printf("QUEUE(front=%d rear=%d) = ", q->front, q->rear);
    if(!is_empty(q)){
        int i = q->front;
        do{
            i = (i+1) % (MAX_QUEUE_SIZE);
            printf("%s | ", q->data[i]);
            if(i == q->rear)
                break;
        }while(i != q->front);
    }
    printf("\n");
}
int is_full(QueueType* q) {return ((q->rear + 1) % MAX_QUEUE_SIZE ==  q->front);}
int is_empty(QueueType* q) {return (q->front == q->rear);}
int count(QueueType* q){return q->size;}
void enqueue(QueueType* q, element item){
    if(is_full(q)) {fprintf(stderr, "큐가 포화 상태입니다\n");}
    q->rear = (q->rear + 1) % MAX_QUEUE_SIZE;
    q->data[q->rear] = (char *)malloc(sizeof(char) * 30); //add
    strcpy(q->data[q->rear], item);
    q->size++;
}
element dequeue(QueueType* q){
    if(is_full(q)) {fprintf(stderr, "큐가 포화 상태입니다\n");}
    q->front = (q->front + 1) % MAX_QUEUE_SIZE;
    //free(q->data[q->front]);
    q->size--;
    return q->data[q->front];
}
element peek(QueueType* q){
    if(is_empty(q)) {fprintf(stderr, "큐가 빈  상태입니다\n");}
    return q->data[(q->front + 1) % MAX_QUEUE_SIZE];
}

// int main(){
//     return 0;
// }
