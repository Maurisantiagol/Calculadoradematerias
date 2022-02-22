package com.example.calculadorapromediossdlm;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView materia;
    TextView numero;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        materia = findViewById(R.id.editTextMateria);
        numero = findViewById(R.id.editTextNumber);
        addButton = findViewById(R.id.AddCalf);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableLayout lista = (TableLayout) findViewById(R.id.Tabla);
                String[] cadena = {materia.getText().toString(), numero.getText().toString()};
                TableRow row = new TableRow(getBaseContext());
                TextView textView;
                TextView promedioText = (TextView) findViewById(R.id.resultado);
                if (lista.getChildCount() <= 15) {
                    if (numero.getText().toString().length() <= 2 && numero.getText().toString().length() >= 1 && Integer.parseInt(numero.getText().toString()) <= 10) {
                        if (materia.getText().toString().length() <= 35 && materia.getText().toString().length() >= 1) {
                            for (int i = 0; i < 2; i++) {

                                textView = new TextView(getBaseContext());
                                textView.setGravity(Gravity.CENTER_VERTICAL);
                                if (Integer.parseInt(numero.getText().toString()) <= 5) {
                                    textView.setBackgroundResource(R.color.red);
                                    textView.setTextColor(Color.WHITE);
                                } else {
                                    if (Integer.parseInt(numero.getText().toString()) <= 7) {
                                        textView.setBackgroundResource(R.color.yellow);
                                        textView.setTextColor(Color.BLACK);
                                    } else {
                                        if (Integer.parseInt(numero.getText().toString()) == 8) {
                                            textView.setBackgroundResource(R.color.green_light);
                                            textView.setTextColor(Color.BLACK);
                                        } else {
                                            if (Integer.parseInt(numero.getText().toString()) <= 7) {
                                            } else {
                                                textView.setBackgroundResource(R.color.green);
                                                textView.setTextColor(Color.BLACK);

                                            }
                                        }
                                    }
                                }
                                textView.setText(cadena[i]);
                                textView.setPadding(5, 5, 5, 5);

                                if (i == 1) {
                                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                }

                                row.addView(textView);

                            }
                            lista.addView(row);
                            promedioText.setText(Integer.toString(promediar()));
                        } else {
                            errMaxCar();
                        }
                    } else {
                        errMaxNum();
                    }
                } else {
                    errMaxMat();
                }
            }
        });
    }

    public int promediar() {
        int promedio = 0;
        String text;
        TextView cell;
        TableLayout lista = (TableLayout) findViewById(R.id.Tabla);
        for (int i = 1; i < lista.getChildCount(); i++) {
            TableRow row = (TableRow) lista.getChildAt(i);
            cell = (TextView) row.getChildAt(1);
            text = cell.getText().toString();
            promedio = promedio + (Integer.parseInt(text));

        }
        promedio = promedio / (lista.getChildCount() - 1);

        return promedio;
    }

    public void errMaxMat() {

        Toast.makeText(this, "Solo puedes promediar 15 materias", Toast.LENGTH_SHORT).show();

    }

    public void errMaxCar() {
        Toast.makeText(this, "Escribe una materia entre 0 y 35 caracteres", Toast.LENGTH_SHORT).show();

    }

    public void errMaxNum() {
        Toast.makeText(this, "Escribe una calificacion entre 0 y 10", Toast.LENGTH_SHORT).show();

    }
}