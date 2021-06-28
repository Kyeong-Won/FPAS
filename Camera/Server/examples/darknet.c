//./darknet detect cfg/yolov3.cfg yolov3.weights 54321
#include "darknet.h"

#include <time.h>
#include <stdlib.h>
#include <stdio.h>

//add
#include "queue.h"
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <pthread.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <dirent.h>

#define MAX_BUFSIZE 1000000
pthread_mutex_t mutex;
int num_client = 0;

struct Msg {
    int len;
    char buf[MAX_BUFSIZE]; 
};
//add end

extern void predict_classifier(char *datacfg, char *cfgfile, char *weightfile, char *filename, int top);
extern void test_detector(char *datacfg, char *cfgfile, char *weightfile, char *filename, float thresh, float hier_thresh, char *outfile, int fullscreen);
extern void run_yolo(int argc, char **argv);
extern void run_detector(int argc, char **argv);
extern void run_coco(int argc, char **argv);
extern void run_nightmare(int argc, char **argv);
extern void run_classifier(int argc, char **argv);
extern void run_regressor(int argc, char **argv);
extern void run_segmenter(int argc, char **argv);
extern void run_isegmenter(int argc, char **argv);
extern void run_char_rnn(int argc, char **argv);
extern void run_tag(int argc, char **argv);
extern void run_cifar(int argc, char **argv);
extern void run_go(int argc, char **argv);
extern void run_art(int argc, char **argv);
extern void run_super(int argc, char **argv);
extern void run_lsd(int argc, char **argv);

void average(int argc, char *argv[])
{
    char *cfgfile = argv[2];
    char *outfile = argv[3];
    gpu_index = -1;
    network *net = parse_network_cfg(cfgfile);
    network *sum = parse_network_cfg(cfgfile);

    char *weightfile = argv[4];   
    load_weights(sum, weightfile);

    int i, j;
    int n = argc - 5;
    for(i = 0; i < n; ++i){
        weightfile = argv[i+5];   
        load_weights(net, weightfile);
        for(j = 0; j < net->n; ++j){
            layer l = net->layers[j];
            layer out = sum->layers[j];
            if(l.type == CONVOLUTIONAL){
                int num = l.n*l.c*l.size*l.size;
                axpy_cpu(l.n, 1, l.biases, 1, out.biases, 1);
                axpy_cpu(num, 1, l.weights, 1, out.weights, 1);
                if(l.batch_normalize){
                    axpy_cpu(l.n, 1, l.scales, 1, out.scales, 1);
                    axpy_cpu(l.n, 1, l.rolling_mean, 1, out.rolling_mean, 1);
                    axpy_cpu(l.n, 1, l.rolling_variance, 1, out.rolling_variance, 1);
                }
            }
            if(l.type == CONNECTED){
                axpy_cpu(l.outputs, 1, l.biases, 1, out.biases, 1);
                axpy_cpu(l.outputs*l.inputs, 1, l.weights, 1, out.weights, 1);
            }
        }
    }
    n = n+1;
    for(j = 0; j < net->n; ++j){
        layer l = sum->layers[j];
        if(l.type == CONVOLUTIONAL){
            int num = l.n*l.c*l.size*l.size;
            scal_cpu(l.n, 1./n, l.biases, 1);
            scal_cpu(num, 1./n, l.weights, 1);
                if(l.batch_normalize){
                    scal_cpu(l.n, 1./n, l.scales, 1);
                    scal_cpu(l.n, 1./n, l.rolling_mean, 1);
                    scal_cpu(l.n, 1./n, l.rolling_variance, 1);
                }
        }
        if(l.type == CONNECTED){
            scal_cpu(l.outputs, 1./n, l.biases, 1);
            scal_cpu(l.outputs*l.inputs, 1./n, l.weights, 1);
        }
    }
    save_weights(sum, outfile);
}

long numops(network *net)
{
    int i;
    long ops = 0;
    for(i = 0; i < net->n; ++i){
        layer l = net->layers[i];
        if(l.type == CONVOLUTIONAL){
            ops += 2l * l.n * l.size*l.size*l.c/l.groups * l.out_h*l.out_w;
        } else if(l.type == CONNECTED){
            ops += 2l * l.inputs * l.outputs;
        } else if (l.type == RNN){
            ops += 2l * l.input_layer->inputs * l.input_layer->outputs;
            ops += 2l * l.self_layer->inputs * l.self_layer->outputs;
            ops += 2l * l.output_layer->inputs * l.output_layer->outputs;
        } else if (l.type == GRU){
            ops += 2l * l.uz->inputs * l.uz->outputs;
            ops += 2l * l.uh->inputs * l.uh->outputs;
            ops += 2l * l.ur->inputs * l.ur->outputs;
            ops += 2l * l.wz->inputs * l.wz->outputs;
            ops += 2l * l.wh->inputs * l.wh->outputs;
            ops += 2l * l.wr->inputs * l.wr->outputs;
        } else if (l.type == LSTM){
            ops += 2l * l.uf->inputs * l.uf->outputs;
            ops += 2l * l.ui->inputs * l.ui->outputs;
            ops += 2l * l.ug->inputs * l.ug->outputs;
            ops += 2l * l.uo->inputs * l.uo->outputs;
            ops += 2l * l.wf->inputs * l.wf->outputs;
            ops += 2l * l.wi->inputs * l.wi->outputs;
            ops += 2l * l.wg->inputs * l.wg->outputs;
            ops += 2l * l.wo->inputs * l.wo->outputs;
        }
    }
    return ops;
}

void speed(char *cfgfile, int tics)
{
    if (tics == 0) tics = 1000;
    network *net = parse_network_cfg(cfgfile);
    set_batch_network(net, 1);
    int i;
    double time=what_time_is_it_now();
    image im = make_image(net->w, net->h, net->c*net->batch);
    for(i = 0; i < tics; ++i){
        network_predict(net, im.data);
    }
    double t = what_time_is_it_now() - time;
    long ops = numops(net);
    printf("\n%d evals, %f Seconds\n", tics, t);
    printf("Floating Point Operations: %.2f Bn\n", (float)ops/1000000000.);
    printf("FLOPS: %.2f Bn\n", (float)ops/1000000000.*tics/t);
    printf("Speed: %f sec/eval\n", t/tics);
    printf("Speed: %f Hz\n", tics/t);
}

void operations(char *cfgfile)
{
    gpu_index = -1;
    network *net = parse_network_cfg(cfgfile);
    long ops = numops(net);
    printf("Floating Point Operations: %ld\n", ops);
    printf("Floating Point Operations: %.2f Bn\n", (float)ops/1000000000.);
}

void oneoff(char *cfgfile, char *weightfile, char *outfile)
{
    gpu_index = -1;
    network *net = parse_network_cfg(cfgfile);
    int oldn = net->layers[net->n - 2].n;
    int c = net->layers[net->n - 2].c;
    scal_cpu(oldn*c, .1, net->layers[net->n - 2].weights, 1);
    scal_cpu(oldn, 0, net->layers[net->n - 2].biases, 1);
    net->layers[net->n - 2].n = 11921;
    net->layers[net->n - 2].biases += 5;
    net->layers[net->n - 2].weights += 5*c;
    if(weightfile){
        load_weights(net, weightfile);
    }
    net->layers[net->n - 2].biases -= 5;
    net->layers[net->n - 2].weights -= 5*c;
    net->layers[net->n - 2].n = oldn;
    printf("%d\n", oldn);
    layer l = net->layers[net->n - 2];
    copy_cpu(l.n/3, l.biases, 1, l.biases +   l.n/3, 1);
    copy_cpu(l.n/3, l.biases, 1, l.biases + 2*l.n/3, 1);
    copy_cpu(l.n/3*l.c, l.weights, 1, l.weights +   l.n/3*l.c, 1);
    copy_cpu(l.n/3*l.c, l.weights, 1, l.weights + 2*l.n/3*l.c, 1);
    *net->seen = 0;
    save_weights(net, outfile);
}

void oneoff2(char *cfgfile, char *weightfile, char *outfile, int l)
{
    gpu_index = -1;
    network *net = parse_network_cfg(cfgfile);
    if(weightfile){
        load_weights_upto(net, weightfile, 0, net->n);
        load_weights_upto(net, weightfile, l, net->n);
    }
    *net->seen = 0;
    save_weights_upto(net, outfile, net->n);
}

void partial(char *cfgfile, char *weightfile, char *outfile, int max)
{
    gpu_index = -1;
    network *net = load_network(cfgfile, weightfile, 1);
    save_weights_upto(net, outfile, max);
}

void print_weights(char *cfgfile, char *weightfile, int n)
{
    gpu_index = -1;
    network *net = load_network(cfgfile, weightfile, 1);
    layer l = net->layers[n];
    int i, j;
    //printf("[");
    for(i = 0; i < l.n; ++i){
        //printf("[");
        for(j = 0; j < l.size*l.size*l.c; ++j){
            //if(j > 0) printf(",");
            printf("%g ", l.weights[i*l.size*l.size*l.c + j]);
        }
        printf("\n");
        //printf("]%s\n", (i == l.n-1)?"":",");
    }
    //printf("]");
}

void rescale_net(char *cfgfile, char *weightfile, char *outfile)
{
    gpu_index = -1;
    network *net = load_network(cfgfile, weightfile, 0);
    int i;
    for(i = 0; i < net->n; ++i){
        layer l = net->layers[i];
        if(l.type == CONVOLUTIONAL){
            rescale_weights(l, 2, -.5);
            break;
        }
    }
    save_weights(net, outfile);
}

void rgbgr_net(char *cfgfile, char *weightfile, char *outfile)
{
    gpu_index = -1;
    network *net = load_network(cfgfile, weightfile, 0);
    int i;
    for(i = 0; i < net->n; ++i){
        layer l = net->layers[i];
        if(l.type == CONVOLUTIONAL){
            rgbgr_weights(l);
            break;
        }
    }
    save_weights(net, outfile);
}

void reset_normalize_net(char *cfgfile, char *weightfile, char *outfile)
{
    gpu_index = -1;
    network *net = load_network(cfgfile, weightfile, 0);
    int i;
    for (i = 0; i < net->n; ++i) {
        layer l = net->layers[i];
        if (l.type == CONVOLUTIONAL && l.batch_normalize) {
            denormalize_convolutional_layer(l);
        }
        if (l.type == CONNECTED && l.batch_normalize) {
            denormalize_connected_layer(l);
        }
        if (l.type == GRU && l.batch_normalize) {
            denormalize_connected_layer(*l.input_z_layer);
            denormalize_connected_layer(*l.input_r_layer);
            denormalize_connected_layer(*l.input_h_layer);
            denormalize_connected_layer(*l.state_z_layer);
            denormalize_connected_layer(*l.state_r_layer);
            denormalize_connected_layer(*l.state_h_layer);
        }
    }
    save_weights(net, outfile);
}

layer normalize_layer(layer l, int n)
{
    int j;
    l.batch_normalize=1;
    l.scales = calloc(n, sizeof(float));
    for(j = 0; j < n; ++j){
        l.scales[j] = 1;
    }
    l.rolling_mean = calloc(n, sizeof(float));
    l.rolling_variance = calloc(n, sizeof(float));
    return l;
}

void normalize_net(char *cfgfile, char *weightfile, char *outfile)
{
    gpu_index = -1;
    network *net = load_network(cfgfile, weightfile, 0);
    int i;
    for(i = 0; i < net->n; ++i){
        layer l = net->layers[i];
        if(l.type == CONVOLUTIONAL && !l.batch_normalize){
            net->layers[i] = normalize_layer(l, l.n);
        }
        if (l.type == CONNECTED && !l.batch_normalize) {
            net->layers[i] = normalize_layer(l, l.outputs);
        }
        if (l.type == GRU && l.batch_normalize) {
            *l.input_z_layer = normalize_layer(*l.input_z_layer, l.input_z_layer->outputs);
            *l.input_r_layer = normalize_layer(*l.input_r_layer, l.input_r_layer->outputs);
            *l.input_h_layer = normalize_layer(*l.input_h_layer, l.input_h_layer->outputs);
            *l.state_z_layer = normalize_layer(*l.state_z_layer, l.state_z_layer->outputs);
            *l.state_r_layer = normalize_layer(*l.state_r_layer, l.state_r_layer->outputs);
            *l.state_h_layer = normalize_layer(*l.state_h_layer, l.state_h_layer->outputs);
            net->layers[i].batch_normalize=1;
        }
    }
    save_weights(net, outfile);
}

void statistics_net(char *cfgfile, char *weightfile)
{
    gpu_index = -1;
    network *net = load_network(cfgfile, weightfile, 0);
    int i;
    for (i = 0; i < net->n; ++i) {
        layer l = net->layers[i];
        if (l.type == CONNECTED && l.batch_normalize) {
            printf("Connected Layer %d\n", i);
            statistics_connected_layer(l);
        }
        if (l.type == GRU && l.batch_normalize) {
            printf("GRU Layer %d\n", i);
            printf("Input Z\n");
            statistics_connected_layer(*l.input_z_layer);
            printf("Input R\n");
            statistics_connected_layer(*l.input_r_layer);
            printf("Input H\n");
            statistics_connected_layer(*l.input_h_layer);
            printf("State Z\n");
            statistics_connected_layer(*l.state_z_layer);
            printf("State R\n");
            statistics_connected_layer(*l.state_r_layer);
            printf("State H\n");
            statistics_connected_layer(*l.state_h_layer);
        }
        printf("\n");
    }
}

void denormalize_net(char *cfgfile, char *weightfile, char *outfile)
{
    gpu_index = -1;
    network *net = load_network(cfgfile, weightfile, 0);
    int i;
    for (i = 0; i < net->n; ++i) {
        layer l = net->layers[i];
        if ((l.type == DECONVOLUTIONAL || l.type == CONVOLUTIONAL) && l.batch_normalize) {
            denormalize_convolutional_layer(l);
            net->layers[i].batch_normalize=0;
        }
        if (l.type == CONNECTED && l.batch_normalize) {
            denormalize_connected_layer(l);
            net->layers[i].batch_normalize=0;
        }
        if (l.type == GRU && l.batch_normalize) {
            denormalize_connected_layer(*l.input_z_layer);
            denormalize_connected_layer(*l.input_r_layer);
            denormalize_connected_layer(*l.input_h_layer);
            denormalize_connected_layer(*l.state_z_layer);
            denormalize_connected_layer(*l.state_r_layer);
            denormalize_connected_layer(*l.state_h_layer);
            l.input_z_layer->batch_normalize = 0;
            l.input_r_layer->batch_normalize = 0;
            l.input_h_layer->batch_normalize = 0;
            l.state_z_layer->batch_normalize = 0;
            l.state_r_layer->batch_normalize = 0;
            l.state_h_layer->batch_normalize = 0;
            net->layers[i].batch_normalize=0;
        }
    }
    save_weights(net, outfile);
}

void mkimg(char *cfgfile, char *weightfile, int h, int w, int num, char *prefix)
{
    network *net = load_network(cfgfile, weightfile, 0);
    image *ims = get_weights(net->layers[0]);
    int n = net->layers[0].n;
    int z;
    for(z = 0; z < num; ++z){
        image im = make_image(h, w, 3);
        fill_image(im, .5);
        int i;
        for(i = 0; i < 100; ++i){
            image r = copy_image(ims[rand()%n]);
            rotate_image_cw(r, rand()%4);
            random_distort_image(r, 1, 1.5, 1.5);
            int dx = rand()%(w-r.w);
            int dy = rand()%(h-r.h);
            ghost_image(r, im, dx, dy);
            free_image(r);
        }
        char buff[256];
        sprintf(buff, "%s/gen_%d", prefix, z);
        save_image(im, buff);
        free_image(im);
    }
}

void visualize(char *cfgfile, char *weightfile)
{
    network *net = load_network(cfgfile, weightfile, 0);
    visualize_network(net);
}

//add
void* thread_receive(void* arg){
    int s = *(int*)arg;
    free(arg);
    char filename[30];
    // char* filename = NULL;
    int fd;
    int size = 0;
    int len = 0;
    char buf[MAX_BUFSIZE];
    struct Msg msg;
    int count = 1;
    char place[20];
    int item_cnt; 
    
    if((recv(s, place, 20, 0)) < 0){
        printf("recv error");
        exit(1);
    }
    // printf("place: %s\n", place);
    // char first = place[0];

    struct dirent** pp_item;
   
    while (1)
    {
        memset(buf, 0, sizeof(buf));
        memset(msg.buf, 0, sizeof(msg.buf));
        len = 0;
        if(count >= 1001) count = 1;
        sprintf(filename, "test_data/%d_%s.jpg", count, place);
        if ((fd = open(filename, O_WRONLY|O_CREAT, 0644)) < 0){
            printf("file_open_error\n");
        }
        size = recv(s, &(msg.len), 4, 0);
        if (size == 0) {
            printf("======================================");
        }
        // printf("%d\n", ntohl(msg.len));
        // queue_print(&q);
        while((size = recv(s, msg.buf, ntohl(msg.len)-len, 0)) > 0){
            
            item_cnt = scandir("test_data", &pp_item, NULL, alphasort);
            if(item_cnt < 100){
                len += size;
                write(fd, msg.buf, size);
                
                if(len >= ntohl(msg.len)){
                    // item_cnt = scandir("test_data", &pp_item, NULL, alphasort);
                    // printf("%s\n", filename);
                    pthread_mutex_lock(&mutex);
                    enqueue(&q, filename); //0213 add
                    pthread_mutex_unlock(&mutex);
                    break;
                }
            }
            else{
                printf("------------------------else-------------------");
            }
        }
        if (size == 0) {
            printf("************************************");
        }
        close(fd);
        if(size <= 0) break;
        count++;
        sleep(0);
    }

    pthread_mutex_lock(&mutex);
    num_client--;
    pthread_mutex_unlock(&mutex);
    printf("Disconnected: %d\n", num_client);
    close(s);
    return NULL;
}

void* thread_listen(void* arg){
    int sockfd, newfd;
    struct sockaddr_in server_addr, client_addr;
    unsigned int sin_size;
    int num_client=0;
    pthread_t t_id;
    char *port = (char*)arg;
    int* socket_number;

    pthread_mutex_init(&mutex, NULL);

    sockfd = socket(PF_INET, SOCK_STREAM, 0);
    if (sockfd == -1)
    {
        perror("socket");
        return 0;
    }
    
    server_addr.sin_family = PF_INET;
    server_addr.sin_port = htons(atoi(port));
    server_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    memset(&(server_addr.sin_zero), 0, 8);
    
    if (bind(sockfd, (struct sockaddr *)&server_addr, sizeof(struct sockaddr_in)) == -1)
    {
        perror("bind");
        return 0;
    }

    if (listen(sockfd, 5) < 0)
    {
        perror("listen");
        return 0;
    }

    sin_size = sizeof(struct sockaddr_in);
    printf("waiting...\n");
         
    while(1){
        newfd = accept(sockfd, (struct sockaddr *)&client_addr, &sin_size);
        if (newfd == -1)
        {
            perror("accept");
            return 0;
        }
        printf("Connected: %s %d num_client: %d\n", inet_ntoa(client_addr.sin_addr), ntohs(client_addr.sin_port), num_client+1);

        pthread_mutex_lock(&mutex);
        num_client++;
        pthread_mutex_unlock(&mutex);

        socket_number = (int*)malloc(sizeof(int));
        *socket_number = newfd;
        pthread_create(&t_id, NULL, thread_receive, socket_number);
        pthread_detach(t_id);
    }
    close(sockfd);
    return NULL;
}

int main(int argc, char **argv)
{
    init_queue(&q);

    if(argc < 2){
        fprintf(stderr, "usage: %s <function>\n", argv[0]);
        return 0;
    }

    //add
    if(argv[4] == NULL){
        fprintf(stderr, "Usage: %s <PORT>\n", argv[0]);
        return 0;
    }
    //
    
    gpu_index = find_int_arg(argc, argv, "-i", 0);
    if(find_arg(argc, argv, "-nogpu")) {
        gpu_index = -1;
    }


    //add

    pthread_t t_id;
    pthread_create(&t_id, NULL, thread_listen, (void *)argv[4]);
    pthread_detach(t_id);

#ifndef GPU
    gpu_index = -1;
#else
    if(gpu_index >= 0){
        cuda_set_device(gpu_index);
    }
#endif

   if (0 == strcmp(argv[1], "detect")){
        float thresh = find_float_arg(argc, argv, "-thresh", .5);
        // char *filename = (argc > 4) ? argv[4]: 0;
        char *filename = NULL; // add
        char *outfile = find_char_arg(argc, argv, "-out", 0);
        int fullscreen = find_arg(argc, argv, "-fullscreen");
        test_detector("cfg/coco.data", argv[2], argv[3], filename, thresh, .5, outfile, fullscreen);
  
        
    }else {
        fprintf(stderr, "Not an option: %s\n", argv[1]);
    }

    return 0;
}

