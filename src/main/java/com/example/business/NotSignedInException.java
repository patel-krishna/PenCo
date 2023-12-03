package com.example.business;

public class NotSignedInException extends Exception{
    public NotSignedInException (String str)
    {
        super(str);
    }
}
