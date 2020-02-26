package es.studium.ClienteFTPVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ClienteFTPVista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	// Lista para los datos del directorio
	JList<String> listaDirec = new JList<String>();

	public ClienteFTPVista() {
		setTitle("ClienteFTP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 752, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		LineBorder bordeGrid = new LineBorder(new Color(0, 102, 153), 2);

		listaDirec.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//barra de desplazamiento para la lista
		JScrollPane barraDesplazamiento = new JScrollPane(listaDirec);
		barraDesplazamiento.setBorder(bordeGrid);
		contentPane.add(barraDesplazamiento, BorderLayout.CENTER);
		
		JPanel pnlDerecha = new JPanel();
		contentPane.add(pnlDerecha, BorderLayout.EAST);
		pnlDerecha.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlDerTop = new JPanel();
		pnlDerecha.add(pnlDerTop, BorderLayout.NORTH);
		pnlDerTop.setLayout(new GridLayout(4, 2, 0, 0));
		
		JButton btnNewButton = new JButton("Subir fichero");
		JPanel pnlBtn = new JPanel(new BorderLayout());
		pnlBtn.add(btnNewButton);
		pnlBtn.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn);
		
		JButton btnNewButton_1 = new JButton("Renombrar fichero");
		JPanel pnlBtn1 = new JPanel(new BorderLayout());
		pnlBtn1.add(btnNewButton_1);
		pnlBtn1.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn1);
		
		JButton btnNewButton_2 = new JButton("Descargar fichero");
		JPanel pnlBtn2 = new JPanel(new BorderLayout());
		pnlBtn2.add(btnNewButton_2);
		pnlBtn2.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn2);
		
		JButton btnNewButton_3 = new JButton("Eliminar fichero");
		JPanel pnlBtn3 = new JPanel(new BorderLayout());
		pnlBtn3.add(btnNewButton_3);
		pnlBtn3.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn3);
		
		JButton btnNewButton_4 = new JButton("Crear carpeta");
		JPanel pnlBtn4 = new JPanel(new BorderLayout());
		pnlBtn4.add(btnNewButton_4);
		pnlBtn4.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn4);
		
		JButton btnNewButton_5 = new JButton("Eliminar carpeta");
		JPanel pnlBtn5 = new JPanel(new BorderLayout());
		pnlBtn5.add(btnNewButton_5);
		pnlBtn5.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn5);
		
		JButton btnNewButton_6 = new JButton("Renombrar carpeta");
		JPanel pnlBtn6 = new JPanel(new BorderLayout());
		pnlBtn6.add(btnNewButton_6);
		pnlBtn6.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn6);
		
		JButton btnNewButton_7 = new JButton("Salir");
		JPanel pnlBtn7 = new JPanel(new BorderLayout());
		pnlBtn7.add(btnNewButton_7);
		pnlBtn7.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn7);
		
		JPanel pnlDerCenter = new JPanel();
		pnlDerecha.add(pnlDerCenter, BorderLayout.CENTER);

	}
	
	public static void main(String[] args) {
		
		new ClienteFTPVista().setVisible(true);
	}
	
	public static JPanel putOnPanel(JComponent c) {
		
		JPanel result = new JPanel(new FlowLayout());
		result.add(c);
		return result;
	}

}
