import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server implements Runnable {

	public void run() {
		try {
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(12345);
			System.out.println("server start...");
			while(true){
				//接受新的客户端连接
				Socket client = serverSocket.accept();
				
				//从客户端获得数据并打印出来
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				String str = in.readLine();
				System.out.println(client.getInetAddress() +" say: "+str);
				
				//向客户端发送数据
				BufferedWriter bw = null;
				bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
				bw.write("i am server!");
				bw.flush();
				
				//关闭连接
				bw.close();
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		Thread deskThread = new Thread(new Server());
		deskThread.start();
	}

}
