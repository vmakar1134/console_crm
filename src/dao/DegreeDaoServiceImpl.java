package dao;

import connection.Database;
import models.Degree;
import service.DaoService;
import utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DegreeDaoServiceImpl implements DaoService<Degree> {

    private Connection connection;

    public DegreeDaoServiceImpl() {
        this.connection = Database.getConnection();
    }

    @Override
    public Long insert(Degree degree) {
        Long id = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into Degree(name) value (?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, degree.getName());
            preparedStatement.execute();
            id = DatabaseUtil.getGeneratedKey(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<Degree> findAll() {
        List<Degree> degrees = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Degree d");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Degree degree = new Degree();
                degree.setId(resultSet.getLong("id"));
                degree.setName(resultSet.getString("name"));
                degrees.add(degree);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return degrees;
    }
}
