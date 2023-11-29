import java.util.ArrayList;



public class Mapa extends Personaje{
    //Atributos
    private int profundidad;
    private MainGraph Map = new MainGraph();
    private NodoInicial nodo_inicial;
    private abstractNodo nodo_actual;

    public Mapa(String nPersonaje, int profu){
        super(nPersonaje);
        this.profundidad = profu;
    }

    //Metodos
    /**
     * Se encarga de confiurar el mapa
     *
     * No recibe parametros
     *
     * Al ser una funcion void no retorna nada
     **/
    public void setMap(){
        Map.addSize(profundidad);
        Map.getGraph();
    }


    /**
     * Se encarga de mostrar el mapa
     *
     * No recibe parametros
     *
     * Al ser una funcion void no retorna nada
     **/
    public void viewMap(){
        Map.getGraph();
    }


    /**
     * Se encarga de mostrar los siguientes nodos de un nodo dado
     *
     * int identificador: Entero que representa el nodo actual del jugador
     *
     * ArrayList next: ArrayList que contiene los proximos nodos a partir de la posicion actual del jugado
     **/
    public ArrayList<Integer> getNext(int identificador){
        ArrayList<Integer> next = Map.findNodes(identificador);
        return next;
    }


    /**
     * Se encarga de mostrar la profundidad del mapa
     *
     * No recibe parametros
     *
     * int this.profundidad: Entero que representa la profuncidad del mapa
     **/
    public int getProfundidad(){
        return this.profundidad;
    }


    /**
     * Se encarga de confiurar el mapa
     *
     * No recibe parametros
     *
     * int Map.n_nodes(): Entero que representa la cantidad de nodos que posee el mapa
     **/
    public int getNumnodos(){
        return Map.n_nodes();
    }


    /**
     * Se encarga de mostrar el nodo inicial
     *
     * No recibe parametros
     *
     * NodoInicial this.nodo_inicial: NodoInicial que representa el primer nodo
     **/
    public NodoInicial getNodoinicial(){
        return this.nodo_inicial;
    }


    /**
     * Se encarga de mostrar el nodo actual
     *
     * No recibe parametros
     *
     * Nodo this.nodo_actual: Nodo que represnta la ubicacion del jugador
     **/
    public abstractNodo getNodoactual(){
        return this.nodo_actual;
    }
    
}
