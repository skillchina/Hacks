package club.iandroid.hack50.subjectThread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import club.iandroid.hack50.LogUtils;
import club.iandroid.hack50.R;
import club.iandroid.hack50.subjectProxy.Operate;
import club.iandroid.hack50.subjectProxy.OperateImpl;
import club.iandroid.hack50.subjectProxy.TimingInvocationHandler;

/**
 * 线程 及 线程池 的使用
 * <p>
 * 相比new Thread，java提供的四种线程池的好处在于：
 * 1、重用存在的线程，减少对象创建、消亡的开销，性能佳
 * 2、可有效控制最大并发线程数，提高系统资源的使用率，同时避免过多资源竞争，避免堵塞
 * 3、提供定时执行、定期执行、单线程、并发数控制等功能
 * <p>
 * java通过Executors提供四种线程池，分别为：
 * 1、newCachedThreadPool 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * 2、newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
 * 3、newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行
 * 4、newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序（FIFO、LIFO）执行
 *
 * @author jiarong
 * @time 2016/10/11 16:20
 */
public class ThreadActivity extends AppCompatActivity {

    private Button btn_thread;
    private Button btn_cachedPool;
    private Button btn_fixedPool;
    private Button btn_scheduledPool;
    private Button btn_singelThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        initView();
        addEvent();
    }

    private void initView() {
        btn_thread = (Button) findViewById(R.id.btn_thread);
        btn_fixedPool = (Button) findViewById(R.id.btn_fixedPool);
        btn_scheduledPool = (Button) findViewById(R.id.btn_scheduledPool);
        btn_cachedPool = (Button) findViewById(R.id.btn_cachedPool);
        btn_singelThread = (Button) findViewById(R.id.btn_singelThread);
    }

    private void addEvent() {
        btn_thread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1、每次new Thread新建对象性能差
                //2、线程缺乏统一管理，可能无限制新建线程，相互之间竞争，及可能占用过多系统资源导致死机或oom
                //3、缺乏更多功能，如定时执行、定期执行、线程终端
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        for (int i = 0; i < 10; i++) {
                            LogUtils.log("thread->i:" + i);
                            try {
                                Thread.sleep(3000);
                            } catch (Exception e) {

                            }
                        }
                    }
                }.start();
            }
        });

        btn_cachedPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
                //线程池为无限大，当执行第二个任务时，第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程
                final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
                for (int i = 0; i < 10; i++) {
                    final int index = i;
                    try {
                        Thread.sleep(index * 1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    cachedThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            LogUtils.log("current index:" + index);
                        }
                    });
                }
            }
        });

        btn_fixedPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
                //因为线程池大小为3，每个任务输出后sleep2秒，所以每两次打印3个数字
                //定长线程池的大小最好根据系统资源进行配置。
                //如 Runtime.getRuntime().availableProcessors();可参考PreloadDataCache
                ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
                for (int i=0;i<10;i++){
                    final int index = i;
                    fixedThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                LogUtils.log("index:"+index);
                                Thread.sleep(2000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });

        btn_scheduledPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例：
                //ScheduledExecutorService比Tmer更安全，功能更强大
                ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
                scheduledThreadPool.schedule(new Runnable() {
                    @Override
                    public void run() {
                        LogUtils.log("delay 3 seconds");
                    }
                },  3, TimeUnit.SECONDS);
            }
        });

        btn_singelThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务
                //保证所有任务按照指定顺序（FIFO,LIFO)执行
                ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
                for (int i=0;i<10;i++){
                    final  int index = i;
                    singleThreadExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            //结果依次输出，相当于顺序执行各个任务
                            //现行大多数GUI程序都是单线程的，
                            // android中单线程可用于数据库操作
                            // 文件操作，应用批量安装、应用批量删除等不适合并发， 但可能IO阻塞性能及影响UI线程响应的操作
                            try{
                                LogUtils.log("--->"+index);
                                Thread.sleep(2000);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });

        findViewById(R.id.btn_invoke).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimingInvocationHandler timing = new TimingInvocationHandler(new OperateImpl());
                Operate operate = (Operate)(Proxy.newProxyInstance(Operate.class.getClassLoader(),
                        new Class[]{Operate.class}, timing));

                //call method of proxy instance
                operate.operateMethod1();
                operate.operateMethod2();
                operate.operateMethod3();


            }
        });
    }
}
