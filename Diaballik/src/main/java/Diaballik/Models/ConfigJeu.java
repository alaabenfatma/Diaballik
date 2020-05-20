package Diaballik.Models;

public class ConfigJeu {
    private Mode mode = Mode.humain;
    private Timer timer = Timer.illimite;
    private boolean p1First = true;
    private String name1 = "Joueur 1", name2 = "Joueur 2", name3 = "IA";
    private IALevel iaLevel = IALevel.facile;
    private boolean variante = false;
    public String avatarA = "";
    public String avatarB = "";
    public String damierA = "#007acc";
    public String damierB = "#79beed";

    public Mode getMode() {
        return mode;
    }

    public Timer getTimer() {
        return timer;
    }

    public boolean getP1First() {
        return p1First;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public String getName3() {
        return name3;
    }

    public IALevel getIALevel() {
        return iaLevel;
    }

    public boolean getVariante() {
        return variante;
    }

    public void setMode(Mode newMode) {
        this.mode = newMode;
    }

    public void setTimer(Timer newTimer) {
        this.timer = newTimer;
    }

    public void setP1First(boolean newP1First) {
        this.p1First = newP1First;
    }

    public void setName1(String newName) {
        this.name1 = newName;
    }

    public void setName2(String newName) {
        this.name2 = newName;
    }

    public void setName3(String newName) {
        this.name3 = newName;
    }

    public void setIALevel(IALevel newIALevel) {
        this.iaLevel = newIALevel;
    }

    public void setVariante(boolean newVariante) {
        this.variante = newVariante;
    }

    public enum IALevel {
        facile, moyen, difficile;
    }

    public enum Mode {
        humain, ordinateur;
    }

    public enum Timer {
        illimite, un, deux, trois;
    }
}
