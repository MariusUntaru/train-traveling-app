namespace MarnieWebApi.Models
{
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;

    [Table("Route")]
    public partial class Route
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Route()
        {
            
        }

        public Route(string name, ICollection<Stop> stops)
        {
            Name = name;
            Stops = stops;
        }

        public Route(int id, string name, ICollection<Stop> stops)
        {
            Id = id;
            Name = name;
            Stops = stops;
        }

        public int Id { get; set; }

        [Required]
        [StringLength(50)]
        public string Name { get; set; }

        [NotMapped]
        public Stop StopFrom { get; set; }
        [NotMapped]
        public Stop StopTo { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public ICollection<Stop> Stops { get; set; } = new List<Stop>();
    }
}
