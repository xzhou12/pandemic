import java.io.File;

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

	public static final File archivo = new File("parametros.xml");

	// Lee el archivo .xml
	static String[] leerArchivo() {

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
	static void actualizarValor(File file, int valor1, int valor2, int valor3, int valor4) {

		try {
			// Cargamos el documento
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);

			// Cargamos el dato y lo guardamos en el Nodo
			Node node = document.getElementsByTagName("numCiudadesInfectadasInicio").item(0);
			// Lo cambiamos por el valor nuevo
			node.setTextContent(Integer.toString(valor1));
			node = document.getElementsByTagName("numCuidadesInfectadasRonda").item(0);
			node.setTextContent(Integer.toString(valor2));
			node = document.getElementsByTagName("numEnfermedadesActivasDerrota").item(0);
			node.setTextContent(Integer.toString(valor3));
			node = document.getElementsByTagName("numBrotesDerrota").item(0);
			node.setTextContent(Integer.toString(valor4));

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.transform(new DOMSource(document), new StreamResult(file));
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
