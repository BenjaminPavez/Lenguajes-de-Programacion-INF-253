CC=gcc
CFLAGS=-c -Wall
SOURCE=main.c
OBJ=$(SOURCE:.c=.o)
EXE=main

all: 
	$(SOURCE) $(EXE)

$(EXE): $(OBJ)
    $(CC) $(OBJ) -o $@

%.o: %.c
    $(CC) $(CFLAGS) $< -o $@

run:
    @$(MAKE) && ./$(EXE) $(ARGS)

clean:
    rm -rf $(OBJ) $(EXE)
