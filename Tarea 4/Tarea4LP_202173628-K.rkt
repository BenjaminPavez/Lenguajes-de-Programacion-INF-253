#lang scheme

;;Inicio Funcion Inverso

;; La funcion añade elementos a una lista de 0 a n - 1
;;
;; lista : Lista vacia a llenar
;; n : Cantidad de numeros que tendra la lista 
(define (añado lista n)
    (if(>= n 0)
        (añado (cons n lista) (- n 1))
        lista
    )
)


;; La funcion busca un elemento en una lista
;;
;; elem : Elemento a buscar
;; lista : Lista de numeros 
(define busca (lambda (elem lista)
                (if (null? lista)
                    '()
                    (if (= (car lista) elem)
                        (cons elem (busca elem (cdr lista)))
                        (busca elem (cdr lista))
                    )
                ))
)


;; La funcion quita los elemetos de una lista respecto a la otra
;;
;; lista1 : Lista de numeros
;; lista2 : Otra lista de numeros
(define quitar (lambda (lista1 lista2)
                    (if (null? lista1)
                        '()
                        (if (null? (busca (car lista1) lista2))
                            (cons (car lista1) (quitar (cdr lista1) lista2))
                            (quitar (cdr lista1) lista2)
                        )
                    ))
                 
)


;; Funcion Principal
(define (inverso lista n)
    (quitar (añado '() (- n 1)) lista)
)

;;Termino Funcion Inverso


;;Inicio Funcion Umbral Simple

;; La funcion busca si hay sublistas para que quede solo en una unica lista
;;
;; val : Lista que contiene sublistas
(define (searchlist val)
    (cond ((null? val) '())
        ((pair? val) (append (searchlist (car val)) (searchlist (cdr val))))
        (else (list val))
    )
)


;; Lista auxiliar que almacena los datos de la(s) funciones
(define k(list ))


;; La funcion busca los elementos que estan en la lista que son mayores que el umbral (Simple)
;;
;; lista : Lista con numeros
;; largo : largo de la lista
;; umbral : Numero de referencia para realizar las comparaciones 
(define (mayor lista largo umbral)
    (if (negative? largo)
        k
        (if (>(list-ref lista largo) umbral)
            (searchlist (append  k (list (mayor lista (- largo 1) umbral)) (list largo)))
            (mayor lista (- largo 1) umbral)
        )
    )
)


;; La funcion busca los elementos que estan en la lista que son menores que el umbral (Simple)
;;
;; lista : Lista con numeros
;; largo : largo de la lista
;; umbral : Numero de referencia para realizar las comparaciones 
(define (menor lista largo umbral)
    (if (negative? largo)
        k
        (if (<(list-ref lista largo) umbral)
            (searchlist (append  k (list (menor lista (- largo 1) umbral)) (list largo)))
            (menor lista (- largo 1) umbral)
        )
    )
)
       

;; Funcion Principal (Simple)
(define (umbral_simple lista umbral tipo)
    (cond
        ((equal? tipo #\M) (mayor lista (- (length lista) 1) umbral))
        ((equal? tipo #\m) (menor lista (- (length lista) 1) umbral))
        (else "Notacion incorrecta")
    )
)
;;Termino Funcion Umbral Simple



;;Inicio Funcion Umbral Cola

;; La funcion elimina un numero de la lista
;;
;; numero : Numero que se desea eliminar
;; lista : Lista de numeros
(define (del numero lista)
    (if (null? lista)
        '()
        (if (equal? numero (car lista))
            (del numero (cdr lista))
            (cons (car lista) (del numero (cdr lista)))
        )
    )
)


;; La funcion busca los elementos que estan en la lista que son mayores que el umbral (Cola)
;;
;; lista : Lista con numeros
;; largo : largo de la lista
;; umbral : Numero de referencia para realizar las comparaciones
;; lista_pos : Lista que contiene una secuencia de numeros de 0 hasta el largo de la lista -1
(define (mayor_cola lista largo umbral lista_pos)
    (if (negative? largo)
        lista_pos
        (if (>(list-ref lista largo) umbral)
            (mayor_cola lista (- largo 1) umbral lista_pos) ;es cola
            (mayor_cola lista (- largo 1) umbral (del largo lista_pos)) ;es cola
        )
    )
)


;; La funcion busca los elementos que estan en la lista que son menores que el umbral (Cola)
;;
;; lista : Lista con numeros
;; largo : largo de la lista
;; umbral : Numero de referencia para realizar las comparaciones
;; lista_pos : Lista que contiene una secuencia de numeros de 0 hasta el largo de la lista -1
(define (min_cola lista largo umbral lista_pos)
    (if (negative? largo)
        lista_pos
        (if (<(list-ref lista largo) umbral)
            (min_cola lista (- largo 1) umbral lista_pos) ;es cola
            (min_cola lista (- largo 1) umbral (del largo lista_pos)) ;es cola
        )
    )
)
       

;; Funcion Principal (Cola)
(define (umbral_cola lista umbral tipo)
    (cond
        ((equal? tipo #\M) (mayor_cola lista (- (length lista) 1) umbral (añado '() (- (length lista) 1))))
        ((equal? tipo #\m) (min_cola lista (- (length lista) 1) umbral (añado '() (- (length lista) 1))))
        (else "Notacion incorrecta")
    )
)
;;Termino Funcion Umbral Cola



;;Inicio Funcion Modsel Simple

;; La funcion remplaza un elemento de la lista por otro
;;
;; lista : Lista con numeros
;; acamb : Numero que estamos buscando en la lista
;; remp : Numero que va a remplazar al encontrarlo
(define (reemplaza lista acamb remp)
    (if (null? lista)
        '()
        (if(= (car lista) acamb)
            (cons remp (reemplaza (cdr lista) acamb remp))
            (cons (car lista) (reemplaza (cdr lista) acamb remp))
        )
    )
)
                    

;; La funcion remplaza el numero encontrado en la posicion por una funcion lambda (Simple)
;;
;; lista : Lista con numeros
;; seleccion : Lista con los indices a buscar en lista
;; largo : Largo de la lista seleccion
;; f : Funcion lambda con la operacion a realizar al valor encontrado
(define (operatoria_simple lista seleccion largo f)
    (if (< largo 0 )
        lista
        (reemplaza (operatoria_simple lista seleccion (- largo 1) f) (list-ref lista (list-ref seleccion largo)) (f (list-ref lista (list-ref seleccion largo))))
    )
)


;; Funcion Principal (Simple)
(define (modsel_simple lista seleccion f)
    (operatoria_simple lista seleccion (- (length seleccion) 1) f)
    
)
;;Termino Funcion Modsel Simple



;;Inicio Funcion Modsel Cola

;; La funcion remplaza el numero encontrado en la posicion por una funcion lambda (Cola)
;;
;; lista : Lista con numeros
;; seleccion : Lista con los indices a buscar en lista
;; largo : Largo de la lista seleccion
;; f : Funcion lambda con la operacion a realizar al valor encontrado
(define (operatoria_cola lista seleccion largo f)
    (if (< largo 0 )
        lista
        (operatoria_cola (reemplaza lista (list-ref lista (list-ref seleccion largo)) (f (list-ref lista (list-ref seleccion largo)))) seleccion (- largo 1) f)
    )
)

;; Funcion Principal (Cola)
(define (modsel_cola lista seleccion f)
    (operatoria_cola lista seleccion (- (length seleccion) 1) f)
    
)
;;Termino Funcion Modsel Cola



;;Inicio Funcion Estables

;; La funcion verifica si el numero es mayor y si al aplicarle fM sigue siendo mayor que el umbral, aumenta en uno el contador
;;
;; lista : Lista con numeros
;; umbral : Numero de referencia para realizar las comparaciones
;; largo : Largo de la lista
;; cfuncion1 : Contador que almacena las veces que se a cumplido las condiciones
;; fM : Funcion lambda con la operacion a realizar al valor encontrado
(define (estf1 lista umbral largo cfuncion1 fM)
    (if (< largo 0 )
        cfuncion1
        (if (>(list-ref lista largo) umbral)
            (if (>(fM (list-ref lista largo)) umbral)
                (estf1 lista umbral (- largo 1) (+ cfuncion1 1) fM)
                (estf1 lista umbral (- largo 1) cfuncion1 fM))
            (estf1 lista umbral (- largo 1) cfuncion1 fM)
        )
    )
)


;; La funcion verifica si el numero es menor y si al aplicarle fm sigue siendo menor que el umbral, aumenta en uno el contador
;;
;; lista : Lista con numeros
;; umbral : Numero de referencia para realizar las comparaciones
;; largo : Largo de la lista
;; cfuncion2 : Contador que almacena las veces que se a cumplido las condiciones
;; fm : Funcion lambda con la operacion a realizar al valor encontrado
(define (estf2 lista umbral largo cfuncion2 fm)
    (if (< largo 0 )
        cfuncion2
        (if (<(list-ref lista largo) umbral)
            (if (<(fm (list-ref lista largo)) umbral)
                (estf2 lista umbral (- largo 1) (+ cfuncion2 1) fm)
                (estf2 lista umbral (- largo 1) cfuncion2 fm))
            (estf2 lista umbral (- largo 1) cfuncion2 fm)
        )
    )
)
          
            
;; x : Entero que representa la cantidad de numeros que cumplieron la condicion fM
(define x 0)

;; y : Entero que representa la cantidad de numeros que cumplieron la condicion fm
(define y 0)


;; Funcion Principal
(define (estables lista umbral fM fm)
    (list (estf1 lista umbral (-(length lista) 1) x fM) (estf2 lista umbral (-(length lista) 1) y fm))
  
)
;;Termino Funcion Estables


;;Inicio Funcion Query

;; Funcion Principal
(define (query lista pos op params)
    (cond
        ((equal? op 1) (umbral_simple (list-ref lista pos) (list-ref params 0) (eval (list-ref params 1))))
        ((equal? op 2) (modsel_simple (list-ref lista pos) (list-ref params 0) (eval (list-ref params 1))))
        ((equal? op 3) (estables (list-ref lista pos) (list-ref params 0) (eval (list-ref params 1)) (eval (list-ref params 2))))
    )
)
;;Termino Funcion Query
;;Termino Funciones