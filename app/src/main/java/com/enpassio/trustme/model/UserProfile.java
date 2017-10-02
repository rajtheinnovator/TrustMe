package com.enpassio.trustme.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ABHISHEK RAJ on 9/5/2017.
 */

public class UserProfile implements Parcelable {
    @SuppressWarnings("unused")
    public static final Creator<UserProfile> CREATOR = new Creator<UserProfile>() {
        @Override
        public UserProfile createFromParcel(Parcel in) {
            return new UserProfile(in);
        }

        @Override
        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };
    private String usersName;
    private String usersCity;
    private String usersEmail;
    private String usersGender;
    private String userProfileImageUrl;
    private String userOrganization;
    private String userGoogleProfileUrl;
    private String userGoogleProfileCoverPhotoUrl;

    public UserProfile(String name, String city, String gender, String email) {
        usersName = name;
        usersCity = city;
        usersGender = gender;
        usersEmail = email;
    }

    public UserProfile(String name, String city, String gender, String email, String profileImageUrl, String organization, String googleProfileUrl, String coverPhotoUrl) {
        usersName = name;
        usersCity = city;
        usersGender = gender;
        usersEmail = email;
        userProfileImageUrl = profileImageUrl;
        userOrganization = organization;
        userGoogleProfileUrl = googleProfileUrl;
        userGoogleProfileCoverPhotoUrl = coverPhotoUrl;
    }

    public UserProfile(Parcel in) {
        usersName = in.readString();
        usersCity = in.readString();
        usersEmail = in.readString();
        usersGender = in.readString();
        userProfileImageUrl = in.readString();
        userOrganization = in.readString();
        userGoogleProfileUrl = in.readString();
        userGoogleProfileCoverPhotoUrl = in.readString();
    }

    public String getUsersName() {
        return usersName;
    }

    public String getUsersCity() {
        return usersCity;
    }

    public String getUsersEmail() {
        return usersEmail;
    }

    public String getUsersGender() {
        return usersGender;
    }

    public String getUsersProfileImageUrl() {
        return userProfileImageUrl;
    }

    public String getUsersOrganization() {
        return userOrganization;
    }

    public String getUserGoogleProfileUrl() {
        return userGoogleProfileUrl;
    }

    public String getUsersGoogleProfileCoverPhotoUrl() {
        return userGoogleProfileCoverPhotoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(usersName);
        dest.writeString(usersCity);
        dest.writeString(usersEmail);
        dest.writeString(usersGender);
        dest.writeString(userProfileImageUrl);
        dest.writeString(userOrganization);
        dest.writeString(userGoogleProfileUrl);
        dest.writeString(userGoogleProfileCoverPhotoUrl);
    }
}
