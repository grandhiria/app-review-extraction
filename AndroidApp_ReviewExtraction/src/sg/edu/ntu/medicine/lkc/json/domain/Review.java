/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.ntu.medicine.lkc.json.domain;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"author_id",
"rating",
"lang",
"content",
"date",
"app_version"
})
public class Review {

@JsonProperty("author_id")
private String authorId;
@JsonProperty("rating")
private Double rating;
@JsonProperty("lang")
private String lang;
@JsonProperty("content")
private String content;
@JsonProperty("date")
private String date;
@JsonProperty("app_version")
private String appVersion;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("author_id")
public String getAuthorId() {
return authorId;
}

@JsonProperty("author_id")
public void setAuthorId(String authorId) {
this.authorId = authorId;
}

@JsonProperty("rating")
public Double getRating() {
return rating;
}

@JsonProperty("rating")
public void setRating(Double rating) {
this.rating = rating;
}

@JsonProperty("lang")
public String getLang() {
return lang;
}

@JsonProperty("lang")
public void setLang(String lang) {
this.lang = lang;
}

@JsonProperty("content")
public String getContent() {
return content;
}

@JsonProperty("content")
public void setContent(String content) {
this.content = content;
}

@JsonProperty("date")
public String getDate() {
return date;
}

@JsonProperty("date")
public void setDate(String date) {
this.date = date;
}

@JsonProperty("app_version")
public String getAppVersion() {
return appVersion;
}

@JsonProperty("app_version")
public void setAppVersion(String appVersion) {
this.appVersion = appVersion;
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