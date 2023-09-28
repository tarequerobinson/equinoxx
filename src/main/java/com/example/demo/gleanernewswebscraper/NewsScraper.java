package com.example.demo.gleanernewswebscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.HttpStatusException;
import com.google.gson.Gson;

public class NewsScraper {
    public static List<News> returnJson() {
        List<News> newsfeed = new ArrayList<News>();

        try {
            Document doc = Jsoup.connect("https://jamaica-gleaner.com/business").timeout(30000).followRedirects(true).get();

            Elements newsSections = doc.select("div.field-content");

            for (Element newsSection : newsSections) {
                Element imageElement = newsSection.select("img").first();
                String coverImageUrl = imageElement.attr("src");

                Element headlineElement = newsSection.select("h2.views-field.views-field-field-short-headline.lead-item").first();
                String headline = headlineElement.text();

                Element newsPreviewElement = newsSection.select("div.views-field.views-field-body > div.field-content").first();
                String newsPreview = newsPreviewElement.text();

                // Print the news element in the console
                System.out.println("Headline: " + headline);
                System.out.println("Cover Image URL: " + coverImageUrl);
                System.out.println("News Preview: " + newsPreview);
                System.out.println();

                // If you want to extract the URL from the <a> tag inside the <h2> element
                Element linkElement = headlineElement.select("a").first();
                String url = linkElement.attr("href");

                News news = new News(coverImageUrl, headline, newsPreview);
                newsfeed.add(news);
            }
        } catch (HttpStatusException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        return newsfeed;
    }
}
