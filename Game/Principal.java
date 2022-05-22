package Game;
import javax.swing.JFrame;

public class Principal {
	
	public Principal() {
		JFrame janela = new JFrame("Jogo2D"); // cria a janela
		Game game = new Game(); // cria a tela do jogo
		//game.setPreferredSize(new Dimension(Game.recursos.LARGURA_TELA_JOGO,Game.recursos.ALTURA_TELA_JOGO));
		janela.getContentPane().add(game); // adiciona a tela do jogo na janela
		// configura a janela
		janela.setResizable(false); // impede redimensionamento
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // modo de encerramento
		janela.setUndecorated(true);
		janela.setVisible(true); // torna a janela vis�vel

		Game.recursos.device.setFullScreenWindow(janela);
	}
	public static void main(String[] args) {
		new Principal(); // dispara a aplicação
	}
}