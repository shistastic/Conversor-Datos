package com.example.dataconversor;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    //Inicializar Variables "Global"
    Spinner conv;

    //onCreate Ejecutará el código en su interior al iniciar la aplicación
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Arranca la escena seleccionada "activity_main.xml"


        /************Lista Desplegable*****************/
        //Array de cadenas de caracteres(Strings) que contiene los elementos a mostrar por el desplegable
        String[] elemConv = new String[] {
                "String a Binario", "String a Hexadecimal",
                "Hexadecimal a String", "Hexadecimal a Binario",
                "Binario a String", "Binario a Hexadecimal"
        };
        conv = (Spinner)findViewById(R.id.conv);   //Asignar nombre de variable de un elemento seleccionado por Id
        //ArrayAdapter para adaptador los elementos de nuestro array a una lista vertical especificada.
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, elemConv);
        conv.setAdapter(adaptador); //Al desplegable le añadimos los elementos adaptados.
    }

    /************Botón*****************/
    //Al pulsar botón con onClick="convertir" se ejecutará el codigo en el interior del siguiente metodo
    public void convertir(View view) throws UnsupportedEncodingException {
        //Asignar nombre de variable a distintos elementos seleccionados por "Id"
        TextView result = (TextView)findViewById(R.id.resultado);
        TextInputLayout aConvertir = (TextInputLayout)findViewById(R.id.aConv);

        //Recoger valor como String de elemento introducido
        String aConv = String.valueOf(aConvertir.getEditText().getText());
        //Recoger valor como String de elemento seleccionado
        String valDesplegable = conv.getSelectedItem().toString();
        //Chequear si alguno de los campos se encuentra vacio al pulsar botón
        //Campos vacíos devolveran un cuadro comunicando el error correspondiente.
        if (TextUtils.isEmpty(aConv)) {
            aConvertir.setError("Campo vacío, introduce un valor válido.");
        } else{
            aConvertir.setError(null); //Campo no esta vacío se descarta el error anterior.
        }

        //Inicializar variable del resultado
        String res = "";

        /************Conversión String a Binario*****************/
        if (valDesplegable == "String a Binario"){
            try {
                res = "";
                char[] messChar = aConv.toCharArray(); //Separar e introducir en un array la cadena de texto por caracteres

                for (int i = 0; i < messChar.length; i++) { //Por cada caracter en el array se reproduce el bucle
                    res += Integer.toBinaryString(messChar[i]) + " ";   //Pasa cada caracter a binario e introduce un espacio
                }
            }catch (Exception e){
                printStackTrace(e);
            }

        }
        /************Conversión String a Hexadecimal*****************/
        else if(valDesplegable == "String a Hexadecimal"){
            try {
                res = "";
                char[] messChar = aConv.toCharArray(); //Separar e introducir en un array la cadena de texto por caracteres

                for (int i = 0; i < messChar.length; i++) { //Por cada caracter en el array se reproduce el bucle
                    res += Integer.toHexString(messChar[i]) + " ";   //Pasa cada caracter a binario e introduce un espacio
                }
            }catch (Exception e){
                printStackTrace(e);
            }
        }
        /************Conversión Hexadecimal a String*****************/
        else if(valDesplegable == "Hexadecimal a String") {
            try {
                //Inicializar StringBuilder(16Chars)
                StringBuilder strCons = new StringBuilder();
                for (int i = 0; i < aConv.length(); i += 2) {
                    String str = aConv.substring(i, i + 2);
                    strCons.append((char) Integer.parseInt(str, 16));
                }
                res = strCons.toString().trim();
                result.setText(res);
            }catch (Exception e){
                printStackTrace(e);
            }
        }
        /************Conversión Hexadecimal a Binario*****************/
        else if(valDesplegable == "Hexadecimal a Binario"){
            try {
                res = new BigInteger(aConv, 16).toString(2);
            }catch (Exception e){
                printStackTrace(e);
            }
            //Nota: BigInteger se utiliza siempre y cuando se necesita una variable capaz
            //de almacenar una cantidad mayor de valores que por ejemplo un long o un int.
            //Al tratar con numeros binarios puede resultar conveniente ya que se tienden
            //a alargar, por ejemplo la palabra "hello" ocupa 48bits/5bytes.
        }
        /************Conversión Binario a String*****************/
        else if(valDesplegable == "Binario a String") {
            try {
                int bin = Integer.parseInt(aConv, 2);   //Almacenar en int, String introducido en base 2
                res = new Character((char) bin).toString();  //almacenar como String, int bin como String "texto"
            }catch (Exception e){
                printStackTrace(e);
            }

        }
        /************Conversión Binario a Hexadecimal*****************/
        else if(valDesplegable == "Binario a Hexadecimal"){
            try {
                int bin = Integer.parseInt(aConv, 2);   //Almacenar en int, String introducido en base 2
                res = Integer.toHexString(bin); //almacenar como String, int bin como String Hexadecimal
            }catch (Exception e){
                printStackTrace(e);
            }
        }

        result.setText(res); //Agregar resultado de conversión a elemento definido como "result"
    }

    private void printStackTrace(Exception e) {

    }

}