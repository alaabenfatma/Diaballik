package Diaballik;

import Diaballik.Controllers.TerrainUtils;
import Diaballik.Models.*;

/**
 * Hello world!
 *
 */
public class Diaballik {
    public static void main(String[] args) {
        Terrain tr = new Terrain();
        tr.Create();
        tr.PrintTerrain();
        
        //tr.getTerrain()[6][6].move(1, 2);
        tr.PrintTerrain();
        System.out.println(tr.getTerrain()[6][6].PossiblePositions());
    }
}
