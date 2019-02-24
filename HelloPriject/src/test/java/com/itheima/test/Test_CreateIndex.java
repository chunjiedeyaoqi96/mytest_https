package com.itheima.test;

import com.itheima.model.Book;
import com.itheima.service.BookService;
import com.itheima.service.impl.BookServiceImpl;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 包名: com.itheima.test
 * 作者: CF
 * 时间: 2019/2/22 8:51
 */
public class Test_CreateIndex {


    @Test
    public void test_CreateIndex() throws Exception{

        //  采集数据
        /*
        Book book = new Book();
        book.setId(1001);
        book.setName("java进阶");
        book.setPrice(100.5f);
        book.setPic("100101.jpg");
        book.setDesc("java进阶学习");
        */

        BookServiceImpl bookService = new BookServiceImpl();

        List<Book> books = bookService.queryBookList();

        //  创建Document文档对象
        List<Document> docs = new ArrayList<Document>();

        Document doc = new Document();

        for (Book book : books) {

            doc.add(new StoredField("id", book.getId().toString()));
            doc.add(new TextField("name", book.getName(), Field.Store.YES));
            doc.add(new TextField("price", book.getPrice().toString(), Field.Store.YES));
            doc.add(new StoredField("pic", book.getPic()));
            doc.add(new TextField("desc", book.getDesc(), Field.Store.NO));

            docs.add(doc);

        }



        //  创建分析器（分词器）
        Analyzer analyzer = new IKAnalyzer();

        //  创建IndexWriterConfig配置信息类
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);

        //  创建Directory对象，声明索引库存储位置
        Directory directory = FSDirectory.open(new File("G:/index").toPath());

        //  创建IndexWriter写入对象
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

        //  把Document写入到索引库中
        indexWriter.addDocuments(docs);

        //  释放资源
        indexWriter.close();


    }

}
