package newty.resource;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import newty.service.ScraperService;
import java.util.List;

/**
 * Newty page resource skeleton — copy and adapt.
 * 
 * Pattern:
 * 1. Inject your ScraperService
 * 2. Declare a @CheckedTemplate inner class (optional: requireTypeSafeExpressions=false)
 * 3. GET method accepts query params (period, count, locale)
 * 4. Fetch data, pass to template via .data()
 * 5. Compute UI state server-side (nextCount, isMax for toggle buttons)
 */
@Path("/")
@Produces(MediaType.TEXT_HTML)
public class PageResource {

    // Replace with your service
    private final ScraperService service = new ScraperService();

    // Replace with your toggle steps
    private static final int[] COUNT_STEPS = {1, 3, 5, 10};

    // ---------- Replace: @CheckedTemplate inner class ----------
    // @CheckedTemplate(basePath = "pages", requireTypeSafeExpressions = false)
    // static class Templates {
    //     public static native TemplateInstance myPage();
    // }

    @GET
    @Path("/mypage")
    public String myPage(
            @QueryParam("period") @DefaultValue("daily") String period,
            @QueryParam("count") @DefaultValue("1") int count) {

        List<ScraperService.Item> items = service.fetch(period, count);

        // ponytail: compute next toggle count server-side
        int nextCount = 1;
        for (int i = 0; i < COUNT_STEPS.length; i++) {
            if (COUNT_STEPS[i] == count) {
                nextCount = COUNT_STEPS[(i + 1) % COUNT_STEPS.length];
                break;
            }
        }

        // ---------- Replace: return template instance ----------
        // return TemplateLocaleUtil.apply(Templates.myPage(), localeCookie, headers)
        //     .data("items", items)
        //     .data("period", period)
        //     .data("count", count)
        //     .data("nextCount", nextCount)
        //     .data("isMax", count == COUNT_STEPS[COUNT_STEPS.length - 1]);

        return "Page with " + items.size() + " items, period=" + period + ", nextCount=" + nextCount;
    }
}
