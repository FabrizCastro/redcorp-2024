using Microsoft.EntityFrameworkCore;
using Redcorp.Services.WorkAndProyectAPI.Models;

namespace Redcorp.Services.WorkAndProyectAPI.Context
{
    public class WorkAndProyectDbContext : DbContext
    {
        public WorkAndProyectDbContext()
        {

        }

        public WorkAndProyectDbContext(DbContextOptions<WorkAndProyectDbContext> options) : base(options) { }
        public DbSet<Section> Sections { get; set; }
        public DbSet<Project> Projects { get; set; }
        public DbSet<Team> Teams { get; set; }
        public DbSet<SectionAndEmployee> SectionsAndEmployees { get; set; }
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
                var serverVersion = new MySqlServerVersion(new Version(8, 0, 29));
                optionsBuilder.UseMySql("Server=localhost,3306;Uid=root;Pwd=12345678;Database=redcorp_work&proyect_servicesdb;", serverVersion);

            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.Entity<Section>().ToTable("Sections");
            modelBuilder.Entity<Section>().HasKey(p => p.Id);
            modelBuilder.Entity<Section>().Property(p => p.Id).IsRequired().ValueGeneratedOnAdd();
            modelBuilder.Entity<Section>().Property(p => p.Description).IsRequired().HasMaxLength(70);
            modelBuilder.Entity<Section>().Property(p => p.IsActive).HasDefaultValue(true);

            modelBuilder.Entity<Project>().ToTable("Projects");
            modelBuilder.Entity<Project>().HasKey(p => p.Id);
            modelBuilder.Entity<Project>().Property(p => p.Id).IsRequired().ValueGeneratedOnAdd();
            modelBuilder.Entity<Project>().Property(p => p.Description).IsRequired().HasMaxLength(70);
            modelBuilder.Entity<Project>().Property(p => p.IsActive).HasDefaultValue(true);
            modelBuilder.Entity<Project>().Property(p => p.InitialDate).HasDefaultValue(DateTime.Now);

            modelBuilder.Entity<Team>().ToTable("Teams");
            modelBuilder.Entity<Team>().HasKey(p => p.Id);
            modelBuilder.Entity<Team>().Property(p => p.Id).IsRequired().ValueGeneratedOnAdd();
            modelBuilder.Entity<Team>().Property(p => p.Description).IsRequired().HasMaxLength(70);
            modelBuilder.Entity<Team>().Property(p => p.IsActive).HasDefaultValue(true);

            modelBuilder.Entity<SectionAndEmployee>().ToTable("SectionsAndEmployees");
            modelBuilder.Entity<SectionAndEmployee>().HasKey(p => p.Id);
            modelBuilder.Entity<SectionAndEmployee>().Property(p => p.Id).IsRequired().ValueGeneratedOnAdd();
            modelBuilder.Entity<SectionAndEmployee>().Property(p => p.Section_Id).IsRequired();
            modelBuilder.Entity<SectionAndEmployee>().Property(p => p.Employees_Id).IsRequired();
            modelBuilder.Entity<SectionAndEmployee>().Property(p => p.IsActive).HasDefaultValue(true);
        }
    }
}
