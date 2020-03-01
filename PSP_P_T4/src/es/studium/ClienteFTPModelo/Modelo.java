package es.studium.ClienteFTPModelo;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import es.studium.ClienteFTPVista.ClienteFTPVista;

public class Modelo {

	public static String devuelveRuta(ArrayList<String> lista) {
		String resultado = "";

		try {
			for (String elem : lista) {
				resultado += elem;
			}
		} catch (Exception e) {
		}

		return resultado;
	}

	public static String subeEnRuta(ArrayList<String> lista) {

		String resultado = "";

		if (lista.size() != 1) {
			if(lista.size() == 2) {
				lista.remove(lista.size() - 1);			
				}
			else {
				lista.remove(lista.size() - 1);
				lista.remove(lista.size() - 1);			
				}
		}
		

		for (String elem : lista) {
			resultado += elem;
		}

		return resultado;
	}

	// Este método nos sirve para recortar una línea de texto demasiado larga
	// respetando los espacios
	public static String recortaCadena(String cadenaInicial) {

		String cadenaResultado = "";
		Integer indiceEspacio = -1;

		if (cadenaInicial.length() > 30) {
			indiceEspacio = 30 + cadenaInicial.substring(30).indexOf(" ");
			if (indiceEspacio != -1) {
				cadenaResultado += cadenaInicial.substring(0, indiceEspacio + 1) + "<br/>"
						+ recortaCadena(cadenaInicial.substring(indiceEspacio));
			} else {
				cadenaResultado = cadenaInicial;
			}
		} else {
			cadenaResultado = cadenaInicial;
		}

		return cadenaResultado;
	}

	public static void llenarLista(FTPClient cliente, JList<String> listaDirec) throws IOException {

		FTPFile[] files = cliente.listFiles();

		if (files == null)
			return;
		// se crea un objeto DefaultListModel
		DefaultListModel<String> modeloLista = new DefaultListModel<String>();
		modeloLista = new DefaultListModel<String>();
		// se definen propiedades para la lista, color y tipo de fuente

		listaDirec.setForeground(Color.blue);
		Font fuente = new Font("Courier", Font.PLAIN, 12);
		listaDirec.setFont(fuente);
		// se eliminan los elementos de la lista
		listaDirec.removeAll();

		// se añade el directorio de trabajo al listmodel,
		// primerelementomodeloLista.addElement(direc2);
		// se recorre el array con los ficheros y directorios
		for (int i = 0; i < files.length; i++) {
			if (!(files[i].getName()).equals(".") && !(files[i].getName()).equals("..")) {
				// nos saltamos los directorios . y ..
				// Se obtiene el nombre del fichero o directorio
				String f = files[i].getName();
				// Si es directorio se añade al nombre (DIR)
				if (files[i].isDirectory())
					f = "(DIR) " + f;
				// se añade el nombre del fichero o directorio al listmodel
				modeloLista.addElement(f);
			} // fin if
		} // fin for

		listaDirec.setModel(modeloLista);

	}// Fin llenarLista

	public static boolean SubirFichero(FTPClient cliente, ClienteFTPVista cFTPv) {

		boolean ok = false;
		JFileChooser f = new JFileChooser();
		File file;
		f.setFileSelectionMode(JFileChooser.FILES_ONLY);
		f.setDialogTitle("Selecciona el fichero a subir al servidor FTP");
		int returnVal = f.showDialog(f, "Cargar");

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = f.getSelectedFile();
			String rutaAbsolutaArchivo = file.getAbsolutePath();
			String nombreArchivo = file.getName();

			try {
				cliente.setFileType(FTP.BINARY_FILE_TYPE);
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(rutaAbsolutaArchivo));
				// directorio de trabajo actual
				if (cliente.storeFile(nombreArchivo, in)) {
					String s = " " + nombreArchivo + " => Subido correctamente...";
					JOptionPane.showMessageDialog(cFTPv, s);
					Modelo.llenarLista(cliente, cFTPv.getListaDirec());
					cFTPv.getLblMensajeVar().setText("<<Árbol de directorios actualizado>>");
					ok = true;
				} else {
					String s = "No se ha podido subir... " + nombreArchivo;
					JOptionPane.showMessageDialog(cFTPv, s);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		return ok;
	}
	// final de SubirFichero

	public static void DescargarFichero(FTPClient cliente, ArrayList<String> listaRutas, String nombreFichero) {

		String rutaCompleta = "";

		for (String elem : listaRutas) {
			rutaCompleta += elem;
		}
		rutaCompleta += "/" + nombreFichero;

		File file;
		String archivoyCarpetaDestino = "";
		String carpetaDestino = "";
		JFileChooser f = new JFileChooser();
		// solo se pueden seleccionar directorios
		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// título de la ventana
		f.setDialogTitle("Selecciona el Directorio donde Descargar el Fichero");
		int returnVal = f.showDialog(null, "Descargar");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = f.getSelectedFile();
			// obtener carpeta de destino
			carpetaDestino = (file.getAbsolutePath()).toString();
			// construimos el nombre completo que se creará en nuestro disco
			archivoyCarpetaDestino = carpetaDestino + File.separator + nombreFichero;
			try {
				cliente.setFileType(FTP.BINARY_FILE_TYPE);
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(archivoyCarpetaDestino));
				if (cliente.retrieveFile(rutaCompleta, out))
					JOptionPane.showMessageDialog(null, nombreFichero + " => Se ha descargado correctamente ...");
				else
					JOptionPane.showMessageDialog(null, nombreFichero + " => No se ha podido descargar ...");
				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	public static void borrarFichero(FTPClient cliente, ArrayList<String> listaRutas, String nombreFichero)
			throws HeadlessException, IOException {
		String rutaCompleta = "";

		for (String elem : listaRutas) {
			rutaCompleta += elem;
		}
		rutaCompleta += "/" + nombreFichero;

		if (cliente.deleteFile(rutaCompleta)) {
			String m = nombreFichero + " => Eliminado correctamente... ";
			JOptionPane.showMessageDialog(null, m);
			nombreFichero = "";
		} else
			JOptionPane.showMessageDialog(null, nombreFichero + " => No se ha podido eliminar ...");

	}

	public static void borrarDirectorio(FTPClient cliente, ArrayList<String> listaRutas, String nombreDirect) throws IOException {
		String rutaCompleta = "";
		
		for (String elem :listaRutas) {
			rutaCompleta +=elem;
		}
		rutaCompleta += "/" + nombreDirect;
		if (cliente.removeDirectory(rutaCompleta)) {
			String m = nombreDirect + " => Eliminado correctamente... ";
			JOptionPane.showMessageDialog(null, m);
			nombreDirect = "";		
			}
		else {
				JOptionPane.showMessageDialog(null, nombreDirect + " => No se ha podido eliminar. Compruebe que el directorio está vacío");
		}
	}
}
