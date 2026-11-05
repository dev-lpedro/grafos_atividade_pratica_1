import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        try {
            Grafo grafo = LeitorGexf.ler("LesMiserables.gexf");
            Estatisticas estatisticas = new Estatisticas(grafo);

            int[] excentricidade = estatisticas.calcularExcentricidades();
            double[] closeness = estatisticas.calcularClosenessNormalizado();

            System.out.println("Total de vertices: " + grafo.getQuantidadeDeVertices());

            System.out.println("\n=== Eccentricity ===");
            try (PrintWriter arquivo = new PrintWriter("eccentricity.txt", "UTF-8")) {
                for (int id = 0; id < excentricidade.length; id++) {
                    String linha = grafo.getIdParaNome().get(id) + ": " + excentricidade[id];
                    System.out.println(linha);
                    arquivo.println(linha);
                }
            }

            System.out.println("\n=== Closeness Centrality (normalizado) ===");
            try (PrintWriter arquivo = new PrintWriter("closeness_centrality.txt", "UTF-8")) {
                for (int id = 0; id < closeness.length; id++) {
                    String linha = grafo.getIdParaNome().get(id) + ": "
                            + String.format(Locale.US, "%.6f", closeness[id]);
                    System.out.println(linha);
                    arquivo.println(linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever os arquivos de saida: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
