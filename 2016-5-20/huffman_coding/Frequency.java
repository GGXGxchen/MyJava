package huffman_coding;

import java.util.*;

public class Frequency {
	public String charset;
	public char ch;				//统计的字符
	public double weight;		//统计字符的频率，即结点的权重
	public String fileStr;
	Frequency[] fz = null; 
	
	public String toString(){	//返回某个Frequency的字符值和权重。
		return "字符" + ch + "\t频率" + weight;
	}
	

	public void frequency(String fileStr){//传入字符串，统计其中字符出现频率
		this.fileStr=fileStr;
		StringBuilder sb = new StringBuilder();//程序对附加字符串的需求很频繁，使用StringBuider来提高效率
		List<Frequency> list = new ArrayList<Frequency>();//创建list集合，类型为Frequency
		here:
			for(int i=0;i<fileStr.length();i++){
				char ch = fileStr.charAt(i);
				for(Frequency f :list){
					if(f.ch==ch){
						f.weight++;
						continue here;//继续进行here部分的循环
					}
				}
				Frequency co = new Frequency();
				co.ch = ch;
				co.weight = 1;
				list.add(co);
				sb.append(ch);
			}
		
		int sum = fileStr.length();	//总字符数
		for(Frequency c : list)
			c.weight = c.weight/sum;//权重=字符数/总字符数
		fz=list.toArray(new Frequency[list.size()]);
		this.charset = sb.toString();
	}

//	public static void main(String[] args){ //测试频率表类是否能运行
//		Frequency fy=new Frequency();
//		fy.frequency("abcdef");
//		for(Frequency f :fy.fz){
//		System.out.print(f.weight);
//		}
//			
//	}

}
