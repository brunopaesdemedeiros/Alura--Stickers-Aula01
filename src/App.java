import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        //parcear o texto
        
        //fazer uma conexão http e buscar os top 250 filmes

        String url = "https://api.mocki.io/v2/549a5d8b";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        

        //pegar só os dados que interessam(título, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manupular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            String title = filme.get("title");            
            Float rating = Float.parseFloat(filme.get("imDbRating"));
            String poster = filme.get("image");
            String estrela = "";
            for (int i = 0; i <= rating; i++) {
               estrela = estrela +"\u2B50"; 
            }       

            System.out.println("\u001b[48;2;228;2;2m\u001b[1mNota: \u001b[m\u001b[48;2;228;2;2m" + rating + " \u001b[m");
            System.out.println(estrela+"\n");
            System.out.println("\u001b[255,255,255m Título: \u001b[0m" + title);
            System.out.println("Poster: " + poster);
            System.out.println();
        }

       
    }
}
