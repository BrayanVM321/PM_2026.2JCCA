import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Fred extends JFrame {

    JButton[] casillas = new JButton[4];
    int secuencia[] = new int[6];
    Random random = new Random();

    boolean turnoUsuario = false;
    int posicion = 0;
    boolean fallo = false;

    public Fred() {
        setSize(300, 300);
        setDefaultCloseOperation(3);
        setTitle("Fred 20");
        setLayout(new GridLayout(2, 2));

        for (int i = 0; i < 4; i++) {
            casillas[i] = new JButton();
            casillas[i].setBackground(Color.LIGHT_GRAY);
            casillas[i].setOpaque(true);

            int idx = i;

            casillas[i].addActionListener(e -> {

                if(!turnoUsuario) return;

                if(idx == 0) casillas[idx].setBackground(Color.RED);
                if(idx == 1) casillas[idx].setBackground(Color.GREEN);
                if(idx == 2) casillas[idx].setBackground(Color.BLUE);
                if(idx == 3) casillas[idx].setBackground(Color.YELLOW);

                casillas[idx].repaint();

                if(secuencia[posicion] != idx){
                    fallo = true;
                }

                posicion++;

                new Thread(() -> {
                    try {
                        Thread.sleep(300);
                        casillas[idx].setBackground(Color.LIGHT_GRAY);
                        casillas[idx].repaint();
                    } catch (Exception ex) {}
                }).start();

                if(posicion == secuencia.length){
                    turnoUsuario = false;
                    if(fallo){
                        JOptionPane.showMessageDialog(null, "Perdiste");
                    }else{
                        JOptionPane.showMessageDialog(null, "Ganaste");
                    }
                }

            });

            add(casillas[i]);
        }

        crearSecuencia();
        mostrarSecuencia();
    }

    public void crearSecuencia() {

        for (int i = 0; i < secuencia.length; i++) {
            secuencia[i] = random.nextInt(4);
        }

        for (int x : secuencia) {
            System.out.print(x + " ");
        }
    }

    public void mostrarSecuencia() {

        turnoUsuario = false;

        Thread h = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < secuencia.length; i++) {
                    int indice = secuencia[i];
                    try {

                        if(indice == 0) casillas[indice].setBackground(Color.RED);
                        if(indice == 1) casillas[indice].setBackground(Color.GREEN);
                        if(indice == 2) casillas[indice].setBackground(Color.BLUE);
                        if(indice == 3) casillas[indice].setBackground(Color.YELLOW);

                        casillas[indice].repaint();

                        Thread.sleep(700);

                        casillas[indice].setBackground(Color.LIGHT_GRAY);
                        casillas[indice].repaint();

                        Thread.sleep(300);

                    } catch (Exception e) {
                    };
                }

                turnoUsuario = true;
                posicion = 0;
                fallo = false;

                JOptionPane.showMessageDialog(null, "Replica la secuencia");

            }
        });
        h.start();
    }

    public static void main(String[] args) {
        Fred f = new Fred();
        f.setVisible(true);
    }
}