import java.util.*;



public class NodoTienda extends abstractNodo{
    //Atributos
    private List<Item> inventario = new ArrayList<>();
    private int posNode;
    private Mapa soyMapa;
    private String typeNode = "NodoInicial";
    private ArrayList<String> setItems = new ArrayList<String>();
    private Hashtable<Integer, String> typesNodes = new Hashtable<Integer, String>();
    private Scanner escanear = new Scanner(System.in);

    //Metodos
    /**
     * Se encarga de mostrar lo que puede hacer el jugador
     *
     * Jugador Var0: Jugador que representa al jugador que esta en la partida
     *
     * Al ser una funcion void no retorna nada
     **/
    public void Interactuar(Jugador Var0){
        boolean continued = true;
        
        while(continued){
            System.out.println();
            System.out.println("    Bienvenido a la Tienda*    ");
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
                System.out.println("5. Ver Tienda: ");
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
                    Var0.verEstados();
                }else if (n == 3){
                    verificador = 3;
                    System.out.println();
                    Var0.verItems();
                }else if (n == 5){
                verificador = 5;
                int valor = 0;
                //getCatalogo();
                //comprar();
                comprar(valor, Var0);
                verificador = 0;
                }else{
                    verificador = n;
                }
            }

            if (verificador == 4) {
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
                    nTienda.Interactuar(Var0);
                }else if(typeNode == "NodoCombate"){
                    //Avanzo al proximo nodo y entrego los datos
                    NodoCombate nCombate3 = new NodoCombate();
                    nCombate3.setNodeCombate(posNode);
                    nCombate3.setMapaCombate(soyMapa);
                    nCombate3.setTableCombate(typesNodes);
                    nCombate3.Interactuar(Var0);
                }else if(typeNode == "NodoEvento"){
                    //Avanzo al proximo nodo y entrego los datos
                    NodoEvento nEvento4 = new NodoEvento();
                    nEvento4.setNodeEvento(posNode);
                    nEvento4.setMapaEvento(soyMapa);
                    nEvento4.setTableEvento(typesNodes);
                    nEvento4.Interactuar(Var0);
                }else if(typeNode == "NodoJefeFinal"){
                    //Avanzo al proximo nodo y entrego los datos
                    NodoJefeFinal nJefeFinal5 = new NodoJefeFinal();
                    nJefeFinal5.setNodeJefefinal(posNode);
                    nJefeFinal5.setMapaJefefinal(soyMapa);
                    nJefeFinal5.setTableJefefinal(typesNodes);
                    nJefeFinal5.Interactuar(Var0);
                }
            }else{
                System.out.println("Seleccion Incorrecta");
            }

            if(posNode >= 12){
                continued = false;
            }
            System.out.println();
            
        }
        escanear.close(); //
    }


    /**
     * Se encarga de setear el nodo tienda
     *
     * int n_nodo: Jugador que representa al jugador que esta en la partida
     *
     * Al ser una funcion void no retorna nada
     **/
    public void setNodeTienda(int n_nodo){
        this.posNode = n_nodo;
    }


    /**
     * Se encarga de setear el mapa
     *
     * Mapa Var1:Mapa que representa el mapa del juego
     *
     * Al ser una funcion void no retorna nada
     **/
    public void setMapaTienda(Mapa Var1){
        this.soyMapa = Var1;
    }


    /**
     * Se encarga de elegir los items que tendra la tienda
     *
     * No recibe parametros
     *
     * ArrayList itemsTienda: ArrayList que contiene los items de la tienda
     **/
    public ArrayList<String> setProducts(){
        setItems.add("Espada");
        setItems.add("Bola de Nieve");
        setItems.add("Frisbee");
        setItems.add("Tortuga");
        setItems.add("Estrella de Mar");
        setItems.add("Resortera");
        setItems.add("Bomba");
        setItems.add("Pastel");
        setItems.add("Flechas");
        setItems.add("Pistola de agua");
        setItems.add("Dardo");
        setItems.add("Bola de Fuego");
        setItems.add("Antorcha");

        //Aleatoriamente agrega los datos que tienen los items
        Random precioRandom = new Random();
        Random recuhpRandom = new Random();
        Random auhpRandom = new Random();
        Random audaRandom = new Random();
        Random audeRandom = new Random();

        Random random_method = new Random();
        ArrayList<String> itemsTienda = new ArrayList<String>();
        for(int u = 1; u<8; u++){
            int ran1 = precioRandom.nextInt(100)+10;
            int ran2 = recuhpRandom.nextInt(20)+2;
            int ran3 = auhpRandom.nextInt(20)+2;
            int ran4 = audaRandom.nextInt(20)+2;
            int ran5 = audeRandom.nextInt(20)+2;
            int index = random_method.nextInt(setItems.size());
            itemsTienda.add((String) setItems.get(index));
            setItems.remove(index);
            inventario.add(new Item(ran1,ran2,ran3,ran4,ran5));
        }
        return itemsTienda;
    }


    /**
     * Se encarga de mostrar lo que puede hacer el jugador
     *
     * Jugador Var5: Jugador que representa al jugador que esta en la partida
     *
     * Al ser una funcion void no retorna nada
     **/
    public void setTableTienda(Hashtable<Integer, String> Var3){
        this.typesNodes = Var3;
    }

    public void getCatalogo(){
        /*
        ArrayList Tienda = setProducts();
        System.out.println("Productos Disponibles");
        System.out.println("--------------------------------------------------------------------------------------------");
            System.out.printf("%2s. %20s %7s %16s %11s %13s %16s", "N°", "Articulo", "Precio", "Recuperacion Hp", "Aumento Hp", "Aumento Daño", "Aumento Defensa");
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------------");
        for(int y = 0; y<Tienda.size(); y++){
            System.out.format("%2d. %20s %5d %11d %13d %12d %13d",
                    y+1, Tienda.get(y), inventario.get(y).getPrecio(), inventario.get(y).getRecuperarhp(), inventario.get(y).getAumentarhptotal(), inventario.get(y).getAumentardanio(), inventario.get(y).getAumentardefensa());
            System.out.println();
        }
        System.out.println("--------------------------------------------------------------------------------------------");

         */
    }


    /**
     * Se encarga de mostrar lo que puede hacer el jugador
     *
     * Jugador Var5: Jugador que representa al jugador que esta en la partida
     *
     * Al ser una funcion void no retorna nada
     **/
    public void comprar(int cesta, Jugador p){
        //Scanner selectItem = new Scanner(System.in);
        ArrayList<String> Tienda = setProducts();
        System.out.println("Productos Disponibles");
        System.out.println("--------------------------------------------------------------------------------------------");
            System.out.printf("%2s. %20s %7s %16s %11s %13s %16s", "N°", "Articulo", "Precio", "Recuperacion Hp", "Aumento Hp", "Aumento Daño", "Aumento Defensa");
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------------");
        for(int y = 0; y<Tienda.size(); y++){
            System.out.format("%2d. %20s %5d %11d %13d %12d %13d",
                    y+1, Tienda.get(y), inventario.get(y).getPrecio(), inventario.get(y).getRecuperarhp(), inventario.get(y).getAumentarhptotal(), inventario.get(y).getAumentardanio(), inventario.get(y).getAumentardefensa());
            System.out.println();
        }
        System.out.println("--------------------------------------------------------------------------------------------");

        //Pregunta Usuario
        System.out.print("Seleccione el N° del item: ");
        String sigItem = escanear.nextLine();

        //Selecciona un item
        System.out.println("Articulo Comprado");
        //Añado el elemento a los items
        p.addItems(inventario.get(Integer.parseInt(sigItem)-1));
        p.addNameitem((String) Tienda.get(Integer.parseInt(sigItem)-1));
        p.verItems();

        p.addDinero(-inventario.get(Integer.parseInt(sigItem)-1).getPrecio());
        p.addDanio(inventario.get(Integer.parseInt(sigItem)-1).getAumentardanio());
        p.addDefensa(inventario.get(Integer.parseInt(sigItem)-1).getAumentardefensa());
        p.addHptotal(inventario.get(Integer.parseInt(sigItem)-1).getAumentarhptotal());
        System.out.println("       Boleta       ");
        System.out.println("    Almacenes USM    ");
        System.out.println("Su saldo actual es de: "+p.getDinero());
        System.out.println("Su daño actual es de: "+p.getDanio());
        System.out.println("Su defensa actual es de: "+p.getDefensa());
    }
    
}
