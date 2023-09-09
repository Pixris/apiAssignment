package com.example.apiAssignment2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

/**
 * CSC340 Assignment 2
 * @author Jeong Won Kim
 */
@SpringBootApplication
public class ApiAssignment2Application {

    /**
     * Calls bored method
     * 
     * @param args 
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiAssignment2Application.class, args);
        bored();
        System.exit(0);
    }

    /**
     * This method gets the activity, activity type, people needed for the activity
     */
    public static void bored() {
        try {
            String url = "https://www.boredapi.com/api/activity";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonPrice = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonPrice);

            //gets activity
            String toDo = root.findValue("activity").asText();
            //type of activity
            String type = root.findValue("type").asText();
            //gets number of people needed
            String numUsers = root.findValue("participants").asText();
            //print quote and author
            System.out.println("What type of activity is this? " + type);
            System.out.println("Recommended activity: " +  toDo);
            System.out.println("Participants needed: " + numUsers);

        } catch (JsonProcessingException ex) {
            System.out.println("error");
        }
    }
}