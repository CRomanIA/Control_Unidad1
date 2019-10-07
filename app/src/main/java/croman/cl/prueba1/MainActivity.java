package croman.cl.prueba1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG-";
    private TextView /*tvProductoRef,*/etCodigoRef, etDescripcionRef, etUbicacionRef, etFechaCompraRef,
            etStockRef, etCostoRef, etVentaRef;

    private Button btnIngresarRef, btnEliminarRef;

    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initResources();
    }
    private void initResources(){
        //tvProductoRef = findViewById(R.id.tvProducto);
        etCodigoRef = findViewById(R.id.etCodigo);
        etDescripcionRef = findViewById(R.id.etDescripcion);
        etUbicacionRef = findViewById(R.id.etUbicacion);
        etFechaCompraRef = findViewById(R.id.etFechaCompra);
        etStockRef = findViewById(R.id.etStock);
        etCostoRef = findViewById(R.id.etCosto);
        etVentaRef = findViewById(R.id.etVenta);

        btnIngresarRef = findViewById(R.id.btnIngresar);

        btnIngresarRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateForm();
            }
        });
    }

    private void validateForm(){
        try {
            //String productValue =tvProductoRef.getText().toString();
            String codeValue = etCodigoRef.getText().toString();
            String descriptionValue = etDescripcionRef.getText().toString().replaceAll(" ", "");
            String ubicationValue = etUbicacionRef.getText().toString().replaceAll(" ", "");
            String datePurchaseValue = etFechaCompraRef.getText().toString();
            String stockValue = (etStockRef.getText().toString());
            String costValue = etCostoRef.getText().toString();
            String saleValue = etVentaRef.getText().toString();

            //tvProductoRef.setText(productValue);

            //Log.d(TAG, "Producto " + productValue);
            Log.d(TAG, "Codigo " + codeValue);
            Log.d(TAG, "Descripcion " + descriptionValue);
            Log.d(TAG, "Ubicacion " + ubicationValue);
            Log.d(TAG, "Fecha Compra " + datePurchaseValue);
            Log.d(TAG, "Stock " + stockValue);
            Log.d(TAG, "Costo " + costValue);
            Log.d(TAG, "Venta " + saleValue);

            etDescripcionRef.setText(descriptionValue);
            etUbicacionRef.setText(ubicationValue);

            if (codeValue.isEmpty() || descriptionValue.isEmpty() || ubicationValue.isEmpty() ||
            datePurchaseValue.isEmpty() || stockValue.isEmpty() || costValue.isEmpty() || saleValue.isEmpty()){
               Log.d(TAG,"No existen Datos ingresados");

                //TODO: Operador Ternario: si el valor esta vacio, con el '?' identificamos según la primera validacion
                //TODO: sí es verdadero y según la segunda validacion es falsa ()
               etCodigoRef.setError(codeValue.isEmpty() ? getResources().getString(R.string.error_empty_code): null);
               etDescripcionRef.setError(descriptionValue.isEmpty() ? getResources().getString(R.string.error_empty_description): null);
               etUbicacionRef.setError(ubicationValue.isEmpty() ? getResources().getString(R.string.error_empty_ubication) : null);
               etFechaCompraRef.setError(datePurchaseValue.isEmpty() ? getResources().getString(R.string.error_empty_date_purchase) : null);
               etStockRef.setError(stockValue.isEmpty() ? getResources().getString(R.string.error_empty_stock) : null);
               etCostoRef.setError(costValue.isEmpty() ? getResources().getString(R.string.error_empty_cost):null);
               etVentaRef.setError(saleValue.isEmpty() ? getResources().getString(R.string.error_empty_sale) : null);

            }else{
                boolean validateCode = validateCode(codeValue);
                boolean validateDescription = validateDescription(descriptionValue);
                boolean validateUbication = validateUbication(ubicationValue);
                boolean validateDatePurchase = validateDatePurchase(datePurchaseValue);
                boolean validateStock = validateStock(stockValue);
                boolean validateCost = validateCost(costValue);
                boolean validateSale = validateSale(saleValue);

                if (validateCode && validateDescription && validateUbication && validateDatePurchase &&
                validateStock && validateCost && validateSale){
                    Toast.makeText(MainActivity.this,  getResources().getString(R.string.success_validation), Toast.LENGTH_LONG);
                    //en caso de evitar que se muestre algun error.
                    etCodigoRef.setError(null);
                    etDescripcionRef.setError(null);
                    etUbicacionRef.setError(null);
                    etFechaCompraRef.setError(null);
                    etStockRef.setError(null);
                    etCostoRef.setError(null);
                    etVentaRef.setError(null);
                }

            }

    }catch (Exception e){
            Log.e(TAG, "No existen datos ingresados" , e);
        }
    }

    private boolean validateCode(String code) {
     try{
            //boolean resultCode = code.matches("")
     }catch (Exception e){
         Log.e(TAG , "Codigo mal generado", e);
     }
        return false;
    }

    private boolean validateDescription(String descriptionValue) {
        return false;
    }

    private boolean validateUbication(String ubicationValue) {
        return false;
    }

    private boolean validateDatePurchase(String datePurchaseValue) {
        return  false;
    }

    private boolean validateStock(String stockValue) {
       try {
           boolean resultado;
           try {
               Integer.parseInt(stockValue);
               resultado = true;
           } catch (NumberFormatException excepcion) {
               resultado = false;
           }
           return resultado;

       }catch (Exception e){
            Log.e(TAG, "la validación no es númerica.", e);
       }
        return false;
    }

    private boolean validateCost(String costValue) {
        try {
            boolean resultado;
            try {
                Double.parseDouble(costValue);
                resultado = true;
            } catch (NumberFormatException excepcion) {
                resultado = false;
            }
            return resultado;

        }catch (Exception e){
            Log.e(TAG, "la validación no es decimal.", e);
        }
        return false;
    }

    private boolean validateSale(String saleValue) {
        try {
            boolean resultado;
            try {
                Double.parseDouble(saleValue);
                resultado = true;
            } catch (NumberFormatException excepcion) {
                resultado = false;
            }
            return resultado;

        }catch (Exception e){
            Log.e(TAG, "la validación no es decimal.", e);
        }
        return false;
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput();
        }

    };
}
