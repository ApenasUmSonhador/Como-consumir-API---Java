import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.Gson;

import br.com.alura.screenmatch.modelos.Titulo;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        String apikey = "aqui vai a sua chave de API";
        Scanner s = new Scanner(System.in);
        System.out.println("Digite o nome do filme que deseja buscar: ");
        var nomeDoFilme = s.nextLine();

        String url = "http://www.omdbapi.com/?t=" + nomeDoFilme + "&apikey=" + apikey;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        var json = response.body();
        Gson gson = new Gson();
        Titulo titulo = gson.fromJson(response.body(), Titulo.class);
        titulo.exibeFichaTecnica();
    }
}
