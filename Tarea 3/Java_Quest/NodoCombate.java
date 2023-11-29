import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;



public class NodoCombate extends abstractNodo{
    //Atributos
    Personaje enemigo;
    private int posNode;
    private int pelea = 0;
    private Mapa soyMapa;
    private String typeNode = "NodoInicial";
    private Hashtable<Integer, String> typesNodes = new Hashtable<Integer, String>();

    //Metodos
    /**
     * Se encarga de mostrar lo que puede hacer el jugador
     *
     * Jugador Var5: Jugador que representa al jugador que esta en la partida
     *
     * Al ser una funcion void no retorna nada
     **/
    public void Interactuar(Jugador Var5){
        boolean continued = true;
        Scanner escanear = new Scanner(System.in);
        while(continued){
            System.out.println();
            System.out.println("    Bienvenido al Combate*    ");
            System.out.println("Usted se encuentra en el nodo: "+posNode);
            setId(posNode); //Abstract
            int verificador = 0;
            while(verificador<4){
                System.out.println();
                System.out.println("Que desea hacer: ");
                System.out.println("1. Ver Mapa: ");
                System.out.println("2. Ver Estadisticas: ");
                System.out.println("3. Ver Items: ");
                System.out.println("4. Avanzar: ");
                System.out.println("5. Al Combate: ");
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
                    Var5.verEstados();
                }else if (n == 3){
                    verificador = 3;
                    System.out.println();
                    Var5.verItems();
                }else if (n == 5 && pelea == 0){
                    verificador = 5;
                    getResultado(Var5);
                    verificador = 0;
                }else{
                    verificador = n;
                }
            }

            if (verificador == 4 && pelea == 1) {
                ArrayList<Integer> siguientes = soyMapa.getNext(posNode);
                for(int h = 0; h<siguientes.size(); h++){
                    System.out.println("Proximo Nodo: "+siguientes.get(h));
                }
                System.out.print("Seleccione un nodo para avanzar: ");
                String Siguientes = escanear.nextLine();
                posNode = Integer.parseInt(Siguientes);
                typeNode = typesNodes.get(posNode);
                if(typeNode == "NodoTienda"){
                    //Avanzo al proximo nodo y entrego los datos
                    NodoTienda nTienda = new NodoTienda();
                    nTienda.setNodeTienda(posNode);
                    nTienda.setMapaTienda(soyMapa);
                    nTienda.setTableTienda(typesNodes);
                    nTienda.Interactuar(Var5);
                }else if(typeNode == "NodoCombate"){
                    //Avanzo al proximo nodo y entrego los datos
                    NodoCombate nCombate3 = new NodoCombate();
                    nCombate3.setNodeCombate(posNode);
                    nCombate3.setMapaCombate(soyMapa);
                    nCombate3.setTableCombate(typesNodes);
                    nCombate3.Interactuar(Var5);
                }else if(typeNode == "NodoEvento"){
                    //Avanzo al proximo nodo y entrego los datos
                    NodoEvento nEvento4 = new NodoEvento();
                    nEvento4.setNodeEvento(posNode);
                    nEvento4.setMapaEvento(soyMapa);
                    nEvento4.setTableEvento(typesNodes);
                    nEvento4.Interactuar(Var5);
                }else if(typeNode == "NodoJefeFinal"){
                    //Avanzo al proximo nodo y entrego los datos
                    NodoJefeFinal nJefeFinal5 = new NodoJefeFinal();
                    nJefeFinal5.setNodeJefefinal(posNode);
                    nJefeFinal5.setMapaJefefinal(soyMapa);
                    nJefeFinal5.setTableJefefinal(typesNodes);
                    nJefeFinal5.Interactuar(Var5);
                }

            }else{
                if(pelea == 0){
                    System.out.println();
                    System.out.println("*****************************************************");
                    System.out.println("***Para avanzar primero debe pelear con su enemigo***");
                    System.out.println("*****************************************************");
                    System.out.println();
                }else if(pelea == 1){
                    System.out.println();
                    System.out.println("*********************************");
                    System.out.println("***La pelea ya se ha realizado***");
                    System.out.println("*********************************");
                    System.out.println();

                }else{
                    System.out.println("Seleccion Incorrecta");
                }
            }

            if(posNode >= 12){
                continued = false;
            }
            System.out.println();
        }
        escanear.close();
    }


    /**
     * Se encarga de setear el nodo combate
     *
     * int n_nodo: Entero que representa el numero del nodo
     *
     * Personaje randomJefe.get(index): Personaje que represena al jefe final
     **/
    public void setNodeCombate(int n_nodo){
        this.posNode = n_nodo;
    }


    /**
     * Se encarga de setear el mapa
     *
     * Mapa Var1:Mapa que representa el mapa del juego
     *
     * Al ser una funcion void no retorna nada
     **/
    public void setMapaCombate(Mapa Var1){
        this.soyMapa = Var1;
    }


    /**
     * Se encarga de guardar la informacion de los nodos
     *
     * Hashtable Var3: Hashtable que almacena los tipos de nodos
     *
     * Al ser una funcion void no retorna nada
     **/
    public void setTableCombate(Hashtable<Integer, String> Var3){
        this.typesNodes = Var3;
    }


    /**
     * Se encarga de elegir aleaoriamente un enemigo
     *
     * No recibe parametros
     *
     * Personaje randomEnemigo.get(index): Personaje que represena al enemigo
     **/
    public Personaje setEnemigo(){
        ArrayList<Personaje> randomEnemigo = new ArrayList<Personaje>();
        //Nombre Enemigos
        randomEnemigo.add(new Personaje("Juan Carlos Bodoque"));
        randomEnemigo.add(new Personaje("Kiwi"));
        randomEnemigo.add(new Personaje("Chispita"));
        randomEnemigo.add(new Personaje("Tulio Triviño"));
        randomEnemigo.add(new Personaje("Doctor Simi"));
        randomEnemigo.add(new Personaje("Renacin"));

        //Elije un enemigo aleatorio
        Random random_method = new Random();
        int index = random_method.nextInt(randomEnemigo.size());
        return randomEnemigo.get(index);

    }


    /**
     * Se encarga de realizar la pelea con algun enemigo
     *
     * Jugador w: Jugador que representa al jugador de la partida
     *
     * Al ser una funcion void no retorna nada
     **/
    public void getResultado(Jugador w){
        enemigo = setEnemigo();
        System.out.println(w.getNombre()+" vs "+enemigo.getNombre());
        w.combate(enemigo); // Seteo al enemigo

        while(enemigo.getHpactual()>=0){
            System.out.println("Golpe de "+w.getNombre());
            enemigo.addHpactual(-w.getDanio());
            System.out.println("Hp Enemigo: "+enemigo.getHpactual()); //Imprime
            
            System.out.println("Golpe de "+enemigo.getNombre());
            w.addHpactual(-enemigo.getDanio());
            System.out.println("Tu Hp: "+w.getHpactual()); //Imprime
        }
        if(w.getHpactual()>0){
            System.out.println("Felicitaciones Ganaste");
        }else{
            System.out.println(w.getHpactual());
            System.out.println("Perdiste");
            System.exit(0);

        }
        pelea = 1;

    }
}
