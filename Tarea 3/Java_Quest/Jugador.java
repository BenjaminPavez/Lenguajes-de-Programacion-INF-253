import java.util.ArrayList;
import java.util.List;


public class Jugador extends Personaje {
    //Atributos
    private List<Item> items_aplicados = new ArrayList<>();
    private ArrayList<String> NameItems = new ArrayList<String>();

    //Constructor
    public Jugador(String cosa){
        super(cosa);
    }

    //Metodos
    /**
     * Se encarga de mostrar los datos del jugador
     *
     * No recibe parametros
     *
     * Al ser una funcion void no retorna nada
     **/
    public void verEstados(){
        System.out.println(("Estado actual: "));
        System.out.println("Tu Personaje es: "+getNombre());
        System.out.println("Tu Saldo actual es: "+getDinero());
        System.out.println("Tu Hp Actual es: "+getHpactual());
        System.out.println("Tu Hp Total es: "+getHptotal());
        System.out.println("Tu Daño actual es: "+getDanio());
        System.out.println("Tu Defensa actual es: "+getDefensa());

    }


    /**
     * Se encarga de mostrar los items del jugador
     *
     * No recibe parametros
     *
     * Al ser una funcion void no retorna nada
     **/
    public void verItems(){
        System.out.println("Items Aplicados: ");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("%2s. %20s %7s %16s %11s %13s %16s", "N°", "Articulo", "Precio", "Recuperacion Hp", "Aumento Hp", "Aumento Daño", "Aumento Defensa");
        System.out.println();

        System.out.println("--------------------------------------------------------------------------------------------");
        for(int y = 0; y<items_aplicados.size(); y++){
            System.out.format("%2d. %20s %5d %11d %13d %12d %13d",
                    y+1, NameItems.get(y), items_aplicados.get(y).getPrecio(), items_aplicados.get(y).getRecuperarhp(), items_aplicados.get(y).getAumentarhptotal(), items_aplicados.get(y).getAumentardanio(), items_aplicados.get(y).getAumentardefensa());
            System.out.println();
        }
    }


    /**
     * Se encarga de añadir items al jugador
     *
     * Item elemento: Item que representa un "arma" que compro o gano el jugador
     *
     * Al ser una funcion void no retorna nada
     **/
    public void addItems(Item elemento){
        items_aplicados.add(elemento);
    }


    /**
     * Se encarga de añadir los nombres de los items
     *
     * String name: String que representa el nombre del articulo a agregar
     *
     * Al ser una funcion void no retorna nada
     **/
    public void addNameitem(String name){
        NameItems.add(name);
    }

}
