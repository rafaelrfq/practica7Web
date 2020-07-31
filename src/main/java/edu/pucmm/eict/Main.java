package edu.pucmm.eict;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONObject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnirestException {
        HttpResponse listadoEstudiantes = Unirest.get("http://localhost:7000/api/estudiante/")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();

        System.out.println("Listado de estudiantes obtenido:\n" + listadoEstudiantes.getBody()+"\n");


        System.out.println("Busqueda de estudiante por matricula. Inserte la matricula: ");
        Scanner in = new Scanner(System.in);

        String matricula = in.nextLine();

        HttpResponse busquedaEstudiante = Unirest.get("http://localhost:7000/api/estudiante/" + matricula)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();

        System.out.println("El estudiante obtenido en la busqueda es:\n" + busquedaEstudiante.getBody()+"\n");


        JSONObject estudiante = new JSONObject();
        System.out.println("Agregar nuevo estudiante: \nMatricula: ");
        String matri = in.nextLine();
        estudiante.put("matricula",matri);
        System.out.println("\nNombre: ");
        String nombre = in.nextLine();
        estudiante.put("nombre",nombre);
        System.out.println("\nCarrera: ");
        String carrera = in.nextLine();
        estudiante.put("carrera",carrera);


        HttpResponse agregarEstudiante = Unirest.post("http://localhost:7000/api/estudiante/")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(estudiante)
                .asJson();

        System.out.println("Estudiante insertado. Datos: " + agregarEstudiante.getBody());

        System.out.println("\n\nInserte la matricula para borrar el estudiante asignado a esta: \n");
        String matriculaBorrar = in.nextLine();

        HttpResponse eliminarEstudiante = Unirest.delete("http://localhost:7000/api/estudiante/" + matriculaBorrar).asEmpty();

        // Cuando le hago print me retorna null pero esta borrando correctamente
        System.out.println("\n\nEstudiante borrado: " + eliminarEstudiante.getBody());
    }
}
