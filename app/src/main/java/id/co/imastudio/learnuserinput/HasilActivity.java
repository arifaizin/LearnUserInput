package id.co.imastudio.learnuserinput;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class HasilActivity extends AppCompatActivity {

    TextView sisi, jenis, hasil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        sisi = (TextView) findViewById(R.id.tvSisi);
        jenis = (TextView) findViewById(R.id.tvJenis);
        hasil = (TextView) findViewById(R.id.tvHasil);

        //terima data
        String dataSisi = getIntent().getStringExtra(Konstanta.DATA_SISI);
        String dataJenis = getIntent().getStringExtra(Konstanta.DATA_JENIS);
        String dataHasil = getIntent().getStringExtra("DATA_HASIL");
        Log.d("HasilActivity",dataSisi);

        sisi.setText(dataSisi);
        jenis.setText(dataJenis);
        hasil.setText(dataHasil);

    }
}
