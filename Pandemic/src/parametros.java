import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class parametros {
	static int valor;

	// Lee el archivo .xml
	static String[] leerArchivo(File archivo) {

		// Variables
		String numCiudadesInfectadasInicio = "";
		String numCuidadesInfectadasRonda = "";
		String numEnfermedadesActivasDerrota = "";
		String numBrotesDerrota = "";
		String[] parametros = new String[4];

		try {
			// Cargamos DocumentBuilder y documentos
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(archivo);

			// Leemos parametro a parametro y lo guardamos en Strings
			numCiudadesInfectadasInicio = document.getElementsByTagName("numCiudadesInfectadasInicio").item(0)
					.getTextContent();
			numCuidadesInfectadasRonda = document.getElementsByTagName("numCuidadesInfectadasRonda").item(0)
					.getTextContent();
			numEnfermedadesActivasDerrota = document.getElementsByTagName("numEnfermedadesActivasDerrota").item(0)
					.getTextContent();
			numBrotesDerrota = document.getElementsByTagName("numBrotesDerrota").item(0).getTextContent();
		} catch (Exception e) {
			System.out.println(e);
		}

		// Lo guardamos en una Array
		parametros[0] = numCiudadesInfectadasInicio;
		parametros[1] = numCuidadesInfectadasRonda;
		parametros[2] = numEnfermedadesActivasDerrota;
		parametros[3] = numBrotesDerrota;

		// Y lo retornamos
		return parametros;

	}

	// Actualiza los valores(nombre de archivo, numero de parametro, valor nuevo)
	static void actualizarValor(File file, int num, int valor) {

		try {
			// Cargamos el documento
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);

			if (num == 1) {
				// Cargamos el dato y lo guardamos en el Nodo
				Node node = document.getElementsByTagName("numCiudadesInfectadasInicio").item(0);
				// Lo cambiamos por el valor nuevo
				node.setTextContent(Integer.toString(valor));

			} else if (num == 2) {
				Node node = document.getElementsByTagName("numCuidadesInfectadasRonda").item(0);
				node.setTextContent(Integer.toString(valor));

			} else if (num == 3) {
				Node node = document.getElementsByTagName("numEnfermedadesActivasDerrota").item(0);
				node.setTextContent(Integer.toString(valor));

			} else if (num == 4) {
				Node node = document.getElementsByTagName("numBrotesDerrota").item(0);
				node.setTextContent(Integer.toString(valor));

			}

			// Guardamos el archivo
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.transform(new DOMSource(document), new StreamResult(file));
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
