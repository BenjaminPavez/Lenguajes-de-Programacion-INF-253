
public class Edge implements Comparable<Edge> {
    //Atributos
    public final Integer x;
    public final Integer y;

    //Metodos
    /**
     * Se encarga de crear los edges
     *
     * Integrer x: Entero que representa un nodo
     * Integrer y: Entero que representa otro nodo
     *
     * no retorna nada
     **/
    public Edge(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Se encarga de comparar los edges
     *
     * Edge e: Edge que repesenta un nodo
     *
     * int first != 0 ? first : second: Entero
     **/
    public int compareTo(Edge e) {
        int first = this.x.compareTo(e.x);
        int second = this.y.compareTo(e.y);
        return first != 0 ? first : second;
    }

}
