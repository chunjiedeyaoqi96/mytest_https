package com.itheima.service;

import com.itheima.model.Book;

import java.util.List;

/**
 * 包名: com.itheima.service
 * 作者: CF
 * 时间: 2019/2/22 9:59
 */
public interface BookService {
    List<Book> queryBookList() throws Exception;
}
