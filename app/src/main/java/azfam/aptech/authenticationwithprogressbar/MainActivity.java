package azfam.aptech.authenticationwithprogressbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
ProgressBar progressBar;
EditText username,password;
private FirebaseAuth firebaseAuth;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar =findViewById(R.id.pro);
        btn=findViewById(R.id.btn);
        username =findViewById(R.id.username);
        password =findViewById(R.id.password);

        firebaseAuth = FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                signin(username.getText().toString(),password.getText().toString());
            }
        });

    }

    private void signin(String email,String password){
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful())
                   {
                       progressBar.setVisibility(View.INVISIBLE);
                       Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                   }
                   else {
                       progressBar.setVisibility(View.INVISIBLE);
                       Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                   }
                    }
                });
    }
}