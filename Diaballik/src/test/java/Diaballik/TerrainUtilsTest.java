package Diaballik;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Diaballik.Controllers.TerrainUtils;
import Diaballik.Models.Piece;
import Diaballik.Models.PieceType;
import Diaballik.Models.Terrain;


//##################### TerrainUtils.java #####################

public class TerrainUtilsTest {
	Terrain tr = new Terrain();
	 
	  @Rule
	  public ExpectedException thrownException = ExpectedException.none();
	  
	  /**
	   * @author Hedi
	   * On teste si on a 2 pieces a et b sont egales
	   */
	  @Test
	  public void testSwapTrue() {
		Piece a = tr.Create()[0][3];
		Piece b = tr.getTerrain()[1][3];
	    TerrainUtils.Swap(a, b);
	    assertTrue((tr.getTerrain()[0][3].Type == PieceType.Empty) && (tr.getTerrain()[1][3].Type == PieceType.Black));
	  }
	  
	  /**
	   * @author Hedi
	   * On teste si on a 2 pieces a et b differentes
	   * en creant un deuxieme terrain t
	   */
	  @Test
	  public void testSwapFalse() throws Exception{ 
		  Terrain t = new Terrain();
		  Piece a = tr.Create()[0][3];
		  Piece b = t.Create()[0][4];
		  thrownException.expect(IllegalStateException.class);
	      thrownException.expectMessage("Not on the same terrain.");
	      TerrainUtils.Swap(a, b);
	  }
	  
	  /**
	   * @author Hedi
	   * On teste si on rentre dans le premier if
	   */
	  @Test
	  public void testExchangeBallTrue1() {
		  Piece a = tr.Create()[0][3];
		  Piece b = tr.getTerrain()[0][4];
	      TerrainUtils.ExchangeBall(a, b);
	      assertTrue((tr.getTerrain()[0][3].HasBall == false) && (tr.getTerrain()[0][4].HasBall == true));
	  }
	  
	  /**
	   * @author Hedi
	   * On teste si on rentre dans le 2eme if
	   */
	  @Test
	  public void testExchangeBallTrue2() {
		  Piece b = tr.Create()[0][3];
		  Piece a = tr.getTerrain()[0][4];
	      TerrainUtils.ExchangeBall(a, b);
	      assertTrue((tr.getTerrain()[0][3].HasBall == false) && (tr.getTerrain()[0][4].HasBall == true));
	  }
	  
	  /**
	   * @author Hedi
	   * aucun joueur n'a la balle
	   */
	  @Test
	  public void testExchangeBallFalse1() {
		  Piece a = tr.Create()[0][5];
		  Piece b = tr.getTerrain()[0][4];
		  thrownException.expect(IllegalAccessError.class);
	      thrownException.expectMessage("Aucun des deux joueurs n'a le ballon");
		  TerrainUtils.ExchangeBall(a, b);
	  }
		  
	  /**
	   * @author Hedi
	   * On teste si les 2 joueurs sont de la mm equipe
	   */
	  @Test
	  public void testExchangeBallFalse2() throws Exception{
		  Piece a = tr.Create()[0][3];
		  Piece b = tr.getTerrain()[6][4];
		  thrownException.expect(IllegalAccessError.class);
	      thrownException.expectMessage("Les deux joueurs sont de deux types différents");
	      TerrainUtils.ExchangeBall(a, b);
	  }
	  /**
	   * @author Hedi
	   * On teste si les 2 joueurs sont de la mm equipe
	   */
	  @Test
	  public void testExchangeBallFalse3() throws Exception{
		  Piece a = tr.Create()[6][3];
		  Piece b = tr.getTerrain()[0][3];
		  thrownException.expect(IllegalAccessError.class);
	      thrownException.expectMessage("Les deux joueurs sont de deux types différents");
	      TerrainUtils.ExchangeBall(a, b);
	  }
	  /**
	   * @author Thomas
	   * Test joueur blanc qui passe la balle au joueur blanc
	   */
	  @Test
	  public void passeWrapper_test(){
	    Piece a = tr.Create()[0][3];
	    Piece b = tr.getTerrain()[0][4];
	    TerrainUtils.passeWrapper(a,b);
	    assertTrue((tr.getTerrain()[0][3].HasBall == false) && (tr.getTerrain()[0][4].HasBall == true));
	  }
	  /**
	   * @author Thomas
	   * Test joueur noir qui passe la balle au joueur noir
	   */
	  @Test
	  public void passeWrapper_test_2(){
	    Piece a = tr.Create()[0][3];
	    Piece b = tr.getTerrain()[0][0];
	    TerrainUtils.passeWrapper(a,b);
	    assertTrue((tr.getTerrain()[0][3].HasBall == false) && (tr.getTerrain()[0][0].HasBall == true));
	  }
	  /**
	   * @author Thomas
	   * Test joueur noir qui passe la balle au joueur noir
	   */
	  @Test
	  public void passeWrapper_test_3(){
	    Piece a = tr.Create()[6][3];
	    Piece b = tr.getTerrain()[6][6];
	    TerrainUtils.passeWrapper(a,b);
	    assertTrue((tr.getTerrain()[6][3].HasBall == false) && (tr.getTerrain()[6][6].HasBall == true));
	  }
	  /**
	   * @author Thomas
	   * Test joueur noir qui passe la balle au joueur noir
	   */
	  @Test
	  public void passeWrapper_test_4(){
	    Piece a = tr.Create()[6][3];
	    Piece b = tr.getTerrain()[6][0];
	    TerrainUtils.passeWrapper(a,b);
	    assertTrue((tr.getTerrain()[6][3].HasBall == false) && (tr.getTerrain()[6][0].HasBall == true));
	  }
	  /**
	   * @author Thomas
	   * Test joueur noir qui passe la balle au joueur noir
	   */
	  @Test
	  public void passeWrapper_test_5(){
	    Piece a = tr.Create()[6][3];
	    Piece b = tr.getTerrain()[0][2];
	    a.move(3,3);
	    b.move(4,2);
	    b = tr.getTerrain()[6][6];
	    TerrainUtils.passeWrapper(a,b);
	    assertTrue((tr.getTerrain()[3][3].HasBall == false) && (tr.getTerrain()[6][6].HasBall == true));
	  }
	  /**
	   * @author Thomas
	   */
	  @Test
	  public void passeWrapper_test_6(){
	    Piece a = tr.Create()[6][3];
	    Piece b = tr.getTerrain()[0][2];
	    a.move(3,3);
	    b.move(4,2);
	    b = tr.getTerrain()[6][0];
		TerrainUtils.passeWrapper(a,b);
		assertTrue(a.HasBall == true && b.HasBall == false);
	  }
	  /**
	   * @author Thomas
	   * Test joueur blanc qui passe la balle au joueur noir
	   */
	  @Test
	  public void passeWrapper_test_7(){
	    Piece a = tr.Create()[0][3];
	    Piece b = tr.getTerrain()[6][2];
	    a.move(3,3);
	    b.move(2,2);
	    b = tr.getTerrain()[0][0];
		TerrainUtils.passeWrapper(a,b);
		assertTrue(a.HasBall == true && b.HasBall == false);
	  }
	  /**
	   * @author Thomas
	   * Test joueur noir qui se passe la balle à lui même
	   */
	  @Test
	  public void passeWrapper_test_8(){
		Piece a = tr.Create()[0][3];
		Piece b = tr.getTerrain()[0][3];
		TerrainUtils.passeWrapper(a,b);
		assertTrue(a.HasBall == true);
	  }
	  /**
	   * @author Thomas
	   * Test joueur blanc qui se passe la balle à lui même
	   */
	  @Test
	  public void passeWrapper_test_9(){
		Piece a = tr.Create()[6][3];
		Piece b = tr.getTerrain()[6][3];
		TerrainUtils.passeWrapper(a,b);
		assertTrue(a.HasBall == true);
	  }
}
