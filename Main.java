package railwaysystem;

import java.util.Scanner;

public class Main {
	public static void bookticket(Passenger p)
	{
		Ticketbooker booker=new Ticketbooker();
		if(Ticketbooker.waiting==0)
		{
			System.out.println("no tickets available");
			return;
		}
		if(p.berthpreference.equals("L")&&Ticketbooker.lowerberth>0||
				p.berthpreference.equals("M")&&Ticketbooker.middlebert>0||
				p.berthpreference.equals("U")&&Ticketbooker.lowerberth>0)
		{
			System.out.println("preferred berth available");
			if(p.berthpreference.equals("L"))
			{
				System.out.println("Lower berth given");
				booker.bookticket(p,(booker.lowerposition.get(0)) , "L");
				booker.lowerposition.remove(0);
				booker.lowerberth--;
			}
			else if(p.berthpreference.equals("M"))
			{
				System.out.println("middle berth given");
				booker.bookticket(p,(booker.middleposition.get(0)) , "L");
				booker.middleposition.remove(0);
				booker.middlebert--;
			}
			else if(p.berthpreference.equals("U"))
			{
				System.out.println("upper berth given");
				booker.bookticket(p,(booker.upperposition.get(0)) , "L");
				booker.upperposition.remove(0);
				booker.upperberth--;
			}
		}
		else if(Ticketbooker.lowerberth>0)
		{
			System.out.println("Lower berth given");
			booker.bookticket(p,(booker.lowerposition.get(0)) , "L");
			booker.lowerposition.remove(0);
			booker.lowerberth--;
		}
		else if(Ticketbooker.middlebert>0)
		{
			System.out.println("middle berth given");
			booker.bookticket(p,(booker.middleposition.get(0)) , "L");
			booker.middleposition.remove(0);
			booker.middlebert--;
		}
		else if(Ticketbooker.upperberth>0)
		{
			System.out.println("upper berth given");
			booker.bookticket(p,(booker.upperposition.get(0)) , "L");
			booker.upperposition.remove(0);
			booker.upperberth--;
		}
		else if(Ticketbooker.rac>0)
		{
			System.out.println("rac available");
			booker.addtoRac(p, (Ticketbooker.racposition.get(0)),"rac");
		}
		else if(Ticketbooker.waiting>0)
		{
			System.out.println("waiting available");
			booker.addtowaiting(p, (Ticketbooker.waitingposition.get(0)),"wl");
		}
		
		
	}
    public static void cancelTicket(int id)
    {
        Ticketbooker booker = new Ticketbooker();
        //check if passenger id is valid
        if(!booker.passengers.containsKey(id))
        {
            System.out.println("Passenger detail Unknown");
        }
        else
            booker.cancelticket(id);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		boolean loop=true;
		while(loop)
		{
			System.out.println("enter your choice:");
			System.out.println("1.ticket booking");
			System.out.println("2.view available ticket");
			System.out.println("3.cancel ticket");
			System.out.println("4.view booked ticket");
			System.out.println("5.exit");
			int n=sc.nextInt();
			switch(n)
			{
			case 1:
			{
				System.out.println("enter the passenger name,age and  berth (L,U,M)");
				String name=sc.next();
				int age=sc.nextInt();
				String berth=sc.next();
				Passenger p=new Passenger(name,age,berth);
				bookticket(p);
			}
			break;
				
			case 2:
				Ticketbooker booker = new Ticketbooker();
                booker.printAvailable();
                break;
			case 3:
			{
				System.out.println("enter the passenger id:");
				int nn=sc.nextInt();
				cancelTicket(nn);
			}
			break;
			case 4:
				Ticketbooker booke= new Ticketbooker();
                booke.printPassengers();
                break;
			case 5:
				loop=false;
				break;
				
			}
		}
		
		

	}

}
