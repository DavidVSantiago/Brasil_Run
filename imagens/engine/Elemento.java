package imagens.engine;

import java.awt.Graphics2D;

public interface Elemento {
 
     // MÉTODOS GAMELOOP ----------------------------------------------------
     public abstract void handlerEvents();
 
     public abstract void update(double tempoDelta);
 
     public abstract void render(Graphics2D g2d);
}
