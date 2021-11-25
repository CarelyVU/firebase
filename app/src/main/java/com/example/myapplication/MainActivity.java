package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "MYTAG";
    private List<String> cities=new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cities.add("Bolivia");
        cities.add("Chile");
        listView=(ListView) findViewById(R.id.list);
        ArrayAdapter <String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,cities);
        listView.setAdapter(adapter);

        db.collection("cities")
                //.whereEqualTo("state", "CA")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.e(TAG, "Listen failed.", e);
                            return;
                        }
                        cities.clear();
                        //List<String> cities = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : value) {
                            if (doc.get("name") != null) {
                                cities.add(doc.getString("name"));
                                adapter.notifyDataSetChanged();
                            }
                        }
                        Log.e(TAG, "Current cites in CA: " + cities);
                    }
                });


        // Create a new user with a first and last name
//        Map<String, Object> user = new HashMap<>();
//        user.put("first", "Carely");
//        user.put("last", "Villca");
//        user.put("born", 2000);

// Add a new document with a generated ID
//        db.collection("users")
 //               .add(user)
 //               .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {


  //                  @Override
    //                public void onSuccess(DocumentReference documentReference) {
    //                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
    //                }
    //            })
     //           .addOnFailureListener(new OnFailureListener() {
      //              @Override
       //             public void onFailure(@NonNull Exception e) {
        //                Log.w(TAG, "Error adding document", e);
         //           }
           //     });
    }
}