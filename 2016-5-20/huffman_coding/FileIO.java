package huffman_coding;
import java.io.*;
public class FileIO {
	private static File file =null;
	
	public static String readFile(String filepath){ //读取文件方法，输入文件目录
		String text = "";
		FileInputStream fein = null;	//创建输入流
		try {							//捕获IO流可能产生的异常
			file = new File(filepath);
			fein = new FileInputStream(file);
			byte[] byarry = new byte[1024];
			int len = 0;
			while((len=fein.read(byarry))!=-1){
				text += new String (byarry,0,len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{						//无论之前语句运行如何都会执行close()来释放IO流内存
			try{	
				fein.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return text;
	}
	
	public static void saveFile(byte[] data,String FilePath){
		file = new File(FilePath);
		FileOutputStream fos = null;
		try {
			fos=new FileOutputStream(file);
			fos.write(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				fos.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

}
