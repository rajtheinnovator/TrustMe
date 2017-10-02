package com.enpassio.trustme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ABHISHEK RAJ on 10/2/2017.
 */

public class GoogleUserProfile {
    @SerializedName("names")
    @Expose
    public List<Name> familyName;

    @SerializedName("photos")
    @Expose
    public List<Photos> photos;

    @SerializedName("genders")
    @Expose
    public List<Genders> genders;

    @SerializedName("residences")
    @Expose
    public List<Residence> residences;

    @SerializedName("organizations")
    @Expose
    public List<Organization> organizationses;

    @SerializedName("urls")
    @Expose
    public List<UserGoogleProfile> userGoogleProfiles;

    @SerializedName("coverPhotos")
    @Expose
    public List<CoverPhoto> userProfileCoverPhoto;

    public List<Name> getName() {
        return familyName;
    }

    public List<Photos> getPhotos() {
        return photos;
    }

    public List<Genders> getGender() {
        return genders;
    }

    public List<Residence> getResidences() {
        return residences;
    }

    public List<Organization> getOrganizationses() {
        return organizationses;
    }

    public List<UserGoogleProfile> getUserGoogleProfiles() {
        return userGoogleProfiles;
    }

    public List<CoverPhoto> getUserProfileCoverPhoto() {
        return userProfileCoverPhoto;
    }

    public class Name {
        @SerializedName("familyName")
        @Expose
        public String familyName;

        @SerializedName("givenName")
        @Expose
        public String givenName;

        public Name(String name, String surname) {
            familyName = name;
            givenName = surname;
        }

        public String getFamilyName() {
            return familyName;
        }

        public String getGivenName() {
            return givenName;
        }

        public Name getName() {
            return new Name(familyName, givenName);
        }
    }


    public class Photos {
        @SerializedName("url")
        @Expose
        public String profilePicUrl;

        public Photos(String url) {
            profilePicUrl = url;
        }

        public String getProfilePicUrl() {
            return profilePicUrl;
        }

        public Photos getPhotos() {
            return new Photos(profilePicUrl);
        }
    }

    public class Genders {
        @SerializedName("value")
        @Expose
        public String userGender;
        String gender;

        public Genders(String gender) {
            userGender = gender;
        }

        public String getUserGender() {

            if (userGender.equals("male")) {
                gender = "Male";
            } else if (userGender.equals("female")) {
                gender = "Female";
            } else gender = userGender;
            return gender;
        }

        public Genders getPhotos() {
            return new Genders(userGender);
        }
    }

    public class Residence {
        @SerializedName("value")
        @Expose
        public String originalAddress;

        public Residence(String address) {
            originalAddress = address;
        }

        public String getUsersresidence() {
            return originalAddress;
        }

        public Residence getResidence() {
            return new Residence(originalAddress);
        }
    }

    public class Organization {
        @SerializedName("name")
        @Expose
        public String organization;

        public Organization(String organizationOfUser) {
            organization = organizationOfUser;
        }

        public String getUsersOrganization() {
            return organization;
        }

        public Residence getOrganization() {
            return new Residence(organization);
        }
    }

    public class UserGoogleProfile {
        @SerializedName("value")
        @Expose
        public String userGoogleProfileUrl;

        public UserGoogleProfile(String googleProfileUrl) {
            userGoogleProfileUrl = googleProfileUrl;
        }

        public String getUserGoogleProfileUrl() {
            return userGoogleProfileUrl;
        }

        public Residence getOrganization() {
            return new Residence(userGoogleProfileUrl);
        }
    }

    public class CoverPhoto {
        @SerializedName("url")
        @Expose
        public String userGoogleProfileCoverPhotoUrl;

        public CoverPhoto(String coverPhotoUrl) {
            userGoogleProfileCoverPhotoUrl = coverPhotoUrl;
        }

        public String getUserGoogleProfileCoverPhotoUrl() {
            return userGoogleProfileCoverPhotoUrl;
        }

        public Residence getOrganization() {
            return new Residence(userGoogleProfileCoverPhotoUrl);
        }
    }

}
