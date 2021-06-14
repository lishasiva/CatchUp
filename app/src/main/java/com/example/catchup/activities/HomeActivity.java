package com.example.catchup.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuPopupHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.catchup.R;
import com.example.catchup.bases.BaseActivity;
import com.example.catchup.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends BaseActivity {

    ImageView imageMenu;

    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    TextView textYourName;

    LinearLayout layoutList, layoutWater, layoutWorkout, layoutQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textYourName = findViewById(R.id.textYourName);
        imageMenu = findViewById(R.id.imageMenu);
        setUserName();
        setPopUpmenu();

        layoutList = findViewById(R.id.layoutList);
        layoutWater = findViewById(R.id.layoutWater);
        layoutWorkout = findViewById(R.id.layoutWorkout);
        layoutQuestion = findViewById(R.id.layoutQuestion);

        layoutList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(_activity,DietListActivity.class));
            }
        });

        layoutWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(_activity,WaterActivity.class));
            }
        });

        layoutWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(_activity,WorkoutActivity.class));
            }
        });

        layoutQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(_activity,DiyetisyeneSorActivity.class));
            }
        });

    }

    private void setPopUpmenu() {

        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu menu = new PopupMenu(_activity, imageMenu);
                menu.inflate(R.menu.navigation_menu);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        if (item.getItemId() == R.id.sign_out) {
                            mAuth.signOut();
                            startActivity(new Intent(_activity, WelcomeActivity.class));
                            _activity.finish();
                        }
                        return false;
                    }
                });

                menu.show();

            }
        });
    }

    private void setUserName() {

        mRef.child("Users").child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                textYourName.setText(user.getName());
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}