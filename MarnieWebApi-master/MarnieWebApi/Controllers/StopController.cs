using MarnieWebApi.DbAccess;
using MarnieWebApi.Models;
using System.Collections.Generic;
using System.Web.Http;

namespace MarnieWebApi.Controllers
{
    public class StopController : ApiController
    {
        private DbStop db = new DbStop();

        // GET: api/Stop
        public IEnumerable<Stop> Get()
        {
            return db.GetAllWithRelations();
        }

        // GET: api/Stop/5
        public Stop Get(int id)
        {
            return db.GetWithRelations(id);
        }

        // POST: api/Stop
        public void Post([FromBody]Stop stop)
        {
            db.Insert(stop);
        }

        // PUT: api/Stop/5
        public void Put([FromBody]Stop stop)
        {
            db.Update(stop);
        }

        // DELETE: api/Stop/5
        public void Delete(int id)
        {
            db.Delete(id);
        }
    }
}
