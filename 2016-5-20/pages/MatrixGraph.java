package pages;

import java.util.Scanner;

public class MatrixGraph {						//带权图类 SeqLise类为顶点集合,Matrix类为边的集合
	private final int MAX_WEIGHT = 0x0000ffff;  //最大权值，容错，视为无边
	private SeqList vertex;      //顶点顺序表
	private Matrix matrix;       //邻接矩阵代表的图
	
	public MatrixGraph(){
		int countVillage = 0;  //顶点树（村庄数目）
		int countEdge = 0;    //边数量（道路数目）
		
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入村庄数目: ");
		countVillage = sc.nextInt();
		
		System.out.print("\n请输入道路条数: ");
		countEdge = sc.nextInt();
		
		String[] villages = new String[countVillage];//村庄数组
		Triple[] roads = new  Triple[countEdge];  //道路数组
	
		for(int i=0;i<countVillage;i++){
			System.out.print("请输入第"+(i+1)+"个村庄的代号");
				villages[i] = sc.next();
			System.out.println();
		}
		
		System.out.println("请分别输入第x个村庄到第y个村庄的道路长度z，并用减号分开，格式：“(x-1)-(y-1)-z”例“0-1-2”,第一个顶点下标为0");//传入值为int类型的原因如下
		here:
			for(int i=0;i<countEdge;i++){
				
				System.out.print("请输入第"+(i+1)+"条道路的起点，终点，长度");
				String[] inStr = sc.next().split("-");//用“-”作为分隔符
				if(inStr.length > 3){
					System.out.println("输入错误，请重新输入");
					i =i-1;
					continue here;
					
				}else
				roads[i] = new Triple(Integer.parseInt(inStr[0]),Integer.parseInt(inStr[1]),Integer.parseInt(inStr[2]));//Triple的构造方法传入值为int
			}
		
		System.out.println("\n");
		createMatrixGraph(villages,roads);
		shortestRoad();//计算两点间最短路径
	}

	public void createMatrixGraph(String[] village,Triple[] road){  //根据村庄和道路数组类创建图
		this.vertex = new SeqList(village.length);
		this.matrix = new Matrix(road.length);
		
		for(int i=0;i<village.length;i++){//将传入的村庄插入图中的顶点
			this.insertVertex(village[i]);
		}
		
		for(int i=0;i<road.length;i++){//将传入的道路插入到图中的边
			this.insertEdge(road[i]);
		}
	}
			
	public int insertVertex(String x){  //插入顶点（村庄）到顶点顺序表中，返回x的位置
		int i = this.vertex.insert(x);	//顶点顺序表的插入方法，插入队末
		for(int j=0;j<i;j++){			//初始化i行、列元素为无穷（视为无边）
			this.matrix.set(i, j,MAX_WEIGHT);
			this.matrix.set(j, i,MAX_WEIGHT);
		}
		return i;
	}
	
	public void insertEdge(Triple road){  //无指定位置时插入边（道路）
		this.insertEdge(road.row,road.column,road.value);
	}
	
	public void insertEdge(int i,int j,int weight){  //插入指定边（道路）
		if(i!=j){									 //不能自身环（原地没有道路）
			if(weight<=0)							 //权重容错，视为无边
				weight = MAX_WEIGHT;
			this.matrix.set(i, j, weight);		     //设矩阵（i，j）元素权值为weight			
		}else throw new IllegalArgumentException("不能插入自身环，i="+i+",j="+j);
	}
	
	public int getWeight(int i,int j){     //通过矩阵的get()方法获取指定边的权值
		return this.matrix.get(i, j);
	}
					
	public void shortestRoad(){   	//计算每对顶点的最短路径
		int n = this.vertex.size();	//顶点数
		Matrix path = new Matrix(n),dist = new Matrix(n);//新建临时矩阵
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){						 //遍历权值并赋予临时矩阵
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
	private void targetPlace(Matrix dist){   //计算偏心度并求出目标顶点
		int n = this.vertex.size();
		int[] maxDist = new int[n];			 //最大距离数组
		
		for(int i=0;i<n;i++){
			int num = -1;
			int sum = 0;
			for(int j=0;j<n;j++){		//计算其他村庄到i的距离之和
				if(num<dist.get(j, i))	
					num = dist.get(j, i);
				if(dist.get(j, i)!=MAX_WEIGHT&&sum!=MAX_WEIGHT)
					sum+=dist.get(j, i);
				else
					sum = MAX_WEIGHT;
			}
			if(sum!=MAX_WEIGHT)
				System.out.println("其他村庄到"+vertex.get(i)+"的距离之和为:"+sum);
			else
				System.out.println("其他村庄中有无法到达"+vertex.get(i)+"的");
			maxDist[i] = num;//将每个地点到i最大的权重赋予maxDist[i]
		}
		
		System.out.println();//换行分隔
		for(int i=0;i<n;i++){
			System.out.println("顶点"+vertex.get(i)+"偏心度为:"+maxDist[i]);
		}
		
		int num = maxDist[0];
		int index = 0;
		for(int i=1;i<n;i++){
			if(num>maxDist[i]){
				num = maxDist[i];
				index = i;
			}
		}
		System.out.println("建造地址在："+vertex.get(index)+"村");
	}
	public static void main(String[] args){//测试
		new MatrixGraph();
	}

}
