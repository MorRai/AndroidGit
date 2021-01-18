/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.project2;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int numberOfCoffees = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox hasWhippedCream = (CheckBox) findViewById(R.id.whipped_cream);
        CheckBox hasChocоlate = (CheckBox) findViewById(R.id.Chocоlate);
        int cost = calculatePrice(hasWhippedCream.isChecked(),hasChocоlate.isChecked());
        String priceMessage = createOrderSummary(cost, hasWhippedCream.isChecked(),hasChocоlate.isChecked()) ;
//        displayMessage(priceMessage);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
       // intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java");
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void increment (View view) {
        if (numberOfCoffees == 100) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Количество чашек кофе не может быть больше 100!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        else {
            numberOfCoffees = numberOfCoffees + 1;
        }
        displayQuantity(numberOfCoffees);
    }

    public void decrement (View view) {
        //int numberOfCoffees = 2;
        if (numberOfCoffees == 1) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Количество чашек кофе не может быть меньше 1!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        else {
            numberOfCoffees = numberOfCoffees - 1;
        }
        displayQuantity(numberOfCoffees);
    }



    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocоlate) {
        int cost = 5;
        if (hasWhippedCream) {
            cost = cost + 1;
        }
        if (hasChocоlate) {
            cost = cost + 2;
        }
        int price = numberOfCoffees * cost;
        return price;
    }

    private String createOrderSummary(int cost, boolean hasWhippedCream, boolean hasChocоlate) {
        EditText NameText = (EditText) findViewById(R.id.name_text_view);

        String priceMessage = "Name: "+NameText.getText() +"\nAdd whipped cream? " + hasWhippedCream +"\nAdd Chocоlate? " + hasChocоlate +"\nQuantity: " + numberOfCoffees +"\nTotal: $ " + cost;
        priceMessage += "\n"+ getString(R.string.thank_you) +"!" ;
        return priceMessage;
    }


    /**
     * This method displays the given price on the screen.
     */


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView order_summary_text_view = (TextView) findViewById(R.id.order_summary_text_view);
        order_summary_text_view.setText(message);
    }

}