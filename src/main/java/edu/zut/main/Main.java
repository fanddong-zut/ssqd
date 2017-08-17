package edu.zut.main;

import com.sun.org.apache.xpath.internal.operations.Bool;
import edu.zut.utils.DocUtil;
import edu.zut.utils.JdbcUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FDD on 2017/8/17.
 */
public class Main {
    private static final Integer TOTAL_PAGE = 108;//总页数

    public static void main(String[] args) {
        String url = "http://kaijiang.zhcw.com/zhcw/inc/ssq/ssq_wqhg.jsp?pageNum=";
        Document document = null;

        for(int i=1;i<=108;i++) {
            String totalUrl="";
            if(i!=1)
                totalUrl=url+i;
            else
                totalUrl=url.substring(0,url.indexOf("?"));
            System.out.println("当前链接："+totalUrl);
            document = DocUtil.connect(totalUrl);

            parseDoc(document);
        }



    }


    public static void parseDoc(Document doc) {
        Elements elements = doc.select("table.wqhgt >tbody >tr");
        for(Element element:elements){
            Elements tds=element.select(">td");
//            System.out.println("--->>"+tds.size());
            if(tds.size()==7) {
                String date=tds.get(0).text();
                String issue=tds.get(1).text();
                String opeNum=tds.get(2).text();//开奖号码
                String money=tds.get(3).text();//销售额
                String firstNum=tds.get(4).text();//一等奖中奖注数
                String secondNum=tds.get(5).text();//二等奖中奖注数
//                Period period=new Period(date,issue,opeNum,money,firstNum,secondNum);
                List list =new ArrayList();
                list.add(date);
                list.add(issue);
                list.add(getAfterStr(opeNum));
                list.add(money);
                list.add(firstNum);
                list.add(secondNum);
//                System.out.println(list);
                saveData(list);
            }
        }
    }

    public static void saveData(List param) {
        String sql ="insert into t_period (date,issue,opeNum,money,firstNum,secondNum) values (?,?,?,?,?,?)";
        try {
            Boolean flag = JdbcUtils.insertByPreparedStatement(sql,param);
            if (flag){
                System.out.printf("--->%s<---",param.get(1)+"保存成功");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

    }


    public static String getAfterStr(String str){
//        08 10 16 20 23 30 09
        String s1 = str.substring(str.length()-2);
        String s2 = str.substring(0,str.length()-2);

        return s2 + "| " + s1;
    }
//    public static void
}
