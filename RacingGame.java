import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import java.util.Random;
import java.util.concurrent.TimeUnit;

// Classe herdando do JFrame e (depois) implementando as interfaces KeyListener (para eventos do teclado) e o actionListener para eventos 
class RacingGame extends JFrame implements KeyListener, ActionListener {
    
    // Criando posição da linha
    private int faixaEstradaPosicao = 0;
    private int gramaPosicao = 0;
    private int limiteEstradaPosicao = 0;

    // CARROS
    private int xPosicaoCarroPlayer = 400;
    private int yPosicaoCarroPlayer = 700;
    // Criando imagem do carro
    private ImageIcon carroPlayer;

    // MOVIMENTAÇÕES
    // Posições X, Y possíveis dos carros
    private int carroPosicaoX [] = { 270, 400, 530, 660 };
    private int carroPosicaoY [] = { -240, -480, -720, -960, -1200 };

    // Definindo um random para utilizar nas posições dos carros inimigos
    Random random = new Random();

    // Incrementos 
    private int carroPosicaoX1 = 0, carroPosicaoX2 = 2, carroPosicaoX3 = 3;
    private int carroPosicaoY1 = random.nextInt(4), carroPosicaoY2 = random.nextInt(4), carroPosicaoY3 = random.nextInt(4);

    int y1pos = carroPosicaoY[carroPosicaoY1], y2pos = carroPosicaoY[carroPosicaoY2], y3pos = carroPosicaoY[carroPosicaoY3]; // y position of the car

    // Para as imagens dos carros inimigos
    private ImageIcon carroInimigo1, carroInimigo2, carroInimigo3;

    
    private int score = 0, delay = 100, velocidade = 90;
    private boolean fimDeJogo = false, paint = false;

    // Constructor: iníciando o jogo (janela)
    public RacingGame(String title){
        // Chamando o constructor do JFrame
        super(title);
        setBounds(300,10,1020,700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setFocusable(true);
        setResizable(false);
        addKeyListener(this);
    }
    
    public void paint(Graphics artesGraficas) {
        
        Color gramaCor1 = new Color (0x0A6906);
        Color gramaCor2 = new Color (0X008000);
        
        artesGraficas.setColor(gramaCor1);
        artesGraficas.fillRect(0,0,1020,700);

        if(gramaPosicao == 0) {
            for (int i = 0; i<700; i += 130){
                artesGraficas.setColor(gramaCor2);
                artesGraficas.fillRect(0, i, 1020,70);
                artesGraficas.fillRect(0, i, 1020,70);
                artesGraficas.fillRect(0, i, 1020,70);
            }
        }
        
        if(gramaPosicao == 0) {
            for (int i = 0; i<700; i += 130){
                artesGraficas.setColor(gramaCor2);
                artesGraficas.fillRect(0, i, 1020,70);
            }
            gramaPosicao = 1;
        } else if(gramaPosicao == 1) {
            for (int i = 50; i<700; i += 130){
                artesGraficas.setColor(gramaCor2);
                artesGraficas.fillRect(0, i, 1020,70);
            }
            gramaPosicao = 0;
        }
        
        Color estrada = new Color (0X738595);
        
        artesGraficas.setColor(estrada);
		artesGraficas.fillRect(255, 0, 510, 700);
        
        Color limiteEstradaCor1 = new Color (0X2138AB);
        Color limiteEstradaCor2 = new Color (0XC7C9D5);

        artesGraficas.setColor(limiteEstradaCor1);
        artesGraficas.fillRect(215, 0,40,700);
        artesGraficas.fillRect(765, 0, 40, 700);

        if(limiteEstradaPosicao == 0) {
            for (int i = 0; i<700; i += 130){
                artesGraficas.setColor(limiteEstradaCor2);
                artesGraficas.fillRect(215, i, 40,50);
                artesGraficas.fillRect(765, i, 40,50);
            }
            limiteEstradaPosicao = 1;
        } else if(limiteEstradaPosicao == 1) {
            for (int i = 50; i<700; i += 130){
                artesGraficas.setColor(limiteEstradaCor2);
                artesGraficas.fillRect(215, i, 40,50);
                artesGraficas.fillRect(765, i, 40,50);
            }
            limiteEstradaPosicao = 0;
        }
        
        // Criando as faixas da estrada:
        Color listaEstradaCor = new Color (0Xfffff2);
        if (faixaEstradaPosicao == 0) {
            for(int i = 0; i <= 700; i += 130){
                artesGraficas.setColor(listaEstradaCor);
                artesGraficas.fillRect(375, i, 10,70);
                artesGraficas.fillRect(505, i, 10,70);
                artesGraficas.fillRect(635, i, 10,70);
            }
            faixaEstradaPosicao = 1;
        } else if (faixaEstradaPosicao == 1) {
            for(int i=50; i<=700; i+=130) {
                artesGraficas.setColor(listaEstradaCor);
                artesGraficas.fillRect(375, i, 10,70);
                artesGraficas.fillRect(505, i, 10,70);
                artesGraficas.fillRect(635, i, 10,70);
            }
            faixaEstradaPosicao = 0;
        }

        // CARRO PLAYER JOGÁVEL
        // Carregando a imagem do carro jogável, carro do jogador, adicionando um try catch porque é necessário
        try {
            carroPlayer = new ImageIcon(ImageIO.read(getClass().getResource("./assets/cars/topGearCar1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
         // Colocando a imagem carregada anteriormente na tela, JFrame
        carroPlayer.paintIcon(this, artesGraficas, xPosicaoCarroPlayer, yPosicaoCarroPlayer);

        // Posição inicial do carro jogável para ele surgir fora da tela
        yPosicaoCarroPlayer -= 40;
		if (yPosicaoCarroPlayer < 500) {
			yPosicaoCarroPlayer = 500;
		}

        // CARROS INIMIGOS
        try {
            carroInimigo1 = new ImageIcon(ImageIO.read(getClass().getResource("./assets/cars/topGearCar2.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            carroInimigo2 = new ImageIcon(ImageIO.read(getClass().getResource("./assets/cars/topGearCar3.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            carroInimigo3 = new ImageIcon(ImageIO.read(getClass().getResource("./assets/cars/topGearCar4.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Defininco a posição dos carros inimigos
        carroInimigo1.paintIcon(this, artesGraficas, carroPosicaoX[carroPosicaoX1] , y1pos);
        carroInimigo2.paintIcon(this, artesGraficas, carroPosicaoX[carroPosicaoX2] , y2pos);
        carroInimigo3.paintIcon(this, artesGraficas, carroPosicaoX[carroPosicaoX3], y3pos);

        // incrementando a posição dos inimigos
        y1pos += 50;
		y2pos += 50;
		y3pos += 50;

        // Resetando a posição do carro inimigo para que não saia da tela: ToDo: testar essa lógica!!!
        if (y1pos > 700) {
			carroPosicaoX1 = random.nextInt(4);
			carroPosicaoY1 = random.nextInt(4);
			y1pos = carroPosicaoY[carroPosicaoY1]; 

		}
		if (y2pos > 700) {
			carroPosicaoX2++;
			if (carroPosicaoX2 > 4) {
				carroPosicaoX2 = 0;
			}

			carroPosicaoX2 = random.nextInt(4);
			carroPosicaoY2 = random.nextInt(4);
			y2pos = carroPosicaoY[carroPosicaoY2];

		}
		if (y3pos > 700) {
			carroPosicaoX3++;
			if (carroPosicaoX3 > 4) {
				carroPosicaoX3 = 0;
			}
			carroPosicaoX3 = random.nextInt(4);
			carroPosicaoY3 = random.nextInt(4);
			y3pos = carroPosicaoY[carroPosicaoY3];
		}

		if (carroPosicaoX1 == carroPosicaoX2 && carroPosicaoY1 > -100 && carroPosicaoY2 > -100) {
			carroPosicaoX1 -= 1;
			if (carroPosicaoX1 < 0) {
				carroPosicaoX1 += 2;
			}
		}
		if (carroPosicaoX1 == carroPosicaoX3 && carroPosicaoY1 > -100 && carroPosicaoY3 > -100) {
			carroPosicaoX3 -= 1;
			if (carroPosicaoX3 < 0) {
				carroPosicaoX3 += 2;
			}
		}
		if (carroPosicaoX2 == carroPosicaoX3 && carroPosicaoY3 > -100 && carroPosicaoY2 > -100) {
			carroPosicaoX2 -= 1;
			if (carroPosicaoX2 < 0) {
				carroPosicaoX2 += 2;
			}
		}
		if (carroPosicaoX1 < 2 && carroPosicaoX2 < 2 && carroPosicaoX3 < 2) {
			if (carroPosicaoX1 == 0 && carroPosicaoX2 == 0 && carroPosicaoX3 == 1) {
				carroPosicaoX3++;
				carroPosicaoX2++;
			} else if (carroPosicaoX1 == 0 && carroPosicaoX2 == 1 && carroPosicaoX3 == 0) {
				carroPosicaoX3++;
				carroPosicaoX2++;
			} else if (carroPosicaoX1 == 1 && carroPosicaoX2 == 0 && carroPosicaoX3 == 0) {
				carroPosicaoX1++;
				carroPosicaoX2++;
			}
		}

        // Lidando com batida entre os carros. 175 = tamanho da imagem do carro em y
        if (y1pos < yPosicaoCarroPlayer && y1pos + 175 > yPosicaoCarroPlayer && carroPosicaoX[carroPosicaoX1] == carroPosicaoX1) {
			fimDeJogo = true;
		}
		if (y2pos < yPosicaoCarroPlayer && y2pos + 175 > yPosicaoCarroPlayer && carroPosicaoX[carroPosicaoX2] == carroPosicaoX1) {
			fimDeJogo = true;
		}
		if (y3pos < yPosicaoCarroPlayer && y3pos + 175 > yPosicaoCarroPlayer && carroPosicaoX[carroPosicaoX3] == carroPosicaoX1) {
			fimDeJogo = true;
		}
		if (yPosicaoCarroPlayer < y1pos && yPosicaoCarroPlayer + 175 > y1pos && carroPosicaoX[carroPosicaoX1] == carroPosicaoX1) {
			fimDeJogo = true;
		}
		if (yPosicaoCarroPlayer < y2pos && yPosicaoCarroPlayer + 175 > y2pos && carroPosicaoX[carroPosicaoX2] == carroPosicaoX1) {
			fimDeJogo = true;
		}
		if (yPosicaoCarroPlayer < y3pos && yPosicaoCarroPlayer + 175 > y3pos && carroPosicaoX[carroPosicaoX3] == carroPosicaoX1) {
			fimDeJogo = true;
		}

        // MARCADORES
        // Marcador de pontos/score
        Color marcadores = new Color (0xFF5A36);
        artesGraficas.setColor(marcadores);
        artesGraficas.fillRect(100, 35, 220, 50);
        artesGraficas.setColor(Color.black);
        artesGraficas.fillRect(105, 40, 210, 40);
        
        // Marcador de velocidade
        artesGraficas.setColor(marcadores);
        artesGraficas.fillRect(100, 110,220, 50);
        artesGraficas.setColor(Color.black);
        artesGraficas.fillRect(105, 115, 210, 40);

        artesGraficas.setColor(marcadores);
        artesGraficas.setFont(new Font("Monospaced", Font.BOLD, 30));
        artesGraficas.drawString("SCORE: " + score, 120, 70);
        artesGraficas.drawString(velocidade + "Km/h", 120, 145);

        // Incrementando score e speed
        score++;
		velocidade++;

        if(velocidade > 140) {
            velocidade = 240 - delay;
        }
        if (score % 50 == 0) {
            delay -= 10;
            if (delay < 60) {
                delay = 60;
            }
        }

        // ToDo: Lidando com o delay do jogo
		try {

			TimeUnit.MILLISECONDS.sleep(delay); // delay the game
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

        if (y1pos < yPosicaoCarroPlayer && y1pos + 175 > yPosicaoCarroPlayer && carroPosicaoX[carroPosicaoX1] == xPosicaoCarroPlayer) {
			fimDeJogo = true;
		}
		if (y2pos < yPosicaoCarroPlayer && y2pos + 175 > yPosicaoCarroPlayer && carroPosicaoX[carroPosicaoX2] == xPosicaoCarroPlayer)

		{
			fimDeJogo = true;
		}
		if (y3pos < yPosicaoCarroPlayer && y3pos + 175 > yPosicaoCarroPlayer && carroPosicaoX[carroPosicaoX3] == xPosicaoCarroPlayer) {
			fimDeJogo = true;
		}

        Color amarelinhoFonte = new Color (0xFCD917);
        Color fundoFimDoJogo = new Color (0x9172EC);
        if (fimDeJogo) {
			artesGraficas.setColor(fundoFimDoJogo);
			artesGraficas.fillRect(100, 210, 820, 200);
			artesGraficas.setColor(Color.black);
			artesGraficas.fillRect(110, 220, 800, 180);
			artesGraficas.setFont(new Font("Monospaced", Font.BOLD, 50));
			artesGraficas.setColor(amarelinhoFonte);
			artesGraficas.drawString("Fim do Jogo!", 340, 290);
			artesGraficas.setColor(amarelinhoFonte);
			artesGraficas.setFont(new Font("Monospaced", Font.BOLD, 30));
			artesGraficas.drawString("Pressione Enter", 360, 340);
			if (!paint) {
				repaint();
				paint = true;
			}
		} else {
			repaint();
		}
    }    
    
    public static void main(String[] args) {
        RacingGame game = new RacingGame("Top Gear Memorial");
    
    }

    @Override
	public void keyPressed(KeyEvent e) {
        // Se a tecla esquerda for pressionada, o carro move para a esquerda
        if (e.getKeyCode() == KeyEvent.VK_LEFT && !fimDeJogo ) {
            xPosicaoCarroPlayer -= 130;
            if(xPosicaoCarroPlayer < 270) {
                xPosicaoCarroPlayer = 270;
            }
        }
        // Se a tecla direita for pressionada, o carro move para a direita
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && !fimDeJogo) {
            xPosicaoCarroPlayer += 130;
            if(xPosicaoCarroPlayer > 660) {
                xPosicaoCarroPlayer = 660;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER && fimDeJogo) {
            fimDeJogo = false;
			paint = false;
			carroPosicaoX1 = 0;
			carroPosicaoX2 = 2;
			carroPosicaoX3 = 3;
			carroPosicaoY1 = random.nextInt(4);
			carroPosicaoY2 = random.nextInt(4);
			carroPosicaoY3 = random.nextInt(4);
			y1pos = carroPosicaoY[carroPosicaoY1];
			y2pos = carroPosicaoY[carroPosicaoY2];
			y3pos = carroPosicaoY[carroPosicaoY3];
			velocidade = 90; // Reseta velocidade
			score = 0; // Reseta Score
			delay = 100; // delay para 100
			// Reseta a posição do carro player
            xPosicaoCarroPlayer = 400;
			yPosicaoCarroPlayer = 700; 
        }
    }
    @Override
    public void keyReleased(KeyEvent arg0){

    }

    // Aplicando para s e a, não só para seta
    @Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == 'a' && !fimDeJogo) {
			xPosicaoCarroPlayer -= 130;

		}
		if (e.getKeyChar() == 's' && !fimDeJogo) { 
			xPosicaoCarroPlayer += 130; 
		}

		repaint();
	}

    @Override
	public void actionPerformed(ActionEvent arg0) {
	}
}