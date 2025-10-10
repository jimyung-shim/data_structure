#include<stdio.h>
#include<stdlib.h>

typedef struct treeNode{
    int data;
    struct treeNode* leftChild;
    struct treeNode* rightChild;
}treeNode;

treeNode* root;

treeNode* new_node(int data){
    treeNode* temp=(treeNode*)malloc(sizeof(treeNode));
    temp->data=data;
    temp->leftChild=NULL;
    temp->rightChild=NULL;
}

treeNode* BST_insert(treeNode* node, int data){
    if(node==NULL){
        node=new_node(data);
        return node;
    }
    else {
        if (data < node->data){
            BST_insert(node->leftChild, data);
        }else{
            BST_insert(node->rightChild, data);
        }
    }
}

int getLevel(treeNode*root, int value, int level){
    if(root==NULL)return -1;
    if (root->data==value) return level;
    else {
        if(value < root->data)
            getLevel(root->leftChild, value, level+1);
        else getLevel(root->rightChild, value, level+1);
    }
}

int main(){
    root=BST_insert(root,5);
    root=BST_insert(root,3);
    root=BST_insert(root,7);
    root=BST_insert(root,1);
    root=BST_insert(root,4);
    root=BST_insert(root,6);
    root=BST_insert(root,9);

    int x=getLevel(root, 1,1);
    printf("%d ",x);

}