package imagens;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import Game.Game;
import imagens.engine.Sprite;

public class Gramado extends Sprite {
    // ATRIBUTOS -----------------------------------------------------------
    private static final BufferedImage imagem = Game.recursos.gramado;
    public static final int larguraImagem = imagem.getWidth();
    public static final int alturaImagem = imagem.getHeight();

    // CONSTRUTOR ----------------------------------------------------------
    public Gramado() {
        super(larguraImagem,alturaImagem,imagem);
    }

    // MÉTODOS GAMELOOP ----------------------------------------------------
}
