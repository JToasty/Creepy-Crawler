import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Matthew Caggiano on 5/10/17.
 */
public class Crawler {

    private ArrayList<String> weblinks;
    private final int MAXPAGES = 50;
    private int current_depth;

    public Crawler(){
        weblinks = new ArrayList<String>();
        current_depth = 0;

    }

    public void crawl(String URL) throws IOException {
        if ((!weblinks.contains(URL) && (current_depth < MAXPAGES))) {
            weblinks.add(URL);

            Document WebDocument = Jsoup.connect(URL).get();
            Elements FoundLinksOnPage = WebDocument.select("a[href]");

            current_depth++;
            for (Element page : FoundLinksOnPage) {
                crawl(page.attr("abs:href"));
            }
        }
    }

    public void dump(){
        for(String L : weblinks){
            System.out.println(L);
        }
    }

    public ArrayList<String> getWeblinks() {
        return weblinks;
    }

    public void cleanUp(){
        weblinks.clear();
    }
}


