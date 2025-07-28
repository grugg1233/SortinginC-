#include <stdio.h>
#include <stdlib.h> 
#include <string.h> 

//Sorts.c  - rewriting Sorts.java in C programming language 


/*
// How to call
int x = 5, y = 10;
generic_swap(&x, &y, sizeof(int));
*/
void generic_swap(void* a, void* b, size_t size) {
    char* p = a;
    char* q = b;
    for (size_t i = 0; i < size; i++) {
        char temp = p[i];
        p[i] = q[i];
        q[i] = temp;
    }
}


int compare_ints(const void* a, const void* b ) {
    int x = *(const int*) a; 
    int y = *(const int*) b; 
            
    return (x > y) - (x < y) ; 
}



void mergeSort(void* a,  size_t size, size_t elemSize, int(*cmp)(const void* , const void*)) {
    char* p = a; 
    if(size > 1) {
        size_t mid = size / 2;
        char* left = malloc(mid * elemSize); 
        char* right = malloc((size - mid) * elemSize); 
        //copy the elements of the array into 2 seperate arrays left and right
        // void *memcpy(void *dest, const void *src, size_t n);
        memcpy(left, a, (mid * elemSize));
        memcpy(right, (char*)a + (mid * elemSize), ((size - mid) * elemSize));

        mergeSort(left, mid, elemSize, cmp); 
        mergeSort(right, (size - mid), elemSize, cmp); 

        int i; 
        int l = 0, r = 0; 

        for (i = 0; i < size && l < mid && r < (size - mid) ; ++i) {
            //left > right
            if( cmp( (char*) left + (l * elemSize) , (char*)right + (r * elemSize) )  <= 0) {
                memcpy((char*)a + i * elemSize, (char*)left + l*elemSize, elemSize);
                l++; 
            }
            //left > right
            else {
                 memcpy((char*)a + i * elemSize, (char*)right + r*elemSize, elemSize); 
                 r++; 
            }
        }
        if (i < size) { 
            if(l < mid) {
                while (l < mid) {
                    memcpy((char*)a + i * elemSize,(char*)left + l * elemSize, elemSize);
                    i++; 
                    l++;
                }
            }
            else {
                while (r < (size - mid)) {
                    memcpy((char*)a + i * elemSize,(char*)right + r * elemSize, elemSize);
                    i++; 
                    r++; 
                }
            }
        }
        free(left); 
        free(right); 
    }     
}

int main() {

    int a[] = {1,4,5,6,7,76,5,5,4,3,33,4,5,2,3,5,26,3,236,23,4};
    //clever trick to find size of an arry, size of the array = size of array / size of each element in the array 
    size_t size = sizeof(a) / sizeof(a[0]); 
    size_t elemSize = sizeof(a[0]); 

    //compare_ints can be passed with no arguments (const void* paramters) 
    mergeSort(a, size, elemSize, compare_ints);
    
    for(size_t i = 0; i < size; i++) {
        printf("%d ", a[i]);
    }
    printf("\n");


    return 0; 



}