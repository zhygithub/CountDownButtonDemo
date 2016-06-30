package network.scau.com.countdownbuttondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn)
    Button btn;

    @Bind(R.id.cdb)
    CountDownButton cdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.cdb)
    public void countting() {
        Toast.makeText(this,"countting",Toast.LENGTH_LONG).show();
        cdb.countDown();

    }

    @OnClick(R.id.btn)
    public void shutdown(){
        Toast.makeText(this,"shutdown",Toast.LENGTH_LONG).show();
        cdb.shutDown();
    }
}
