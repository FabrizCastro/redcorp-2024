using System.ComponentModel.DataAnnotations;

namespace Redcorp.Services.AuthAndProfileAPI.Models.Dto
{
    public class EmployeeRequestPutDto
    {

        public string? name { get; set; }

        public string? last_name { get; set; }
        public string? dni { get; set; }

        public string? email { get; set; }
        public string? area { get; set; }
  
        public string? cargo { get; set; }

        public string? photo { get; set; }
    }
}
