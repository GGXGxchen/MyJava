package pages;

import java.util.Scanner;

public class MatrixGraph {						//��Ȩͼ�� SeqLise��Ϊ���㼯��,Matrix��Ϊ�ߵļ���
	private final int MAX_WEIGHT = 0x0000ffff;  //���Ȩֵ���ݴ���Ϊ�ޱ�
	private SeqList vertex;      //����˳���
	private Matrix matrix;       //�ڽӾ�������ͼ
	
	public MatrixGraph(){
		int countVillage = 0;  //����������ׯ��Ŀ��
		int countEdge = 0;    //����������·��Ŀ��
		
		Scanner sc = new Scanner(System.in);
		System.out.print("�������ׯ��Ŀ: ");
		countVillage = sc.nextInt();
		
		System.out.print("\n�������·����: ");
		countEdge = sc.nextInt();
		
		String[] villages = new String[countVillage];//��ׯ����
		Triple[] roads = new  Triple[countEdge];  //��·����
	
		for(int i=0;i<countVillage;i++){
			System.out.print("�������"+(i+1)+"����ׯ�Ĵ���");
				villages[i] = sc.next();
			System.out.println();
		}
		
		System.out.println("��ֱ������x����ׯ����y����ׯ�ĵ�·����z�����ü��ŷֿ�����ʽ����(x-1)-(y-1)-z������0-1-2��,��һ�������±�Ϊ0");//����ֵΪint���͵�ԭ������
		here:
			for(int i=0;i<countEdge;i++){
				
				System.out.print("�������"+(i+1)+"����·����㣬�յ㣬����");
				String[] inStr = sc.next().split("-");//�á�-����Ϊ�ָ���
				if(inStr.length > 3){
					System.out.println("�����������������");
					i =i-1;
					continue here;
					
				}else
				roads[i] = new Triple(Integer.parseInt(inStr[0]),Integer.parseInt(inStr[1]),Integer.parseInt(inStr[2]));//Triple�Ĺ��췽������ֵΪint
			}
		
		System.out.println("\n");
		createMatrixGraph(villages,roads);
		shortestRoad();//������������·��
	}

	public void createMatrixGraph(String[] village,Triple[] road){  //���ݴ�ׯ�͵�·�����ഴ��ͼ
		this.vertex = new SeqList(village.length);
		this.matrix = new Matrix(road.length);
		
		for(int i=0;i<village.length;i++){//������Ĵ�ׯ����ͼ�еĶ���
			this.insertVertex(village[i]);
		}
		
		for(int i=0;i<road.length;i++){//������ĵ�·���뵽ͼ�еı�
			this.insertEdge(road[i]);
		}
	}
			
	public int insertVertex(String x){  //���붥�㣨��ׯ��������˳����У�����x��λ��
		int i = this.vertex.insert(x);	//����˳���Ĳ��뷽���������ĩ
		for(int j=0;j<i;j++){			//��ʼ��i�С���Ԫ��Ϊ�����Ϊ�ޱߣ�
			this.matrix.set(i, j,MAX_WEIGHT);
			this.matrix.set(j, i,MAX_WEIGHT);
		}
		return i;
	}
	
	public void insertEdge(Triple road){  //��ָ��λ��ʱ����ߣ���·��
		this.insertEdge(road.row,road.column,road.value);
	}
	
	public void insertEdge(int i,int j,int weight){  //����ָ���ߣ���·��
		if(i!=j){									 //����������ԭ��û�е�·��
			if(weight<=0)							 //Ȩ���ݴ���Ϊ�ޱ�
				weight = MAX_WEIGHT;
			this.matrix.set(i, j, weight);		     //�����i��j��Ԫ��ȨֵΪweight			
		}else throw new IllegalArgumentException("���ܲ���������i="+i+",j="+j);
	}
	
	public int getWeight(int i,int j){     //ͨ�������get()������ȡָ���ߵ�Ȩֵ
		return this.matrix.get(i, j);
	}
					
	public void shortestRoad(){   	//����ÿ�Զ�������·��
		int n = this.vertex.size();	//������
		Matrix path = new Matrix(n),dist = new Matrix(n);//�½���ʱ����
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){						 //����Ȩֵ��������ʱ����
				int w = this.getWeight(i, j);
				dist.set(i, j, w);						 //
				path.set(i, j, (i!=j&&w<MAX_WEIGHT?i:-1));
			}
		}
		for(int k =0;k<n;k++){
			for(int i=0;i<n;i++){
				if(i!=k){
					for(int j=0;j<n;j++){
						if(j!=k&&j!=i&&dist.get(i, j)>dist.get(i, k)+dist.get(k, j)){
							dist.set(i, j, dist.get(i, k)+dist.get(k, j));
							path.set(i, j, path.get(k, j));
						}
					}
				}
			}
		}
		targetPlace(dist);
	}
	private void targetPlace(Matrix dist){   //����ƫ�ĶȲ����Ŀ�궥��
		int n = this.vertex.size();
		int[] maxDist = new int[n];			 //����������
		
		for(int i=0;i<n;i++){
			int num = -1;
			int sum = 0;
			for(int j=0;j<n;j++){		//����������ׯ��i�ľ���֮��
				if(num<dist.get(j, i))	
					num = dist.get(j, i);
				if(dist.get(j, i)!=MAX_WEIGHT&&sum!=MAX_WEIGHT)
					sum+=dist.get(j, i);
				else
					sum = MAX_WEIGHT;
			}
			if(sum!=MAX_WEIGHT)
				System.out.println("������ׯ��"+vertex.get(i)+"�ľ���֮��Ϊ:"+sum);
			else
				System.out.println("������ׯ�����޷�����"+vertex.get(i)+"��");
			maxDist[i] = num;//��ÿ���ص㵽i����Ȩ�ظ���maxDist[i]
		}
		
		System.out.println();//���зָ�
		for(int i=0;i<n;i++){
			System.out.println("����"+vertex.get(i)+"ƫ�Ķ�Ϊ:"+maxDist[i]);
		}
		
		int num = maxDist[0];
		int index = 0;
		for(int i=1;i<n;i++){
			if(num>maxDist[i]){
				num = maxDist[i];
				index = i;
			}
		}
		System.out.println("�����ַ�ڣ�"+vertex.get(index)+"��");
	}
	public static void main(String[] args){//����
		new MatrixGraph();
	}

}
