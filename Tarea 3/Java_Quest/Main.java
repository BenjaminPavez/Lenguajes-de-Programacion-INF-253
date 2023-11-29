import java.util.*;


public class Main {
    private static String name_player;
    public static void main(String[] args) {
        System.out.println("Hello world!");
        NodoInicial Start = new NodoInicial();

        //Inicio
        Scanner nombre = new Scanner(System.in);
        System.out.print("Escriba el nombre de su personaje: ");
        name_player = nombre.nextLine();
        Jugador Player = new Jugador(name_player);
        Start.Interactuar(Player);
        nombre.close();

    }
}