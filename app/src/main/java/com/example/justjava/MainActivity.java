package com.example.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

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
        CheckBox checkBox = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = checkBox.isChecked();
        displayMessage(createOrderSummary(calculatePrice(),hasWhippedCream));
    }

    private String createOrderSummary(int price, boolean hasWhippedCream){

        return "Name: Norberto Vega\nAdd whipped cream? " + hasWhippedCream + "\nQuantity: " + quantity + "\nTotal: $" + price + "\nThank you!!!";
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    public void increment(View view){
        quantity++;
        displayQuantity(quantity);
    }

    public void decrement(View view){
        if(quantity>1)
            quantity--;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
