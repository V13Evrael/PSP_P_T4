package es.studium.ClienteFTPControlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
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
	String elementoSeleccionado = "";

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

		cFTPv.getBtnSalir().addActionListener(this);

		cFTPv.getBtnCreateDir().addActionListener(this);

		cFTPv.getBtnDeleteDir().addActionListener(this);

		cFTPv.getBtnUpFile().addActionListener(this);

		cFTPv.getBtnDownloadFile().addActionListener(this);

		cFTPv.getBtnDeleteFile().addActionListener(this);

		cFTPv.getBtnRenameFile().addActionListener(this);

		cFTPv.getBtnRenameDir().addActionListener(this);

	}

	// INICIO: Listener del Ratón
	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getClickCount() == 2) {

			String selected = cFTPv.getListaDirec().getSelectedValue();
			if (selected.startsWith("(DIR) ")) {
				directorioSeleccionado = selected.substring(6);
				System.err.println(listaDirectorios);
				System.err.println(rutaCompleta);
				if (listaDirectorios.size() != 1) {
					listaDirectorios.add("/");
				}
				listaDirectorios.add(directorioSeleccionado);
				rutaCompleta = Modelo.devuelveRuta(listaDirectorios);
				System.err.println(listaDirectorios);
				System.err.println(rutaCompleta);
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

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
	// FIN: Listeners del Ratón.

	// INICIO: Listeners de acciones:
	@Override
	public void actionPerformed(ActionEvent e) {

		// Si el botón pulsado es Atrás
		if (e.getSource().equals(cFTPv.getBtnGoBack())) {

			System.out.println("EL TOTAL DE ELEMENTOS EN LA LISTA ES:" + listaDirectorios.size());
			System.err.println(listaDirectorios);
			System.err.println(rutaCompleta);
			if (listaDirectorios.size() != 1) {
				if(listaDirectorios.size() == 2) {
					directorioSeleccionado = listaDirectorios.get(listaDirectorios.size() - 2);
					
				}
				else {
					directorioSeleccionado = listaDirectorios.get(listaDirectorios.size() - 3);	
				}
				System.err.println(directorioSeleccionado);
				
				rutaCompleta = Modelo.subeEnRuta(listaDirectorios);
				System.err.println(listaDirectorios);
				System.err.println(rutaCompleta);

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

		// Si el botón pulsado es Salir
		if (e.getSource().equals(cFTPv.getBtnSalir())) {
			try {
				cliente.disconnect();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		}

		// Si el botón pulsado es Crear carpeta
		if (e.getSource().equals(cFTPv.getBtnCreateDir())) {
			String nombreCarpeta = JOptionPane.showInputDialog(null, "Introduce el nombre del directorio", "Nueva Carpeta");
			if (!(nombreCarpeta == null)) {
				String directorio = directorioSeleccionado;
				if (!directorioSeleccionado.equals("/"))
					directorio = rutaCompleta + "/";
				// nombre del directorio a crear
				directorio += nombreCarpeta.trim();
				// quita blancos a derecha y a izquierda
				try {
					System.err.println("Prueba " + directorio);
					System.err.println("Directorio seleccionado " + directorioSeleccionado);
					if (cliente.makeDirectory(directorio)) {
						String m = nombreCarpeta.trim() + " => Se ha creado correctamente ...";
						JOptionPane.showMessageDialog(null, m);
						cFTPv.getLblMensajeVar().setText(m);
						// llenar la lista
						Modelo.llenarLista(cliente, cFTPv.getListaDirec());
					} else
						JOptionPane.showMessageDialog(null, nombreCarpeta.trim() + " => No se ha podido crear ...");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		// Si el botón pulsado es Subir Fichero
		if (e.getSource().equals(cFTPv.getBtnUpFile())) {

			Modelo.SubirFichero(cliente, cFTPv);
		} // Fin de la acción: pulsar botón Subir Fichero.

		// Si el botón pulsado es Descargar Fichero
		if (e.getSource().equals(cFTPv.getBtnDownloadFile())) {

			if (!elementoSeleccionado.startsWith("(DIR)")) {
				Modelo.DescargarFichero(cliente, listaDirectorios, elementoSeleccionado);
			} else {
				JOptionPane.showMessageDialog(cFTPv, "El elemento seleccionado no es un archivo válido");
			}
		} // Fin de la acción: pulsar botón Descargar Fichero

		// Si el botón pulsado es Eliminar Fichero
		if (e.getSource().equals(cFTPv.getBtnDeleteFile())) {
			if (!elementoSeleccionado.startsWith("(DIR)") & !elementoSeleccionado.equals("")) {
				int seleccion = JOptionPane.showConfirmDialog(cFTPv, "¿Desea eliminar el fichero seleccionado?");
				if (seleccion == JOptionPane.OK_OPTION) {

					try {
						Modelo.borrarFichero(cliente, listaDirectorios, elementoSeleccionado);
						elementoSeleccionado = "";
						Modelo.llenarLista(cliente, cFTPv.getListaDirec());
					} catch (IOException e1) {
					}
				}
			} else {
				JOptionPane.showMessageDialog(cFTPv,
						"No se seleccionó ningún elemento o el elemento seleccionado no es un archivo válido");
			}
		} // Fin de la acción: pulsar botón Eliminar Fichero

		// Si el botón pulsado es Eliminar Directorio
		if (e.getSource().equals(cFTPv.getBtnDeleteDir())) {
			if (elementoSeleccionado.startsWith("(DIR)")) {
				int seleccion = JOptionPane.showConfirmDialog(cFTPv, "¿Desea eliminar el directorio seleccionada?");
				if (seleccion == JOptionPane.OK_OPTION) {

					try {
						Modelo.borrarDirectorio(cliente, listaDirectorios, elementoSeleccionado.substring(6));
						elementoSeleccionado = "";
						Modelo.llenarLista(cliente, cFTPv.getListaDirec());
					} catch (IOException e1) {
					}
				}
			} else {
				JOptionPane.showMessageDialog(cFTPv,
						"No se seleccionó ningún elemento o el elemento seleccionado no es un directorio válido");
			}
		} // Fin de la acción: pulsar botón Eliminar Directorio

		// Si el botón pulsado es Renombrar Fichero
		if (e.getSource().equals(cFTPv.getBtnRenameFile())) {
			if (!elementoSeleccionado.startsWith("(DIR)") & !elementoSeleccionado.equals("")) {
				String nombreNuevo = elementoSeleccionado;
				try {
					nombreNuevo = JOptionPane.showInputDialog(cFTPv,
							"Introduzca el nuevo nombre para el fichero:\n" + elementoSeleccionado
									+ "\n(Recuerde introducir la extensión del archivo)",
							"Cambiar nombre de fichero", JOptionPane.QUESTION_MESSAGE);
					if (JOptionPane.showConfirmDialog(cFTPv,
							"Se cambiará el nombre actual del fichero a: " + nombreNuevo + "\n¿Está seguro/a?",
							"Confirmación", JOptionPane.YES_NO_OPTION) == 0) {
						if (directorioSeleccionado.equals("/")) {
							cliente.rename(rutaCompleta + elementoSeleccionado, nombreNuevo);
						} else {
							cliente.rename(rutaCompleta + "/" + elementoSeleccionado, nombreNuevo);
						}
					}
					Modelo.llenarLista(cliente, cFTPv.getListaDirec());
				} catch (Exception exc) {
				}
			} else {
				JOptionPane.showMessageDialog(cFTPv,
						"No se seleccionó ningún elemento o el elemento seleccionado no es un archivo válido");
			}
		} // Fin de la acción: pulsar botón Renombrar Archivo

		// Si el botón pulsado es Renombrar carpeta
		if (e.getSource().equals(cFTPv.getBtnRenameDir())) {
			if (elementoSeleccionado.startsWith("(DIR)")) {
				String nombreNuevo = elementoSeleccionado.substring(6);
				try {
					nombreNuevo = JOptionPane.showInputDialog(cFTPv,
							"Introduzca el nuevo nombre para el directorio:\n" + elementoSeleccionado.substring(6),
							"Cambiar nombre de directorio", JOptionPane.QUESTION_MESSAGE);
					if (JOptionPane.showConfirmDialog(cFTPv,
							"Se cambiará el nombre actual del directorio a: " + nombreNuevo + "\n¿Está seguro/a?",
							"Confirmación", JOptionPane.YES_NO_OPTION) == 0) {
						if (directorioSeleccionado.equals("/")) {
							cliente.rename(rutaCompleta + elementoSeleccionado.substring(6), nombreNuevo);
						} else {
							cliente.rename(rutaCompleta + "/" + elementoSeleccionado.substring(6), nombreNuevo);
						}
					}
					Modelo.llenarLista(cliente, cFTPv.getListaDirec());
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(cFTPv,
						"No se seleccionó ningún elemento o el elemento seleccionado no es un directorio válido");
			}
		} // Fin de la acción: pulsar botón
	}

	// FIN: Listener de acciones.

	// INICIO: Listener de listas:
	@Override
	public void valueChanged(ListSelectionEvent lse) {
		if (lse.getSource().equals(cFTPv.getListaDirec())) {

			String fic = "";
			if (lse.getValueIsAdjusting()) {

				fic = cFTPv.getListaDirec().getSelectedValue().toString();
				elementoSeleccionado = cFTPv.getListaDirec().getSelectedValue().toString();
				String elem = "";

				if (fic.startsWith("(DIR)")) {
					elem = "directorio";
				} else {
					elem = "fichero";

				}
				String resultado = Modelo.recortaCadena("Seleccionado el " + elem + ": " + elementoSeleccionado);
				resultado = "<html> " + resultado + " </html>";
				cFTPv.getLblMensajeVar().setText(resultado);
			}
		}
	}
	// FIN: LIstener de listas.

}
