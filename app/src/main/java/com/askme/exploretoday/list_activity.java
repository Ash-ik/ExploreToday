
package com.askme.exploretoday;


import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class list_activity extends Activity {
	ProgressDialog loading;
	MyBaseAdapter adapter0,adapter1;
		ListView lvDetail;
		TextView tv;
		String xmlLink;
		Context context = list_activity.this;
        ArrayList<ListData> myList0 = new ArrayList<ListData>();
        ArrayList<ListData>myList1= new ArrayList<ListData>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.caption_list);

                xmlLink=getIntent().getExtras().getString("newspaper_name");
                lvDetail = (ListView) findViewById(R.id.lvCustomList);


        		tv=(TextView) findViewById(R.id.news_caption);

                try{
                new backgroundProcess().execute(adapter0,adapter1);
                }catch(Exception e){
                	tv.setText(e.toString());
                }





		        lvDetail.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						String url=adapter0.getItem(position).getDescription();
						String c=adapter0.getItem(position).getTitle();
						String d=adapter0.getItem(position).getDetails();
						Intent intent=new Intent(context,newsPaper_launcher.class);
						intent.putExtra("URL", url);
						intent.putExtra("Title",c);
						intent.putExtra("Description",d);
						startActivity(intent);
					}
				});




		        }


    	public class backgroundProcess extends AsyncTask<MyBaseAdapter, Integer, MyBaseAdapter>{

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				loading=new ProgressDialog(context);
		        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		        loading.setMessage("Please wait...");
		        loading.setIndeterminate(true);
		        loading.setCancelable(true);
		        loading.show();

			}
			@Override
			protected MyBaseAdapter doInBackground(MyBaseAdapter... adapterArray) {
				// TODO Auto-generated method stub

				// insert fake data into the list before setting the adapter
                // otherwise it will generate NullPointerException  - Obviously

                ListData ld = new ListData();
                ld.setTitle("fakeData");
                ld.setDescription("fakeData");
                ld.setDetails("fake Data");
                myList0.add(ld);
                myList1.add(ld);



                adapterArray[0]=new MyBaseAdapter(context, myList0);
				adapter0=new MyBaseAdapter(context, myList0);
        		String title,address,details;
            	XMLParser parser = new XMLParser();
            	String xml = null;
            	try{
            		xml=parser.getXmlFromUrl(xmlLink);
//            		xml=parser.getXmlFromUrl("http:/newagebd.net/feed/");
//            		xml=parser.getXmlFromUrl("http://www.ittefaq.com.bd/rss.xml");
//                xml= parser.getXmlFromUrl("http://bdnews24.com/?widgetName=rssfeed&widgetId=1150&getXmlFeed=true"); // getting XML
//            	xml=parser.getXmlFromUrl("http://thebangladeshtoday.com/feed/");
//            	xml=parser.getXmlFromUrl("http://bangla.bdnews24.com/?widgetName=rssfeed&widgetId=1151&getXmlFeed=true");
//            	xml=parser.getXmlFromUrl("http://www.amardeshonline.com/pages/rss");
            	}catch(Exception e)
            	{
            		tv.setText(e.toString());
            	}
				Element cap,url,news;
				ListData temp;
                Document doc = parser.getDomElement(xml); // getting DOM element
                NodeList caption=doc.getElementsByTagName("title");
                NodeList link = doc.getElementsByTagName("link");
                NodeList fullNews=doc.getElementsByTagName("description");
                	for (int i = 2; i < link.getLength(); i++) {
                    cap = (Element) caption.item(i);
                    url = (Element) link.item(i);
                    news=(Element) fullNews.item(i);
                   title = parser.getValue(cap, "title");
                   address = parser.getValue(url, "link");
                   details=parser.getValue(news, "description");
						Log.i("INFO Check",title+"\n"+address+"\n"+details);
						temp = new ListData();
                   temp.setTitle(title);
                   temp.setDescription(address);
                   temp.setDetails(details);
                   myList0.add(temp);
                }

                myList0.remove(0);

                adapter0.notifyDataSetChanged();
                adapterArray[0].notifyDataSetChanged();

				return adapterArray[0];

			}
                @Override
    			protected void onPostExecute(MyBaseAdapter result) {
    				// TODO Auto-generated method stub
                	loading.cancel();
    				lvDetail.setAdapter(adapter0);
//    				super.onPostExecute(result);
    			}

			}



}
