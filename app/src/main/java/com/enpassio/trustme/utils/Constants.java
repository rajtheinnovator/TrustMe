package com.enpassio.trustme.utils;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ABHISHEK RAJ on 10/3/2017.
 */

public class Constants {

    public static final ArrayList<String> requestForArrayList = new ArrayList<String>();
    public static final String PEOPLE_API_KEY = "AIzaSyCKLmb7IjJc981itGdoCljydm73cBaUpkE";
    /**
     * Used to set an expiration time for a geofence. After this amount of time Location Services
     * stops tracking the geofence.
     */
    public static final long GEOFENCE_EXPIRATION_IN_HOURS = 12;
    /**
     * For this sample, geofences expire after twelve hours.
     */
    public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS =
            GEOFENCE_EXPIRATION_IN_HOURS * 60 * 60 * 1000;
    public static final float GEOFENCE_RADIUS_IN_METERS = 50;
    /**
     * Map for storing information about airports in the San Francisco bay area.
     */
    public static final HashMap<String, LatLng> HOT_RECRUITMENT_CITIES_LANDMARKS = new HashMap<String, LatLng>();

    static {
        // San Francisco International Airport.
        HOT_RECRUITMENT_CITIES_LANDMARKS.put("Krishna Water Supply", new LatLng(12.923916, 77.613462));
    }

    private Constants() {
    }

    public final static ArrayList<String> getRequestForArrayList() {
        requestForArrayList.add("addresses");
        requestForArrayList.add("residences");
        requestForArrayList.add("photos");
        requestForArrayList.add("names");
        requestForArrayList.add("ageRanges");
        requestForArrayList.add("biographies");
        requestForArrayList.add("birthdays");
        requestForArrayList.add("braggingRights");
        requestForArrayList.add("coverPhotos");
        requestForArrayList.add("emailAddresses");
        requestForArrayList.add("events");
        requestForArrayList.add("events");
        requestForArrayList.add("genders");
        requestForArrayList.add("imClients");
        requestForArrayList.add("interests");
        requestForArrayList.add("locales");
        requestForArrayList.add("memberships");
        requestForArrayList.add("metadata");
        requestForArrayList.add("nicknames");
        requestForArrayList.add("occupations");
        requestForArrayList.add("organizations");
        requestForArrayList.add("phoneNumbers");
        requestForArrayList.add("relations");
        requestForArrayList.add("relationshipInterests");
        requestForArrayList.add("relationshipStatuses");
        requestForArrayList.add("skills");
        requestForArrayList.add("taglines");
        requestForArrayList.add("urls");
        return requestForArrayList;
    }
}
