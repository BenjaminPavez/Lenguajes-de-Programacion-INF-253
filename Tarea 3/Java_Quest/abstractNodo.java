import java.util.List;

public abstract class abstractNodo{
    //Atributos
    private int id; //Se utiliza
    private static List<abstractNodo> siguientes_nodos;

    //Metodos
    /**
     * Se encarga de mostrar lo que puede hacer el jugador
     *
     * Jugador w: Jugador que representa al jugador que esta en la partida
     *
     * Al ser una funcion void no retorna nada
     **/
    public abstract void Interactuar(Jugador w);


    /**
     * Se encarga de agregar nodo
     *
     * abstractNodo obj2: Nodo abstracto
     *
     * Al ser una funcion void no retorna nada
     **/
    public void agregarNodo(abstractNodo obj2){
        siguientes_nodos.add(obj2);
    }


    /**
     * Se encarga de setear el id
     *
     * int num: Entero que representa la id
     *
     * Al ser una funcion void no retorna nada
     **/
    public void setId(int num){
        this.id = num;
    }
}
