// TRY.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include "Header.h"
#include <vector>
struct node {
    int data;
    struct node* left;
    struct node* right;
};
bool hasPathSum(struct node* node, int sum)
{
    /* return true if we run
    out of tree and sum==0 */
    if (node == NULL) {
        return (sum == 0);
    }

    else {
        bool ans = 0;

        int subSum = sum - node->data;

        /* If we reach a leaf node
        and sum becomes 0 then
         * return true*/
        if (subSum == 0 && node->left == NULL
            && node->right == NULL)
            return 1;

        /* otherwise check both subtrees */
        if (node->left)
            ans = ans
            || hasPathSum(node->left, subSum);
        if (node->right)
            ans = ans
            || hasPathSum(node->right, subSum);

        return ans;
    }
}

/* UTILITY FUNCTIONS */
/* Helper function that
   allocates a new node with the
   given data and NULL left
   and right pointers. */
struct node* newnode(int data)
{
    struct node* node
        = (struct node*)malloc(sizeof(struct node));
    node->data = data;
    node->left = NULL;
    node->right = NULL;

    return (node);
}
int main()
{
    /*abc a;
    a.f();*/
    std::vector<abc> a;
}
// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
