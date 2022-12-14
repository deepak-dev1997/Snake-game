import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;



public class GamePanel extends JPanel implements ActionListener {
    int screenwidth=600;
    int screenheight=600;
    int unit_Size=25;
    int game_unit=(screenwidth*screenheight);
    int delay=100;
    int x[]=new int[game_unit];
    int y[]=new int[game_unit];
    int body_part=6;
    int appleseat;
    int applex;
    int appley;
    char Direction='R';
    boolean running=false;
    Timer timer;
    Random random;
    GamePanel(){

    random=new Random();
    this.setPreferredSize(new Dimension(screenwidth,screenheight));
    this.setBackground(Color.black);
    this.setFocusable(true);
    this.addKeyListener(new MyKeyAdapter());
    startGame();
    }


    public void startGame(){
    newApple();
    running=true;
    timer=new Timer(delay,this);
    timer.start();
    }

    public void paintComponent(Graphics g){
    super.paintComponent(g);
    draw(g);
    }

    public void draw(Graphics g){

    if(running==true) {
        g.setColor(Color.red);
        g.fillOval(applex, appley, unit_Size, unit_Size);
        for (int i = 0; i < body_part; i++) {
            if (i == 0) {
                g.setColor(Color.green);
                g.fillRect(x[i], y[i], unit_Size, unit_Size);
            } else {
                g.setColor(new Color(50, 180, 0));
                g.fillRect(x[i], y[i], unit_Size, unit_Size);
            }
        }
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,20));
        FontMetrics metrics= getFontMetrics(g.getFont());
        g.drawString("Score : "+appleseat,(screenwidth-metrics.stringWidth("Score : "+appleseat))/2,g.getFont().getSize());
    }
    else{
        gameOver(g);
    }
    }


    public void newApple(){
    applex=random.nextInt((int)(screenwidth/unit_Size))*unit_Size;
    appley=random.nextInt((int)(screenheight/unit_Size))*unit_Size;

    }
    public void move(){
    for(int i=body_part;i>0;i--){
        x[i]=x[i-1];
        y[i]=y[i-1];
    }
    switch(Direction){
        case 'U':
            y[0]=y[0]-unit_Size;
            break;
        case 'D':
            y[0]=y[0]+unit_Size;
            break;
        case 'L':
            x[0]=x[0]-unit_Size;
            break;
        case 'R':
            x[0]=x[0]+unit_Size;
            break;
    }
    }


    public void checkApple(){
    if((x[0]==applex) && y[0]==appley){
        body_part++;
        appleseat++;
        newApple();
    }
    }


    public void checkCollision(){
        //this checks if head collides with body
        for(int i=body_part;i>0;i--){
            if(x[0]==x[i] && y[0]==y[i]){
                running=false;
            }
        }
        //if head touches left , right,up and down border
        if(x[0]>screenwidth){ //right border
            running=false;
        }
        else if(x[0]<0){
            running=false;  //left border
        }
        else if(y[0]>screenheight){
            running=false; //up border
        }
        else if(y[0]<0){
            running=false; // down border
        }

        if(running==false){
        timer.stop();

    }
    }
    public void gameOver(Graphics g){
        // text for game over
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics= getFontMetrics(g.getFont());
        g.drawString("Game Over!!",(screenwidth-metrics.stringWidth("Game Over!!"))/2,screenheight/2);



    }
    @Override
    public void actionPerformed(ActionEvent e) {
    if(running==true){
        move();
        checkApple();
        checkCollision();
    }
    repaint();
    }
    public class MyKeyAdapter extends KeyAdapter{
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(Direction!='R'){
                Direction='L';
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(Direction!='L'){
                Direction='R';
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            if(Direction!='U'){
                Direction='D';
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_UP){
            if(Direction!='D'){
                Direction='U';
            }
        }
    }
    }
}
