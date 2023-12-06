package com.phttproduction.sadasdgsfdgf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Spinner spinner;
    ArrayList spinnerArrayList;

    int quantity = 0;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName;
    double price;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createSpinner();
        createMap();
        userNameEditText = findViewById(R.id.editTextText);
    }



    public void plus(View view) {
        TextView quantityText = findViewById(R.id.quantityTextView);
        quantity +=1;
        quantityText.setText("" + quantity);
        TextView priceText = findViewById(R.id.priceTextView);
        priceText.setText("" + (price * quantity) + "$");
    }


    public void minus(View view) {
        TextView quantityText = findViewById(R.id.quantityTextView);
        if (!(quantity<1)){
            quantity -= 1;
            quantityText.setText("" + quantity);
        }
        TextView priceText = findViewById(R.id.priceTextView);
        priceText.setText("" + (price * quantity) + "$");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double)goodsMap.get(goodsName);
        TextView priceText = findViewById(R.id.priceTextView);
        priceText.setText("" + (price * quantity) + "$");
        ImageView goodsImageView = findViewById(R.id.imageView);
        if (goodsName.equals("Guitar")) {
            goodsImageView.setImageResource(R.drawable.guitar);
        }
        else if (goodsName.equals("Drums")) {
            goodsImageView.setImageResource(R.drawable.drums);
        }
        else if (goodsName.equals("Balalaika")) {
            goodsImageView.setImageResource(R.drawable.balalaika);
        }
        else {
            goodsImageView.setImageResource(R.drawable.guitar);
        }

    }

    void createSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("Guitar");
        spinnerArrayList.add("Drums");
        spinnerArrayList.add("Balalaika");

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }
    void createMap() {
        goodsMap = new HashMap();
        goodsMap.put("Guitar", 600.0);
        goodsMap.put("Drums", 1700.0);
        goodsMap.put("Balalaika", 500.0);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCard(View view) {
        Order order = new Order();
        order.username = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.orderPrice = quantity*price;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userNameForIntent", order.username);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("orderPrice", order.orderPrice);
        orderIntent.putExtra("orderQuantity", order.quantity);
        startActivity(orderIntent);
    }
}