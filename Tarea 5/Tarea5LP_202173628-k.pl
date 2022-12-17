%La funcion busca un elemento en la lista
%
% [_|Xs]: Corresponde a una lista
% N: Corresponde a la posicion del numero
% Y: Corresponde al numero que esta en la posicion
%
buscar([Y|_], 0, Y).
buscar([_|Xs], N, Y):-
    N2 is N - 1,
    buscar(Xs, N2, Y).


%La funcion guarda los numero de las posicione pares e impares
%
% [_|Xs]: Corresponde a una lista
% N: Corresponde a la posicion del numero
% Y: Corresponde al numero que esta en la posicion
%
sepparimpar([], [], []).
sepparimpar([X,Y|T], [X|Xs], [Y|Ys]):- 
    sepparimpar(T, Xs, Ys).



%La funcion busca/verifica algun rango dentro la lista
%
% L1: Corresponde a una lista
% Min: Corresponde al numero mayor del rango
% Max: Corresponde al numero menor del rango
%
todosrango([], 0, 0).
todosrango(L1, Min, Max):- 
    member(Min, L1), 
    V is Min+1, 
    member(Max, L1),
    Z is Min+2, 
    write('X = '), write(Min), write('  '), write('Y = '), write(Z), write('\n'), 
    length(L1,D), Z=< D-> todosrango(L1, V, Z);todosrango([], 0, 0).


%La funcion busca un elemento en la lista
%
% [_|Xs]: Corresponde a una lista
% N: Corresponde a la posicion del numero
% Y: Corresponde al numero que esta en la posicion
%
todosrango2(L1, Min, Max, C1, C2):-
length(L1, T),
M is T-1,
C2 < M-> buscar(L1, C1, B1), C3 is C2+1, buscar(L1, C3, B2), 
    (B1 >= B2-> C4 is C3+1, todosrango2(L1, Min, Max, C1, C4); todosrango2(L1, B1, B2, C1, C3));

K is C1+1,
todosrango2(L1, Min, Max, K, 0).



%La funcion busca/verifica el rango mas grande dentro la lista
%
% L1: Corresponde a una lista
% Min: Corresponde al numero mayor del rango
% Max: Corresponde al numero menor del rango
%
rangomax([], 0, 0).
rangomax(L1, Min, Max):- mayorque(L1, -1, 0, M), menorque(L1, 100, 0, Z), Max is M+1, Min is Z -> rangomax([], 0, 0); write('false').


%La funcion busca un numero mas grande de una lista
%
% Lista: Corresponde a una lista
% Mayor: Numero de referencia (Se utiliza el -1, pero se debe cambiar si hay algun numero menor)
% C: Corresponde al un contador
% Ma: Corresponde al numero mas grande
%
mayorque([], 0, 0, 0).
mayorque(Lista, Mayor, C, Ma):- buscar(Lista, C, X), 
    length(Lista, H), C < H -> 
    (X > Mayor-> P is C+1, mayorque(Lista, X, P, Ma); P is C+1, mayorque(Lista, Mayor, P, Ma)); 
    Ma is Mayor, mayorque([], 0, 0, 0). 


%La funcion busca un numero mas pequeño de una lista
%
% Lista: Corresponde a una lista
% Menor: Numero de referencia (Se utiliza el 100, pero se debe cambiar si hay algun numero mayor)
% C: Corresponde al un contador
% Mi: Corresponde al numero mas pequeño
%
menorque([], 0, 0, 0).
menorque(Lista, Menor, C, Mi):- buscar(Lista, C, X), 
    length(Lista, H), C < H -> 
    (X < Menor-> P is C+1, menorque(Lista, X, P, Mi); P is C+1, menorque(Lista, Menor, P, Mi)); 
    Mi is Menor, menorque([], 0, 0, 0).  


%La funcion genera lista(s) con numnero entre los rangos y posiciones pares e impares
%
% L: Corresponde a una lista
% Min: Corresponde al numero menor del rango
% Max: Corresponde al numero mayor del rango
%
chicograndechico([], 0, 0).
chicograndechico(T, Min, Max):- 
    P1 is round((Max+Min)/2),
    intervalo(Min,P1,P), 
    I1 is round(((Max+Min)/2)),intervalo(I1,Max,I),
    sepparimpar(T, P, I), 
    chicograndechico([], 0, 0).



%La funcion crea una lista a partir de un rango
%
% X: Corresponde al rango inicial
% Y: Corresponde al rango final
% [X|Xs]: Corresponde a una lista
%
intervalo(X,X,[X]).
intervalo(X,Y,[X|Xs]) :- X < Y, Z is X + 1, intervalo(Z,Y,Xs).