package com.example.business;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

class storefrontFacadeTest {

    @Test
    void setOrderOwner_alreadyClaimed() throws NotSignedInException, InvalidPasswordException, DuplicatePasswordException, ClaimedOrderException {
        // Create a Customer object
        Customer customer = new Customer();

        //id of an already claimed order
        int orderId = 10000;

        // Create an instance of the class containing the setOrderOwner method
        storefrontFacade facade = new storefrontFacade();

        // Call the setOrderOwner method with the Customer and orderId
        try {
            facade.setOrderOwner(customer, orderId);
        }catch(ClaimedOrderException e){
            // Assert that the order was claimed by the Customer
            //assertTrue(orderIsClaimed(orderId));
            String message = "This order already belongs to another customer. Please ensure you have the correct order ID, and try again.";
            assertEquals(message, e.getMessage());
            assertTrue(orderIsClaimed(orderId));
        }

//        // Attempt to claim the order again
//        facade.setOrderOwner(customer, orderId);
//
//        // Assert that the order is still claimed (it should not be claimed again)
//        assertTrue(orderIsClaimed(orderId));
    }

    @Test
    void setOrderOwner_orderNotClaimed() throws NotSignedInException, InvalidPasswordException, DuplicatePasswordException, ClaimedOrderException {
        // Create a Customer object
        Customer customer = new Customer();

        //id of a not claimed order
        int orderId = 10002;

        //check that the order is unclaimed
        assertFalse(orderIsClaimed(orderId));

        // Create an instance of the class containing the setOrderOwner method
        storefrontFacade facade = new storefrontFacade();

        // Call the setOrderOwner method with the Customer and orderId
        try {
            facade.setOrderOwner(customer, orderId);
        }catch(ClaimedOrderException e){
            // Assert that the order was claimed by the Customer
            //assertTrue(orderIsClaimed(orderId));
            String message = "This order already belongs to another customer. Please ensure you have the correct order ID, and try again.";
            assertEquals(message, e.getMessage());
        }

        //show that the unclaimed order has been claimed
        assertTrue(orderIsClaimed(orderId));
    }

    @Test
    void setPasscode_alreadyTaken() {
        User user = new User();
        String existingPasscode = "Hello12345";

        // Create an instance of the class containing the setPasscode method
        storefrontFacade facade = new storefrontFacade();

        // Set the passcode for the customer
        try {
            facade.setPasscode(user, existingPasscode);
        } catch (InvalidPasswordException e) {
            throw new RuntimeException(e);
        } catch (DuplicatePasswordException e) {
            throw new RuntimeException(e);
        } catch (NotSignedInException e) {
            throw new RuntimeException(e);
        }

        // Attempt to set the passcode again
        DuplicatePasswordException exception = assertThrows(DuplicatePasswordException.class,
                () -> facade.setPasscode(user, existingPasscode));

        assertEquals("Passcode is already in use", exception.getMessage());

    }

    @Test
    void setPasscode_notTaken() {
        User user = new Customer();  // or Staff, depending on the actual implementation
        String newPasscode = "NewPasscode123";

        // Create an instance of the class containing the setPasscode method
        storefrontFacade facade = new storefrontFacade();

        // Set the passcode for the customer
        assertDoesNotThrow(() -> facade.setPasscode(user, newPasscode));

        // Verify that the passcode is set successfully
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            assertEquals(newPasscode, getPasscodeForUser(customer.getUserId()));
        } else {
            Staff staff = (Staff) user;
            assertEquals(newPasscode, getPasscodeForUser(staff.getUserId()));
        }
    }

    private String getPasscodeForUser(int userId) {
        SQLConnector connector = new SQLConnector();

        try {
            String getPasscodeQuery = "SELECT passcode FROM Users WHERE user_id = ?";

            try (Connection connection = connector.myDbConn;
                 PreparedStatement getPasscodeStatement = connection.prepareStatement(getPasscodeQuery)) {

                getPasscodeStatement.setInt(1, userId);

                try (ResultSet resultSet = getPasscodeStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("passcode");
                    } else {
                        return null; // User not found
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            connector.closeConnection();
        }
    }

    private boolean orderIsClaimed(int orderId) {
        SQLConnector connector = new SQLConnector();

        try {
            String checkOrderQuery = "SELECT * FROM Orders WHERE order_id = ? AND user_id != 0";

            try (Connection connection = connector.myDbConn;
                 PreparedStatement checkOrderStatement = connection.prepareStatement(checkOrderQuery)) {

                checkOrderStatement.setInt(1, orderId);

                try (ResultSet resultSet = checkOrderStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            connector.closeConnection();
        }
    }

}