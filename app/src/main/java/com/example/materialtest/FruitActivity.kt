package com.example.materialtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_fruit.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar

class FruitActivity : AppCompatActivity() {
    
    companion object {
        const val FRUIT_NAME = "fruit_name"
        const val FRUIT_IMAGE_ID = "fruit_image_id"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit)

        //通过intent获取了传入的水果名和水果图片的资源id
        val fruitName = intent.getStringExtra(FRUIT_NAME) ?: ""
        val fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0)

        //接着使用了Toolbar的标准用法，将它作为ActionBar显示
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //调用setTitle()方法，将水果名设置成当前界面的标题
        collapsingToolbar?.title = fruitName
        //然后使用Glide加载传入的水果图片，并且设置到标题栏的ImageView上面
        Glide.with(this).load(fruitImageId).into(fruitImageView)
        fruitContentText.text = generateFruitContent(fruitName)
    }

    //并启用Home按钮，由于Home按钮的默认图标就是一个返回箭头，这正是我们所期望的，因此不用额外设置别的图标了
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                //当点击这个按钮时，就调用finish()方法关闭当前的Activity，从而返回上一个Activity
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //将水果名循环拼接500次，从而生成了一个比较长的字符串，将它设置到了TextView上面
    private fun generateFruitContent(fruitName: String) = fruitName.repeat(500)

}