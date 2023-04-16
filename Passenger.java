package railwaysystem;

public class Passenger {
   static int id=1;
   String name;
   int age;
   String berthpreference;
   int passengerid;
   String allocated;
   int number;
   public Passenger(String name,int age,String berthperference)
   {
	   this.name=name;
	   this.age=age;
	   this.berthpreference=berthperference;
	   this.passengerid=id++;
	   allocated="";
	   number=-1;
   }

}
