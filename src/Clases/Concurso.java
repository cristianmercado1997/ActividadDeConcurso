package clases;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class Concurso {
    private String nombre;
    private final Integer numProblemas;
    private HashSet<String> equipos;
    private LinkedList<Envios> respuestas;
    
    public Concurso(String nombre, Integer numProblemas) {
        this.nombre = nombre;
        this.numProblemas = numProblemas;
        this.equipos = new HashSet<>();
        this.respuestas = new LinkedList<>();
    }
    
    public Concurso(String nombre) {
        this(nombre, 5);
    }
    
    public String getNombre() { return nombre; }
    public Integer getNumProblemas() { return numProblemas; }
    public HashSet<String> getEquipos() { return equipos; }
    
    
    

    public int getNumEquipos() { return this.equipos.size(); }
    
       public void añadirEquipos(String... str) {
        Collections.addAll(equipos, str);
    }
    
    boolean eliminarEquipo(String equipo) {
        //Recorremos la lista de envio para borrar los envios hechos por el equipo
        //Los que encontremos los guardamos en una lista auxiliar
        LinkedList<Envios> envio = new LinkedList<>();
        for(Envios ev : this.respuestas) {
            if(ev.getNombreEquipo().equals(equipo)) {
                envio.add(ev);
            }
        }
        //Eso lo hacemos porque la clase LinkedList permite eliminar un conjunto de elementos a la vez
        this.respuestas.removeAll(envio);
        //Luego eliminamos el equipo del hashmap con su metodo propio que convenientemente
        //Retorna un booleano tambien: true si se pudo -> false si no existe
        return this.equipos.remove(equipo);
    }
    
    public Envios registrarEnvio(String nombreEquipo, Integer numProblema, String respuesta) {
        if(this.equipos.contains(nombreEquipo)) { //Si el equipo esta registrado...
            //Si el numero no es negativo y es menor o igual que el total de problemas
            if(!(numProblema < 0) && (numProblema <= numProblemas)) {
                //Si el objeto respuesta no es nulo y la cadena no esta vacia
                if((respuesta != null) && !(respuesta.isEmpty())) {
                    //Si cumple con todo, se almacena y se retorna
                    Envios ev = new Envios(nombreEquipo, numProblema, respuesta);
                    this.respuestas.add(ev);
                    return ev;
                }
            }
        }
        
        //Si falla en cualquier if, pailas, llega hasta aca
        return null;
    }
}