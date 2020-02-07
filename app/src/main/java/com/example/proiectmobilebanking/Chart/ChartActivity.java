package com.example.proiectmobilebanking.Chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;

import com.example.proiectmobilebanking.SharedPreferencesUser;
import com.example.proiectmobilebanking.database.DatabaseManager;
import com.example.proiectmobilebanking.database.models.Tranzaction;
import com.example.proiectmobilebanking.database.service.TranzactionService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.proiectmobilebanking.R;

import java.util.List;

public class ChartActivity extends AppCompatActivity {
private SharedPreferencesUser preferences;
long idUser;
Integer amountSend=0;
Integer amountAsk=0;
LinearLayout layout;
Integer values[]=new Integer[2];
float valuesFloat[]=new float[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences=new SharedPreferencesUser(getApplicationContext());
        idUser=preferences.getUser();
        new TranzactionService.GetAllTransactions(getApplicationContext()) {
            @Override
            protected void onPostExecute(
                    List<Tranzaction> results) {
                if (results != null) {
                    for(Tranzaction tranzaction:results){
                        if(tranzaction.getIdUser()==idUser&&tranzaction.getStatus().equals("Send")){
                            amountSend=amountSend+tranzaction.getAmount();

                        }
                        if(tranzaction.getIdUser()==idUser&&tranzaction.getStatus().equals("Ask")){
                            amountAsk=amountAsk+tranzaction.getAmount();

                        }

                    }
                    if(amountAsk!=null||amountSend!=null)
                    { if(amountSend!=null)
                    {values[0]=amountSend;
                        valuesFloat[0]=(float)amountSend;}
                        if(amountAsk!=null)
                        {values[1]=amountAsk;
                            valuesFloat[1]=(float)amountAsk;}
                        valuesFloat=calculate(valuesFloat);}
                    layout=findViewById(R.id.chartLayout);
                    if(valuesFloat[0]!=0 || valuesFloat[1]!=0)
                    {layout.addView(new Grafic(getApplicationContext(),valuesFloat));}
                }
            }
        }.execute();
//        if(amountAsk!=null||amountSend!=null)
//        { if(amountSend!=null)
//        {values[0]=amountSend;
//        valuesFloat[0]=(float)amountSend;}
//        if(amountAsk!=null)
//        {values[1]=amountAsk;
//        valuesFloat[1]=(float)amountAsk;}
//        valuesFloat=calculate(valuesFloat);}
//         layout=findViewById(R.id.chartLayout);
//         if(valuesFloat[0]!=0 || valuesFloat[1]!=0)
//         {layout.addView(new Grafic(this,valuesFloat));}

    }
    private float[] calculate(float[] vector){
        float sum=0;
        for(int i=0;i<vector.length;i++){
            sum=sum+vector[i];
        }
        for(int i=0;i<vector.length;i++){
            vector[i]=360*(vector[i]/sum);
        }
        return vector;
    }

    public class Grafic extends View{
        private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        private float[] degree;
        private int[] colors={Color.MAGENTA,Color.YELLOW};
        private Paint paint2=new Paint(Paint.ANTI_ALIAS_FLAG);
      RectF rectf=new RectF();
      RectF rectfRect=new RectF();
      RectF recffRect2=new RectF();
        int temp=0;
        public Grafic(Context context,float[] values){
            super(context);
            degree=new float[values.length];
            for(int i=0;i<values.length;i++){
                degree[i]=valuesFloat[i];
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int height=canvas.getHeight()/2;
            int width=canvas.getWidth()/2;
            int heightRect=300+(int)0.3*canvas.getHeight();
            int widthRect=300+(int)0.3*canvas.getWidth();
            int heightRect2=350+(int)0.3*canvas.getHeight();
            int widthRect2=350+(int)0.3*canvas.getWidth();
            rectfRect.set(widthRect-10,heightRect-10,widthRect+10,heightRect+10);
            recffRect2.set(widthRect-10,heightRect-10,widthRect+10,heightRect+10);
            rectf.set(width-100,height-100,width+100,height+100);
            rectf.inset(-200,-200);
            rectfRect.inset(-8,-8);
            recffRect2.offsetTo(widthRect-10,heightRect+40);
            recffRect2.inset(-8,-8);

            for(int i=0;i<degree.length;i++){
                if(i==0){
                    paint.setColor(colors[i]);
                    canvas.drawRect(rectfRect,paint);
                    canvas.drawArc(rectf,0,degree[i],true,paint);
                    paint.setColor(Color.BLACK);
                    paint.setTextSize(40);
                    canvas.drawText("Money sent",widthRect+40,widthRect+10,paint);
                    paint2.setColor(colors[i+1]);
                    canvas.drawRect(recffRect2,paint2);
                    paint.setColor(Color.BLACK);
                    paint.setTextSize(40);
                    canvas.drawText("Money asked",widthRect+40,widthRect+60,paint);
                }
                else{
                    temp+=(int)degree[i-1];
                    paint.setColor(colors[i]);
                    canvas.drawArc(rectf,temp,degree[i],true,paint);


                }
            }
        }
    }

}
