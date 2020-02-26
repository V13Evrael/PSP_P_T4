package es.studium.ClienteFTPControlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import es.studium.ClienteFTPModelo.Modelo;
import es.studium.ClienteFTPVista.ClienteFTPVista;

public class ClienteFTPControlador implements ListSelectionListener, ActionListener, MouseListener{
	
	ClienteFTPVista cFTPv;
	
	//Datos de acceso al servidor:
	static FTPClient cliente = new FTPClient();
	static String server = "127.0.0.1";
	static String user = "jmplatero";
	static String pasw = "Studium2018;";
	boolean login;
	static String directorioInicial = "/";
	
	static String directorioSeleccionado = directorioInicial;
	static String ficheroSelec = "";
	
	public ClienteFTPControlador() {
		
		cFTPv = new ClienteFTPVista();
		cliente.addProtocolCommandListener(new PrintCommandListener(new PrintWriter (System.out)));
		
		try {
			cliente.connect(server);
			cliente.enterLocalPassiveMode();
			login = cliente.login(user, pasw);
			cliente.changeWorkingDirectory(directorioInicial);
			Modelo.llenarLista(cliente, cFTPv.getListaDirec());
			
			
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	//INICIO: Listener del Ratón
	@Override
	public void mouseClicked(MouseEvent e) {

		
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
	//FIN: Listeners del Ratón.
	
	//INICIO: Listeners de acciones:
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	//FIN: Listener de acciones.
	
	//INICIO: Listener de listas:
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
	//FIN: LIstener de listas.

}
