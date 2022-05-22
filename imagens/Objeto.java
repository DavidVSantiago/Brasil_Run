package imagens;

import java.awt.Graphics2D;

public abstract class Objeto {
    // ATRIBUTOS -----------------------------------------------------------
    public int posX, posY, velX, velY;
    public int direita, esquerda, cima, baixo; // caixa de colisão 

    // CONSTRUTOR ----------------------------------------------------------
    public Objeto(){}

    // MÉTODOS GAMELOOP ----------------------------------------------------
    public abstract void handlerEvents();

	public void update(long tempoDelta) {
        // atualiza posição
		posX +=(velX*tempoDelta);
        posY +=(velY*tempoDelta);
        // atualiza caixa de colisão
        direita+=(velX*tempoDelta);
        esquerda+=(velX*tempoDelta);
        cima+=(velY*tempoDelta);
        baixo+=(velY*tempoDelta);
	}

	public abstract void render(Graphics2D g2d);

    // MÉTODOS -------------------------------------------------------------
    public boolean intersede(Objeto o){
        return esquerda>o.esquerda && direita<o.direita &&
            cima < o.cima && baixo > o.baixo;
    }
}
