package android.sankara.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.dd.morphingbutton.MorphingButton;

public class Issue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_issue);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getIntent().getStringExtra("Tell us your concern");

        final MorphingButton btnMorph1 = (MorphingButton) findViewById(R.id.btnMorph1);
        btnMorph1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                morphToSuccess(btnMorph1);
            }
        });
        final MorphingButton btnMorph2 = (MorphingButton) findViewById(R.id.btnMorph2);
        btnMorph2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                morphToSuccess(btnMorph2);
            }
        });
        final MorphingButton btnMorph3 = (MorphingButton) findViewById(R.id.btnMorph3);
        btnMorph3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                morphToSuccess(btnMorph3);
            }
        });
        final MorphingButton btnMorph4 = (MorphingButton) findViewById(R.id.btnMorph4);
        btnMorph4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                morphToSuccess(btnMorph4);
            }
        });
        final MorphingButton btnMorph5 = (MorphingButton) findViewById(R.id.btnMorph5);
        btnMorph5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                morphToSuccess(btnMorph5);
            }
        });


        Button btn = (Button) findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyShortcuts.showToast("Your concern submitted successfully, we will working on it shortly!",getBaseContext());
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void morphToSuccess(final MorphingButton btnMorph) {
        btnMorph.setVisibility(View.VISIBLE);
        MorphingButton.Params circle = MorphingButton.Params.create()
                .duration(getResources().getInteger(R.integer.mb_animation))
                .cornerRadius((int) getResources().getDimension(R.dimen.mb_height_56))
                .width((int) getResources().getDimension(R.dimen.mb_height_56))
                .height((int) getResources().getDimension(R.dimen.mb_height_56))
                .color(getResources().getColor(R.color.mb_green))
                .colorPressed(getResources().getColor(R.color.mb_green_dark))
                .icon(R.drawable.ic_done);
        btnMorph.morph(circle);
    }
}
