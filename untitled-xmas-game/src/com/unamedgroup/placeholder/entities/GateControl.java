package com.unamedgroup.placeholder.entities;

import com.unamedgroup.placeholder.main.Handler;

public class GateControl {
    private static Gate gate;
    private static GateSwitch[] gateSwitch = new GateSwitch[3];

    public GateControl(Handler handler) {
        gate = new Gate(0, 0, 0, 0, 0, handler);
        gateSwitch = new GateSwitch[3];
        for (int i = 0; i < gateSwitch.length; i++) {
            gateSwitch[i] = new GateSwitch(i,0, 0, handler);
        }
    }



    public static Gate getGate(Double x, Double y, int destiny, int tpx, int tpy, Handler handler){
        gate.setX(x);
        gate.setY(y);
        gate.destiny = destiny;
        gate.tpx = tpx;
        gate.tpy = tpy;
        gate.handler = handler;
        gate.getLamp()[0].x = x + 3;
        gate.getLamp()[0].y = y - 5;
        gate.getLamp()[1].x = x + 13;
        gate.getLamp()[1].y = y - 7;
        gate.getLamp()[2].x = x + 23;
        gate.getLamp()[2].y = y - 5;
        return gate;
    }

    public static GateSwitch getSwitch(int index, double x, double y, Handler handler){
        gateSwitch[index].setX(x);
        gateSwitch[index].setY(y);
        gateSwitch[index].handler = handler;
        return gateSwitch[index];
    }
 
}
