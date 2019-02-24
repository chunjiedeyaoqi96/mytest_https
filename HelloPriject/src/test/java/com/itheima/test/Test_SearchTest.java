package com.itheima.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

/**
 * 包名: com.itheima.test
 * 作者: CF
 * 时间: 2019/2/22 9:27
 *
 * 搜索实现
 *
 */
public class Test_SearchTest {


    /*
    @Test
    public void test_SearchTest() throws Exception{

        //  创建分词器
        Analyzer analyzer = new StandardAnalyzer();

        //  创建Query搜索对象
        QueryParser queryParser = new QueryParser("desc", analyzer);

        Query query = queryParser.parse("desc:java");

        //  创建Directory流对象,声明索引库位置
        Directory directory = FSDirectory.open(new File("G:/index").toPath());

        //  创建索引读取对象IndexReader
        IndexReader indexReader = DirectoryReader.open(directory);

        //  创建索引搜索对象IndexSearcher
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        //  使用索引搜索对象，执行搜索，返回结果集TopDocs
        TopDocs docs = indexSearcher.search(query, 10);

        //  解析结果集
        ScoreDoc[] scoreDocs = docs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {

            Document document = indexSearcher.doc(scoreDoc.doc);
            System.out.println("id: "+document.get("id"));
            System.out.println("name: "+document.get("name"));
            System.out.println("price: "+document.get("price"));
            System.out.println("pic: "+document.get("pic"));
            System.out.println("desc: "+document.get("desc"));

        }


        //  释放资源
        indexReader.close();

    }
    */



    @Test
    public void test_IK() throws Exception{

        //  创建分词器
        Analyzer analyzer = new IKAnalyzer();

        //  创建Query搜索对象
        QueryParser queryParser = new QueryParser("name", analyzer);
        Query parse = queryParser.parse("name:蛤蛤蛤蛤蛤");

        //  创建Directory流对象,声明索引库位置
        Directory directory = FSDirectory.open(new File("G:/index").toPath());

        //  创建索引读取对象IndexReader
        IndexReader indexReader = DirectoryReader.open(directory);

        //  创建索引搜索对象IndexSearcher
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        //  使用索引搜索对象，执行搜索，返回结果集TopDocs
        TopDocs docs = indexSearcher.search(parse, 10);

        //  解析结果集
        ScoreDoc[] scoreDocs = docs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {

            Document document = indexSearcher.doc(scoreDoc.doc);
            System.out.println("id: "+document.get("id"));
            System.out.println("name: "+document.get("name"));
            System.out.println("price: "+document.get("price"));
            System.out.println("pic: "+document.get("pic"));
            System.out.println("desc: "+document.get("desc"));


        }

        //  释放资源
        indexReader.close();

    }

}
