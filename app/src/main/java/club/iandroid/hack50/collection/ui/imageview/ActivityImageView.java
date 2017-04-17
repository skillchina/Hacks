package club.iandroid.hack50.collection.ui.imageview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.QuickContactBadge;

import com.example.mydemo.R;

public class ActivityImageView extends Activity 
{
	//定义一个访问图片的数组
	int[] images = new int[]{R.drawable.block_1,R.drawable.block_2,
			R.drawable.block_3,R.drawable.block_4,R.drawable.block_5};
	
	//定义默认显示的图片
	int currentImg = 2;
	//定义图片的初始透明度
	private int alpha = 255;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageview_demo);
		final ImageView image1 = (ImageView)findViewById(R.id.imageView1);
		final ImageView image2 = (ImageView)findViewById(R.id.imageView2);
		final Button plus = (Button)findViewById(R.id.plus);
		plus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		final Button minus = (Button)findViewById(R.id.minus);
		minus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		Button next = (Button)findViewById(R.id.next);
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//控制ImageView显示下一张图片
				image1.setImageResource(images[++currentImg % images.length]);
			}
		});
		//定义改变图片透明度的方法
		OnClickListener listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(v == plus)
				{
					alpha += 20;
				}
				if(v == minus)
				{
					alpha -= 20;
				}
				if(alpha >= 255)
				{
					alpha = 255;
				}
				if(alpha <= 0)
				{
					alpha = 0;
				}
				//改变图片的透明度
				image1.setAlpha(alpha);
				
			}
		};
		//为两个按钮添加监听器
		plus.setOnClickListener(listener);
		minus.setOnClickListener(listener);
		
		image1.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				BitmapDrawable bitmapDrawable = (BitmapDrawable)image1.getDrawable();
				//获取第一个图片显示框中的位图
				Bitmap bitmap = bitmapDrawable.getBitmap();
				//bitmap图片实际大小与第一个ImageView的缩放比例
				double scale = bitmap.getWidth() / 320.0;
				//获取需要显示的图片的开始点
				int x = (int) (event.getX() * scale);
				int y = (int) (event.getY() * scale);
				if(x + 10 > bitmap.getWidth())
				{
					x = bitmap.getWidth() - 10;
				}
				if(y + 10 > bitmap.getHeight())
				{
					y = bitmap.getHeight() - 10;
				}
				//显示图片的指定区域
				image2.setImageBitmap(Bitmap.createBitmap(bitmap,x,y,10,10));
				image2.setAlpha(alpha);
				return false;
			}
		});
		
		QuickContactBadge badge = (QuickContactBadge)findViewById(R.id.badge);
		//将QuickContactBadge组件与特定电话号码对应的联系人建立关联
		badge.assignContactFromPhone("051268559854", false);
	}
}
