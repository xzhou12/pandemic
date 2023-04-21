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
	public static void main(String[] args) {
		// Archivo xml
		File file = new File("parametros.xml");
		// Modifica el archivo
		modificarArchivo(file);
		System.out.println("FIN");

	}

	// Printa los parametros
	static void mostrarParametros(String[] parametros) {
		System.out.println("------PARAMAETROS------");
		System.out.println("1: Ciudades infectadas al inicio: " + parametros[0]);
		System.out.println("2: Ciudades infectadas por ronda: " + parametros[1]);
		System.out.println("3: Enfermedades activas para derrota: " + parametros[2]);
		System.out.println("4: Brotes para derrota: " + parametros[3]);
	}

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

	// Modifica el archivo xml al gusto
	static void modificarArchivo(File file) {

		// Scanner y variables
		Scanner s = new Scanner(System.in);
		String[] parametros;
		int num = 0;

		// Mientras el numero no sea 5
		while (num != 5) {
			// Lee el archivo y lo guarda en una array
			parametros = leerArchivo(file);
			// Muestra los parametros
			mostrarParametros(parametros);
			System.out.println("5: Salir");
			System.out.println("Cual desea modificar? (1-4)");
			// Pide un numero
			num = s.nextInt();
			if (num >= 1 && num <= 4) {
				// Si el valor esta entre 1 y 4, acutaliza el valor
				//ahora pide el nuevo valor del parametro
				valor = s.nextInt();
				actualizarValor(file, num, valor);
			} else if (num == 5) {
				// Si es 5, sale
				continue;
			} else { // Si es otro, continua
				System.out.println("ERROR! El numero del parametro no existe");
			}

		}
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
