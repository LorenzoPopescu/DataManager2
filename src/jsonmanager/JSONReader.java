/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonmanager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

/**
 *
 * @author MC
 */
public class JSONReader {
    
    public static final String JSON_FILE="libri.json";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Libro> libri =new ArrayList<Libro>();
        //Libro libri[];
        StringBuilder sb;
        
        InputStream input = new FileInputStream(JSON_FILE);
        
        JsonReader jsonReader = Json.createReader(input);
        
        JsonObject jsonObject = jsonReader.readObject();
        
        jsonReader.close();
        
        JsonObject innerJsonObject = jsonObject.getJsonObject("libreria");
        
        JsonArray jsonArray = innerJsonObject.getJsonArray("libri");
        
        //libri = new Libro[jsonArray.size()];
        
        int index = 0;
        
        for (JsonValue element : jsonArray) {
            Libro libro = new Libro();
            
            libro.setGenere(element.asJsonObject().getString("genere"));
            libro.setTitolo(element.asJsonObject().getString("titolo"));
            libro.setAutore(element.asJsonObject().getString("autore"));
            libro.setPrezzo((float) element.asJsonObject().getJsonNumber("prezzo").doubleValue());
            
            libri.add(libro);        
        }
        
        for (index=0; index<libri.size(); index++)
            System.out.println(libri.get(index).toString());   
    }
    
}
