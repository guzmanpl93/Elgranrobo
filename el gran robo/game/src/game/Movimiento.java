package game;

public class Movimiento {
    //atributos id_movimiento, id jugador, id equipo, tiempo, nombre, pos x, pos y
    private int Id_Movimiento;
    private int Id_Jugador;
    private int Id_Equipo;
    
    private String Nombre;
    private int PosX;
    private int PosY;
    private int Tiempo;

    //getters y setters
    public int getId_Movimiento() {
        return Id_Movimiento;
    }

    public void setId_Movimiento(int id_Movimiento) {
        Id_Movimiento = id_Movimiento;
    }

    public int getId_Jugador() {
        return Id_Jugador;
    }

    public void setId_Jugador(int id_Jugador) {
        Id_Jugador = id_Jugador;
    }

    public int getId_Equipo() {
        return Id_Equipo;
    }

    public void setId_Equipo(int id_Equipo) {
        Id_Equipo = id_Equipo;
    }
    
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getPosX() {
        return PosX;
    }

    public void setPosX(int posX) {
        PosX = posX;
    }

    public int getPosY() {
        return PosY;
    }

    public void setPosY(int posY) {
        PosY = posY;
    }

    public int getTiempo() {
        return Tiempo;
    }

    public void setTiempo(int tiempo) {
        Tiempo = tiempo;
    }

    //Metodos

    //mover() es un metodo que mueve a un jugador
    public void mover(int PosX, int PosY) {
        this.PosX = PosX;
        this.PosY = PosY;
    }

    //lugarInicio() es un metodo que muestra el lugar de inicio de un jugador
    public void lugarInicio() {
        System.out.println("El jugador " + getNombre() + " ha empezado en la posicion " + getPosX() + ", " + getPosY());
    }

    //lugarValido() es un metodo que muestra si la posicion es valida
    public void lugarValido() {
        if (getPosX() >= 0 && getPosX() <= 100 && getPosY() >= 0 && getPosY() <= 100) {
            System.out.println("La posicion es valida");
        } else {
            System.out.println("La posicion no es valida");
        }
    }

    //tiempo() es un metodo que muestra el tiempo de juego
    public void tiempo() {
        System.out.println("El tiempo de juego es de " + getTiempo() + " minutos");
    }

}
