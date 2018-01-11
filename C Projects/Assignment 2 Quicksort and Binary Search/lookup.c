/*

 Assignment 2 ECSE202
 Name: Carl El Khoury
 Mcgill ID: 260806273

 */


#define MAXRECORDS 100
#define MAXNAMELENGTH 15
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//Define structure to hold student data
struct StudentRecord
{
	char FirstNames[MAXNAMELENGTH];
	char LastNames[MAXNAMELENGTH];
	int IDNums;
	int Marks;
};

int binarysearch(struct StudentRecord *pSRecords[], int lowerboundary, int higherboundary, char* target){
	int middlepoint; // create an middle point

	while (lowerboundary <= higherboundary)
	{
		middlepoint = (lowerboundary + higherboundary)/2; //assign to the middle point the average of higherboundary and lowerboundary of the array
		if (strcmp(target, pSRecords[middlepoint]->LastNames) == 0) //compare the Last name from the  structure corresponding to the middle point with the targeted last name
			return middlepoint;// if they are equal just return the value of the middlepoint which is the index value of the result

		else if (strcmp(target, pSRecords[middlepoint]->LastNames) > 0) //check if the target is greater in alphabetic order then the lastname
			lowerboundary = middlepoint + 1;//if yes the lower boundary is now the middle point +1
		else
			higherboundary = middlepoint - 1;// if not the higher boundary is now the middle point -1
	}
	return -1;//if we exist the while loop without an return this means that we didn't find the last name so return -1 to indicate the output
}

void swap(struct StudentRecord *SRecords[], int i, int j)
{
	struct StudentRecord *temp = SRecords[i]; // temp is an temporary pointer that stores the pointer to swap
	SRecords[i] = SRecords[j]; //the first pointer is assigned the value of the other
	SRecords[j] = temp;// the second one is assigned the value of temp
}
int partitioning(struct StudentRecord *Srecords[], int start , int finish)
{
	char *pivot = Srecords[start]->LastNames; //take last name of the first array of struct as the pivot
	int i = start;//i is defined as the first index of the array to sort
	int j = finish;//j is defined as the last index of the array to sort
	while(1)
	{
		while (strcmp(pivot, Srecords[i]->LastNames)>0) //While the Last name of the index i is lesser then the pivot
		{
			i = i + 1; // increment i by one so you check the next index on the next loop, leaving the structure untouched
		}
		while (strcmp(pivot, Srecords[j]->LastNames)<0)//While the Last name of the index j is greater then the pivot
		{
			j = j - 1;// increment j by one so you check the next index on the next loop, leaving the structure untouched
		}
		if (i >= j){ //when i>=j (when the indexes meant) return i which will be the middle point of the sorted array
		    return i;
	}


		swap(Srecords,i,j); //else swap the two pointers and start the while loop again

	}
}

void quicksort (struct StudentRecord *SRecords[], int start , int finish)
{

	if(start < finish)
	{
	int p = partitioning(SRecords,start,finish); //partition the array from start to finish and assign to p the middle point
	quicksort(SRecords,start,p);//recall the function but with the new array index deviding it into 2 seperate arrays
	quicksort(SRecords,p+1,finish);
	}
}

int main(int argc, char * argv[]) {

	struct StudentRecord SRecords[MAXRECORDS];
    	int numrecords, nummarks, recordnum;
	
    if (argc != 4) {
        printf("wrong number of arguments"); // checking for the number of arguments
        return 0;
    }
	//Read in Names and ID data
	FILE * NamesIDs;
	if((NamesIDs = fopen(argv[1], "r")) == NULL){
		printf("Can't read from file %s\n", argv[1]);
		exit(1);
	}
	
	numrecords=0;
    	while (fscanf(NamesIDs,"%s%s%d",&(SRecords[numrecords].FirstNames[0]),
		      				&(SRecords[numrecords].LastNames[0]),
		      				&(SRecords[numrecords].IDNums)) != EOF) {
	  numrecords++;
 	}
	  
	fclose(NamesIDs);
	
	//Read in marks data
	FILE * marks;
	if((marks = fopen(argv[2], "r")) == NULL){
		printf("Can't read from file %s\n", argv[2]);
		exit(1);
	}
	nummarks=0;
	while(fscanf(marks,"%d",&(SRecords[nummarks].Marks)) != EOF) {
	    nummarks++;
	}
	
	fclose(marks);
    struct StudentRecord *pSRecords[MAXRECORDS];
    for (int i=0;i<numrecords;i++) {
        pSRecords[i]=&SRecords[i];
    }
    quicksort(pSRecords,0,numrecords-1); // quicksort the array SRecords from the index value of 0 to the number of records - 1

	int index = binarysearch(pSRecords, 0 , numrecords-1, argv[3]); //find the index value of the result if they is any
	if (index != -1) // check if we found an answer
	printf("The following record was found:\nName: %s %s \nStudent ID: %d \nStudent Grade: %d \n", pSRecords[index]->LastNames , pSRecords[index]->FirstNames , (*pSRecords[index]).IDNums, pSRecords[index]->Marks); //print the answer
	else
		printf ("No record found for student with last name %s \n",argv[3]); //print that we didn't find the answer

}
