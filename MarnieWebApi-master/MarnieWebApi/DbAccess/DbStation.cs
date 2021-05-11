using MarnieWebApi.Models;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System;
using System.Device.Location;

namespace MarnieWebApi.DbAccess
{
    public class DbStation : DBInterface<Station>
    {
        public void Delete(int id)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    var station = new Station { Id = id };
                    db.Stations.Attach(station);
                    db.Stations.Remove(station);
                    db.SaveChanges();
                }
                catch (Exception e)
                {
                    throw e;
                }
            }
        }

        public Station GetNearestStation(double latitude, double longitude)
        {            
            var personLocation = new GeoCoordinate(latitude, longitude);
            var stationList = GetAll() as IEnumerable<Station>;
            var geo_station = new Dictionary<Station, double>();

            foreach (Station st in stationList)
            {
                var lat = st.Latitude;
                var lon = st.Longitude;
                var stGeo = new GeoCoordinate(lat, lon);//create GeoCoordinate object for each station
                var distance = personLocation.GetDistanceTo(stGeo);// calculate distanse from given koordinates to each station
                geo_station.Add(st, distance); //save station and calculated distance to it in dictionary as key-value pair

            }
            // return geo_station.Aggregate((l, r) => l.Value < r.Value ? l : r).Key;//find the station with minimal distatce in dictionary
            return geo_station.OrderBy(k => k.Value).FirstOrDefault().Key;
        }

        public Station Get(int id)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Stations.Find(id);
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public ICollection<Station> GetAll()
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    return db.Stations.ToList();
                }
                catch (System.Exception e)
                {
                    throw e;
                }
            }
        }

        public ICollection<Station> GetAllWithRelations()
        {
            return GetAll();
        }

        public Station GetWithRelations(int id)
        {
            return Get(id);
        }

        public void Insert(Station item)
        {
            using (var db = new MyDbContext())
            {
                try
                {
                    db.Stations.Add(item);
                    db.SaveChanges();
                }
                catch (System.Exception e)
                {
                    throw e;
                }

            }
        }

        public void Update(Station item)
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
