% Sirinian Aram Emmanouil A.M. 2537
%-----------------------------------------------------------------------------------------

%-- ASKHSH 1

gcdF(0,P,P).

gcdF(A1,P,R) :- A1 < P,
                T is P mod A1,
                gcdF(T,A1,R).

gcdF(A1,P,R) :- A1 >= P,
                T is A1 mod P,
                gcdF(T,P,R).

fracSmpl(A,P,Z) :- gcdF(A,P,R),
                   TempA is A/R,
                   TempP is P/R,
                   Z = TempA/TempP.
                   

fracSum(X,Y,Z) :- A1/B=X,
                  C/D=Y,
                  B =:= D,
                  A is A1+C,
                  fracSmpl(A,B,Z).

fracSum(X,Y,Z) :- A1/B=X,
                  C/D=Y,
                  B =\= D, /*no nead*/
                  Tempa1 is A1*D,
                  Tempb is B*D,
                  Tempc is C*B,
                  Tempd is D*B,
                  A is Tempa1+Tempc,
                  P is Tempb,
                  fracSmpl(A,P,Z).







%-----------------------------------------------------------------------------------------
     
%-- ASKHSH 2

sumdH(N,T,S1) :- T > N//2,
                 S1 is 0.

sumdH(N,T,S1) :- T =< N/2,
                 0 =\= N mod T,
                 G is T+1,
                 sumdH(N,G,S1).

sumdH(N,T,S1) :- T =< N/2,
                 0 =:= N mod T,
                 G is T+1,
                 sumdH(N,G,S2),
                 S1 is T+S2.

sumd(N,S) :- sumdH(N,1,S1),
             S is S1+N.










%-----------------------------------------------------------------------------------------
     
%-- ASKHSH 3

alpha(L,S) :- fail.










%-----------------------------------------------------------------------------------------
     
%-- ASKHSH 4


expand(L,E) :- fail.









%-----------------------------------------------------------------------------------------
     
%-- ASKHSH 5                                   


puzzle(L,N) :- fail.



