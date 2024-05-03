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
        [Required]
        [MaxLength(8)]
        [MinLength(8)]
        public string? dni { get; set; }

        [Required]
        public string? email { get; set; }
        [Required]
        public string? password { get; set; }
        [Required]
        public string? area { get; set; }
        [Required]
        public string? cargo { get; set; }
    }
}
