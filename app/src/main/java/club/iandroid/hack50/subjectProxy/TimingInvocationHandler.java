package club.iandroid.hack50.subjectProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import club.iandroid.hack50.LogUtils;

/**
 * 是负责链接代理类和委托类的中间类必须实现的接口
 * @author jiarong
 * @time 2016/10/11 18:06
 */
public class TimingInvocationHandler implements InvocationHandler {
    private Object target;//表示委托类对象

    public TimingInvocationHandler(){}

    public TimingInvocationHandler(Object target){
        this.target = target;
    }

    /**
     * 盗用代理对象的每个函数实际最终都是调用了InvocationHandler的invoke函数
     * @param proxy 表示通过Proxy.newProxyInstance()生成的代理类对象
     * @param method 表示代理对象被调用的函数
     * @param args
     * @return 表示代理对象被调用的函数的参数
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = method.invoke(target, args);
        LogUtils.log(method.getName()+" cost time is:"+(System.currentTimeMillis() - start));
        return obj;
    }
}
