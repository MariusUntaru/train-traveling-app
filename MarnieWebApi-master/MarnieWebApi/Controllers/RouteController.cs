using System;
using MarnieWebApi.DbAccess;
using MarnieWebApi.Models;
using System.Collections.Generic;
using System.Web.Http;
using System.Net.Http;
using System.Net;

namespace MarnieWebApi.Controllers
{
    public class RouteController : ApiController
    {
        private DbRoute db = new DbRoute();

        // GET: api/Route
        public IEnumerable<Route> Get()
        {
            return db.GetAllWithRelations();
        }

        //GET: api/Route?...
        public IEnumerable<Route> Get(string from, string to, DateTime startTime)
        {
            try
            {
                return db.FindRoutes(from, to, startTime);
            }
            catch (Exception e)
            {
                throw e;
            }
        }

        // GET: api/Route/5
        public Route Get(int id)
        {
            return db.GetWithRelations(id);
        }

        // POST: api/Route
        public void Post(Route route)
        {
            db.Insert(route);
        }

        // PUT: api/Route/5
        public void Put([FromBody]Route route)
        {
            db.Update(route);
        }

        // DELETE: api/Route/5
        public void Delete(int id)
        {
            db.Delete(id);
        }
    }
}
