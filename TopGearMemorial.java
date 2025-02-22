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
    private int FaixaEstradaPosicao = 0;
    
    // Constructor: iníciando o jogo (janela)
    public TopGearMemorial(String title){
        // Chamando o constructor do JFrame
        super(title);
        setBounds(300,10,700,700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setFocusable(true);
        setResizable(false);
    }
    
    public void paint(Graphics artesGraficas) {
        var grama = new Color (0X136d15);
        artesGraficas.setColor(grama);
        artesGraficas.fillRect(0,0,700,700);
        
        var estrada = new Color (0X355372);
        artesGraficas.setColor(estrada);
		artesGraficas.fillRect(100, 0, 500, 700);
        
        var limiteEstrada = new Color (0Xff0000);
        artesGraficas.setColor(limiteEstrada);
        artesGraficas.fillRect(90, 0,10,700);
        artesGraficas.fillRect(600, 0, 10, 700);
        
        // Criando as faixas da estrada:
        var listaEstradaCor = new Color (0Xfffff2);
        if (FaixaEstradaPosicao == 0) {
            for(int i = 0; i <= 700; i++){
                artesGraficas.setColor(listaEstradaCor);
                artesGraficas.fillRect(350, i, 10,70);
                artesGraficas.fillRect(225, i, 10,70);
                artesGraficas.fillRect(475, i, 10,70);
            }
            FaixaEstradaPosicao = 1;
        } else if (FaixaEstradaPosicao == 1) {
            for(int i=50; i<=700; i+=100) {
                artesGraficas.setColor(listaEstradaCor);
                artesGraficas.fillRect(350, i, 10,70);
                artesGraficas.fillRect(225, i, 10,70);
                artesGraficas.fillRect(475, i, 10,70);
            }
            FaixaEstradaPosicao = 0;
        }
    }    
    
    public static void main(String[] args) {
        TopGearMemorial game = new TopGearMemorial("Top Gear Memorial");
    
    }
}