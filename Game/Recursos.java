package Game;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Recursos {
    
    // ATRIBUTOS -----------------------------------------------------------
    // objetos da tela
    public final GraphicsDevice device;
    public final int LARGURA_TELA_JOGO = 355;
    public final int ALTURA_TELA_JOGO = 200;
    public final int LARGURA_TELA_DEVICE; // 1366
    public final int ALTURA_TELA_DEVICE; // 768
    // sprites do jogo
    public BufferedImage spritePerson;


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
    }

}
