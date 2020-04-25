package Diaballik.Models;

public interface ITerrain {
    // Creates the terrain.
    public Piece[][] Create();

    // Sets up the terrain.
    public void Setup();

    // Destroys the terrain before closing the game.
    public void Destroy();

    // Returns the terrain.
    public Piece[][] getTerrain();

    // Prints the terrain on the console.
    public void PrintTerrain();

    //Returns true if a pos is occupied by a player
    public boolean isOccupied(Position pos);

}