#define MAX_QUEUE_SIZE 101

typedef char* element;
typedef struct{
    int front;
    int rear;
    int size;
    element data[MAX_QUEUE_SIZE];
} QueueType;

extern QueueType q;

// void error(char* message);
void init_queue(QueueType* q);
void queue_print(QueueType* q);
int is_full(QueueType* q);
int is_empty(QueueType* q);
int count(QueueType *q);
void enqueue(QueueType* q, element item);
element dequeue(QueueType* q);
element peek(QueueType* q);
