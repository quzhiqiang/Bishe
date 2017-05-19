package com.qzq.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import com.qzq.bean.ChatBean;
import com.qzq.util.GetDate;
import com.qzq.util.GetHttpSessionConfigurator;


@ServerEndpoint(value="/websocket",configurator=GetHttpSessionConfigurator.class)  
public class WebSocketListen {  
  
    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。  
    private static int onlineCount = 0;  
  
    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识  
    private static Map<String,WebSocketListen> webSocketMap = new ConcurrentHashMap<String,WebSocketListen>();
    
    private static CopyOnWriteArraySet<WebSocketListen> writeMap = new CopyOnWriteArraySet<WebSocketListen>();
  
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据  
    private Session session; 
    
    private HttpSession httpSession;
    
    private String nickname = null;
    
    private String picture = null;
    
      
    /** 
     * 连接建立成功调用的方法 
     *  
     * @param session 
     *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据 
     */  
    @OnOpen  
    public void onOpen(Session session,EndpointConfig config) {
    	this.httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
    	nickname = (String) httpSession.getAttribute("userName");
    	picture =  (String) httpSession.getAttribute("picture");
    	if(webSocketMap.containsKey(nickname)){
    		//单个用户连接已建立
    		writeMap.remove(webSocketMap.get(nickname));
    		webSocketMap.remove(nickname);
    		this.session = session;
    		webSocketMap.put(nickname, this);
    		writeMap.add(this);
    	}
    	else{
        this.session = session;
        webSocketMap.put(nickname, this);
        writeMap.add(this);
        addOnlineCount(); // 在线数加1 
    	}
    }  
  
    /** 
     * 连接关闭调用的方法 
     */  
    @OnClose  
    public void onClose() {  
    	webSocketMap.remove(nickname); // 从set中删除  
    	writeMap.remove(this);
        subOnlineCount(); // 在线数减1
    }  
  
    /** 
     * 收到客户端消息后调用的方法 
     *  
     * @param message 
     *            客户端发送过来的消息 
     * @param session 
     *            可选的参数 
     */  
    @OnMessage  
    public synchronized void onMessage(String message, Session session) {
        if(webSocketMap.containsValue(this)){
            Iterator<WebSocketListen> it = webSocketMap.values().iterator();
        	 while(it.hasNext()){
                 WebSocketListen item = it.next();
                 if(item != null){
                	 try {  
                     	String time = new GetDate().getFormatdate("HH:mm:ss");
                     	ChatBean chatBean = new ChatBean();
                     	chatBean.setMessage(message);
                     	chatBean.setUserName(nickname);
                     	chatBean.setPicture(picture);
                     	chatBean.setTime(time);
                     	JSONObject json = new JSONObject(chatBean);
                         item.sendMessage(json.toString());  
                     } catch (IOException e) {  
                         e.printStackTrace();  
                         continue;  
                     }  
                 }
                
             }
        }
        else{
        	try {
				this.sendMessage("warning");
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
       
    }  
  
    /** 
     * 发生错误时调用 
     *  
     * @param session 
     * @param error 
     */  
    @OnError  
    public void onError(Session session, Throwable error) {
        System.out.println("聊天室连接发生错误");  
        error.printStackTrace();  
    }  
  
    /** 
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。 
     *  
     * @param message 
     * @throws IOException 
     */  
    public void sendMessage(String message) throws IOException {
                	 this.session.getAsyncRemote().sendText(message); 
        
    }  
  
    public static synchronized int getOnlineCount() {  
        return onlineCount;  
    }  
  
    public static synchronized void addOnlineCount() {  
        WebSocketListen.onlineCount++;  
    }  
  
    public static synchronized void subOnlineCount() {  
        WebSocketListen.onlineCount--;  
    }
    
}
