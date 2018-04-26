package com.hqnRegression.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;


public class PuttyConnection {
	
	public static void main(String[] args){
		PuttyConnection t=new PuttyConnection();
        try{
            t.go();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void go() throws Exception{
        String host="163.164.86.135";
        String user="jeyaraja";
        String password="xxxx";
        int port=22;
        /*String domain = "WLMSDEV";
        
        
     // creating credentials
        //Process p = Runtime.getRuntime().exec("cmdkey /generic:"+host+" /user:"+user+" /pass:"+password );
        Process p = Runtime.getRuntime().exec("cmdkey /generic:"+host+" /user:"+domain+"\""+user+" /pass:"+password );
       // p.destroy();
       Runtime.getRuntime().exec("mstsc /v: "+host+"");
       Thread.sleep(2*60*1000); // min sec millisec 
*/        
        
        
        /*int port=3389;
        String command1="ls -ltr";
        
        int tunnelLocalPort=3388;
        String tunnelRemoteHost="HDEV-RBMAPP-02";
        int tunnelRemotePort=22;*/
        
        JSch jsch=new JSch();
        Session session=jsch.getSession(user, host, port);
        session.setPassword(password);
        
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        System.out.println("Connected to Session");
        
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp csftp = (ChannelSftp) channel;

        File file = new File("C:/JenkinsMaven/mavenparam.properties"); //local file location 
        String fileAbsolutePath = "/tmp/mavenparam.properties";//The to location on remote server

        csftp.put(new FileInputStream(file), fileAbsolutePath);
        
        csftp.disconnect();
        channel.disconnect();
        
                
        /*ChannelExec channel1=(ChannelExec) session.openChannel("exec");
        BufferedReader in=new BufferedReader(new InputStreamReader(channel1.getInputStream()));
        //channel1.setCommand("ls -l /tmp");
        channel1.setCommand("scp /tmp/mavenparam.properties jeyaraja@d3-actcomp-01:22 /tmp");
        channel1.connect();

        String msg=null;
        while((msg=in.readLine())!=null){
          System.out.println(msg);
        }

        
        channel1.disconnect();
        */
        
        session.disconnect();
        
        System.out.println("DONE");
        
       /* session.setPortForwardingL(tunnelLocalPort,tunnelRemoteHost,tunnelRemotePort);
        System.out.println("Port Forwarding done..");
        
        
        Channel channel=session.openChannel("exec");
        ((ChannelExec)channel).setCommand(command1);
        channel.setInputStream(null);
        ((ChannelExec)channel).setErrStream(System.err);
         
        InputStream in=channel.getInputStream();
        channel.connect();
        byte[] tmp=new byte[1024];
        while(true){
          while(in.available()>0){
            int i=in.read(tmp, 0, 1024);
            if(i<0)break;
            System.out.print(new String(tmp, 0, i));
          }
          if(channel.isClosed()){
            System.out.println("exit-status: "+channel.getExitStatus());
            break;
          }
          try{Thread.sleep(1000);}catch(Exception ee){}
        }
        channel.disconnect();
        session.disconnect();
        System.out.println("DONE");*/
    
    }
    
  class localUserInfo implements UserInfo{
    String passwd;
    public String getPassword(){ return passwd; }
    public boolean promptYesNo(String str){return true;}
    public String getPassphrase(){ return null; }
    public boolean promptPassphrase(String message){return true; }
    public boolean promptPassword(String message){return true;}
    public void showMessage(String message){}
  } 
}
