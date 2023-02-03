#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#define Longitud_Linea 1000



//Estructura pregunta de Alternativa Simple
typedef struct{
   char enunciado[128];
   int n_alternativas;
   char** alternativas;
   int alternativa_correcta;
}tEnunciadoAlternativa;



//Estructura pregunta de Alternativa Multiple
typedef struct{
   char enunciado[128];
   int n_alternativas;
   char** alternativas;
   int*alternativa_correcta;
}tEnunciadoAlternativaMultiple;



//Estructura pregunta de Verdadero Falso
typedef struct{
   char enunciado [128];
   bool respuesta;
}tEnunciadoVerdaderoFalso;



//Estructura pregunta de Completar
typedef struct{
   int n_textos;
   char** textos;
   char** respuestas;
}tEnunciadoCompletar;



//Estructura de Pregunta
typedef struct{
   char tipo [64];
   void* enunciado;
   void* respuesta;
   bool (*revisar)(void*,void*);
}tPregunta;



//Estructura de Certamen
typedef struct{
   int n_preguntas;
   tPregunta* preguntas;
}tCertamen;



//Variables Globales
int Posicion = 0;
int Respuestas_Correctas = 0;
int Respuestas_Incorrectas = 0;



/*
La funcion crea un certamen vacio

Parametros :
   int n_preguntas : numero de preguntas

Retorno :
   Retorna un puntero a struct tipo certamen
 
*/
tCertamen* crearCertamen(int n_preguntas){
   tCertamen*certamen = (tCertamen*)malloc(sizeof(tCertamen));
   certamen->n_preguntas = n_preguntas;
   certamen->preguntas = (tPregunta*)malloc(n_preguntas*sizeof(tPregunta));
   return certamen;

}



/*
La funcion crea una pregunta

Parametros :
   tCertamen* certamen : Puntero a struct tCertamen
   char* tipo : Puntero a char que almacena el tipo de pregunta
   void* enunciado : Puntero void que almacen la pregunta
   bool(*revisar)(void*,void*) : Funcion para revisar

Retorno :
   Retorna un puntero a struct tipo pregunta
 
*/
tPregunta* crearPregunta(tCertamen* certamen, char* tipo, void* enunciado, bool(*revisar)(void*,void*)){
   tPregunta *pregunta = (tPregunta*)malloc(sizeof(tPregunta));
   if(strncmp(tipo,"AlternativaSimple",strlen("AlternativaSimple")) == 0){
      tEnunciadoAlternativa *obj1 = (tEnunciadoAlternativa*)enunciado;
      tPregunta *pregunta1 = (tPregunta*)malloc(sizeof(tPregunta));
      strcpy(pregunta1->tipo,tipo);
      pregunta1->enunciado = obj1;
      pregunta1->revisar = revisar;
      pregunta = pregunta1;

   }else if(strncmp(tipo,"VerdaderoFalso",strlen("VerdaderoFalso")) == 0){
      tEnunciadoVerdaderoFalso *obj2 = (tEnunciadoVerdaderoFalso*)enunciado;
      tPregunta *pregunta2 = (tPregunta*)malloc(sizeof(tPregunta));
      strcpy(pregunta2->tipo,tipo);
      pregunta2->enunciado = obj2;
      pregunta2->revisar = revisar;
      pregunta = pregunta2;

   }else if(strncmp(tipo,"AlternativaMultiple",strlen("AlternativaMultiple")) == 0){
      tEnunciadoAlternativaMultiple *obj3 = (tEnunciadoAlternativaMultiple*)enunciado;
      tPregunta *pregunta3 = (tPregunta*)malloc(sizeof(tPregunta));
      strcpy(pregunta3->tipo,tipo);
      pregunta3->enunciado = obj3;
      pregunta3->revisar = revisar;
      pregunta = pregunta3;

   }else if(strncmp(tipo,"Completar",strlen("Completar")) == 0){
      tEnunciadoCompletar *obj4 = (tEnunciadoCompletar*)enunciado;
      tPregunta *pregunta4 = (tPregunta*)malloc(sizeof(tPregunta));
      strcpy(pregunta4->tipo,tipo);
      pregunta4->enunciado = obj4;
      pregunta4->revisar = revisar;
      pregunta = pregunta4;
   }
   return pregunta; 
}



/*
La funcion asigna la pregunta a la posicion indicada

Parametros :
   tCertamen* certamen : struct tipo certamen
   int n_pregunta : Numero de preguntas
   tPregunta* pregunta : struct tipo pregunta

Retorno :
   Nada, ya que es tipo void
 
*/
void asignarPregunta(tCertamen* certamen, int n_pregunta, tPregunta* pregunta){
   certamen->preguntas[n_pregunta] = (*pregunta);
}



/*
La funcion retorna la pregunta que esta en la posicion dada

Parametros :
   tCertamen certamen : struct tipo certamen
   int n_pregunta : Entero que contiene la posicion de la pregunta

Retorno :
   tPregunta certamen->preguntas[n_pregunta] : Pregunta del certamen que esta en la posicion dada
 
*/
tPregunta leerPregunta(tCertamen* certamen , int n_pregunta){
   return certamen->preguntas[n_pregunta];
}



/*
La funcion retorna el numero de alternativas correctas

Parametros :
   tCertamen certamen : struct tipo certamen

Retorno :
   Retorna un entero con el numero de alternativas correctas
 
*/
int nCorrectasCertamen(tCertamen* certamen){
   return Respuestas_Correctas;
}



/*
La funcion retorna el numero de preguntas

Parametros :
   tCertamen certamen : struct tipo certamen

Retorno :
   Retorna un entero con la cantidad de preguntas
 
*/
int largoCertamen(tCertamen certamen){
   return certamen.n_preguntas;
}



/*
La funcion revisa la pregunta Alternativa Simple

Parametros :
   tPregunta pregunta: struct tipo pregunta

Retorno :
   Retorna un booleano
 
*/
bool revisarAlternativaSimple(tPregunta pregunta){
   tEnunciadoAlternativa *adj1 = (tEnunciadoAlternativa*)pregunta.enunciado;
   printf("-------------------------------------------------------------------------------------\n");
   printf("                                  Alternativa Simple                                 \n");
   printf("-------------------------------------------------------------------------------------\n");
   printf("\n");
   printf("%s", adj1->enunciado);
   printf("\n");
   for(int k = 0; k<adj1->n_alternativas; k++){
      printf("%d) %s\n",k+1,adj1->alternativas[k]);
   }
   int answer;
   printf("Seleccione una alternativa: ");scanf("%d", &answer);
   printf("\n");


   if(answer == (adj1->alternativa_correcta)){
      printf("Respuesta Correcta\n");
      printf("\n");
      Respuestas_Correctas++;
      return true;
   }else{
      printf("Respuesta Incorrecta\n");
      printf("\n");
      Respuestas_Incorrectas++;
      return false;
   }
   printf("-------------------------------------------------------------------------------------\n");
   printf("\n");
   printf("\n");

}



/*
La funcion busca un entero en un array

Parametros :
   int arreglo[]: Arreglo de enteros
   int busqueda: Numero a buscar en el arreglo
   int longitud: Largo del arreglo

Retorno :
   Retorna 1 si esta y -1 si no esta
 
*/
int buscarEnArregloEntero(int arreglo[], int busqueda, int longitud) {
   for (int x = 0; x < longitud; x++) {
      if(arreglo[x] == busqueda){
         return 1;
      }
   }
   return -1;
}



/*
La funcion revisa la pregunta Alternativa Multiple

Parametros :
   tPregunta pregunta: struct tipo pregunta

Retorno :
   Retorna un booleano
 
*/
bool revisarAlternativaMultiple(tPregunta pregunta){
   tEnunciadoAlternativaMultiple*adj2 = (tEnunciadoAlternativaMultiple*)pregunta.enunciado;
   printf("-------------------------------------------------------------------------------------\n");
   printf("                                 Alternativa Multiple                                \n");
   printf("-------------------------------------------------------------------------------------\n");
   printf("\n");
   printf("%s", adj2->enunciado);
   printf("\n");
   for(int g = 1; g <=adj2->n_alternativas; g++){
      printf("%d) %s\n",g,adj2->alternativas[g-1]);
   }
   
   int seleccion[adj2->n_alternativas];
   bool repetir = true;

   int size_a = sizeof(&adj2->alternativa_correcta)/sizeof(adj2->alternativa_correcta[0]);
   char respuesta[2];
   int iteraciones = 0;
   int cantidad_respuesta = 10;
   int pass = 0;

   while(repetir == true){
      int answer;
      printf("Seleccione una alternativa: ");scanf("%d", &answer);
      printf("\n");
      seleccion[iteraciones] = answer;
      cantidad_respuesta++;
      printf("Desea seleccionar otra alternativa Y/N: ");scanf("%s", respuesta);
      printf("\n");

      if(strncmp(respuesta,"Y",1) == 0){
         iteraciones++;
         //Comprobacion cantidad de palabras
         if(iteraciones >= size_a){
            printf("Error: No puedes selecionar otra alternativa\n");
            printf("\n");
            repetir = false;
            pass = 1;
         }else{
            repetir = true;
         }
      }else if(strncmp(respuesta,"N",1) == 0){
         iteraciones++;
         repetir = false;
         pass = 1;
      }
   }

    //Verificacion
   int cons = 0;
   if(pass == 1){
      for(int r = 0; r < iteraciones; r++){
         int ans = buscarEnArregloEntero(adj2->alternativa_correcta, seleccion[r], size_a);
         if(ans == 1){
            cons++;
         }else{
            cons--;
         }
      }
   }

   if(cons == size_a){
      printf("Respuesta Correcta\n");
      printf("\n");
      Respuestas_Correctas++;
      printf("-------------------------------------------------------------------------------------\n");
      printf("\n");
      return true;
      
   }else{
      printf("Respuesta Incorrecta\n");
      printf("\n");
      Respuestas_Incorrectas++;
      printf("-------------------------------------------------------------------------------------\n");
      printf("\n");
      return false;
   }
}


/*
La funcion revisa la pregunta Verdadero Falso

Parametros :
   tPregunta pregunta: struct tipo pregunta

Retorno :
   Retorna un booleano
 
*/
bool revisarVerdaderoFalso(tPregunta pregunta){
   tEnunciadoVerdaderoFalso*adj3 = (tEnunciadoVerdaderoFalso*)pregunta.enunciado;
   printf("-------------------------------------------------------------------------------------\n");
   printf("                                  Verdadero o Falso                                  \n");
   printf("-------------------------------------------------------------------------------------\n");
   printf("\n");
   printf("%s",adj3->enunciado);
   printf("\n");

   char pre[1];
   int respfin;
   //Importante: Respetar las Mayusculas
   int bucle = 0;
   while(bucle != 1){
      printf("Seleccione V o F: ");scanf("%s", pre);
      printf("\n");
      if(strcmp(pre,"V") == 0){
         respfin = 0;
         bucle = 1;
      }else if(strcmp(pre,"F") == 0){
         respfin = 1;
         bucle = 1;
      }else{
         printf("\n");
         printf("Error de seleccion, favor intente nuevamente\n");
         printf("\n");
      }

   }

   int respcorr;
   if(adj3->respuesta){
      respcorr = 0;    
   }else{
      respcorr = 1;
   }


   if(respfin == respcorr){
      printf("Respuesta Correcta\n");
      printf("\n");
      Respuestas_Correctas++;
      return true;
   }else{
      printf("Respuesta Incorrecta\n");
      printf("\n");
      Respuestas_Incorrectas++;
      return false;
   }
   printf("-------------------------------------------------------------------------------------\n");
   printf("\n");
   printf("\n");

}



/*
La funcion busca una palabra en un array

Parametros :
   char* arreglo[]: Arreglo de caracteres
   char busqueda[128]: Palabra a buscar en el arreglo
   int longitud: Largo del arreglo

Retorno :
   Retorna 1 si esta y -1 si no esta
 
*/
int buscarEnArreglo(char* arreglo[], char busqueda[128], int longitud) {
   for (int x = 0; x < longitud; x++) {
      if(strncmp(arreglo[x],busqueda,strlen(busqueda)) == 0){
         return 1;
      }
   }
   return -1;
}




/*
La funcion revisa la pregunta Completar

Parametros :
   tPregunta pregunta: struct tipo pregunta

Retorno :
   Retorna un booleano
 
*/
bool revisarCompletar(tPregunta pregunta){
   tEnunciadoCompletar*adj4 = (tEnunciadoCompletar*)pregunta.enunciado;
   printf("-------------------------------------------------------------------------------------\n");
   printf("                                      Completar                                      \n");
   printf("-------------------------------------------------------------------------------------\n");
   printf("\n");
   printf("Enunciado(s): ");
   for(int g = 0; g <adj4->n_textos; g++){
      if(g == adj4->n_textos-1){
         printf(" '%s'",strtok(adj4->textos[g], "\n"));
      }else{
         printf(" '%s' , ",strtok(adj4->textos[g], "\n"));
      }
   }
   printf("\n");
   printf("\n");
   
   char* seleccion[adj4->n_textos-1];
   int pass = 0;
   bool repetir = true;
   char respuesta[2];
   int iteraciones = 0;
   while(repetir == true){
      char complet[128];
      printf("Escriba la(s) palabra(s) que falta(n): ");scanf("%s",complet);
      printf("\n");
      seleccion[iteraciones] = complet;
      printf("Desea seleccionar otra alternativa Y/N: ");scanf("%s", respuesta);
      printf("\n");
      if(strncmp(respuesta,"Y",1) == 0){
         iteraciones++;
         //Comprobacion cantidad de palabras
         if(iteraciones >= adj4->n_textos-1){
            printf("Error: No puedes ingresar mas palabras\n");
            printf("\n");
            repetir = false;
            pass = 1;
         }else{
            repetir = true;
         }
      }else if(strncmp(respuesta,"N",1) == 0){
         iteraciones++;
         repetir = false;
         pass = 1;
      }
   }

   
   //Verificacion
   int cons = 0;
   if(pass == 1){
      //printf("REPETICIONES: %d\n",iteraciones);
      for(int r = 0; r < iteraciones; r++){
         int ans = buscarEnArreglo(adj4->respuestas, seleccion[r], (adj4->n_textos-1));
         if(ans == 1){
            cons++;
         }else{
            cons--;
         }
      }
   }

   if(cons == (adj4->n_textos-1)){
      printf("Respuesta Correcta\n");
      printf("\n");
      Respuestas_Correctas++;
      printf("-------------------------------------------------------------------------------------\n");
      printf("\n");
      return true;
      
   }else{
      printf("Respuesta Incorrecta\n");
      printf("\n");
      Respuestas_Incorrectas++;
      printf("-------------------------------------------------------------------------------------\n");
      printf("\n");
      return false;
   }
   
}