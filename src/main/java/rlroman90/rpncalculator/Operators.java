package rlroman90.rpncalculator;

import java.util.ArrayList;
import java.util.HashMap;

public class Operators {
    public ArrayList<String> ValidOperators;
    public HashMap<String, String> TwoElementOperationsAndInverses;

    public Operators() {
        TwoElementOperationsAndInverses = new HashMap<String, String>();
        TwoElementOperationsAndInverses.put("+", "-");
        TwoElementOperationsAndInverses.put("-", "+");
        TwoElementOperationsAndInverses.put("*", "/");
        TwoElementOperationsAndInverses.put("/", "*");

        ValidOperators = new ArrayList<String>();
        ValidOperators.addAll(TwoElementOperationsAndInverses.keySet());
        ValidOperators.add("sqrt");
        ValidOperators.add("clear");
        ValidOperators.add("undo");
    }
}
