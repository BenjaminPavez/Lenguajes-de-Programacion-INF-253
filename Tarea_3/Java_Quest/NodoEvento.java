import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;



public class NodoEvento extends abstractNodo{
    //Atributos
    private String descripcion;
    private String alternativa1;
    private String alternativa2;
    private Item resultado1;
    private Item resultado2;
    private int posNode;
    private int event = 0;
    private Mapa soyMapa;
    private String typeNode = "NodoInicial";
    private Hashtable<Integer, String> typesNodes = new Hashtable<Integer, String>();

    //Metodos
    /**
     * Se encarga de mostrar lo que puede hacer el jugador
     *
     * Jugador Var6: Jugador que representa al jugador que esta en la partida
     *
     * Al ser una funcion void no retorna nada
     **/
    public void Interactuar(Jugador Var6){
        boolean continued = true;
        Scanner escanear = new Scanner(System.in);
        while(continued){
            System.out.println();
            System.out.println("    Bienvenido al Evento*    ");
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
                System.out.println("5. Evento: ");
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
                    Var6.verEstados();
                }else if (n == 3){
                    verificador = 3;
                    System.out.println();
                    Var6.verItems();
                }else if (n == 5 && event == 0){
                    //Evento
                    event = 1;
                    verificador = 5;
                    setDescripcion();
                    System.out.println();
                    System.out.println(descripcion);
                    System.out.println();
                    System.out.println(alternativa1);
                    System.out.println(alternativa2);
                    System.out.print("Seleccione una alternativa: ");
                    String resp_user = escanear.nextLine();
                    if(Integer.parseInt(resp_user) == 1){
                        setResultado1(Var6);
                        //getResultado1();
                    }else if(Integer.parseInt(resp_user) == 2){
                        setResultado2(Var6);
                        //getResultado2();
                    }else{
                        System.out.println("Ingrese un numero valido");
                    }
                    //System.exit(0);
                    verificador = 0;
                }else{
                    verificador = n;
                }
            }

            if (verificador == 4 && event == 1) {
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
                    nTienda.Interactuar(Var6);
                }else if(typeNode == "NodoCombate"){
                    //Avanzo al proximo nodo y entrego los datos
                    NodoCombate nCombate3 = new NodoCombate();
                    nCombate3.setNodeCombate(posNode);
                    nCombate3.setMapaCombate(soyMapa);
                    nCombate3.setTableCombate(typesNodes);
                    nCombate3.Interactuar(Var6);
                }else if(typeNode == "NodoEvento"){
                    //Avanzo al proximo nodo y entrego los datos
                    NodoEvento nEvento4 = new NodoEvento();
                    nEvento4.setNodeEvento(posNode);
                    nEvento4.setMapaEvento(soyMapa);
                    nEvento4.setTableEvento(typesNodes);
                    nEvento4.Interactuar(Var6);
                }else if(typeNode == "NodoJefeFinal"){
                    //Avanzo al proximo nodo y entrego los datos
                    NodoJefeFinal nJefeFinal5 = new NodoJefeFinal();
                    nJefeFinal5.setNodeJefefinal(posNode);
                    nJefeFinal5.setMapaJefefinal(soyMapa);
                    nJefeFinal5.setTableJefefinal(typesNodes);
                    nJefeFinal5.Interactuar(Var6);
                }

            }else{
                if(event == 0){
                    System.out.println();
                    System.out.println("**************************************************");
                    System.out.println("***Para avanzar primero debe ingresar al evento***");
                    System.out.println("**************************************************");
                    System.out.println();
                }else if(event == 1){
                    System.out.println();
                    System.out.println("**********************************");
                    System.out.println("***El Evento ya se ha realizado***");
                    System.out.println("**********************************");
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
     * Se encarga de setear el nodo evento
     *
     * int n_nodo: Entero que representa el numero del nodo
     *
     * Al ser una funcion void no retorna nada
     **/
    public void setNodeEvento(int n_nodo){
        this.posNode = n_nodo;
    }


    /**
     * Se encarga de setear el mapa
     *
     * Mapa Var1: Mapa que representa el mapa del juego
     *
     * Al ser una funcion void no retorna nada
     **/
    public void setMapaEvento(Mapa Var1){
        this.soyMapa = Var1;
    }


    /**
     * Se encarga de guardar la informacion de los nodos
     *
     * Hashtable Var3: Hashtable que almacena los tipos de nodos
     *
     * Al ser una funcion void no retorna nada
     **/
    public void setTableEvento(Hashtable<Integer, String> Var3){
        this.typesNodes = Var3;
    }




    /**
     * Se encarga de elegir una descripcion aleatoria
     *
     * No recibe parametros
     *
     * Al ser una funcion void no retorna nada
     **/
    public void setDescripcion(){
        ArrayList<String> randomAl1 = new ArrayList<String>();
        //Descripciones
        randomAl1.add("En el Bosque se encuentra un viejo aldeano que nos muestra dos pócimas, una de ellas tiene la\ncapacidad de aumentar el daño, pero la otra disminuye la defensa, el aldeano nos dice que no sabe\ncual es cual por lo que nos dice si queremos la que esta a su derecha o a su izquierda.");
        randomAl1.add("LP es un lugar muy tenebroso, pero este lugar oculta un poderoso virus encapsulado llamado C++\nque provoca un aumento de Hp del personaje, además se encuentra el virus Python que hace todo\nlo contrario, el gran problema es que ninguno de ellos trae etiquetado, lo único apreciable es que\nuno de ellos está contenido en una capsula de color negro y el otro en una de color rojo.");
        randomAl1.add("Caminando por el Desierto es un lugar muy sombrío, pero en este lugar se encuentra una tienda con\nsolo 2 armas, una de ellas permite quitar mas daño que cualquier otra arma de las tiendas, pero la\notra no funciona, el vendedor nos dice que lanzamos una moneda para saber cual comprar, si\nesta sale cara, nos llevamos el arma más poderosa del mapa y si sale sello nos llevamos el arma\ndescompuesta.");
        randomAl1.add("El DMAT es un lugar muy tétrico, pero en este lugar se oculta el C2-MAT023, poderosa pócima\ndescubierta por desconocidos de la USM, pero además nos encontramos con otra pócima, apodada\nC1-MAT023, el problema es una de ellas aumenta el Hp total, pero la otra la disminuye.");
        randomAl1.add("En la B022 se encuentra un científico que esta diseñando una maquina para crear dinero infinito, el\nproblema es que a veces la maquina destruye el dinero, por lo que nos pregunta si queremos\ningresar 100 javapesos para aumentarlo al doble. Si decides no ingresar el científico se molestará y te\nquitara Hp actual con su varita mágica.");
        randomAl1.add("La USM es un lugar muy prestigioso, pero en ls F408 se encuentra el testamento, que es una\npoción en fase Alpha por lo que, al ingerirla, podemos aumentar o disminuir nuestra defensa. Si decides no\ningerir la poción, tu daño disminuirá debido a el gran esfuerzo que tomo subir las escaleras.");
        randomAl1.add("El LDS es un lugar muy raro, pero en este lugar oculta un poderoso virus que provoca un aumento\nde Hp del personaje, el problema es que en algunas personas les causa una reacción alérgica,\nprovocando que disminuya el daño.Si decides no entrar, tu cantidad de defensa disminuirá debido\na la exposición prolongada en la USM.");
        randomAl1.add("El DFIS es un lugar muy tétrico, pero en este lugar se oculta el C2-FIS130, poderosa pócima que\naumenta o disminuye la defensa del personaje, pero minutos antes el Dr. del laboratorio trajo otra\npoción, de esta ultima no se tiene información acerca de lo que hace.");

        //Elije una descripcion aleatorio
        Random random_method = new Random();
        int index = random_method.nextInt(randomAl1.size());
        descripcion = randomAl1.get(index);
        setAlternativa1(index);
        setAlternativa2(index);

    }





    /**
     * Se encarga de mostrar y guardar la 1era alternativa
     *
     * int n_lista: Entero que representa la descripcion que aparecio
     *
     * Al ser una funcion void no retorna nada
     **/
    public void setAlternativa1(int n_lista){
        if(n_lista == 0){
            alternativa1 = "1. Beber la pócima de lado derecho";
        }else if(n_lista == 1){
            alternativa1 = "1. Ingerir capsula negra";
        }else if(n_lista == 2){
            alternativa1 = "1. Cara";
        }else if(n_lista == 3){
            alternativa1 = "1. Beber la pocíma C2-MAT023";
        }else if(n_lista == 4){
            alternativa1 = "1. Entregar los $100";
        }else if(n_lista == 5){
            alternativa1 = "1. Ingerir la pocíma";
        }else if(n_lista == 6){
            alternativa1 = "1. Adentrarse al LDS";
        }else if(n_lista == 7){
            alternativa1 = "1. Ingerir la pocíma C2-FIS130";
        }

    }





    /**
     * Se encarga de mostrar y guardar la 2da alternativa
     *
     * int n_lista: Entero que representa la descripcion que aparecio
     *
     * Al ser una funcion void no retorna nada
     **/
    public void setAlternativa2(int n_lista){
        if(n_lista == 0){
            alternativa2 = "2. Beber la pócima de lado izquierdo";
        }else if(n_lista == 1){
            alternativa2 = "2. Ingerir capsula roja";
        }else if(n_lista == 2){
            alternativa2 = "2. Sello";
        }else if(n_lista == 3){
            alternativa2 = "2. Beber la posíma C1-MAT023";
        }else if(n_lista == 4){
            alternativa2 = "2. No entregar los $100";
        }else if(n_lista == 5){
            alternativa2 = "2. No ingerir la pocíma";
        }else if(n_lista == 6){
            alternativa2 = "2. No ingresar al LDS";
        }else if(n_lista == 7){
            alternativa2 = "2. Ingerir la otra pocíma";
        }
    }





    /**
     * Se encarga de aplicar la alternativa seleccionada
     *
     * Jugador Obj3: Jugador que representa al jugador
     *
     * Jugador Obj3: Jugador que representa al jugador con los parametros modificados
     **/
    public Jugador setResultado1(Jugador Obj3){
        if(alternativa1 == "1. Beber la pócima de lado derecho"){
            //Aumenta Daño o Disminuye defensa
            Random aleatorio11 = new Random();
            int valorDado1 = aleatorio11.nextInt(2)+1;
            Random au = new Random();
            int Aumento = au.nextInt(10)+1;
            //Condicional
            if(valorDado1 == 1){
                System.out.println();
                System.out.println("Aumentando Daño...");
                Obj3.addDanio(valorDado1*Aumento);
            }else{
                System.out.println();
                System.out.println("Disminuyendo Defensa...");
                Obj3.addDefensa(-valorDado1*Aumento);
            }
            //Se muestra los cambios


        }else if(alternativa1 == "1. Ingerir capsula negra"){
            //Aumenta o Disminuye Hp actual
            Random aleatorio12 = new Random();
            int valorDado2 = aleatorio12.nextInt(2)+1;
            Random au = new Random();
            int Aumento = au.nextInt(10)+1;
            //Condicional
            if(valorDado2 == 1){
                System.out.println();
                System.out.println("Aumentando Hp Actual...");
                Obj3.addHpactual(valorDado2*Aumento);
            }else{
                System.out.println();
                System.out.println("Disminuyendo Hp Actual...");
                Obj3.addHpactual(-valorDado2*Aumento);
            }
            //Se muestra los cambios


        }else if(alternativa1 == "1. Cara"){
            //Cara o sello
            Random aleatorio13 = new Random();
            int valorDado3 = aleatorio13.nextInt(2)+1;
            if(valorDado3 == 1){
                resultado1 = new Item(100,100,100,100,100);
                Obj3.addNameitem("Vara Magica USM");
                resultado1.aplicar(Obj3);

                System.out.println();
                System.out.println("Item Aplicado: ");
                System.out.println("--------------------------------------------------------------------------------------------");
                System.out.printf("%2s. %20s %7s %16s %11s %13s %16s", "N°", "Articulo", "Precio", "Recuperacion Hp", "Aumento Hp", "Aumento Daño", "Aumento Defensa");
                System.out.println();

                System.out.println("--------------------------------------------------------------------------------------------");
                for(int y = 0; y<1; y++){
                    System.out.format("%2d. %20s %5d %11d %13d %12d %13d",
                            y+1, "Vara Magica USM", resultado1.getPrecio(), resultado1.getRecuperarhp(), resultado1.getAumentarhptotal(), resultado1.getAumentardanio(), resultado1.getAumentardefensa());
                    System.out.println();
                }
                Obj3.addItems(resultado1);
            }else{
                resultado1 = new Item(100,0,0,0,0);
                Obj3.addNameitem("Navaja sin filo");
                resultado1.aplicar(Obj3);

                System.out.println();
                System.out.println("Item Aplicado: ");
                System.out.println("--------------------------------------------------------------------------------------------");
                System.out.printf("%2s. %20s %7s %16s %11s %13s %16s", "N°", "Articulo", "Precio", "Recuperacion Hp", "Aumento Hp", "Aumento Daño", "Aumento Defensa");
                System.out.println();

                System.out.println("--------------------------------------------------------------------------------------------");
                for(int y = 0; y<1; y++){
                    System.out.format("%2d. %20s %5d %11d %13d %12d %13d",
                            y+1, "Navaja sin filo", resultado1.getPrecio(), resultado1.getRecuperarhp(), resultado1.getAumentarhptotal(), resultado1.getAumentardanio(), resultado1.getAumentardefensa());
                    System.out.println();
                }
                Obj3.addItems(resultado1);
            }
            //Se muestra los cambios


        }else if(alternativa1 == "1. Beber la pocíma C2-MAT023"){
            //Aumenta o dismunuye
            Random aleatorio14 = new Random();
            int valorDado4 = aleatorio14.nextInt(2)+1;
            Random au = new Random();
            int Aumento = au.nextInt(10)+1;
            //Condicional
            if(valorDado4 == 1){
                System.out.println();
                System.out.println("Aumentando Hp Total...");
                Obj3.addHptotal(valorDado4*Aumento);
            }else{
                System.out.println();
                System.out.println("Disminuyendo Hp Actual...");
                Obj3.addHptotal(-valorDado4*Aumento);
            }
            //Se muestra los cambios


        }else if(alternativa1 == "1. Entregar los $100"){
            Random aleatorio15 = new Random();
            int valorDado5 = aleatorio15.nextInt(2)+1;
            if(valorDado5 == 1){
                System.out.println();
                System.out.println("USM Bank: Se realizo una compra por $100 en MakeMoneyMachine");
                Obj3.addDinero(-100);
            }else{
                System.out.println();
                System.out.println("USM Bank: Se realizo una transferencia de $200 de MakeMoneyMachine");
                Obj3.addDinero(200);
            }
            //Se muestra los cambios


        }else if(alternativa1 == "1. Ingerir la pocíma"){
            Random aleatorio16 = new Random();
            int valorDado6 = aleatorio16.nextInt(2)+1;
            Random au = new Random();
            int Aumento = au.nextInt(10)+1;
            //Condicional
            if(valorDado6 == 1){
                System.out.println();
                System.out.println("Aumentando Defensas...");
                Obj3.addDefensa(Aumento);
            }else{
                System.out.println();
                System.out.println("Disminuyendo Defensas...");
                Obj3.addDefensa(-Aumento);
            }
            //Se muestra los cambios


        }else if(alternativa1 == "1. Adentrarse al LDS"){
            Random aleatorio17 = new Random();
            int valorDado7 = aleatorio17.nextInt(2)+1;
            Random au = new Random();
            int Aumento = au.nextInt(10)+1;
            //Condicional
            if(valorDado7 == 1){
                System.out.println();
                System.out.println("Aumentando Hp Actual...");
                Obj3.addHpactual(valorDado7*Aumento);
            }else{
                System.out.println();
                System.out.println("Disminuyendo Hp Actual...");
                Obj3.addDanio(-valorDado7*Aumento);
            }
            //Se muestra los cambios


        }else if(alternativa1 == "1. Ingerir la pocíma C2-FIS130"){
            Random aleatorio18 = new Random();
            int valorDado8 = aleatorio18.nextInt(2)+1;
            if(valorDado8 == 1){
                System.out.println();
                System.out.println("Aumentando Todo...");
                Obj3.addDinero(10);
                Obj3.addHpactual(10);
                Obj3.addHptotal(10);
                Obj3.addDanio(10);
                Obj3.addDefensa(10);
            }else{
                System.out.println();
                System.out.println("Disminuyendo Todo...");
                Obj3.addDinero(-10);
                Obj3.addHpactual(-10);
                Obj3.addHptotal(-10);
                Obj3.addDanio(-10);
                Obj3.addDefensa(-10);
            }
            //Se muestra los cambios
        }
        return Obj3;
    }




    /**
     * Se encarga de aplicar la alternativa seleccionada
     *
     * Jugador Obj4: Jugador que representa al jugador
     *
     * Jugador Obj4: Jugador que representa al jugador con los parametros modificados
     **/
    public Jugador setResultado2(Jugador Obj4){
        if(alternativa2 == "2. Beber la pócima de lado izquierdo"){
            //Aumenta Daño o Disminuye defensa
            Random aleatorio21 = new Random();
            int valorDado1 = aleatorio21.nextInt(2)+1;
            Random au = new Random();
            int Aumento = au.nextInt(10)+1;
            //Condicional
            if(valorDado1 == 1){
                System.out.println();
                System.out.println("Aumentando Daño...");
                Obj4.addDanio(valorDado1*Aumento);
            }else{
                System.out.println();
                System.out.println("Disminuyendo Defensa...");
                Obj4.addDefensa(-valorDado1*Aumento);
            }
            //Se muestra los cambios


        }else if(alternativa2 == "2. Ingerir capsula roja"){
            //Aumenta o Disminuye Hp actual
            Random aleatorio22 = new Random();
            int valorDado10 = aleatorio22.nextInt(2)+1;
            Random au = new Random();
            int Aumento = au.nextInt(10)+1;
            //Condicional
            if(valorDado10 == 1){
                System.out.println();
                System.out.println("Aumentando Hp Actual...");
                Obj4.addHpactual(valorDado10*Aumento);
            }else{
                System.out.println();
                System.out.println("Disminuyendo Hp Actual...");
                Obj4.addHpactual(-valorDado10*Aumento);
            }
            //Se muestra los cambios


        }else if(alternativa2 == "2. Sello"){
            //Cara o sello
            Random aleatorio23 = new Random();
            int valorDado11 = aleatorio23.nextInt(2)+1;
            if(valorDado11 == 1){
                resultado2 = new Item(100,100,100,100,100);
                Obj4.addNameitem("Vara Magica USM");
                resultado2.aplicar(Obj4);

                System.out.println();
                System.out.println("Item Aplicado: ");
                System.out.println("--------------------------------------------------------------------------------------------");
                System.out.printf("%2s. %20s %7s %16s %11s %13s %16s", "N°", "Articulo", "Precio", "Recuperacion Hp", "Aumento Hp", "Aumento Daño", "Aumento Defensa");
                System.out.println();
                System.out.println("--------------------------------------------------------------------------------------------");
                for(int y = 0; y<1; y++){
                    System.out.format("%2d. %20s %5d %11d %13d %12d %13d",
                            y+1, "Vara Magica USM", resultado2.getPrecio(), resultado2.getRecuperarhp(), resultado2.getAumentarhptotal(), resultado2.getAumentardanio(), resultado2.getAumentardefensa());
                    System.out.println();
                }
                Obj4.addItems(resultado2);
            }else{
                resultado2 = new Item(100,0,0,0,0);
                Obj4.addNameitem("Navaja sin filo");
                resultado2.aplicar(Obj4);

                System.out.println();
                System.out.println("Item Aplicado: ");
                System.out.println("--------------------------------------------------------------------------------------------");
                System.out.printf("%2s. %20s %7s %16s %11s %13s %16s", "N°", "Articulo", "Precio", "Recuperacion Hp", "Aumento Hp", "Aumento Daño", "Aumento Defensa");
                System.out.println();
                System.out.println("--------------------------------------------------------------------------------------------");
                for(int y = 0; y<1; y++){
                    System.out.format("%2d. %20s %5d %11d %13d %12d %13d",
                            y+1, "Navaja sin filo", resultado2.getPrecio(), resultado2.getRecuperarhp(), resultado2.getAumentarhptotal(), resultado2.getAumentardanio(), resultado2.getAumentardefensa());
                    System.out.println();
                }
                Obj4.addItems(resultado2);
            }
            //Se muestra los cambios


        }else if(alternativa2 == "2. Beber la posíma C1-MAT023"){
            //Aumenta o dismunuye
            Random valorDado24 = new Random();
            int valorDado12 = valorDado24.nextInt(2)+1;
            Random au = new Random();
            int Aumento = au.nextInt(10)+1;
            //Condicional
            if(valorDado12 == 1){
                System.out.println();
                System.out.println("Aumentando Hp Total...");
                Obj4.addHptotal(valorDado12*Aumento);
            }else{
                System.out.println();
                System.out.println("Disminuyendo Hp Total...");
                Obj4.addHptotal(-valorDado12*Aumento);
            }
            //Se muestra los cambios


        }else if(alternativa2 == "2. No entregar los $100"){
            Random aleatorio25 = new Random();
            int valorDado13 = aleatorio25.nextInt(2)+1;
            System.out.println("Disminuyendo Hp actual...");
            Obj4.addHpactual(-valorDado13*10);


        }else if(alternativa2 == "2. No ingerir la pocíma"){
            Random aleatorio26 = new Random();
            int valorDado14 = aleatorio26.nextInt(2)+1;
            System.out.println("Disminuyendo Daño...");
            Obj4.addDanio(-valorDado14*10);


        }else if(alternativa2 == "2. No ingresar al LDS"){
            Random aleatorio27 = new Random();
            int valorDado15 = aleatorio27.nextInt(2)+1;
            System.out.println("Disminuyendo Defensas...");
            Obj4.addDefensa(-valorDado15*10);


        }else if(alternativa2 == "2. Ingerir la otra pocíma"){
            Random aleatorio28  = new Random();
            int valorDado16 = aleatorio28.nextInt(2)+1;
            if(valorDado16 == 1){
                System.out.println();
                System.out.println("Aumentando Todo...");
                Obj4.addDinero(10);
                Obj4.addHpactual(10);
                Obj4.addHptotal(10);
                Obj4.addDanio(10);
                Obj4.addDefensa(10);
            }else{
                System.out.println();
                System.out.println("Disminuyendo Todo...");
                Obj4.addDinero(-10);
                Obj4.addHpactual(-10);
                Obj4.addHptotal(-10);
                Obj4.addDanio(-10);
                Obj4.addDefensa(-10);
            }
        }
        return Obj4;
    }
}
