using MarnieWebApi.DbAccess;
using MarnieWebApi.Models;
using System.Collections.Generic;
using System.Web.Http;

namespace MarnieWebApi.Controllers
{
    public class DateController : ApiController
    {
        private DbDate db = new DbDate();

        // GET: api/Date
        public IEnumerable<Date> Get()
        {
            return db.GetAllWithRelations();
        }

        // GET: api/Date/5
        public Date Get(int id)
        {
            return db.GetWithRelations(id);
        }

        // GET: api/Date?
        public IEnumerable<Date> GetMyDates(int personId)
        {
            return db.GetMyDates(personId);
        }

        // POST: api/Date
        public void Post([FromBody]Date date)
        {
            db.Insert(date);
        }

        // PUT: api/Date/5
        public void Put([FromBody]Date date)
        {
            db.Update(date);
        }

        // DELETE: api/Date/5
        public void Delete(int id)
        {
            db.Delete(id);
        }
    }
}