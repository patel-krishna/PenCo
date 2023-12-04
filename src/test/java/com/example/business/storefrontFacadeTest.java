package com.example.business;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

class storefrontFacadeTest {

    @Test
    void setOrderOwner_alreadyClaimed() {
        // Create a Customer object
        Customer customer = new Customer();
        int orderId = 1000;

        // Create an instance of the class containing the setOrderOwner method
        storefrontFacade facade = new storefrontFacade();

        // Call the setOrderOwner method with the Customer and orderId
        facade.setOrderOwner(customer, orderId);

        // Assert that the order was claimed by the Customer
        assertTrue(orderIsClaimed(orderId, customer.getUserId()));

        // Attempt to claim the order again
        facade.setOrderOwner(customer, orderId);

        // Assert that the order is still claimed (it should not be claimed again)
        assertTrue(orderIsClaimed(orderId, customer.getUserId()));
    }

    @Test
    void setPasscode_alreadyTaken() {
    }

    @Test
    void setPasscode_notTaken() {
    }

    private boolean orderIsClaimed(int orderId, int userId) {
        SQLConnector connector = new SQLConnector();

        try {
            String checkOrderQuery = "SELECT * FROM Orders WHERE order_id = ? AND user_id = ?";

            try (Connection connection = connector.myDbConn;
                 PreparedStatement checkOrderStatement = connection.prepareStatement(checkOrderQuery)) {

                checkOrderStatement.setInt(1, orderId);
                checkOrderStatement.setInt(2, userId);

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