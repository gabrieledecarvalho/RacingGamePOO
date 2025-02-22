import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Classe herdando do JFrame e (depois) implementando as interfaces KeyListener (para eventos do teclado) e o actionListener para eventos 
class TopGearMemorial extends JFrame /*implements KeyListener, ActionListener */{
    
    // Criando posição da linha
    private int faixaEstradaPosicao = 0;
    /* pensar se adiciona depois
    private int gramaPosicao = 0;
    private int limiteEstradaPosicao = 0;
     */
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
        
        var grama1 = new Color (0x0A6906);
        //var grama2 = new Color (0X008000);
        
        artesGraficas.setColor(grama1);
        artesGraficas.fillRect(0,0,1020,700);
        
        var estrada = new Color (0X738595);
        
        artesGraficas.setColor(estrada);
		artesGraficas.fillRect(255, 0, 510, 700);
        
        var limiteEstrada1 = new Color (0X2138AB);
        // var limiteEstrada2 = new Color (0XC7C9D5);

        artesGraficas.setColor(limiteEstrada1);
        artesGraficas.fillRect(215, 0,40,700);
        artesGraficas.fillRect(765, 0, 40, 700);
        
        // Criando as faixas da estrada:
        var listaEstradaCor = new Color (0Xfffff2);
        if (faixaEstradaPosicao == 0) {
            for(int i = 0; i <= 700; i++){
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

        // Marcador de pontos/score
        artesGraficas.setColor(Color.orange);
        artesGraficas.fillRect(100, 35, 220, 50);
        artesGraficas.setColor(Color.white);
        artesGraficas.fillRect(105, 40, 210, 40);
        
        // Marcador de velocidade
        artesGraficas.setColor(Color.orange);
        artesGraficas.fillRect(100, 110,220, 50);
        artesGraficas.setColor(Color.white);
        artesGraficas.fillRect(105, 115, 210, 40);
    }    
    
    public static void main(String[] args) {
        TopGearMemorial game = new TopGearMemorial("Top Gear Memorial");
    
    }
}