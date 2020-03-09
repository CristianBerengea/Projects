package com.company;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class GamePlay extends JPanel implements KeyListener, ActionListener, MouseListener, MouseMotionListener {

    private Timer t;

    Time time = new Time();

    private boolean play = false;

    private int playerX = 1350/2;
    private int playerY = 720/4;

    private boolean freze = false;
    private boolean inv = false;

    private boolean pause = false;

    private boolean highScore = false;

    private int n=1000;

    private int z=0;

    private int lastSeconds=0;
    private int lastSecondsFreze=0;
    private int lastSecondsInv=0;

    private int now=0;

    private int pauseTime=0;

    private int mod = 2;  // easy/mediu/hard

    private int playMod = 2;  // keyboard/mouse

    private int life = 3;
    private int rate = 2;
    private int highScoreEasy = 0;
    private int highScoreMediu = 0;
    private int highScoreHard = 0;

    private int[] ballX = new int[n];
    private int[] ballY = new int[n];
    private int[] ballM = new int[n];
    private int[] ballDX = new int[n];
    private int[] ballDY = new int[n];


    private boolean[] ballV = new boolean[n];


    public GamePlay()
    {
        addKeyListener(this);
        setFocusable(true);

        addMouseListener(this);

        addMouseMotionListener(this);

        t = new Timer(10,this);
        t.start();
        time.start();
    }

    public void paint(Graphics g)
    {

        g.setColor(Color.black);
        g.fillRect(0,0,1400,700);

        g.setColor(Color.white);
        g.fillRect(10,10,1310,660);

        if(!inv) g.setColor(Color.black);
        else g.setColor(Color.gray);
        g.fillRect(playerX,playerY,30,30);

        if(!highScore)
        for(int i=0;i<z;i++)
        {
            if(ballV[i]||pause)
            {
                g.setColor(Color.red);
                if(i%3==0) g.setColor(Color.blue);
                if(i%10==0) g.setColor(Color.green);
                if(i%13==0) g.setColor(Color.yellow);
                g.fillOval(ballX[i],ballY[i],ballM[i],ballM[i]);
            }
        }
        else
        {
            for(int i=0;i<n;i++)
            {

                g.setColor(Color.red);
                if(i%3==0) g.setColor(Color.blue);
                if(i%10==0) g.setColor(Color.green);
                if(i%13==0) g.setColor(Color.yellow);
                g.fillOval(ballX[i],ballY[i],ballM[i],ballM[i]);
            }

            g.setColor(Color.black);
            g.setFont(new Font("serif",Font.BOLD,100));
            g.drawString("New High Score", 350, 130);
        }

        g.setColor(Color.black);
        g.setFont(new Font("serif",Font.BOLD,25));
        if(mod==1) g.drawString("Easy", 30, 30);
        else if(mod==2) g.drawString("Mediu", 30, 30);
        else if(mod==3)  g.drawString("Hard", 30, 30);

        g.setColor(Color.black);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString("Life: "+life, 600, 30);

            g.setColor(Color.black);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("Score: " + now, 700, 30);


        g.setColor(Color.black);
        g.setFont(new Font("serif",Font.BOLD,25));
        if(mod==1) g.drawString("Highscore: "+highScoreEasy, 1000, 30);
        else if(mod==2) g.drawString("Highscore: "+highScoreMediu, 1000, 30);
        else g.drawString("Highscore: "+highScoreHard, 1000, 30);


        if(life == 0)
        {
            g.setColor(Color.black);
            g.setFont(new Font("serif",Font.BOLD,50));
            g.drawString("Game Over", 530, 200);
        }

        if(play==false||pause) {

            if(pause&&life!=0)
            {
                g.setColor(Color.black);
                g.setFont(new Font("serif",Font.BOLD,50));
                g.drawString("Pause", 600, 200);
            }

            g.setColor(Color.gray);
            g.fillRect(530,240,260,60);

            g.setColor(Color.black);
            g.setFont(new Font("serif",Font.BOLD,50));
            g.drawString("Start Easy", 530, 285);

            g.setColor(Color.gray);
            g.fillRect(530,310,260,60);

            g.setColor(Color.black);
            g.setFont(new Font("serif",Font.BOLD,50));
            g.drawString("Start Mediu", 530, 355);

            g.setColor(Color.gray);
            g.fillRect(530,380,260,60);

            g.setColor(Color.black);
            g.setFont(new Font("serif",Font.BOLD,50));
            g.drawString("Start Hard", 530, 420);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        t.start();

        if(play) {

            now= time.getSeconds();

            if(freze&&now==lastSecondsFreze+4)
            {
                freze=false;
            }

            if(inv&&now==lastSecondsInv+5)
            {
                inv=false;
            }


            if(now==lastSeconds+rate )
            {
                if(++z<n) {
                    ballV[z]=true;
                }
                lastSeconds+=rate;
                ballM[new Random().nextInt(z)]+=10;
            }

            Rectangle rect= new Rectangle(playerX,playerY,30,30);

            for(int i=0;i<z;i++)
            {

                if(ballV[i] && !freze) {
                    ballX[i]+=ballDX[i];
                    ballY[i]+=ballDY[i];

                    ballX[i] = Math.max(ballX[i], 10);
                    ballY[i] = Math.max(ballY[i], 10);
                    ballX[i] = Math.min(ballX[i], 1320 - ballM[i]);
                    ballY[i] = Math.min(ballY[i], 670- ballM[i]);
                    if (ballX[i]==10 || ballX[i]+ballM[i]==1320)
                        ballDX[i]*=-1;
                    if (ballY[i]==10 || ballY[i]+ballM[i]==670)
                        ballDY[i]*=-1;
                }

                if(ballV[i]) {
                    if(rect.intersects(new Rectangle(ballX[i],ballY[i],ballM[i],ballM[i])))
                    {
                        ballV[i]=false;
                        if(i%13==0) {
                            inv = true;
                            lastSecondsInv=now;
                        }
                        else if(i%10==0) {
                            freze = true;
                            lastSecondsFreze=now;
                        }else if(i%3==0)
                        {
                             life++;
                        }
                        else
                        {
                            if(!inv) life--;
                            else ballV[i]=true;
                        }

                        if(life==0)
                        {
                            play = false;
                            if(mod==1)
                            {
                                if(now>highScoreEasy){
                                    highScoreEasy = now;
                                    highScore = true;
                                }
                            }else if(mod==2)
                            {
                                if(now>highScoreMediu) {
                                    highScoreMediu = now;
                                    highScore = true;
                                }
                            }else if(mod==3)
                            {
                                if(now>highScoreHard)
                                {
                                    highScoreHard = now;
                                    highScore = true;
                                }
                            }
                        }
                    }
                }

            }

        }

        if(highScore)
            for(int i=0;i<n;i++) {
                ballX[i] += ballDX[i];
                ballY[i] += ballDY[i];

                ballX[i] = Math.max(ballX[i], 10);
                ballY[i] = Math.max(ballY[i], 10);
                ballX[i] = Math.min(ballX[i], 1320 - ballM[i]);
                ballY[i] = Math.min(ballY[i], 670 - ballM[i]);
                if (ballX[i] == 10 || ballX[i] + ballM[i] == 1320)
                    ballDX[i] *= -1;
                if (ballY[i] == 10 || ballY[i] + ballM[i] == 670)
                    ballDY[i] *= -1;
            }
        repaint();
    }

    public void highScoreR()
    {
        for(int i=0;i<n;i++) {
            ballM[i]=new Random().nextInt(30)+10;
            ballDX[i]=new Random().nextInt(5)+1;
            ballDY[i]=new Random().nextInt(5)+1;

            if(i%2==0) {
                ballX[i]= -ballM[i];
                ballY[i]= -ballM[i];

            }
            else
            {
                ballX[i]= 1350 +ballM[i];
                ballY[i]= -ballM[i];
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if(playMod==1)
        {
            if(keyCode == KeyEvent.VK_UP)
            {
                 moveUp();
                repaint();
            }

            if(keyCode == KeyEvent.VK_DOWN)
            {
                moveDown();
                repaint();
            }

            if(keyCode == KeyEvent.VK_LEFT)
            {
                 moveLeft();
                repaint();
            }

            if(keyCode == KeyEvent.VK_RIGHT)
            {
                 moveRight();
                repaint();
            }
        }

            if(keyCode == KeyEvent.VK_SPACE)
            {
                if (!play&&life!=0) {
                    play = true;
                    time.setSeconds(pauseTime);
                    pause=false;
                }
                else {
                    play = false; pauseTime=time.getSeconds();
                    pause=true;
                    repaint();
                }
            }

            if(keyCode == KeyEvent.VK_C)
            {
                if(playMod == 1) playMod = 2;
                else playMod = 1;
            }

    }

    public void moveUp(){
        if (play) playerY-=30;
        playerX = Math.max(playerX, 10);
        playerY = Math.max(playerY, 10);
        playerX = Math.min(playerX, 1320 - 30);
        playerY = Math.min(playerY, 670-30);
    }
    public void moveDown(){
        if (play) playerY+=30;
        playerX = Math.max(playerX, 10);
        playerY = Math.max(playerY, 10);
        playerX = Math.min(playerX, 1320 - 30);
        playerY = Math.min(playerY, 670-30);
    }
    public void moveLeft(){
        if (play) playerX-=30;
        playerX = Math.max(playerX, 10);
        playerY = Math.max(playerY, 10);
        playerX = Math.min(playerX, 1320 - 30);
        playerY = Math.min(playerY, 670-30);
    }
    public void moveRight(){
        if (play) playerX+=30;
        playerX = Math.max(playerX, 10);
        playerY = Math.max(playerY, 10);
        playerX = Math.min(playerX, 1320 - 30);
        playerY = Math.min(playerY, 670-30);
    }

    @Override
    public void keyReleased(KeyEvent e) {	}

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            if(play == false)
            {
                if(e.getX()>530&&e.getX()<790)
                {

                    if(e.getY()>240&&e.getY()<300)
                    {
                        mod=1;
                        rate=3;

                        for(int i=0;i<n;i++) {
                            ballV[i]=false;
                            ballM[i]=new Random().nextInt(30)+10;
                            ballDX[i]=new Random().nextInt(5)+1;
                            ballDY[i]=new Random().nextInt(5)+1;

                            if(i%2==0) {
                                ballX[i]= -ballM[i];
                                ballY[i]= -ballM[i];

                            }
                            else
                            {
                                ballX[i]= 1350 +ballM[i];
                                ballY[i]= -ballM[i];
                            }
                            reset();
                        }
                    }else if(e.getY()>310&&e.getY()<370)
                    {
                        mod=2;
                        rate=2;

                        for(int i=0;i<n;i++) {
                            ballV[i]=false;
                            ballM[i]=new Random().nextInt(30)+10;
                            ballDX[i]=new Random().nextInt(7)+1;
                            ballDY[i]=new Random().nextInt(7)+1;

                            if(i%2==0) {
                                ballX[i]= -ballM[i];
                                ballY[i]= -ballM[i];

                            }
                            else
                            {
                                ballX[i]= 1350 +ballM[i];
                                ballY[i]= -ballM[i];
                            }
                            reset();
                        }
                    }if(e.getY()>380&&e.getY()<440)
                {
                    mod=3;
                    rate=1;

                    for(int i=0;i<n;i++) {
                        ballV[i]=false;
                        ballM[i]=new Random().nextInt(30)+10;
                        ballDX[i]=new Random().nextInt(12)+1;
                        ballDY[i]=new Random().nextInt(12)+1;

                        if(i%2==0) {
                            ballX[i]= -ballM[i];
                            ballY[i]= -ballM[i];

                        }
                        else
                        {
                            ballX[i]= 1350 +ballM[i];
                            ballY[i]= -ballM[i];
                        }
                    }
                    reset();
                }
                }
            }
        }
    }

    public void reset()
    {
        play = true;
        pause = false;

        time.resetSeconds();
        ballV[0]=true;
        z=0;
        life=3;
        lastSeconds=0;
        lastSecondsFreze=0;
        lastSecondsInv=0;
        freze = false;
        inv = false;
        highScore=false;
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) { }

    @Override
    public void mouseMoved(MouseEvent e) {
          if(playMod==2)
          {
              playerX = e.getX() -15;
              playerY = e.getY() -15;

              playerX = Math.max(playerX, 10);
              playerY = Math.max(playerY, 10);
              playerX = Math.min(playerX, 1320 - 30);
              playerY = Math.min(playerY, 670-30);

              repaint();
          }
    }
}

