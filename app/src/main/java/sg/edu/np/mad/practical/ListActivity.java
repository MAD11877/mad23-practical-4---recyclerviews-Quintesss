package sg.edu.np.mad.practical;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity implements UserRecyclerInterface {
    String title = "ListActivity";
    ArrayList<User> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //declare Recycler variables
        userArrayList = new ArrayList<>();
        RecyclerView userRecyclerView = findViewById(R.id.rvUser);

        createUserArray();
        AdapterUserRecycler adapter = new AdapterUserRecycler(this, userArrayList,this);
        userRecyclerView.setAdapter(adapter);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            profileViewAlert();
            }
        });*/
    }

    private void profileViewAlert(User selectedUser){
        AlertDialog.Builder userAlert = new AlertDialog.Builder(this);
        userAlert.setTitle("Profile");
        userAlert.setMessage(selectedUser.getName());
        userAlert.setCancelable(false);
        userAlert.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(title, "User Views!");
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                intent.putExtra("User", selectedUser);
                startActivity(intent);
            }
        });

        userAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(title, "User Cancelled!");
                finish();
            }
        });

        userAlert.show();
    }

    private int randomUsername(){
        Random ran = new Random();
        int myRandomNumber = ran.nextInt(999999999);
        return myRandomNumber;

    }

    void createUserArray(){
        for(int i=0; i<20; i++){
            Random rd = new Random();
            userArrayList.add(new User("Name"+randomUsername(),"Description "+randomUsername(),i, rd.nextBoolean()));
        }
    }

    @Override
    public void onUserClick(int position) {
        User selectedUser = userArrayList.get(position);
        profileViewAlert(selectedUser);
        /*Intent intent = new Intent(ListActivity.this, MainActivity.class);
        intent.putExtra("User", userArrayList.get(position));
        startActivity(intent);*/
    }

}