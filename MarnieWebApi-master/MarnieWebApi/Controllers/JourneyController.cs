using System;
using MarnieWebApi.DbAccess;
using MarnieWebApi.Models;
using System.Collections.Generic;
using System.Web.Http;
namespace MarnieWebApi.Controllers
{
    public class JourneyController : ApiController
    {
        private DbJourney _db = new DbJourney();

        // GET: api/Journey
        public IEnumerable<Journey> Get()
        {
            return _db.GetAllWithRelations();
        }

        // GET: api/Journey/5
        public List<Journey> Get(int personId)
        {
            return _db.GetMyJourneyList(personId);
        }
        // GET: api/Journey/
        public List<Journey> GetJourneysByRouteAndTime(int routeId,int personId, DateTime myStart, DateTime myStop)
        {
            return _db.GetJourneysByRouteAndTime(routeId, personId, myStart, myStop);
        }

        // POST: api/Journey
        public void Post([FromBody]Journey journey)
        {            
            _db.Insert(journey);
        }

        // PUT: api/Journey/5
        public void Put([FromBody]Journey journey)
        {
            _db.Update(journey);
        }

        // DELETE: api/Journey/5
        public void Delete(int id)
        {
            _db.Delete(id);
        }
    }
}