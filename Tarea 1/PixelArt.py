import re
import numpy as np
from multiprocessing.resource_sharer import stop
from PIL import Image


#Enumera las lineas
Comprobar = 0
Recorrer = 0
Cantidad_lineas = 1


#Posicion actual recorrido matriz
Posicion_x = 0
Posicion_y = 0


#Tamano de la matriz
Tamano = 0


#Orientacion del personaje
Vector_Avan = 0


#Hacia que direccion apunta el personaje
Giro = "Posicion_0"
Ultima_linea = ""


#Variable que almacena una tupla con los colores
rgb = (0,0,0)



#Funciones
'''
La funcion identifica los colores, tanto en palabras como la tupla de numeros

    Parametros:
        cadena (str): String que contiene el color o la tupla de numeros

    Retorno:
        rgb (int): Tupla que contiene el color detectado
'''
def Identificar_color(cadena):
    if cadena == "Rojo":
        rgb = (255,0,0)
    elif cadena == "Verde":
        rgb = (0,255,0)
    elif cadena == "Azul":
        rgb = (0,0,255)
    elif cadena == "Negro":
        rgb = (0,0,0)
    elif cadena == "Blanco":
        rgb = (255,255,255)
    elif "RGB" in cadena:
        cadena = cadena.replace('RGB',  '')
        rgb = eval(cadena)
    #Verifica si el valor RGB existe    
    if (rgb[0]+rgb[1]+rgb[2])<=765:
        return rgb
    else:
        Errores(Recorrer,cadena)





'''
La funcion colorea la celda señalada de la matriz
    Parametros:
        color (str): String que contiene el color o la tupla de numeros
        matrix (lista de lista de tuplas de enteros): Matriz que contiene la forma de la imagen
        x (int): Entero que contiene la posicion en eje x donde se va a pintar 
        y (int): Entero que contiene la posicion en eje y donde se va a pintar 

    Retorno:
        Nada
'''
def Colorear_celda(color, matrix, x, y):
    escala = Identificar_color(color)
    if Posicion_x<Tamano and Posicion_y<Tamano:
        matrix[x][y] = escala
        color = ""
    else:
        print("Error, se ha salido de la matriz",Posicion_x,Posicion_y)
        MatrizAImagen(matrix)
        exit()
    return
        




'''
La funcion crea una matriz
    Parametros:
        medida (int): Entero que contiene el tamaño de la matriz
        color (int): Tupla que contiene el codigo del color 

    Retorno:
        matrix (lista de lista de tuplas de enteros): Matriz coloreada con el color de fondo
'''
def Crear_matriz(medida, color):
    Matrix = []
    for i in range(medida):
        Matrix.append([])
        for j in range(medida):
            Matrix[i].append(color)
    return Matrix     
            




'''
La funcion transforma la matriz a una imagen
    Parametros:
        matriz (lista de lista de tuplas de enteros): Matriz que representa la imagen en rgb.
        filename (str): Nombre del archivo en que se guardara la imagen.
        factor (int): Factor por el cual se escala el tamaño de las imagenes. 

    Retorno:
        Nada
'''
def MatrizAImagen(matriz, filename='pixelart.png', factor=10):
    matriz = np.array(matriz, dtype=np.uint8)
    np.swapaxes(matriz, 0, -1)

    N = np.shape(matriz)[0]

    img = Image.fromarray(matriz, 'RGB')
    img = img.resize((N*10, N*10), Image.Resampling.BOX)
    img.save(filename)   





#Se crea el archivo que almacena los errores y el formato de cada linea
file = open("errores.txt", "w")
formato_linea = "{} {} \n"

'''
La funcion detecta los errores de sintaxis presentes en el archivo
    Parametros:
        lin_error (in): Entero con el numero de linea en la que fallo
        linea_error (str): String con la descripcion de la linea que fallo

    Retorno:
        Nada
'''
def Errores(lin_error,linea_error):
    global Comprobar
    linea_error = linea_error.strip()
    if linea_error != "":
        print("Se ha encontrado un error en la sintaxis")
        lin_escribir = formato_linea.format(str(lin_error),linea_error)
        file.write(lin_escribir)
    else:
        Comprobar = 1
    return




'''
La funcion avanza de celda dependiendo de la posicion actual
    Parametros:
        Dir (str): String que almacena la direccion a avanzar
        c_avanzar (int): Entero con la cantidad a avanzar

    Retorno:
        Nada
'''
def Avanzar(Dir,c_avanzar):
    global Ultima_linea
    global Posicion_x
    global Posicion_y
    global Giro
    if Posicion_y<Tamano and Posicion_x<Tamano:
        if Giro == "Posicion_0":
            if Dir == "Derecha":
                Posicion_x+=c_avanzar
            elif Dir == "Izquierda":
                Posicion_x-=c_avanzar
            else:
                Posicion_y+=c_avanzar

        elif Giro == "Posicion_1":
            if Dir == "Derecha":
                Posicion_y-=c_avanzar
            elif Dir == "Izquierda":
                Posicion_y+=c_avanzar
            else:
                Posicion_x+=c_avanzar
            
        elif Giro == "Posicion_2":
            if Dir == "Derecha":
                Posicion_x-=c_avanzar
            elif Dir == "Izquierda":
                Posicion_x+=c_avanzar
            else:
                Posicion_y-=c_avanzar
            
        elif Giro == "Posicion_3":
            if Dir == "Derecha":
                Posicion_y+=c_avanzar
            elif Dir == "Izquierda":
                Posicion_y-=c_avanzar
            else:
                Posicion_x-=c_avanzar
    else:
        print("Error, se ha salido de la matriz",Posicion_x,Posicion_y)
    return      




'''
La funcion gira 90 grados a la derecha
    Parametros:
        Ninguno

    Retorno:
        Nada
'''
#Nota: El rango de la sucesion esta acotado por 100, por lo que si se necesita realizar mas giros, favor modificar la cantidad
def Derecha():
    global Giro
    global Vector_Avan
    if Vector_Avan in range(0, 100, 4):
        Giro = "Posicion_0"
    elif Vector_Avan in range(1, 100, 4):
        Giro = "Posicion_1"
    elif Vector_Avan in range(2, 100, 4):
        Giro = "Posicion_2"
    elif Vector_Avan in range(3, 100, 4):
        Giro = "Posicion_3"
    return
    

    


'''
La funcion gira 90 grados a la izquierda
    Parametros:
        Ninguno

    Retorno:
        Nada
'''
#Nota: El rango de la sucesion esta acotado por 100, por lo que si se necesita realizar mas giros, favor modificar la cantidad
def Izquierda():
    global Giro
    global Vector_Avan
    if Vector_Avan in range(0, 100, 4):
        Giro = "Posicion_0"
    elif Vector_Avan in range(1, 100, 4):
        Giro = "Posicion_3"
    elif Vector_Avan in range(2, 100, 4):
        Giro = "Posicion_2"
    elif Vector_Avan in range(3, 100, 4):
        Giro = "Posicion_1"
    return





'''
La funcion repite las intrucciones
    Parametros:
        matri (lista de lista de tuplas de enteros): Matriz a modificar
        cant_iteraciones (int): Entero con la cantidad repeticiones a realizar
        linea (str): String con lo que hay que repetir

    Retorno:
        Nada
'''
def Repetir(matri, cant_iteraciones, linea):
    con = 1
    lista = list(linea[0][1].split('\n'))
    while con<=cant_iteraciones:
        largo_li = 0
        while largo_li<len(lista):
            Instrucciones(matri, lista[largo_li].split('\t'))
            largo_li+=1
        con+=1
    return
    




'''
La funcion traduce todas la intrucciones que estan en la lista
    Parametros:
        matriz_2 (lista de lista de tuplas de enteros): Matriz a modificar
        lista (str): Lista con todas la intrucciones extraidas del archivo

    Retorno:
        Matris_1 (lista de lista de tuplas de enteros): Retorna la matriz finalizado todas las instrucciones
'''
def Instrucciones(matriz_2, lista):
    global Recorrer
    global Vector_Avan
    global Giro
    global Posicion_x
    global Cantidad_lineas
    to = 0
    sub_lista = ""
    for linea in lista:

        if re.match("Avanzar ([^ ]*)", linea.strip(' ')):
            v4 = re.findall("Avanzar ([^ ]*)", linea)
            dis = v4[0].strip('\n')
            if dis == "Derecha":
                Avanzar(dis,1)
                lista_v = []
                sig = linea.replace(("Avanzar "+dis+" "),"")
                lista_v.append(sig)
                Instrucciones(matriz_2, lista_v)
            elif dis == "Izquierda":
                Izquierda()
                lista_v = []
                sig = linea.replace(("Avanzar "+dis+" "),"")
                lista_v.append(sig)
                if sig == Ultima_linea:
                    return Matris_1
                else:
                    Instrucciones(matriz_2, lista_v)
            else:
                if dis == "":
                    Avanzar("Adelante",1)
                    lista_v = []
                    sig = linea.replace(("Avanzar "+dis+" "),"")
                    lista_v.append(sig)
                    Instrucciones(matriz_2, lista_v)
                else:
                    Avanzar("Adelante",int(dis))
                    lista_v = []
                    sig = linea.replace(("Avanzar "+dis+" "),"")
                    lista_v.append(sig)
                    if sig == Ultima_linea:
                        return Matris_1
                    else:
                        Instrucciones(matriz_2, lista_v)



        elif re.match("Avanzar([. ]*)", linea.strip(' ')):
            v5 = re.findall("Avanzar([. ]*)", linea)
            v5.append('')
            Avanzar("Adelante",1)



        elif re.match("Pintar ([^ ]*)", linea.strip(' ')):
            v6 = re.findall("Pintar ([^ ]*)", linea)
            col = v6[0].strip('\n')
            Colorear_celda(col, matriz_2, Posicion_x, Posicion_y)
            lista_v = []
            sig = linea.replace(("Pintar "+col+" "),"")
            lista_v.append(sig)
            if sig == Ultima_linea:
                return Matris_1
            else:
                Instrucciones(matriz_2, lista_v)
                


        elif re.match("Repetir ([^ ]*) veces {([(\s\S)]*)\s}\S*", linea.strip(' ')):
            v3 = re.findall("Repetir ([^ ]*) veces {([(\s\S)]*)\s}\S*", linea)
            repeticiones = int(v3[0][0])
            Repetir(matriz_2, repeticiones, v3)
            Vector_Avan += 3
            
            

        #Cuando la instruccion repetir posee mas de 1 linea, entra en la sentencia
        elif re.match("^Repetir ([^ ]*) veces [{\s(.*)\s}]$", linea.strip(' ')):
            sub_lista+=linea+"\n"
            to = 1
        elif re.match("^[	]", linea.strip(' ')) and to == 1:
            sub_lista+=linea+"\n"
        elif re.match("^[}]", linea.strip(' ')) or to == 1:
            sub_lista+=linea



        elif re.match("Derecha ([^ ]*)", linea):
            Vector_Avan+=1
            Derecha()
            n2 = []
            sig = linea[8:]
            n2.append(sig)
            if sig == Ultima_linea:
                return Matris_1
            else:
                Instrucciones(matriz_2, n2)



        elif re.match("Izquierda ([^ ]*)", linea):
            Vector_Avan+=1
            Izquierda()
            n2 = []
            sig = linea[10:]
            n2.append(sig)
            if sig == Ultima_linea:
                return Matris_1
            else:
                Instrucciones(matriz_2, lista_v)
            
        
        else:
            Errores(Recorrer,linea)
            Recorrer+=1
            

    to = 0
    if re.match("Repetir ([^ ]*) veces {([(\s\S)]*)\s}\S*", sub_lista):
        v9 = re.findall("Repetir ([^ ]*) veces {([(\s\S)]*)\s}\S*", sub_lista)
        Repetir(matriz_2, int(v9[0][0]), v9)

    return

Recorrer += 1       





#Parte pricipal, se lee el archivo linea por linea, y las instrucciones se almacenan en una lista
sentencias = []            
fichero = open('Code3.txt')  #Nota: Los archivos probados son Code3.txt, Code4.txt y Code5.txt
li = 0
for elemento in fichero:

    if re.match("Ancho ([^ ]*)", elemento):
        v1 = re.findall("Ancho ([^ ]*)", elemento)
        Ancho = int(v1[0].strip('\n'))
        Tamano = Ancho
        li+=1
        Cantidad_lineas+=1
        Recorrer+=1

    elif re.match("Color de fondo ([^ ]*)", elemento):
        v2 = re.findall("Color de fondo ([^ ]*)", elemento)
        C_fondo = v2[0].strip('\n')
        Codigo = Identificar_color(C_fondo)
        Matris_1 = Crear_matriz(Ancho, Codigo)
        li+=1
        Cantidad_lineas+=1
        Recorrer+=1

    elif li == 2:
        li+=1
        Cantidad_lineas+=1
        Recorrer+=1

    elif li>=3:
        sentencias.append(elemento.strip('\n'))
        Cantidad_lineas+=1

    else:
        Errores(Recorrer,elemento)
        Recorrer+=1

        
    
    

#Se almacena el valor de la ultima linea para saber donde esta
Ultima_linea+=elemento.strip('\n')

#Ingresan las sentencias
Instrucciones(Matris_1, sentencias)

#Si Comprobar es igual a 1, significa que no encontro errores, por lo que se escribe en el archivo
if Comprobar == 1:
    file.write("No hay errores!")
file.close()

#Resultado Final
print("Matriz creada: ")
MatrizAImagen(Matris_1)
print(Matris_1)