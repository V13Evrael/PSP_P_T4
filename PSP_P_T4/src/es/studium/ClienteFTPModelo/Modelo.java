package es.studium.ClienteFTPModelo;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class Modelo {
	
	public static String devuelveRuta(ArrayList<String> lista) {
		String resultado = "";
		
		try {
			for (String elem :lista) {
				resultado += elem;
			}
		} catch (Exception e) {}
		
		return resultado;
	}
	
	public static String subeEnRuta(ArrayList<String> lista) {
		
		String resultado ="";
		
		lista.remove(lista.size()-1);
		lista.remove(lista.size()-1);
		
		for (String elem :lista) {
			resultado += elem;
		}
		
		return resultado;
	}
	
	public static void llenarLista(FTPClient cliente, JList<String> listaDirec) throws IOException 
	{
		
		FTPFile[] files = cliente.listFiles();
		
		if (files == null)
			return;
		//se crea un objeto DefaultListModel
		DefaultListModel<String> modeloLista = new DefaultListModel<String>();
		modeloLista = new DefaultListModel<String>();
		//se definen propiedades para la lista, color y tipo de fuente

		listaDirec.setForeground(Color.blue);
		Font fuente = new Font("Courier", Font.PLAIN, 12);
		listaDirec.setFont(fuente);
		//se eliminan los elementos de la lista
		listaDirec.removeAll();
		
		//se añade el directorio de trabajo al listmodel, primerelementomodeloLista.addElement(direc2);
		//se recorre el array con los ficheros y directorios
		for (int i = 0; i < files.length; i++) 
		{
			if (!(files[i].getName()).equals(".") && !(files[i].getName()).equals("..")) 
			{
				//nos saltamos los directorios . y ..
				//Se obtiene el nombre del fichero o directorio
				String f = files[i].getName();
				//Si es directorio se añade al nombre (DIR)
				if (files[i].isDirectory()) f = "(DIR) " + f;
				//se añade el nombre del fichero o directorio al listmodel
				modeloLista.addElement(f);
			}//fin if
		}//fin for
		
		listaDirec.setModel(modeloLista);
		
	}//Fin llenarLista
}
