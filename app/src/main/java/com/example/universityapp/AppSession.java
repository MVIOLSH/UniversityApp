package com.example.universityapp;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AppSession {
    public static class Session {
        public static User userSession = new User();
        public static FirebaseUser authUser;
        public static String uName;
    }

}
