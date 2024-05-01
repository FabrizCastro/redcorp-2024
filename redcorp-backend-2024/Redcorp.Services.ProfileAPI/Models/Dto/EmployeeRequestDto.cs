using System.ComponentModel.DataAnnotations;

namespace Redcorp.Services.ProfileAPI.Models.Dto
{
    public class EmployeeRequestDto
    {
        [Required]
        [MaxLength(30)]
        [MinLength(3)]
        public string? name { get; set; }
        [Required]
        [MaxLength(30)]
        [MinLength(3)]
        public string? last_name { get; set; }
        public string? email { get; set; }
        public string? password { get; set; }
        public string? area { get; set; }
        public string? cargo { get; set; }
        public string? photo { get; set; }

        public string? rol { get; set; }
    }
}
