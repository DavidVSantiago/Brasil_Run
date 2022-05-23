package imagens;

import java.awt.Graphics2D;
import java.util.ArrayList;

import imagens.engine.Elemento;
import imagens.engine.Objeto;

public class Cenario implements Elemento{
    // ATRIBUTOS -----------------------------------------------------------
    private Gramado gramado1, gramado2;
    private Pista pista1,pista2;
    private ArrayList<Elemento> listaElementosGraficos;

    // CONSTRUTOR ----------------------------------------------------------
    public Cenario(){
        listaElementosGraficos = new ArrayList<Elemento>();

        gramado1 = new Gramado();
        gramado1.posX = 0;
        gramado1.posY = 27;
        gramado1.velX = -3;
        
        gramado2 = new Gramado();
        gramado2.posX = gramado1.larguraImagem;
        gramado2.posY = 27;
        gramado2.velX = -3;

        pista1 = new Pista();
        pista1.posX = 0;
        pista1.posY = 61;
        pista1.velX = -3;

        pista2 = new Pista();
        pista2.posX = pista1.larguraImagem;
        pista2.posY = 61;
        pista2.velX = -3;

        // adiciona os elementos na lista (na ordem de desenho)
        listaElementosGraficos.add(gramado1);
        listaElementosGraficos.add(gramado2);
        listaElementosGraficos.add(pista1);
        listaElementosGraficos.add(pista2);
    }

    // MÉTODOS GAMELOOP ----------------------------------------------------
	@Override
	public void handlerEvents() {
		for(Elemento e: listaElementosGraficos){
            e.handlerEvents();
        }
	}

    @Override
    public void update(double tempoDelta) {
        for(Elemento e: listaElementosGraficos){
            e.update(tempoDelta);
        }
        reporCenario();

    }

	@Override
	public void render(Graphics2D g2d) {
		for(Elemento e: listaElementosGraficos){
            e.render(g2d);
        }
	}

    // MÉTODOS -------------------------------------------------------------
    private void reporCenario(){
        // repor gramado
        if((gramado1.posX+gramado1.larguraImagem)<=0){ // repor gramado1
            gramado1.posX = gramado2.posX+gramado2.larguraImagem;
        }
        if((gramado2.posX+gramado2.larguraImagem)<=0){ // repor gramado2
            gramado2.posX = gramado1.posX+gramado1.larguraImagem;
        }
        // repor pista
        if((pista1.posX+pista1.larguraImagem)<=0){ // repor gramado1
            pista1.posX = pista2.posX+pista2.larguraImagem;
        }
        if((pista2.posX+pista2.larguraImagem)<=0){ // repor gramado2
            pista2.posX = pista1.posX+pista1.larguraImagem;
        }
    }
}
