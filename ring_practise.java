import java.util.Scanner;

public class ring_practise 
{
	static boolean[] state=new boolean[5];
	static int coordinator=5;
	
	public static void main(String[] args)
	{
		for(int i=0;i<5;i++)
			state[i]=true;
		
		System.out.println("5 active processes are: p1 p2 p3 p4 p5\n"
				+ "Process 5 is the coordinator");
			
		int ch;
		Scanner sc=new Scanner(System.in);
		do
		{
			System.out.println();
			System.out.print("Select from below: \n"
					+ " 1. Election\n 2. Quit\n\n"
					+ "Enter your choice: ");
			ch=sc.nextInt();
			System.out.println();
			
			switch(ch)
			{
				case 1: 
					state[coordinator-1]=false;
					System.out.println("Current coordinator "+coordinator+" is down !");
					System.out.println("Which process wants to hold election? ");
					int elect=sc.nextInt();
					election(elect);
					break;
					
				case 2: System.out.println("Program terminated...");
					break;
				
				default: System.out.println("Please enter choice from 1 and 2 only !!");
			}
		}while(ch!=2);
		sc.close();
	}
	
	public static void election(int up)
	{
		int pid=up-1;
		if(state[pid]!=true)
			System.out.println("process is down !");	
		else
		{
			int[] arr=new int[5];
			int index=0;
			System.out.println("\nElection message sent from:");
			
			for(int i=pid,j=-1;j!=(pid+1)%5;)
			{
				if(j==-1)
					j=(i+1)%5;
				if(state[j]!=false)
				{
					int process1=(i+1)%5;
					int process2=(j+1)%5;
					
					System.out.println("Process "+process1+" -> "+process2);
					
					arr[index++]=i;
					i=j;
					j=(j+1)%5;
				}
				else
					j=(j+1)%5;
			}
			
			int max=arr[0];
			for(int i=1;i<5;i++)
			{
				if(arr[i]>max)
					max=arr[i];
			}
			coordinator=max+1;
			System.out.println("\nProcess "+coordinator+" is elected as the new coordinator");
		}
	}
}
