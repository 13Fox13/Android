package com.klopov.andrey.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements   View. OnClickListener {

    private int  seconds  =   0 ;
    private boolean  running ;
    private Button  res ;
    private Button  str ;
    private Button stp ;
    private boolean wasRunning ;

    @Override
    protected   void  onCreate ( Bundle  savedInstanceState )   {
        super . onCreate ( savedInstanceState );
        setContentView ( R . layout . activity_main );
        if   ( savedInstanceState  !=   null )   {
            seconds  =  savedInstanceState . getInt ( "seconds" );
            running  =  savedInstanceState . getBoolean ( "running" );
        }
        res  =   ( Button )  findViewById ( R . id . button );
        str  =   ( Button )  findViewById ( R . id . button3);
        stp  =   ( Button )  findViewById ( R . id . button2);
        res . setOnClickListener ( this );
        str . setOnClickListener ( this );
        stp . setOnClickListener ( this );
        runTimer ();
    }

    @Override

    public   void  onClick ( View  view )   {
        if   ( view . equals ( res )){
        running  =   false ;
        seconds  =   0 ;
        return ;
    }
        if   ( view . equals ( str ))   {
            running  =   true ;
        } if   ( view . equals ( stp ))   {
            running  =   false ;
        }
    }

    private   void  runTimer ()   {
        final   TextView  timeView  =   (TextView)  findViewById ( R . id . textView);
        final Handler handler  =   new   Handler ();
        handler . post ( new   Runnable ()   {
            @Override
            public   void  run ()   {
                int  hours  =  seconds  /   3600 ;
                int  minutes  =   ( seconds  %   3600 )   /   60 ;
                int  secs  =  seconds  %   60 ;
                String  time  =   String . format( "%d:%02d:%02d" , hours ,  minutes ,  secs );
                timeView . setText ( time );
                if   ( running )   {
                    seconds ++;
                }
                handler . postDelayed ( this ,   1000 );
            }
        });
    }
    @Override
    public   void  onSaveInstanceState ( Bundle  savedInstanceState )   {
        savedInstanceState . putInt ( "seconds" ,  seconds );
        savedInstanceState . putBoolean ( "running" ,  running );
    }
    @Override
    protected   void  onStop ()   {
    super . onStop ();
    wasRunning  =  running ;
    running  =   false ;
    }
}