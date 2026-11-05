import java.util.ArrayDeque;
import java.util.Arrays;

public class Estatisticas {
    private Grafo grafo;

    public Estatisticas(Grafo grafo) {
        this.grafo = grafo;
    }

    // BFS: dist[v] = menor número de arestas de "origem" até v (-1 se inalcançável)
    private int[] bfs(int origem) {
        int[] dist = new int[this.grafo.getQuantidadeDeVertices()];
        Arrays.fill(dist, -1);

        ArrayDeque<Integer> fila = new ArrayDeque<>();
        dist[origem] = 0;
        fila.add(origem);

        while (!fila.isEmpty()) {
            int atual = fila.poll();
            for (int vizinho : this.grafo.getArestas(atual)) {
                if (dist[vizinho] == -1) {
                    dist[vizinho] = dist[atual] + 1;
                    fila.add(vizinho);
                }
            }
        }

        return dist;
    }

    private void verificarConexo(int[] dist) {
        for (int d : dist) {
            if (d == -1) {
                throw new IllegalStateException("O grafo não é conexo.");
            }
        }
    }

    /*
     * ECCENTRICITY: maior distância entre o vértice v e qualquer outro vértice,
     * ou seja, quão longe v está do vértice mais distante dele.
     * Quanto menor, mais central o vértice.
     */
    public int[] calcularExcentricidades() {
        int n = this.grafo.getQuantidadeDeVertices();
        int[] excentricidade = new int[n];

        for (int v = 0; v < n; v++) {
            int[] dist = this.bfs(v);
            this.verificarConexo(dist);

            int maior = 0;
            for (int d : dist) {
                maior = Math.max(maior, d);
            }
            excentricidade[v] = maior;
        }

        return excentricidade;
    }

    /*
     * CLOSENESS CENTRALITY (normalizada): (n - 1) / soma das distâncias de v
     * até todos os outros vértices. Mede quão perto v está, em média, dos
     * demais: vai de 0 a 1, e quanto maior, mais central o vértice.
     */
    public double[] calcularClosenessNormalizado() {
        int n = this.grafo.getQuantidadeDeVertices();
        double[] closeness = new double[n];

        for (int v = 0; v < n; v++) {
            int[] dist = this.bfs(v);
            this.verificarConexo(dist);

            long somaDistancias = 0;
            for (int d : dist) {
                somaDistancias += d;
            }

            // soma 0 só ocorre com 1 vértice (evita divisão por zero)
            closeness[v] = somaDistancias == 0 ? 0.0 : (double) (n - 1) / somaDistancias;
        }

        return closeness;
    }
}
