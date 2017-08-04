/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.ntu.medicine.lkc.json.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"package_name",
"title",
"developer",
"number_ratings",
"rating",
"icon",
"icon_72",
"market_url",
"price",
"start_date",
"reviews",
"number_reviews",
"total_reviews",
"page",
"limit",
"total_pages"
})
public class AppPackage {
    

@JsonProperty("package_name")
private String packageName;
@JsonProperty("title")
private String title;
@JsonProperty("developer")
private String developer;
@JsonProperty("number_ratings")
private Integer numberRatings;
@JsonProperty("rating")
private Double rating;
@JsonProperty("icon")
private String icon;
@JsonProperty("icon_72")
private String icon72;
@JsonProperty("market_url")
private String marketUrl;
@JsonProperty("price")
private String price;
@JsonProperty("start_date")
private String startDate;
@JsonProperty("reviews")
private List<Review> reviews;
@JsonProperty("number_reviews")
private Integer numberReviews;
@JsonProperty("total_reviews")
private Integer totalReviews;
@JsonProperty("page")
private Integer page;
@JsonProperty("limit")
private Integer limit;
@JsonProperty("total_pages")
private Integer totalPages;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();



@JsonProperty("package_name")
public String getPackageName() {
return packageName;
}

@JsonProperty("package_name")
public void setPackageName(String packageName) {
this.packageName = packageName;
}

@JsonProperty("title")
public String getTitle() {
return title;
}

@JsonProperty("title")
public void setTitle(String title) {
this.title = title;
}

@JsonProperty("developer")
public String getDeveloper() {
return developer;
}

@JsonProperty("developer")
public void setDeveloper(String developer) {
this.developer = developer;
}

@JsonProperty("number_ratings")
public Integer getNumberRatings() {
return numberRatings;
}

@JsonProperty("number_ratings")
public void setNumberRatings(Integer numberRatings) {
this.numberRatings = numberRatings;
}

@JsonProperty("rating")
public Double getRating() {
return rating;
}

@JsonProperty("rating")
public void setRating(Double rating) {
this.rating = rating;
}

@JsonProperty("icon")
public String getIcon() {
return icon;
}

@JsonProperty("icon")
public void setIcon(String icon) {
this.icon = icon;
}

@JsonProperty("icon_72")
public String getIcon72() {
return icon72;
}

@JsonProperty("icon_72")
public void setIcon72(String icon72) {
this.icon72 = icon72;
}

@JsonProperty("market_url")
public String getMarketUrl() {
return marketUrl;
}

@JsonProperty("market_url")
public void setMarketUrl(String marketUrl) {
this.marketUrl = marketUrl;
}

@JsonProperty("price")
public String getPrice() {
return price;
}

@JsonProperty("price")
public void setPrice(String price) {
this.price = price;
}

@JsonProperty("start_date")
public String getStartDate() {
return startDate;
}

@JsonProperty("start_date")
public void setStartDate(String startDate) {
this.startDate = startDate;
}

@JsonProperty("reviews")
public List<Review> getReviews() {
return reviews;
}

@JsonProperty("reviews")
public void setReviews(List<Review> reviews) {
this.reviews = reviews;
}

@JsonProperty("number_reviews")
public Integer getNumberReviews() {
return numberReviews;
}

@JsonProperty("number_reviews")
public void setNumberReviews(Integer numberReviews) {
this.numberReviews = numberReviews;
}

@JsonProperty("total_reviews")
public Integer getTotalReviews() {
return totalReviews;
}

@JsonProperty("total_reviews")
public void setTotalReviews(Integer totalReviews) {
this.totalReviews = totalReviews;
}

@JsonProperty("page")
public Integer getPage() {
return page;
}

@JsonProperty("page")
public void setPage(Integer page) {
this.page = page;
}

@JsonProperty("limit")
public Integer getLimit() {
return limit;
}

@JsonProperty("limit")
public void setLimit(Integer limit) {
this.limit = limit;
}

@JsonProperty("total_pages")
public Integer getTotalPages() {
return totalPages;
}

@JsonProperty("total_pages")
public void setTotalPages(Integer totalPages) {
this.totalPages = totalPages;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}