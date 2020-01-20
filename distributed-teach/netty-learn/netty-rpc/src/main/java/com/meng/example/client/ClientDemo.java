package com.meng.example.client;

import com.meng.example.contract.intf.UserIntf;
import com.meng.example.contract.model.Ad;
import com.meng.example.contract.model.UserInfo;
import com.meng.example.rpc.RpcClient;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ClientDemo {

    public static void main(String[] args) throws Exception {
        client();
    }

    private static void client() throws IOException, InterruptedException {
        for (int i = 0; i < 1; i++) {
            final int b = i + 1;
            new Thread(() -> {
                try (RpcClient client = RpcClient.get("localhost", 9000) ) {
                    UserIntf userIntf = client.getProxy(UserIntf.class);
                    int pageSize = 1;
                    int start = (b - 1) * pageSize;
                    int end = b * pageSize;
                    for (int j = start; j < end; j++) {
                        UserInfo userInfo = userIntf.find(j);
                        System.out.println("查询的结果1：" + userInfo);
                        List<UserInfo> userInfos = userIntf.findById(j);
                        System.out.println("查询的结果2：" + userInfos);
                        UserInfo[] userInfoArray = userIntf.findByIds(new int[]{j});
                        System.out.println("查询的结果3：" + userInfoArray);
                        List<Ad> ads = userIntf.findByUserinfos(userInfos);
                        System.out.println("查询的结果4：" + ads);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        //System.exit(0);
    }

    private static void test() throws IOException {
        byte[] a = "{[".getBytes(StandardCharsets.UTF_8);
        byte[] b = "]}".getBytes(StandardCharsets.UTF_8);

        System.in.read();
    }

    private static void testTypeToClass() {
        List<UserInfo> userInfo = new ArrayList<>();
        Class clazz = userInfo.getClass();
        // 获取该类直接父类的类型
        Type type = clazz.getGenericSuperclass();
        // 通过ParameterizedType获取 此类型实际类型参数的 Type对象的数组
        ParameterizedType pType = (ParameterizedType) type;
        Type[] types = pType.getActualTypeArguments();
        // Type类型所有已知实现类： Class，所以可以强制转换
        Class<UserInfo> cClass = (Class<UserInfo>) types[0];
        System.out.println(cClass);
        System.out.println(types[0].getClass());
    }

}
