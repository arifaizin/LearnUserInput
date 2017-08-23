package id.co.imastudio.learnuserinput;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText sisi, hasil;
    Button tombolHitung;
    Spinner spinner;

    String[] pilihanHitung = {
            "Volume",
            "Luas Permukaan",
            "Keliling"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                //kirim data
                Intent pindah = new Intent(MainActivity.this, HasilActivity.class);
                pindah.putExtra(Konstanta.DATA_SISI,sisi.getText().toString());
                pindah.putExtra(Konstanta.DATA_JENIS,spinner.getSelectedItem().toString());
                pindah.putExtra("DATA_HASIL",hasil.getText().toString());

                startActivity(pindah);

            }
        });

        sisi = (EditText) findViewById(R.id.edSisi);
        hasil = (EditText) findViewById(R.id.edHasil);
        tombolHitung = (Button) findViewById(R.id.btnHitung);
        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, pilihanHitung);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Kamu memilih " + position, Toast.LENGTH_SHORT).show();
                if (position == 0) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tombolHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (spinner.getSelectedItem().toString().equals(pilihanHitung[0])) {
                        //rumus volume
                        hitungVolume();
                    } else if (spinner.getSelectedItem().toString().equals(pilihanHitung[1])) {
                        //rumus luas permukaan
                        Double nilaisisi = Double.parseDouble(sisi.getText().toString());
                        hitungLuasPermukaan(nilaisisi);
                    } else if (spinner.getSelectedItem().toString().equals("Keliling")) {
                        //rumus keliling
                        Double nilaiHasil = hitungKeliling();
                        hasil.setText(nilaiHasil.toString());
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error : " + e, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private Double hitungKeliling() {
        Double nilaisisi = Double.parseDouble(sisi.getText().toString());
        Double nilaihasil = 12 * nilaisisi;
        return nilaihasil;
    }

    private void hitungLuasPermukaan(Double nilaisisi) {
        Double nilaihasil = 6 * nilaisisi * nilaisisi;
        hasil.setText(nilaihasil.toString());
    }

    private void hitungVolume() {
        Double nilaisisi = Double.parseDouble(sisi.getText().toString());
        Double nilaihasil = nilaisisi * nilaisisi * nilaisisi;
        hasil.setText(String.format("%.3f", nilaihasil));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_send) {
            Toast.makeText(this, "Calling....", Toast.LENGTH_SHORT).show();
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, "www.idn.id");
            startActivity(Intent.createChooser(share, "Share Link"));

        } else if (id == R.id.action_panggil) {
            Intent telfon = new Intent(Intent.ACTION_CALL, Uri.parse("tel:085740482440"));

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[] {Manifest.permission.CALL_PHONE},
                        1);
            }
            startActivity(telfon);
        }

        return super.onOptionsItemSelected(item);
    }
}
