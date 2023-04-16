package railwaysystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.*;

public class Ticketbooker {
      static int lowerberth=1;
      static int upperberth=1;
      static int middlebert=1;
      static int waiting=1;
      static int rac=1;
      
      static Queue<Integer> waitinglist=new LinkedList<>();
      static Queue<Integer> raclist=new LinkedList<>();
      static List<Integer> bookedTicketList=new ArrayList<>();
      
      static List<Integer> upperposition=new ArrayList<>(Arrays.asList(1));
      static List<Integer> lowerposition=new ArrayList<>(Arrays.asList(1));
      static List<Integer> middleposition=new ArrayList<>(Arrays.asList(1));
      static List<Integer> racposition=new ArrayList<>(Arrays.asList(1));
      static List<Integer> waitingposition=new ArrayList<>(Arrays.asList(1));
      
      
      
      static Map<Integer,Passenger> passengers=new HashMap<>();
      
      public static void bookticket(Passenger p,int berthinfo,String allotedBerth)
      {
    	  p.number=berthinfo;
    	  p.allocated=allotedBerth;
    	  passengers.put(p.passengerid, p);
    	  bookedTicketList.add(p.passengerid);
    	  System.out.println("--------------------Booked successfully");
    	  
      }
      public void addtoRac(Passenger p,int racinfo,String allotedrac)
      {
    	  p.number=racinfo;
    	  p.allocated=allotedrac;
    	  passengers.put(p.passengerid,p);
    	  raclist.add(p.passengerid);
    	  rac--;
    	  racposition.remove(0);
    	  System.out.println("----added to rac");
      }
      public void addtowaiting(Passenger p,int waitinginfo,String alloctedwl)
      {
    	  p.number=waitinginfo;
    	  p.allocated=alloctedwl;
     	  passengers.put(p.passengerid,p);
     	  waitinglist.add(p.passengerid);
     	  waiting--;
     	 waitingposition.remove(0);
    	  System.out.println("----added to waitinglist");
      }
      public void printAvailable()
      {
          System.out.println("Available Lower Berths "  + lowerberth);
          System.out.println("Available Middle Berths "  + middlebert);
          System.out.println("Available Upper Berths "  + upperberth);
          System.out.println("Availabel RACs " + rac);
          System.out.println("Available Waiting List " + waiting);
          System.out.println("--------------------------");
      }
      public void printPassengers()
      {
          if(passengers.size() == 0)
          {
              System.out.println("No details of passengers");
              return;
          }
          for(Passenger p : passengers.values())
          {
              System.out.println("PASSENGER ID " + p.id );
              System.out.println(" Name " + p.name );
              System.out.println(" Age " + p.age );
              System.out.println(" Status " + p.number + p.allocated);
              System.out.println("--------------------------");
          }
      }
      public void cancelticket(int passengerId)
      {
          //remove the passenger from the map
          Passenger p = passengers.get(passengerId);
          passengers.remove(Integer.valueOf(passengerId));
          //remove the booked ticket from the list
          bookedTicketList.remove(Integer.valueOf(passengerId));

          //take the booked position which is now free
          int positionBooked = p.number;

          System.out.println("---------------cancelled Successfully");

          //add the free position to the correspoding type of list (either L,M,U)
          if(p.allocated.equals("L")) 
          { 
            lowerberth++;
            lowerposition.add(positionBooked);
          }
          else if(p.allocated.equals("M"))
          { 
            middlebert++;
            middleposition.add(positionBooked);
          }
          else if(p.allocated.equals("U"))
          { 
            upperberth++;
            upperposition.add(positionBooked);
          }

          //check if any RAC is there
          if(raclist.size() > 0)
          {
              //take passenger from RAC and increase the free space in RAC list and increase available RAC tickets
              Passenger passengerFromRAC = passengers.get(raclist.poll());
              int positionRac = passengerFromRAC.number;
              racposition.add(positionRac);
              racposition.remove(Integer.valueOf(passengerFromRAC.passengerid));
              rac++;

              //check if any WL is there
              if(waitinglist.size()>0)
              {
                  //take the passenger from WL and add them to RAC , increase the free space in waiting list and 
                  //increase available WL and decrease available RAC by 1
                  Passenger passengerFromWaitingList = passengers.get(waitinglist.poll());
                  int positionWL = passengerFromWaitingList.number;
                  waitinglist.add(positionWL);
                  waitinglist.remove(Integer.valueOf(passengerFromWaitingList.passengerid));

                  passengerFromWaitingList.number = racposition.get(0);
                  passengerFromWaitingList.allocated = "RAC";
                  racposition.remove(0);
                  raclist.add(passengerFromWaitingList.passengerid);
                  
                  waiting++;
                  rac--;
              }
              // now we have a passenger from RAc to whom we can book a ticket, 
              //so book the cancelled ticket to the RAC passenger
              Main.bookticket(passengerFromRAC);
          }
      
      }
      
}
