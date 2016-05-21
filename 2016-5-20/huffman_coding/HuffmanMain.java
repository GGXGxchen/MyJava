package huffman_coding;

import java.util.*;
//import java.io.*;
public class HuffmanMain {
	private String inFilePath;   //哈夫曼编码输出目录
	private String outFilePath;  //哈夫曼译码输出目录
	private Frequency fy = new Frequency();	
	public HuffmanMain(){		 //哈夫曼压缩方法
		Scanner sc = new Scanner(System.in); 
		System.out.println("请输入源文本路径（格式如：D：/text.txt）");
		String filePath = sc.next();         
		fy.frequency(FileIO.readFile(filePath));
//		for(Frequency f :fy.fz){
//			System.out.println(f.ch+","+f.weight);
//		}
		HuffmanTree hft=new HuffmanTree(fy);
		System.out.println("请输入存放编码文本路径（格式如：D：/huff.txt）");
		this.inFilePath = sc.next();
		
		System.out.println("请输入存放解码文本路径（格式如：D：/decode.txt）");
		this.outFilePath = sc.next();
		FileIO.readFile(filePath);
		
		int in = 0,in2 = 0;
		here:
			while(true){
				System.out.println("请输入下一步操作对应的数字编号：\n 1.查看字符使用频率   2.查看编码表  3.压缩文本  4.查看压缩比  5.解压文本  6.退出");
				in = sc.nextInt();
				switch(in){
				case 1:
					hft.getFy();//获取字符串的频率表
					break;
				case 2:
					hft.getHcodeMap();//获取哈夫曼树编码表
					break;
				case 3:
					hft.encode(inFilePath);
					break;
				case 4:
					if(hft.ratio<0)
						hft.encode(inFilePath);
					hft.getRatio();
					break;
				case 5:
					hft.decode(inFilePath,outFilePath);
					break;
				case 6:
					System.out.println("确认退出？   1、是        2、否");
					in2 = sc.nextInt();
					switch(in2){
					case 1:
						System.out.println("GoodBye!");
						System.exit(0);
					case 2:
						continue here;
					default :
						System.out.println("请重新输入正确的指令！\n");
						continue here;
					}
					
//					break here;
				default :
					System.out.println("请重新输入正确的指令！\n");
					continue here;
				}
			}
	}
	public static void main(String[] args){
		new HuffmanMain();
	}

	


}
