package com.uplus.matdori;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest(
	properties = {"spring.config.location=classpath:application.properties"}
)

@ComponentScan(
    basePackages = {"com.uplus.matdori"}
)

public class MatdoriApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testDataSourceNotNull() {
        assertNotNull(dataSource, "DataSource가 null입니다! MySQL 연결을 확인하세요.");
    }
}
