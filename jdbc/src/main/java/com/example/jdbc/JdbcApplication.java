package com.example.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class JdbcApplication {

    public static void main(String[] args) throws SQLException {
        // 어플리케이션 실행 컨텍스트 생성
        var context = SpringApplication.run(JdbcApplication.class, args);
        // 데이터 조회 클래스 빈 조회
        var repository = context.getBean(DataRepository.class);

        // 테이블 생성
        repository.createTable();
        // 유저정보 추가
        repository.insertUser("Teasun Kim");
        // 유저정보 조회
        System.out.println("User: " + repository.findUserById(1L));
    }

}
