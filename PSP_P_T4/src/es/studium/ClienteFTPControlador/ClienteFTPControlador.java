package es.studium.ClienteFTPControlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;

import es.studium.ClienteFTPModelo.Modelo;
import es.studium.ClienteFTPVista.ClienteFTPVista;

public class ClienteFTPControlador implements ListSelectionListener, ActionListener, MouseListener {

	public ClienteFTPVista cFTPv;

	// Datos de acceso al servidor:
	FTPClient cliente = new FTPClient();
	String server = "127.0.0.1";
	String user = "jmplatero";
	String pasw = "Studium2018;";
	boolean login;
	String directorioInicial = "/";
	String rutaCompleta = directorioInicial;
	ArrayList<String> listaDirectorios = new ArrayList<String>();
	String directorioSeleccionado = directorioInicial;
	String ficheroSelec = "";

	public ClienteFTPControlador() {

		cFTPv = new ClienteFTPVista();
		cliente.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

		try {
			cliente.connect(server);
			cliente.enterLocalPassiveMode();
			login = cliente.login(user, pasw);
			cliente.changeWorkingDirectory(directorioInicial);
			listaDirectorios.add(directorioInicial);
			Modelo.llenarLista(cliente, cFTPv.getListaDirec());

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		cFTPv.getLblVarServer().setText(server);
		cFTPv.getLblVarUser().setText(user);
		cFTPv.getLblVarRaiz().setText(directorioInicial);
		cFTPv.getLblVarDirActual().setText(directorioSeleccionado);

		// Añadimos los listeners
		cFTPv.getListaDirec().addListSelectionListener(this);
		cFTPv.getListaDirec().addMouseListener(this);

		cFTPv.getBtnGoBack().addActionListener(this);

	}

	// INICIO: Listener del Ratón
	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getClickCount() == 2) {

			String selected = cFTPv.getListaDirec().getSelectedValue();
			if (selected.startsWith("(DIR) ")) {
				directorioSeleccionado = selected.substring(6, selected.length());
				listaDirectorios.add("/");
				listaDirectorios.add(directorioSeleccionado);
				rutaCompleta = Modelo.devuelveRuta(listaDirectorios);

				try {
					cliente.changeWorkingDirectory(rutaCompleta);
					Modelo.llenarLista(cliente, cFTPv.getListaDirec());
					cFTPv.getLblVarDirActual().setText(directorioSeleccionado);
					cFTPv.getLblMensajeVar().setText("Se ha accedido al Directorio Seleccionado");

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	// FIN: Listeners del Ratón.

	// INICIO: Listeners de acciones:
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(cFTPv.getBtnGoBack())) {

			System.out.println("EL TOTAL DE ELEMENTOS EN LA LISTA ES:" + listaDirectorios.size());
			if (listaDirectorios.size() != 1) {
				directorioSeleccionado = listaDirectorios.get(listaDirectorios.size() - 3);
				rutaCompleta = Modelo.subeEnRuta(listaDirectorios);

				try {
					cliente.changeWorkingDirectory(rutaCompleta);
					Modelo.llenarLista(cliente, cFTPv.getListaDirec());
					cFTPv.getLblVarDirActual().setText(directorioSeleccionado);
					cFTPv.getLblMensajeVar().setText("Se ha accedido al Directorio Seleccionado");

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				cFTPv.getLblMensajeVar().setText("Ya se encuentra en el directorio raíz");
			}

		}

	}
	// FIN: Listener de acciones.

	// INICIO: Listener de listas:
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}
	// FIN: LIstener de listas.

}
