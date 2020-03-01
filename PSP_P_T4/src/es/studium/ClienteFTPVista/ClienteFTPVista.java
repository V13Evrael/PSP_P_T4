package es.studium.ClienteFTPVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ClienteFTPVista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	// Lista para los datos del directorio
	private JList<String> listaDirec = new JList<String>();
	private JButton btnUpFile;
	private JButton btnRenameFile;
	private JButton btnDownloadFile;
	private JButton btnDeleteFile;
	private JButton btnCreateDir;

	private JButton btnDeleteDir;
	private JButton btnRenameDir;
	private JButton btnSalir;
	private JLabel lblVarServer;
	private JLabel lblVarUser;
	private JLabel lblVarRaiz;
	private JLabel lblVarDirActual;
	private JLabel lblMensajeVar;
	private JButton btnGoBack;

	public ClienteFTPVista() {
		setTitle("ClienteFTP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 955, 444);
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
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		btnGoBack = new JButton("Atr\u00E1s");
		btnGoBack.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(btnGoBack, BorderLayout.WEST);
		
		JPanel pnlDerecha = new JPanel();
		contentPane.add(pnlDerecha, BorderLayout.EAST);
		pnlDerecha.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlDerTop = new JPanel();
		pnlDerecha.add(pnlDerTop, BorderLayout.NORTH);
		pnlDerTop.setLayout(new GridLayout(4, 2, 0, 0));
		
		btnUpFile = new JButton("Subir fichero");
		JPanel pnlBtn = new JPanel(new BorderLayout());
		pnlBtn.add(btnUpFile);
		pnlBtn.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn);
		
		btnCreateDir = new JButton("Crear carpeta");
		JPanel pnlBtn4 = new JPanel(new BorderLayout());
		pnlBtn4.add(btnCreateDir);
		pnlBtn4.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn4);
		
		btnDownloadFile = new JButton("Descargar fichero");
		JPanel pnlBtn2 = new JPanel(new BorderLayout());
		pnlBtn2.add(btnDownloadFile);
		pnlBtn2.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn2);
		
		btnRenameDir = new JButton("Renombrar carpeta");
		JPanel pnlBtn6 = new JPanel(new BorderLayout());
		pnlBtn6.add(btnRenameDir);
		pnlBtn6.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn6);
		
		btnRenameFile = new JButton("Renombrar fichero");
		JPanel pnlBtn1 = new JPanel(new BorderLayout());
		pnlBtn1.add(btnRenameFile);
		pnlBtn1.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn1);
		
		btnDeleteDir = new JButton("Eliminar carpeta");
		JPanel pnlBtn5 = new JPanel(new BorderLayout());
		pnlBtn5.add(btnDeleteDir);
		pnlBtn5.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn5);
		
		btnDeleteFile = new JButton("Eliminar fichero");
		JPanel pnlBtn3 = new JPanel(new BorderLayout());
		pnlBtn3.add(btnDeleteFile);
		pnlBtn3.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn3);
		
		btnSalir = new JButton("Salir");
		JPanel pnlBtn7 = new JPanel(new BorderLayout());
		pnlBtn7.add(btnSalir);
		pnlBtn7.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn7);
		
		JPanel pnlDerCenter = new JPanel();
		pnlDerecha.add(pnlDerCenter, BorderLayout.CENTER);
		pnlDerCenter.setLayout(new GridLayout(4, 1, 0, 15));
		
		JPanel panel_2 = new JPanel();
		pnlDerCenter.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblServidor = new JLabel("Servidor FTP:");
		lblServidor.setVerticalAlignment(SwingConstants.BOTTOM);
		lblServidor.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_2.add(lblServidor, BorderLayout.NORTH);
		lblServidor.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblVarServer = new JLabel("");
		lblVarServer.setVerticalAlignment(SwingConstants.TOP);
		lblVarServer.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblVarServer.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblVarServer);
		
		JPanel panel_4 = new JPanel();
		pnlDerCenter.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblUser = new JLabel("Usuario:");
		lblUser.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_4.add(lblUser, BorderLayout.NORTH);
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblVarUser = new JLabel("");
		lblVarUser.setVerticalAlignment(SwingConstants.TOP);
		lblVarUser.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblVarUser.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblVarUser);
		
		JPanel panel_6 = new JPanel();
		pnlDerCenter.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lblRaiz = new JLabel("Directorio ra\u00EDz:");
		lblRaiz.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRaiz.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_6.add(lblRaiz, BorderLayout.NORTH);
		lblRaiz.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblVarRaiz = new JLabel("");
		lblVarRaiz.setVerticalAlignment(SwingConstants.TOP);
		lblVarRaiz.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblVarRaiz.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblVarRaiz);
		
		JPanel panel_8 = new JPanel();
		pnlDerCenter.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDirActual = new JLabel("Directorio actual:");
		lblDirActual.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDirActual.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_8.add(lblDirActual, BorderLayout.NORTH);
		lblDirActual.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblVarDirActual = new JLabel("");
		lblVarDirActual.setVerticalAlignment(SwingConstants.TOP);
		lblVarDirActual.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblVarDirActual.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(lblVarDirActual);
		
		JPanel pnlDerBot = new JPanel();
		pnlDerecha.add(pnlDerBot, BorderLayout.SOUTH);
		pnlDerBot.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel = new JPanel();
		pnlDerBot.add(panel);
		
		JLabel lblMensaje = new JLabel("Mensaje: ");
		lblMensaje.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblMensaje);
		lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(2, 2, 2, 2));
		pnlDerBot.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		lblMensajeVar = new JLabel("<<\u00C1rbol de directorios construido>>");
		lblMensajeVar.setBorder(new EmptyBorder(2, 2, 2, 2));
		lblMensajeVar.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panel_1.add(lblMensajeVar);
		lblMensajeVar.setHorizontalAlignment(SwingConstants.CENTER);

	}
	
	public JButton getBtnGoBack() {
		return btnGoBack;
	}

	public JList<String> getListaDirec() {
		return listaDirec;
	}

	public JButton getBtnUpFile() {
		return btnUpFile;
	}

	public JButton getBtnRenameFile() {
		return btnRenameFile;
	}

	public JButton getBtnDownloadFile() {
		return btnDownloadFile;
	}

	public JButton getBtnDeleteFile() {
		return btnDeleteFile;
	}

	public JButton getBtnCreateDir() {
		return btnCreateDir;
	}

	public JButton getBtnDeleteDir() {
		return btnDeleteDir;
	}

	public JButton getBtnRenameDir() {
		return btnRenameDir;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public JLabel getLblVarServer() {
		return lblVarServer;
	}

	public JLabel getLblVarUser() {
		return lblVarUser;
	}

	public JLabel getLblVarRaiz() {
		return lblVarRaiz;
	}

	public JLabel getLblVarDirActual() {
		return lblVarDirActual;
	}

	public JLabel getLblMensajeVar() {
		return lblMensajeVar;
	}
}
