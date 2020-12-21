package com.example.layout_constr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;
    /**
     * Android
     * onCreate:create表示创建，这是Activity生命周期的第一个方法，也是我们在android开发中接触的最多的生命周期方法。它本身的作用是进行Activity的一些初始化工作，比如使用setContentView加载布局，对一些控件和变量进行初始化等。但也有很多人将很多与初始化无关的代码放在这，其实这是不规范的。此时Activity还在后台，不可见。所以动画不应该在这里初始化，因为看不到……
     *
     * viewDidload: 初始化view
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.recycleViewId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,HomeActivity.class));
            }
        });
    }

    /**
     * Android
     * onStart:start表示启动，这是Activity生命周期的第二个方法。此时Activity已经可见了，但是还没出现在前台，我们还看不到，无法与Activity交互。其实将Activity的初始化工作放在这也没有什么问题，放在onCreate中是由于官方推荐的以及我们开发的习惯
     *
     * viewWillAppear： 将要可见
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Android
     * 表示继续、重新开始，这名字和它的职责也相同。此时Activity经过前两个阶段的初始化已经蓄势待发。Activity在这个阶段已经出现在前台并且可见了。这个阶段可以打开独占设备
     *
     * viewDidAppear： 可见
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Android
     * pause表示暂停，当Activity要跳到另一个Activity或应用正常退出时都会执行这个方法。此时Activity在前台并可见，我们可以进行一些轻量级的存储数据和去初始化的工作，不能太耗时，因为在跳转Activity时只有当一个Activity执行完了onPause方法后另一个Activity才会启动，而且android中指定如果onPause在500ms即0.5秒内没有执行完毕的话就会强制关闭Activity。从生命周期图中发现可以在这快速重启，但这种情况其实很罕见，比如用户切到下一个Activity的途中按back键快速得切回来
     *
     * viewWillDisappear
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Android
     * top表示停止，此时Activity已经不可见了，但是Activity对象还在内存中，没有被销毁。这个阶段的主要工作也是做一些资源的回收工作
     * viewDidDisappear
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * Android
     * destroy表示毁灭，这个阶段Activity被销毁，不可见，我们可以将还没释放的资源释放，以及进行一些回收工作
     * dealloc
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Android
     * restart表示重新开始，Activity在这时可见，当用户按Home键切换到桌面后又切回来或者从后一个Activity切回前一个Activity就会触发这个方法。这里一般不做什么操作
     * viewWillAppear
     */
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /**
     *  onCreate和onStart之间有什么区别？
     *  1.可见与不可见的区别。前者不可见，后者可见。
     *  2.执行次数的区别。onCreate方法只在Activity创建时执行一次，而onStart方法在Activity的切换以及按Home键返回桌面再切回应用的过程中被多次调用。因此Bundle数据的恢复在onStart中进行比onCreate中执行更合适。
     *  3.onCreate能做的事onStart其实都能做，但是onstart能做的事onCreate却未必适合做。如前文所说的，setContentView和资源初始化在两者都能做，然而想动画的初始化在onStart中做比较好
     *
     */

    /**
     *
     * onPause方法和onStop方法有什么区别？
     * 是否可见。onPause时Activity可见，onStop时Activity不可见，但Activity对象还在内存中
     * 在系统内存不足的时候可能不会执行onStop方法，因此程序状态的保存、独占设备和动画的关闭、以及一些数据的保存最好在onPause中进行，但要注意不能太耗时
     *
     */

    /**
     * onStop阶段Activity还没有被销毁，对象还在内存中，此时可以通过切换Activity再次回到该Activity，而onDestroy阶段Acivity被销毁
     *
     *
     *
     */
}
