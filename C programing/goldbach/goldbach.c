/* THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - DONG SUN YOON */


#include <stdio.h>
#include <stdlib.h>

typedef struct _seg {
	int bits[256];
	struct _seg *next, *prev;
}seg;

#define BITSPERSEG (8*256*sizeof(int))


void setBit(int N);
void clearAll();
seg* whichseg(int j);
int whichint(int j);
int whichbit(int j);
int marknonprime(int j);
void sieveOfE(int N);
int  countPrimes(int N);

int howmany;
seg *head;

void setBit(int N){

	seg *pt;
	int i;
	pt = head;
	for (i = 0; i<N/BITSPERSEG; i++){
		pt = pt->next;
	}
	N -= (N/BITSPERSEG)*BITSPERSEG;
	pt -> bits[N/32] |= 1 << (N%32);
}

void clearAll(){
	seg *pt;
	int i, j;
	pt = head;

	for (i=0; i< howmany; i++){
		for (j=0; j< 256; j++){
			pt->bits[j]=0;
		}
		pt=pt->next;
	}
}


seg* whichseg (int j){
	seg *pt;
	pt = head;
	int i;

	for (i = 0; i < (j/2)/BITSPERSEG; i++){ 
		pt = pt-> next;
	}

	return pt;
}

int whichint (int j){ 	return ((j/2)%BITSPERSEG)/32; }

int whichbit (int j){	return ((j/2)%BITSPERSEG)%32; }

int marknonprime (int j){
	seg *pt;
	int i, b;
	j = j*2+1;
	pt = whichseg(j);
	i = whichint(j);
	b = whichbit(j);

	return ( (pt -> bits[i] & (1 << (b))) == 0);

}

void sieveOfE(int N){

	clearAll();
	int i, j, k, n;
	setBit(0);
	if (N%2 != 0){ n = N/2; }
	else { n = (N-1)/2; }

	k = 1;
	while (k <= n){
		for (i = k; i <= n; i++){
			if (marknonprime(i)) { break; }
		}

		for (j = (i*3)+1; j <= n; j = j+(i*2)+1){
			setBit(j);
		}
		k = i + 1;
	}
}

int countPrimes(int N){ 
	int count, i, n;
	count = 0;
	if (N%2 != 0){ n = N/2; }
	else { n = (N-1)/2; }

	for (i =0; i <= n; i++){
		if (marknonprime(i)){ count++; }
	}
	return count;
}

void decompose(int evenNum){
	int I, K, solNum, inc;
	I = 3; 
	K = evenNum - I;
	solNum = 0;

	seg *front, *back;
	int intI, intK, bitI, bitK;

	front = whichseg(I);
	intI = whichint(I);
	bitI = whichbit(I);

	back = whichseg(K);
	intK = whichint(K);
	bitK = whichbit(K);

	while(I <= K){

		if (((front->bits[intI] & (1 << bitI)) == 0) && ((back->bits[intK] & (1 << bitK)) == 0) && bitI == bitK && intI == intK){
			solNum++;
			inc = 0;
			break;
		}
		
		if (( (front->bits[intI] & (1 << bitI)) != 0) || ((back->bits[intK] & (1 << bitK)) != 0)) {
			if (bitI != 31) { bitI +=1; }
			else{
				bitI = 0;
				if(intI != 255) {intI += 1;}
				else{
					intI = 0;
					front = front->next;
				}
			}

			if (bitK != 0){ bitK -=1; }
			else{
				bitK = 31;
				if(intK != 0) { intK-=1; }
				else{
					intK = 255;
					back = back->prev;
				}
			}
		
			I+=2;
			K-=2;
			inc++;
		}
			
		else {
			inc = 0;
			solNum++;
			if (bitI != 31) { bitI +=1; }
			else{
				bitI = 0;
				if(intI != 255) {intI += 1;}
				else{
					intI = 0;
					front = front->next;
				}
			}

			if (bitK != 0){ bitK -=1; }
			else{
				bitK = 31;
				if(intK != 0) { intK-=1; }
				else{
					intK = 255;
					back = back->prev;
				}
			}
			I+=2;
			K-=2;
			inc++;
		}
	}

	printf("Largest %d = %d + %d out of %d solutions\n", evenNum, I-2*inc, K+2*inc, solNum);
}


int main (int argc, char *argv[]){
	seg *pt;
	int i, N, evenNum;
	head = NULL;

	if(argc == 1){
		printf("Specify N\n");
		exit(1);
	}

	sscanf(argv[1], "%d", &N);

	if (N <= 5){
		printf("Please enter N > 5\n");
		exit(1);
	}

	howmany =  (N + BITSPERSEG -1)/BITSPERSEG;

	for (i = 0; i < howmany; i++){
   	   pt = (seg*) malloc(sizeof(seg)); 
   	   pt->next = head;            
   	   pt->prev = NULL;
   	  
   	   if(head != NULL) { head->prev = pt; }
 	   head = pt; 
   	}

	clearAll();
	printf("Calculating odd primes up to %d...\n", N);
	sieveOfE(N);
	printf("Found %d odd primes\n", countPrimes(N));
	printf("Enter Even Numbers > 5 for Goldbach Tests \n");

	do {
		if (evenNum == EOF){ break; }
		else if(evenNum%2 == 0 && evenNum <= N && evenNum > 5) { decompose(evenNum); }
	}while(scanf("%d", &evenNum) == 1);
}
