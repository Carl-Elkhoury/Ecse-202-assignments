/* Name: Carl El khoury
 * mcgill ID: 26086273
 */

#include <stdio.h>

int main(int argc , char *argv[]){
int a, b, R, i;
char List[100];
char lut[]="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

//checking if the number of arguments is correct, if not print an error and terminate the program
	if (argc != 3 && argc !=2){
		printf("wrong number of arguments\n");
		return 0;
	}
// checking if the argument for the base is present if not, program defaults it to 2
	if (argc == 3){
	sscanf (argv[2] , "%d", &b);
	sscanf (argv[1] , "%d", &a);
	}
	else{
		b=2;
		sscanf (argv[1] , "%d", &a);
	}
// checking if the base is equal to 10 (if yes we don't need to do any calculations)
	if (b==10){
		printf( "The Base-10 form of %d is: %d", a , a);
		return 0;
	}
// checking if the base or the number is negative or too big if that is the case the program doesn't support it
	if (a<0 || b<0 ){
		printf("Error: the integer to convert or the base is negative or too big");
		return 0;
	}
// checking if the base is equal to zero if yes the operation is impossible
	if (b==0){
		printf("Error: the base is equal to zero");
		return 0;
	}
// doing the operations
int n=0 , q=a;
	while (a!=0){ // as long as the number to convert is not 0 keep dividing it by the base
		n++;
		R= a % b; // we find the remainder of the division between the number and the base
		a = a / b; // we assign to a the number to run at its next loop (if there is one)
		List[n] = lut[R]; // we assign the character corresponding to the remainder and we assign it to an array named List
	}
	printf("The Base-%d form of %d is: " , b , q); //report to user
	for (i=n ; i>0 ;i--){ //print the array List backwards
		printf("%c",List[i]);
	}
	printf("\n");

	return 0;
}
