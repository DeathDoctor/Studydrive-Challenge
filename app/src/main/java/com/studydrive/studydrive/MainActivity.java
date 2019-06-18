package com.studydrive.studydrive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // declaration section

    private Button addProducerButton;
    private Button addCustomerButton;

    private ArrayList<ProducerCustomerItem> producerCustomerList;

    private RecyclerView producerCustomerRecyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    // constants for the intervals in order to add/delete producer/customer
    private int addProducerInterval = 3000;
    private int removeCustomerInterval = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        producerCustomerList = new ArrayList<>();

        // linking the declared button to the views from the UI
        addProducerButton = findViewById(R.id.add_producer);
        addCustomerButton = findViewById(R.id.add_customer);

        // building the recyclerView by setting the layout manager and the adapter
        producerCustomerRecyclerView = findViewById(R.id.producer_customers_list);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewAdapter = new ProducerCustomerAdapter(producerCustomerList);
        producerCustomerRecyclerView.setLayoutManager(layoutManager);
        producerCustomerRecyclerView.setAdapter(recyclerViewAdapter);

        // defining onClickListeners for the two buttons that add producer/customer
        addProducerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProducer();
            }
        });

        addCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    addCustomer();
            }
        });

        // starting the threads that will run in loop
        producerAddingThread.start();
        customerRemovalThread.start();
    }

    // function that adds a producer to the list
    public void addProducer() {

        // adding the item in the position 0 in order to see the addings in case of a long list
        this.producerCustomerList.add(0, new ProducerCustomerItem("Producer"));

        // this show an little animation while an item is added to the list
        recyclerViewAdapter.notifyItemInserted(0);

    }

    // function that adds a producer to the list
    public void addCustomer() {

        // adding the item in the position 0 in order to see the addings in case of a long list
        this.producerCustomerList.add(0, new ProducerCustomerItem("Customer"));

        // this show an little animation while an item is added to the list
        recyclerViewAdapter.notifyItemInserted(0);


    }

    // function that removes a customer from the list
    public void deleteCustomer() {

        // 'found' indicates if we have found a customer in our list
        boolean found = false;

        // checkingif the list is not empty
        if(this.producerCustomerList.size()>0) {

            // intiating 'i' which will be the counter to 0
            int i = 0;

            // iterating over the list until we find a customer or we reach the end of the list
            while (!found && (i<this.producerCustomerList.size())) {

                // checking if item is a customer
                found = this.producerCustomerList.get(i).getName() == "Customer";
                if (found) {


                    // if so we remove the item from the list
                    this.producerCustomerList.remove(i);

                    // this show an little animation while an item is added to the list
                    recyclerViewAdapter.notifyItemRemoved(i);

                }

                // incrementing the counter to move to the next element
                i = i + 1;
            }
        }
    }

    // creating the thread responsible for adding constantly producers
    Thread producerAddingThread = new Thread() {
        @Override
        public void run() {
            // keeps running the thread
            while (!isInterrupted()){
                try {

                    // the thread waits the necessary time for the next insertion
                    Thread.sleep(addProducerInterval);

                    // updating the UI thread by adding a producer to the list
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addProducer();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    // creating the thread responsible for removing constantly customers
    Thread customerRemovalThread = new Thread() {
        @Override
        public void run() {

            // keeps running the thread
            while (!isInterrupted()){
                try {

                    // the thread waits the necessary time for the next deletion
                    Thread.sleep(removeCustomerInterval);

                    // updating the UI thread by adding a producer to the list
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            deleteCustomer();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };


}
