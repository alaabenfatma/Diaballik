package Diaballik.Models;

import java.util.ArrayList;

import javax.swing.*;

import Diaballik.Diaballik;
import Diaballik.Controllers.TerrainUtils;

public class Piece extends JButton implements IPiece {
    public PieceType Type = PieceType.Empty;
    public Position Position;
    public boolean HasBall = false;
    public Terrain Parent;

    public Piece(PieceType type, boolean hasBall, int l, int c, Terrain parent) {
        this.Type = type;
        this.Position = new Position(l, c);
        this.HasBall = hasBall;
        this.Parent = parent;
    }

    @Override
    public void addBall() {
        if (this.HasBall) {
            throw new IllegalStateException("Le joueur ne peut avoir qu'une seule balle.");
        }
        this.HasBall = true;
    }

    @Override
    public void removeBall() {
        if (!this.HasBall) {
            throw new IllegalStateException("le joueur n'a pas déjà de balle.");
        }
        this.HasBall = false;
    }

    @Override
    public void move(int l, int c) {
        this.Position = new Position(l,c);
        TerrainUtils.Swap(this, Parent.getTerrain()[l][c]);
    }

    @Override
    public Piece Clone() {
        return new Piece(this.Type, this.HasBall, this.Position.l, this.Position.c, this.Parent);
    }

    public String toString() {
        return "[" + this.Type + "," + this.HasBall + "," + this.Position + "]";
    }

    //@Override
    public ArrayList<Position> PossiblePositions() {
        ArrayList<Position> result = new ArrayList<Position>();
    
        if(((this.Position.l-1)>=0) && !this.Parent.isOccupied(new Position(this.Position.l-1, this.Position.c))){
            result.add(new Position(this.Position.l-1, this.Position.c));
            System.out.println("here : "+this.Parent.isOccupied(new Position(this.Position.l-1, this.Position.c)));
            if(!this.Parent.isOccupied(new Position(this.Position.l-2, this.Position.c)) && ((this.Position.l-2)>=0)){
                result.add(new Position(this.Position.l-2, this.Position.c));
            }
        }
        if(((this.Position.l+1)<this.Parent.taille()) && !this.Parent.isOccupied(new Position(this.Position.l+1, this.Position.c))){
            result.add(new Position(this.Position.l+1, this.Position.c));
            if(!this.Parent.isOccupied(new Position(this.Position.l+2, this.Position.c)) && ((this.Position.l+2)<this.Parent.taille())){
                result.add(new Position(this.Position.l+2, this.Position.c));
            }
        }
        if(((this.Position.c+1)<this.Parent.taille()) && !this.Parent.isOccupied(new Position(this.Position.l, this.Position.c+1))){
            result.add(new Position(this.Position.l, this.Position.c+1));
            if(!this.Parent.isOccupied(new Position(this.Position.l, this.Position.c+2)) && ((this.Position.c+2)<this.Parent.taille())){
                result.add(new Position(this.Position.l, this.Position.c+2));
            }
        }
        if(((this.Position.c-1)>=0) && !this.Parent.isOccupied(new Position(this.Position.l, this.Position.c-1))){
            result.add(new Position(this.Position.l, this.Position.c-1));
            if(!this.Parent.isOccupied(new Position(this.Position.l, this.Position.c-2)) && ((this.Position.c-2)>=0)){
                result.add(new Position(this.Position.l, this.Position.c-2));
            }
        }
        if(((this.Position.c-1)>=0 &&(this.Position.l-1)>=0) && !this.Parent.isOccupied(new Position(this.Position.l-1, this.Position.c-1))){
            result.add(new Position(this.Position.l-1, this.Position.c-1));
        }
        if(((this.Position.c+1)<this.Parent.taille() &&(this.Position.l+1)<this.Parent.taille()) && !this.Parent.isOccupied(new Position(this.Position.l+1, this.Position.c+1))){
            result.add(new Position(this.Position.l+1, this.Position.c+1));
        }
        if(((this.Position.c-1)>=0 &&(this.Position.l+1)<this.Parent.taille()) && !this.Parent.isOccupied(new Position(this.Position.l+1, this.Position.c-1)) ){
            result.add(new Position(this.Position.l+1, this.Position.c-1));
        }
        if(((this.Position.c+1)<this.Parent.taille() &&(this.Position.l-1)>=0) && !this.Parent.isOccupied(new Position(this.Position.l-1, this.Position.c+1))){
            result.add(new Position(this.Position.l-1, this.Position.c+1));
        }
        return result;
    }


    //toujours en cours, ne pas toucher
    public ArrayList<Position> passesPossibles() {
        //TODO
        int i = this.Position.l;
        int j = this.Position.c;
        ArrayList<Position> result = new ArrayList<Position>();
        /*
        while((index<Parent.taille()) && (Parent.isOccupied(new Position(this.Position.l,this.Position.c + index +1)))){
            result.add(new Position(this.Position.l,this.Position.c + index +1));
            index++;
        }
        */
        for(int l=i+1; l<Parent.taille(); l++){
            if(Parent.isOccupied(new Position(l,j))){

            }
        }
        return null;
    }
}