package school.lichangxin.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;

public class JsoupTest {
    public static void main(String[] args)  throws  Exception{
        // 获取student.xml 的path
        String path = JsoupTest.class.getClassLoader().getResource("student.xml").toURI().getPath();
        // 解析 xml 文件，加载文档进内存
        Document document = Jsoup.parse(new File(path), "utf-8");
        // 获取元素对象 Elements 的集合 ArrayList<Element>
        Elements elements = document.getElementsByTag("name");
        // System.out.println(elements.size());
        Element element = elements.get(0);
        String text = element.text();
        System.out.println(text);
    }
}
