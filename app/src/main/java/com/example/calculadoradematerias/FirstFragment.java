package com.example.calculadoradematerias;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {
    TextView materia;
    TextView numero;
    Button addButton;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first, container, false);

        materia = v.findViewById(R.id.editTextMateria);
        numero = v.findViewById(R.id.editTextNumber);
        addButton = v.findViewById(R.id.AddCalf);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableLayout lista = (TableLayout) getActivity().findViewById(R.id.Tabla);
                String[] cadena = {materia.getText().toString(), numero.getText().toString()};
                TableRow row = new TableRow(getActivity().getBaseContext());
                TextView textView;
                TextView promedioText=(TextView)getActivity().findViewById(R.id.resultado);
                if (lista.getChildCount() <= 15) {
                    if (numero.getText().toString().length() <= 2 && numero.getText().toString().length() >= 1 && Integer.parseInt(numero.getText().toString()) <= 10) {
                        if (materia.getText().toString().length() <= 35 && materia.getText().toString().length() >= 1) {
                            for (int i = 0; i < 2; i++) {

                                textView = new TextView(getActivity().getBaseContext());
                                textView.setGravity(Gravity.CENTER_VERTICAL);
                                textView.setBackgroundResource(R.color.red_normal);
                                textView.setText(cadena[i]);
                                textView.setTextColor(Color.WHITE);
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
        // Inflate the layout for this fragment
        return v;
    }
    public int promediar(){
        int promedio=0;
        String text;
        TextView cell;
        TableLayout lista = (TableLayout) getActivity().findViewById(R.id.Tabla);
        for (int i=1;i<lista.getChildCount();i++){
            TableRow row = (TableRow)lista.getChildAt(i);
              cell = (TextView) row.getChildAt(1);
              text=cell.getText().toString();
              promedio=promedio+(Integer.parseInt(text));

        }
        promedio=promedio/(lista.getChildCount()-1);

        return promedio;
    }
    public void errMaxMat() {

        Toast.makeText(this.getActivity(), "Solo puedes promediar 15 materias", Toast.LENGTH_SHORT).show();

    }

    public void errMaxCar() {
        Toast.makeText(this.getActivity(), "Escribe una materia entre 0 y 35 caracteres", Toast.LENGTH_SHORT).show();

    }

    public void errMaxNum() {
        Toast.makeText(this.getActivity(), "Escribe una calificacion entre 0 y 10", Toast.LENGTH_SHORT).show();

    }

}