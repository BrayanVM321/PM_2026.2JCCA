import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Velazco Martinez Brayan 

public class CalculadoraGrafica extends JFrame {
    JButton btnSumar, btnRestar, btnMultplicar, btnDividir;
    JTextField n1, n2;
    JLabel txtRes;
    Font fuente = new Font("Courier",2,60);
    JPanel pOpciones = new JPanel(new GridLayout(1,4));

    public CalculadoraGrafica(){
        setTitle("casio");
        setSize(400,400);
        setDefaultCloseOperation(3);  
        setLayout(new GridLayout(5,1));
       
        n1 = new JTextField(10);
        n1.setFont(fuente);
        n2 = new JTextField(10);
        n2.setFont(fuente);

        btnSumar = new JButton("+");
        btnSumar.setFont(fuente);

        btnRestar = new JButton("-");
        btnRestar.setFont(fuente);        

        btnMultplicar = new JButton("*");
        btnMultplicar.setFont(fuente);          

        btnDividir = new JButton("/");
        btnDividir.setFont(fuente);

        pOpciones.add(btnSumar);
        pOpciones.add(btnRestar);
        pOpciones.add(btnMultplicar);
        pOpciones.add(btnDividir);
       
        txtRes = new JLabel("");
        txtRes.setFont(fuente);

        // 🔥 TODOS con lambdas

        btnSumar.addActionListener(e -> {
            int num1 = Integer.parseInt(n1.getText());
            int num2 = Integer.parseInt(n2.getText());
            txtRes.setText((num1 + num2) + "");
        });

        btnRestar.addActionListener(e -> {
            Calculadora c = new Calculadora();
            int num1 = Integer.parseInt(n1.getText());
            int num2 = Integer.parseInt(n2.getText());
            txtRes.setText(c.restar(num1, num2) + "");
        });

        btnMultplicar.addActionListener(e -> {
            calcular("*");
        });
       
        btnDividir.addActionListener(e -> {
            calcular("/");
        });
       
        add(n1);
        add(n2);
        add(pOpciones);
        add(txtRes);
    }
   
    public void calcular(String operador){
         double num1 = Double.parseDouble(n1.getText());
         double num2 = Double.parseDouble(n2.getText());
         double res=0;

         switch(operador){
             case "*": res = num1 * num2; break;
             case "/": res = num1 / num2; break;
         }
         
         txtRes.setText(res + "");
    }
   
    public static void main(String[] args) {
        CalculadoraGrafica cg = new CalculadoraGrafica();
        cg.setVisible(true);
    }
}