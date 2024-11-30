package lv.venta.helpers.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Location {
	@JsonProperty("ZIP")
	private String zip;
	
	@JsonProperty("NAME")
	private String name;
	
	@JsonProperty("TYPE")
	private String type;
	
	@JsonProperty("A0_NAME")
	private String country;
	
	@JsonProperty("A1_NAME")
	private String region;
	
	@JsonProperty("A2_NAME")
	private String municipality;
	@JsonProperty("A3_NAME")
	private String city;
	@JsonProperty("A4_NAME")
	private String area4;
	@JsonProperty("A5_NAME")
	private String street;
	@JsonProperty("A6_NAME")
	private String area6;
	@JsonProperty("A7_NAME")
	private String building;
	@JsonProperty("A8_NAME")
	private String area8;
	@JsonProperty("X_COORDINATE")
	private String xCoordinate;
	@JsonProperty("Y_COORDINATE")
	private String yCoordinate;
	@JsonProperty("SERVICE_HOURS")
	private String serviceHours;
	@JsonProperty("TEMP_SERVICE_HOURS")
	private String temp_serviceHours;
	@JsonProperty("TEMP_SERVICE_HOURS_UNTIL")
	private String temp_serviceHours_until;
	@JsonProperty("TEMP_SERVICE_HOURS_2")
	private String temp_serviceHours_2;
	@JsonProperty("TEMP_SERVICE_HOURS_2_UNTIL")
	private String temp_serviceHours_2_until;
	@JsonProperty("comment_est")
	private String comment_est;
	@JsonProperty("comment_eng")
	private String comment_eng;
	@JsonProperty("comment_rus")
	private String comment_rus;
	@JsonProperty("comment_lav")
	private String comment_lav;
	@JsonProperty("comment_lit")
	private String comment_lit;
	@JsonProperty("MODIFIED")
	private String modified;
}
