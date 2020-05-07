package Diaballik;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import Diaballik.Controllers.TerrainUtils;
import Diaballik.Models.Jeu;
import Diaballik.Models.Piece;
import Diaballik.Models.PieceType;
import Diaballik.Models.Position;
import Diaballik.Models.Terrain;

public class JeuTest {
    Terrain tr = new Terrain();
    Boolean test;
    ArrayList<Position> list_test,list_pos;

    private ArrayList<Position> test_list(ArrayList<Position> list_test,ArrayList<Position> list_pos){
        //System.out.println();
        //for(int k=0;k<list_test.size();k++){System.out.println(list_test.get(k));}
        for(int i=0;i<list_pos.size();i++){
            for(int j=0;j<list_test.size();j++){
                if(list_pos.get(i).equals(list_test.get(j))){
                    list_test.remove(j);
                    break;
                }
            }
        }
        return list_test;
    }

    /**
     * @author Thomas
     * Test de la fonction winningMove sur pion blanc
     */
    @Test
    public void winningMove_test_2_possibilitées_1(){
        test = true;
        Piece a = tr.Create()[0][3];
        a.move(3,3);
        TerrainUtils.Swap(tr.getTerrain()[6][0], tr.getTerrain()[0][0]);
        TerrainUtils.Swap(tr.getTerrain()[6][6], tr.getTerrain()[0][6]);
        Jeu k = new Jeu(tr);
        list_test = k.winningMove(a);
        list_pos = new ArrayList<Position>();
        list_pos.add(new Position(6,0));
        list_pos.add(new Position(6,6));
        list_test = test_list(list_test,list_pos);
        if(list_test.size()!=0){test = false;}
        assertTrue( test );
    }
    /**
     * @author Thomas
     * Test de la fonction winningMove sur pion noir
     */
    @Test
    public void winningMove_test_2_possibilitées_2(){
        test = true;
        Piece a = tr.Create()[6][3];
        a.move(3,3);
        TerrainUtils.Swap(tr.getTerrain()[6][0], tr.getTerrain()[0][0]);
        TerrainUtils.Swap(tr.getTerrain()[6][6], tr.getTerrain()[0][6]);
        Jeu k = new Jeu(tr);
        list_test = k.winningMove(a);
        list_pos = new ArrayList<Position>();
        list_pos.add(new Position(0,0));
        list_pos.add(new Position(0,6));
        list_test = test_list(list_test,list_pos);
        if(list_test.size()!=0){test = false;}
        assertTrue( test );
    }
    /**
     * @author Thomas
     * Test de la fonction winningMove une possibilité sur pion blanc
     */
    @Test
    public void winningMove_test_1_possibilitées_1(){
        test = true;
        Piece a = tr.Create()[6][3];
        a.move(2,6);
        TerrainUtils.Swap(tr.getTerrain()[6][4], tr.getTerrain()[0][4]);
        Jeu k = new Jeu(tr);
        list_test = k.winningMove(a);
        list_pos = new ArrayList<Position>();
        list_pos.add(new Position(0,4));
        list_test = test_list(list_test,list_pos);
        if(list_test.size()!=0){test = false;}
        assertTrue( test );
    }
    /**
     * @author Thomas
     * Test de la fonction winningMove une possibilité sur pion blanc
     */
    @Test
    public void winningMove_test_1_possibilitées_2(){
        test = true;
        Piece a = tr.Create()[6][3];
        a.move(1,1);
        TerrainUtils.Swap(tr.getTerrain()[6][1], tr.getTerrain()[0][1]);
        Jeu k = new Jeu(tr);
        list_test = k.winningMove(a);
        list_pos = new ArrayList<Position>();
        list_pos.add(new Position(0,1));
        list_test = test_list(list_test,list_pos);
        if(list_test.size()!=0){test = false;}
        assertTrue( test );
    }
    /**
     * @author Thomas
     * Test de la fonction winningMove une possibilité sur pion noir
     */
    @Test
    public void winningMove_test_1_possibilitées_3(){
        test = true;
        Piece a = tr.Create()[0][3];
        a.move(4,6);
        TerrainUtils.Swap(tr.getTerrain()[6][4], tr.getTerrain()[0][4]);
        Jeu k = new Jeu(tr);
        list_test = k.winningMove(a);
        list_pos = new ArrayList<Position>();
        list_pos.add(new Position(6,4));
        list_test = test_list(list_test,list_pos);
        if(list_test.size()!=0){test = false;}
        assertTrue( test );
    }
    /**
     * @author Thomas
     * Test de la fonction winningMove une possibilité sur pion noir
     */
    @Test
    public void winningMove_test_1_possibilitées_4(){
        test = true;
        Piece a = tr.Create()[0][3];
        a.move(5,1);
        TerrainUtils.Swap(tr.getTerrain()[6][1], tr.getTerrain()[0][1]);
        Jeu k = new Jeu(tr);
        list_test = k.winningMove(a);
        list_pos = new ArrayList<Position>();
        list_pos.add(new Position(6,1));
        list_test = test_list(list_test,list_pos);
        if(list_test.size()!=0){test = false;}
        assertTrue( test );
    }
    /**
     * @author Thomas
     * Test de la fonction winningMove aucune possibilité sur pion noir
     */
    @Test
    public void winningMove_test_0_possibilitées_1(){
        test = true;
        Piece a = tr.Create()[0][3];
        Jeu k = new Jeu(tr);
        assertNull(k.winningMove(a));
    }
    /**
     * @author Thomas
     * Test de la fonction winningMove aucune possibilité sur pion noir
     */
    @Test
    public void winningMove_test_0_possibilitées_2(){
        test = true;
        Piece a = tr.Create()[0][3];
        a.move(3,3);
        Jeu k = new Jeu(tr);
        assertNull(k.winningMove(a));
    }
    /**
     * @author Thomas
     * Test de la fonction winningMove aucune possibilité sur pion blanc
     */
    @Test
    public void winningMove_test_0_possibilitées_3(){
        test = true;
        Piece a = tr.Create()[6][3];
        Jeu k = new Jeu(tr);
        assertNull(k.winningMove(a));
    }
    /**
     * @author Thomas
     * Test de la fonction winningMove aucune possibilité sur pion blanc
     */
    @Test
    public void winningMove_test_0_possibilitées_4(){
        test = true;
        Piece a = tr.Create()[6][3];
        a.move(3,3);
        Jeu k = new Jeu(tr);
        assertNull(k.winningMove(a));
    }
}