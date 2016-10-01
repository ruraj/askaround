package com.askaround.askaround.model;

import java.util.Date;

/**
 * Created by ruraj on 10/1/16.
 */
public class Question {

  private String title;
  private String description;
  private String category;
  private long responseCount;

  private double lat;
  private double lon;

  private Date datePublished;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public long getResponseCount() {
    return responseCount;
  }

  public void setResponseCount(long responseCount) {
    this.responseCount = responseCount;
  }

  public double getLat() {
    return lat;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public double getLon() {
    return lon;
  }

  public void setLon(double lon) {
    this.lon = lon;
  }

  public Date getDatePublished() {
    return datePublished;
  }

  public void setDatePublished(Date datePublished) {
    this.datePublished = datePublished;
  }
}
