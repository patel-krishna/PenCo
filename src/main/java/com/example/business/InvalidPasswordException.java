package com.example.business;

public class InvalidPasswordException  extends Exception
{
    public InvalidPasswordException ()
    {
        super("Passcode must be at least 4 characters long and alphanumeric.");
    }
}