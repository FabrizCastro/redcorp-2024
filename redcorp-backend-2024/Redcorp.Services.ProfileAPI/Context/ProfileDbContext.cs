using Microsoft.EntityFrameworkCore;
using Redcorp.Services.ProfileAPI.Models;

namespace Redcorp.Services.ProfileAPI.Context 
{
    public class ProfileDbContext : DbContext
    {
        public ProfileDbContext()
        {

        }

        public ProfileDbContext(DbContextOptions<ProfileDbContext> options) : base(options) { }

        public DbSet<Employee> Employees { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
                var serverVersion = new MySqlServerVersion(new Version(8, 0, 29));
                optionsBuilder.UseMySql("Server=localhost,3306;Uid=root;Pwd=12345678;Database=redcorp_profile_servicesdb;", serverVersion);

            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);
            modelBuilder.Entity<Employee>().ToTable("Employees");
            modelBuilder.Entity<Employee>().HasKey(p => p.id);
            modelBuilder.Entity<Employee>().Property(p => p.id).IsRequired().ValueGeneratedOnAdd();
            modelBuilder.Entity<Employee>().Property(p => p.name).IsRequired().HasMaxLength(30);
            modelBuilder.Entity<Employee>().Property(p => p.last_name).IsRequired().HasMaxLength(30);
            modelBuilder.Entity<Employee>().Property(p => p.dni).IsRequired().HasMaxLength(8);
            modelBuilder.Entity<Employee>().Property(p => p.email).IsRequired();
            modelBuilder.Entity<Employee>().Property(p => p.area).IsRequired();
            modelBuilder.Entity<Employee>().Property(p => p.cargo).IsRequired();
            modelBuilder.Entity<Employee>().Property(p => p.rol).IsRequired();
            modelBuilder.Entity<Employee>().Property(p => p.isActive).HasDefaultValue(true);
        }
    }
}
