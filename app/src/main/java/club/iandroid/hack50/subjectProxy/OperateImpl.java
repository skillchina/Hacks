package club.iandroid.hack50.subjectProxy;

import club.iandroid.hack50.LogUtils;

/**
 * 委托类，实现Operate接口
 */
public class OperateImpl implements Operate{
    @Override
    public void operateMethod1() {
        LogUtils.log("Invoke operateMethod1");
        sleep(110);
    }

    @Override
    public void operateMethod2() {
        LogUtils.log("Invoke operateMethod2");
        sleep(120);
    }

    @Override
    public void operateMethod3() {
        LogUtils.log("Invoke operateMethod3");
        sleep(130);
    }

    private static void sleep(long millSeconds){
        try {
            Thread.sleep(millSeconds);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
