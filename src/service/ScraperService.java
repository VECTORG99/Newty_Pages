package newty.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Newty scraper skeleton — copy and adapt for any data source.
 * 
 * What to replace:
 *   Item         → your domain record
 *   buildUrl()   → your data source URL  
 *   parse()      → your HTML/JSON parsing logic
 *   translate()  → your text transformation (EN→ES, markup→plain, etc.)
 * 
 * The contract: fetch() returns List<Item>, never null, never throws.
 */
public class ScraperService {

    private static final Logger log = Logger.getLogger(ScraperService.class.getName());
    private final HttpClient http = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    // ---------- Replace with your domain record ----------
    public record Item(String title, String url, String description, String extra) {}

    // ---------- Replace: build target URL ----------
    private URI buildUrl(String period) {
        return URI.create("https://example.com/data?period=" + period);
    }

    // ---------- Replace: parse raw response ----------
    private List<Item> parse(String raw, int maxCount) {
        List<Item> items = new ArrayList<>();
        // Your parsing logic here
        // Sort, limit to maxCount
        return items;
    }

    // ---------- Replace: translate/transform ----------
    protected String translate(String text) {
        if (text == null || text.isBlank()) return "";
        // Simple word-replacement map goes here
        return text;
    }

    // ---------- Public API (keep as-is) ----------

    public List<Item> fetch(String period, int count) {
        try {
            var req = HttpRequest.newBuilder()
                    .uri(buildUrl(period))
                    .header("User-Agent", "Newty/1.0")
                    .timeout(Duration.ofSeconds(15))
                    .GET().build();
            var resp = http.send(req, HttpResponse.BodyHandlers.ofString());
            log.info(() -> "Fetched " + resp.body().length() + " bytes");
            return parse(resp.body(), count);
        } catch (Exception e) {
            log.warning("Scrape failed: " + e.getMessage());
            return List.of();
        }
    }
}
