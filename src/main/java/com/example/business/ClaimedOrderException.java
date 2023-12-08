package com.example.business;

public class ClaimedOrderException extends Exception {

        public ClaimedOrderException ()
        {
            super("This order already belongs to another customer. Please ensure you have the correct order ID, and try again.");
        }

}
