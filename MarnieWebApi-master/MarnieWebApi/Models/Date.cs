namespace MarnieWebApi.Models
{
    using System;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;

    [Table("Date")]
    public partial class Date
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Date()
        {

        }

        public int Id { get; set; }

        public int RouteId { get; set; }

        [Required]
        [StringLength(50)]
        public string DateStartLocation { get; set; }

        public DateTime StartTime { get; set; }

        [Required]
        [StringLength(50)]
        public string DateDestination { get; set; }

        public DateTime EndTime { get; set; }

        public int Person1Id { get; set; }

        public Person Person1 { get; set; }

        public int Person2Id { get; set; }

        public Person Person2 { get; set; }

        // status: 0= pending, 1 = confirmed, 2 = declined
        public int StatusP1 { get; set; }

        // status: 0= pending, 1 = confirmed, 2 = declined
        public int StatusP2 { get; set; }

        // status: 0= pending, 1 = confirmed, 2 = declined
        public int DateStatus { get; set; }

        public Route Route { get; set; }
    }
}
