package huffman_coding;

import java.util.*;
//import java.io.*;
public class HuffmanMain {
	private String inFilePath;   //�������������Ŀ¼
	private String outFilePath;  //�������������Ŀ¼
	private Frequency fy = new Frequency();	
	public HuffmanMain(){		 //������ѹ������
		Scanner sc = new Scanner(System.in); 
		System.out.println("������Դ�ı�·������ʽ�磺D��/text.txt��");
		String filePath = sc.next();         
		fy.frequency(FileIO.readFile(filePath));
//		for(Frequency f :fy.fz){
//			System.out.println(f.ch+","+f.weight);
//		}
		HuffmanTree hft=new HuffmanTree(fy);
		System.out.println("�������ű����ı�·������ʽ�磺D��/huff.txt��");
		this.inFilePath = sc.next();
		
		System.out.println("�������Ž����ı�·������ʽ�磺D��/decode.txt��");
		this.outFilePath = sc.next();
		FileIO.readFile(filePath);
		
		int in = 0,in2 = 0;
		here:
			while(true){
				System.out.println("��������һ��������Ӧ�����ֱ�ţ�\n 1.�鿴�ַ�ʹ��Ƶ��   2.�鿴�����  3.ѹ���ı�  4.�鿴ѹ����  5.��ѹ�ı�  6.�˳�");
				in = sc.nextInt();
				switch(in){
				case 1:
					hft.getFy();//��ȡ�ַ�����Ƶ�ʱ�
					break;
				case 2:
					hft.getHcodeMap();//��ȡ�������������
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
					System.out.println("ȷ���˳���   1����        2����");
					in2 = sc.nextInt();
					switch(in2){
					case 1:
						System.out.println("GoodBye!");
						System.exit(0);
					case 2:
						continue here;
					default :
						System.out.println("������������ȷ��ָ�\n");
						continue here;
					}
					
//					break here;
				default :
					System.out.println("������������ȷ��ָ�\n");
					continue here;
				}
			}
	}
	public static void main(String[] args){
		new HuffmanMain();
	}

	


}
