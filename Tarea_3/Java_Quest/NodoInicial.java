import java.util.*;



public class NodoInicial extends abstractNodo{
    //Atributos
    private  String name_player;
    private String nodoAnterior;
    private String typeNode = "NodoInicial";
    private static Hashtable<Integer, String> typesNodes = new Hashtable<Integer, String>();
    private List<String> setNodes = new ArrayList<String>();
    private int posNode = 0;

    //Metodos
    /**
     * Se encarga de mostrar lo que puede hacer el jugador
     *
     * Jugador Player3: Jugador que representa al jugador que esta en la partida
     *
     * Al ser una funcion void no retorna nada
     **/
    public void Interactuar(Jugador Player3){
        String Contexto = "Tras exitosamente superar tus certamenes con la ayuda de la herramienta que programaste \nen la tarea anterior, te encuentras finalmente con algo de tiempo libre en tus manos, y decides \nutilizarlo para continuar con uno de tus hobbies, hacer videojuegos!";
        System.out.println("Bienvenido a Java Quest");
        Scanner escanear = new Scanner(System.in);
        System.out.println("1. Nueva Partida ");
        System.out.println("2. Salir ");
        System.out.print("Seleccione: ");
        String valor = escanear.nextLine();


        if(Integer.parseInt(valor) == 1) {
            for (int k = 0; k < Contexto.length(); k++) {
                System.out.print(Contexto.charAt(k));
            }
            System.out.println();
        }else{
            System.exit(0);
        }
        


        System.out.print("Ingrese la profundidad del mapa: ");
        String Depth = escanear.nextLine();
        System.out.println("Creando Mapa, espere");
        

        //Crea el HashMap
        Mapa soyMapa = new Mapa(name_player,Integer.parseInt(Depth));
        soyMapa.setMap();
        

        //Añade los elementos a la lista setNodes
        setNodes.add("NodoTienda");
        setNodes.add("NodoCombate");
        setNodes.add("NodoEvento");
        

        //Calculo el porcentaje de aparicion en el mapa
        int N_NodoTienda = Math.round((10*(soyMapa.getNumnodos()-2))/100);
        int N_NodoCombate = Math.round((60*(soyMapa.getNumnodos()-2))/100);
        int N_NodoEvento = Math.round((30*(soyMapa.getNumnodos()-2))/100);


        //Buscamos aleatoriamente un tipo de nodo en la lista y se intercalan
        Random random_method = new Random();
        int val = 0;
        for(int u = 1; u<12; u++) {
            int index = random_method.nextInt(setNodes.size());
            if (val == 0) { //La primera iteracion
                typesNodes.put(u, (String) setNodes.get(index));
                nodoAnterior = (String) setNodes.get(index);
                val = 1;
                //Compruebo la cantidad de repeticiones
                if(setNodes.get(index) == "NodoTienda") {
                    N_NodoTienda = N_NodoTienda - 1;
                    if(N_NodoTienda == 0) {
                        setNodes.remove(index);
                    }
                }else if(setNodes.get(index) == "NodoCombate") {
                    N_NodoCombate = N_NodoCombate - 1;
                    if(N_NodoCombate == 0) {
                        setNodes.remove(index);
                    }
                }else{
                    N_NodoEvento = N_NodoEvento - 1;
                    if(N_NodoEvento == 0) {
                        setNodes.remove(index);
                    }
                }

            }else if (setNodes.get(index) != nodoAnterior){
                typesNodes.put(u, (String) setNodes.get(index));
                nodoAnterior = (String) setNodes.get(index);

                //Compruebo la cantidad de repeticiones
                if(setNodes.get(index) == "NodoTienda") {
                    N_NodoTienda = N_NodoTienda - 1;
                    if(N_NodoTienda == 0) {
                        setNodes.remove(index);
                    }
                }else if(setNodes.get(index) == "NodoCombate") {
                    N_NodoCombate = N_NodoCombate - 1;
                    if (N_NodoCombate == 0) {
                        setNodes.remove(index);
                    }
                }else{
                    N_NodoEvento = N_NodoEvento - 1;
                    if (N_NodoEvento == 0) {
                        setNodes.remove(index);
                    }
                }
            }else if (setNodes.get(index) == nodoAnterior){
                if(setNodes.get(index) == "NodoCombate"){
                    typesNodes.put(u, "NodoEvento");
                    nodoAnterior = "NodoEvento";
                    N_NodoEvento = N_NodoEvento - 1;
                    if (N_NodoEvento == 0) {
                        setNodes.remove(index);
                    }

                }else if(setNodes.get(index) == "NodoEvento") {
                    typesNodes.put(u, "NodoCombate");
                    nodoAnterior = "NodoCombate";
                    N_NodoCombate = N_NodoCombate - 1;
                    if (N_NodoCombate == 0) {
                        setNodes.remove(index);
                    }

                }
            }
        }

        typesNodes.put(12, "NodoJefeFinal");
        System.out.println(typesNodes);


        //Ciclo con la visita de cada nodo
        boolean continued = true;
        while(continued){
            System.out.println();
            System.out.println("    Bienvenido al "+typeNode+"    ");
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
                    Player3.verEstados();
                }else if (n == 3){
                    verificador = 3;
                    System.out.println();
                    Player3.verItems();
                }
                else{
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
                    nTienda.Interactuar(Player3);
                }else if(typeNode == "NodoCombate"){
                    //Avanzo al proximo nodo y entrego los datos
                    NodoCombate nCombate3 = new NodoCombate();
                    nCombate3.setNodeCombate(posNode);
                    nCombate3.setMapaCombate(soyMapa);
                    nCombate3.setTableCombate(typesNodes);
                    nCombate3.Interactuar(Player3);
                }else if(typeNode == "NodoEvento"){
                    //Avanzo al proximo nodo y entrego los datos
                    NodoEvento nEvento4 = new NodoEvento();
                    nEvento4.setNodeEvento(posNode);
                    nEvento4.setMapaEvento(soyMapa);
                    nEvento4.setTableEvento(typesNodes);
                    nEvento4.Interactuar(Player3);
                }else if(typeNode == "NodoJefeFinal"){
                    //Avanzo al proximo nodo y entrego los datos
                    NodoJefeFinal nJefeFinal5 = new NodoJefeFinal();
                    nJefeFinal5.setNodeJefefinal(posNode);
                    nJefeFinal5.setMapaJefefinal(soyMapa);
                    nJefeFinal5.setTableJefefinal(typesNodes);
                    nJefeFinal5.Interactuar(Player3);
                }
            }else{
                System.out.println("Seleccion Incorrecta");
            }

            if(posNode >= 12){
                continued = false;
            }
            System.out.println();
        }
        escanear.close();
    }
}
