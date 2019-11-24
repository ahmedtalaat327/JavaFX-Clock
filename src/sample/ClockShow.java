package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.TimeZone;

public class ClockShow extends Thread{
    Pane paneParent = null;
    Pane paneBackground = new Pane();
    ///centre types:
    Pane centreCircleBelow = new Pane(); double ratioforBelow =8.0;
    Pane centreCircleAbove = new Pane(); double ratioforAbove =12.0;
    ///Ticks types:
    Pane HourTick = new Pane();
    Pane MinutTick = new Pane();
    Pane SectTick = new Pane();

    Timer rep = null;

    public ClockShow(Pane p){
        this.paneParent=p;
        guiStatic();
    }

    private void guiStatic(){
        String radiusCorners = paneParent.getPrefHeight()+" "+paneParent.getPrefHeight()+" "+paneParent.getPrefWidth()+" "+paneParent.getPrefWidth();
        paneBackground.setPrefHeight(paneParent.getPrefHeight());
        paneBackground.setPrefWidth(paneParent.getPrefWidth());
        paneBackground.setStyle("-fx-background-size: "+paneParent.getPrefWidth()+" "+paneParent.getPrefHeight()+";\n" +
                "    -fx-background-radius: "+radiusCorners+";\n" +
                "    -fx-border-radius: 0 0 18 18;\n" +
                "    -fx-background-color: #FAF4FF;"+
                "-fx-effect: dropshadow(three-pass-box, #2A607B, 10, 0, 10, 10);");
        paneParent.getChildren().add(paneBackground);

        paneParent.getChildren().add(drawFace());



        //HourTick --

        String radiusCornersHour = paneParent.getPrefHeight()/25+" "+paneParent.getPrefHeight()/25+" "+paneParent.getPrefWidth()/25+" "+paneParent.getPrefWidth()/25;
        HourTick.setPrefHeight(paneParent.getPrefHeight()/25);
        HourTick.setPrefWidth(paneParent.getPrefWidth()/2*(5.0/10.0));
        HourTick.setStyle("-fx-background-size: "+paneParent.getPrefWidth()/2*(5.0/10.0)+" "+paneParent.getPrefHeight()/25+";\n" +
                "    -fx-background-radius: "+radiusCornersHour+";\n" +
                "    -fx-border-radius: 0 0 18 18;\n" +
                "    -fx-background-color: #053456;"+
                "-fx-effect: dropshadow(three-pass-box, lightgrey, 10, 0, 5, 5);");
        HourTick.setLayoutX(paneParent.getPrefWidth()/2.0);
        HourTick.setLayoutY(paneParent.getPrefHeight()/2.0-(paneParent.getPrefHeight()/25/2));
        paneParent.getChildren().add(HourTick);
        Rotate rotate3 = new Rotate();
        //Setting the angle for the rotation
        rotate3.setAngle(-90);

        //Setting pivot points for the rotation
        rotate3.setPivotX(0);
        rotate3.setPivotY(HourTick.getPrefHeight()/2);

        HourTick.getTransforms().add(rotate3);

        //MinutTick --

        String radiusCornersMinut = paneParent.getPrefHeight()/20+" "+paneParent.getPrefHeight()/20+" "+paneParent.getPrefWidth()/20+" "+paneParent.getPrefWidth()/20;
        MinutTick.setPrefHeight(paneParent.getPrefHeight()/20);
        MinutTick.setPrefWidth(paneParent.getPrefWidth()/2*(6.0/10.0));
        MinutTick.setStyle("-fx-background-size: "+paneParent.getPrefWidth()/2*(6.0/10.0)+" "+paneParent.getPrefHeight()/20+";\n" +
                "    -fx-background-radius: "+radiusCornersMinut+";\n" +
                "    -fx-border-radius: 0 0 18 18;\n" +
                "    -fx-background-color: #053456;"+
                "");
        MinutTick.setLayoutX(paneParent.getPrefWidth()/2.0);
        MinutTick.setLayoutY(paneParent.getPrefHeight()/2.0-(paneParent.getPrefHeight()/20/2));
        paneParent.getChildren().add(MinutTick);
        Rotate rotate2 = new Rotate();
        //Setting the angle for the rotation
        rotate2.setAngle(-90);

        //Setting pivot points for the rotation
        rotate2.setPivotX(0);
        rotate2.setPivotY(MinutTick.getPrefHeight()/2);

        MinutTick.getTransforms().add(rotate2);

















///centre below circle - above all
        String radiusCorners_below = paneParent.getPrefHeight()/ratioforBelow+" "+paneParent.getPrefHeight()/ratioforBelow+" "+paneParent.getPrefWidth()/ratioforBelow+" "+paneParent.getPrefWidth()/ratioforBelow;
        centreCircleBelow.setPrefHeight(paneParent.getPrefHeight()/ratioforBelow);
        centreCircleBelow.setPrefWidth(paneParent.getPrefWidth()/ratioforBelow);
        centreCircleBelow.setStyle("-fx-background-size: "+paneParent.getPrefWidth()/ratioforBelow+" "+paneParent.getPrefHeight()/ratioforBelow+";\n" +
                "    -fx-background-radius: "+radiusCorners_below+";\n" +
                "    -fx-border-radius: 0 0 18 18;\n" +
                "    -fx-background-color: #053456;");
        centreCircleBelow.setLayoutX((paneBackground.getPrefWidth()-centreCircleBelow.getPrefWidth())/2);
        centreCircleBelow.setLayoutY((paneBackground.getPrefHeight()-centreCircleBelow.getPrefHeight())/2);
        paneParent.getChildren().add(centreCircleBelow);
        ///centre above circle - above esc
        String radiusCorners_above = paneParent.getPrefHeight()/ratioforAbove+" "+paneParent.getPrefHeight()/ratioforAbove+" "+paneParent.getPrefWidth()/ratioforAbove+" "+paneParent.getPrefWidth()/ratioforAbove;
        centreCircleAbove.setPrefHeight(paneParent.getPrefHeight()/ratioforAbove);
        centreCircleAbove.setPrefWidth(paneParent.getPrefWidth()/ratioforAbove);
        centreCircleAbove.setStyle("-fx-background-size: "+paneParent.getPrefWidth()/ratioforAbove+" "+paneParent.getPrefHeight()/ratioforAbove+";\n" +
                "    -fx-background-radius: "+radiusCorners_above+";\n" +
                "    -fx-border-radius: 0 0 18 18;\n" +
                "    -fx-background-color: #EC640B;");
        centreCircleAbove.setLayoutX((paneBackground.getPrefWidth()-centreCircleAbove.getPrefWidth())/2);
        centreCircleAbove.setLayoutY((paneBackground.getPrefHeight()-centreCircleAbove.getPrefHeight())/2);
        paneParent.getChildren().add(centreCircleAbove);


        //SecTick--

        String radiusCornersSec = paneParent.getPrefHeight()/40+" "+paneParent.getPrefHeight()/40+" "+paneParent.getPrefWidth()/40+" "+paneParent.getPrefWidth()/40;
        SectTick.setPrefHeight(paneParent.getPrefHeight()/40);
        SectTick.setPrefWidth(paneParent.getPrefWidth()/2*(7.0/10.0));
        SectTick.setStyle("-fx-background-size: "+paneParent.getPrefWidth()/2*(7.0/10.0)+" "+paneParent.getPrefHeight()/40+";\n" +
                "    -fx-background-radius: "+radiusCornersSec+";\n" +
                "    -fx-border-radius: 0 0 18 18;\n" +
                "    -fx-background-color: #EC640B;"+
                "");
        SectTick.setLayoutX(paneParent.getPrefWidth()/2.0);
        SectTick.setLayoutY(paneParent.getPrefHeight()/2.0-(paneParent.getPrefHeight()/40/2));
        paneParent.getChildren().add(SectTick);
        Rotate rotate1 = new Rotate();
        //Setting the angle for the rotation
        rotate1.setAngle(-90);

        //Setting pivot points for the rotation
        rotate1.setPivotX(0);
        rotate1.setPivotY(SectTick.getPrefHeight()/2);

        SectTick.getTransforms().add(rotate1);


    }

    public void run(){

        Rotate rotate1 = new Rotate();
        SectTick.getTransforms().add(rotate1);

        Rotate rotate2 = new Rotate();
        MinutTick.getTransforms().add(rotate2);

        Rotate rotate3 = new Rotate();
        HourTick.getTransforms().add(rotate3);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                double second = (double) (calendar.get(Calendar.SECOND));
                double secondAngle = second / 60.0 * 2.0 * Math.PI;

                //Setting the angle for the rotation
                rotate1.setAngle(secondAngle*(180/Math.PI));

                //Setting pivot points for the rotation
                rotate1.setPivotX(0);
                rotate1.setPivotY(SectTick.getPrefHeight() / 2);
                ////********************************

                double minutes = (double) (calendar.get(Calendar.MINUTE));
                double minutesAngle = minutes / 60.0 * 2.0 * Math.PI;

                //Setting the angle for the rotation
                rotate2.setAngle(minutesAngle*(180/Math.PI));

                //Setting pivot points for the rotation
                rotate2.setPivotX(0);
                rotate2.setPivotY(MinutTick.getPrefHeight() / 2);
                ////********************************

                double houres = (double) (calendar.get(Calendar.HOUR));
                double houresAngle = houres/12.0*2.0*Math.PI;

                //Setting the angle for the rotation
                rotate3.setAngle(houresAngle*(180/Math.PI));

                //Setting pivot points for the rotation
                rotate3.setPivotX(0);
                rotate3.setPivotY(HourTick.getPrefHeight() / 2);

            }};
        timer.start();
    }
    private Path drawFace(){
        //Tick Styles
        int tickLen = 10;  // short tick
        int medTickLen = 7;  // at 5-minute intervals
        int longTickLen = 50; // at the quarters
        int tickColor = 0xCCCCCC;
        double cX = paneParent.getPrefWidth()/2;
        double cY = paneParent.getPrefHeight()/2;
        double r = Math.min(paneParent.getPrefWidth(), paneParent.getPrefHeight())/2-10;
        Path ticksPath = new Path();
        ticksPath.setFill(Color.rgb(5, 52, 86));
        ticksPath.setStroke(Color.rgb(5, 52, 86));
        ticksPath.setStrokeWidth(2.5);
        ticksPath.setFillRule(FillRule.EVEN_ODD);
        // Draw a tick for each "second" (1 through 60)
        for ( int i=1; i<= 60; i++){
            // default tick length is short
            int len = tickLen;
            if ( i % 15 == 0 ){
                // Longest tick on quarters (every 15 ticks)
                //  len = longTickLen;
                len = medTickLen;
            } else if ( i % 5 == 0 ){
                // Medium ticks on the '5's (every 5 ticks)
                len = medTickLen;

            }
            else {
                len = 0;
            }

            double di = (double)i; // tick num as double for easier math

            // Get the angle from 12 O'Clock to this tick (radians)
            double angleFrom12 = di/60.0*2.0*Math.PI;

            // Get the angle from 3 O'Clock to this tick
            // Note: 3 O'Clock corresponds with zero angle in unit circle
            // Makes it easier to do the math.
            double angleFrom3 = Math.PI/2.0-angleFrom12;

            // Move to the outer edge of the circle at correct position
            // for this tick.
            if(len>0) {
                float mov1 = (float) (cX + Math.cos(angleFrom3) * r);
                float mov2 = (float) (cY - Math.sin(angleFrom3) * r);
                ticksPath.getElements().add(new MoveTo(mov1, mov2));


                // Draw line inward along radius for length of tick mark
                float lin1 = (float) (cX + Math.cos(angleFrom3) * (r - len));
                float lin2 = (float) (cY - Math.sin(angleFrom3) * (r - len));
                ticksPath.getElements().add(new LineTo(lin1, lin2));
            }

        }
        return ticksPath;
    }
}
