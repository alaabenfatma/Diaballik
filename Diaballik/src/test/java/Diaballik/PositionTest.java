package Diaballik;

import static org.junit.Assert.*;
import org.junit.Test;

import Diaballik.Models.Piece;
import Diaballik.Models.Position;
import Diaballik.Models.Terrain;

public class PositionTest {
    Terrain tr = new Terrain();
    // ##################### Position.java #####################
    /**
     * @author Thomas
     * Test si les positions sont correctes
     */
    @Test
    public void Position_test(){
        boolean test = true;
        tr.Create();
        for(int j=0;test && j<tr.taille();j++){
            for(int i=0;test && i<tr.taille();i++){
                Piece p = tr.getTerrain()[j][i];
                if( j != p.Position.l || i != p.Position.c){
                    test = false;
                }
            }
        }
        assertTrue( test );

    }
    /**
     * @author Thomas
     * Test de la fonction qui teste deux positions 
     */
    @Test
    public void equals_test_Flase(){
        assertFalse(new Position(3,3).equals(new Position(2,3)));
    }

    /**
     * @author Hedi
     * Test de la fonction qui teste deux positions 
     */
    @Test
    public void test_equals_true() {
    	int l = 3;
    	int c = 3;
    	Position p = new Position(l, c);
    	Position position = new Position(l, c);
    	assertEquals((position.l == p.l && position.c == p.c), position.equals(p));
    	
    	
    }
    
}