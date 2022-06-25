package com.aplicacion.a13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aplicacion.a13.configuracion.SQLiteConexion;
import com.aplicacion.a13.configuracion.Transacciones;

public class consulta extends AppCompatActivity {

    SQLiteConexion conexion;
    EditText id, Cname, CfirstName, Cage, Cmail, Caddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        // llamar a la conexion de bd sqlite

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);


        // Botones
        Button btnConsulta = (Button) findViewById(R.id.btnSearch);
        Button btnEliminar =  (Button) findViewById(R.id.btnDelete);
        Button btnActualizar =  (Button) findViewById(R.id.btnUpDate);


        id = (EditText) findViewById(R.id.txtCId);
        Cname = (EditText) findViewById(R.id.txtCName);
        CfirstName = (EditText) findViewById(R.id.txtCFirstName);
        Cage = (EditText) findViewById(R.id.txxCAge);
        Cmail = (EditText) findViewById(R.id.txtCMail);
        Caddress = (EditText) findViewById(R.id.txtCAddress);


        btnConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buscar();

            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Actualizar();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar();
            }
        });
    }
    private void Buscar() {
        SQLiteDatabase db = conexion.getWritableDatabase();

        /* Parametros de configuracion de la sentencia SELECT */

        String[] params = {id.getText().toString()};
        // parametro de la busqueda

        String[] fields = {Transacciones.nombres,
                Transacciones.apellidos,
                Transacciones.edad,
                Transacciones.correo,
                Transacciones.direccion};

        String wherecond = Transacciones.id + "=?";


        try {

            Cursor cdata = db.query(Transacciones.tablaPersonas, fields, wherecond, params, null, null, null);

            cdata.moveToFirst();


            Cname.setText(cdata.getString(0));
            CfirstName.setText(cdata.getString(1));
            Cage.setText(cdata.getString(2));
            Cmail.setText(cdata.getString(3));
            Caddress.setText(cdata.getString(4));


            Toast.makeText(getApplicationContext(), "Busqueda Exitosa", Toast.LENGTH_LONG).show();

        } catch (Exception ex) {
            ClearScreen();
            Toast.makeText(getApplicationContext(), "No se encontro ID", Toast.LENGTH_LONG).show();

        }
    }

    private void ClearScreen() {
    }
    private void Eliminar()
    {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {id.getText().toString()};
        String wherecond = Transacciones.id + "=?";
        db.delete(Transacciones.tablaPersonas, wherecond, params);
        Toast.makeText(getApplicationContext(), "ELIMINADA", Toast.LENGTH_LONG).show();
        ClearScreen();

    }

    private void Actualizar()
    {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {id.getText().toString()};
        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, Cname.getText().toString());
        valores.put(Transacciones.apellidos, CfirstName.getText().toString());
        valores.put(Transacciones.edad, Cage.getText().toString());
        valores.put(Transacciones.correo, Cmail.getText().toString());
        valores.put(Transacciones.direccion, Caddress.getText().toString());
        db.update(Transacciones.tablaPersonas, valores, Transacciones.id + "=?", params);
        Toast.makeText(getApplicationContext(), "ACTUALIZADA", Toast.LENGTH_LONG).show();
        ClearScreen();

    }


}

