package com.example.project9;


import java.io.Serializable;

public class Answer implements Serializable{
    private boolean result;

    Answer(boolean bool)
    {
        result = bool;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
