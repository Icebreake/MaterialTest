package com.example.materialtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    //定义了一个水果集合，集合里面存放了很多个Fruit的实例，每个实例都代表一种水果
    val fruits = mutableListOf(Fruit("Apple", R.drawable.apple),
                                Fruit("Banana", R.drawable.banana),
                                Fruit("Orange", R.drawable.orange),
                                Fruit("Watermelon", R.drawable.watermelon),
                                Fruit("Pear", R.drawable.pear),
                                Fruit("Grape", R.drawable.grape),
                                Fruit("Pineapple", R.drawable.pineapple),
                                Fruit("Strawberry", R.drawable.strawberry),
                                Fruit("Cherry", R.drawable.cherry),
                                Fruit("Mango", R.drawable.mango)
    )

    val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //调用setSupportActionBar()方法并将ToolBar的实例传入，这样就做到既使用了ToolBar，又让它的外观与功能都和ActionBar一致了
        setSupportActionBar(toolbar)
        //调用getSupportActionBar()方法得到了ActionBar的实例，虽然这个ActionBar的具体实现是由Toolbar来完成的
        supportActionBar?.let {
            //在它不为空的请联系调用setDisplayHomeAsUpEnabled()方法让导航按钮显示出来
            it.setDisplayHomeAsUpEnabled(true)
            //调用setHomeAsUpIndicator()方法来设置一个导航按钮图标
            /*
            实际上，Toolbar最左侧的这个按钮就叫作Home按钮，它默认的图标是一个返回的箭头，含义是返回上一个Activity
            很明显，这里我们将它默认的样式和作用都进行了修改
             */
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        //将Call菜单项设置为默认选中
        navView.setCheckedItem(R.id.navCall)
        //设置一个菜单项选中事件的监听器，当用户点击了任意菜单项时，就会回调到传入的Lambda表达式当中，可以在这里编写具体的逻辑处理
        navView.setNavigationItemSelectedListener {
            //调用closeDrawers()将滑动菜单关闭
            drawerLayout.closeDrawers()
            //返回true表示此事件已经被处理
            true
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT)
                .setAction("Undo") {
                    Toast.makeText(this, "Data restored", Toast.LENGTH_SHORT).show()
                }.show()
        }

        initFruits()
        //接收两个参数：第一个是Context；第二个是列数。这里希望每一行中会有两列数据
        val layoutManager = GridLayoutManager(this,2)
        recyclerView.layoutManager = layoutManager
        val adapter = FruitAdapter(this, fruitList)
        recyclerView.adapter = adapter

        //设置下拉刷新进度条的颜色，这里使用主题中的colorPrimary作为进度条的颜色
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        //设置一个下拉刷新的监听器，当用户进行了下拉刷新操作时，就会回调到Lambda表达式中，如何在这里去处理具体的刷新逻辑即可
        swipeRefresh.setOnRefreshListener {
            refreshFruits(adapter)
        }

    }

    /*
    通常情况下，应该是去网络上请求最新的数据，然后再将这些数据展示出来，这里简单期间，就不和网络进行交互了
    本地刷新操作
     */
    private fun refreshFruits(adapter: FruitAdapter) {
        thread {
            //之所以将线程沉睡两秒钟，是因为本地刷新操作速度非常快，如果不沉睡的话，刷新立刻就结束了，从而看不到刷新的过程
            Thread.sleep(2000)
            //将线程切换回主线程
            runOnUiThread{
                //重新生成数据
                initFruits()
                //通知数据发生了变化
                adapter.notifyDataSetChanged()
                //false表示刷新事件结束，并隐藏刷新进度条
                swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun initFruits() {
        //先清空一下fruitList中的数据
        fruitList.clear()
        //使用了repeat()函数，随机挑选50个水果
        repeat(50) {
            //使用一个随机函数，从刚才定义的Fruit数组中随机挑选一个水果放入fruitList中的数据，这样每次打开程序看到的水果数据都会是不同的
            val index = (0 until fruits.size).random()
            fruitList.add(fruits[index])
        }

    }

    //加载了toolbar.xml这个菜单文件
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    //处理各个按钮的点击事件
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            //Home按钮的id永远都是android.R.id.home，然后调用openDrawer()将滑动菜单展示出来
                //openDrawer()要求传入一个Gravity参数，为了保证这里的行为和XML中定义的一致，我们传入了GravityCompat.START
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
            R.id.backup -> Toast.makeText(this, " You clicked Backup", Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, " You clicked Delete", Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this, " You clicked Settings", Toast.LENGTH_SHORT).show()
        }
        return true
    }

}