using MarnieWebApi.Models;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;

namespace MarnieWebApi.DbAccess
{
    public class DbStop
    {
        public void Delete(int id)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    var stop = new Stop { Id = id };
                    db.Stops.Attach(stop);
                    db.Stops.Remove(stop);
                    db.SaveChanges();
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public Stop Get(int id)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Stops.Find(id);
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public ICollection<Stop> GetAll()
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Stops.ToList();
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public ICollection<Stop> GetAllWithRelations()
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Stops.Include(x => x.Route).Include(x => x.Station).ToList();
                }
                catch (System.Exception e)
                {

                    throw e;
                }
            }
        }

        public Stop GetWithRelations(int id)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Stops.Where(x => x.Id == id).Include(x => x.Route).Include(x => x.Station).FirstOrDefault();
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public void Insert(Stop item)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    db.Stops.Add(item);
                    db.SaveChanges();
                }
                catch (System.Exception e)
                {
                    throw e;
                }

            }
        }

        public void Update(Stop item)
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