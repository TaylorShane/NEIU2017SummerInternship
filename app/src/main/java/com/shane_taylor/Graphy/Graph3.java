package com.shane_taylor.Graphy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

/**
 * Reflexive triangles
 */

public class Graph3 extends Activity {

    private int Ux1A, Uy1A, Ux2A, Uy2A, Ux1B, Uy1B, Ux2B, Uy2B, Ux1C, Uy1C, Ux2C, Uy2C;
    private int randX1A, randY1A, randX2A, randY2A, randX1B, randY1B, randX2B, randY2B, randX1C, randY1C, randX2C, randY2C;
    // --Commented out by Inspection (7/24/2017 3:41 PM):private TextView results;
    // --Commented out by Inspection (7/24/2017 3:41 PM):private TextView userResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_graph3);
        createRandomTriangle();
        setGraphScale();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void createRandomTriangle() { /* random triangle generator - in quadrant I*/

        Random random = new Random();
        randX1A = random.nextInt(11);
        randY1A = random.nextInt(11);
        randX2A = random.nextInt(11);
        randY2A = random.nextInt(11);

        randX1B = randX2A;
        randY1B = randY2A;
        randX2B = random.nextInt(11);
        randY2B = random.nextInt(11);

        // logic to avoid triangles with three sides all on one line.
        while (randX2B == randX1A || randY2B == randY2A) {
            randX2B = random.nextInt(11);
            randY2B = random.nextInt(11);
        }

        randX1C = randX1A;
        randY1C = randY1A;
        randX2C = randX2B;
        randY2C = randY2B;

        /*
          This graph library expects lines to be drawn from left to right.  The first X coordinate MUST be less than the second.
          The below logic swaps the two values if this is not the case, along with the appropriate Y values.
          Vertical lines are not affected no matter the relation of the start and end X values.
         */

        GraphView graph = (GraphView) findViewById(R.id.linegraph);
        if (randX1A > randX2A) {
            int temp = randX1A;
            randX1A = randX2A;
            randX2A = temp;

            temp = randY1A;
            randY1A = randY2A;
            randY2A = temp;
        }
        LineGraphSeries<DataPoint> LineA = new LineGraphSeries<>(new DataPoint[]{

                new DataPoint(randX1A, randY1A),
                new DataPoint(randX2A, randY2A)
        });
        graph.addSeries(LineA);
        LineA.setColor(Color.BLUE);
        LineA.setDrawDataPoints(true);
        LineA.setDataPointsRadius(0);
        LineA.setThickness(3);

        if (randX1B > randX2B) {
            int temp = randX1B;
            randX1B = randX2B;
            randX2B = temp;

            temp = randY1B;
            randY1B = randY2B;
            randY2B = temp;
        }
        LineGraphSeries<DataPoint> LineB = new LineGraphSeries<>(new DataPoint[]{

                new DataPoint(randX1B, randY1B),
                new DataPoint(randX2B, randY2B)
        });
        graph.addSeries(LineB);
        LineB.setColor(Color.RED);
        LineB.setDrawDataPoints(true);
        LineB.setDataPointsRadius(0);
        LineB.setThickness(3);

        if (randX1C > randX2C) {
            int temp = randX1C;
            randX1C = randX2C;
            randX2C = temp;

            temp = randY1C;
            randY1C = randY2C;
            randY2C = temp;
        }
        LineGraphSeries<DataPoint> LineC = new LineGraphSeries<>(new DataPoint[]{

                new DataPoint(randX1C, randY1C),
                new DataPoint(randX2C, randY2C)
        });
        graph.addSeries(LineC);
        LineC.setColor(Color.rgb(3, 126, 3));
        LineC.setDrawDataPoints(true);
        LineC.setDataPointsRadius(0);
        LineC.setThickness(3);

        /* for debugging
         results = (TextView) findViewById(R.id.random_values_results);
         results.setText("PLEASE IGNORE THIS TEST DATA" + "\n" +
                 "randX1A: " + randX1A + " randY1A: " + randY1A + " randX2A: " + randX2A + " randY2A: " + randY2A + "\n" +
                 "randX1B: " + randX1B + " randY1B: " + randY1B + " randX2B: " + randX2B + " randY2B: " + randY2B + "\n" +
                 "randX1C: " + randX1C + " randY1C; " + randY1C + " randX2C: " + randX2C + " randY2C: " + randY2C);
         */
    }

    private void setGraphScale() {
        GraphView graph = (GraphView) findViewById(R.id.linegraph);

        LineGraphSeries<DataPoint> Xaxis = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(-10, 0),
                new DataPoint(10, 0)
        });

        LineGraphSeries<DataPoint> Yaxis = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, -10),
                new DataPoint(0, 10)
        });
        graph.addSeries(Xaxis);
        Xaxis.setTitle("X Axis");
        Xaxis.setColor(Color.BLACK);
        Xaxis.setDrawDataPoints(false);
        Xaxis.setDataPointsRadius(0);
        Xaxis.setThickness(0);

        graph.addSeries(Yaxis);
        Yaxis.setTitle("Y Axis");
        Yaxis.setColor(Color.BLACK);
        Yaxis.setDrawDataPoints(false);
        Yaxis.setDataPointsRadius(0);
        Yaxis.setThickness(0);

        try {
            int x = 10;
            for (int y = 10; y > -10; y--) {
                LineGraphSeries<DataPoint> fineLinesX = new LineGraphSeries<>(new DataPoint[]{
                        new DataPoint(x * -1, y),
                        new DataPoint(x, y)
                });

                LineGraphSeries<DataPoint> fineLinesY = new LineGraphSeries<>(new DataPoint[]{
                        new DataPoint(y, -1 * x),
                        new DataPoint(y, x)
                });

                graph.addSeries(fineLinesX);
                fineLinesX.setColor(Color.GRAY);
                fineLinesX.setDrawDataPoints(true);
                fineLinesX.setDataPointsRadius(0);
                fineLinesX.setThickness(1);

                graph.addSeries(fineLinesY);
                fineLinesY.setColor(Color.GRAY);
                fineLinesY.setDrawDataPoints(true);
                fineLinesY.setDataPointsRadius(0);
                fineLinesY.setThickness(1);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Creating fineLines didn't work", Toast.LENGTH_SHORT).show();
        }
    }

    private void evaluate() { // y=y, x= -1*x
        TextView triangleInstructions = (TextView) findViewById(R.id.triangleInstructions);

        if (((((Ux1A == randX1A * -1) || (Ux1A == randX2A * -1)) && ((Uy1A == randY1A) || (Uy1A == randY2A))) &&
                (((Ux2A == randX2A * -1) || (Ux2A == randX1A * -1)) && ((Uy2A == randY2A) || (Uy2A == randY1A))) &&
                (((Ux1B == randX2B * -1) || (Ux1B == randX2B * -1)) && ((Uy1B == randY1B) || (Uy1B == randY2B))) &&
                (((Ux2B == randX2B * -1) || (Ux2B == randX1B * -1)) && ((Uy2B == randY2B) || (Uy2B == randY1B))) &&
                (((Ux1C == randX1C * -1) || (Ux1C == randX2C * -1)) && ((Uy1C == randY1C) || (Uy1C == randY2C))) &&
                (((Ux2C == randX2C * -1) || (Ux2C == randX1C * -1)) && ((Uy2C == randY2C) || (Uy2C == randY1C)))) &&
                triangleInstructions.getText() == getResources().getString(R.string.triangleInstruction1)) {
            Toast toast = new Toast(this);
            ImageView view = new ImageView(this);
            view.setImageResource(R.drawable.correct_large);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
            triangleInstructions.setText(getResources().getString(R.string.triangleInstruction2));
            //Userx1A.setText("");, Usery1A, Userx2A, Usery2A, Userx1B, Usery1B, Userx2B, Usery2B, Userx1C, Usery1C, Userx2C, Usery2C;

            clearForm((ViewGroup) findViewById(R.id.enterCoordinatesLayout));
        } else if (((((Ux1A == randX1A * -1) || (Ux1A == randX2A * -1)) && ((Uy1A == randY1A * -1) || (Uy1A == randY2A * -1))) &&
                (((Ux2A == randX2A * -1) || (Ux2A == randX1A * -1)) && ((Uy2A == randY2A * -1) || (Uy2A == randY1A * -1))) &&
                (((Ux1B == randX2B * -1) || (Ux1B == randX2B * -1)) && ((Uy1B == randY1B * -1) || (Uy1B == randY2B * -1))) &&
                (((Ux2B == randX2B * -1) || (Ux2B == randX1B * -1)) && ((Uy2B == randY2B * -1) || (Uy2B == randY1B * -1))) &&
                (((Ux1C == randX1C * -1) || (Ux1C == randX2C * -1)) && ((Uy1C == randY1C * -1) || (Uy1C == randY2C * -1))) &&
                (((Ux2C == randX2C * -1) || (Ux2C == randX1C * -1)) && ((Uy2C == randY2C * -1) || (Uy2C == randY1C * -1)))) &&
                triangleInstructions.getText() == getResources().getString(R.string.triangleInstruction2)) {
            Toast toast = new Toast(this);
            ImageView view = new ImageView(this);
            view.setImageResource(R.drawable.correct_large);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
            triangleInstructions.setText(getResources().getString(R.string.triangleInstruction3));

            clearForm((ViewGroup) findViewById(R.id.enterCoordinatesLayout));

        } else if (((((Ux1A == randX1A) || (Ux1A == randX2A)) && ((Uy1A == randY1A * -1) || (Uy1A == randY2A * -1))) &&
                (((Ux2A == randX1A) || (Ux2A == randX2A)) && ((Uy2A == randY2A * -1) || (Uy2A == randY1A * -1))) &&
                (((Ux1B == randX1B) || (Ux1B == randX2B)) && ((Uy1B == randY1B * -1) || (Uy1B == randY2B * -1))) &&
                (((Ux2B == randX1B) || (Ux2B == randX2B)) && ((Uy2B == randY2B * -1) || (Uy2B == randY1B * -1))) &&
                (((Ux1C == randX1C) || (Ux1C == randX2C)) && ((Uy1C == randY1C * -1) || (Uy1C == randY2C * -1))) &&
                (((Ux2C == randX1C) || (Ux2C == randX2C)) && ((Uy2C == randY2C * -1) || (Uy2C == randY1C * -1)))) &&
                triangleInstructions.getText() == getResources().getString(R.string.triangleInstruction3)) {
            Toast toast = new Toast(this);
            ImageView view = new ImageView(this);
            view.setImageResource(R.drawable.correct_large);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
            triangleInstructions.setText(getResources().getString(R.string.newTriangle));

            clearForm((ViewGroup) findViewById(R.id.enterCoordinatesLayout));
        } else {
            // Try again toast image
            Toast toast = new Toast(this);
            ImageView view = new ImageView(this);
            view.setImageResource(R.drawable.try_again_large);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();

        }

        /* for debugging
        userResults = (TextView) findViewById(R.id.user_values_results);
        userResults.setText("X1A: " + Ux1A + " Y1A: " + Uy1A + " X2A: " + Ux2A + " Y2A: " + Uy2A + "\n"
                + "Ux1B: " + Ux1B + " Uy1B: " + Uy1B + " X2B: " + Ux2B + " Y2B: " + Uy2B + "\n"
                + "X1C: " + Ux1C + " Y1C; " + Uy1C + " X2C: " + Ux2C + " Y2C: " + Uy2C);
        */
    }

    private void createUserTriangle() {  // Using user coordinates to create user line
        GraphView graph = (GraphView) findViewById(R.id.linegraph);

        /*
          This graph library expects lines to be drawn from left to right.  The first X coordinate MUST be less than the second.
          The below logic swaps the two values if this is not the case, along with the appropriate Y values.
          Vertical lines are not affected no matter the relation of the start and end X values.
         */
        if (Ux1A > Ux2A) {
            int temp = Ux1A;
            Ux1A = Ux2A;
            Ux2A = temp;

            temp = Uy1A;
            Uy1A = Uy2A;
            Uy2A = temp;
        }
        LineGraphSeries<DataPoint> userLineA = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(Ux1A, Uy1A),
                new DataPoint(Ux2A, Uy2A)
        });

        graph.addSeries(userLineA);
        userLineA.setColor(Color.parseColor("#ff8a05"));
        userLineA.setThickness(3);

        if (Ux1B > Ux2B) {
            int temp = Ux1B;
            Ux1B = Ux2B;
            Ux2B = temp;

            temp = Uy1B;
            Uy1B = Uy2B;
            Uy2B = temp;
        }
        LineGraphSeries<DataPoint> userLineB = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(Ux1B, Uy1B),
                new DataPoint(Ux2B, Uy2B)
        });

        graph.addSeries(userLineB);
        userLineB.setColor(Color.parseColor("#ff8a05"));
        userLineB.setThickness(3);

        if (Ux1C > Ux2C) {
            int temp = Ux1C;
            Ux1C = Ux2C;
            Ux2C = temp;

            temp = Uy1C;
            Uy1C = Uy2C;
            Uy2C = temp;
        }
        LineGraphSeries<DataPoint> userLineC = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(Ux1C, Uy1C),
                new DataPoint(Ux2C, Uy2C)
        });

        graph.addSeries(userLineC);
        userLineC.setColor(Color.parseColor("#ff8a05"));
        userLineC.setThickness(3);
    }

    private void getUserCoordinates() {  // Getting user coordinates
        EditText userx1A = (EditText) findViewById(R.id.x1A);
        EditText usery1A = (EditText) findViewById(R.id.y1A);
        EditText userx2A = (EditText) findViewById(R.id.x2A);
        EditText usery2A = (EditText) findViewById(R.id.y2A);

        EditText userx1B = (EditText) findViewById(R.id.x1B);
        EditText usery1B = (EditText) findViewById(R.id.y1B);
        EditText userx2B = (EditText) findViewById(R.id.x2B);
        EditText usery2B = (EditText) findViewById(R.id.y2B);

        EditText userx1C = (EditText) findViewById(R.id.x1C);
        EditText usery1C = (EditText) findViewById(R.id.y1C);
        EditText userx2C = (EditText) findViewById(R.id.x2C);
        EditText usery2C = (EditText) findViewById(R.id.y2C);

        try {
            Ux1A = Integer.parseInt(userx1A.getText().toString());
            Uy1A = Integer.parseInt(usery1A.getText().toString());
            Ux2A = Integer.parseInt(userx2A.getText().toString());
            Uy2A = Integer.parseInt(usery2A.getText().toString());

            Ux1B = Integer.parseInt(userx1B.getText().toString());
            Uy1B = Integer.parseInt(usery1B.getText().toString());
            Ux2B = Integer.parseInt(userx2B.getText().toString());
            Uy2B = Integer.parseInt(usery2B.getText().toString());

            Ux1C = Integer.parseInt(userx1C.getText().toString());
            Uy1C = Integer.parseInt(usery1C.getText().toString());
            Ux2C = Integer.parseInt(userx2C.getText().toString());
            Uy2C = Integer.parseInt(usery2C.getText().toString());
            if(Ux1A > 10 || Uy1A > 10 || Ux2A > 10 || Uy2A > 10 ||
                    Ux1B > 10 || Uy1B > 10 || Ux2B > 10 || Uy2B > 10 ||
                    Ux1C > 10 || Uy1C > 10 || Ux2C > 10 || Uy2C > 10){
                Toast.makeText(this,  R.string.outOfBounds, Toast.LENGTH_SHORT).show();
            }
            else{
                createUserTriangle();
                evaluate();
            }
        } catch (Exception e) {
            Toast.makeText(this, R.string.blank, Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickPlot(View v) { /* button click method **/

        getUserCoordinates();

    }

    public void onClickReset(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void onCLickSSS(View v) {
        Intent intent = new Intent(this, Graph4.class);
        startActivity(intent);
    }

    private void clearForm(ViewGroup group) {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText) view).setText("");
            }

            if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0))
                clearForm((ViewGroup) view);
        }
    }

    public void onClickMenu(View view) {
        Intent intent = new Intent(this, MenuPageActivity.class);
        startActivity(intent);
    }
}
