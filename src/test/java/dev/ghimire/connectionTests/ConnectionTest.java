package dev.ghimire.connectionTests;

import dev.ghimire.util.ConnectionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConnectionTest {
    @Test
    void get_connection_test_1()
    {
        Connection conn =ConnectionUtil.getConnection();
        System.out.println(conn);
        Assertions.assertNotNull(conn);
    }
}
