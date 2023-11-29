import java.util.ArrayList;
import java.util.Hashtable;
import java.util.SortedSet;

public class MainGraph {
    //Atributos
    private int Longitud;
    private Hashtable<Integer, ArrayList<Integer>> nextNode = new Hashtable<Integer, ArrayList<Integer>>();


    //Metodos
    /**
     * Se encarga de obtener el grafo
     *
     * No recibe parametros
     *
     * Al ser una funcion void no retorna nada
     **/
    public void getGraph(){
        System.out.println("Cargando Mapa");
        SortedSet<Edge> edges = GraphGenerator.Generar(Longitud);
        for (Edge e : edges) {
            System.out.printf("(%d) -> (%d)\n", e.x, e.y);
            ArrayList<Integer> listNodos = new ArrayList<Integer>();  //Lista
            for(Edge a : edges){
                if(e.x == a.x){
                    listNodos.add(a.y);
                }
            }
            nextNode.put(e.x,listNodos);
        }
    }


    //Metodos
    /**
     * Se encarga de mostrar los nodos cercanos al nodo señalado
     *
     * int id: Entero que representa el nodo actual
     *
     * ArrayList<Integer> nextNode.get(id): ArrayList<Integer> que representa una lista de los nodos cercanos
     **/
    public ArrayList<Integer> findNodes(int id){
        return nextNode.get(id);
    }


    //Metodos
    /**
     * Se encarga de mostrar el numero de nodos
     *
     * No recibe parametros
     *
     * int nextNode.size(): Entero que representa el numero de nodos
     **/
    public int n_nodes(){
        return nextNode.size();

    }


    /**
     * Se encarga de aumetar tamaño
     *
     * int value: Entero que añade longitud
     *
     * Al ser una funcion void no retorna nada
     **/
    public void addSize(int value){
        this.Longitud = value;
    }
}
