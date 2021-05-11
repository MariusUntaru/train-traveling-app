namespace MarnieWebApi.Models
{
    using System.Data.Entity;

    public partial class MyDbContext : DbContext
    {
        public MyDbContext()
            : base("name=MyDbContext")
        {
        }

        public virtual DbSet<Date> Dates { get; set; }
        public virtual DbSet<Journey> Journeys { get; set; }
        public virtual DbSet<Person> Persons { get; set; }
        public virtual DbSet<Route> Routes { get; set; }
        public virtual DbSet<Station> Stations { get; set; }
        public virtual DbSet<Stop> Stops { get; set; }        

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Person>()
                .HasMany(e => e.Dates)
                .WithRequired(e => e.Person1)
                .HasForeignKey(e => e.Person1Id)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<Person>()
                .HasMany(e => e.Dates)
                .WithRequired(e => e.Person2)
                .HasForeignKey(e => e.Person2Id)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<Person>()
                .HasMany(e => e.Journeys)
                .WithRequired(e => e.Person)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<Route>()
                .HasMany(e => e.Stops)
                .WithRequired(e => e.Route)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<Stop>()
                .Property(e => e.ArrivalTime)
                .HasPrecision(0);

            modelBuilder.Entity<Stop>()
                .Property(e => e.DepartureTime)
                .HasPrecision(0);
        }
    }
}