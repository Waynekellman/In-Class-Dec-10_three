package com.nyc.in_class_dec_10_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button button;
    private ArrayList<String> familyMembers1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);

        JSONObject family = getSampleJSON();
        familyMembers1 = getFamalyFromJson(family);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("family", familyMembers1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    public ArrayList<String> getFamalyFromJson(JSONObject family) {
        ArrayList<String> familyMembers = new ArrayList<>();
        try {
            JSONArray jsonArray = family.getJSONArray("Family");
            for (int i = 0; i < jsonArray.length(); i++) {
                familyMembers.add(jsonArray.get(i).toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (String s: familyMembers) {
            Log.d(TAG, s);
        }
        return familyMembers;
    }

    static JSONObject getSampleJSON() {
        JSONObject family = new JSONObject();
        try {
            family.put("Family",
                    new JSONArray()
                            .put(new JSONObject()
                                    .put("name", "Mary")
                                    .put("relationship", "Mom")
                            )
                            .put(new JSONObject()
                                    .put("name", "Brandy")
                                    .put("relationship", "Sister")
                            )
                            .put(new JSONObject()
                                    .put("name", "Tiffany")
                                    .put("relationship", "Sister")
                            )
                            .put(new JSONObject()
                                    .put("name", "Jeremiah")
                                    .put("relationship", "Nephew")
                            )
                            .put(new JSONObject()
                                    .put("name", "Wayne")
                                    .put("relationship", "Dad")
                            )
                            .put(new JSONObject()
                                    .put("name", "John")
                                    .put("relationship", "Brotha from anotha motha")
                            )
            );

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(TAG, family.toString());
        return family;
    }

}
