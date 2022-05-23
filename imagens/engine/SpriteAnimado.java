package imagens.engine;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class SpriteAnimado extends Objeto{
    // ATRIBUTOS -----------------------------------------------------------
    protected BufferedImage[][] listaQuadros;
    public int larguraQuadro, alturaQuadro;
    private int quadroAtual, totalQuadros, colunas, duracaoQuadro;
    private long tempoAcumulado;

    // CONSTRUTOR ----------------------------------------------------------
    public SpriteAnimado(int linhas, int colunas, int larguraQuadro, int alturaQuadro, int duracaoQuadro,BufferedImage sprite){
        tempoAcumulado = 0;
        this.duracaoQuadro = duracaoQuadro;
        totalQuadros = linhas*colunas;
        quadroAtual = 0;
        this.colunas = colunas;
        this.alturaQuadro = alturaQuadro;
        this.larguraQuadro = larguraQuadro;
        listaQuadros = new BufferedImage[linhas][colunas]; // inicializa o array de quadros
        for(int i=0;i<linhas;i++){
            for(int j=0;j<colunas;j++){
                int x = j*larguraQuadro;
                int y = i*alturaQuadro;
                listaQuadros[i][j] = sprite.getSubimage(x, y, larguraQuadro, alturaQuadro);
            }
        }
    }

    // MÉTODOS GAMELOOP ----------------------------------------------------
    @Override
    public void handlerEvents() {

    }
    @Override
    public void update(double tempoDelta) {
        super.update(tempoDelta); // atualiza a posição e caixa de colisão
        mudarQuadro(tempoDelta);

    }
    @Override
    public void render(Graphics2D g2d) {
        g2d.drawImage(obterQuadroAtual(), (int)posX, (int)posY, null);
    }
    // MÉTODOS -------------------------------------------------------------
    private void mudarQuadro(double tempoDelta){
        tempoAcumulado += tempoDelta*(1e-6);
        System.out.println("tempo acumulado: "+tempoAcumulado);
        if(tempoAcumulado >= duracaoQuadro){
            quadroAtual++;
            if(quadroAtual>=totalQuadros) quadroAtual = 0;
            tempoAcumulado = 0;
        }
    }

    private BufferedImage obterQuadroAtual(){
        return listaQuadros[quadroAtual/colunas][quadroAtual%colunas];
    }
}