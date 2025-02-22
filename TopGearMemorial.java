import javax.swing.JFrame;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Classe herdando do JFrame e (depois) implementando as interfaces KeyListener (para eventos do teclado) e o actionListener para eventos 
class TopGearMemorial extends JFrame /*implements KeyListener, ActionListener */{
    
    // Constructor: iníciando o jogo
    public TopGearMemorial(String title){
        // Chamando o constructor do JFrame
        super(title);
    }
    public static void main(String[] args) {
        TopGearMemorial game = new TopGearMemorial("Top Gear Memorial");
    }
}