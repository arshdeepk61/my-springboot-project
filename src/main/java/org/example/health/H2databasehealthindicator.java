package org.example.health;

import org.aspectj.bridge.Message;
import org.example.repository.TaskRepository;
import org.example.repository.UserRepository;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Component
public class H2databasehealthindicator implements HealthIndicator {

//    @Override
//    public Health getHealth(boolean includeDetails) {
//        return HealthIndicator.super.getHealth(includeDetails);
//    }

    private final TaskRepository taskRepository;

    private final DataSource dataSource;

    private final UserRepository userRepository;

    public H2databasehealthindicator( DataSource dataSource1, TaskRepository taskRepository,UserRepository userRepository)
    {
        this.dataSource = dataSource1;
        this.taskRepository = taskRepository;
        this.userRepository=userRepository;
    }

    @Override
    public Health health() {
        try {
            try (Connection connection = dataSource.getConnection()) {
                DatabaseMetaData Db = connection.getMetaData();

                String productName = Db.getDatabaseProductName();
                String version = Db.getDatabaseProductVersion();
                Map<String, Boolean> tablestatus = tableStatus(Db);
                Health.Builder healthBuider = Health
                        .up()
                        .withDetail("Database", productName)
                        .withDetail("version", version)
                        .withDetail("URL", Db.getURL())
                        .withDetail("Driver", Db.getDriverName())
                        .withDetail("tables", tablestatus);
                boolean alltables = tablestatus.values().stream().allMatch(exsit -> exsit);
                if (!alltables) {
                    return healthBuider.status("DEGARDED")
                            .withDetail("message", "some Required tables are missing")
                            .build();
                }

                return healthBuider
                        .withDetail("message", "H2 Db is healthy and opertional")
                        .build();
            }

        } catch (Exception e) {
            return Health.down().withDetail("Error", e.getMessage())
                    .withDetail("message", "failed to connect to DB")
                    .withException(e).build();
        }


    }

    private Map<String ,Boolean>  tableStatus(DatabaseMetaData metadata ) throws SQLException {


        Map<String ,Boolean> tablestatus = new HashMap<>();
        String[] requiredTables= {"USERS","TASKS","CHECK"};

        try(ResultSet tables = metadata.getTables(null,null,null,new String[]{"TABLE"}))
        {
            Map<String ,Boolean> foundtables = new HashMap<>();

            while(tables.next())
            {
               String TableName= tables.getString("TABLE_NAME").toUpperCase() ;
                foundtables.put(TableName,true);
            }
            for(String tabble:requiredTables)
            {
                tablestatus.put(tabble,foundtables.containsKey(tabble));
            }
        }

        return tablestatus;
    }
}
