import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

// Brayan Velazco Martinez 

public class Gato01 extends JFrame implements ActionListener{
    JButton botones [] = new JButton[9];
    boolean turnoX;
    JButton btnReiniciar;
    Font fuente = new Font("Arial",1,50);
    JPanel panelJuego, panelOpciones;
    String letra;
    JLabel lblTurno;
       
    public Gato01(){
        setTitle("Juego Gato");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        panelJuego = new JPanel();
        panelJuego.setLayout(new GridLayout(3,3));
       
        for(int i=0;i<botones.length;i++){
            botones[i] = new JButton("");
            botones[i].setFont(fuente);
            botones[i].addActionListener(this);
            panelJuego.add(botones[i]);
        }
       
        add(panelJuego, BorderLayout.CENTER);
       
        panelOpciones = new JPanel();

        lblTurno = new JLabel("Turno de O");
        panelOpciones.add(lblTurno);

        btnReiniciar = new JButton("Reinciar Juego");
       
        btnReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 for(int i=0;i<botones.length;i++){
                        botones[i].setText("");
                        botones[i].setEnabled(true);
                        botones[i].setBackground(null);
                 }
                 lblTurno.setText("Turno de O");
            }
        });
       
        panelOpciones.add(btnReiniciar);
        add(panelOpciones, BorderLayout.SOUTH);
    }
   
    public static void main(String [] a){
        Gato01 g01 = new Gato01();
        g01.setVisible(true);
    }
   
    public void actionPerformed(ActionEvent e){        
        if(turnoX){
            letra = "X";
            turnoX=false;
            lblTurno.setText("Turno de O");
        }else{
            letra = "O";
            turnoX=true;
            lblTurno.setText("Turno de X");
        }
       
        for(int i=0;i<botones.length;i++){
            if(e.getSource()==botones[i]){
                botones[i].setText(letra);

                if(letra.equals("X")){
                    botones[i].setBackground(Color.RED);
                }else{
                    botones[i].setBackground(Color.BLUE);
                }

                botones[i].setOpaque(true);
                botones[i].setBorderPainted(false);

                botones[i].setEnabled(false);
            }
        }

        validarGanador();
    }

    public void validarGanador(){

        int[][] combinaciones = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
        };

        for(int i=0;i<combinaciones.length;i++){

            String a = botones[combinaciones[i][0]].getText();
            String b = botones[combinaciones[i][1]].getText();
            String c = botones[combinaciones[i][2]].getText();

            if(!a.equals("") && a.equals(b) && b.equals(c)){
                JOptionPane.showMessageDialog(this, "Ganó " + a);

                for(int j=0;j<botones.length;j++){
                    botones[j].setEnabled(false);
                }
            }
        }
    }
}