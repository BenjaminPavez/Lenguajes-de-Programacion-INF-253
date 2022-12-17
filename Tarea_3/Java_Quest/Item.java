
public class Item {
    //Atributos
    private int precio;
    private int recuperar_hp;
    private int aumentar_hp_total;
    private int aumentar_danio;
    private int aumentar_defensa;

    //Constructor
    public Item(int precio, int recuperar_hp, int aumentar_hp_total, int aumentar_danio, int aumentar_defensa){
        this.precio = precio;
        this.recuperar_hp = recuperar_hp;
        this.aumentar_hp_total = aumentar_hp_total;
        this.aumentar_danio = aumentar_danio;
        this.aumentar_defensa = aumentar_defensa;
    }

    
    //Metodos
    /**
     * Se encarga de obtener el precio del item
     *
     * No recibe parametros
     *
     * int this.precio: Entero que muestra el precio
     **/
    public int getPrecio(){
        return this.precio;
    }


    /**
     * Se encarga de obtener la recuperacion de hpc del item
     *
     * No recibe parametros
     *
     * int this.recuperar_hp: Entero que muestra la recuperacion de hp
     **/
    public int getRecuperarhp(){
        return this.recuperar_hp;
    }


    /**
     * Se encarga de obtener el hp total del item
     *
     * No recibe parametros
     *
     * int this.aumentar_hp_total: Entero que muestra el hp total
     **/
    public int getAumentarhptotal(){
        return this.aumentar_hp_total;
    }


    /**
     * Se encarga de obtener el daño del item
     *
     * No recibe parametros
     *
     * int this.aumentar_danio: Entero que muestra el daño
     **/
    public int getAumentardanio(){
        return this.aumentar_danio;
    }


    /**
     * Se encarga de obtener la defensa del item
     *
     * No recibe parametros
     *
     * int this.aumentar_defensa: Entero que muestra la defensa
     **/
    public int getAumentardefensa(){
        return this.aumentar_defensa;
    }


    
    /**
     * Se encarga de aplicar el item
     *
     * Jugador obj1: Jugador actual
     *
     * Al ser una funcion void no retorna nada
     **/
    public void aplicar(Jugador obj1){
        obj1.addDinero(-getPrecio());
        obj1.addHptotal(getAumentarhptotal());
        obj1.addDanio(getAumentardanio());
        obj1.addDefensa(getAumentardefensa());
        obj1.addItems(new Item(getPrecio(),getRecuperarhp(),getAumentarhptotal(),getAumentardanio(),getAumentardefensa()));

    }
}
