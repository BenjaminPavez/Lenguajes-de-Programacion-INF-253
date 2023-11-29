# JFLAGS: flags con las que se ejecuta el codigo.
# -g -> Compila con informacion de debugging.
# -Xlint:all -> Muestra los warnings al compilar.
JFLAGS = -g -Xlint:all

# Indicar el compilador de java a utilizar
JC = javac

# Indicar la maquina virtual para ejecutar codigos de java
JVM = java

# Nombre de la clase que contiene la funcion main
MAIN = Main

# En JAVAS se indican los archivos .java, en este caso se estan indicando:
# $(wildcard ./*.java) -> todos los archivos .java dentro del mismo directorio de la terminal, es decir tarea3_javaquest/
JAVAS := $(wildcard ./*.java)

# CLASSES son los nombres de los archivos ya compilados, corresponde a los JAVAS pero remplazando el .java por .class
CLASSES := $(JAVAS:.java=.class)

# Indica que al ejecutar el comando make o make all se quieren compilar todos los .java
all: $(CLASSES)

# Permite que se pueda ejecutar:
# make all (compila todos los programas)
# make clean (elimina los archivos .class del directorio)
# make run (ejecuta los archivos)
.PHONY: all clean run

# Compila un archivo .java para generar su correspondiente .class, utilizando el JC y JFLAGS definidos anteriormente
%.class: %.java
	$(JC) $(JFLAGS) $^

# Ejecuta el main. Si las clases no se encuentran compiladas, entonces las compila.
run: $(CLASSES)
	$(JVM) $(MAIN)

# Elimina todos los archivos compilados
clean:
	rm -rf $(CLASSES)
