//Author: Sirinian Aram Emmanouil
#include <stdio.h>

int main(void)
{
  int a[10] ={0,2,4,6,8,7,6,4,2,0};
  int *pa = &a[1], *pb = &a[8], *pc;
  
  printf("1.   *(a + 6) = %d\n", *(a + 6));
  printf("2.    pb - pa = %d\n", pb - pa);
  printf("3.      pb[1] = %d\n", pb[1]);
  printf("4.     &pb[1] = %d\n", &pb[1]);
  printf("5.   *pa += 3 = %d\n", *pa += 3);
  printf("6. *(pb -= 3) = %d\n", *(pb -= 3));
  
  /* a) Erwtima */
  /*
   * 1. --> 6, Pigainei sto ekto stoixeio tou pinaka a kai ektipwnei to periexomeno
   * 2. --> 7, Ektipwnei tin diafora twn dieuthinsewn twn stoixeiwn a[8], a[1].
   *           Efoson prokeitai gia deiktes akeraiwn emfanizei tin diafora tous
   *           se akeraious (oxi se bytes).
   * 3. --> 1, To pb deixnei sto 8o stoixeio, ara to pb[1] tha exei ta dedomena
   *           mias thesis parakatw, diladi tou 9ou stoixeiou tou pinaka
   * 4. --> -1407101372, einai i dieuthinsi tou 9ou stoixeiou tou pinaka
	 *           kapou sth mnhmh.
   * 5. --> 5, To pa deixnei sto prwto stoixeio tou pinaka. Ara edw exoume to
   *           stoixeio ayto auksimeno kata 3.
   * 6. --> 7, To pb deixnei stin 8h thesi tou pinaka. To (pb - 3) deixnei stin
   *           5h thesi tou pinaka. Ara edw exoume ta periexomena tis 5hs thesis.
   */

  /* b) Erwtima */
  /* Anairw tis proigoumenes allages */
  *pa -= 3;
  pb += 3;
  
  printf("4.1.      pb[1] = %d\n", pb[1]); /* Apotelesma --> 0 */
  printf("5.1. *(pa += 3) = %d\n", *(pa += 3)); /* Apotelesma --> 8 */
  printf("6.1.    pb -= 3 = %d\n", pb -= 3); /* Apotelesma --> 254024868 */
  
  /* c) Erwtima */
  pc = a;          /* Isodynamo me pc = &a[0]; */
  printf("pc=%d, *pc=%d\n", pc, *pc);
  
  return 0;
}
