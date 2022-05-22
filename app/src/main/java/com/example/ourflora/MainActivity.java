package com.example.ourflora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.ourflora.Adapter.AccessoryAdapter;
import com.example.ourflora.Adapter.ProductAdapter;
import com.example.ourflora.Adapter.ProductCategoryAdapter;
import com.example.ourflora.model.Accessory;
import com.example.ourflora.model.ProductCategory;
import com.example.ourflora.model.Products;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProductCategoryAdapter productCategoryAdapter;
    RecyclerView productCatRecycler,prodItemRecycler,accessItemRecycler;
    ProductAdapter productAdapter;
    AccessoryAdapter accessoryAdapter;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.propic_img);
        registerForContextMenu(img);

        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(new ProductCategory(1,"Most Popular"));
        productCategoryList.add(new ProductCategory(2,"Indoor Plants"));
        productCategoryList.add(new ProductCategory(3,"Outdoor Plants"));
        productCategoryList.add(new ProductCategory(4,"Products"));

        setProductRecycler(productCategoryList);

        List<Products> productsList = new ArrayList<>();
        productsList.add(new Products(1,"Snake Plant","Nigeria","Rs 799.00",R.drawable.snake_plantpic));
        productsList.add(new Products(2,"Philodendron","Brazil","Rs 1,299.00",R.drawable.philodendron));
        productsList.add(new Products(3,"Propeller Plant","Madagascar","Rs 499.00",R.drawable.senecio));
        productsList.add(new Products(4,"Sage","India","Rs 299.00",R.drawable.sage));
        productsList.add(new Products(5,"Aloe vera","India","Rs 149.00",R.drawable.aloe));
        productsList.add(new Products(6,"Dracaena","Sudan","Rs 1399.00",R.drawable.dracaena));
        productsList.add(new Products(7,"Palm Tree","KSA","Rs 999.00",R.drawable.palmtree));
        setProdItemRecyclerRecycler(productsList);

        List<Accessory> accessoryList = new ArrayList<>();
        accessoryList.add(new Accessory(1,"Hanging Glass Pots","13 x 14.5 cm","Rs 299.00",R.drawable.hangingglasspotters));
        accessoryList.add(new Accessory(2,"Potters Stand","25 x 40cm","Rs 629.00",R.drawable.potters));
        accessoryList.add(new Accessory(3,"Threaded Hanging Pot","15 x 17cm","Rs 399.00",R.drawable.hangingpots));


        setAccessoryRecycler(accessoryList);
    }

    private void setProductRecycler(List<ProductCategory> productCategoryList){
        productCatRecycler = findViewById(R.id.cat_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        productCatRecycler.setLayoutManager(layoutManager);
        productCategoryAdapter = new ProductCategoryAdapter(this,productCategoryList);
        productCatRecycler.setAdapter(productCategoryAdapter);
    }

    private void setProdItemRecyclerRecycler(List<Products> productsList){
        prodItemRecycler = findViewById(R.id.product_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        prodItemRecycler.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(this,productsList);
        prodItemRecycler.setAdapter(productAdapter);
    }

    private void setAccessoryRecycler(List<Accessory> accessoryList){
        accessItemRecycler = findViewById(R.id.accessories_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        accessItemRecycler.setLayoutManager(layoutManager);
        accessoryAdapter = new AccessoryAdapter(this,accessoryList);
        accessItemRecycler.setAdapter(accessoryAdapter);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {

            case R.id.logout:
            {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                return true;
            }

        }
        return super.onContextItemSelected(item);
    }
}
