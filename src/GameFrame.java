import javax.swing.*;

public class GameFrame extends JFrame {
    GameFrame(){
    GamePanel panel=new GamePanel();
    add(panel);
    setTitle("The Snake Game");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    pack();
    setVisible(true);
    setLocationRelativeTo(null);
    }
}
