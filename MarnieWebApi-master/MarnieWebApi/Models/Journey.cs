using System.Threading;

namespace MarnieWebApi.Models
{
    using System;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;

    [Table("Journey")]
    public partial class Journey
    {
        public Journey(int routeId, int personId, string startLocation, DateTime startTime, string destination, DateTime endTime, int status)
        {
            RouteId = routeId;
            PersonId = personId;
            StartLocation = startLocation;
            StartTime = startTime;
            Destination = destination;
            EndTime = endTime;
            Status = status;
        }

        public Journey()
        {
    
        }

        public int Id { get; set; }

        public int RouteId { get; set; }

        public int PersonId { get; set; }

        [Required]
        [StringLength(50)]
        public string StartLocation { get; set; }

        public DateTime StartTime { get; set; }

        [Required]
        [StringLength(50)]
        public string Destination { get; set; }

        public DateTime EndTime { get; set; }

        //0 = active, 1 = partlyBooked, 2 = no more dates.
        public int Status { get; set; }

        public Person Person { get; set; }

        public Route Route { get; set; }
    }
}
