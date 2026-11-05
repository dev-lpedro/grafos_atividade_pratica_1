import java.util.HashMap;

import org.w3c.dom.*;

public class LeitorGexf {

    public static Grafo ler(String caminho) {
        XmlParser parser = new XmlParser(caminho);
        NodeList vertices = parser.getListaDeVertices();
        NodeList arestas = parser.getListaDeArestas();

        if (vertices == null || arestas == null) {
            throw new IllegalStateException("Não foi possível ler o arquivo GEXF: " + caminho);
        }

        Grafo grafo = new Grafo();

        // As arestas do GEXF usam o "id" do nó (ex.: "11.0"); este mapa o converte para o id inteiro do Grafo
        HashMap<String, Integer> gexfIdParaId = new HashMap<>();

        for (int i = 0; i < vertices.getLength(); i++) {
            Element vertice = (Element) vertices.item(i);
            String gexfId = vertice.getAttribute("id");
            String nome = vertice.getAttribute("label");
            if (nome.isEmpty()) {
                nome = gexfId;
            }

            gexfIdParaId.put(gexfId, i);
            grafo.adicionarVertice(i, nome);
        }

        for (int i = 0; i < arestas.getLength(); i++) {
            Element aresta = (Element) arestas.item(i);
            Integer origem = gexfIdParaId.get(aresta.getAttribute("source"));
            Integer destino = gexfIdParaId.get(aresta.getAttribute("target"));

            if (origem == null || destino == null) {
                throw new IllegalStateException("Aresta " + aresta.getAttribute("id")
                        + " referencia um vértice inexistente.");
            }

            grafo.adicionarAresta(origem, destino);
        }

        return grafo;
    }
}
