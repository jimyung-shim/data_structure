#include<stdio.h>
#include<stdlib.h>

typedef struct node{
    int data;
    struct node*next;
    struct node*prev;
}node;

struct node*next=NULL;
struct node*prev=NULL;

node* head=NULL;
node* tail=NULL;

int countNode=0;




void push(int data){
    node*newNode=(node*)malloc(sizeof(node));
    newNode->data=data;

    if(head==NULL){
        head=tail=newNode;
        countNode++;
    } else {
        tail->next=newNode;
        newNode->prev=tail;
        tail=newNode;
        countNode++;
    }
}

void insert(int index, int data){
    node*cur=head;
    if(index<1 && index > countNode){
        printf("Invalid access");
        return;
    }

    node*newNode=(node*)malloc(sizeof(node));
    newNode->data=data;

    for(int i=1;i<=countNode;i++){
        if(i==index){
            if(i==1){
                newNode->next=head;
                head->prev=newNode;
                head=newNode;
                break;
            } else if (i==countNode){
                tail->next=newNode;
                newNode->prev=tail;
                tail=newNode;
                break;
            } else{
                cur->prev->next=newNode;
                newNode->prev=cur->prev;
                cur->prev=newNode;
                newNode->next=cur;
            }
        }
        cur=cur->next;
    }

}

void delete_node(int index){
    node*cur=head;
    if(index<1 && index > countNode){
        printf("Invalid access");
    }

    for(int i=1;i<=countNode;i++){
        if(i==index){
            if(i==1){
                cur->next->prev=NULL;
                cur->next=NULL;
                free(cur);
                break;
            } else if (i==countNode){
                cur->prev->next=NULL;
                cur->prev=NULL;
                free(cur);
                break;
            } else{
                cur->prev->next=cur->next;
                cur->next->prev=cur->prev;
                free(cur);
                break;
            }
        }
        cur=cur->next;
    }
}

void show_list(){
    node*cur=head;

    if(head!=NULL){
        while(cur!=NULL){
            printf("%d ", cur->data);
            cur=cur->next;
        }
        printf("\n-----------------------------------\n");
    }
}

void show_reversed_list(){
    node*cur=tail;

    if(tail!=NULL){
        while(cur!=NULL){
            printf("%d ",cur->data);
            cur=cur->prev;
        }
        printf("\n-----------------------------------\n");
    }
}

int main(){
    push(1);
    push(3);
    push(25);
    push(65);
    push(34);
    push(26);
    push(7);

    // show_list();
    // show_reversed_list();

    // delete_node(3);
    
    // show_list();
    // show_reversed_list();

    insert(3,100);
    show_list();
    show_reversed_list();

    printf("%d",countNode);

}
