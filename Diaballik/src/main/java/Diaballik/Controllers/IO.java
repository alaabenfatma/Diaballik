package Diaballik.Controllers;

import java.io.File;
import java.io.IOException;

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
    public String nowDate = 
    JeuToExport(Jeu j){
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
            objectMapper.writeValue(new File("./terrain.json"), jte);
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