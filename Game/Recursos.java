package Game;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

public class Recursos {
    
    // ATRIBUTOS -----------------------------------------------------------
    // objetos da tela
    public final GraphicsDevice device;
    public final int LARGURA_TELA_JOGO = 427;
    public final int ALTURA_TELA_JOGO = 240;
    public final int LARGURA_TELA_DEVICE; // 1366
    public final int ALTURA_TELA_DEVICE; // 768
    // sprites do jogo
    public BufferedImage spritePerson;
    public BufferedImage pistaCompleta;
    public BufferedImage gramado;

    // construtor
    public Recursos(){
        device = GraphicsEnvironment
                .getLocalGraphicsEnvironment().getScreenDevices()[0];
        LARGURA_TELA_DEVICE = Toolkit.getDefaultToolkit().getScreenSize().width;
        ALTURA_TELA_DEVICE = Toolkit.getDefaultToolkit().getScreenSize().height;
        
        carregaSprites();
    }

    // MÉTODOS -------------------------------------------------------------
    private void carregaSprites(){
        try {
			spritePerson = ImageIO.read(getClass().getResource("../imgs/person.png"));
		}catch (Exception e) {
			System.out.println("Erro ao carregar a imagem!");
            e.printStackTrace();
		}
        carregaGramado();
        carregaPista();
    }
    private void carregaGramado(){
        BufferedImage grama1,grama2;
        try {
            // carrega as imagens que irão compor o gramado
            grama1 = ImageIO.read(getClass().getResource("../imgs/grama_1.png"));
            grama2 = ImageIO.read(getClass().getResource("../imgs/grama_2.png"));
            // inicializa o bitmap onde será desenhado o gramado
            int qtdBlocosGrama = 10;
            gramado = new BufferedImage(qtdBlocosGrama*50,30, BufferedImage.TYPE_INT_ARGB);
            // desenha o gramado
            Graphics2D g2d = (Graphics2D)gramado.getGraphics();
            for(int i=0;i<qtdBlocosGrama;i++){
                if(i%2==0) 
                    g2d.drawImage(grama1, i*50, 0, null);
                else
                    g2d.drawImage(grama2, i*50, 0, null);
            }
        }catch (Exception e) {
			System.out.println("Erro ao desenhar o gramado!");
            e.printStackTrace();
		}
    }
   
    private void carregaPista() {
        BufferedImage pista, faixa, faixaCompleta;
        try {
            // carrega as imagens que irão compor a pista
            pista = ImageIO.read(getClass().getResource("../imgs/pista.png"));
            faixa = ImageIO.read(getClass().getResource("../imgs/faixa.png"));
            // inicializa os bitmaps onde serão desenhadas as pistas
            faixaCompleta = new BufferedImage(100,142, BufferedImage.TYPE_INT_ARGB);
            pistaCompleta = new BufferedImage(500,142, BufferedImage.TYPE_INT_ARGB);
            // desenha a faixa completa
            Graphics2D g2d = (Graphics2D) faixaCompleta.getGraphics();
            g2d.drawImage(faixa, 0, 0, null);
            g2d.drawImage(pista, 0, 2, null);
            g2d.drawImage(faixa, 0, 35, null);
            g2d.drawImage(pista, 0, 37, null);
            g2d.drawImage(faixa, 0, 70, null);
            g2d.drawImage(pista, 0, 72, null);
            g2d.drawImage(faixa, 0, 105, null);
            g2d.drawImage(pista, 0, 107, null);
            g2d.drawImage(faixa, 0, 140, null);
            g2d.drawImage(pista, 0, 142, null);
            // desenha a pista
            g2d = (Graphics2D) pistaCompleta.getGraphics();
            g2d.drawImage(faixaCompleta, 0, 0, null);
            g2d.drawImage(faixaCompleta, 100, 0, null);
            g2d.drawImage(faixaCompleta, 200, 0, null);
            g2d.drawImage(faixaCompleta, 300, 0, null);
            g2d.drawImage(faixaCompleta, 400, 0, null);
            
        } catch (Exception e) {
            System.out.println("Erro ao desenhar o gramado!");
            e.printStackTrace();
        }

    }

}
