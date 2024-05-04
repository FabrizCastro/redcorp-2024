using System.ComponentModel.DataAnnotations;

namespace Redcorp.Services.WorkAndProyectAPI.Models.Dto
{
    public class ProjectRequest
    {

        public string Name { get; set; }
        public string Description { get; set; }
        public string InitialDate { get; set; }
        public string FinalDate { get; set; }
        public string State { get; set; }
    }
}
