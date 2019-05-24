package com.kamel.mber.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.kamel.mber.model.Token;


public class MyFirebaseService extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        updateTokenToServer(refreshedToken);
//        Update Realtime db when we have realtim token

    }

    private void updateTokenToServer(String refreshedToken) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Tokens");

        Token token = new Token(refreshedToken);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) //If already login then update token
        {
            ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);

        }
    }
}
