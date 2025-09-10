package com.example.querymapper.dao;

import com.example.querymapper.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    private final SqlSession sqlSession;

    public UserDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public User selectUserById(long id) {
        return this.sqlSession.selectOne("selectUserById", id);
    }

}
