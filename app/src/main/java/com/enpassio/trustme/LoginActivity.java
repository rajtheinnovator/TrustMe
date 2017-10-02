package com.enpassio.trustme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.enpassio.trustme.model.Constants;
import com.enpassio.trustme.model.GoogleUserProfile;
import com.enpassio.trustme.model.UserProfile;
import com.enpassio.trustme.utils.InternetConnectivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.edit_text_email)
    EditText userEmailEditText;

    @BindView(R.id.password_edit_text)
    EditText accountPasswordEditText;

    @BindView(R.id.button_sign_in)
    Button signInButton;

    @BindView(R.id.button_sign_up)
    Button logInButton;

    @BindView(R.id.button_forgot_password)
    Button forgotPassword;

    @BindView(R.id.google_sign_in)
    SignInButton googleSignIn;
    //what user data we want to get for users signing in using Google
    String requestFor;
    ArrayList<String> requestForArrayList;
    private FirebaseAuth mAuth;
    private String email;
    private String password;
    private String name;
    private String city;
    private String gender;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    //Signin button
    private LinearLayout googleSignInButton;
    //Signing Options
    private GoogleSignInOptions gso;
    //google api client
    private GoogleApiClient mGoogleApiClient;
    //Signin constant to check the activity result
    private int RC_SIGN_IN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("userProfile");

        requestForArrayList = Constants.getRequestForArrayList();

        //initialize request to get a clean request string
        requestFor = "";
        for (int i = 0; i < requestForArrayList.size(); i++) {
            if (!(i == requestForArrayList.size() - 1)) {
                requestFor += "person." + requestForArrayList.get(i) + ",";
            } else
                requestFor += "person." + requestForArrayList.get(i); //if it's the last item, don't end up with a comma
        }

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        //Initializing google signin option
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @OnClick(R.id.button_sign_in)
    public void signInButtonClicked() {
        email = userEmailEditText.getText().toString().trim();
        password = accountPasswordEditText.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            userEmailEditText.setError(getResources().getString(R.string.error_enter_valid_email));
            return;
        }
        if (TextUtils.isEmpty(password)) {
            accountPasswordEditText.setError(getResources().getString(R.string.error_password_less_than_six_character_long));
            return;
        }
        if (InternetConnectivity.isInternetConnected(LoginActivity.this)) {
            SignInWithEmailPassword(email, password);
        } else {
            Toast.makeText(LoginActivity.this, getResources()
                    .getString(R.string.check_internet_connectivity), Toast.LENGTH_SHORT).show();
        }
    }

    private void SignInWithEmailPassword(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            checkIfEmailVerified();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, getResources()
                                            .getString(R.string.toast_authentication_failed),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void checkIfEmailVerified() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user.isEmailVerified()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        } else {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            FirebaseAuth.getInstance().signOut();
            //restart this activity
        }
    }

    @OnClick(R.id.button_sign_up)
    public void signUpButtonClicked() {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        finish();
    }

    @OnClick(R.id.button_forgot_password)
    public void resetPasswordButtonClicked() {
        startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
        finish();
    }

    @OnClick(R.id.google_sign_in)
    public void signInWithGoogle() {
        signIn();
    }

    //This function will option signing intent
    private void signIn() {
        //Creating an intent
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        //Starting intent for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //If signin
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin
            handleSignInResult(result);
        }
    }

    //After the signing we are calling this function
    private void handleSignInResult(GoogleSignInResult result) {
        //If the login succeed
        if (result.isSuccess()) {
            //Getting google account
            GoogleSignInAccount acct = result.getSignInAccount();
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            try {
                run(personName, personEmail, personId);
            } catch (IOException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();

        } else {
            //If login fails
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    void run(final String name, final String email, final String userId) throws IOException {

        String urlOfRequest = "https://people.googleapis.com/v1/people/" + userId + "?requestMask.includeField=" + requestFor + "&key=AIzaSyCKLmb7IjJc981itGdoCljydm73cBaUpkE";
        Uri baseUri = Uri.parse(urlOfRequest);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        Request request = new Request.Builder()
                .url(uriBuilder.toString())
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String jsonData = response.body().string();

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                GoogleUserProfile googleUserProfile = gson.fromJson(jsonData, GoogleUserProfile.class);
                List<GoogleUserProfile.Name> names = googleUserProfile.getName();
                List<GoogleUserProfile.Photos> photoses = googleUserProfile.getPhotos();
                List<GoogleUserProfile.Genders> genderses = googleUserProfile.getGender();
                List<GoogleUserProfile.Residence> residences = googleUserProfile.getResidences();
                List<GoogleUserProfile.Organization> organizations = googleUserProfile.getOrganizationses();
                List<GoogleUserProfile.UserGoogleProfile> userGoogleProfiles = googleUserProfile.getUserGoogleProfiles();
                List<GoogleUserProfile.CoverPhoto> coverPhotos = googleUserProfile.getUserProfileCoverPhoto();

                String city = "unknown";
                if (residences != null) {
                    city = residences.get(0).getUsersresidence();
                }
                String gender = "unknown";
                if (genderses != null) {
                    gender = genderses.get(0).getUserGender();
                }

                String profileImageUrl = "";
                if (photoses != null) {
                    profileImageUrl = photoses.get(0).getProfilePicUrl();
                }
                String organization = "";
                if (organizations != null) {
                    organization = organizations.get(0).getUsersOrganization();
                }
                String userGoogleProfileUrl = "";
                if (userGoogleProfiles != null) {
                    userGoogleProfileUrl = userGoogleProfiles.get(0).getUserGoogleProfileUrl();
                }
                String coverPhotoUrl = "";
                if (coverPhotos != null) {
                    coverPhotoUrl = coverPhotos.get(0).getUserGoogleProfileCoverPhotoUrl();
                }

                DatabaseReference mDatabase;
                mDatabase = FirebaseDatabase.getInstance().getReference();

                UserProfile user = new UserProfile(name, city, gender, email, profileImageUrl, organization, userGoogleProfileUrl, coverPhotoUrl);
                mDatabase.child("userProfile").child(userId).setValue(user);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }
        });

    }

}
