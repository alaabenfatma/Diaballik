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
	   * On teste si on a 2 pieces a et b sont egales
	   * @author Hedi
	   */
	  @Test
	  public void testSwapTrue() {
		Piece a = tr.Create()[0][3];
		Piece b = tr.getTerrain()[1][3];
	    TerrainUtils.Swap(a, b);
	    assertTrue((tr.getTerrain()[0][3].Type == PieceType.Empty) && (tr.getTerrain()[1][3].Type == PieceType.Black));
	  }
	  
	  /**
	   * On teste si on a 2 pieces a et b differentes
	   * en creant un deuxieme terrain t
	   * @author Hedi
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
	   * On teste si on rentre dans le premier if
	   * @author Hedi
	   */
	  @Test
	  public void testExchangeBallTrue1() {
		  Piece a = tr.Create()[0][3];
		  Piece b = tr.getTerrain()[0][4];
	      TerrainUtils.ExchangeBall(a, b);
	      assertTrue((tr.getTerrain()[0][3].HasBall == false) && (tr.getTerrain()[0][4].HasBall == true));
	  }
	  
	  /**
	   * On teste si on rentre dans le 2eme if
	   * @author Hedi
	   */
	  @Test
	  public void testExchangeBallTrue2() {
		  Piece b = tr.Create()[0][3];
		  Piece a = tr.getTerrain()[0][4];
	      TerrainUtils.ExchangeBall(a, b);
	      assertTrue((tr.getTerrain()[0][3].HasBall == false) && (tr.getTerrain()[0][4].HasBall == true));
	  }
	  
	  /**
	   * aucun joueur n'a la balle
	   * @author Hedi
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
	   * On teste si les 2 joueurs sont de la mm equipe
	   * @author Hedi
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
	   */
	  @Test
	  public void testExchangeBallFalse3() throws Exception{
		  Piece a = tr.Create()[6][3];
		  Piece b = tr.Create()[0][4];
		  thrownException.expect(IllegalAccessError.class);
	      thrownException.expectMessage("Les deux joueurs sont de deux types différents");
	      TerrainUtils.ExchangeBall(a, b);
	  }
	  /**
	   * @author Thomas
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
	   */
	  @Test
	  public void passeWrapper_test_1(){
	    Piece a = tr.Create()[6][3];
	    Piece b = tr.getTerrain()[6][0];
	    TerrainUtils.passeWrapper(a,b);
	    assertTrue((tr.getTerrain()[6][3].HasBall == false) && (tr.getTerrain()[6][0].HasBall == true));
	  }
	  /**
	   * @author Thomas
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
	    thrownException.expect(IllegalAccessError.class);
	    thrownException.expectMessage("Erreur coup illegal: les pieces choisies ne devraient pas faire de passes");
	    TerrainUtils.passeWrapper(a,b);
	  }
	  /**
	   * @author Thomas
	   */
	  @Test
	  public void passeWrapper_test_7(){
	    Piece a = tr.Create()[0][3];
	    Piece b = tr.getTerrain()[6][2];
	    a.move(3,3);
	    b.move(2,2);
	    b = tr.getTerrain()[0][0];
	    thrownException.expect(IllegalAccessError.class);
	    thrownException.expectMessage("Erreur coup illegal: les pieces choisies ne devraient pas faire de passes");
	    TerrainUtils.passeWrapper(a,b);
	  }
	  /**
	   * @author Thomas
	   */
	  @Test
	  public void passeWrapper_test_8(){
	    Piece a = tr.Create()[0][3];
	    thrownException.expect(IllegalAccessError.class);
	    thrownException.expectMessage("Le joueur a tenté de se passer à lui meme la balle");
	    TerrainUtils.passeWrapper(a,a);
	  }
	  /**
	   * @author Thomas
	   */
	  @Test
	  public void passeWrapper_test_9(){
	    Piece a = tr.Create()[6][3];
	    thrownException.expect(IllegalAccessError.class);
	    thrownException.expectMessage("Le joueur a tenté de se passer à lui meme la balle");
	    TerrainUtils.passeWrapper(a,a);
	  }
}
