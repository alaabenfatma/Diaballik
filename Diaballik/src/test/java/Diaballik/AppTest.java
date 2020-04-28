package Diaballik;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

//import org.junit.Rule;
import org.junit.Test;
//import org.junit.rules.ExpectedException;

import Diaballik.Models.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    Terrain tr = new Terrain();
    /**
     * 
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
    // ##################### Piece.java #####################
    /**
     * Test du constructeur Piece
     * @author Thomas
     */
    @Test
    public void Piece_test(){
        boolean test = true;
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
    /**
     * Test de la fonction addBall
     * @author Thomas
     */

    @Test
    public void addBall_test_1(){
        tr.Create();
        tr.getTerrain()[0][2].addBall();
        assertTrue(tr.getTerrain()[0][2].HasBall);
    }
    @Test
    public void addBall_test_2(){
        tr.Create();
        assertTrue(tr.getTerrain()[0][3].HasBall);
    }
    //TODO : Faire addBall sur l'excepction
    
    /**
     * Test de la fonction removeBall
     * @author Thomas
     */
    
    @Test
    public void removeBall_test_1(){
        tr.Create();
        tr.getTerrain()[0][3].removeBall();
        assertFalse(tr.getTerrain()[0][3].HasBall);
    }
    //TODO : Faire removeBall sur l'excepction
    
    @Test
    public void removeBall_test_2(){
        tr.Create();
        tr.getTerrain()[6][5].addBall();
        tr.getTerrain()[6][5].removeBall();
        assertFalse(tr.getTerrain()[6][5].HasBall);
    }
    /**
     * Test de la fonction move_test
     * @author Thomas
     */
    @Test
    public void move_test_1(){
        boolean test = true;
        PieceType p1,p2;
        p1=tr.Create()[0][3].Type;
        p2=tr.getTerrain()[1][3].Type;
        tr.getTerrain()[0][3].move(1,3);
        if(tr.getTerrain()[0][3].Type != p2 && tr.getTerrain()[1][3].Type != p1){
            test = false;
            System.out.println(" Mauvais déplacement ");
        }
        assertTrue( test );
    }
    //TODO : Faire déplacement en dehors du terrain
    /**
     * Test de la fonction move_test
     * @author Thomas
     */

    @Test
    public void move_test_2(){
        boolean test = true;
        PieceType p1,p2;
        p1=tr.Create()[6][0].Type;
        p2=tr.getTerrain()[0][5].Type;
        tr.getTerrain()[6][0].move(0,5);
        if(tr.getTerrain()[6][0].Type != p2 && tr.getTerrain()[6][0].Type != p1){
            test = false;
            System.out.println(" Mauvais déplacement ");
        }
        assertTrue( test );
    }

/*
    @Test
    public void Clone_test(){
        Piece p1,p2;
        Terrain tr = new Terrain();
        tr.Create();
        p1 = tr.getTerrain()[0][3];
        p2 = tr.getTerrain()[0][3].Clone();
    }
*/
    @Test
    public void test_Diagonals(){
        boolean test = true;
        Piece p = tr.Create()[3][3];
        int i = p.Position.l;
        int j = p.Position.c;
        ArrayList<Position> list_test = p.getDiagonals();
        // test diag bd
        while( test && (i + 1< tr.taille()) && (j + 1 < tr.taille())){
            i++;
            j++;
            if(!new Position(i,j).equals(list_test.get(0))){
                test = false;
                System.out.println("Position diagonale bd ne correspond pas");
            }
            list_test.remove(0);
        }
        // test diag bg
        i = p.Position.l;
        j = p.Position.c;
        while( test && (i + 1< tr.taille()) && (j - 1 < tr.taille())){
            i++;
            j--;
            if(!new Position(i,j).equals(list_test.get(0))){
                test = false;
                System.out.println("Position diagonale bg ne correspond pas");
            }
            list_test.remove(0);
        }
        // test diag hd
        i = p.Position.l;
        j = p.Position.c;
        while( test && (i - 1 >= 0) && ( j + 1 < tr.taille())){
            i--;
            j++;
            if(!new Position(i,j).equals(list_test.get(0))){
                test = false;
                System.out.println("Position diagonale hd ne correspond pas");
            }
            list_test.remove(0);
        }
        // test diag hg
        i = p.Position.l;
        j = p.Position.c;
        while( test && (i - 1 >= 0) && (j - 1 >= 0)){
            i--;
            j--;
            if(!new Position(i,j).equals(list_test.get(0))){
                test = false;
                System.out.println("Position diagonale hg ne correspond pas");
            }
            list_test.remove(0);
        }
        if(list_test.size()!=0){
            System.out.println("Position restante(s) :" + list_test.get(0).toString());
            for(int k=1;k< list_test.size();k++){
                System.out.println("        "+list_test.get(k).toString());
            }
            test=false;
        }
        assertTrue( test );
    }

}
