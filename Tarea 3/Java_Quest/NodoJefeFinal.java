import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;



public class NodoJefeFinal extends abstractNodo{
    //Atributos
    Personaje jefe;
    private int posNode;
    private int conflicto = 0;
    private Mapa soyMapa;
    private Hashtable<Integer, String> typesNodes = new Hashtable<Integer, String>();

    //Metodos
    /**
     * Se encarga de mostrar lo que puede hacer el jugador
     *
     * Jugador Var7: Jugador que representa al jugador que esta en la partida
     *
     * Al ser una funcion void no retorna nada
     **/
    public void Interactuar(Jugador Var7){
        boolean continued = true;
        Scanner escanear = new Scanner(System.in);
        while(continued){
            System.out.println();
            System.out.println("    Bienvenido al Combate Final*    ");
            System.out.println("Usted se encuentra en el nodo: "+posNode);
            setId(posNode); //Abstract
            int verificador = 0;
            while(verificador<4){
                System.out.println();
                System.out.println("Que desea hacer: ");
                System.out.println("1. Ver Mapa: ");
                System.out.println("2. Ver Estadisticas: ");
                System.out.println("3. Ver Items: ");
                System.out.println("4. Terminar: ");
                System.out.println("5. Pelea Final: ");
                System.out.print("Seleccione una opcion: ");

                String option = escanear.nextLine();
                int n = Integer.parseInt(option);
                if(n == 1){
                    verificador = 1;
                    System.out.println();
                    soyMapa.viewMap();
                }else if (n == 2){
                    verificador = 2;
                    System.out.println();
                    Var7.verEstados();
                }else if (n == 3){
                    verificador = 3;
                    System.out.println();
                    Var7.verItems();
                }else if (n == 5){
                    verificador = 5;
                    getResultadojefe(Var7);
                    verificador = 0;
                }else{
                    verificador = n;
                }
            }

            if (verificador == 4 && conflicto == 1){
                System.out.println("Felicitaciones, terminaste el juego");
                System.out.println();

            }else{
                if(conflicto == 0){
                    System.out.println();
                    System.out.println("*********************************************************");
                    System.out.println("***Para terminar primero debe pelear con el Jefe Final***");
                    System.out.println("*********************************************************");
                    System.out.println();
                    NodoJefeFinal Final = new NodoJefeFinal();
                    Final.setNodeJefefinal(posNode);
                    Final.setMapaJefefinal(soyMapa);
                    Final.setTableJefefinal(typesNodes);
                    Final.Interactuar(Var7);
                }else{
                    System.out.println("Seleccion Incorrecta");
                }
            }

            if(posNode >= 12){
                continued = false;
            }
            System.out.println();
            
        }
        escanear.close(); //
        
    }


    /**
     * Se encarga de setear el nodo jefe final
     *
     * int n_nodo: Entero que representa el numero del nodo
     *
     * Al ser una funcion void no retorna nada
     **/
    public void setNodeJefefinal(int n_nodo){
        this.posNode = n_nodo;
    }


    /**
     * Se encarga de setear el mapa
     *
     * Mapa Var1: Mapa que representa el mapa del juego
     *
     * Al ser una funcion void no retorna nada
     **/
    public void setMapaJefefinal(Mapa Var1){
        this.soyMapa = Var1;
    }


    /**
     * Se encarga de guardar la informacion de los nodos
     *
     * Hashtable Var3: Hashtable que almacena los tipos de nodos
     *
     * Al ser una funcion void no retorna nada
     **/
    public void setTableJefefinal(Hashtable<Integer, String> Var3){
        this.typesNodes = Var3;
    }


    /**
     * Se encarga de elegir aleaoriamente un Jefe Final
     *
     * No recibe parametros
     *
     * Personaje randomJefe.get(index): Personaje que represena al jefe final
     **/
    public Personaje setJefe(){
        ArrayList<Personaje> randomJefe = new ArrayList<Personaje>();
        //Nombre Enemigos
        randomJefe.add(new Personaje("Mario Hugo"));
        randomJefe.add(new Personaje("Chiquitin"));
        randomJefe.add(new Personaje("Michelin"));
        randomJefe.add(new Personaje("Jumbito"));
        randomJefe.add(new Personaje("Patricio"));
        randomJefe.add(new Personaje("Clippy"));

        //Elije un enemigo aleatorio
        Random random_method = new Random();
        int index = random_method.nextInt(randomJefe.size());
        return randomJefe.get(index);
    }


    /**
     * Se encarga de realizar la pelea con el jefe final
     *
     * Jugador Pl: Jugador que representa al jugador de la partida
     *
     * Al ser una funcion void no retorna nada
     **/
    public void getResultadojefe(Jugador Pl){
        jefe = setJefe();
        System.out.println(Pl.getNombre()+" vs "+jefe.getNombre());
        Pl.combate(jefe);
        System.out.println("     Pelea Final     ");
        System.out.println(Pl.getNombre()+" vs "+jefe.getNombre());

        while(jefe.getHpactual()>=0){
            System.out.println("Golpe de "+Pl.getNombre());
            jefe.addHpactual(-Pl.getDanio());
            System.out.println("Hp Enemigo: "+jefe.getHpactual()); //Imprime
            
            System.out.println("Golpe de "+jefe.getNombre());
            Pl.addHpactual(-jefe.getDanio());
            System.out.println("Tu Hp: "+Pl.getHpactual()); //Imprime

        }
        if(Pl.getHpactual()>0){
            System.out.println("Felicitaciones Ganaste La Partida");
            System.exit(0);
        }else{
            System.out.println("Perdiste");
            System.exit(0);
        }
        conflicto = 1;
    }
}
