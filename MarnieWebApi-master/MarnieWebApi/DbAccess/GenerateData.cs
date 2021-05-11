using MarnieWebApi.DbAccess;
using MarnieWebApi.Models;
using System;
using System.Collections.Generic;
using System.Web.UI.WebControls;

//This class is just to populate the DB with test data.
namespace Marnie.Model
{
    public class GenerateData
    {
        public GenerateData()
        {
            var dbRoute = new DbRoute();
            var dbJourney = new DbJourney();

            Station s1 = (new Station("Aalborg", 57.0430039, 9.91709930000002));
            Station s2 = (new Station("Århus", 56.150356, 10.204572));
            Station s3 = (new Station("Randers", 56.461794, 10.021929));
            Station s4 = (new Station("Vejle", 55.706753, 9.536611));
            Station s5 = (new Station("Hobro", 56.6434139, 9.78294019999998));
            Station s6 = (new Station("Horsens", 55.8627586, 9.83645809999996));
            Station s7 = (new Station("Skanderborg", 56.0436557, 9.92642969999997));

            Stop stop11 = new Stop(1, TimeSpan.Parse("10:01"), TimeSpan.Parse("10:03"));
            Stop stop12 = new Stop(5, TimeSpan.Parse("10:41"), TimeSpan.Parse("10:42"));
            Stop stop13 = new Stop(3, TimeSpan.Parse("10:57"), TimeSpan.Parse("10:58"));
            Stop stop14 = new Stop(2, TimeSpan.Parse("11:35"), TimeSpan.Parse("11:42"));
            Stop stop15 = new Stop(7, TimeSpan.Parse("11:54"), TimeSpan.Parse("11:55"));
            Stop stop16 = new Stop(6, TimeSpan.Parse("12:09"), TimeSpan.Parse("12:10"));
            Stop stop17 = new Stop(4, TimeSpan.Parse("12:26"), TimeSpan.Parse("12:29"));

            Route R1 = new Route("Ic 136", new List<Stop>() { stop11, stop12, stop13, stop14, stop15, stop16, stop17 });


            Stop stop21 = new Stop(1, TimeSpan.Parse("11:01"), TimeSpan.Parse("11:03"));
            Stop stop22 = new Stop(5, TimeSpan.Parse("11:41"), TimeSpan.Parse("11:42"));
            Stop stop23 = new Stop(3, TimeSpan.Parse("11:57"), TimeSpan.Parse("11:58"));
            Stop stop24 = new Stop(2, TimeSpan.Parse("12:35"), TimeSpan.Parse("12:42"));
            Stop stop25 = new Stop(7, TimeSpan.Parse("12:54"), TimeSpan.Parse("12:55"));
            Stop stop26 = new Stop(6, TimeSpan.Parse("13:09"), TimeSpan.Parse("13:10"));
            Stop stop27 = new Stop(4, TimeSpan.Parse("13:26"), TimeSpan.Parse("13:29"));

            Route R2 = new Route("Ic 140", new List<Stop>() { stop21, stop22, stop23, stop24, stop25, stop26, stop27 });

            Stop stop31 = new Stop(1, TimeSpan.Parse("12:01"), TimeSpan.Parse("12:03"));
            Stop stop32 = new Stop(5, TimeSpan.Parse("12:41"), TimeSpan.Parse("12:42"));
            Stop stop33 = new Stop(3, TimeSpan.Parse("12:57"), TimeSpan.Parse("12:58"));
            Stop stop34 = new Stop(2, TimeSpan.Parse("13:35"), TimeSpan.Parse("13:42"));
            Stop stop35 = new Stop(7, TimeSpan.Parse("13:54"), TimeSpan.Parse("13:55"));
            Stop stop36 = new Stop(6, TimeSpan.Parse("14:09"), TimeSpan.Parse("14:10"));
            Stop stop37 = new Stop(4, TimeSpan.Parse("14:26"), TimeSpan.Parse("14:29"));

            Route R3 = new Route("Ic 144", new List<Stop>() { stop31, stop32, stop33, stop34, stop35, stop36, stop37 });
            
            Stop stop41 = new Stop(1, TimeSpan.Parse("13:01"), TimeSpan.Parse("13:03"));
            Stop stop42 = new Stop(5, TimeSpan.Parse("13:41"), TimeSpan.Parse("13:42"));
            Stop stop43 = new Stop(3, TimeSpan.Parse("14:57"), TimeSpan.Parse("13:58"));
            Stop stop44 = new Stop(2, TimeSpan.Parse("14:35"), TimeSpan.Parse("14:42"));
            Stop stop45 = new Stop(7, TimeSpan.Parse("14:54"), TimeSpan.Parse("14:55"));
            Stop stop46 = new Stop(6, TimeSpan.Parse("15:09"), TimeSpan.Parse("15:10"));
            Stop stop47 = new Stop(4, TimeSpan.Parse("15:26"), TimeSpan.Parse("15:29"));

            Route R4 = new Route("Ic 148", new List<Stop>() { stop41, stop42, stop43, stop44, stop45, stop46, stop47 });

            var loc1 = "Aalborg";
            var loc2 = "Århus";
            var loc3 = "Vejle";
            var loc1Time1 = parseTime("10:03");
            var loc2Time1 = parseTime("10:58");
            var loc3Time1 = parseTime("13:29");
            var loc1Time2 = parseTime("11:03");
            var loc2Time2 = parseTime("11:58");
            var loc3Time2 = parseTime("14:29");
            var loc1Time3 = parseTime("12:03");
            var loc2Time3 = parseTime("12:58");
            var loc3Time3 = parseTime("15:29");
            var loc1Time4 = parseTime("13:03");
            var loc2Time4 = parseTime("13:58");
            var loc3Time4 = parseTime("16:29");

            var j1 = new Journey(22, 7, loc1, loc1Time1, loc2, loc2Time1, 0);
            var j2 = new Journey(22, 9, loc1, loc1Time1, loc3, loc3Time1, 0);
            var j3 = new Journey(22, 13, loc1, loc1Time1, loc3, loc3Time1, 2);
            var j4 = new Journey(23, 20, loc2, loc2Time2, loc3, loc3Time2, 0);
            var j5 = new Journey(22, 22, loc2, loc2Time1, loc3, loc3Time1, 0);
            var j6 = new Journey(24, 21 , loc1, loc1Time3, loc3, loc3Time3, 0);

            List<Journey> jList = new List<Journey>() {j1, j2, j3, j4, j5, j6};

            for (int i = 0; i < 6; i++)
            {
                dbJourney.Insert(jList[i]);
            }

            //dbRoute.Insert(R1);
            //dbRoute.Insert(R2);
            //dbRoute.Insert(R3);
            //dbRoute.Insert(R4);
        }

        private DateTime parseTime(string s)
        {
            var date = DateTime.Today;
            var time = TimeSpan.Parse(s);
            return date + time;
        }
    }
}
