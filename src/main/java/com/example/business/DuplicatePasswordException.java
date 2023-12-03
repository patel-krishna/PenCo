package com.example.business;

public class DuplicatePasswordException  extends Exception
{
    public DuplicatePasswordException ()
    {
        super("Passcode is already in use, please pick another one.");
    }
}