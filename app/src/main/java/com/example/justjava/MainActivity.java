package com.example.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        EditText editText = findViewById(R.id.name_edit_text);
        String name = editText.getText().toString();
        displayMessage(createOrderSummary(calculatePrice(hasWhippedCream,hasChocolate), hasWhippedCream, hasChocolate, name));
        /*
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java order to "+ name);
        intent.putExtra(Intent.EXTRA_TEXT,createOrderSummary(calculatePrice(hasWhippedCream,hasChocolate), hasWhippedCream, hasChocolate, name));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }*/
    }

    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String name){

        return getString(R.string.order_summary_name,name) + getString(R.string.add_w_cream,hasWhippedCream) + getString(R.string.add_chocolate,hasChocolate)
                + getString(R.string.quantity_order_summary,quantity) + getString(R.string.total_order_summary, NumberFormat.getCurrencyInstance().format(price))+ getString(R.string.thank_you);
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice(boolean hasWhippedCream,boolean hasChocolate) {
        int basePrice = 5;
        if(hasWhippedCream)
            basePrice++;
        if(hasChocolate)
            basePrice+=2;
        return quantity*basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    public void increment(View view){
        if(quantity<100){
            quantity++;
            displayQuantity(quantity);
        } else{
            Context context = getApplicationContext();
            CharSequence text = "You can't order more than 100 cups!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void decrement(View view){
        if(quantity>1){
            quantity--;
            displayQuantity(quantity);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "You can't order less than 1 cup!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
