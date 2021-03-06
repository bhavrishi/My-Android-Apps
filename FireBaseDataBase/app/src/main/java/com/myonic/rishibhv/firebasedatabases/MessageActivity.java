package com.myonic.rishibhv.firebasedatabases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MessageActivity extends AppCompatActivity {

    private Button btnBack;
    private Button btnSend;
    private EditText edtSentText;
    private TextView tvAuthor;
    private TextView tvTime;
    private TextView tvBody;
    private static final String REQUIRED = "Required";

    private FirebaseUser user;

    private DatabaseReference mDatabase;
    private DatabaseReference mMessageReference;
    private ValueEventListener mMessageListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        btnSend = (Button) findViewById(R.id.btn_send);
        btnBack = (Button) findViewById(R.id.btn_back);
        edtSentText = (EditText) findViewById(R.id.edt_sent_text);
        tvAuthor = (TextView) findViewById(R.id.tv_author);
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvBody = (TextView) findViewById(R.id.tv_body);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mMessageReference = FirebaseDatabase.getInstance().getReference("message");
        user = FirebaseAuth.getInstance().getCurrentUser();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitMessage();
                edtSentText.setText("");
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();

        ValueEventListener messageListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Message message = dataSnapshot.getValue(Message.class);

                    Log.e("demo", "onDataChange: Message data is updated: " + message.author + ", " + message.time + ", " + message.body);

                    tvAuthor.setText(message.author);
                    tvTime.setText(message.time);
                    tvBody.setText(message.body);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.e("demo", "onCancelled: Failed to read message");

                tvAuthor.setText("");
                tvTime.setText("");
                tvBody.setText("onCancelled: Failed to read message!");
            }
        };

        mMessageReference.addValueEventListener(messageListener);

        // copy for removing at onStop()
        mMessageListener = messageListener;
    }
    @Override
    protected void onStop() {
        super.onStop();

        if (mMessageListener != null) {
            mMessageReference.removeEventListener(mMessageListener);
        }
    }
    private void submitMessage() {
        final String body = edtSentText.getText().toString();

        if (TextUtils.isEmpty(body)) {
            edtSentText.setError(REQUIRED);
            return;
        }

        // User data change listener
        mDatabase.child("users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                if (user == null) {
                    Log.e("demo", "onDataChange: User data is null!");
                    Toast.makeText(MessageActivity.this, "onDataChange: User data is null!", Toast.LENGTH_SHORT).show();
                    return;
                }

                writeNewMessage(body);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("demo", "onCancelled: Failed to read user!");
            }
        });
    }

   private void writeNewMessage(String body) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        Message message = new Message(getUsernameFromEmail(user.getEmail()), body, time);

        mMessageReference.setValue(message);
    }

    private String getUsernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }
}
