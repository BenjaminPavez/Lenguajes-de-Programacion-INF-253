#include "certamen.h"
#include <string.h>



/*
La funcion revisa el certamen y las envia a revision

Parametros :
   tCertamen Certamen : struct tipo certamen 
   int n_preguntas : numero de preguntas

Retorno :
   Nada, ya que es tipo void
 
*/
void Estructura(tCertamen* Certamen, int npreguntas){
    
    for(int i = 0; i<npreguntas; i++){
        if(strncmp(Certamen->preguntas[i].tipo,"AlternativaSimple",strlen("AlternativaSimple")) == 0){
            revisarAlternativaSimple(Certamen->preguntas[i]);

        }else if(strncmp(Certamen->preguntas[i].tipo,"VerdaderoFalso",strlen("VerdaderoFalso")) == 0){
            revisarVerdaderoFalso(Certamen->preguntas[i]);

        }else if(strncmp(Certamen->preguntas[i].tipo,"AlternativaMultiple",strlen("AlternativaMultiple")) == 0){
            revisarAlternativaMultiple(Certamen->preguntas[i]);

        }else if(strncmp(Certamen->preguntas[i].tipo,"Completar",strlen("Completar")) == 0){
            revisarCompletar(Certamen->preguntas[i]);

        }
    }

}



/*
La funcion muestra opciones despues de responder el certamen

Parametros :
   tCertamen Certamen : struct tipo certamen

Retorno :
   Nada, ya que es tipo void
 
*/
void Menu(tCertamen* Certamen){
    printf("******************************El certamen ha finalizado******************************\n");
    printf("\n");
    printf("Que desea hacer: \n");
    printf("\n");
    printf("1. Ver resultados \n");
    printf("\n");
    printf("2. Salir \n");
    printf("\n");
    int preg;
    printf("Ingrese una opcion: ");scanf("%d", &preg);
    if(preg == 1){
        printf("************************************Resultados************************************\n");
        int correctas = nCorrectasCertamen(Certamen);
        printf("\n");
        printf("Resultados :\n");
        printf("\n");
        printf("Puntaje: %d / %d\n",correctas,Certamen->n_preguntas);
        printf("\n");
    }else{
        exit(0);
    }

}
