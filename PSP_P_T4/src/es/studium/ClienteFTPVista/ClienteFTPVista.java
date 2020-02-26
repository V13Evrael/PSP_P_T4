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
		
		btnRenameFile = new JButton("Renombrar fichero");
		JPanel pnlBtn1 = new JPanel(new BorderLayout());
		pnlBtn1.add(btnRenameFile);
		pnlBtn1.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn1);
		
		btnDownloadFile = new JButton("Descargar fichero");
		JPanel pnlBtn2 = new JPanel(new BorderLayout());
		pnlBtn2.add(btnDownloadFile);
		pnlBtn2.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn2);
		
		btnDeleteFile = new JButton("Eliminar fichero");
		JPanel pnlBtn3 = new JPanel(new BorderLayout());
		pnlBtn3.add(btnDeleteFile);
		pnlBtn3.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn3);
		
		btnCreateDir = new JButton("Crear carpeta");
		JPanel pnlBtn4 = new JPanel(new BorderLayout());
		pnlBtn4.add(btnCreateDir);
		pnlBtn4.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn4);
		
		btnDeleteDir = new JButton("Eliminar carpeta");
		JPanel pnlBtn5 = new JPanel(new BorderLayout());
		pnlBtn5.add(btnDeleteDir);
		pnlBtn5.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn5);
		
		btnRenameDir = new JButton("Renombrar carpeta");
		JPanel pnlBtn6 = new JPanel(new BorderLayout());
		pnlBtn6.add(btnRenameDir);
		pnlBtn6.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn6);
		
		btnSalir = new JButton("Salir");
		JPanel pnlBtn7 = new JPanel(new BorderLayout());
		pnlBtn7.add(btnSalir);
		pnlBtn7.setBorder(bordeGrid);
		pnlDerTop.add(pnlBtn7);
		
		JPanel pnlDerCenter = new JPanel();
		pnlDerecha.add(pnlDerCenter, BorderLayout.CENTER);
		pnlDerCenter.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblServidor = new JLabel("Servidor: ");
		lblServidor.setHorizontalAlignment(SwingConstants.TRAILING);
		pnlDerCenter.add(lblServidor);
		
		lblVarServer = new JLabel("");
		pnlDerCenter.add(lblVarServer);
		
		JLabel lblUser = new JLabel("Usuario: ");
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlDerCenter.add(lblUser);
		
		lblVarUser = new JLabel("");
		pnlDerCenter.add(lblVarUser);
		
		JLabel lblRaiz = new JLabel("Directorio ra\u00EDz: ");
		lblRaiz.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlDerCenter.add(lblRaiz);
		
		lblVarRaiz = new JLabel("");
		pnlDerCenter.add(lblVarRaiz);
		
		JLabel lblDirActual = new JLabel("Directorio actual: ");
		lblDirActual.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlDerCenter.add(lblDirActual);
		
		lblVarDirActual = new JLabel("");
		pnlDerCenter.add(lblVarDirActual);
		
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
		pnlDerBot.add(panel_1);
		
		lblMensajeVar = new JLabel("<<\u00C1rbol de directorios construido>>");
		lblMensajeVar.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panel_1.add(lblMensajeVar);
		lblMensajeVar.setHorizontalAlignment(SwingConstants.CENTER);

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
