package Diaballik;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import Diaballik.Controllers.TerrainUtils;
import Diaballik.Models.Piece;
import Diaballik.Models.PieceType;
import Diaballik.Models.Position;
import Diaballik.Models.Terrain;


//##################### Piece.java #####################
public class PieceTest {
    Terrain tr = new Terrain();
    @Rule
    public ExpectedException thrownException = ExpectedException.none();

    
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
    @Test
    public void testaddBallFalse() {
        Piece a = tr.Create()[0][3];
        thrownException.expect(IllegalStateException.class);
        thrownException.expectMessage("Le joueur ne peut avoir qu'une seule balle.");
        a.addBall();
    }
    
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
    /**
     * @author Thomas
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
    @Test
    public void PossiblePositions_test_0(){
        boolean test = true;
        ArrayList<Position> list_pos;
        ArrayList<Position> list_test = new ArrayList<Position>();
        Piece p1 = tr.Create()[0][3];
        Piece p2 = tr.getTerrain()[5][5];
        TerrainUtils.Swap(p1, p2);
        list_pos = p1.PossiblePositions(2);
        list_test.add(new Position(5,3));
        list_test.add(new Position(4,4));
        list_test.add(new Position(5,4));
        list_test.add(new Position(3,5));
        list_test.add(new Position(4,5));
        list_test.add(new Position(4,6));
        list_test.add(new Position(5,6));
        System.out.println( list_pos.size());
        if(list_test.size() != list_pos.size()){
            System.out.println(" Il manque des positions ! ");
            test = false;
        }
        for(int i = 0; i<list_test.size() && test ;i++){
            for(int j=0;j<list_pos.size() && test ;j++){
                if(list_test.get(i).equals(list_pos.get(j))){
                    list_pos.remove(j);
                    break;
                }
            }
        }
        if(list_pos.size() != 0){
            System.out.println(" Il y a des positions en trop ! ");
            test =false;
        }
        assertTrue( test );
    }
    @Test
    /**
     * @author Thomas
     */
    public void PossiblePositions_test_1(){
        boolean test = true;
        ArrayList<Position> list_pos;
        ArrayList<Position> list_test = new ArrayList<Position>();
        Piece p1 = tr.Create()[0][3];
        Piece p2 = tr.getTerrain()[3][3];
        TerrainUtils.Swap(p1, p2);
        list_pos = p1.PossiblePositions(2);
        list_test.add(new Position(2,2));
        list_test.add(new Position(2,3));
        list_test.add(new Position(2,4));
        list_test.add(new Position(3,2));
        list_test.add(new Position(3,4));
        list_test.add(new Position(4,2));
        list_test.add(new Position(4,3));
        list_test.add(new Position(4,4));
        list_test.add(new Position(3,1));
        list_test.add(new Position(3,5));
        list_test.add(new Position(1,3));
        list_test.add(new Position(5,3));
        if(list_test.size() != list_pos.size()){
            if(list_test.size() < list_pos.size()){
            System.out.println("positions possible en trop !");
            }
            else{
                System.out.println("positions possible manquante(s) !");
            }
            test = false;
        }
        for(int i = 0; i<list_test.size() && test ;i++){
            for(int j=0;j<list_pos.size() && test ;j++){
                if(list_test.get(i).equals(list_pos.get(j))){
                    list_pos.remove(j);
                    break;
                }
            }
        }
        if(list_pos.size() != 0){
            System.out.println(" Il y a des positions différentes ! ");
            test =false;
        }
        assertTrue( test );
    }
    /**
     * @author Thomas
     */
    private void affiche_bug_passesPossibles(ArrayList<Position> list_test ,ArrayList<Position> list_pos){
            tr.PrintTerrain();
            if(list_pos.size() < list_test.size()){
                System.out.println("Passe possible en trop !");
                for(int i = 0; i<list_pos.size();i++){
                    for(int j = 0; j<list_test.size();j++){
                        if(list_pos.get(i).equals(list_test.get(j))){
                            list_test.remove(j);
                            break;
                        }
                    }
                }
                for(int i=0;i < list_test.size();i++){
                    System.out.println(list_test.get(i).toString());
                }
            }
            else{
                System.out.println("Passe possible manquante(s) !");
                for(int i = 0; i<list_test.size();i++){
                    for(int j = 0; j<list_pos.size();j++){
                        if(list_test.get(i).equals(list_pos.get(j))){
                            list_pos.remove(j);
                            break;
                        }
                    }
                }
                for(int i=0;i < list_pos.size();i++){
                    System.out.println(list_pos.get(i).toString());
                }
            }
            System.out.println("************************************");
    }
    /**
     * @author Thomas
     */
    @Test
    public void passesPossibles_test_0(){
        ArrayList<Position> list_pos = new ArrayList<Position>();
        ArrayList<Position> list_test;
        boolean test = true;
        Piece p1 = tr.Create()[0][3];
        p1.move(2,3);
        list_test = p1.passesPossibles();
        list_pos.add(new Position(0,1));
        list_pos.add(new Position(0,5));
        if(list_pos.size() != list_test.size()){
            affiche_bug_passesPossibles(list_test,list_pos);
            test = false;
        }
        for(int i = 0;  test && i<list_pos.size();i++){
            for(int j = 0; test && j<list_test.size();j++){
                if(list_pos.get(i).equals(list_test.get(j))){
                    list_test.remove(j);
                    break;
                }
            }
        }
        if(test && list_test.size() != 0){
            System.out.println(" Il y a des positions différentes ! ");
            tr.PrintTerrain();
            for(int i=0;i<list_test.size();i++){
                System.out.print(list_test.get(i).toString()+" ");
            }
            test =false;
        }
        assertTrue( test );
    }
    /**
     * @author Thomas
     */
    @Test
    public void passesPossibles_test_1(){
        ArrayList<Position> list_pos = new ArrayList<Position>();
        ArrayList<Position> list_test;
        boolean test = true;
        Piece p1 = tr.Create()[0][3];
        p1.move(2,3);
        Piece p = tr.getTerrain()[0][1];
        p.move(2,1);
        p = tr.getTerrain()[0][2];
        p.move(0,3);
        p = tr.getTerrain()[0][4];
        p.move(2,4);
        p = tr.getTerrain()[0][0];
        p.move(5,0);
        list_test = p1.passesPossibles();
        list_pos.add(new Position(5,0));
        list_pos.add(new Position(2,1));
        list_pos.add(new Position(0,3));
        list_pos.add(new Position(2,4));
        list_pos.add(new Position(0,5));
        if(list_pos.size() != list_test.size()){
            affiche_bug_passesPossibles(list_test,list_pos);
            test = false;
        }
        for(int i = 0;  test && i<list_pos.size();i++){
            for(int j = 0; test && j<list_test.size();j++){
                if(list_pos.get(i).equals(list_test.get(j))){
                    list_test.remove(j);
                    break;
                }
            }
        }
        if( test && list_test.size() != 0){
            System.out.println(" Il y a des positions différentes ! ");
            tr.PrintTerrain();
            for(int i=0;i<list_test.size();i++){
                System.out.print(list_test.get(i).toString()+" ");
            }
            test =false;
        }
        assertTrue( test );
    }
    /**
     * @author Thomas
     */
    @Test
    public void passesPossibles_test_2(){
        ArrayList<Position> list_pos = new ArrayList<Position>();
        ArrayList<Position> list_test;
        boolean test = true;
        Piece p1 = tr.Create()[0][3];
        p1.move(2,3);
        Piece p = tr.getTerrain()[0][1];
        p.move(2,1);
        p = tr.getTerrain()[0][2];
        p.move(0,3);
        p = tr.getTerrain()[0][4];
        p.move(2,4);
        p = tr.getTerrain()[0][0];
        p.move(5,0);

        p = tr.getTerrain()[6][1];
        p.move(4,1);
        p = tr.getTerrain()[6][4];
        p.move(1,4);
        list_test = p1.passesPossibles();
        list_pos.add(new Position(2,1));
        list_pos.add(new Position(0,3));
        list_pos.add(new Position(2,4));
        if(list_pos.size() != list_test.size()){
            affiche_bug_passesPossibles(list_test,list_pos);
            test = false;
        }
        for(int i = 0;  test && i<list_pos.size();i++){
            for(int j = 0; test && j<list_test.size();j++){
                if(list_pos.get(i).equals(list_test.get(j))){
                    list_test.remove(j);
                    break;
                }
            }
        }
        if( test && list_test.size() != 0){
            System.out.println(" Il y a des positions différentes ! ");
            tr.PrintTerrain();
            for(int i=0;i<list_test.size();i++){
                System.out.print(list_test.get(i).toString()+" ");
            }
            test =false;
        }
        assertTrue( test );
    }
    /**
     * @author Thomas
     */
    @Test
    public void passesPossibles_test_3(){
        ArrayList<Position> list_pos = new ArrayList<Position>();
        ArrayList<Position> list_test;
        boolean test = true;
        Piece p1 = tr.Create()[6][3];
        p1.move(3,3);
        list_test = p1.passesPossibles();
        list_pos.add(new Position(6,0));
        list_pos.add(new Position(6,6));
        if(list_pos.size() != list_test.size()){
            affiche_bug_passesPossibles(list_test,list_pos);
            test = false;
        }
        for(int i = 0;  test && i<list_pos.size();i++){
            for(int j = 0; test && j<list_test.size();j++){
                if(list_pos.get(i).equals(list_test.get(j))){
                    list_test.remove(j);
                    break;
                }
            }
        }
        if(test && list_test.size() != 0){
            System.out.println(" Il y a des positions différentes ! ");
            tr.PrintTerrain();
            for(int i=0;i<list_test.size();i++){
                System.out.print(list_test.get(i).toString()+" ");
            }
            test =false;
        }
        assertTrue( test );
    }
    /**
     * @author Thomas
     */
    @Test
    public void passesPossibles_test_4(){
        ArrayList<Position> list_pos = new ArrayList<Position>();
        ArrayList<Position> list_test;
        boolean test = true;
        Piece p1 = tr.Create()[6][3];
        p1.move(3,3);
        Piece p = tr.getTerrain()[6][0];
        p.move(3,0);
        p = tr.getTerrain()[6][1];
        p.move(1,3);
        p = tr.getTerrain()[6][2];
        p.move(6,3);
        p = tr.getTerrain()[6][6];
        p.move(1,5);

        list_test = p1.passesPossibles();
        list_pos.add(new Position(6,3));
        list_pos.add(new Position(3,0));
        list_pos.add(new Position(1,3));
        list_pos.add(new Position(1,5));
        
        if(list_pos.size() != list_test.size()){
            affiche_bug_passesPossibles(list_test,list_pos);
            test = false;
        }
        for(int i = 0;  test && i<list_pos.size();i++){
            for(int j = 0; test && j<list_test.size();j++){
                if(list_pos.get(i).equals(list_test.get(j))){
                    list_test.remove(j);
                    break;
                }
            }
        }
        if(test && list_test.size() != 0){
            System.out.println(" Il y a des positions différentes ! ");
            tr.PrintTerrain();
            for(int i=0;i<list_test.size();i++){
                System.out.print(list_test.get(i).toString()+" ");
            }
            test =false;
        }
        assertTrue( test );
    }
    /**
     * @author Thomas
     */
    @Test
    public void passesPossibles_test_5(){
        ArrayList<Position> list_pos = new ArrayList<Position>();
        ArrayList<Position> list_test;
        boolean test = true;
        Piece p1 = tr.Create()[6][3];
        p1.move(3,3);
        Piece p = tr.getTerrain()[6][0];
        p.move(3,0);
        p = tr.getTerrain()[6][1];
        p.move(1,3);
        p = tr.getTerrain()[6][2];
        p.move(6,3);
        p = tr.getTerrain()[6][6];
        p.move(1,5);
        p = tr.getTerrain()[0][1];
        p.move(3,1);
        p = tr.getTerrain()[0][4];
        p.move(2,4);
        list_test = p1.passesPossibles();
        list_pos.add(new Position(6,3));
        list_pos.add(new Position(1,3));

        if(list_pos.size() != list_test.size()){
            affiche_bug_passesPossibles(list_test,list_pos);
            test = false;
        }
        for(int i = 0;  test && i<list_pos.size();i++){
            for(int j = 0; test && j<list_test.size();j++){
                if(list_pos.get(i).equals(list_test.get(j))){
                    list_test.remove(j);
                    break;
                }
            }
        }
        if(test && list_test.size() != 0){
            System.out.println(" Il y a des positions différentes ! ");
            tr.PrintTerrain();
            for(int i=0;i<list_test.size();i++){
                System.out.print(list_test.get(i).toString()+" ");
            }
            test =false;
        }
        assertTrue( test );
    }
 // ##################### Position.java #####################
    /**
     * @author Thomas
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
     */
    @Test
    public void equals_test(){
        assertFalse(new Position(3,3).equals(new Position(2,3)));
    }

    /**
     * @author Hedi
     */
    @Test
    public void testequals() {
    	int l = 3;
    	int c = 3;
    	Position p = new Position(l, c);
    	Position position = new Position(l, c);
    	assertEquals((position.l == p.l && position.c == p.c), position.equals(p));
    	
    	
    }
    
}
