package com.example.dump;

import java.io.File;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	public TextView textView;
	public Button buttonSelect;
	public RelativeLayout relativeLayout;
	public int width;
	public int height;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        relativeLayout = (RelativeLayout)findViewById(R.id.RelativeLayout);
		
        buttonSelect = new Button(this);
		buttonSelect.setText("Dump");
		buttonSelect.setOnClickListener(listener_select);
		buttonSelect.setVisibility(View.INVISIBLE);
		
		textView = new TextView(this);
		textView.setText("1:��libmono.so����sd����·���µ�Dump�ļ�����(ע�����ִ�Сд)\n2:�����ܵ�dll����sd����·���µ�Dump�ļ�����(ע�����ִ�Сд)\n3:���Dump��ť���ɽ���\n4:ע��libmono.so��ָ�һ��Ҫ�����л���һ��");
		textView.setEnabled(false);
		textView.setTextColor(Color.rgb(0, 0, 0));
		textView.setGravity(Gravity.TOP);
		
		relativeLayout.addView(buttonSelect);
		relativeLayout.addView(textView);

    }

    
    public void onWindowFocusChanged(boolean hasFocus)  
	{  
		if (hasFocus)  
		{  
			width = relativeLayout.getWidth();
			height = relativeLayout.getHeight();   
			
			LayoutParams param;  
			param = textView.getLayoutParams();
			param.width = width - 20;
			param.height = height - 100;
			textView.setX(10);
			textView.setY(0);
			textView.setLayoutParams(param);
			
			buttonSelect.setX(width/2 - 100);
			buttonSelect.setY(height - 100);
			buttonSelect.setWidth(200);
			buttonSelect.setHeight(100);
			buttonSelect.setVisibility(View.VISIBLE);
		}  
	} 
    
    public static boolean IsSdCardExist() {  
        return Environment.getExternalStorageState().equals(  
                Environment.MEDIA_MOUNTED);  
    }
    
    public static String GetSdCardPath() {  
	    boolean exist = IsSdCardExist();  
	    String sdpath = "";  
	    if (exist) {  
	        sdpath = Environment.getExternalStorageDirectory()  
	                .getAbsolutePath();  
	    } else {  
	        sdpath = "";  
	    }  
	    return sdpath;  
	  
	}  
    
    private OnClickListener listener_select = new OnClickListener(){  

		@Override  
		public void onClick(View view) { 
			
			textView.setText("");
			
			DumpJni dump = new DumpJni(textView);
			
			String root = GetSdCardPath() + "/Dump";
			
			File destDir = new File(root);
			if (!destDir.exists()) 
			{
			   destDir.mkdirs();
			}
			
			dump.dump(root);
		}  

	};  
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
