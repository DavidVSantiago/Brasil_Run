package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Canvas;
import java.util.ArrayList;
import javax.swing.JPanel;

import imagens.Cenario;
import imagens.Person;
import imagens.engine.Elemento;
import imagens.engine.Objeto;

public class Game extends Canvas {
	// ATRIBUTOS -----------------------------------------------------------
	// recursos do jogo
	public static final Recursos recursos = new Recursos();

	// desenho em tela cheia
	BufferedImage cacheScreen;
	BufferStrategy bufferStrategy;
	Graphics2D g2d;
	float fator;
	int larguraTelaCheia;
	int alturaTelaCheia;
	int posXTelaCheia;
	int posYTelaCheia;

	// objetos gráficos
	ArrayList<Elemento> listaElementosGraficos;
	Person person;
	Cenario cenario;

	// variáveis do deltatime
	private long tempoQuadroAtual, tempoQuadroAnterior;
	private double tempoDelta;
	private final int taxaFPS = 60;
	private final int tempoMinimoCadaQuadro; // quadros por segundo

	// CONSTRUTOR -----------------------------------------------------------
	public Game() {

		// configuração da tela
		//setBackground(Color.BLACK);
		cacheScreen = new BufferedImage(recursos.LARGURA_TELA_JOGO, recursos.ALTURA_TELA_JOGO,
				BufferedImage.TYPE_INT_ARGB);
		g2d = (Graphics2D) cacheScreen.getGraphics();
		createBufferStrategy(2);
		calculaFatorTelaCheia();

		// consigura variáveis do deltatime
		tempoQuadroAnterior = System.nanoTime();
		tempoMinimoCadaQuadro = 1000 / taxaFPS; // duração mínima do quadro (em nanosegundos)

		// inicializa os objetos gráficos
		listaElementosGraficos = new ArrayList<Elemento>();
		cenario = new Cenario();
		person = new Person();
		listaElementosGraficos.add(cenario);
		listaElementosGraficos.add(person);

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
		long tempoAcumulado = 0;
		while (true) { // repetição intermitente do gameloop
			tempoQuadroAtual = System.nanoTime();
			tempoDelta = (tempoQuadroAtual - tempoQuadroAnterior);
			tempoQuadroAnterior = tempoQuadroAtual;

			// System.out.println("acumulado:"+tempoAcumulado);
			handlerEvents();
			update(tempoDelta);
			render();

			try {
				int tempoEspera = (tempoMinimoCadaQuadro - (int) (tempoDelta * (1e-6)));
				Thread.sleep(Math.abs(tempoEspera));
			} catch (Exception e) {
			}
		}
	}

	public void handlerEvents() {
		for (Elemento e : listaElementosGraficos) {
			e.handlerEvents();
		}
	}

	public void update(double tempoDelta) {
		for (Elemento e : listaElementosGraficos) {
			e.update(tempoDelta);
		}
	}

	public void render() {
		drawCacheScreen();
		repaint();

	}

	// OUTROS METODOS -------------------------
	public void testeColisoes() {

	}

	private void calculaFatorTelaCheia() {
		float fatorLargura = (float) recursos.LARGURA_TELA_DEVICE / recursos.LARGURA_TELA_JOGO;
		float fatorAltura = (float) recursos.ALTURA_TELA_DEVICE / recursos.ALTURA_TELA_JOGO;
		fator = fatorAltura < fatorLargura ? fatorAltura : fatorLargura;
		larguraTelaCheia = (int) (fator * recursos.LARGURA_TELA_JOGO);
		alturaTelaCheia = (int) (fator * recursos.ALTURA_TELA_JOGO);
		posXTelaCheia = (recursos.LARGURA_TELA_DEVICE / 2) - (larguraTelaCheia / 2);
		posYTelaCheia = (recursos.ALTURA_TELA_DEVICE / 2) - (alturaTelaCheia / 2);
	}

	// METODOS AUXILIARES DE PINTURA ---------------------
	private void drawCacheScreen() {
		// desenha o fundo
		g2d.setColor(Color.pink);
		g2d.fillRect(0, 0, Game.recursos.LARGURA_TELA_JOGO, Game.recursos.ALTURA_TELA_JOGO);
		// desenha os objetos gráficos
		for (Elemento e : listaElementosGraficos) {
			e.render(g2d);
		}
	}

	@Override
	public void paint(Graphics g) {
		BufferStrategy bufferStrategy = getBufferStrategy();
		g = bufferStrategy.getDrawGraphics();
	
		//g2dLocal.drawImage(cacheScreen, posXTelaCheia, posYTelaCheia, larguraTelaCheia, alturaTelaCheia, null);
		g.dispose();
    	bufferStrategy.show();
	}
}