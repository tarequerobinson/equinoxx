package com.example.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rometools.rome.feed.synd.SyndEnclosure;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.XmlReader;

import com.rometools.rome.io.SyndFeedInput;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.springframework.http.MediaType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;






@RestController
@RequestMapping("/api/news")
public class RssNewsFeedController {
	
	
	
	@GetMapping(value = "/rss", produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> getRssFeed() throws Exception {
	    String url = "https://jamaica-gleaner.com/feed/business.xml";
	    try (CloseableHttpClient client = HttpClients.createMinimal()) {
	        HttpRequestBase request = new HttpGet(url);
	        try (CloseableHttpResponse response = client.execute(request);
	             InputStream stream = response.getEntity().getContent()) {
	            SyndFeedInput input = new SyndFeedInput();
	            SyndFeed feed = input.build(new XmlReader(stream));

	            // Read the HTML template from file
	            String htmlTemplate = readHtmlTemplate();

	            // Replace placeholders with actual data
	            htmlTemplate = htmlTemplate.replace("feed-title", feed.getTitle());
	            htmlTemplate = htmlTemplate.replace("feed-content", generateFeedItemsHtml(feed));

	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.TEXT_HTML);

	            return new ResponseEntity<>(htmlTemplate, headers, HttpStatus.OK);
	        }
	    }
	}

	private String readHtmlTemplate() throws IOException {
	    // Load the HTML template file from the classpath or file system
	    ClassPathResource resource = new ClassPathResource("templates/rss-template.html");
	    InputStream inputStream = resource.getInputStream();

	    // Read the template content as a string
	    return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
	}

	private String generateFeedItemsHtml(SyndFeed feed) {
	    StringBuilder itemsHtml = new StringBuilder();
	    for (SyndEntry entry : feed.getEntries()) {
	        String itemHtml = "<div class=\"feed-item\">" +
	                "<div class=\"feed-item-title\">" + entry.getTitle() + "</div>" +
	                "<div class=\"feed-item-description\">" + entry.getDescription().getValue() + "</div>" +
	                "<div class=\"feed-item-link\"><a href=\"" + entry.getLink() + "\">Read More</a></div>" +
	                "</div>";
	 

            itemsHtml.append(itemHtml);
        }
	    return itemsHtml.toString();
	}
	
	

}
