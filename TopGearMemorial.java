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

// Classe herdando do JFrame e (depois) implementando as interfaces KeyListener (para eventos do teclado) e o actionListener para eventos 
class TopGearMemorial extends JFrame /*implements KeyListener, ActionListener */{
    
    // Criando posição da linha
    private int faixaEstradaPosicao = 0;
    /* pensar se adiciona depois
    private int gramaPosicao = 0;
    private int limiteEstradaPosicao = 0;
     */

    // CARROS
    private int xPosicaoCarroPlayer = 400;
    private int yPosicaoCarroPlayer = 700;
    // Criando imagem do carro
    private ImageIcon carroPlayer;

    // MOVIMENTAÇÕES
    // Posição inicial, possíveis
    private int carroPosicaoX [] = {270, 400, 530, 660};
    private int carroPosicaoY [] = { -240, -480, -720, -960, -1200 };

    Random random = new Random();
    // Incrementos
    private int carroPosicaoX1 = 0, carroPosicaoX2 = 2, carroPosicaoX3 = 4;
    private int carroPosicaoY1 = random.nextInt(5), carroPosicaoY2 = random.nextInt(5), carroPosicaoY3 = random.nextInt(5);

    int y1pos = carroPosicaoY[carroPosicaoY1], y2pos = carroPosicaoY[carroPosicaoY2], y3pos = carroPosicaoY[carroPosicaoY3]; // y position of the car

    private ImageIcon carroInimigo1, carroInimigo2, carroInimigo3;

    // Constructor: iníciando o jogo (janela)
    public TopGearMemorial(String title){
        // Chamando o constructor do JFrame
        super(title);
        setBounds(300,10,1020,700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setFocusable(true);
        setResizable(false);
    }
    
    public void paint(Graphics artesGraficas) {
        
        Color gramaCor1 = new Color (0x0A6906);
        //var gramaCor2 = new Color (0X008000);
        
        artesGraficas.setColor(gramaCor1);
        artesGraficas.fillRect(0,0,1020,700);
        
        Color estrada = new Color (0X738595);
        
        artesGraficas.setColor(estrada);
		artesGraficas.fillRect(255, 0, 510, 700);
        
        Color limiteEstradaCor1 = new Color (0X2138AB);
        // var limiteEstradaCor2 = new Color (0XC7C9D5);

        artesGraficas.setColor(limiteEstradaCor1);
        artesGraficas.fillRect(215, 0,40,700);
        artesGraficas.fillRect(765, 0, 40, 700);
        
        // Criando as faixas da estrada:
        Color listaEstradaCor = new Color (0Xfffff2);
        if (faixaEstradaPosicao == 0) {
            for(int i = 0; i <= 700; i += 100){
                artesGraficas.setColor(listaEstradaCor);
                artesGraficas.fillRect(375, i, 10,70);
                artesGraficas.fillRect(305, i, 10,70);
                artesGraficas.fillRect(635, i, 10,70);
            }
            faixaEstradaPosicao = 1;
        } else if (faixaEstradaPosicao == 1) {
            for(int i=50; i<=700; i+=100) {
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

        // Resetando a posição do carro inimigo para que não saia da tela
        if (y1pos > 700) {
			carroPosicaoX1 = random.nextInt(5);
			carroPosicaoY1 = random.nextInt(5);
			y1pos = carroPosicaoY[carroPosicaoY1]; 

		}
		if (y2pos > 700) {
			carroPosicaoX2++;
			if (carroPosicaoX2 > 4) {
				carroPosicaoX2 = 0;
			}

			carroPosicaoX2 = random.nextInt(5);
			carroPosicaoY2 = random.nextInt(5);
			y2pos = carroPosicaoY[carroPosicaoY2];

		}
		if (y3pos > 700) {
			carroPosicaoX3++;
			if (carroPosicaoX3 > 4) {
				carroPosicaoX3 = 0;
			}
			carroPosicaoX3 = random.nextInt(5);
			carroPosicaoY3 = random.nextInt(5);
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

        // MARCADORES
        // Marcador de pontos/score
        artesGraficas.setColor(Color.orange);
        artesGraficas.fillRect(100, 35, 220, 50);
        artesGraficas.setColor(Color.black);
        artesGraficas.fillRect(105, 40, 210, 40);
        
        // Marcador de velocidade
        artesGraficas.setColor(Color.orange);
        artesGraficas.fillRect(100, 110,220, 50);
        artesGraficas.setColor(Color.black);
        artesGraficas.fillRect(105, 115, 210, 40);

        artesGraficas.setColor(Color.ORANGE);
        artesGraficas.setFont(new Font("Monospaced", Font.BOLD, 30));
        artesGraficas.drawString("SCORE: " , 120, 70);
        artesGraficas.drawString("SPEED: ", 120, 145);
    }    
    
    public static void main(String[] args) {
        TopGearMemorial game = new TopGearMemorial("Top Gear Memorial");
    
    }
}