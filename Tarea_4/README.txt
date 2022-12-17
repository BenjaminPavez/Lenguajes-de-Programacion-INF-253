Nombre: Benjamin Alejandro Pavez Ortiz

Rol USM: 202173628-K

Entorno de trabajo: DrRacket version 8.6[cs]

SO: Windows 11

Instrucciones de Uso

1) El programa funciona correctamente con todos los ejemplos entregados en el pdf

2) La gran mayoria de las funciones utilizan funciones auxiliares, estas se encuentra detalladamente especificadas en el archivo .rkt

3) La indentacion se realizo con tabulador

4) Con las especiicaciones anteriormente mencionadas el programa debe funcionar correctamente

5) Los ejemplos probados con su respeciva respuesta fueron:

> (inverso '(1 3 7) 10)
(0 2 4 5 6 8 9)

> (umbral_simple '(15 2 1 3 27 5 10) 5 #\M)
(0 4 6)

> (umbral_simple '(15 2 1 3 27 5 10) 5 #\m)
(1 2 3)

> (umbral_cola '(15 2 1 3 27 5 10) 5 #\M)
(0 4 6)

> (umbral_cola '(15 2 1 3 27 5 10) 5 #\m)
(1 2 3)

> (modsel_simple '(15 2 1 3 27 5 10) '(0 4 6) (lambda (x) (modulo x 2)))
(1 2 1 3 1 5 0)

> (modsel_simple '(15 2 1 3 27 5 10) '(3 1 2) (lambda (x) (+ x 5)))
(15 7 6 8 27 5 10)

> (modsel_cola '(15 2 1 3 27 5 10) '(0 4 6) (lambda (x) (modulo x 2)))
(1 2 1 3 1 5 0)

> (modsel_cola '(15 2 1 3 27 5 10) '(3 1 2) (lambda (x) (+ x 5)))
(15 7 6 8 27 5 10)

> (estables '(15 2 1 3 27 5 10) 5 (lambda (x) (/ x 2)) (lambda (x) (* x 2)))
(2 2)

> (query '((0 1 2 3 4) (4 3 2 1 0) (15 2 1 3 27 5 10)) 1 1 '(1 #\M))
(0 1 2)

> (query '((0 1 2 3 4) (4 3 2 1 0) (15 2 1 3 27 5 10)) 0 2 '((0 4) (lambda(x) (+ x 100))))
(100 1 2 3 104)

> (query '((0 1 2 3 4) (4 3 2 1 0) (15 2 1 3 27 5 10)) 2 3 '(5 (lambda (x)(/ x 2)) (lambda (x) (* x 2))))
(2 2)
