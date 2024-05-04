namespace Redcorp.Services.WorkAndProyectAPI.Models
{
    public class Team
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public int Id_Employee { get; set; }
        public int Id_Project { get; set; }
        public int Id_Task { get; set; }

        public bool IsActive { get; set; }
    }
}
