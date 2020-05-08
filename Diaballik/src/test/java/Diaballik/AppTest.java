package Diaballik;

import static org.junit.Assert.*;
import org.junit.Test;


import Diaballik.Models.*;
/**
 * Unit test for simple App.
 */
public class AppTest {
    Terrain tr = new Terrain();
    /**
     * @author Thomas
     */
    // ##################### Terrain.java #####################
    @Test
    public void Create_test(){
        boolean test = true;
        tr.Create();
        Piece[][] Terrain = tr.getTerrain();
        for(int i = 0; (i < tr.taille()) && test ;i++){
            if(Terrain[0][i].Type != PieceType.Black){
                test = false;
                System.out.println("Type : "+Terrain[0][i].Type+" à la position <0,"+i+"> au lieux de Black");
            }
        }
        for(int i = 0; (i < tr.taille()) && test ;i++){
            if(Terrain[6][i].Type != PieceType.White){
                test = false;
                System.out.println("Type : "+Terrain[6][i].Type+" à la position <0,"+i+"> au lieux de White");
            }
        }
        for(int i = 1; (i < tr.taille()-1) && test ;i++){
            for(int j = 0; (j < tr.taille()) && test ;j++){
                if(Terrain[i][j].Type != PieceType.Empty){
                    test = false;
                    System.out.println("Type : "+Terrain[0][i].Type+" à la position <"+i+","+j+"> au lieux de Empty");
                }
            }
        }
        assertTrue( test );
    }

    /**
     * 
     * @author Thomas
     */

    @Test
    public void Setup_test(){
        boolean test =true;
        Piece[][] Tr = tr.Create();
        if(Tr[0][3].HasBall != true){
            test = false;
            System.out.println( "Aucune balle à la posistion <0,3>");
        }
        if(Tr[6][3].HasBall != true){
            test = false;
            System.out.println( "Aucune balle à la posistion <6,3>");
        }
        assertTrue( test );
    }

    /**
     * On teste si le terrain est a une taille de 7
     * @author Hedi
     */
 
    @Test
    public void testtailleTrue(){
        tr.Create();
        assertEquals(tr.taille(), 7);
    }


    @Test
    public void testtailleFalse(){
        tr.Create();
        assertNotEquals(tr.taille(), 6);
    }


    @Test
    public void testgetTerrain(){
        Piece [][] terrainCreate = tr.Create();
        Piece [][] terrainget = tr.getTerrain();

        for (int i = 0; i < tr.taille(); i++){
            for (int j = 0; j < tr.taille(); j++){
                assertEquals(terrainCreate[i][j], terrainget[i][j]);
            }
        } 
    }

    /**
     * On verfie si une case est bien occupee ou pas
     * @author Hedi
     */

    @Test 
    public void testisOccupiedTrue(){
        Position pos = new Position(0, 0);
        tr.Create();
        assertTrue(tr.isOccupied(pos));   
    }


    @Test 
    public void testisOccupiedFalse(){
        Position pos = new Position(3, 3);
        tr.Create();
        assertFalse(tr.isOccupied(pos));   
    }

}
