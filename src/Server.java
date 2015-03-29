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
				//�����µĿͻ�������
				Socket client = serverSocket.accept();
				
				//�ӿͻ��˻�����ݲ���ӡ����
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				String str = in.readLine();
				System.out.println(client.getInetAddress() +" say: "+str);
				
				//��ͻ��˷�������
				BufferedWriter bw = null;
				bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
				bw.write("i am server!");
				bw.flush();
				
				//�ر�����
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
