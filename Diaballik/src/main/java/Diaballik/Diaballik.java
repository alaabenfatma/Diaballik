package Diaballik;

import Diaballik.Controllers.TerrainUtils;
import Diaballik.Models.Terrain;

/**
 * Hello world!
 *
 */
public class Diaballik {
    public static void main(String[] args) {
        Terrain tr = new Terrain();
        tr.Create();
        tr.PrintTerrain();
        TerrainUtils.Swap(tr.getTerrain()[0][0], tr.getTerrain()[1][0]);
        tr.PrintTerrain();
        TerrainUtils.ExchangeBall(tr.getTerrain()[1][0], tr.getTerrain()[0][3]);
        tr.PrintTerrain();
    }
}
