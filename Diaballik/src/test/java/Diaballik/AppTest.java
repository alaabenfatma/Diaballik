package Diaballik;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

//import org.junit.Rule;
import org.junit.Test;
//import org.junit.rules.ExpectedException;

import Diaballik.Models.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * 
     * @author Thomas
     */
    // ##################### Terrain.java #####################
    @Test
    public void Create_test(){
        boolean test = true;
        Terrain tr = new Terrain();
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
        Terrain tr = new Terrain();
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
        Terrain terrain = new Terrain();
        terrain.Create();
        assertEquals(terrain.taille(), 7);
    }


    @Test
    public void testtailleFalse(){
        Terrain terrain = new Terrain();
        terrain.Create();
        assertNotEquals(terrain.taille(), 6);
    }


    @Test
    public void testgetTerrain(){
        Terrain terrain = new Terrain();
        Piece [][] terrainCreate = terrain.Create();
        Piece [][] terrainget = terrain.getTerrain();

        for (int i = 0; i < terrain.taille(); i++){
            for (int j = 0; j < terrain.taille(); j++){
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
        Terrain terrain = new Terrain();
        Position pos = new Position(0, 0);
        terrain.Create();
        assertTrue(terrain.isOccupied(pos));   
    }


    @Test 
    public void testisOccupiedFalse(){
        Terrain terrain = new Terrain();
        Position pos = new Position(3, 3);
        terrain.Create();
        assertFalse(terrain.isOccupied(pos));   
    }
    // ##################### Piece.java #####################
    /**
     * Test du constructeur Piece
     * @author Thomas
     */
    @Test
    public void Piece_test(){
        boolean test = true;
        Terrain tr = new Terrain();
        Piece Tr = tr.Create()[0][3];
        if(Tr.HasBall != true){
            test = false;
            System.out.println(" Ne possède pas la balle à la pos <0,6>");
        }
        if(!Tr.Position.equals(new Position(0,3))){
            test = false;
            System.out.println(" Position différente entre coord tableau et coord contenu ");
        }
        if(Tr.Type != PieceType.Black ){
            test = false;
            System.out.println(" Type de la pièce différent ");
        }

        assertTrue( test );
    }
    
}
