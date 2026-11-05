import java.util.ArrayList;
import java.util.HashMap;

public class Grafo {
    private ArrayList<ArrayList<Integer>> vizinhos;
    private HashMap<String, Integer> nomeParaId;
    private HashMap<Integer, String> IdParaNome;

    public Grafo() {
        this.vizinhos = new ArrayList<>();
        this.nomeParaId = new HashMap<>();
        this.IdParaNome = new HashMap<>();
    }

    public void adicionarVertice(Integer id, String nome) {
        this.nomeParaId.put(nome, id);
        this.IdParaNome.put(id, nome);
        
        while (this.vizinhos.size() <= id) {
            this.vizinhos.add(new ArrayList<>());
        }
    }

    // Não dirigido: registra nas duas listas, ignorando laços e arestas repetidas
    public void adicionarAresta(Integer u, Integer v) {
        if (u.equals(v) || this.vizinhos.get(u).contains(v)) {
            return;
        }
        this.setAresta(u, v);
        this.setAresta(v, u);
    }

    public Integer getQuantidadeDeVertices() {
        return this.vizinhos.size();
    }

    public ArrayList<ArrayList<Integer>> getVizinhos() {
        return vizinhos;
    }

    public void setVizinhos(ArrayList<ArrayList<Integer>> vizinhos) {
        this.vizinhos = vizinhos;
    }

    public ArrayList<Integer> getArestas(Integer id) {
        return this.vizinhos.get(id);
    }

    public void setAresta(Integer idVertice, Integer idNovoVizinho) {
        this.vizinhos.get(idVertice).add(idNovoVizinho);
    }

    public HashMap<Integer, String> getIdParaNome() {
        return IdParaNome;
    }

    public void setIdParaNome(HashMap<Integer, String> idParaNome) {
        IdParaNome = idParaNome;
    }

    public HashMap<String, Integer> getNomeParaId() {
        return nomeParaId;
    }

    public void setNomeParaId(HashMap<String, Integer> nomeParaId) {
        this.nomeParaId = nomeParaId;
    }
}