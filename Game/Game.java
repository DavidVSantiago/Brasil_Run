package Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

import imagens.Objeto;
import imagens.Person;

public class Game extends JPanel{
	// ATRIBUTOS -----------------------------------------------------------
	// recursos do jogo
	public static final Recursos recursos = new Recursos();

	// desenho em tela cheia
	BufferedImage cacheScreen;
	Graphics2D g2d;
	float fator;
	int larguraTelaCheia;
	int alturaTelaCheia;
	int posXTelaCheia;
	int posYTelaCheia;

	// objetos gráficos
	ArrayList<Objeto> listaObjetosGraficos;
	Person person;

	// variáveis do deltatime
	private long tempoQuadroAtual, tempoQuadroAnterior, tempoDelta;
	private final double taxaFPS = 60, tempoMinimoCadaQuadro; // quadros por segundo
	
	// CONSTRUTOR -----------------------------------------------------------
	public Game() {

		// configuração da tela
		setBackground(Color.BLACK);
		cacheScreen = new BufferedImage(recursos.LARGURA_TELA_JOGO,recursos.ALTURA_TELA_JOGO, BufferedImage.TYPE_INT_ARGB);
		g2d = (Graphics2D)cacheScreen.getGraphics();
		calculaFatorTelaCheia();
		
		// consigura variáveis do deltatime
		tempoQuadroAnterior = 0;
		tempoMinimoCadaQuadro = (1e9)/taxaFPS; // duração mínima do quadro (em nanosegundos)
		
		// inicializa os objetos gráficos
		listaObjetosGraficos = new ArrayList<Objeto>();
		person = new Person();
		listaObjetosGraficos.add(person);

		// dispara o gameloop
		new Thread(new Runnable() { // instancia da Thread + classe interna an�nima
			@Override
			public void run() {
				gameloop(); // inicia o gameloop
			}
		}).start(); // dispara a Thread
	}
	// GAMELOOP -------------------------------
	public void gameloop() {
		while(true) { // repetição intermitente do gameloop
			tempoQuadroAtual = System.nanoTime();
			tempoDelta = tempoQuadroAtual - tempoQuadroAnterior;
			
			handlerEvents();
			update(tempoDelta);
			render();

			tempoQuadroAnterior = tempoQuadroAtual;
			
			try {
				int tempoEspera = (int) ((tempoMinimoCadaQuadro - tempoDelta)*(1e-6));
				Thread.sleep(tempoEspera);
			}catch (Exception e) {}
		}
	}
	public void handlerEvents() {
		for(Objeto o: listaObjetosGraficos){
			o.handlerEvents();
		}
	}
	public void update(long tempoDelta) {
		for(Objeto o: listaObjetosGraficos){
			o.update(tempoDelta);
		}
	}
	public void render() {
		drawCacheScreen();
		repaint();

	}
	
	// OUTROS METODOS -------------------------
	public void testeColisoes() {
		
	}


	// METODOS AUXILIARES DE PINTURA ---------------------
	private void drawCacheScreen(){
		// desenha o fundo
		g2d.setColor(Color.pink);
		g2d.fillRect(0, 0, Game.recursos.LARGURA_TELA_JOGO, Game.recursos.ALTURA_TELA_JOGO);
		// desenha os objetos gráficos
		for(Objeto o: listaObjetosGraficos){
			o.render(g2d);
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(cacheScreen, posXTelaCheia, posYTelaCheia, larguraTelaCheia, alturaTelaCheia,null);
	}

	private void calculaFatorTelaCheia(){
		float fatorLargura = (float)recursos.LARGURA_TELA_DEVICE/recursos.LARGURA_TELA_JOGO;
		float fatorAltura = (float)recursos.ALTURA_TELA_DEVICE/recursos.ALTURA_TELA_JOGO;
		fator = fatorAltura<fatorLargura?fatorAltura:fatorLargura;
		larguraTelaCheia = (int) (fator*recursos.LARGURA_TELA_JOGO);
		alturaTelaCheia = (int) (fator*recursos.ALTURA_TELA_JOGO);
		posXTelaCheia = (recursos.LARGURA_TELA_DEVICE/2) - (larguraTelaCheia/2);
		posYTelaCheia = (recursos.ALTURA_TELA_DEVICE/2) - (alturaTelaCheia/2);
	}
}