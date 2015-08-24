package com.unb.bikex;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

/**
 * Created by Charles on 7/25/2015.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity>{

    private MainActivity mainActivity;
    private TextView hello;

    public MainActivityTest(){
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception{
        super.setUp();
        mainActivity = getActivity();
        //hello = (TextView) mainActivity.findViewById(R.id.hello_world);
    }

    public void testLayoutExists(){
    //    assertNotNull(mainActivity.findViewById(R.id.hello_world));
    }

    public void testHelloWorld(){
        assertEquals("Hello world!", hello.getText().toString());
    }

    @Override
    public void tearDown() throws Exception{
        super.tearDown();
    }

}
