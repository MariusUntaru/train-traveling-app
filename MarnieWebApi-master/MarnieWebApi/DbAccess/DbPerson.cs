using MarnieWebApi.Models;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System;
using System.Security.Cryptography.X509Certificates;

namespace MarnieWebApi.DbAccess
{
    public class DbPerson : DBInterface<Person>
    {
        public void Delete(int id)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    var person = new Person { Id = id };
                    db.Persons.Attach(person);
                    db.Persons.Remove(person);
                    db.SaveChanges();
                }
                catch (System.Exception e)
                {
                    throw e;
                }                
            }
        }

        public Person GetByAuthId(string id)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Persons.FirstOrDefault(x => x.AuthId == id);
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public List<Person> GetPersonsByRouteIdAndTime(List<Journey> journeys)
        {
            var persons = new List<Person>();
            using (var db = new MyDbContext())
            {
                persons.AddRange(journeys.Select(j => j.PersonId).Select(Get));
            }
            return persons;
        }

        public Person Get(int id)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Persons.Find(id);
                }
                catch (System.Exception e)
                {
                    throw e;
                }                
            }
        }

        public ICollection<Person> GetAll()
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Persons.ToList();
                }
                catch (System.Exception e)
                {
                    throw e;
                }                
            }
        }

        public ICollection<Person> GetAllWithRelations()
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Persons.Include(x => x.Dates).Include(x => x.Journeys).ToList();
                }
                catch (System.Exception e)
                {
                    throw e;
                }                
            }
        }

        public Person GetByAuth(string auth)
        {
            using (var db = new MyDbContext())
            {
                try
                {                    
                    var list = db.Persons.Where(x => x.AuthId.Equals(auth)).Include(x => x.Dates).Include(x => x.Journeys).ToList();
                    return list[0];
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public Person GetWithRelations(int id)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Persons.Where(x => x.Id == id).Include(x => x.Dates).Include(x => x.Journeys).FirstOrDefault();
                }
                catch (System.Exception e)
                {
                    throw e;
                }                
            }
        }

        public void Insert(Person item)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    db.Persons.Add(item);
                    db.SaveChanges();
                }
                catch (System.Exception e)
                {
                    throw e;
                }                
            }
        }

        public void Update(Person item)
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