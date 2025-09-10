package com.example.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootApplication
public class JdbcApplication {

    public static void main(String[] args) throws SQLException {
        // 어플리케이션 실행 컨텍스트 생성
        SpringApplication.run(JdbcApplication.class, args);

        // 데이터베이스 연결정보
        String url = "jdbc:h2:mem:testdb"; 	// spring.datasource.url
        String username = "sa";				// spring.datasource.username

        // connection 얻어오기
        try (Connection connection = DriverManager.getConnection(url, username, null)) {
            try {
                // 테이블 생성 (statement 생성)
                String creatSql = "CREATE TABLE USERS (id SERIAL, username varchar(255))";
                try (PreparedStatement statement = connection.prepareStatement(creatSql)) {
                    statement.execute();
                }

                // 데이터 추가 (statement 생성)
                String insertSql = "INSERT INTO USERS (username) VALUES ('teasun kim')";
                try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
                    statement.execute();
                }

                // 데이터 조회 (statement 생성 후 rs = resultSet 수신 & next() 조회)
                String selectSql = "SELECT * FROM USERS";
                try (PreparedStatement statement = connection.prepareStatement(selectSql)) {
                    var rs = statement.executeQuery();
                    while (rs.next()) {
                        System.out.printf("%d, %s", rs.getInt("id"), rs.getString("username"));
                    }
                }
            } catch (SQLException e) {
                if (e.getMessage().equals("ERROR: relation \"account\" already exists")) {
                    System.out.println("USERS 테이블이 이미 존재합니다.");
                } else {
                    throw new RuntimeException();
                }
            }
        }
    }

}
