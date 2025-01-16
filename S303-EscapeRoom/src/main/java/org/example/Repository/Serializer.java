package org.example.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Serializer {

    public ArrayList<Object> serialize(ResultSet resultSet) throws SQLException {
        ArrayList<Object> values = new ArrayList<>();
        int columnCount = resultSet.getMetaData().getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            values.add(resultSet.getObject(i));
        }
        return values;
    }

    public void deserialize(PreparedStatement statement, ArrayList<Object> values) throws SQLException {
        for (int i = 0; i < values.size(); i++) {
            statement.setObject(i + 1, values.get(i));
        }
    }
}