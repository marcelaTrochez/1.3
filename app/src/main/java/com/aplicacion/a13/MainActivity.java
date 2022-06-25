package com.aplicacion.a13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aplicacion.a13.configuracion.SQLiteConexion;
import com.aplicacion.a13.configuracion.Transacciones;

public class MainActivity extends AppCompatActivity {

    EditText name, firstName, age, mail, address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.txtName);
        firstName =(EditText) findViewById(R.id.txtFirstName);
        age = (EditText)  findViewById(R.id.txtAge);
        mail = (EditText) findViewById(R.id.txtMail);
        address = (EditText) findViewById(R.id.txtAddress);
        Button btnGuardar = (Button) findViewById(R.id.btnSave);
        Button btnSearchs=(Button)findViewById(R.id.btnSearchs);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarPersonas();
            }
        });

        btnSearchs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), consulta.class);
                startActivity(intent);
            }
        });
    }
    private void AgregarPersonas()
    {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, name.getText().toString());
        valores.put(Transacciones.apellidos, firstName.getText().toString());
        valores.put(Transacciones.edad, age.getText().toString());
        valores.put(Transacciones.correo, mail.getText().toString());
        valores.put(Transacciones.direccion, address.getText().toString());

        Long resultado = db.insert(Transacciones.tablaPersonas,Transacciones.id,valores);

        Toast.makeText(getApplicationContext(), "Registro ingresado " + resultado.toString(),Toast.LENGTH_LONG).show();

        db.close();

        LimpiarPantalla();

    }

    private void LimpiarPantalla()
    {
        name.setText("");
        firstName.setText("");
        age.setText("");
        mail.setText("");
        address.setText("");
    }

}