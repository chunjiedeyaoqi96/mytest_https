package com.itheima.service.impl;

import com.itheima.model.Book;
import com.itheima.service.BookService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 包名: com.itheima.service.impl
 * 作者: CF
 * 时间: 2019/2/22 9:59
 */
public class BookServiceImpl implements BookService {


    /**
     * 查询数据库数据
     * @return
     */
    @Override
    public List<Book> queryBookList() throws Exception{

        // 数据库链接
        Connection connection = null;
        // 预编译statement
        PreparedStatement preparedStatement = null;
        // 结果集
        ResultSet resultSet = null;
        // 图书列表
        List<Book> books = new ArrayList<Book>();

        try {
            // 加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 连接数据库
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/heimatest", "root", "123456");

            // SQL语句
            String sql = "SELECT * FROM book";
            // 创建preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            // 获取结果集
            resultSet = preparedStatement.executeQuery();
            // 结果集解析
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setPrice(resultSet.getFloat("price"));
                book.setPic(resultSet.getString("pic"));
                book.setDesc(resultSet.getString("desc"));
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }




}
