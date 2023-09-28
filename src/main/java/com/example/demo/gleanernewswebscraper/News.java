package com.example.demo.gleanernewswebscraper;

public class News {
	
	String coverImageUrl;
	

	String headline;
	String newsPreview;
	
	
	//Constructor
	public News(String coverImageUrl, String headline, String newsPreview) {
		super();
		this.coverImageUrl = coverImageUrl;
		this.headline = headline;
		this.newsPreview = newsPreview;
	}
	
	
	
	public String getCoverImageUrl() {
		return coverImageUrl;
	}
	public void setCoverImageUrl(String coverImageUrl) {
		this.coverImageUrl = coverImageUrl;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getNewsPreview() {
		return newsPreview;
	}
	public void setNewsPreview(String newsPreview) {
		this.newsPreview = newsPreview;
	}

}
