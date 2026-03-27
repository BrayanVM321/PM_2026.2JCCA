import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Velazco Martinez Brayan

public class Ventana extends JFrame implements ActionListener {
    int ancho, alto;
    JTextField txtEdad;
    JButton btnAceptar;
    JPanel panelBotones;

    public Ventana() {
        ancho = 400;
        alto = 400;
        setTitle("Verificador de edad");
        setSize(ancho, alto);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelBotones = new JPanel();
        txtEdad = new JTextField(5);
        btnAceptar = new JButton("Ingresa tu edad actual");

        btnAceptar.addActionListener(this);

        panelBotones.add(txtEdad);
        panelBotones.add(btnAceptar);

        add(panelBotones);
    }

    public static void main(String[] args) {
        Ventana v = new Ventana();
        v.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int edad = Integer.parseInt(txtEdad.getText());

            if (edad >= 18) {
                JOptionPane.showMessageDialog(null, "Eres mayor de edad");
            } else {
                JOptionPane.showMessageDialog(null, "NO eres mayor de edad");
            }

            txtEdad.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingresa una edad valida ");
        }
    }
}