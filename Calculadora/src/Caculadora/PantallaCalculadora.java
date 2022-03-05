package Caculadora;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jvnet.substance.SubstanceDefaultLookAndFeel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Toolkit;

import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

	public class PantallaCalculadora extends JFrame{
		
		
	private static final long serialVersionUID = 1L;
	private JTextField pantalla;
	private JPanel contentPane;
	private double num1,num2=0,resutaldo;
	private char ultimosigno='0';
	Teclado tecla = new Teclado();

	
	

	public Image getIconImage() {
		Image retValue = Toolkit.getDefaultToolkit().
		         getImage(ClassLoader.getSystemResource("Caculadora/iconocalculadora.png"));


		   return retValue;
	}
	
	public PantallaCalculadora() {
		
		contentPane=new JPanel();
		setDefaultLookAndFeelDecorated(true);
		SubstanceDefaultLookAndFeel.setSkin("org.jvnet.substance.skin.RavenGraphiteSkin");
		setIconImage(getIconImage());
		setResizable(false);
		setTitle("Calculadora");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 367, 299);
		setLocationRelativeTo(null);
		pantalla = new JTextField();
		pantalla.setBorder(BorderFactory.createEmptyBorder());
		pantalla.setFocusable(false);
		pantalla.setHorizontalAlignment(JTextField.RIGHT);
		pantalla.setEditable(false);
		pantalla.setFont(new Font("Arial Black", Font.BOLD, 20));
		pantalla.setBounds(10, 11, 330, 69);
		contentPane.add(pantalla);
		//pantalla.setColumns(0);
		//-----------------------------------------------
		contentPane.setLayout(null);
		//Creacion de botones
		BotonesNumericos("0", 10, 227, 160, 23);
		BotonesNumericos("1", 10, 193, 75, 23);
		BotonesNumericos("4", 10, 159, 75, 23);
		BotonesNumericos("7", 10, 125, 75, 23);
		BotonesNumericos("2", 95, 193, 75, 23);
		BotonesNumericos("3", 180, 193, 75, 23);
		BotonesNumericos("5", 95, 159, 75, 23);
		BotonesNumericos("6", 180, 159, 75, 23);
		BotonesNumericos("8", 95, 125, 75, 23);
		BotonesNumericos("9", 180, 125, 75, 23);
		
		
		JButton btnlimpiar = new JButton("C");
		btnlimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantalla.setText("");
				resutaldo=0;
				ultimosigno='0';
			}
		});
		btnlimpiar.setFocusable(false);
		btnlimpiar.setBounds(10, 91, 75, 23);
		contentPane.add(btnlimpiar);
		
	
		JButton buttonpunto = new JButton(".");
		buttonpunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean resultado =false;
				String cadena=pantalla.getText();
				for (int i = 0; i < cadena.length(); i++) {
					if (cadena.substring(i, i+1).equals(".")) {
						resultado=true;
						break;
					}
				}
				if (!resultado) {
					pantalla.setText(pantalla.getText()+".");
				}
			}
		});
		buttonpunto.setBounds(180, 227, 75, 23);
		buttonpunto.addKeyListener(tecla);
		buttonpunto.setFocusable(false);
		contentPane.add(buttonpunto);
		BotonesOperaciones("-", 265, 159, 75, 23);
		BotonesOperaciones("/", 265, 91, 75, 23);
		BotonesOperaciones("X", 265, 125, 75, 23);
		BotonesOperaciones("+", 265, 193, 75, 23);
		btResultado();
		BotonesOperaciones("-", 265, 159, 75, 23);
		BotonesOperaciones("/", 265, 91, 75, 23);
		BotonesOperaciones("X", 265, 125, 75, 23);
		BotonesOperaciones("^", 180, 91, 75, 23);
		
		JButton btnCe = new JButton("CE");
		btnCe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cadena=pantalla.getText();
				
				if (cadena.length()>0) {
					cadena=cadena.substring(0, cadena.length()-1);
					pantalla.setText(cadena);
				}
			}
		});
		btnCe.setBounds(95, 91, 75, 23);
		btnCe.setFocusable(false);
		contentPane.add(btnCe);
		
		getContentPane().add(contentPane,BorderLayout.CENTER);
	//	add(panel2,BorderLayout.NORTH);
		addKeyListener(tecla);
	    setFocusable(true);
	    
	}
	public static boolean existepunto(String cadena) {
		boolean resultado =false;
		for (int i = 0; i < cadena.length(); i++) {
			if (cadena.substring(i, i+1).equals(".")) {
				resultado=true;
				break;
			}
		}
		
		return resultado;
	}
	
	public JButton btResultado() {
		JButton bt = new JButton("=");
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ResultadoFinal();
			}
		});
		bt.setBounds(265, 227, 75, 23);
		bt.addKeyListener(tecla);
		contentPane.add(bt);
		return bt;
	}
	public JButton BotonesNumericos(String nombre,int x,int y,int width,int height) {

		JButton bt = new JButton(nombre);
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (ultimosigno=='=') {
					pantalla.setText("");
					ultimosigno='0';
				
				}
				pantalla.setText(pantalla.getText()+nombre);
				
			}
		});
		
		bt.setBounds(x, y, width, height);
		bt.addKeyListener(tecla);
		bt.setFocusable(false);
		contentPane.add(bt);
		
			return bt;
	}
	
	public void ResultadoFinal() {
		switch (ultimosigno) {
		case '+': {
			String cadena =pantalla.getText();
			num2=Double.parseDouble(cadena);
			resutaldo+=num2; 
			pantalla.setText(String.valueOf(resutaldo));
			resutaldo=00;
			break;
		}
		case '-': {
			String cadena =pantalla.getText();
			num2=Double.parseDouble(cadena);
			resutaldo-=num2;
			pantalla.setText(String.valueOf(resutaldo));
			resutaldo=00;
			break;
		}
		case '/': {
			String cadena =pantalla.getText();
			num2=Double.parseDouble(cadena);
			resutaldo/=num2;
			pantalla.setText(String.valueOf(resutaldo));
			resutaldo=00;
			break;
		}
		case '^':{
			String cadena = pantalla.getText();
			num1 = Double.parseDouble(cadena);
			//resutaldo*=num1;
			resutaldo =	Math.pow(resutaldo, num1);
			pantalla.setText(String.valueOf(resutaldo));
			break;
		}
		case 'X' : 
		case '*' : 
			String cadena =pantalla.getText();
			num2=Double.parseDouble(cadena);
			resutaldo*=num2;
			pantalla.setText(String.valueOf(resutaldo));
			resutaldo=00;
			break;
		default: System.out.println("Error");
		}
		
		
		ultimosigno='=';
	}
	
	public JButton BotonesOperaciones(String nombre,int x,int y,int width,int height) {
		JButton bt = new JButton(nombre);
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if (!pantalla.getText().equals("")) {
					
					if (ultimosigno == '-') {
						String cadena = pantalla.getText();
						num1= Double.parseDouble(cadena);
						resutaldo-=num1;
						pantalla.setText("");
					}
					else if (ultimosigno == '+') {
						String cadena = pantalla.getText();
						num1= Double.parseDouble(cadena);
						resutaldo+=num1;
						pantalla.setText("");
					}
					else if (ultimosigno == '/') {
						String cadena = pantalla.getText();
						num1= Double.parseDouble(cadena);
						resutaldo/=num1;
						pantalla.setText("");
					}
					else if (ultimosigno == 'X'||ultimosigno == '*') {
						String cadena = pantalla.getText();
						num1= Double.parseDouble(cadena);
						resutaldo*=num1;
						pantalla.setText("");
					}
					else if (ultimosigno == '^') {
						String cadena = pantalla.getText();
						num1= Double.parseDouble(cadena);
						//resutaldo*=num1;
						resutaldo =	Math.pow(resutaldo, num1);
						pantalla.setText("");
					}
					else {
					String cadena = pantalla.getText();
					num1= Double.parseDouble(cadena);
					resutaldo=num1;
					pantalla.setText("");
					
					}
				}
				ultimosigno= nombre.charAt(0);
			}
		});
		bt.setBounds(x, y, width, height);
		bt.addKeyListener(tecla);
		bt.setFocusable(false);
		contentPane.add(bt);
			
			return bt;
	}
	
	class Teclado implements KeyListener {

		public void keyTyped(KeyEvent e) {
			
			
		}

		public void keyPressed(KeyEvent e) {
			//System.out.println(e.getKeyCode());
			if (e.getKeyCode()==8) {
				String cadena=pantalla.getText();
				if (cadena.length()>0) {
					cadena=cadena.substring(0, cadena.length()-1);
					pantalla.setText(cadena);
				}
			}else if (e.getKeyCode()==10) {
				ResultadoFinal();
			}else if (e.getKeyCode()==127) {
				pantalla.setText("");
				resutaldo=0;
				//ultimosigno='0';
			}
			
			
			switch (e.getKeyChar()) {
			case '1':
			case '2': 
			case '3': 
			case '4': 
			case '5': 
			case '6': 
			case '7': 
			case '8': 
			case '9': 
			case '0': 
				if (ultimosigno=='=') {
					pantalla.setText("");
					ultimosigno='0';
				
				}
		    pantalla.setText(pantalla.getText()+e.getKeyChar());
				break;
				
			case '.':
				boolean resultado =false;
				String a=pantalla.getText();
				for (int i = 0; i < a.length(); i++) {
					if (a.substring(i, i+1).equals(".")) {
						resultado=true;
						break;
					}
				}
				if (!resultado) {
					pantalla.setText(pantalla.getText()+".");
				}
				break;
			case '+':
			case '-':
			case '*':
			case '/':
					if (!pantalla.getText().equals("")) {
					
					if (ultimosigno == '-') {
						String cadena = pantalla.getText();
						num1= Double.parseDouble(cadena);
						resutaldo-=num1;
						pantalla.setText("");
					}
					else if (ultimosigno == '+') {
						String cadena = pantalla.getText();
						num1= Double.parseDouble(cadena);
						resutaldo+=num1;
						pantalla.setText("");
					}
					else if (ultimosigno == '/') {
						String cadena = pantalla.getText();
						num1= Double.parseDouble(cadena);
						resutaldo/=num1;
						pantalla.setText("");
					}
					else if (ultimosigno == 'X'||ultimosigno == '*') {
						String cadena = pantalla.getText();
						num1= Double.parseDouble(cadena);
						resutaldo*=num1;
						pantalla.setText("");
					}
					else {
					String cadena = pantalla.getText();
					num1= Double.parseDouble(cadena);
					resutaldo=num1;
					pantalla.setText("");
					
					}
				}
				ultimosigno= e.getKeyChar();	
				break;
			default:
			}
			
			
		//System.out.println(e.getKeyCode());
			
		}

		public void keyReleased(KeyEvent e) {
			
			
		}

	}
      }
	 
