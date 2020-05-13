package Diaballik.Controllers;

import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Diaballik.Models.ConfigJeu;
import Diaballik.Models.Jeu;
import Diaballik.Models.JeuJSON;
import Diaballik.Models.Terrain;

public class IO {
    public static void ExportGameToJSON(Jeu j) {
        JeuJSON jte = new JeuJSON(j);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(jte);
            try {
                FileWriter fw = new FileWriter("Diaballik/src/main/java/Diaballik/data/history.json", true);
                fw.write(json + System.lineSeparator());
                fw.close();
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static Jeu JeuFromJSON() {
        Jeu j = new Jeu();
        return j;
    }

    public static void main(String[] args) {
        Terrain t = new Terrain();
        t.Create();
        Jeu j = new Jeu();
        ConfigJeu cfg = new ConfigJeu();
        j.configurer(cfg);
        j.start();
        IO.ExportGameToJSON(j);
    }
}