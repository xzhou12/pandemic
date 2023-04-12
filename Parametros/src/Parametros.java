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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Parametros {

	public static void main(String[] args) {
		File file = new File("parametros.xml");
		modificarArchivo(file);
		System.out.println("FIN");

	}

	static void mostrarParametros(String[] parametros) {
		System.out.println("------PARAMAETROS------");
		System.out.println("1: Ciudades infectadas al inicio: " + parametros[0]);
		System.out.println("2: Ciudades infectadas por ronda: " + parametros[1]);
		System.out.println("3: Enfermedades activas para derrota: " + parametros[2]);
		System.out.println("4: Brotes para derrota: " + parametros[3]);
	}

	static String[] leerArchivo(File archivo) {

		String numCiudadesInfectadasInicio = "";
		String numCuidadesInfectadasRonda = "";
		String numEnfermedadesActivasDerrota = "";
		String numBrotesDerrota = "";
		String[] parametros = new String[4];

		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(archivo);

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

		parametros[0] = numCiudadesInfectadasInicio;
		parametros[1] = numCuidadesInfectadasRonda;
		parametros[2] = numEnfermedadesActivasDerrota;
		parametros[3] = numBrotesDerrota;

		return parametros;

	}

	static void modificarArchivo(File file) {
		// https://www.javaguides.net/2018/10/how-to-modify-or-update-xml-file-in-java-dom-parser.html
		Scanner s = new Scanner(System.in);
		Node aux;
		String[] parametros;
		int num = 0;
		int valor = 0;

		while (num != 5) {
			parametros = leerArchivo(file);
			mostrarParametros(parametros);
			System.out.println("Cual desea modificar? (1-4)");
			num = s.nextInt();
			actualizarValor(file, s, num);

		}
	}

	static void actualizarValor(File file, Scanner s, int num) {

		int valor = 0;

		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);

			System.out.println("Valor?");
			valor = s.nextInt();

			if (num == 1) {
				Node node = document.getElementsByTagName("numCiudadesInfectadasInicio").item(0);
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

			} else {
				System.out.println("ERROR! El numero del parametro no existe");
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.transform(new DOMSource(document), new StreamResult(file));
		} catch (Exception e) {

		}

	}

}
