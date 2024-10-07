package org.shenefelt.Controller.Database;

import java.sql.Connection;
import java.util.Optional;

public interface Database
{
    public Optional<Connection> getConnection();
    public int getNumRecords();


}
