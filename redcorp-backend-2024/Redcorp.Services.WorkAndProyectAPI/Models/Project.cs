namespace Redcorp.Services.WorkAndProyectAPI.Models
{
    public class Project
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public DateTime InitialDate { get; set; }
        public DateTime FinalDate { get; set; }
        public string State { get; set; }

        public bool IsActive { get; set; }
    }
}
