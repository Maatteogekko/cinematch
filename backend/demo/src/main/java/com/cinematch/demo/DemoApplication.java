package com.cinematch.demo;

// spring boot framework
import org.springframework.boot.SpringApplication; // create database ,, entities ,, api endpoints ,, api calls testing ---> https://hevodata.com/learn/spring-boot-rest-api/
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// db hibernate/jpa implementation
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// OKHttpClient
import okhttp3.OkHttpClient; // #TO-DO ///: use okhttp4 instead for endpoints~~ and ~~api =: different kind of api key? ask pino | ????
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
// Random numbers
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class DemoApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(DemoApplication.class, args);
        MoviePool movie_pool = new MoviePool();

        System.out.println(movie_pool.getMovie(3));
	}
}

@Entity
class User
{
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO) // hibernate implementation not getting imported
    private int id;
    // liked movies array

    // get ,, set methods
}

interface UserRepository extends JpaRepository<User, Long>
{
    // Custom queries can be defined here if needed
}


class MoviePool
{
    private static final int pool_size=50;
    private Movie[] pool = new Movie[pool_size];

    public MoviePool()
    {
        for(int i=0; i<pool_size; i++)
        {
            // generate random movie id;
            int movie_id = ThreadLocalRandom.current().nextInt(0, 9999+1); // #TO-DO ///: handle numbers < xxxx && check if proper id format

            // create Movie object && fetch movie info with http request && assign them
            pool[i] = new Movie();
            pool[i].fetchMovieInfo(movie_id);
        }
    }

    public Movie getMovie(int i)
    {
        // get movie at specified index
        if(i >= 0 && i < pool_size)
        {
            return pool[i];
        }
        else
        {
            return null;
        }
    }
}

class Movie
{
    private String title;
    private String overview;
    private String releaseDate;
    private String genres;
    // ...

    public Movie()
    {
        title = "N/A";
        overview = "N/A";
        releaseDate = "N/A";
        genres = "N/A";
    }

    public Movie(String t, String d, String r_d, String g)
    {
        title = t;
        overview = d;
        releaseDate = r_d;
        genres = g;
    }

    public void fetchMovieInfo(int movie_id)
    {
        // initialize okhttp client
        OkHttpClient client = new OkHttpClient();
        // TMDb API key
        String api_key = "dc7d794112655ff472cde48b94bab9bb";
        // generate adequate url for api endpoint call based on movie_id
        String api_url = "https://api.themoviedb.org/3/movie" + movie_id + "?api_key=" + api_key;

        Request request = new Request.Builder()
            .url(api_url)
            .get()
            .build();

        try
        {
            // send http request, store in `response` object
            Response response = client.newCall(request).execute();
            // check if response status code in 200-299 range
            if(response.isSuccessful())
            {
                // assign response to ResponseBody type object
                ResponseBody responseBody = response.body();
                // check if response insn't null
                if(responseBody != null)
                {
                    // convert response into a json string
                    String jsonResponse = responseBody.string();
                    // parse json for title
                    String fetchedTitle = jsonResponse.split("\"title\":\"")[1].split("\"")[0]; // #TO-CHECK ///: maybe use gson lib + proper error handling && tags
                    // parse json for description
                    String fetchedOverview = jsonResponse.split("\"overview\":\"")[1].split("\"")[0]; // #TO-CHECK ///: maybe use gson lib + proper error handling && tags
                    // parse json for genre
                    String fetchedGenre = jsonResponse.split("\"genre\":\"")[1].split("\"")[0]; // #TO-CHECK ///: maybe use gson lib + proper error handling && tags
                    // parse json for release date
                    String fetchedReleaseDate = jsonResponse.split("\"release date\":\"")[1].split("\"")[0]; // #TO-CHECK ///: maybe use gson lib + proper error handling && tags

                    // assign values to Movie variables
                    title = fetchedTitle;
                    overview = fetchedOverview;
                    genres = fetchedGenre;
                    releaseDate = fetchedReleaseDate;

                    //debug
                    // /*
                    System.out.println(jsonResponse);
                    // */
                     /*
                    System.out.println("Title: " + title);
                    System.out.println("Description: " + overview);
                    System.out.println("Genres: " + genres);
                    System.out.println("Realease Date: " + releaseDate);
                    // */
                }
                else
                {
                    // if response is null, print error code + error message
                    System.out.println("Error code: " + response.code() + " ; " + response.message());
                }
            }
            else
            {
                // if response is not successful, print error code + error message
                System.out.println("Error code: " + response.code() + " ; " + response.message());
            }
        }
        catch (Exception e)
        {
            // exception handling, print tack info
            e.printStackTrace();
        }
    }

    public String toString()
    {
        return ("Title: " + title + " | Overview: " + overview + " | Genres:" + genres + " | Release Date:" + releaseDate);
    }
}