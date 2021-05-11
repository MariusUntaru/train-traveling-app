using System;
using MarnieWebApi.Models;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;

namespace MarnieWebApi.DbAccess
{
    public class DbDate : DBInterface<Date>
    {
        public void Delete(int id)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    var date = new Date { Id = id };
                    db.Dates.Attach(date);
                    db.Dates.Remove(date);
                    db.SaveChanges();
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public ICollection<Date> GetAllWithRelations()
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Dates.Include(x => x.Person1).Include(x => x.Person2).Include(x => x.Route).ToList();
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public ICollection<Date> GetMyDates(int personId)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Dates.Where(date => (date.Person1Id == personId | date.Person2Id == personId && date.DateStatus == 1))
                            .Include(x => x.Person1).Include(x => x.Person2).Include(x => x.Route).ToList();
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public Date GetWithRelations(int id)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Dates.Where(x => x.Id == id).Include(x => x.Person1).Include(x => x.Person2).Include(x => x.Route).FirstOrDefault();
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public void Insert(Date inputDate)
        {
            using (var db = new MyDbContext())
            {
                var myDate = db.Dates.SingleOrDefault(date => (date.Person2Id == inputDate.Person1Id && date.Person1Id == inputDate.Person2Id && date.RouteId == inputDate.RouteId && date.StartTime.Equals(inputDate.StartTime)));
                var dateAlreadyInDB = db.Dates.SingleOrDefault(date => (date.Person1Id == inputDate.Person1Id && date.Person2Id == inputDate.Person2Id && date.RouteId == inputDate.RouteId && date.StartTime.Equals(inputDate.StartTime)));
                try
                {
                    if (myDate != null)
                    {
                        myDate.StatusP1 = 1;
                        myDate.StatusP2 = 1;
                        myDate.DateStatus = 1;
                        Update(myDate);
                    }
                    else
                    {
                        if ((dateAlreadyInDB != null))
                        {
                           //http response to client.
                        }
                        else
                        {
                            db.Dates.Add(inputDate);
                            db.SaveChanges();
                        }
                    }
                    
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public void Update(Date inputDate)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    db.Entry(inputDate).State = EntityState.Modified;
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