package com.enpassio.trustme.model;

import java.util.ArrayList;

/**
 * Created by ABHISHEK RAJ on 10/3/2017.
 */

public final class Constants {

    public static final ArrayList<String> requestForArrayList = new ArrayList<String>();
    public static final String PEOPLE_API_KEY = "AIzaSyCKLmb7IjJc981itGdoCljydm73cBaUpkE";

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
