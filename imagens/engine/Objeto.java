package imagens.engine;

import java.awt.Graphics2D;

public abstract class Objeto implements Elemento {
    // ATRIBUTOS -----------------------------------------------------------
    public double posX, posY, velX, velY;
    public double direita, esquerda, cima, baixo; // caixa de colisão 

    // CONSTRUTOR ----------------------------------------------------------
    public Objeto(){}

    // MÉTODOS GAMELOOP ----------------------------------------------------

	public void update(double tempoDelta) {
        // atualiza posição
        tempoDelta*=(6e-8); // amenização do tempo delta
       	posX +=(velX*tempoDelta);
        posY +=(velY*tempoDelta);
        // atualiza caixa de colisão
        direita+=(velX*tempoDelta);
        esquerda+=(velX*tempoDelta);
        cima+=(velY*tempoDelta);
        baixo+=(velY*tempoDelta);
	}

    // MÉTODOS -------------------------------------------------------------
    public boolean intersede(Objeto o){
        return esquerda>o.esquerda && direita<o.direita &&
            cima < o.cima && baixo > o.baixo;
    }
}
