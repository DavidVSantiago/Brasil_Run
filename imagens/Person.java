package imagens;

import Game.Game;
import java.awt.image.BufferedImage;

public class Person extends SpriteAnimado{
    // ATRIBUTOS -----------------------------------------------------------
    private static final int linhas=1;
    private static final int colunas=8;
    private static final int larguraQuadro=22;
    private static final int alturaQuadro=34;
    private static final int duracaoQuadro=80;
    private static BufferedImage sprite = Game.recursos.spritePerson;

    // CONSTRUTOR ----------------------------------------------------------
    public Person() {
        super(linhas,colunas,larguraQuadro,alturaQuadro,duracaoQuadro,sprite);
        posX = 10;
        posY= 120;
    }
    // MÉTODOS GAMELOOP ----------------------------------------------------

    // MÉTODOS -------------------------------------------------------------
}
