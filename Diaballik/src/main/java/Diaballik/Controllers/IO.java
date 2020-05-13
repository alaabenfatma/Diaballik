package Diaballik.Controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Diaballik.Models.ConfigJeu;
import Diaballik.Models.Jeu;
import Diaballik.Models.PieceType;
import Diaballik.Models.Terrain;

class JeuToExport {
    public String player1;
    public String player2;
    public char[][] terrain = new char[7][7];
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
    public String CreationDate = formatter.format(date);

    public JeuToExport(Jeu j) {
        player1 = j.joueur1.name;
        player2 = j.joueur2.name;
        terrain = j.tr.toChar();
    }
}

public class IO {
    public static void ExportGameToJSON(Jeu j) {
        JeuToExport jte = new JeuToExport(j);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jte);
            try {
                String filename = "history.json";
                FileWriter fw = new FileWriter(filename, true); // the true will append the new data
                fw.write(json);// appends the string to the file
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