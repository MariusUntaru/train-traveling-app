using System;
using MarnieWebApi.Models;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace MarnieWebApi.DbAccess
{
    public class DbRoute
    {
        public void Delete(int id)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    var route = new Route { Id = id };
                    db.Routes.Attach(route);
                    db.Routes.Remove(route);
                    db.SaveChanges();
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public Route Get(int id)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Routes.Find(id);
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public ICollection<Route> GetAll()
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Routes.ToList();
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public ICollection<Route> GetAllWithRelations()
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Routes.Include(x => x.Stops.Select(y => y.Station)).ToList();
                }
                catch (System.Exception e)
                {

                    throw e;
                }
            }
        }

        public ICollection<Route> FindRoutes(string from, string to, DateTime startTime)
        {
            List<Route> routes = new List<Route>();
            int RouteCounter = 0;
            
            using (var db = new MyDbContext())
            {
                try
                {
                    var tempList = db.Routes.Include(x => x.Stops.Select(y => y.Station)).ToList(); 
                    
                    foreach (var route in tempList)
                    {
                        Stop stopFrom = null;
                        Stop stopTo = null;
                        foreach (var stop in route.Stops)
                        {
                            if (stop.Station.Name.Equals(from)) stopFrom = stop;
                            if (stop.Station.Name.Equals(to)) stopTo = stop;
                        }                        
                        
                        if (stopFrom !=null && stopTo != null)
                        {
                            if (stopFrom.ArrivalTime < stopTo.ArrivalTime)
                            {
                                RouteCounter++;
                                if (stopFrom.DepartureTime >= startTime.TimeOfDay)
                                {                                    
                                    route.StopFrom = stopFrom;
                                    route.StopTo = stopTo;
                                    routes.Add(route);
                                }
                            }
                        }
                    }
                    if(RouteCounter == 0)
                    {
                        throw new HttpResponseException(new HttpResponseMessage(HttpStatusCode.BadRequest)
                        {
                            Content = new StringContent("There is no Route Between these stations"),
                            ReasonPhrase = "BadRequest"
                        });
                    }

                    if(routes.Count == 0)
                    {
                        throw new HttpResponseException(new HttpResponseMessage(HttpStatusCode.BadRequest)
                        {
                            Content = new StringContent("No more trains that day"),
                            ReasonPhrase = "BadRequest"
                        });
                    }
                    return routes;
                }
                catch (System.Exception e)
                {

                    throw e;
                }
            }
        }

        public Route GetWithRelations(int id)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Routes.Where(x => x.Id == id).Include(x => x.Stops).FirstOrDefault();
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public void Insert(Route item)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    db.Routes.Add(item);
                    db.SaveChanges();
                }
                catch (System.Exception e)
                {
                    throw e;
                }

            }
        }

        public void Update(Route item)
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