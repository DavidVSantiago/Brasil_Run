package imagens.engine;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Sprite extends Objeto{
    // ATRIBUTOS -----------------------------------------------------------
    protected BufferedImage imagem;
    public int larguraQuadro, alturaQuadro;

    // CONSTRUTOR ----------------------------------------------------------
    public Sprite(int larguraQuadro, int alturaQuadro,BufferedImage imagem){
        this.alturaQuadro = alturaQuadro;
        this.larguraQuadro = larguraQuadro;
        this.imagem = imagem;
    }

    // MÉTODOS GAMELOOP ----------------------------------------------------
    @Override
	public void handlerEvents() {
		
	}

    @Override
    public void update(double tempoDelta) {
        super.update(tempoDelta); // atualiza a posição e caixa de colisão
    }
    @Override
    public void render(Graphics2D g2d) {
        g2d.drawImage(imagem, (int)posX, (int)posY, null);
    }
    // MÉTODOS -------------------------------------------------------------
    
}