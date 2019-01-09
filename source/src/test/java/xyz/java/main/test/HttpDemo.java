package xyz.java.main.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import xyz.frame.utils.HttpClientUtils;

/**
 * http 测试
 * @author shisp
 * @date 2018-12-29 09:58:44
 */
public class HttpDemo {
    public static int total = 100;
    
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        int length = 10;
        ExecutorService service = Executors.newFixedThreadPool(length);
        List<List<String>> lls = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            Future<List<String>> submit = service.submit(()-> test1());
            lls.add(submit.get(60,TimeUnit.SECONDS));
        }
        
        
        for (List<String> fls : lls) {
            for (int i = 0; i < total; i++) {
                String string = fls.get(i);
                if (i%10 == 0 && i!=0) {
                    System.out.println();
                }
                System.out.print(string);
                System.out.print(" : ");
            }
            System.out.println();
            System.out.println("**********************************************************************************************************");
            
        }
        
        try {
            Thread.sleep(99999999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static List<String> test1() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            long currentTimeMillis = System.currentTimeMillis();
            String postJson = HttpClientUtils.postJson("https://t1-managerdaikuan.2345.com/customer-service/app/message/tips","{\"pid\":10002,\"uid\":115954839}");
//            String postJson = HttpClientUtils.postJson("https://managerdaikuan.2345.com/customer-service/app/message/tips","{\"pid\":10002,\"uid\":100293356}");
//            String postJson = HttpClientUtils.postJson("http://127.0.0.1:8088/customer-service/app/message/tips","{\"pid\":10002,\"uid\":100058357}");
            long currentTimeMillis2 = System.currentTimeMillis();
//            list.add(postJson+":"+(currentTimeMillis2-currentTimeMillis));
            list.add(""+(currentTimeMillis2-currentTimeMillis));
            System.out.println(postJson);
        }
        return list;
        
    }
    

}
