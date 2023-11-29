import java.util.Random;

public class Personaje{
    //Atributos
    private String nombre;
    private int dinero;
    private int hp_actual;
    private int hp_total;
    private int danio;
    private int defensa;

    //Datos iniciales
    public Personaje(String nom_personaje){
        this.nombre = nom_personaje;
        this.dinero = 500;
        this.hp_actual = 20;
        this.hp_total = 20;
        this.danio = 5;
        this.defensa = 1;
    }

    //Metodos
    /**
     * Se encarga de mostrar el nombre del personaje
     *
     * No recibe parametros
     *
     * String this.nombre: String con el nombe del personaje
     **/
    //Nombre
    public String getNombre(){
        return this.nombre;
    }


    /**
     * Se encarga de mostrar el dinero del personaje
     *
     * No recibe parametros
     *
     * int this.dinero: Entero con el saldo actual del personaje
     **/
    //Dinero
    public int getDinero(){
        return this.dinero;
    }


    /**
     * Se encarga de añadir dinero del personaje
     *
     * int valor: Entero con el numero a agregar o disminuir
     *
     * Al ser una funcion void no retorna nada
     **/
    public void addDinero(int valor){
        this.dinero = this.dinero + valor;
    }


    /**
     * Se encarga de mostrar el Hp Actual del personaje
     *
     * No recibe parametros
     *
     * int this.hp_actual: Entero con el Hp actual del personaje
     **/
    //Hp Actual
    public int getHpactual(){
        return this.hp_actual;
    }


    /**
     * Se encarga de añadir Hp actual del personaje
     *
     * int valor: Entero con el numero a agregar o disminuir
     *
     * Al ser una funcion void no retorna nada
     **/
    public void addHpactual(int valor){
        this.hp_actual = this.hp_actual + valor;
    }


    /**
     * Se encarga de mostrar el Hp total del personaje
     *
     * No recibe parametros
     *
     * int this.hp_total: Entero con el Hp total del personaje
     **/
    //Hp Total
    public int getHptotal(){
        return this.hp_total;
    }


    /**
     * Se encarga de añadir Hp total del personaje
     *
     * int valor: Entero con el numero a agregar o disminuir
     *
     * Al ser una funcion void no retorna nada
     **/
    public void addHptotal(int valor){
        this.hp_total = this.hp_total + valor;
    }


    /**
     * Se encarga de mostrar el daño del personaje
     *
     * No recibe parametros
     *
     * int this.danio: Entero con el daño actual del personaje
     **/
    //Daño
    public int getDanio(){
        return this.danio;
    }


    /**
     * Se encarga de añadir Daño del personaje
     *
     * int valor: Entero con el numero a agregar o disminuir
     *
     * Al ser una funcion void no retorna nada
     **/
    public void addDanio(int valor){
        this.danio = this.danio + valor;
    }


    /**
     * Se encarga de mostrar la defensa del personaje
     *
     * No recibe parametros
     *
     * int this.defensa: Entero con la defensa actual del personaje
     **/
    //Defensa
    public int getDefensa(){
        return this.defensa;
    }


    /**
     * Se encarga de añadir defensa del personaje
     *
     * int valor: Entero con el numero a agregar o disminuir
     *
     * Al ser una funcion void no retorna nada
     **/
    public void addDefensa(int valor){
        this.defensa = this.defensa + valor;
    }


    /**
     * Se encarga de gestionar el combate con el enemigo/jefe
     *
     * No recibe parametros
     *
     * Al ser una funcion void no retorna nada
     **/
    //Metodo PDF
    public void combate(Personaje obj){
        Random ModificacionEnemigo = new Random();
        int ran1 = ModificacionEnemigo.nextInt(10)+1;
        obj.addDefensa(-ran1);
        obj.addDanio(-ran1);
        obj.addHpactual(-ran1);
        obj.addHptotal(-ran1);
    }

}

