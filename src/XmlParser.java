import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XmlParser {
    private Document doc;

    XmlParser(String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File file = new File(filePath);

            this.doc = builder.parse(file);
            this.doc.getDocumentElement().normalize();

        } catch (ParserConfigurationException e) {
            System.out.println("Erro ao configurar o parser XML: " + e.getMessage());
        } catch (SAXException e) {
            System.out.println("Erro na estrutura do XML: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public NodeList getListaDeVertices() {
        if (this.doc != null) {
            return this.doc.getElementsByTagName("node");
        }
        return null;
    }

    public NodeList getListaDeArestas() {
        if (this.doc != null) {
            return this.doc.getElementsByTagName("edge"); 
        }
        return null;
    }

    public Integer getQuantidadeDeNos() {
        if (this.doc != null) {
            NodeList listaNodes = this.doc.getElementsByTagName("nodes");
            
            if (listaNodes.getLength() > 0) {
                Element elementoNodes = (Element) listaNodes.item(0);
                String valorCount = elementoNodes.getAttribute("count");
                return Integer.parseInt(valorCount);
            }
        }
        return 0;
    }
}