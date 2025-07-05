import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class snake extends JFrame implements KeyListener, Runnable {

    Pregu pre = new Pregu();
    
            
    boolean ven = false;
    JPanel p1, p2;
    JButton[] lb = new JButton[200];
    JButton bonusfood;
    JTextArea t;
    int x = 1000, y = 500, gu = 2,gd = 2, directionx = 1, directiony = 0,
            speed = 50, difference = 0, oldx, oldy, score = 0;
    int[] lbx = new int[600];
    int[] lby = new int[600];
    Point[] lbp = new Point[600];
    Point bfp = new Point();
    Thread myt;
    boolean food = false, run1 = false, runr = true,
            runu = true, rund = true, bonusflag = true;
    Random r = new Random();
    JMenuBar mymbar;
    JMenu game, help, level;

    public void initializeValues() {
        gu = 3;
        gd=3;
        lbx[0] = 200;
        lby[0] = 300;
        directionx = 10;
        directiony = 0;
        difference = 0;
        score = 0;
        food = false;
        run1 = false;
        runr = true;
        runu = true;
        rund = true;
        bonusflag = true;

    }

    snake() {
        super("Snake");
        setSize(950, 590);
        setResizable(false);
        creatbar();
        initializeValues();
        p1 = new JPanel();
        p2 = new JPanel();

        t = new JTextArea("Score -->" + score);
        t.setEnabled(false);
        t.setBackground(Color.BLACK);

        bonusfood = new JButton();
        bonusfood.setEnabled(false);

        createFirstSnake();
        p1.setLayout(null);
        p2.setLayout(new GridLayout(0, 1));
        p1.setBounds(0, 0, x, y);
        p1.setBackground(Color.blue);
        p2.setBounds(0, y, x, 30);
        p2.setBackground(Color.white);

        p2.add(t);

        getContentPane().setLayout(null);
        getContentPane().add(p1);
        getContentPane().add(p2);
        show();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(this);

        myt = new Thread(this);
        myt.start();
    }

    public void createFirstSnake() {
        for (int i = 0; i < 3; i++) {
            lb[i] = new JButton("lb" + i);
            lb[i].setEnabled(false);
            p1.add(lb[i]);
            lb[i].setBounds(lbx[i], lby[i], 10, 10);
            lbx[i + 1] = lbx[i] - 10;
            lby[i + 1] = lby[i];
        }
    }

    public void creatbar() {
        mymbar = new JMenuBar();

        game = new JMenu("Game");

        JMenuItem newgame = new JMenuItem("New Game");
        JMenuItem exit = new JMenuItem("Exit");

        newgame.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        exit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        game.add(newgame);
        game.addSeparator();
        game.add(exit);

        mymbar.add(game);

        level = new JMenu("Level");

        mymbar.add(level);

        help = new JMenu("Help");

        JMenuItem creator = new JMenuItem("Creator");
        JMenuItem instruction = new JMenuItem("instruction");

        creator.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(p2, "Name" + ":");
            }
        });
        help.add(creator);
        help.add(instruction);
        mymbar.add(help);

        setJMenuBar(mymbar);
    }

    void reset() {
        initializeValues();
        p1.removeAll();

        myt.stop();
        

        createFirstSnake();
        t.setText("Score-->" + score);

        myt = new Thread(this);
        myt.start();

    }

    void growup() {
        lb[gu] = new JButton();
        lb[gu].setEnabled(false);
        p1.add(lb[gu]);

        int a = 10 + (r.nextInt(48) * 10);
        int b = 10 + (r.nextInt(23) * 10);

        lbx[gu] = a;
        lby[gu] = b;
        lb[gu].setBounds(a, b, 10, 10);

        gu++;
    }
    void growdown() {
        lb[gu] = new JButton();
        lb[gu-2].setEnabled(false);
        p1.add(lb[gu-1]);

        int a = 10 + (r.nextInt(48) * 10);
        int b = 10 + (r.nextInt(23) * 10);

        lbx[gu] = a;
        lby[gu] = b;
        lb[gu].setBounds(a, b, 10, 10);

        gu--;
    }

    void moveForward() {
        for (int i = 0; i < gu; i++) {
            lbp[i] = lb[i].getLocation();
        }
        lbx[0] += directionx;
        lby[0] += directiony;
        lb[0].setBounds(lbx[0], lby[0], 10, 10);

        for (int i = 1; i < gu; i++) {
            lb[i].setLocation(lbp[i - 1]);
        }
        if (lbx[0] == x) {
            lbx[0] = 10;
        } else if (lbx[0] == 0) {
            lbx[0] = x - 10;
        } else if (lby[0] == y) {
            lby[0] = 10;
        } else if (lby[0] == 0) {
            lby[0] = y - 10;
        }
        if (lbx[0] == lbx[gu - 1] && lby[0] == lby[gu - 1]) {
            food = false;
            score += 50;
            t.setText("Score-->" + score);
            if (score % 50 == 0 && bonusflag == true) {
                //Añadir panel preguntas

                //
                p1.add(bonusfood);
                bonusfood.setBounds((10 * r.nextInt(50)), (10 * r.nextInt(25)), 15, 15);
                bfp = bonusfood.getLocation();
                bonusflag = false;

            }
        }
        if (bonusflag == false) {
            ven = true;
            if (bfp.x <= lbx[0] && bfp.y <= lby[0] && bfp.x + 10 >= lbx[0] && bfp.y + 10 >= lby[0]) {
                p1.remove(bonusfood);
                
                pre.setVisible(true);
                
               
                t.setText("Score-->" + score);
                bonusflag = true;

            }
        }
        if (food == false) {
            growup();
            food = true;
        } else {
            lb[gu - 1].setBounds(lbx[gu - 1], lby[gu - 1], 10, 10);
        }
        for (int i = 1; i < gu; i++) {
            if (lbp[i] == lbp[0]) {
                t.setText("Game Over - " + score);
                try {
                    myt.join();
                } catch (InterruptedException ie) {
                }
                break;
            }
        }
        p1.repaint();
        show();
    }

    public void keyPressed(KeyEvent e) {
        // flecha izquierda 
        if (run1 == true && e.getKeyCode() == 37) {
            directionx = -10;
            directiony = 0;
            runr = false;
            runu = true;
            rund = true;
        }
        // flecha arriba
        if (runu == true && e.getKeyCode() == 38) {
            directionx = 0;
            directiony = -10;
            rund = false;
            runr = true;
            run1 = true;
        }
        // flecha derecha
        if (runr == true && e.getKeyCode() == 39) {
            directionx = +10;
            directiony = 0;
            run1 = false;
            runu = true;
            rund = true;
        }
        // flecha abajo
        if (rund == true && e.getKeyCode() == 40) {
            directionx = 0;
            directiony = +10;
            runu = false;
            runr = true;
            run1 = true;
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void run() {
        for (;;) {
            moveForward();
            try {
                Thread.sleep(speed);
            } catch (InterruptedException ie) {
            }
        }

    }

}
