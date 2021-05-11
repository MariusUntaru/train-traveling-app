using System;
using MarnieWebApi.Models;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Runtime.CompilerServices;

namespace MarnieWebApi.DbAccess
{
    public class DbJourney : DBInterface<Journey>
    {
        public void Delete(int id)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    var journey = new Journey { Id = id };
                    db.Journeys.Attach(journey);
                    db.Journeys.Remove(journey);
                    db.SaveChanges();
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public Journey Get(int id)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Journeys.Find(id);
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public ICollection<Journey> GetAll()
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Journeys.ToList();
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public ICollection<Journey> GetAllWithRelations()
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Journeys.Include(x => x.Person).Include(x => x.Route).ToList();
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public Journey GetWithRelations(int id)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Journeys.Where(x => x.Id == id).Include(x => x.Person).Include(x => x.Route).FirstOrDefault();
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public List<Journey> GetMyJourneyList(int personId)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Journeys.Where(x => x.PersonId == personId).Include(x => x.Person).ToList();
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public List <Journey> GetJourneysByRouteAndTime(int routeId,int personId, DateTime myStart, DateTime myStop)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    var journeys = db.Journeys.Where(x => x.RouteId == routeId && x.PersonId != personId).Include(x => x.Person).Include(x => x.Route).ToList();
                    return journeys.Where(j => IsBetween(myStart, myStop, j.StartTime, j.EndTime)).ToList();
                    
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

       

        private static bool IsBetween(DateTime myStart, DateTime myStop, DateTime start, DateTime end)
        {
            return (end >= myStart && start <= myStop && end != myStart)|| start == myStart || end == myStop;
        }

        public void Insert(Journey myJourney)
        {
            var utc = myJourney.StartTime.ToUniversalTime();
            var local = myJourney.StartTime.ToLocalTime(); 

            myJourney.Route = null;
            myJourney.Person = null;
            using (var db = new MyDbContext())
            {
                try
                {
                    db.Journeys.Add(myJourney);
                    db.SaveChanges();
                }
                catch (System.Exception e)
                {
                    throw e;
                }

            }
        }

        public void Update(Journey item)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    db.Entry(item).State = EntityState.Modified;
                    db.SaveChanges();
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }
    }
}