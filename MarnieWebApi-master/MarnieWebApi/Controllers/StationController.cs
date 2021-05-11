using MarnieWebApi.DbAccess;
using MarnieWebApi.Models;
using System.Collections.Generic;
using System.Web.Http;

namespace MarnieWebApi.Controllers
{
    public class StationController : ApiController
    {
        private DbStation db = new DbStation();

        // GET: api/Station
        public IEnumerable<Station> Get()
        {
            return db.GetAllWithRelations();
        }

        // GET: api/Station/5
        public Station Get(int id)
        {
            return db.GetWithRelations(id);
        }


        // GET: api/Station
       
        public Station GetNearestStation(double latitude , double longitude)
        {
            return db.GetNearestStation(latitude, longitude);
        }

        // POST: api/Station
        public void Post([FromBody]Station station)
        {
            db.Insert(station);
        }

        // PUT: api/Station/5
        public void Put([FromBody]Station station)
        {
            db.Update(station);
        }

        // DELETE: api/Station/5
        public void Delete(int id)
        {
            db.Delete(id);
        }
    }
}
