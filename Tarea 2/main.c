#include "certamen.c"



/*
La funcion se encarga de comunicarse con  la funcion revisarAlternativaSimple

Parametros :
   void *val1 : Puntero tipo void que almacena los paramentro de revisarAlternativaMultiple 
   void *val2 : Puntero tipo void que almacena otros paramentros de revisarAlternativaMultiple 

Retorno :
   Retorna un booleano
 
*/
bool Alsimple(void *val1, void *val2){
    tPregunta *aux = (tPregunta*)val1;
    revisarAlternativaSimple(*aux);
    return true;
}



/*
La funcion se encarga de comunicarse con  la funcion revisarAlternativaMultiple

Parametros :
   void *val1 : Puntero tipo void que almacena los paramentro de revisarAlternativaMultiple 
   void *val2 : Puntero tipo void que almacena otros paramentros de revisarAlternativaMultiple 

Retorno :
   Retorna un booleano
 
*/
bool AlMulti(void *val1, void *val2){
    tPregunta *aux2 = (tPregunta*)val1;
    revisarAlternativaMultiple(*aux2);
    return true;
}



/*
La funcion se encarga de comunicarse con  la funcion revisarVerdaderoFalso

Parametros :
   void *val1 : Puntero tipo void que almacena los paramentro de revisarVerdaderoFalso 
   void *val2 : Puntero tipo void que almacena otros paramentros de revisarVerdaderoFalso 

Retorno :
   Retorna un booleano
 
*/
bool VerFal(void *val1, void *val2){
    tPregunta *aux3 = (tPregunta*)val1;
    revisarVerdaderoFalso(*aux3);
    return true;
}



/*
La funcion se encarga de comunicarse con  la funcion revisarCompletar

Parametros :
   void *val1 : Puntero tipo void que almacena los paramentro de revisarCompletar 
   void *val2 : Puntero tipo void que almacena otros paramentros de revisarCompletar 

Retorno :
   Retorna un booleano
 
*/
bool Comple(void *val1, void *val2){
    tPregunta *aux4 = (tPregunta*)val1;
    revisarCompletar(*aux4);
    return true;
}



/*
La funcion abre, lee y guarda las preguntas

Parametros :
   tCertamen* certamen : Puntero a struct tCertamen

Retorno :
   Nada, ya que es tipo void
 
*/
void AbrirCertamen(tCertamen* certamen){
    //Se hace un array de los tipos de preguntas
    tEnunciadoAlternativa *p1 = (tEnunciadoAlternativa*)malloc(sizeof(tEnunciadoAlternativa));
    tEnunciadoVerdaderoFalso *p2 = (tEnunciadoVerdaderoFalso*)malloc(sizeof(tEnunciadoVerdaderoFalso));
    tEnunciadoAlternativaMultiple *p3 = (tEnunciadoAlternativaMultiple*)malloc(sizeof(tEnunciadoAlternativaMultiple));
    tEnunciadoCompletar *p4 = (tEnunciadoCompletar*)malloc(sizeof(tEnunciadoCompletar));

    int con_simple = 0, con_multi = 0, con_verdadofalso = 0, con_completar = 0, pos_actual = 0;
    FILE *in_file;
    in_file = fopen("certamen.txt", "r");
    char Linea[Longitud_Linea];
    if(in_file == NULL){
        fprintf(stderr,"Error al abrir el archivo input.txt\n");
    }else{
        while(fgets(Linea, Longitud_Linea, in_file)){
            strtok(Linea, "\n");
            if(strncmp(Linea,"AlternativaSimple",strlen("AlternativaSimple")) == 0){
                tPregunta*Pt1 = (tPregunta*)malloc(sizeof(tPregunta));
                fgets(Linea, Longitud_Linea, in_file);
                strcpy(p1->enunciado, Linea);
                fgets(Linea, Longitud_Linea, in_file);
                p1->n_alternativas = atoi(Linea);
                fgets(Linea, Longitud_Linea, in_file);

                //Version 2
                p1->alternativas = malloc(sizeof(char)*p1->n_alternativas);
                char* dato;
                for(int i = 0; i < p1->n_alternativas; i++){
                    dato = (char*) malloc(Longitud_Linea);
                    strcpy(dato,Linea);
                    p1->alternativas[i] = dato;
                    fgets(Linea, Longitud_Linea, in_file);
                }
                p1->alternativa_correcta = atoi(Linea);
                Pt1 = crearPregunta(certamen,"AlternativaSimple",p1,Alsimple);
                asignarPregunta(certamen,pos_actual,Pt1);
                pos_actual++;
                con_simple++;
                

                
            }else if(strncmp(Linea,"VerdaderoFalso",strlen("VerdaderoFalso")) == 0){
                tPregunta*Pt2 = (tPregunta*)malloc(sizeof(tPregunta));
                fgets(Linea, Longitud_Linea, in_file);
                strcpy(p2->enunciado, Linea);
                fgets(Linea, Longitud_Linea, in_file);
                if(strncmp(Linea,"V",1) == 0){
                    p2->respuesta = true;
                }else{
                    p2->respuesta = false;
                }

                Pt2 = crearPregunta(certamen,"VerdaderoFalso",p2,VerFal);
                asignarPregunta(certamen,pos_actual,Pt2);
                pos_actual++;
                con_verdadofalso++;
                

                
            }else if(strncmp(Linea,"AlternativaMultiple",strlen("AlternativaMultiple")) == 0){
                tPregunta*Pt3 = (tPregunta*)malloc(sizeof(tPregunta));
                fgets(Linea, Longitud_Linea, in_file);
                strcpy(p3->enunciado, Linea);
                fgets(Linea, Longitud_Linea, in_file);
                p3->n_alternativas = atoi(Linea);
                fgets(Linea, Longitud_Linea, in_file);

                p3->alternativas = malloc(sizeof(char)*p3->n_alternativas);
                char* dato;
                for(int i = 0;i<p3->n_alternativas;i++){
                    dato = (char*) malloc(Longitud_Linea);
                    strcpy(dato,Linea);
                    p3->alternativas[i] = dato;
                    fgets(Linea, Longitud_Linea, in_file);
                
                }

                //Version 2
                int n_altcorrectas = atoi(Linea);
                fgets(Linea, Longitud_Linea, in_file);


                p3->alternativa_correcta = malloc(sizeof(char)*n_altcorrectas);
                char* obj;
                for(int q = 0; q<n_altcorrectas; q++){
                    obj = (char*) malloc(Longitud_Linea);
                    strcpy(obj,Linea);
                    p3->alternativa_correcta[q] = atoi(obj);
                    fgets(Linea, Longitud_Linea, in_file);
                }


                Pt3 = crearPregunta(certamen,"AlternativaMultiple",p3,AlMulti);
                asignarPregunta(certamen,pos_actual,Pt3);

                pos_actual++;
                con_multi++;
                
                
      
            }else if(strncmp(Linea,"Completar",strlen("Completar")) == 0){
                tPregunta*Pt4 = (tPregunta*)malloc(sizeof(tPregunta));
                fgets(Linea, Longitud_Linea, in_file);
                p4->n_textos = atoi(Linea);
                fgets(Linea, Longitud_Linea, in_file);

                //Textos
                p4->textos = malloc(sizeof(char)*p4->n_textos);
                char* textos_c;
                for(int i = 0; i < p4->n_textos; i++){
                    textos_c = (char*) malloc(Longitud_Linea);
                    strcpy(textos_c,Linea);
                    p4->textos[i] = textos_c;
                    fgets(Linea, Longitud_Linea, in_file);
                }
                //Respuestas
                p4->respuestas = malloc(sizeof(char)*(p4->n_textos-1));
                char* respuestas_c;
                for(int f = 0; f < p4->n_textos-1; f++){
                    respuestas_c = (char*) malloc(Longitud_Linea);
                    strcpy(respuestas_c,Linea);
                    p4->respuestas[f] = respuestas_c;
                    fgets(Linea, Longitud_Linea, in_file);
                }

                

                Pt4 = crearPregunta(certamen,"Completar",p4,Comple);
                asignarPregunta(certamen,pos_actual,Pt4);

                pos_actual++;  
                con_completar++;
                
                
            }
        } 
    }
}



int main(){
    //Variable que almacena el numero de preguntas el certamen
    int npreguntas = 0;

    //Abro el archivo para ver el numero de preguntas
    FILE *archivo = 0;
    archivo = fopen("certamen.txt", "r");
    char Linea[Longitud_Linea];
    npreguntas = atoi(fgets(Linea, Longitud_Linea, archivo));
    fclose(archivo);

    //Abro nuevamente el archivo para guardar las preguntas
    tCertamen*Certamen_1 = crearCertamen(npreguntas);
    AbrirCertamen(Certamen_1);
    Estructura(Certamen_1, npreguntas);
    nCorrectasCertamen(Certamen_1);
    Menu(Certamen_1);

    return 0;
}




