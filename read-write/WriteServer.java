import java.net.*;

class WriteServer {
	public static int serverPort=998;
	public static int clientPort=999;
	public static int bufferSize=1024;
	public static byte buffer[]=new byte[bufferSize];
	public static DatagramSocket ds;

	public static void Server() throws Exception {
		int pos=0;
		while(true) {
			int c=System.in.read();
			switch(c) {
				case -1:
					System.out.println("Server Quits!!");
					return;
				case '\r':
					break;
				case '\n':
					ds.send(new DatagramPacket(buffer,pos,InetAddress.getLocalHost(),clientPort));
					pos=0;
					break;
				default:
					buffer[pos++]=(byte)c;
			}
		}
	}

	public static void Client() throws Exception {

		while(true){
			DatagramPacket p=new DatagramPacket(buffer,buffer.length);
			ds.receive(p);
			System.out.println(new String(p.getData(),0,p.getLength()));
		}
	}

	public static void main(String args[]) throws Exception {
		if(args.length==1) {
			ds=new DatagramSocket(serverPort);
			Server();

		}
		else {
			ds=new DatagramSocket(clientPort);
			Client();
		}
	}

}
