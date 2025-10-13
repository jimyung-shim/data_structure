#include<stdio.h>
#include<stdlib.h>
#define MAX 1000
int stack[100];
int top=-1;

void push(int x){
    if (top<MAX-1){
        top++;
        stack[top]=x;
    } else {
        printf("Stack is full\n");        
    }
}

int pop(){
    if(top>=0){
        int temp=stack[top];
        top--;
        return temp;
    } else{
        printf("Stack is empty\n");
    }
}

typedef struct node{
    int data;
    struct node* next;
}queue;

queue* front=NULL;
queue* rear=NULL;

queue* new_node(int data){
    queue* newNode=(queue*)malloc(sizeof(queue));
    newNode->data=data;
    newNode->next=NULL;
    return newNode;
}

void enqueue(int x){
    queue* newNode=new_node(x);
    if(front==NULL&&rear==NULL){
        front=rear=newNode;
    } else {
        rear->next=newNode;
        rear=newNode;
    }
}

int dequeue(){
    if(front==NULL&&rear==NULL){
        printf("Queue is empty\n");
        return -1;
    } else {
        queue*temp=front;
        front=front->next;
        int value=temp->data;
        free(temp);
        return value;
    }
}

void display_queue(){
    if(front==NULL&&rear==NULL){
        printf("Queue is empty\n");
        return;
    } else {
        queue*current=front;
        while(current!=NULL){
            printf("%d\n",current->data);
            current=current->next;
        }
    }
}

int main(){
    push(1);
    push(2);
    push(3);
    push(4);
    push(5);

    while(top!=-1){
        enqueue(pop());
    }

    display_queue();
}
