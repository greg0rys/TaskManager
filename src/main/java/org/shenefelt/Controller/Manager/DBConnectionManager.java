package org.shenefelt.Controller.Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Optional;

public class DBConnectionManager
{
    // use for static classes do not allow init.
    private DBConnectionManager() { }
    // private bc static
    public static Optional<Connection> getConnection() throws Exception
    {
        List<String> connLogons = CredentialManager.getConnectionLogons();

        return Optional.of(DriverManager.getConnection(connLogons.get(0),
                                                       connLogons.get(1),
                                                       connLogons.get(2)));
    }
}
